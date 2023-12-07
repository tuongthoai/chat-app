package com.hcmus.chatserver.service;

import com.hcmus.chatserver.entities.user.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatSocketSessionContext implements InitializingBean {
    private final Map<Integer, WebSocketSession> sessions;
    private final Map<String, Integer> session2User;
    private final Map<Integer, List<Integer>> groupChatMembers;
    @Autowired
    private GroupChatService service;

    public ChatSocketSessionContext() {
        sessions = new HashMap<>();
        session2User = new HashMap<>();
        groupChatMembers = new HashMap<>();
    }

    public void sendMsg2User(Integer receiver, TextMessage msg) throws IOException {
        if (sessions.containsKey(receiver)) {
            sessions.get(receiver).sendMessage(msg);
        }

        // lưu db
    }

    public void sendMsg2Group(List<Integer> receivers, TextMessage msg) throws IOException {
        for (Integer id : receivers) {
            WebSocketSession session = sessions.getOrDefault(id, null);
            if (session != null) {
                session.sendMessage(msg);
            }
        }
    }

    public void send2Group(Integer groupId, TextMessage msg) throws Exception {
        if (groupChatMembers.containsKey(groupId)) {
            List<Integer> members = groupChatMembers.get(groupId);
            for(Integer id : members){
                if (sessions.containsKey(id)) {
                    sessions.get(id).sendMessage(msg);
                }
            }
        }

        // lưu db
    }

    public void removeSession(String sessionId) {
        Integer userId = session2User.get(sessionId);
        sessions.remove(userId);
        session2User.remove(sessionId);
    }

    public void addSession(Integer userId, WebSocketSession session) {
        sessions.put(userId, session);
        session2User.put(session.getId(), userId);

        // get all chat members
        try {
            List<Integer> groupChatIdsOfUser = service.findAllGroupChatByUserId(userId);
            for(Integer gchatId : groupChatIdsOfUser) {
                List<User> membersId = service.findAllMembers(gchatId);
                List<Integer> integerList = membersId.stream()
                        .map(User::getId)
                        .collect(Collectors.toList());
                if (!groupChatMembers.containsKey(gchatId)) {
                    groupChatMembers.put(gchatId, integerList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addGroupChat(int gChatId, List<Integer> membersId) {
        groupChatMembers.put(gChatId, membersId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Done ChatSession Context");
    }
}