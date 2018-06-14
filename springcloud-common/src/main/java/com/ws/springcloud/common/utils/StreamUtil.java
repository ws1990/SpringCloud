package com.ws.springcloud.common.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created on 15-6-26.
 * 封装了stream的一些常见用法。具体使用方式请参考同名测试文件
 * @author 王松
 */
public class StreamUtil {

    /**
     * 去掉重复的列表。判断是否重复是根据equals方法
     * @param list 有可能拥有重复数据的列表
     * @param <T>
     * @return
     */
    public static <T> List<T> distinct(List<T> list){
        List<T> result = new ArrayList<>();
        if(list == null || list.size() == 0){
            return result;
        }
        list.stream().filter(t -> !result.contains(t)).forEach(result::add);
        return result;
    }

    /**
     * 根据指定属性去掉重复的列表
     * @param list 有可能拥有重复数据的列表
     * @param function 指定对象的属性
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<T> distinct(List<T> list, Function<T, V> function){
        List<T> result = new ArrayList<>();
        //用于识别是否重复的列表
        List<V> distinctList = new ArrayList<>();
        if(list == null || list.size() == 0){
            return result;
        }
        list.stream().filter(
                t -> {
                    V v = function.apply(t);
                    if(!distinctList.contains(v)){
                        distinctList.add(v);
                        return true;
                    }
                    return false;
                }
        ).forEach(result::add);
        return result;
    }

    /**
     * 将目标列表中的某个字符串属性拼接成一个完成的字符串
     * @param list 目标列表
     * @param function 属性选取规则
     * @param delimiter 拼接规则
     * @param <T> 列表类型
     * @return
     */
    public static <T> String joining(Collection<T> list, Function<T, String> function, CharSequence delimiter){
        if(list == null || list.isEmpty()){
            return "";
        }
        return list.stream().map(function).collect(Collectors.joining(delimiter));
    }
    public static String joining(Collection<String> list, CharSequence delimiter){
        if(list == null || list.isEmpty()){
            return "";
        }
        return list.stream().collect(Collectors.joining(delimiter));
    }

    /**
     * 获取列表中的指定属性
     * @param beans 指定的列表
     * @param function 指定的属性获取规则
     * @param <T> 列表泛型
     * @param <R> 属性泛型
     * @return List
     */
    public static <T, R> List<R> getProps(Collection<T> beans, Function<T, R> function){
        if(beans == null || beans.isEmpty()){
            return new ArrayList<>();
        }
        return beans.stream().map(function).collect(Collectors.toList());
    }

    /**
     * 将指定的列表将指定的属性做为key，转换成Map
     * @param beans 需要转换的列表
     * @param function 获取Key的规则
     * @param <T> 列表泛型
     * @param <R> 属性泛型
     * @return Map
     */
    public static <T, R> Map<R, T> convertListToMap(Collection<T> beans, Function<T, R> function){
        if(beans == null || beans.isEmpty()){
            return new HashMap<>();
        }
        return beans.stream().collect(Collectors.toMap(function, t -> t));
    }

    /**
     * 将指定的列表将指定的属性做为key，转换成Map
     * @param beans
     * @param key
     * @param value
     * @return
     */
    public static <R, K, V> Map<K, V> convertListToMap(Collection<R> beans, Function<R, K> key, Function<R, V> value){
        if(beans == null || beans.isEmpty()){
            return new HashMap<>();
        }
        return beans.stream().collect(Collectors.toMap(key, value));
    }

    /**
     * 将指定的列表根据key进行统计
     * @param beans 需要转换的目标列表
     * @param function key的生成规则
     * @param <T> value泛型
     * @param <R> key泛型
     * @return Map
     *
     */
    public static <T, R> Map<R, Long> convertListToMapCount(List<T> beans, Function<T, R> function){
        if(beans == null || beans.isEmpty()){
            return new HashMap<>();
        }
        return beans.stream().collect(Collectors.groupingBy(function, Collectors.counting()));
    }

    /**
     * 将指定的列表转换成以指定规则生成的数据为key，List为value的Map
     * @param beans 需要转换的目标列表
     * @param function key的生成规则
     * @param <T> value泛型
     * @param <R> key泛型
     * @return Map
     */
    public static <T, R> Map<R, List<T>> convertListToMapList(Collection<T> beans, Function<T, R> function){
        if(beans == null || beans.isEmpty()){
            return new HashMap<>();
        }
        return beans.stream().collect(Collectors.groupingBy(function, Collectors.toList()));
    }

    /**
     * 根据给定属性升序排序
     * @param beans 需要排序的列表
     * @param function 排序属性指定规则
     * @param <T> 排序对象类型
     */
    @SafeVarargs
    public static <T> void sort(List<T> beans, Function<T, Comparable>... function){
        sort(beans, Sorter.ASC, function);
    }

