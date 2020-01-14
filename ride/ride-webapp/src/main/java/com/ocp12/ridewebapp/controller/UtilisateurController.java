package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.UtilisateurManager;
import com.ocp12.rideconsumer.dao.UtilisateurDao;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurManager utilisateurManager;

    @Autowired
    private UtilisateurDao utilisateurDao;

    public static String encrypt(String source) {
        String md5 = null;
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(source.getBytes(), 0, source.length());
            md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        } catch (Exception ex) {
            return null;
        }
        return md5;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String listSpots(@RequestParam("userId") String userId, @RequestParam("password") String password,
                            HttpSession session, ModelMap theModelMap) {
        Utilisateur registredUserId = utilisateurManager.findByIdentifiant(userId);
        try {
            String registredID = registredUserId.getIdentifiant();
            String registredPWD = registredUserId.getPassword();
            if (userId.equalsIgnoreCase(registredID) && encrypt(password).equalsIgnoreCase(registredPWD)) {
                session.setAttribute("user", userId);
                session.setAttribute("theUser", registredUserId);

                return "redirect:/sorties";
            } else {
                theModelMap.put("error", "Please check Id & password");
                return "index";

            }

        } catch (NullPointerException e) {
            theModelMap.put("error", "Please register account first");

        }
        ;

        return "index";

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:../account";

    }

    @RequestMapping(value = "registrationForm", method = RequestMethod.GET)
    public String registrationForm(Model theModel) {
        Utilisateur utilisateur = new Utilisateur();
        theModel.addAttribute("utilisateur", utilisateur);
        return "userRegistration-form";
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        utilisateur.setPassword(encrypt(utilisateur.getPassword()));
        utilisateurManager.saveUtilisateur(utilisateur);
        return "redirect:../account";
    }
}
