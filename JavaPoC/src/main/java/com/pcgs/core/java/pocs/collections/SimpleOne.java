package com.pcgs.core.java.pocs.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleOne {
    public static void main(String[] args)
    {
        List<String> strings = new ArrayList<>(List.of("alpha", "bravo", "charlie"));
//        for(String str : strings)
//        {
//            if (! str.contains("r")) {
//                strings.remove(str); // throws ConcurrentModificationException
//            }
//        }
        for (Iterator<String> itr = strings.iterator(); itr.hasNext() ; ) {
            String s = itr.next();
            if (! s.contains("r")) {
                itr.remove();
            }
        }
    }
}

