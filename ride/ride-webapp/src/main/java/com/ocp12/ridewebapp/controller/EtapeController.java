package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.EtapeManager;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
