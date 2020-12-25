package com.edu.aydin.view;

import com.edu.aydin.model.UserModel;
import com.edu.aydin.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserView extends JFrame {

    private JButton cancelButton;
    private JTextField departmentTF;
    private JCheckBox isAdmin;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JTextField nameTF;
    private JTextField passwordTF;
    private JButton saveButton;
    private JTextField surnameTF;
    private JLabel jLabel3;
    private JTextField usernameTF;

    private Boolean isEdit;
    private UserService userService;
    private UserModel user;

    public AddUserView() {
        initializeComponents();
        this.userService = new UserService();
        this.isEdit = false;
    }

    public AddUserView(UserModel userToUpdate) {
        initializeComponents();
        this.userService = new UserService();
        usernameTF.setEnabled(false);
        this.isEdit = true;
        this.user = userToUpdate;
        userToView(userToUpdate);
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
        nameTF = new JTextField();
        surnameTF = new JTextField();
        usernameTF = new JTextField();
        passwordTF = new JTextField();
        departmentTF = new JTextField();
        isAdmin = new JCheckBox();
        saveButton = new JButton();
        cancelButton = new JButton();
        jLabel1.setText("Name :");
        jLabel1.setBounds(70, 60, 100, 30);
        this.add(jLabel1);
        jLabel2.setText("Surname :");
        jLabel2.setBounds(70, 100, 100, 30);
        this.add(jLabel2);
        jLabel3.setText("Username :");
        jLabel3.setBounds(70, 140, 100, 30);
        this.add(jLabel3);
        jLabel4.setText("Password :");
        jLabel4.setBounds(70, 180, 100, 30);
        this.add(jLabel4);
        jLabel5.setText("Department :");
        jLabel5.setBounds(70, 220, 100, 30);
        this.add(jLabel5);
        nameTF.setBounds(170, 60, 100, 30);
        this.add(nameTF);
        surnameTF.setBounds(170, 100, 100, 30);
        this.add(surnameTF);
        usernameTF.setBounds(170, 140, 100, 30);
        this.add(usernameTF);
        passwordTF.setBounds(170, 180, 100, 30);
        this.add(passwordTF);
        departmentTF.setBounds(170, 220, 100, 30);
        this.add(departmentTF);
        isAdmin.setText("Admin");
        isAdmin.setBounds(70, 260, 100, 30);
        this.add(isAdmin);
        saveButton.setText("Save");
        saveButton.setBounds(70, 310, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(e);
            }
        });
        this.add(saveButton);
        cancelButton.setText("Cancel");
        cancelButton.setBounds(170, 310, 100, 30);
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
            userFromView(user);
            userService.update(user.getId(), user);
        } else {

            user = new UserModel();
            userFromView(user);
            userService.save(user);
        }
        new AdminView().setTabIndex(0);
        dispose();
    }

    private void cancel(ActionEvent event) {
        new AdminView().setTabIndex(0);
        dispose();
    }

    private void userFromView(UserModel user) {
        user.setName(nameTF.getText());
        user.setSurname(surnameTF.getText());
        user.setUsername(usernameTF.getText());
        user.setPassword(passwordTF.getText());
        user.setDepartment(departmentTF.getText());
        user.setIsAdmin(isAdmin.isSelected());
    }

    private void userToView(UserModel user) {
        nameTF.setText(user.getName());
        surnameTF.setText(user.getSurname());
        usernameTF.setText(user.getUsername());
        passwordTF.setText(user.getPassword());
        departmentTF.setText(user.getDepartment());
        isAdmin.setSelected(user.getIsAdmin());
    }
}
