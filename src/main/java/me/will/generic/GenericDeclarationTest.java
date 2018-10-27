package me.will.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * GenericDeclaration:可以定义类型变量的公共接口，其实现类包含：Class，Method，Constructor，因为无法在字段上定义类型变量，所以Field不是其实现类
 * Created by duyisong on 27/10/2018.
 */
public class GenericDeclarationTest<A,B> {
    private A a;
    private B b;


    public GenericDeclarationTest(){
    }


    public <E> GenericDeclarationTest(E e){
        System.out.println("constructor:"+e);
    }

    public <D> void test(D d){
        System.out.println("test:"+d);
    }


    public void test(){
        Class clazz = this.getClass();
        TypeVariable[] classTypes = this.getClass().getTypeParameters();
        Arrays.stream(classTypes).forEach(
                r -> {
                    System.out.println("class name:"+r.getName()+",is class:"+(r.getGenericDeclaration() instanceof Class));
                }
        );

        Constructor[] constructors = clazz.getConstructors();
        Arrays.stream(constructors).forEach(
                r -> {
                    TypeVariable[] contructorTypes = r.getTypeParameters();
                    Arrays.stream(contructorTypes).forEach(
                            t -> {
                                System.out.println("class name:"+t.getName()+",is constructor:"+(t.getGenericDeclaration() instanceof Constructor));
                            }
                    );
                }
        );

        Method[] methods = clazz.getMethods();
        Arrays.stream(methods).forEach(
                r -> {
                    TypeVariable[] methodTypes = r.getTypeParameters();
                    Arrays.stream(methodTypes).forEach(
                            t -> {
                                System.out.println("class name:"+t.getName()+",is method:"+(t.getGenericDeclaration() instanceof Method));
                            }
                    );
                }
        );
    }


    public static void main(String[] args) {
        new GenericDeclarationTest<>().test();
    }
}
