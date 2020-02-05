package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.*;
import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.ridebusiness.exceptions.FunctionalException;
import com.ocp12.rideconsumer.dto.SortieKmlDto;
import com.ocp12.rideconsumer.kmlparser.CoordinatesBean;
import com.ocp12.rideconsumer.kmlparser.KmlParser;
import com.ocp12.ridemodele.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("/sorties")
@Controller
public class SortieController {
    private static String UPLOAD_FOLDER = "upload-dir/";



    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private ParticipantManager participantManager;


    @Autowired
    private EtapeManager etapeManager;

    @Autowired
    private EtapeImport etapeImport;

    @Autowired
    private Brules brules;

    @Autowired
    private CommentaireManager commentaireManager;

    @RequestMapping(value = "/liste",method = RequestMethod.GET)
    public String listeSorties(Model theModel){
        List<Sortie> sortiesList=sortieManager.sortiesList();
        theModel.addAttribute("sortiesList",sortiesList);
        return "liste-sorties";
    }

    @RequestMapping(value = "saveFormSortie",method = RequestMethod.POST)
    public String saveSortie(@ModelAttribute SortieKmlDto sortieKmlDto,ModelMap modelMap , HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        sortieKmlDto.setOrganisateur(loggedUser);
        Sortie laSortie=new Sortie();
        laSortie.setOrganisateur(sortieKmlDto.getOrganisateur());
        laSortie.setDate(sortieKmlDto.getDate());
        laSortie.setDescription(sortieKmlDto.getDescription());
        laSortie.setDistance(sortieKmlDto.getDistance());
        laSortie.setDuree(sortieKmlDto.getDuree());
        laSortie.setHorspiste(sortieKmlDto.getHorspiste());
        laSortie.setNiveau(sortieKmlDto.getNiveau());
        laSortie.setNom(sortieKmlDto.getNom());
        laSortie.setNbrParticipants(sortieKmlDto.getNbrParticipants());
        laSortie.setStatut("en attente");
        sortieManager.saveSortie(laSortie);
        MultipartFile file= sortieKmlDto.getFile();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            laSortie.setFilename(file.getOriginalFilename());
            Files.write(path, bytes);
            String folder= UPLOAD_FOLDER + file.getOriginalFilename();
            Integer N=etapeImport.processor(folder,laSortie);
            laSortie.setNbrEtapes(N);
            sortieManager.saveSortie(laSortie);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Participant participant=new Participant();
        participant.setStatut("en attente");
        participant.setSortie(laSortie);
        participant.setUtilisateur(loggedUser);
        participantManager.saveParticipant(participant);


        return "redirect:/sorties/liste";
    }

    @RequestMapping(value = "showFormSortie",method = RequestMethod.GET)
    public String newSortie(ModelMap modelMap){
        SortieKmlDto sortie=new SortieKmlDto();
        modelMap.addAttribute("laSortie",sortie);
        return "sortie-form";

    }

    @RequestMapping(value = "{sortieId}/details")
    public String sortieDetails(@PathVariable("sortieId") Integer sortieId,Model model){
    Sortie laSortie= sortieManager.findById(sortieId);
    List<Commentaire> commentaireList=commentaireManager.sortieCommentairesList(sortieId);
    model.addAttribute("commentaireList",commentaireList);
    model.addAttribute("laSortie",laSortie);


        return "detail-sortie";
    }

    @CrossOrigin("*")
    @GetMapping(value = "kml/{filename}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody byte[] getFile(@PathVariable("filename") String filename) throws IOException {
        FileInputStream in = new FileInputStream("upload-dir/"+filename);
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value = "{sortieId}/joinSortie")
    public String getSortie(@PathVariable("sortieId") Integer sortieId,Model model){
        Sortie laSortie=sortieManager.findById(sortieId);
        model.addAttribute("laSortie",laSortie);
        return "join-sortie";
    }

    @RequestMapping(value = "saveParticipant")
    public String joinSortie(@ModelAttribute Sortie laSortie,BindingResult result,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Participant participant=new Participant();
        participant.setStatut("en attente");
        participant.setSortie(laSortie);
        participant.setUtilisateur(loggedUser);
        brules.checkSortieStatut(laSortie.getSortieId());
        participantManager.saveParticipant(participant);
        return "redirect:/sorties/"+laSortie.getSortieId()+"/details";
    }

    @RequestMapping(value = "userSorties")
    public String userSorties(Model model,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        List<Participant> userParticipant=participantManager.findByUser(loggedUser);
        model.addAttribute("userParticipant",userParticipant);
        return "user-sorties";
    }

    @RequestMapping(value = "organisateurSorties")
    public String organisateurSorties(Model model,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        List<Sortie> organisateurSorties=sortieManager.organisateurSorties(loggedUser);
        model.addAttribute("organisateurSorties",organisateurSorties);
        return "organisateur-sorties";
    }

    @RequestMapping("deleteParticipant")
    public String deleteParticipant(@RequestParam("participantId") Integer participantId,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        brules.checkUserParticipant(loggedUser,participantId);
        brules.checkSortieStatut(participantManager.findById(participantId).getSortie().getSortieId());
        participantManager.deleteParticipant(participantId);
        return "redirect:/sorties/userSorties";
    }

    @RequestMapping("confirmParticipant")
    public String confirmParticipant(@RequestParam("participantId") Integer participantId,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Participant leParticipant=participantManager.findById(participantId);
        leParticipant.setStatut("Confirme");
        brules.checkUserParticipant(loggedUser,participantId);
        participantManager.saveParticipant(leParticipant);
        return "redirect:/sorties/userSorties";
    }

    @RequestMapping("annuleSortie")
    public String annuleSortie(@RequestParam("sortieId") Integer sortieId,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Sortie laSortie=sortieManager.findById(sortieId);
        laSortie.setStatut("Annule");
        brules.checkUserOrganisateur(loggedUser,session,sortieId);
        sortieManager.saveSortie(laSortie);
        return "redirect:/sorties/"+laSortie.getSortieId()+"/details";

    }

    @RequestMapping("confirmSortie")
    public String confirmSortie(@RequestParam("sortieId") Integer sortieId,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Sortie laSortie=sortieManager.findById(sortieId);
        laSortie.setStatut("Confirme");
        brules.checkUserOrganisateur(loggedUser,session,sortieId);
        sortieManager.saveSortie(laSortie);
        return "redirect:/sorties/"+laSortie.getSortieId()+"/details";
    }

    @RequestMapping("termineSortie")
    public String termineSortie(@RequestParam("sortieId") Integer sortieId,HttpServletRequest request, HttpSession session){
        Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
        Sortie laSortie=sortieManager.findById(sortieId);
        laSortie.setStatut("Termine");
        brules.checkUserOrganisateur(loggedUser,session,sortieId);
        sortieManager.saveSortie(laSortie);
        return "redirect:/sorties/"+laSortie.getSortieId()+"/details";
    }

    @RequestMapping("searchByCriteria")
    public String search(@RequestParam(value = "statut", required = false) String statut,@RequestParam(value = "horspiste", required = false) Boolean horspiste,
                         @RequestParam(value = "niveau", required = false) String niveau,
                         @RequestParam(value = "duree", required = false) Integer duree,Model model){
        List<Sortie> sortieList=sortieManager.searchSorties(duree,statut,horspiste,niveau);
        model.addAttribute("sortiesFound",sortieList);
        return "liste-sorties";

    }

}

