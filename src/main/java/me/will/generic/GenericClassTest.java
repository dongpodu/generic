package me.will.generic;

import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * Created by duyisong on 13/10/2018.
 */
@Data
public class GenericClassTest<T extends Number>{
    private T t;

    //该方法只能在子类对象上调用，原因是getGenericSuperclass，对于实现接口，可以通过getGenericSuperInterface
    public Class getGenericClass(){
        Type type = getClass().getGenericSuperclass();
        TypeUtils.checkType(type);
        ParameterizedType parameterizedType = (ParameterizedType)type;
        Type[] actualTypes = parameterizedType.getActualTypeArguments();
        return(Class)actualTypes[0];
    }

    public void test(){
        Class clazz= this.getClass();
        TypeVariable[] types = clazz.getTypeParameters();
        Arrays.stream(types).forEach(
                r -> {
                    System.out.println("name:"+r.getName());
                    System.out.println("declaration:"+r.getGenericDeclaration());
                    //泛型上线，不加extends时，是Object
                    Arrays.stream(r.getBounds()).forEach(type -> {
                        System.out.println("bound:"+type);
                    });
                    Arrays.stream(r.getAnnotatedBounds()).forEach(type -> {
                        System.out.println("annotated bound:"+type);
                    });
                }
        );
    }


    public static void main(String[] args) {
//        new GenericClassTest<>().test();
        //父类对象调用失败
//        new GenericClassTest<Integer>().getGenericClass();
        //子类对象可以调用
        Class clazz = new GenericClassTestSub().getGenericClass();
        System.out.println(clazz);
    }
}
