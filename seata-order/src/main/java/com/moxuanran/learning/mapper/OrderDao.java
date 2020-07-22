package com.moxuanran.learning.mapper;

import com.moxuanran.learning.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 莫轩然
 * @date 2020/7/21 16:08
 */
@Repository
public interface OrderDao {
    void create(Order order);

    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
