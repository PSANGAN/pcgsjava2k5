package com.pcgs.core.java.pocs.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class ICollectionPoC {

    public static void main(String[] args) {
        System.out.println("ICollectionPoC");
        Collection<String>  collection = new ArrayList<>();
        collection.add("Hello ");
        collection.add("World ");
        collection.add("! ");

        Collection<String>  collection2 = new ArrayList<>();
        collection2.add("Hello ");
        collection2.add("Pattukkottai ");
        collection2.add("! ");

        collection.addAll(collection2);

        Collection<Integer>  collection3 = new ArrayList<>();
        collection3.add(101);
        collection3.add(201);
        collection3.add(501);

        collection3.remove(201);

       System.out.println (((List<Integer>)collection3).get(0));

        collection2.iterator().forEachRemaining(System.out::println);

        Iterator<String> iterator = collection2.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Object[] array =    collection.toArray();
        for (Object obj : array) {
            System.out.println(obj);
        }
    }
}
