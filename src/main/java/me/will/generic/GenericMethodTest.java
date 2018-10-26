package me.will.generic;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.lang.reflect.*;

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

    /**
     * 按理说调用该方法时，已经传递了具体类型，为啥还要再传递Class对象，难道在运行时，方法内没法获取到泛型的具体类型吗（没法调用T.class）？
     * 答：对于泛型方法，无法在运行时获取具体类型，但对于类泛型，可以通过https://www.jianshu.com/p/9d2df4c49a8e方式获得
     * @param response
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T parse(String response, Class<T> object){
        T t = JSON.parseObject(response, object);
        return t;
    }

    public <T> T parse(String response){

        return null;
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

            System.out.println(" is ParameterizedType:"+(type instanceof ParameterizedType));
            System.out.println(" is TypeVariable:"+(type instanceof TypeVariable));
            System.out.println(" is WildcardType:"+(type instanceof WildcardType));
            System.out.println(" is GenericArrayType:"+(type instanceof GenericArrayType));

            TypeVariable typeVariable = (TypeVariable)type;
            GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
