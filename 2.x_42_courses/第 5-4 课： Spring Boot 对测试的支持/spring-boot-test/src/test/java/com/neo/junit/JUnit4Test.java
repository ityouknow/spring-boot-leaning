package com.neo.junit;

import com.neo.util.Calculation;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class JUnit4Test {

    Calculation calculation = new Calculation();//要测试的类的对象

    int result;//测试结果

    //在JUnit4中使用@Test标注为测试方法
    @Test
    //测试方法必须是public void的
    public void testAdd() {
        System.out.println("---testAdd开始测试---");

        //每个里面只测一次,因为assertEquals一旦测试发现错误就抛出异常,不再运行后续代码
        result = calculation.add(1, 2);
        assertEquals(3, result);

        System.out.println("---testAdd正常运行结束---");
    }

    //又一个测试方法
    //timeout表示测试允许的执行时间毫秒数,expected表示忽略哪些抛出的异常(不会因为该异常导致测试不通过)
    @Test(timeout = 1, expected = NullPointerException.class)
    public void testSub() {
        System.out.println("---testSub开始测试---");

        result = calculation.sub(3, 2);
        assertEquals(1, result);

        throw new NullPointerException();

        //System.out.println("---testSub正常运行结束---");
    }


    //指示该[静态方法]将在该类的[所有]测试方法执行之[前]执行
    @BeforeClass
    public static void beforeAll() {
        System.out.println("||==BeforeClass==||");
        System.out.println("||==通常在这个方法中加载资源==||");
    }

    //指示该[静态方法]将在该类的[所有]测试方法执行之[后]执行
    @AfterClass
    public static void afterAll() {
        System.out.println("||==AfterClass==||");
        System.out.println("||==通常在这个方法中释放资源==||");
    }

    //该[成员方法]在[每个]测试方法执行之[前]执行
    @Before
    public void beforeEvery() {
        System.out.println("|==Before==|");
    }

    //该[成员方法]在[每个]测试方法执行之[后]执行
    @After
    public void afterEvery() {
        System.out.println("|==After==|");
    }

}
