package me.will.generic.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * 带<>申明变量的类型，那么这个变量是ParameterizedType
 */
public class ParameterizedTypeTest {
    /**
     * getActualTypeArguments:[java.lang.String,java.lang.String]
     * rawType:java.util.Map
     * ownerType:null (Map不是某个类型的成员)
     */
    private Map<String,String> data;


    /**
     * getActualTypeArguments:[java.lang.String,java.lang.String]
     * rawType:rawType:java.util.Map$Entry
     * ownerType:java.util.Map (Entry是某个Map类型的成员)
     */
    private Map.Entry<String, String> entry;

    public void test() throws NoSuchFieldException {
        Class<ParameterizedTypeTest> clazz = ParameterizedTypeTest.class;
        Field field = clazz.getDeclaredField("entry");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type[] types = parameterizedType.getActualTypeArguments();
            for(Type ty:types){
                System.out.println("parameterizedType:"+ty.getTypeName());
            }
            Type ownerType = parameterizedType.getOwnerType();
            System.out.println("ownerType:"+(ownerType==null?null:ownerType.getTypeName()));
            Type rawType= parameterizedType.getRawType();
            System.out.println("rawType:"+(rawType==null?null:rawType.getTypeName()));
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new ParameterizedTypeTest().test();
    }

}
