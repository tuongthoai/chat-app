package com.hcmus.ui;

import com.hcmus.ui.chatbox.ChatBox;
import com.hcmus.ui.chatlist.ChatList;
import com.hcmus.ui.searchbar.SearchBar;
import com.hcmus.ui.sidebar.Sidebar;

import javax.swing.*;
import java.awt.*;

public class ChatLayout extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public ChatLayout() {
        setTitle("Chat UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Sidebar
        Sidebar sidebar = new Sidebar(contentPanel, cardLayout);
        mainPanel.add(sidebar, BorderLayout.WEST);

        // Main screen
        ChatScreen mainScreen = new ChatScreen();
        contentPanel.add(mainScreen, "CHAT");

        // Friend screen
        FriendScreen friendScreen = new FriendScreen();
        contentPanel.add(friendScreen, "FRIENDS");

        // Settings screen
        SettingsScreen settingsScreen = new SettingsScreen();
        contentPanel.add(settingsScreen, "SETTINGS");

        cardLayout.show(contentPanel, "CHAT");

//        // Main panel with BorderLayout
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//        // Sidebar with GridBagLayout
//        JPanel sidebarPanel = new Sidebar();
//
//        // List of conversations (you can use JList or other components)
//        JPanel chatList = new JPanel();
//        chatList.setLayout(new BorderLayout());
//        JScrollPane conversationScrollPane = new ChatList();
//        conversationScrollPane.setPreferredSize(new Dimension(200, 400));
//        chatList.add(new SearchBar(), BorderLayout.NORTH);
//        chatList.add(conversationScrollPane, BorderLayout.CENTER);
//
//        // Message box
//        JPanel chatBox = new ChatBox();
//
//        // Add components to main panel
//        mainPanel.add(sidebarPanel, BorderLayout.WEST);
//        mainPanel.add(chatList, BorderLayout.CENTER);
//        mainPanel.add(chatBox, BorderLayout.EAST);

        // Add main panel to the frame
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private class ChatScreen extends JPanel {
        public ChatScreen() {
            setLayout(new BorderLayout());

            // Main panel with BorderLayout
            JPanel mainPanel = new JPanel(new BorderLayout());

            // List of conversations (you can use JList or other components)
            JPanel chatList = new JPanel();
            chatList.setLayout(new BorderLayout());
            JScrollPane conversationScrollPane = new ChatList();
            conversationScrollPane.setPreferredSize(new Dimension(200, 400));
            chatList.add(new SearchBar(), BorderLayout.NORTH);
            chatList.add(conversationScrollPane, BorderLayout.CENTER);

            // Message box
            JPanel chatBox = new ChatBox();

            // Add components to main panel
            mainPanel.add(chatList, BorderLayout.WEST);
            mainPanel.add(chatBox, BorderLayout.CENTER);

            // Add main panel to the frame
            add(mainPanel, BorderLayout.CENTER);
        }
    }

    private class FriendScreen extends JPanel {
        public FriendScreen() {
            setLayout(new BorderLayout());

            JLabel label = new JLabel("Friend Screen");
            add(label, BorderLayout.CENTER);
        }
    }

    private class SettingsScreen extends JPanel {
        public SettingsScreen() {
            setLayout(new BorderLayout());

            JLabel label = new JLabel("Settings Screen");
            add(label, BorderLayout.CENTER);
        }
    }
}

