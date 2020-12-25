package com.edu.aydin.view;

import com.edu.aydin.model.BookModel;
import com.edu.aydin.service.BookService;
import com.edu.aydin.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class UserView extends JFrame {

    private JComboBox<String> chooseFilter;
    private JTextField filterTxt;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTabbedPane jTabbedPane1;
    private JTable libraryTable;
    private JButton listBtn;
    private JButton logoutButton;
    private JTable myLibraryTable;
    private JButton returnBtn;
    private JButton takeBook;

    private UserService userService;
    private BookService bookService;
    private List<BookModel> libraryBooksList;
    private List<BookModel> userBooksList;
    private String loginUsername;
    private static final String FILE_NAME = "books.txt";
    private String choosing;

    public UserView(String username){

        initializeComponents();
        this.loginUsername = username;
        this.userService = new UserService();
        this.bookService = new BookService();
        libraryBooksList = bookService.getAll();
        refreshList(libraryBooksList);
    }

    private void initializeComponents(){

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(720, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jScrollPane2 = new JScrollPane();
        libraryTable = new JTable();
        listBtn = new JButton();
        takeBook = new JButton();
        filterTxt = new JTextField();
        chooseFilter = new JComboBox<>();
        logoutButton = new JButton();
        jPanel2 = new JPanel();
        jScrollPane1 = new JScrollPane();
        myLibraryTable = new JTable();
        returnBtn = new JButton();

        chooseFilter.setModel(new DefaultComboBoxModel<>(new String[] { "All", "Name", "Author", "Category" }));
        chooseFilter.setBounds(50,100,100,30);


        filterTxt.setBounds(50, 140, 100, 30);

        listBtn.setText("List Books");
        listBtn.setBounds(50, 180, 100, 30);
        listBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listBooks(e);
            }
        });

        takeBook.setText("Take Book");
        takeBook.setBounds(50, 220, 100, 30);
        takeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeBook(e);
            }
        });

        logoutButton.setText("Log Out");
        logoutButton.setBounds(50, 260, 100, 30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout(e);
            }
        });

        returnBtn.setText("Return Book");
        returnBtn.setBounds(50, 220, 100, 30);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook(e);
            }
        });




        libraryTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"ID", "Name", "Author", "Category", "PublishDate", "Publisher"}));
        jScrollPane1.setViewportView(libraryTable);
        jScrollPane1.setBounds(180, 20, 520, 400);
        jPanel1.setLayout(null);
        jPanel1.add(jScrollPane1);
        jPanel1.add(chooseFilter);
        jPanel1.add(filterTxt);
        jPanel1.add(takeBook);
        jPanel1.add(listBtn);
        jPanel1.add(logoutButton);


        myLibraryTable.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {"ID", "Name", "Author", "Category", "PublishDate", "Publisher"}));
        jScrollPane2.setViewportView(myLibraryTable);
        jScrollPane2.setBounds(180, 20, 520, 400);
        jPanel2.setLayout(null);
        jPanel2.add(jScrollPane2);
        jPanel2.add(returnBtn);

        jTabbedPane1.addTab("Library",jPanel1);
        jTabbedPane1.addTab("My Library",jPanel2);

        this.setContentPane(jTabbedPane1);
    }

    private void listBooks(ActionEvent event){
        List<BookModel> books ;

        choosing = chooseFilter.getSelectedItem().toString();

        if(choosing == "Name"){
            libraryBooksList = bookService.getByName(filterTxt.getText());
        }
        else if (choosing == "Author"){

            libraryBooksList = bookService.getByAuthor(filterTxt.getText());
        }
        else if (choosing=="Category"){
            libraryBooksList = bookService.getByCategory(filterTxt.getText());
        }
        else{
            libraryBooksList = bookService.getAll();
        }

        refreshList(libraryBooksList);
    }

    private void takeBook(ActionEvent event){
        TableModel model = libraryTable.getModel();
        int indexs[] = libraryTable.getSelectedRows();

        for(int i=0; i<indexs.length; i++){
            String bookId = model.getValueAt(indexs[i], 0).toString();
            BookModel book = bookService.getById(bookId);
            userService.addBookUser(loginUsername, book);
        }

        refreshList(libraryBooksList);
    }

    private void logout(ActionEvent event){
        new LoginView();
        dispose();
    }

    private void returnBook(ActionEvent event){
        TableModel model = myLibraryTable.getModel();
        int indexs[] = myLibraryTable.getSelectedRows();
        for(int i=0; i<indexs.length; i++){
            String bookId = model.getValueAt(indexs[i], 0).toString();
            libraryBooksList.add(userService.removeBookUser(loginUsername, bookId));
        }

        refreshList(libraryBooksList);
    }

    private void refreshList(List<BookModel> libraryBooksList){

        userBooksList = userService.getUserBooks(loginUsername);
        Collections.sort(libraryBooksList);
        Collections.sort(userBooksList);
        String[] tableColumnNames = {"ID", "Name", "Author", "Publish Date", "Category", "Publisher" };
        DefaultTableModel libraryTableModel = (DefaultTableModel)libraryTable.getModel();
        DefaultTableModel userLibraryTableModel = (DefaultTableModel)myLibraryTable.getModel();

        libraryTableModel.getDataVector().removeAllElements();
        userLibraryTableModel.getDataVector().removeAllElements();
        for(int i=0; i<userBooksList.size(); i++){
            for(int j=0; j<libraryBooksList.size(); j++){
                if(userBooksList.get(i).getId().equals(libraryBooksList.get(j).getId())){
                    libraryBooksList.remove(j);
                    break;
                }
            }
        }


        libraryTableModel.setColumnIdentifiers(tableColumnNames);
        for(BookModel book : libraryBooksList){
            String[] dataRow = {book.getId(), book.getName(), book.getAuthor(), book.getPublishDate(), book.getCategory(), book.getPublisher() };
            libraryTableModel.addRow(dataRow);
        }


        userLibraryTableModel.setColumnIdentifiers(tableColumnNames);
        for(BookModel book : userBooksList){
            String[] dataRow = {book.getId(), book.getName(), book.getAuthor(), book.getPublishDate(), book.getCategory(), book.getPublisher() };
            userLibraryTableModel.addRow(dataRow);
        }
    }
}
