package com.moxuanran.learning.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:44
 */
@Repository
public interface StorageDao {
    Boolean decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
