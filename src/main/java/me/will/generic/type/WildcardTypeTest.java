package me.will.generic.type;

import me.will.generic.Foo;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * ? 就是通配符
 */
public class WildcardTypeTest{
    private List<? extends Foo> items;

    public void test() throws NoSuchFieldException {
        String name="items";
        Field field = this.getClass().getDeclaredField(name);
        Type type = field.getGenericType();
        System.out.println(name+" is ParameterizedType:"+(type instanceof ParameterizedType));
        System.out.println(name+" is TypeVariable:"+(type instanceof TypeVariable));
        System.out.println(name+" is WildcardType:"+(type instanceof WildcardType));
        System.out.println(name+" is GenericArrayType:"+(type instanceof GenericArrayType));
    }

    public void test1() throws NoSuchFieldException {
        String name="items";
        Field field = this.getClass().getDeclaredField(name);
        Type type = field.getGenericType();

        ParameterizedType parameterizedType = (ParameterizedType)type;
        Type[] types = parameterizedType.getActualTypeArguments();
        Arrays.stream(types).forEach(r->{
            if(r instanceof WildcardType){
                WildcardType wildcardType = (WildcardType)r;
                System.out.println("typeName："+wildcardType.getTypeName());
                Arrays.stream(wildcardType.getLowerBounds()).forEach(l->{
                    System.out.println("lowerBound："+r.getTypeName());
                });
                Arrays.stream(wildcardType.getUpperBounds()).forEach(l->{
                    System.out.println("upperBound："+r.getTypeName());
                });
            }
        });
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new WildcardTypeTest().test1();
    }
}
