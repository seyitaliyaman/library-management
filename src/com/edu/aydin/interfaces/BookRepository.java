package com.edu.aydin.interfaces;

import com.edu.aydin.model.BookModel;

import java.util.List;

public interface BookRepository extends CrudRepository<BookModel> {
    BookModel getById(String id);

    List<BookModel> getByName(String name);

    List<BookModel> getByCategory(String category);

    List<BookModel> getByPublisher(String publisher);

    List<BookModel> getByAuthor(String author);
}