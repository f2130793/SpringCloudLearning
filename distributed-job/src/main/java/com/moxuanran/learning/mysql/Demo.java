package com.moxuanran.learning.mysql;

import java.util.Collection;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/29 16:15
 */
public class Demo extends AbstractRunWorker<DemoModel> implements RunWorker{
    /**
     * 加载对应的runnerId的任务数据
     *
     * @param runnerId
     * @param excludeIds 任务数据ID，排除这个任务数据
     * @return
     */
    @Override
    public List<DemoModel> loadData(int runnerId, Collection<Long> excludeIds) {
        return null;
    }

    /**
     * 处理任务数据
     *
     * @param records
     */
    @Override
    public void handle(List<DemoModel> records) {

    }

    @Override
    public void raceRecord(int runnerId) {

    }

    /**
     * 任务名
     *
     * @return {@link String}
     */
    @Override
    public String name() {
        return null;
    }

    /**
     * 更新执行器
     *
     * @param surviveRunnerIds 生存跑步者id
     */
    @Override
    public void changeHealthRunner(List<Integer> surviveRunnerIds) {

    }
}
