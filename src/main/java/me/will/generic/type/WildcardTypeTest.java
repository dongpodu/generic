package me.will.generic.type;

import java.lang.reflect.*;
import java.util.List;

public class WildcardTypeTest{
    private List<?> items; //ParameterizedType

    public void test() throws NoSuchFieldException {
        String name="items";
        Field field = TypeVariableTest.class.getDeclaredField(name);
        Type type = field.getGenericType();
        System.out.println(name+" is ParameterizedType:"+(type instanceof ParameterizedType));
        System.out.println(name+" is TypeVariable:"+(type instanceof TypeVariable));
        System.out.println(name+" is WildcardType:"+(type instanceof WildcardType));
        System.out.println(name+" is GenericArrayType:"+(type instanceof GenericArrayType));
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new TypeVariableTest<String>().test();
    }
}
