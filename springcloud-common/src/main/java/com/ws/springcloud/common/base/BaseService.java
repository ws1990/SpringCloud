package com.ws.springcloud.common.base;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService接口
 *
 * @author cheng.luo
 * @version 1.0.0
 * @date 2017/7/20
 */
public interface BaseService<Record, Example> {

    int countByExample(Example example);

    int insert(Record record);

    int insertSelective(Record record);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Serializable id);

    int deleteByPrimaryKeys(String ids);

    int deleteByPrimaryKeys(List<String> ids);

    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    int updateByExample(@Param("record") Record record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> selectByExample(Example example);

    PageInfo<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize);

    <T> PageInfo<T> doSelectPageInfo(Integer pageNum, Integer pageSize, ISelect select);

    Record selectFirstByExample(Example example);

    Record selectByPrimaryKey(Serializable id);

    void initMapper();
}