    /**
     * 根据给定属性升序排序
     * @param beans 需要排序的列表
     * @param function 排序属性指定规则
     * @param <T> 排序对象类型
     */
    @SafeVarargs
    public static <T> void sortAsc(List<T> beans, Function<T, Comparable>... function){
        sort(beans, Sorter.ASC, function);
    }

    /**
     * 根据给定属性降序排序
     * @param beans 需要排序的列表
     * @param function 排序属性指定规则
     * @param <T> 排序对象类型
     */
    @SafeVarargs
    public static <T> void sortDesc(List<T> beans, Function<T, Comparable>... function){
        sort(beans, Sorter.DESC, function);
    }

    /**
     * 自定义属性进行多级排序，排序规则有sortType统一指定
     * @param beans 需要排序的列表
     * @param sortType 统一排序规则
     * @param functions 自定义排序属性
     * @param <T>
     */
    @SafeVarargs
    private static <T> void sort(List<T> beans, String sortType, Function<T, Comparable>... functions) {
        //对异常参数做处理
        if(beans == null || beans.size() == 0){
            return;
        }
        if(functions == null || functions.length == 0){
            return;
        }

        Sorter[] sorterArray = new Sorter[functions.length];
        for (int i = 0; i < functions.length; i++) {
            sorterArray[i] = new Sorter<>(functions[i], sortType);
        }

        List<T> tem = beans.stream().sorted(
                (b1, b2) -> compare(b1, b2, sorterArray)
        ).collect(Collectors.toList());

        //如果排序出现异常，放弃排序
        if(tem == null || tem.size() == 0){
            return;
        }

        //将排序结果保存在原来的列表中
        beans.clear();
        beans.addAll(tem);
    }

    /**
     * 自定义排序规则进行多级排序
     * @param beans 需要排序的列表
     * @param sorters 排序规则
     * @param <T>
     */
    public static <T> void sort(List<T> beans, Sorter... sorters){
        //对异常参数做处理
        if(beans == null || beans.size() == 0){
            return;
        }
        if(sorters == null || sorters.length == 0){
            return;
        }

        List<T> tem = beans.stream().sorted(
                (b1, b2) -> compare(b1, b2, sorters)
        ).collect(Collectors.toList());

        //如果排序出现异常，放弃排序
        if(tem == null || tem.size() == 0){
            return;
        }

        //将排序结果保存在原来的列表中
        beans.clear();
        beans.addAll(tem);
    }

    /**
     * 根据自定义排序规则对两个对象进行比较
     * @param b1 比较对象1
     * @param b2 比较对象2
     * @param sorters 排序规则
     * @param <T>
     * @return
     */
    protected static <T> int compare(T b1, T b2, Sorter... sorters) {
        double result = 0d;
        for (int i = 0; i < sorters.length; i++) {
            Sorter sorter = sorters[i];
            double multiples = Math.pow(10, (sorters.length - i));
            result += multiples * compare(b1, b2, sorter.function, sorter.sortType);
        }

        if(result > 0){
            return 1;
        } else if (result < 0) {
            return -1;
        }
        return 0;
    }
    /**
     * 核心比较算法
     * @param b1 比较对象1
     * @param b2 比较对象2
     * @param function 比较对象的属性
     * @param sortType 排序类型
     * @param <T>
     * @return
     */
    private static <T> int compare(T b1, T b2, Function<T, Comparable> function, String sortType){
        Comparable r1 = function.apply(b1);
        Comparable r2 = function.apply(b2);
        int result = 0;
        //升序
        if(sortType == null || sortType.equalsIgnoreCase(Sorter.ASC)){
            if(r1 == null){
                if (r2 == null) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (r2 == null){
                return 1;
            }
            result = r1.compareTo(r2);
        } else {
            if(r1 == null){
                if (r2 == null) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (r2 == null){
                return -1;
            }
            result = r2.compareTo(r1);
        }

        if(result > 0){
            result = 1;
        } else if (result < 0) {
            result = -1;
        }
        return result;
    }

    public static class Sorter<T> {
        private Function<T, Comparable> function;
        private String sortType;
        public static final String ASC = "asc";
        public static final String DESC = "desc";

        public Sorter(Function<T, Comparable> function, String sortType){
            this.function = function;
            this.sortType = sortType;
        }
    }

    public static class SorterASC<T> extends Sorter<T> {
        public SorterASC(Function<T, Comparable> function){
            super(function, ASC);
        }
    }

    public static class SorterDESC<T> extends Sorter<T> {
        public SorterDESC(Function<T, Comparable> function){
            super(function, DESC);
        }
    }
}
