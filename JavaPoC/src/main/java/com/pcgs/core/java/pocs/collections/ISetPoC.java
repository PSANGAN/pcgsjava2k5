package com.pcgs.core.java.pocs.collections;

import java.util.HashSet;
import java.util.Set;

class ISetPoC {
    public static void main(String[] args) {
        System.out.println("ISetPoC");

        Set<Character> characterSet = new HashSet<>();
        characterSet.add('A');
        characterSet.add('A');
        characterSet.add('B');
        characterSet.add('C');
        characterSet.add('D');

        System.out.println(characterSet.contains('C'));
        System.out.println(characterSet.size());

        characterSet.remove('A');

        System.out.println(characterSet.contains('A'));

        for(Character character : characterSet) {
            System.out.println(character);
        }
    }
}
