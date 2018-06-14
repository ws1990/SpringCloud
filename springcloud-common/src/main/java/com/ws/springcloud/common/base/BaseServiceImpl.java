package com.ws.springcloud.common.base;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.springcloud.common.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 实现BaseService抽象类
 *
 * @author 王松
 * @version 1.0
 * @date 2017/7/20
 */
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private Mapper mapper;

    @Override
    public int countByExample(Example example) {
        return methodInvokeInt("countByExample", example);
    }

    @Override
    public int deleteByExample(Example example) {
        return methodInvokeInt("deleteByExample", example);
    }

    @Override
    public int deleteByPrimaryKey(Serializable id) {
        return methodInvokeInt("deleteByPrimaryKey", id);
    }

    @Override
    public int insert(Record record) {
        return methodInvokeInt("insert", record);
    }

    @Override
    public int insertSelective(Record record) {
        return methodInvokeInt("insertSelective", record);
    }

    @Override
    public List<Record> selectByExample(Example example) {
        return methodInvokeRecords("selectByExample", example);
    }

    @Override
    public PageInfo<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize) {
        try {
            Method selectByExample = getMapperMethod("selectByExample", example.getClass());
            PageHelper.startPage(pageNum, pageSize, true);
            Object result = selectByExample.invoke(mapper, example);
            return new PageInfo<Record>((Page) result);
        } catch (Exception e) {
            throw new RuntimeException("数据操作出错. method = " + "selectByExampleForStartPage", e);
        }
    }

    @Override
    public <T> PageInfo<T> doSelectPageInfo(Integer pageNum, Integer pageSize, ISelect select) {
        PageInfo<T> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(select);
        return pageInfo;
    }

    @Override
    public Record selectFirstByExample(Example example) {
        List<Record> result = methodInvokeRecords("selectByExample", example);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }

        return null;
    }

    @Override
    public Record selectByPrimaryKey(Serializable id) {
        return methodInvokeRecord("selectByPrimaryKey", id);
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        return methodInvokeInt("updateByExampleSelective", record, example);
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        return methodInvokeInt("updateByExample", record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        return methodInvokeInt("updateByPrimaryKeySelective", record);
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return methodInvokeInt("updateByPrimaryKey", record);
    }


    @Override
    public int deleteByPrimaryKeys(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        String[] idArray = new String[ids.size()];
        idArray = ids.toArray(idArray);
        return deleteByPrimaryKeys(idArray);
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (StringUtils.isBlank(ids)) {
            return 0;
        }
        String[] idArray = ids.split(",");
        return deleteByPrimaryKeys(idArray);
    }

    private int deleteByPrimaryKeys(String[] idArray) {
        int count = 0;
        for (String id : idArray) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            count += methodInvokeInt("deleteByPrimaryKey", id);
        }
        return count;
    }

    private int methodInvokeInt(String methodName, Object... parameters) {
        Object result = methodInvoke(methodName, parameters);
        if (result == null) {
            return 0;
        }

        return Integer.parseInt(String.valueOf(result));
    }

    private Record methodInvokeRecord(String methodName, Object... parameters) {
        Object result = methodInvoke(methodName, parameters);
        if (result == null) {
            return null;
        }

        return (Record) result;
    }

    private List<Record> methodInvokeRecords(String methodName, Object... parameters) {
        Object result = methodInvoke(methodName, parameters);
        if (result == null) {
            return null;
        }

        return (List<Record>) result;
    }

    /**
     * 封装反射调用，避免大量重复代码
     *
     * @return Object
     */
    private Object methodInvoke(String methodName, Object... parameters) {
        Class[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
        }
        try {
            Method method = getMapperMethod(methodName, parameterTypes);
            Object result = method.invoke(mapper, parameters);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("数据操作出错. method = " + methodName, e);
        }
    }

    /**
     * 原计划如果获取不到方法，则获取父类的方法，但是失败了，需要再研究一下
     */
    private Method getMapperMethod(String methodName, Class... parameterTypes) {
        Method method = null;

        for (Class<?> clazz = mapper.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return null;
    }

    @Override
    public void initMapper() {
        this.mapper = SpringContextUtil.getBean(getMapperClass());
    }

    /**
     * 获取类泛型class
     *
     * @return
     */
    public Class<Mapper> getMapperClass() {
        return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
