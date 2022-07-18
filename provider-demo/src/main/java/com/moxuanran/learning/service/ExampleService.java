package com.moxuanran.learning.service;


import com.moxuanran.learning.po.Example;

/**
 * 示例服务
 *
 * @author wutao
 * @date 2022/07/18
 */
public interface ExampleService {

    /**
     * 保存
     *
     * @param example 例子
     * @return {@link Example}
     */
    Example save(Example example);

    /**
     * 发现通过id
     *
     * @param id id
     * @return {@link Example}
     */
    Example findById(Long id);

}
