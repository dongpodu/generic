package me.will.generic.type;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * 直接以 T 申明变量的类型，那么这个变量是TypeVariable
 */
public class TypeVariableTest <T>{
    private T key;//TypeVariable
    private List<T> list; //ParameterizedType
    private List<String> items; //ParameterizedType
    private TypeVariableTest<T> self;//ParameterizedType

    public void test() throws NoSuchFieldException {
        String name="self";
        Field field = this.getClass().getDeclaredField(name);
        Type type = field.getGenericType();
        System.out.println(name+" is ParameterizedType:"+(type instanceof ParameterizedType));
        System.out.println(name+" is TypeVariable:"+(type instanceof TypeVariable));
        System.out.println(name+" is WildcardType:"+(type instanceof WildcardType));
        System.out.println(name+" is GenericArrayType:"+(type instanceof GenericArrayType));
    }


    public void test2() throws NoSuchFieldException {
        String name="key";
        Field field = this.getClass().getDeclaredField(name);
        Type type = field.getGenericType();
        if(type instanceof TypeVariable){
            TypeVariable typeVariable = (TypeVariable)type;
            Arrays.stream(typeVariable.getBounds()).forEach(r ->{
                //泛型的上限，无显示定义（extends），则默认为Object
                System.out.println("getBounds："+r.getTypeName());
            });
            Arrays.stream(typeVariable.getAnnotatedBounds()).forEach(r ->{
                System.out.println("getAnnotatedBounds："+r.getType().getTypeName());
            });
            GenericDeclaration declaration = typeVariable.getGenericDeclaration();
            System.out.println("declaration："+declaration);
            System.out.println("name："+typeVariable.getName());
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new TypeVariableTest<String>().test2();
    }

}
