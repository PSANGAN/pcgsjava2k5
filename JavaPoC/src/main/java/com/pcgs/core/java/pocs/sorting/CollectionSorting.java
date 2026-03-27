package com.pcgs.core.java.pocs.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionSorting {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>( List.of(-10,-25,-9,-1,0,5,7,19,25,50,0,0,100));

        Collections.sort(integerList);

        for(Integer temp : integerList)
        {
            System.out.println(temp);
        }

        System.out.println(System.lineSeparator());

        Collections.sort(integerList, Collections.reverseOrder());

        for(Integer temp : integerList)
        {
            System.out.println(temp);
        }
    }
}
