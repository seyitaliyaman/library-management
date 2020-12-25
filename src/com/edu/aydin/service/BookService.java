package com.edu.aydin.service;

import com.edu.aydin.interfaces.BookRepository;
import com.edu.aydin.model.BookModel;

import java.util.ArrayList;
import java.util.List;

public class BookService extends FileService implements BookRepository {

    private static final String FILE_NAME = "books.txt";

    @Override
    public BookModel getById(String id) {
        String line = super.readLineById(FILE_NAME, id);
        return toBook(line);
    }


    @Override
    public List<BookModel> getByCategory(String category) {
        List<String> lines = super.readLinesByCategory(FILE_NAME, category);
        return allToBook(lines);
    }


    public List<BookModel> getByName(String name) {
        List<String> lines = super.readLinesByName(FILE_NAME, name);
        return allToBook(lines);
    }


    @Override
    public List<BookModel> getByPublisher(String publisher) {
        List<String> lines = super.readLinesByPublisher(FILE_NAME, publisher);
        return allToBook(lines);
    }

    @Override
    public List<BookModel> getByAuthor(String author) {
        List<String> lines = super.readLinesByAuthor(FILE_NAME, author);
        return allToBook(lines);
    }

    @Override
    public void save(BookModel book) {

        int id = super.getLastId(FILE_NAME);
        book.setId(String.valueOf(id));
        super.writeFile(FILE_NAME, book.toString());
    }

    @Override
    public void save(BookModel book, String id) {
        book.setId(id);
        super.writeFile(FILE_NAME, book.toString());
    }

    @Override
    public void update(String id, BookModel book) {
        String temp = id;
        delete(id);
        this.save(book, temp);
    }

    @Override
    public void delete(String id) {
        super.deleteLine(FILE_NAME, id);
    }

    @Override
    public List<BookModel> getAll() {

        List<String> lines = super.readLines(FILE_NAME);
        return allToBook(lines);
    }


    public BookModel toBook(String line) {
        String[] datas = line.split("___");
        return new BookModel(
                datas[0],
                datas[1],
                datas[2],
                datas[3],
                datas[4],
                datas[5]
        );

    }

    public List<BookModel> allToBook(List<String> lines) {
        List<BookModel> books = new ArrayList<>();
        if (lines.size() != 0) {
            for (String line : lines) {
                String[] datas = line.split("___");
                BookModel book = new BookModel(
                        datas[0],
                        datas[1],
                        datas[2],
                        datas[3],
                        datas[4],
                        datas[5]
                );
                books.add(book);
            }
        }
        return books;
    }
}