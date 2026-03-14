package com.pcgs.core.java.pocs.lambda;


import java.time.LocalDate;
import java.util.List;

 record Address(String city, String state, String country) {}

 record Product(long id, String name, double price, String category) {}

 record Order(long id, long personId, List<Product> products, LocalDate orderDate) {}

 record Person(long id, String name, int age, Address address) {}
