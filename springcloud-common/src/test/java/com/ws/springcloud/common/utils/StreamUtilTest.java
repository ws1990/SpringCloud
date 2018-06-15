package com.ws.springcloud.common.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created on 16-6-28.
 *
 * @author 王松
 */
public class StreamUtilTest {
    private List<Bean> beans = new ArrayList<>();

    @Before
    public void setUp(){
        for(int i = 0; i < 5; i++){
            Bean bean = new Bean();
            bean.id = String.valueOf(i);
            bean.name = "name" + i;
            bean.value = i;
            beans.add(bean);
        }
    }
    @After
    public void tearDown(){
        beans.clear();
    }


    @Test
    public void joiningTest(){
        String ids = "0,1,2,3,4";
        String result = StreamUtil.joining(beans, Bean::getId, ",");
        assertEquals(ids, result);
    }


    @Test
    public void distinctTest(){
        List<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("a");
        list.add("b");

        list = StreamUtil.distinct(list);
        System.out.println(list);
        assertEquals(2, list.size());
    }
    @Test
    public void distinctTest2(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = "1";
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "1";
        b2.name = "1";
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "3";
        b3.name = "3";
        list.add(b3);

        list = StreamUtil.distinct(list);
        System.out.println(list);
        assertEquals(2, list.size());
    }
    @Test
    public void distinctTest3(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = "1";
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "2";
        b2.name = "1";
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "3";
        b3.name = "3";
        list.add(b3);

        list = StreamUtil.distinct(list, Bean::getName);
        System.out.println(list);
        assertEquals(2, list.size());
    }


    @Test
    public void getPropsTest(){
        List<String> names = StreamUtil.getProps(beans, Bean::getName);
        String result = StreamUtil.joining(names, ",");

        assertEquals("name0,name1,name2,name3,name4", result);
    }
    @Test
    public void getPropsTest2(){
        List<Integer> values = StreamUtil.getProps(beans, Bean::getValue);
        assertEquals("[0, 1, 2, 3, 4]", values.toString());
    }
    //有null值
    @Test
    public void getPropsTest3(){
        beans.add(new Bean());
        beans.add(new Bean());
        List<String> names = StreamUtil.getProps(beans, Bean::getName);
        assertEquals("[name0, name1, name2, name3, name4, null, null]", names.toString());
    }
    //空列表
    @Test
    public void getPropsTest4(){
        List<String> names = StreamUtil.getProps(null, Bean::getName);
        assertEquals("[]", names.toString());

        names = StreamUtil.getProps(new ArrayList<Bean>(), Bean::getName);
        assertEquals("[]", names.toString());
    }


    @Test
    public void convertListToMap(){
        Map<String, Bean> beanMap = StreamUtil.convertListToMap(beans, Bean::getId);
        Bean bean = beanMap.get("1");
        System.out.println(bean);

        assertEquals("name1", bean.getName());
    }


    @Test
    public void convertListToMapList(){
        Bean bean = new Bean();
        bean.id = "1";
        bean.name = "new Name";
        beans.add(bean);

        Map<String, List<Bean>> beanListMap = StreamUtil.convertListToMapList(beans, Bean::getId);
        List<Bean> beanList = beanListMap.get("1");
        System.out.println(beanList);
        assertEquals(2, beanList.size());

        Bean beanInList = beanList.get(1);
        assertEquals("new Name", beanInList.getName());
    }

    @Test
    public void convertListToMapCount(){
        Bean bean = new Bean();
        bean.id = "1";
        bean.name = "new Name";
        beans.add(bean);

        Map<String, Long> beanListMap = StreamUtil.convertListToMapCount(beans, Bean::getId);
        System.out.println(beanListMap);

        assertEquals(5, beanListMap.size());

        long count = beanListMap.get("1");
        assertEquals(2, count);
    }



    //只能是0或者1
    @Test
    public void randomTest(){
        int result = 0;
        for(int i = 0; i < 500; i++){
            int value = new Random().nextInt(2);
            result += value;
        }
        //result越接近于250表示随机越均匀
        System.out.println(result);
        assertTrue(result > 200 && result < 300);
    }

    @Test
    public void compareTest(){
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = "name3";
        b1.value = 1;

        Bean b2 = new Bean();
        b2.id = "2";
        b2.name = "name2";
        b2.value = 1;

        Bean b3 = new Bean();
        b3.id = "3";
        b3.name = "name1";
        b3.value = 2;

        Bean b4 = new Bean();
        b4.id = "4";
        b4.name = "name4";
        b4.value = 1;

        int result = StreamUtil.compare(b1, b2, new StreamUtil.Sorter<>(Bean::getValue, "asc"), new StreamUtil.Sorter<>(Bean::getName, "asc"));
        assertEquals(1, result);
    }


