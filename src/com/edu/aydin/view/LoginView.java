package com.edu.aydin.view;

import com.edu.aydin.model.UserModel;
import com.edu.aydin.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton loginButton;
    private JTextField password;
    private JTextField username;

    private UserService userService;

    public LoginView(){
        this.userService = new UserService();
        initializeComponents();
    }

    private void initializeComponents(){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        username = new JTextField();
        password = new JTextField();
        loginButton = new JButton();
        jLabel3 = new JLabel();
        jLabel1.setText("Username");
        jLabel1.setBounds(150,200,100,30);
        this.add(jLabel1);
        jLabel2.setText("Password");
        jLabel2.setBounds(150,230,100,30);
        this.add(jLabel2);
        jLabel3.setText("Welcome To Library Management System");
        jLabel3.setBounds(120,100,300,30);
        this.add(jLabel3);
        username.setBounds(250,200,100,30);
        this.add(username);
        password.setBounds(250,230,100,30);
        this.add(password);
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(e);
            }
        });
        loginButton.setBounds(190,300,100,30);
        this.add(loginButton);

    }


    private void login(ActionEvent event){
        UserModel user = userService.login(this.username.getText().trim(), this.password.getText().trim());
        if(user == null){
            JOptionPane.showMessageDialog(new JFrame(), "Username or password is wrong!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if(user.getIsAdmin()){
                new AdminView();
            }else{
                new UserView(user.getUsername());
            }
            this.dispose();
        }
    }
}
