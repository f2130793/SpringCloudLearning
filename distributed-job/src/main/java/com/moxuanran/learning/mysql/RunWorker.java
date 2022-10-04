package com.moxuanran.learning.mysql;

import java.util.List;

/**
 * @author wutao
 * @date 2022/9/29 16:00
 */
public interface RunWorker {
    /**
     * 任务名
     *
     * @return {@link String}
     */
    String name();

    /**
     * 执行
     */
    void execute();

    /**
     * 更新执行器
     *
     * @param surviveRunnerIds 生存跑步者id
     */
    void changeHealthRunner(List<Integer> surviveRunnerIds);

    /**
     * 设置执行器
     *
     * @param runnerManager 跑步者经理
     */
    void setRunnerManager(RunnerManager runnerManager);
}
