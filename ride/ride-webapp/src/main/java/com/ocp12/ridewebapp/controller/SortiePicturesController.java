package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.PicNameSortieManager;
import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.ridemodele.Picnamessortie;
import com.ocp12.ridemodele.Utilisateur;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("sorties")
@Controller
public class SortiePicturesController {
        @Autowired
        private PicNameSortieManager picNameSortieManager;

        @Autowired
        private Brules brules;

        public static final String uploadingDir = "/upload-dir/pics/";

        @RequestMapping("{sortieId}/uploadpics")
        public String uploading(@PathVariable("sortieId") Integer sortieId, Model model) {
            Picnamessortie picnamessortieId =new Picnamessortie();
            picnamessortieId.setSortieId(sortieId);
            model.addAttribute("picnamessortieId", picnamessortieId);
            return "upload-pics";
        }

        @RequestMapping(value = "savepictures", method = RequestMethod.POST)
        public String uploadingPost(@ModelAttribute Picnamessortie picnamessortieId, @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, HttpServletRequest request, HttpSession session) throws IOException {
            Utilisateur loggedUser=(Utilisateur)request.getSession().getAttribute("theUser");
            for(MultipartFile uploadedFile : uploadingFiles) {
                byte[] bytes = uploadedFile.getBytes();
                Path path = Paths.get("upload-dir/pics/" + uploadedFile.getOriginalFilename());
                Files.write(path, bytes);
                Picnamessortie picNamesSortie=new Picnamessortie();
                picNamesSortie.setSortieId(picnamessortieId.getSortieId());
                picNamesSortie.setFilename(uploadedFile.getOriginalFilename());
                brules.checkUserOrganisateur(loggedUser,session,picnamessortieId.getSortieId());
                brules.checkSortieStatut(picnamessortieId.getSortieId());
                picNameSortieManager.savePicNameSortie(picNamesSortie);

            }

            return "redirect:/sorties/"+ picnamessortieId.getSortieId()+"/showPictures";
        }


    @GetMapping(value = "{sortieId}/showPictures", produces = MediaType.IMAGE_JPEG_VALUE)
    public String getImageWithMediaType(@PathVariable("sortieId") Integer sortieId,Model model) throws IOException {

        List<Picnamessortie> picnamessortieList = picNameSortieManager.findBySortieId(sortieId);
        model.addAttribute("picsList",picnamessortieList);

        return "pictures-list";

    }

    }

