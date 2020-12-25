package com.edu.aydin.view;

import com.edu.aydin.model.BookModel;
import com.edu.aydin.model.UserModel;
import com.edu.aydin.service.BookService;
import com.edu.aydin.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class AdminView extends JFrame {

    private JButton addBookButton;
    private JButton addUserButton;
    private JTable bookTable;
    private JButton deleteBookButton;
    private JButton deleteUserButton;
    private JButton editBookButton;
    private JButton editUserButton;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTabbedPane jTabbedPane1;
    private JButton userBooksButton;
    private JTable userTable;
    private JButton logoutButton;

    private UserService userService = new UserService();
    private BookService bookService = new BookService();
    private List<UserModel> userList;
    private List<BookModel> bookList;

    public AdminView() {
        initializeComponents();
        userList = userService.getAll();
        Collections.sort(userList);
        String[] userColumnNames = {"ID", "Name", "Surname", "Username", "Department"};
        DefaultTableModel userTableModel = (DefaultTableModel) userTable.getModel();
        userTableModel.setColumnIdentifiers(userColumnNames);

        for (UserModel user : userList) {
            String[] dataRow = {String.valueOf(user.getId()), user.getName(), user.getSurname(), user.getUsername(), user.getDepartment()};
            userTableModel.addRow(dataRow);
        }

        bookList = bookService.getAll();
        Collections.sort(bookList);
        String[] bookColumnNames = {"ID", "Name", "Author", "Publish Date", "Category", "Publisher"};
        DefaultTableModel bookTableModel = (DefaultTableModel) bookTable.getModel();
        bookTableModel.setColumnIdentifiers(bookColumnNames);

        for (BookModel book : bookList) {
            String[] dataRow = {String.valueOf(book.getId()), book.getName(), book.getAuthor(), book.getPublishDate(), book.getCategory(), book.getPublisher()};
            bookTableModel.addRow(dataRow);
        }
    }

    private void initializeComponents() {
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(720, 510);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jTabbedPane1 = new JTabbedPane();
        jScrollPane2 = new JScrollPane();
        userTable = new JTable();
        addUserButton = new JButton();
        editUserButton = new JButton();
        deleteUserButton = new JButton();
        userBooksButton = new JButton();
        addBookButton = new JButton();
        editBookButton = new JButton();
        deleteBookButton = new JButton();
        jScrollPane1 = new JScrollPane();
        bookTable = new JTable();
        logoutButton = new JButton();

        addUserButton.setText("Add User");
        addUserButton.setBounds(50, 100, 100, 30);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser(e);
            }
        });

        editUserButton.setText("Edit User");
        editUserButton.setBounds(50, 140, 100, 30);
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser(e);
            }
        });


        deleteUserButton.setText("Delete User");
        deleteUserButton.setBounds(50, 180, 100, 30);
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser(e);
            }
        });


        userBooksButton.setText("User Books");
        userBooksButton.setBounds(50, 220, 100, 30);
        userBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUserBooks(e);
            }
        });


        addBookButton.setText("Add Book");
        addBookButton.setBounds(50, 100, 100, 30);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook(e);
            }
        });


        editBookButton.setText("Edit Book");
        editBookButton.setBounds(50, 140, 100, 30);
        editBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBook(e);
            }
        });


        deleteBookButton.setText("Delete Book");
        deleteBookButton.setBounds(50, 180, 100, 30);
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook(e);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.setBounds(50,260,100,30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout(e);
            }
        });


        userTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Surname", "Username", "Department"}));
        jScrollPane1.setViewportView(userTable);
        jScrollPane1.setBounds(180, 20, 520, 400);
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(jScrollPane1);
        panel1.add(addUserButton);
        panel1.add(editUserButton);
        panel1.add(deleteUserButton);
        panel1.add(userBooksButton);
        panel1.add(logoutButton);


        bookTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Name", "Author", "Publish Date", "Category", "Publisher"}));
        jScrollPane2.setViewportView(bookTable);
        jScrollPane2.setBounds(180, 20, 520, 400);
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.add(jScrollPane2);
        panel2.add(addBookButton);
        panel2.add(editBookButton);
        panel2.add(deleteBookButton);
        jTabbedPane1.addTab("Users", panel1);
        jTabbedPane1.addTab("Library", panel2);

        this.setContentPane(jTabbedPane1);
    }


    private void addUser(ActionEvent event) {
        new AddUserView();
        dispose();
    }

    private void editUser(ActionEvent event) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        int selectedRowIndex = userTable.getSelectedRow();
        UserModel userToUpdate = userService.getById(model.getValueAt(selectedRowIndex, 0).toString());
        System.out.println("gelen " + userToUpdate.getName());
        new AddUserView(userToUpdate);
        dispose();
    }

    private void deleteUser(ActionEvent event) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        int selectedRowIndex = userTable.getSelectedRow();
        userService.delete(model.getValueAt(selectedRowIndex, 0).toString(), model.getValueAt(selectedRowIndex, 3).toString());
        model.removeRow(selectedRowIndex);
    }

    private void showUserBooks(ActionEvent event) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        int selectedRowIndex = userTable.getSelectedRow();
        String username = model.getValueAt(selectedRowIndex, 3).toString();
        new UserBooksView(username);
        dispose();
    }

    private void logout(ActionEvent event){
        new LoginView();
        dispose();
    }

    private void addBook(ActionEvent event) {
        new AddBookView();
        dispose();
    }

    private void editBook(ActionEvent event) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        int selectedRowIndex = bookTable.getSelectedRow();
        BookModel bookToUpdate = bookService.getById(model.getValueAt(selectedRowIndex, 0).toString());
        new AddBookView(bookToUpdate);
        dispose();
    }

    private void deleteBook(ActionEvent event) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        int selectedRowIndex = bookTable.getSelectedRow();
        bookService.delete(model.getValueAt(selectedRowIndex, 0).toString());
        model.removeRow(selectedRowIndex);
    }

    public void setTabIndex(int index){
        jTabbedPane1.setSelectedIndex(index);
        setVisible(true);
    }
}