    //测试单排序
    @Test
    public void sortTest(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = "1";
        b1.value = 1000;
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "1";
        b2.name = "3";
        b2.value = 4;
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "1";
        b3.name = "2";
        b3.value = 2;
        list.add(b3);

        Bean b4 = new Bean();
        b4.id = "2";
        b4.name = "1";
        b4.value = 1;
        list.add(b4);

        StreamUtil.sort(list, Bean::getValue);
        String result = StreamUtil.joining(list, b -> String.valueOf(b.getValue()), "<");
        System.out.println("sortByValue ACS: " + result);
        assertEquals("1<2<4<1000", result);

        StreamUtil.sortDesc(list, Bean::getValue);
        result = StreamUtil.joining(list, b -> String.valueOf(b.getValue()), ">");
        System.out.println("sortByValue DSC: " + result);
        assertEquals("1000>4>2>1", result);

        StreamUtil.sortDesc(list, Bean::getId);
        result = StreamUtil.joining(list, Bean::getId, ">");
        System.out.println("sortByID DSC: " + result);
        assertEquals("2>1>1>1", result);
    }
    //测试单排序，有null值
    @Test
    public void sortTest2(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = null;
        b1.name = "1";
        b1.value = 1;
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "1";
        b2.name = "3";
        b2.value = 4;
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "2";
        b3.name = "2";
        b3.value = 2;
        list.add(b3);

        Bean b4 = new Bean();
        b4.id = "1";
        b4.name = null;
        b4.value = 1;
        list.add(b4);

        StreamUtil.sort(list, Bean::getId);
        String result = StreamUtil.joining(list, Bean::getId, "<");
        System.out.println("sortByID ASC: " + result);
        assertEquals("null<1<1<2", result);

        StreamUtil.sortDesc(list, Bean::getId);
        result = StreamUtil.joining(list, Bean::getId, ">");
        System.out.println("sortByID DSC: " + result);
        assertEquals("2>1>1>null", result);
    }
    //测试多排序
    @Test
    public void sortTest3(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = "name3";
        b1.value = 1;
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "2";
        b2.name = "name2";
        b2.value = 1;
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "3";
        b3.name = "name1";
        b3.value = 2;
        list.add(b3);

        Bean b4 = new Bean();
        b4.id = "4";
        b4.name = "name4";
        b4.value = 1;
        list.add(b4);

        //升序:先根据value排序,然后根据name排序
        StreamUtil.sort(list, new StreamUtil.SorterASC<>(Bean::getValue), new StreamUtil.SorterASC<>(Bean::getName));
        String result = StreamUtil.joining(list, Bean::getId, "<");
        System.out.println(list);
        System.out.println("sortByValueAndName ASC: " + result);
        assertEquals("2<1<4<3", result);

        //降序:先根据value排序,然后根据name排序
        StreamUtil.sort(list, new StreamUtil.SorterASC<>(Bean::getValue), new StreamUtil.SorterDESC<>(Bean::getName));
        result = StreamUtil.joining(list, Bean::getId, "<");
        System.out.println(list);
        System.out.println("sortByValueAndName ASC: " + result);
        assertEquals("4<1<2<3", result);
    }
    //测试多排序,有null值
    @Test
    public void sortTest4(){
        List<Bean> list = new ArrayList<>(3);
        Bean b1 = new Bean();
        b1.id = "1";
        b1.name = null;
        b1.value = 1;
        list.add(b1);

        Bean b2 = new Bean();
        b2.id = "2";
        b2.name = "name2";
        b2.value = 1;
        list.add(b2);

        Bean b3 = new Bean();
        b3.id = "3";
        b3.name = "name1";
        b3.value = 2;
        list.add(b3);

        Bean b4 = new Bean();
        b4.id = "4";
        b4.name = "name4";
        b4.value = 1;
        list.add(b4);

        //升序:先根据value排序,然后根据name排序
        StreamUtil.sort(list, new StreamUtil.SorterASC<>(Bean::getValue), new StreamUtil.SorterASC<>(Bean::getName));
        String result = StreamUtil.joining(list, Bean::getId, "<");
        System.out.println(list);
        System.out.println("sortByValueAndName ASC: " + result);
        assertEquals("1<2<4<3", result);

        //降序:先根据value排序,然后根据name排序
        StreamUtil.sort(list, new StreamUtil.SorterASC<>(Bean::getValue), new StreamUtil.SorterDESC<>(Bean::getName));
        result = StreamUtil.joining(list, Bean::getId, "<");
        System.out.println(list);
        System.out.println("sortByValueAndName ASC: " + result);
        assertEquals("4<2<1<3", result);
    }


    private static class Bean {
        private String id;
        private String name;
        private int value;


        public String getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o){
            if(o == null){
                return false;
            }

            if(o instanceof Bean){
                Bean b = (Bean)o;
                return this.id.equals(b.id) && this.name.equals(b.name);
            }

            return false;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{id=").append(id).append(" ")
                    .append("name=").append(name).append(" ")
                    .append("value=").append(value).append("}");
            return sb.toString();
        }

    }
}
