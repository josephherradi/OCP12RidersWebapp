package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Chat;

import java.util.List;

public interface ChatManager {
    public void saveChat(Chat chat);
    public List<Chat> chatList();
}
