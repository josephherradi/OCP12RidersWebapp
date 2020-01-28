package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.EtapeManager;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

@RequestMapping("etapes")
public class EtapeController {
    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private EtapeManager etapeManager;



@RequestMapping("etapesList")
    public String etapesList(@RequestParam("sortieId") Integer sortieId, Model model){
    Sortie laSortie=sortieManager.findById(sortieId);
    List<Etape> etapesList=etapeManager.findBySortie(laSortie);
    model.addAttribute("listetapes",etapesList);

    return "liste-etapes";
}

@RequestMapping("getEtape")
    public String getEtape(@RequestParam("etapeId") Integer etapeId, Model model){
    Etape letape=etapeManager.findById(etapeId);
    model.addAttribute("letape",letape);
    return "etape-form";
}

@RequestMapping("saveEtape")
    public String saveEtape(@ModelAttribute Etape letape, @RequestParam("sortieId") Integer sortieId){
    etapeManager.saveEtape(letape,sortieId);
    return "redirect:/etapes/etapesList?sortieId="+sortieId;
}


}
