package com.moxuanran.learning.mysql;

import com.moxuanran.learning.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class RunnerManager implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(RunnerManager.class);

    public final int RUNNER_HEARTBEAT_TIMEOUT = 12000;

    @Autowired
    private RunnerService runnerService;

    @Autowired
    private List<RunWorker> workers;

    private String localIp;

    private volatile int runnerId;

    private ScheduledThreadPoolExecutor scheduleExecutor;

    private ScheduledThreadPoolExecutor expireRunnerExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        localIp = IPUtils.getLocalHost();
        if (StringUtils.isEmpty(localIp)) {
            throw new IllegalArgumentException("can not obtain localhost info ");
        }
        heartBeat();
        scheduleExecutor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "heart-beat-thread"));
        scheduleExecutor.scheduleWithFixedDelay(() -> {
            try {
                heartBeat();
            } catch (Throwable ex) {
                logger.error("schedule hearBeat runner occur error," + ex.getMessage(), ex);
            }
        }, 3, 3, TimeUnit.SECONDS);
        expireRunnerExecutor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "expire-Runner-Thread"));
        expireRunnerExecutor.scheduleWithFixedDelay(() -> {
            try {
                clearExpireRunner();
            } catch (Throwable e) {
                logger.error("expire runner exception, e:" + e.getMessage(), e);
            }
        }, 10, 5, TimeUnit.SECONDS);

        for(RunWorker w:workers){
            w.setRunnerManager(this);
            new Thread(()->w.execute(),w.name()+"-thread").start();
        }
    }

    private void heartBeat() {
        Runner r = runnerService.getRunner(localIp);
        if (null == r) {
            r = new Runner();
            r.setIp(localIp);
            r.setHeartbeat(new Date());
            runnerService.save(r);
        } else {
            r.setHeartbeat(new Date());
            runnerService.update(r);
        }
        if(runnerId != r.getId()) {
            runnerId = r.getId();
        }
    }

    private List<Integer> lastSurviveRunnerIds  = null;
    private long lastCheckTime = 0;
    private void clearExpireRunner() {
        List<Runner> runnerList = runnerService.getAll();
        if (CollectionUtils.isEmpty(runnerList)) {
            return;
        }
        runnerList.sort(Comparator.comparingInt(Runner::getId));
        int masterId = 0;
        List<Integer> expiredRunnerIds = new ArrayList<>();
        List<Integer> surviveRunnerIds = new ArrayList<>();
        long c = System.currentTimeMillis();
        for (Runner r : runnerList) {
            long expireTime =  c - r.getHeartbeat().getTime();
            if (expireTime > RUNNER_HEARTBEAT_TIMEOUT) {
                expiredRunnerIds.add(r.getId());
            } else {
                surviveRunnerIds.add(r.getId());
                if (masterId == 0) {
                    masterId = r.getId();
                }
            }
        }
        boolean flag = true;
        if (masterId == runnerId) {
            if(surviveRunnerIds.size() > 0 && (!comp(lastSurviveRunnerIds,surviveRunnerIds) || ((c - lastCheckTime) > 10000))) {
                lastCheckTime = c;
                for (RunWorker listener : workers) {
                    try {
                        listener.changeHealthRunner(surviveRunnerIds);
                    } catch (Throwable ex) {
                        flag = false;
                        logger.error("notify RunnerExpireListener occur error," + ex.getMessage(), ex);
                    }
                }
                if(flag){
                    lastSurviveRunnerIds = surviveRunnerIds;
                }
            }
            if(expiredRunnerIds.size() > 0){
                runnerService.deleteByIds(expiredRunnerIds);
            }
        }
    }

    public int getRunnerId(){
        return runnerId;
    }

    private boolean comp(List<Integer> old,List<Integer> newList){
        if(null == old){
            return newList == null;
        }else{
            if(null == newList){
                return false;
            }
            int s1 = old.size();
            int s2 = newList.size();
            if(s1 != s2){
                return false;
            }
            for(int i=0;i<s1;i++){
                if(!old.get(i).equals(newList.get(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
