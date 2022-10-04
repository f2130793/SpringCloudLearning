package com.moxuanran.learning.mysql;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 从数据库中获取执行机器
 *
 * @author wutao
 * @date 2022/9/29 16:13
 */
@Service
public class RunnerServiceImpl implements RunnerService{
    @Override
    public Runner getRunner(String ip) {
        return null;
    }

    @Override
    public void save(Runner runner) {

    }

    @Override
    public void update(Runner runner) {

    }

    @Override
    public List<Runner> getAll() {
        return null;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {

    }
}
