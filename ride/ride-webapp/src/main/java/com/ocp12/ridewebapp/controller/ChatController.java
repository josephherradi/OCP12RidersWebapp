package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.ChatManager;
import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.ridemodele.Chat;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatManager chatManager;

    @Autowired
    private Brules brules;


    @RequestMapping(value = "showChatForm",method = RequestMethod.GET)
    public String newMsg(Model model){
        Chat chat = new Chat();
        model.addAttribute("chat",chat);
        return "chat-form";
    }

    @RequestMapping(value = "saveChat",method = RequestMethod.POST)
    public String saveChat(@ModelAttribute Chat chat, HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        chat.setDate(new Date());
        chat.setUtilisateur(loggedUser);
        brules.checkRegistredUser(loggedUser.getIdentifiant());
        chatManager.saveChat(chat);
        return "redirect:/chatFeed";
    }

    @RequestMapping(value = "chatFeed",method = RequestMethod.GET)
    public String chatFeed(Model model){
        List<Chat> chatList=chatManager.chatList();
        model.addAttribute("chatFeed",chatList);
        return "chat-feed";
    }

}
