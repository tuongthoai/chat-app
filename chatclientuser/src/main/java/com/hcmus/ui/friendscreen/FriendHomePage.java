package com.hcmus.ui.friendscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendHomePage extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardContainPanel;
    public FriendHomePage(){
        cardLayout = new CardLayout();
        cardContainPanel = new JPanel();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setBackground(Color.WHITE);
//        mainPanel.setPreferredSize(new Dimension(600, 500));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.WHITE);

        JButton listFriendButton = createButton("List Friend", "LIST_FRIEND");

        JButton addFriendButton = createButton("Add New Friend", "ADD_FRIEND");

        topPanel.add(listFriendButton);
        topPanel.add(Box.createHorizontalStrut(30));
        topPanel.add(addFriendButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        cardContainPanel.setLayout(cardLayout);
        cardContainPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        cardContainPanel.setBackground(Color.WHITE);

        ListFriend listFriendCard = new ListFriend();
        cardContainPanel.add(listFriendCard, "LIST_FRIEND");

        NewFriend newFriendCard = new NewFriend();
        cardContainPanel.add(newFriendCard, "ADD_FRIEND");

        cardLayout.show(cardContainPanel, "LIST_FRIEND");

        mainPanel.add(cardContainPanel, BorderLayout.CENTER);


        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String title, String cardTitle){
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(200,30));
        button.setFont(new Font("Serif", Font.PLAIN, 18));

        button.addActionListener(new MainButtonAction(cardTitle, cardLayout, cardContainPanel));

        return button;
    }
}
