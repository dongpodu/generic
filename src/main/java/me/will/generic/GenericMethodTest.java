package me.will.generic;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by duyisong on 13/10/2018.
 */
@Data
public class GenericMethodTest{
    //静态函数
    public static <T> void test(T a){
        System.out.println("StaticMethod: "+a.toString());
    }

    //成员函数
    public  <T> void test1(T a){
        System.out.println("Object: "+a.toString());
    }

    public static <T> T parse(String response, Class<T> object){
        T t = JSON.parseObject(response, object);
        return t;
    }

    public static void main(String[] args) {
        GenericMethodTest.test("hello1");
        GenericMethodTest.<String>test("hello2");//推荐此种方式调用

        GenericMethodTest test = new GenericMethodTest();
        test.test1("你好1");
        test.<String>test1("你好2");//推荐此种方式调用

        String str = "{\"success\":false}";
        GenericMethodTest.<SuccessModel>parse(str,SuccessModel.class);

        Class clazz =  test.getClass();
        try {
            Method method = clazz.getMethod("parse",String.class);
            Type type = method.getGenericReturnType();
            TypeUtils.checkType(type);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
