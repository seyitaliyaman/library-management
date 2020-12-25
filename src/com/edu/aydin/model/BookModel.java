package com.edu.aydin.model;

public class BookModel implements Comparable<BookModel> {

    private String id;
    private String name;
    private String author;
    private String publishDate;
    private String category;
    private String publisher;

    public BookModel(String id, String name, String author, String publishDate, String category, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.category = category;
        this.publisher = publisher;
    }

    public BookModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return id + "___" + name + "___" + author + "___" + publishDate + "___" + category + "___" + publisher;
    }

    @Override
    public int compareTo(BookModel o) {
        return Integer.valueOf(this.getId()).compareTo(Integer.valueOf(o.getId()));
    }
}
