package com.edu.aydin.view;

import com.edu.aydin.model.BookModel;
import com.edu.aydin.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class UserBooksView extends JFrame {

    private JButton exitButton;
    private JInternalFrame jInternalFrame1;
    private JScrollPane jScrollPane1;
    private JLabel tableTitle;
    private JTable userBooksTable;

    private UserService userService;
    private List<BookModel> bookList;
    private String username;

    public UserBooksView(String username){
        initializeComponents();
        this.userService = new UserService();
        this.username = username;

        tableTitle.setText(username.toUpperCase()+"\'S BOOKS");
        bookList = userService.getUserBooks(username);
        Collections.sort(bookList);
        String[] bookColumnNames = {"ID", "Name", "Author", "Publish Date", "Category", "Publisher" };
        DefaultTableModel bookTableModel = (DefaultTableModel)userBooksTable.getModel();
        bookTableModel.setColumnIdentifiers(bookColumnNames);

        for(BookModel book: bookList){
            String[] dataRow = {String.valueOf(book.getId()), book.getName(), book.getAuthor(), book.getPublishDate(), book.getCategory(), book.getPublisher() };
            bookTableModel.addRow(dataRow);
        }
    }

    private void initializeComponents(){

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jInternalFrame1 = new JInternalFrame();
        jScrollPane1 = new JScrollPane();
        userBooksTable = new JTable();
        exitButton = new JButton();
        tableTitle = new JLabel();

        tableTitle.setText(username+"'s Books");
        tableTitle.setBounds(230,10,300,30);
        this.add(tableTitle);

        exitButton.setText("Exit");
        exitButton.setBounds(250,390,70,30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(e);
            }
        });
        this.add(exitButton);

        userBooksTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"ID", "Name", "Author", "Category", "Publish Date", "Publisher"}));

        jScrollPane1.setViewportView(userBooksTable);
        jScrollPane1.setBounds(10, 40, 580, 350);
        this.add(jScrollPane1);
    }

    private void exit(ActionEvent e){
        new AdminView();
        dispose();
    }
}
