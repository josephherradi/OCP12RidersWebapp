package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class SortieController {
    @Autowired
    private SortieManager sortieManager;



    @RequestMapping(value = "sorties",method = RequestMethod.GET)
    public String listeSorties(Model theModel){
        List<Sortie> sortiesList=sortieManager.sortiesList();
        theModel.addAttribute("sortiesList",sortiesList);
        return "liste-sorties";
    }

    @RequestMapping(value = "saveFormSortie",method = RequestMethod.POST)
    public String saveSortie(@ModelAttribute Sortie laSortie, BindingResult result, HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        String userId= (String) request.getSession().getAttribute("user");
        laSortie.setOrganisateur(loggedUser);
        laSortie.setDate(new Date());
        sortieManager.saveSortie(laSortie);
        return "redirect:/sorties";
    }
}
