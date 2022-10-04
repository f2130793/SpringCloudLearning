package com.moxuanran.learning.mysql;

import java.util.List;

/**
 * @author wutao
 * @date 2022/9/29 16:03
 */
public interface RunnerService {
    Runner getRunner(String ip);

    void save(Runner runner);

    void update(Runner runner);

    List<Runner> getAll();

    void deleteByIds(List<Integer> ids);
}
