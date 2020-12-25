package com.edu.aydin.service;

import com.edu.aydin.interfaces.UserRepository;
import com.edu.aydin.model.BookModel;
import com.edu.aydin.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserService extends FileService implements UserRepository {

    private static final String USERS_FILE = "users.txt";
    private BookService bookService = new BookService();

    @Override
    public void save(UserModel t) {
        int id = super.getLastId(USERS_FILE);
        t.setId(String.valueOf(id));
        super.writeFile(USERS_FILE, t.toString());
        super.createFile(t.getUsername() + ".txt");
    }

    @Override
    public void save(UserModel t, String id) {
        t.setId(id);
        super.writeFile(USERS_FILE, t.toString());
    }


    @Override
    public void update(String id, UserModel t) {
        String temp = id;
        delete(id);
        save(t, id);
    }


    public void delete(String id) {
        super.deleteLine(USERS_FILE, id);

    }

    public void delete(String id, String username) {
        super.deleteLine(USERS_FILE, id);
        super.deleteFile(username + ".txt");
    }

    @Override
    public List<UserModel> getAll() {
        List<String> lines = super.readLines(USERS_FILE);
        return allToUser(lines);
    }

    private List<UserModel> allToUser(List<String> lines) {

        List<UserModel> users = new ArrayList<>();
        List<BookModel> books = new ArrayList<>();
        System.out.println(lines.size());
        if (lines.size() == 0) {
            return users;
        } else {
            for (String line : lines) {
                String[] datas = line.split("___");
                UserModel user = new UserModel(
                        datas[0],
                        datas[1],
                        datas[2],
                        datas[3],
                        datas[4],
                        datas[5],
                        Boolean.valueOf(datas[6])
                );
                users.add(user);
            }
            return users;
        }
    }

    private UserModel toUser(String line) {
        //datas[3] kullanıcı adı olan dosyayı bulmak için
        String[] datas = line.split("___");
        List<String> userBooks = super.readLines(datas[3] + ".txt");
        List<BookModel> books = new ArrayList<>();

        return new UserModel(
                datas[0],
                datas[1],
                datas[2],
                datas[3],
                datas[4],
                datas[5],
                Boolean.valueOf(datas[6])
        );

    }

    @Override
    public UserModel getById(String id) {
        String line = super.readLineById(USERS_FILE, id);
        return toUser(line);
    }

    @Override
    public UserModel getByUsername(String username) {
        String line = super.readLineById(USERS_FILE, username);
        return toUser(line);
    }

    @Override
    public void addBookUser(String username, BookModel book) {
        String fileName = username + ".txt";
        super.writeFile(fileName, book.toString());
    }

    @Override
    public List<BookModel> getUserBooks(String username) {
        return bookService.allToBook(super.readLines(username + ".txt"));
    }

    @Override
    public BookModel removeBookUser(String username, String bookId) {
        BookModel book = bookService.getById(bookId);
        super.deleteLine(username + ".txt", bookId);
        return book;
    }

    public UserModel login(String username, String password) {
        ArrayList<String> users = super.readLines(USERS_FILE);
        for (String line : users) {
            String[] attr = line.split("___");
            if (attr[3].equals(username) && attr[4].equals(password)) {
                UserModel user = new UserModel((attr[0]), attr[1], attr[2], attr[3], attr[4], attr[5], Boolean.valueOf(attr[6]));
                return user;
            }
        }
        return null;
    }
}
