package me.will.generic;

import java.lang.reflect.*;

/**
 * Created by duyisong on 27/10/2018.
 */
public class TypeUtils {

    public static void checkType(Type type){
        System.out.println(" is ParameterizedType:"+(type instanceof ParameterizedType));
        System.out.println(" is TypeVariable:"+(type instanceof TypeVariable));
        System.out.println(" is WildcardType:"+(type instanceof WildcardType));
        System.out.println(" is GenericArrayType:"+(type instanceof GenericArrayType));
        System.out.println(" is Class:"+(type instanceof Class));
    }
}
