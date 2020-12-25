package com.edu.aydin.view;

import com.edu.aydin.model.BookModel;
import com.edu.aydin.service.BookService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookView extends JFrame {

    private JTextField authorTF;
    private JButton cancelButton;
    private JTextField categoryTF;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTextField nameTF;
    private JTextField publishDateTF;
    private JTextField publisherTF;
    private JButton saveButton;

    private Boolean isEdit;
    private BookModel book;
    private BookService bookService;

    public AddBookView() {
        initializeComponents();
        this.bookService = new BookService();
        this.isEdit = false;

    }

    public AddBookView(BookModel bookToUpdate) {
        initializeComponents();
        this.bookService = new BookService();
        this.isEdit = true;
        this.book = bookToUpdate;
        bookToView(book);

    }


    private void initializeComponents() {
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(350, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        saveButton = new JButton();
        cancelButton = new JButton();
        nameTF = new JTextField();
        authorTF = new JTextField();
        categoryTF = new JTextField();
        publishDateTF = new JTextField();
        publisherTF = new JTextField();
        jLabel1.setText("Name :");
        jLabel1.setBounds(70, 80, 100, 30);
        this.add(jLabel1);
        jLabel2.setText("Author :");
        jLabel2.setBounds(70, 120, 100, 30);
        this.add(jLabel2);
        jLabel3.setText("Category :");
        jLabel3.setBounds(70, 160, 100, 30);
        this.add(jLabel3);
        jLabel4.setText("Publish Date :");
        jLabel4.setBounds(70, 200, 100, 30);
        this.add(jLabel4);
        jLabel5.setText("Publisher :");
        jLabel5.setBounds(70, 240, 100, 30);
        this.add(jLabel5);
        nameTF.setBounds(170, 80, 100, 30);
        this.add(nameTF);
        authorTF.setBounds(170, 120, 100, 30);
        this.add(authorTF);
        categoryTF.setBounds(170, 160, 100, 30);
        this.add(categoryTF);
        publishDateTF.setBounds(170, 200, 100, 30);
        this.add(publishDateTF);
        publisherTF.setBounds(170, 240, 100, 30);
        this.add(publisherTF);
        saveButton.setText("Save");
        saveButton.setBounds(70, 300, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(e);
            }
        });
        this.add(saveButton);
        cancelButton.setText("Cancel");
        cancelButton.setBounds(170, 300, 100, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel(e);
            }
        });
        this.add(cancelButton);
    }

    private void save(ActionEvent event) {

        if (isEdit) {
            bookFromView(book);
            bookService.update(book.getId(), book);
        } else {
            book = new BookModel();
            bookFromView(book);
            bookService.save(book);
        }
        new AdminView().setTabIndex(1);
        dispose();
    }

    private void cancel(ActionEvent event) {
        new AdminView().setTabIndex(1);
        dispose();
    }

    private void bookToView(BookModel book) {
        nameTF.setText(book.getName());
        authorTF.setText(book.getAuthor());
        publishDateTF.setText(book.getPublishDate());
        publisherTF.setText(book.getPublisher());
        categoryTF.setText(book.getCategory());
    }

    private void bookFromView(BookModel book) {
        book.setName(nameTF.getText());
        book.setAuthor(authorTF.getText());
        book.setCategory(categoryTF.getText());
        book.setPublishDate(publishDateTF.getText());
        book.setPublisher(publisherTF.getText());
    }
}
