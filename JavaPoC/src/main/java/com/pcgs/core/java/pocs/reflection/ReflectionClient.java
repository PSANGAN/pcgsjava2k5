package com.pcgs.core.java.pocs.reflection;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.lang.NoSuchMethodException;

public class ReflectionClient {
    public static void main(String[] args) {
      Class<ModelClass> mClass = ModelClass.class;
      System.out.println(mClass.getSimpleName());
      try {
        mClass = (Class<ModelClass>) Class.forName("com.pcgs.core.java.pocs.reflection.ModelClass");
        System.out.println(mClass.getTypeName());
      } catch (ClassNotFoundException e) {
        System.err.println("Class not found: " + e.getMessage());
        e.printStackTrace();
      }
        System.out.println(System.lineSeparator());
        Arrays.stream(mClass.getFields()).forEach(field -> System.out.println(field.getName()));
        System.out.println(System.lineSeparator());
       Arrays.stream(mClass.getMethods()).forEach(method ->
               System.out.println(method.getName()+ " - " + method.getReturnType().getSimpleName()));
        System.out.println(System.lineSeparator());

        Arrays.stream(mClass.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            System.out.println(field.getName());
        });
        System.out.println(System.lineSeparator());

        try {
            Constructor<Master> masterConstructor =  Master.class.getDeclaredConstructor();
            masterConstructor.setAccessible(true);
            Master masterReference =  masterConstructor.newInstance();
            System.out.println(masterReference);

        } catch (NoSuchMethodException e) {
            System.err.println("Cannot access private constructor: " + e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Cannot instantiate abstract class: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access to constructor: " + e.getMessage());
        } catch (java.lang.reflect.InvocationTargetException e) {
            System.err.println("Constructor threw an exception: " + e.getMessage());
        }
        System.out.println(System.lineSeparator());

        Class<Master> masterClass = Master.class;
        Arrays.stream(masterClass.getInterfaces()).forEach(interf -> System.out.println(interf));
       System.out.println(masterClass.getSuperclass().getSimpleName());

        doPrint(new Animal());
        doPrint(new SeaAnimal());
        doPrint(new WildAnimal());
    }

    static void doPrint(Animal animal){
        System.out.println(animal.getClass().getSimpleName());
    }
}
