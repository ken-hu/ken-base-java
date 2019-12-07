package com.hui.base.springboot.user.mapper;

import com.hui.base.springboot.user.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int batchInsert(@Param("recordList") List<Order> recordList);

    int batchDelete(@Param("ids") String[] ids);

    int batchUpdate(@Param("recordList") List<Order> recordList);
}
