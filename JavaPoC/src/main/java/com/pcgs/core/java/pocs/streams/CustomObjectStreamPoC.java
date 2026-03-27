package com.pcgs.core.java.pocs.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomObjectStreamPoC {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

//        books.stream().filter(b -> b.getType() == Type.NOVEL)
//                .sorted(Comparator.comparingInt(b -> b.getPages()))
//                .forEach(System.out::println);
//
//        System.out.println("\n\n");
//        books.stream().filter(b -> b.getType() == Type.NOVEL)
//                .sorted(Comparator.comparingInt(b -> b.getPages()))
//                .map(book -> book.getTitle())
//                .toList()
//                .forEach(System.out::println);

        System.out.println("\n\n");

//      Map<Type, List<Book>> temp = books.stream().collect(Collectors.groupingBy(b -> b.getType()));
//        temp.forEach((type, book) -> {
//            System.out.println(type + " : " + book);
//        });

      var temp =  books.stream().filter(b -> b.getPages() > 300).map(b -> b.getPublisher()).flatMap(Arrays::stream).toList();

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);

        //nums1.stream().flatMap(nums2::stream).forEach(System.out::println);

        // Returns HashMap with key as Type and value as List of books
        Map<Type, List<Book>>  hashMap =  books.stream().collect(Collectors.groupingBy(b -> b.getType()));

       // Returns HashMap with key as Type and value as Set(HashSet) of books
       var tempSet = books.stream().collect(Collectors.groupingBy(b -> b.getType(),
               Collectors.toCollection(HashSet::new)));

        // Returns HashMap with key as Type and value as count of books
//        var tempSet = books.stream().collect(Collectors.groupingBy(b -> b.getType(),
//                Collectors.counting()));

            // Returns TreeMap with key as Type and value as Set(HashSet) of books
//                var tempSet = books.stream().collect(Collectors.groupingBy(b -> b.getType(),
//                TreeMap::new,
//                Collectors.toSet()));

        books.stream().collect(Collectors.partitioningBy(b -> b.getAuthor().equals("Robert F."))).forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

    }
}

class Book {

    private String author;
    private String title;
    private int pages;
    private Type type;

    public String[] getPublisher() {
        return publisher;
    }

    private String[] publisher;

    public Book(String title, String author, int pages, Type type) {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.type = type;
        this.publisher = buildPublisher();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", title=" + title + ", pages=" + pages + ", type=" + type + "]";
    }

    private String[] buildPublisher() {
        this.publisher = new String[6];
        IntStream.range(0, 6).forEach(i -> publisher[i] = this.getAuthor() + " Publisher " + i);
        return publisher;
    }
}

enum Type {
    NOVEL, FICTION, HISTORY, THRILLER, PHILOSOPHY;
}
