package com.hui.base.springboot.user.mapper;

import com.hui.base.springboot.user.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(String productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    int batchInsert(@Param("recordList") List<Product> recordList);

    int batchDelete(@Param("ids") String[] ids);

    int batchUpdate(@Param("recordList") List<Product> recordList);
}
