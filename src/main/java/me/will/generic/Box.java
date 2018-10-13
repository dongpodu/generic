package me.will.generic;

import lombok.Data;

import java.lang.reflect.ParameterizedType;

/**
 * Created by duyisong on 13/10/2018.
 */
@Data
public class Box<T> {
    private T t;

    //静态函数
    public static  <T> void staticMethod(T a){
        System.out.println("StaticMethod: "+a.toString());
    }
    //普通函数
    public  <T> void otherMethod(T a){
        System.out.println("OtherMethod: "+a.toString());
    }

    //只有类或接口泛型可以在运行时获取泛型的class对象，泛型方法是无法获取的，需要传递Class对象
    public Class<T> getGenericClass(){
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static void main(String[] args) {

    }
}
