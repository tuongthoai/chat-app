package com.hcmus.ui.chatbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ChatBox extends JPanel {
    private static final String imageDir = System.getProperty("user.dir") + "/chatclientuser/src/main/java/com/hcmus/ui/images/";
    private JTextArea chatContent;
    private ArrayList<ChatMessage> chatMessages;
    private String username;
    public ChatBox(){
        username = "usn1";
        chatMessages = initChatMessage();

        setPreferredSize(new Dimension(500, 400));
        this.setLayout(new BorderLayout());

        //////////////////////////  HEADER ////////////////////
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(500, 50));

        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setOpaque(true);

        // Create a rounded image icon
        RoundImageIcon avatarImage = new RoundImageIcon(imageDir + "LogoHCMUS.jpg", 40, 40);
        JLabel userAvatar = new JLabel(avatarImage);
        userAvatar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // Create labels for username and status
        JLabel usernameLabel = new JLabel("Tran Gia Thinh");

        // Create a panel for the status with a small green circle
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Use FlowLayout
        statusPanel.setOpaque(false);
        statusPanel.setPreferredSize(new Dimension(40, 40)); // Set preferred size

        // Create a small green circle
        JLabel statusCircle = new JLabel();
        statusCircle.setPreferredSize(new Dimension(10, 10));
        statusCircle.setBackground(Color.GREEN);
        statusCircle.setOpaque(true);

        // Add the "Active" label
        JLabel statusLabel = new JLabel("Active");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
        // Add the status components to the statusPanel
        statusPanel.add(statusCircle, BorderLayout.WEST);
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        // Add components to the UserPanel
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(usernameLabel);
        textPanel.add(statusPanel);
        textPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        userPanel.add(userAvatar, BorderLayout.WEST);
        userPanel.add(textPanel, BorderLayout.CENTER);

        header.add(userPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();

        JButton searchButton = createButton("Search", "search-icon.png");
        JButton deleteButton = createButton("Delete", "trash-solid.png");

        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);

        header.add(buttonPanel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
        ////////////////////////////////  HEADER ////////////////////

        ////////////// CHAT CONTENT //////////////////////////////////
        chatContent = new JTextArea();
        chatContent.setEditable(false);
        Font customFont = new Font("Arial", Font.PLAIN, 14); // You can choose your preferred font
        chatContent.setFont(customFont);
        chatContent.setLineWrap(true);
        chatContent.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        display();
        add(scrollPane, BorderLayout.CENTER);
        ////////////// CHAT CONTENT //////////////////////////////////

        /////////////// FOOTER ///////////////////////////////////
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(500, 30));

        // Add text input field
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 30));
        footer.add(textField, BorderLayout.WEST);

        // Add send message button
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(textField.getText().isEmpty())){
                    String mess = textField.getText();
                    textField.setText(""); // Clear the text field after sending

                    chatMessages.add(new ChatMessage(username, "",mess));

                    display();
                }
            }
        });
        footer.add(sendButton, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);
        /////////////// FOOTER ///////////////////////////////////
    }

    private void display() {
        chatContent.setText(""); // Clear the existing messages before displaying

        for (ChatMessage message : chatMessages) {
            if(!message.getUsername().equals(this.username))
                chatContent.append("< " + message.getUsername() + " >: " + message.getMessage() + "\n");
            else
                chatContent.append("< Me >: " + message.getMessage() + "\n");

            chatContent.setCaretPosition(chatContent.getDocument().getLength());
        }
    }

    private ArrayList<ChatMessage> initChatMessage(){
        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage("usn2", "LogoHCMUS.jpg","Hello"));
        chatMessages.add(new ChatMessage("usn2", "LogoHCMUS.jpg","I am Gia Thinh"));
        chatMessages.add(new ChatMessage("usn1", "","Hello"));
        chatMessages.add(new ChatMessage("usn2", "LogoHCMUS.jpg","Nice to meet you"));
        return chatMessages;
    }
    private static JButton createButton(String text, String iconFilename) {
        JButton button = new JButton();

        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        // Load the icon
        ImageIcon icon = new ImageIcon(imageDir + iconFilename);
        button.setIcon(icon);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Add vertical spacing around the button
        int topPadding = 10;
        int bottomPadding = 10;
        int leftPadding = 10;
        int rightPadding = 10;
        button.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, text + " Clicked!");
            }
        });
        return button;
    }
}