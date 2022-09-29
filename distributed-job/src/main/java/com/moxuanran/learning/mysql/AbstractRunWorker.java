package com.moxuanran.learning.mysql;

import com.google.common.collect.Lists;
import com.moxuanran.learning.util.CollectionUtils;
import com.moxuanran.learning.util.ConcurrentHashSet;
import com.moxuanran.learning.util.NamedThreadFactory;
import com.moxuanran.learning.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 */
public abstract class AbstractRunWorker<T extends BaseModel> implements RunWorker{

    protected Logger logger = LoggerFactory.getLogger(AbstractRunWorker.class);

    public static ThreadPoolExecutor defaultPoolExecutor =
            new ThreadPoolExecutor(20,30,60, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(60),new NamedThreadFactory("async-task-th"));

    private static final int HANDLE_BATCH_SIZE = 50;

    private Random r = new Random();

    private Set<Long> inFlightIds = new ConcurrentHashSet<>();


    private RunnerManager localRunner;

    /**
     *  加载对应的runnerId的任务数据
     * @param runnerId
     * @param excludeIds  任务数据ID，排除这个任务数据
     * @return
     */
    public abstract List<T> loadData(int runnerId, Collection<Long> excludeIds);

    /**
     * 处理任务数据
     * @param records
     */
    public abstract void handle(List<T> records);

    public abstract void raceRecord(int runnerId);


    @Override
    public void setRunnerManager(RunnerManager runnerManager){
        this.localRunner = runnerManager;
    }

    /**
     *  异步任务线程池，有比较重的任务可以设置一个私有的线程池，防止影响到公共业务
     * @return
     */
    protected ThreadPoolExecutor getExecutor(){
        return defaultPoolExecutor;
    }

    /**
     *  handle(List<T> request) requests的最大size
     * @return
     */
    protected int getBatchHandleSize(){ return HANDLE_BATCH_SIZE;}


    long ct = 0;

    @Override
    public void execute(){
        for(;;){
            try {
                doExecute();
            }catch (Throwable ex){
                long c = System.currentTimeMillis();
                if((c - ct) > 15000) {
                    ct = c;
                    logger.error(name() + " schedule execute task occur error," + ex.getMessage(), ex);
                }
                ThreadUtils.sleep(1000 + r.nextInt(800));
            }
        }
    }

    private void doExecute(){
        List<T> lst = loadData(localRunner.getRunnerId(),inFlightIds);
        if(CollectionUtils.isNullOrEmpty(lst)){
            raceRecord(localRunner.getRunnerId());
            lst = loadData(localRunner.getRunnerId(),inFlightIds);
            if(CollectionUtils.isNullOrEmpty(lst)){
                ThreadUtils.sleep(10+r.nextInt(1000));
                return;
            }
        }
        List<List<T>> ls = Lists.partition(lst,getBatchHandleSize());
        for(List<T> rs:ls){
            List<Long> ids = buildIds(rs);
            inFlightIds.addAll(ids);
            for(;;) {
                try {
                    getExecutor().submit(new Worker(ids,rs));
                    break;
                } catch (RejectedExecutionException ex) {
                    ThreadUtils.sleep(1);
                }
            }
        }
    }

    private List<Long> buildIds(List<T> rs){
        List<Long> ids = new ArrayList<>(rs.size());
        for(T request:rs){
            ids.add(request.getId());
        }
        return ids;
    }

    private class Worker implements Runnable{
        List<Long> ids;
        List<T> requests;
        public Worker(List<Long> ids,List<T> requests){
            this.ids = ids;
            this.requests = requests;
        }
        @Override
        public void run() {
            try{
                handle(requests);
            }catch (Throwable ex){
                logger.error(name()+" schedule task handle occur error,"+ex.getMessage(),ex);
            }finally {
                inFlightIds.removeAll(ids);
            }
        }
    }
}
