package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.ChatDao;
import com.ocp12.ridemodele.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatManagerImpl implements ChatManager {
    @Autowired
    private ChatDao chatDao;

    @Override
    public void saveChat(Chat chat) {
        chatDao.save(chat);


    }

    @Override
    public List<Chat> chatList() {
        return chatDao.findAll();
    }
}
