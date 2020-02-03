package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.CommentaireManager;
import com.ocp12.ridebusiness.ParticipantManager;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridebusiness.exceptions.FunctionalException;
import com.ocp12.ridemodele.Commentaire;
import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
@Controller

@RequestMapping("sorties")
public class CommentaireController {
    @Autowired
    private ParticipantManager participantManager;

    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private CommentaireManager commentaireManager;


    @RequestMapping(value = "{sortieId}/commentsList")
    public String sortieCommentsList(@PathVariable("sortieId") Integer sortieId,Model model){
        List<Commentaire> commentaireList=commentaireManager.sortieCommentairesList(sortieId);
        model.addAttribute("commentaireList",commentaireList);
    return "commentaires-list";
    }

    @RequestMapping(value = "{sortieId}/showCommentForm",method = RequestMethod.GET)
    public String showformComment (Model model, @PathVariable("sortieId") Integer sortieId){
        Commentaire lecommentaire=new Commentaire();
        model.addAttribute("lecommentaire",lecommentaire);
        return "commentaire-form";
    }

    @RequestMapping(value = "{sortieId}/saveCommentForm",method = RequestMethod.POST)
    public String saveFormComment(@ModelAttribute Commentaire lecommentaire, @PathVariable("sortieId") Integer sortieId, HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Sortie sortie=sortieManager.findById(sortieId);
        Participant participant=participantManager.findByUtilisateurAndSortie(loggedUser,sortie);
        lecommentaire.setParticipant(participant);
        lecommentaire.setDate(new Date());
        commentaireManager.saveCommentaire(lecommentaire);
        return "redirect:/sorties/{sortieId}/commentsList";
    }



}
