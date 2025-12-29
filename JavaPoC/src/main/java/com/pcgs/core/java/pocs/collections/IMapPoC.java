package com.pcgs.core.java.pocs.collections;

import java.util.HashMap;
import java.util.Map;

class IMapPoC {
    public static void main(String[] args) {
        System.out.println("IMapPoC");

        Map<String, Integer> map = new HashMap<>();
        map.put("Dummy", 123);
        map.put("Rummy", 456);
        map.put("Mummy", 789);

        System.out.println(map); // {Dummy=123, Rummy=456, Mummy=789}

        map.remove("Dummy");
        System.out.println(map); // {Rummy=456, Mummy=789}

        map.put("Rummy", 853);
        System.out.println(map); // {Mummy=789, Rummy=853}

        for(Map.Entry<String, Integer> mapElement:map.entrySet()){
            System.out.println(mapElement.getKey() + " " + mapElement.getValue());
        }

    }
}
