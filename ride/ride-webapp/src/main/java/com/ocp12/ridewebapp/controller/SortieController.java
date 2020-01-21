package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.rideconsumer.dto.SortieKmlDto;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RequestMapping("/sorties")
@Controller
public class SortieController {
    private static String UPLOAD_FOLDER = "upload-dir/";


    @Autowired
    private SortieManager sortieManager;



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
        laSortie.setNbrEtapes(sortieKmlDto.getNbrEtapes());
        laSortie.setNiveau(sortieKmlDto.getNiveau());
        laSortie.setNom(sortieKmlDto.getNom());
        laSortie.setNbrParticipants(sortieKmlDto.getNbrParticipants());
        sortieManager.saveSortie(laSortie);
        MultipartFile file= sortieKmlDto.getFile();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
    model.addAttribute("laSortie",laSortie);
        File kmlFolder=new File("upload-dir/"+sortieId);
        if (kmlFolder.exists()) {
            File[] kmlFound=kmlFolder.listFiles(new FilenameFilter() {
                public boolean accept(File kmlFolder, String filename)
                { return filename.endsWith(".kml"); }
            } );
          System.out.println(kmlFound[0].getAbsolutePath());
        model.addAttribute("kmlPath",kmlFound[0].getAbsolutePath());
        }

        return "detail-sortie";
    }
}
