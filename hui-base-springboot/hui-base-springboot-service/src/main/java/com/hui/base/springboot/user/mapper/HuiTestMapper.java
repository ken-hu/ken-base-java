package com.hui.base.springboot.user.mapper;

import com.hui.base.springboot.user.entity.HuiTest;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HuiTestMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(HuiTest record);

    int insertSelective(HuiTest record);

    HuiTest selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(HuiTest record);

    int updateByPrimaryKey(HuiTest record);

    int batchInsert(@Param("recordList") List<HuiTest> recordList);

    int batchDelete(@Param("ids") java.math.BigDecimal[] ids);

    int batchUpdate(@Param("recordList") List<HuiTest> recordList);
}
