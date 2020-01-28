package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.PicNameSortieManager;
import com.ocp12.ridemodele.PicNamesSortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping("sorties")
@Controller
public class PicUploadController {
        @Autowired
        private PicNameSortieManager picNameSortieManager;

        public static final String uploadingDir = "/upload-dir/pics/";

        @RequestMapping("{sortieId}/uploadpics")
        public String uploading(@PathVariable("sortieId") Integer sortieId, Model model) {
            PicNamesSortie picNamesSortieId=new PicNamesSortie();
            picNamesSortieId.setSortieId(sortieId);
            model.addAttribute("picNamesSortieId",picNamesSortieId);
            return "upload-pics";
        }

        @RequestMapping(value = "savepictures", method = RequestMethod.POST)
        public String uploadingPost(@ModelAttribute PicNamesSortie picNamesSortieId, @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
            for(MultipartFile uploadedFile : uploadingFiles) {
                byte[] bytes = uploadedFile.getBytes();
                Path path = Paths.get("upload-dir/pics/" + uploadedFile.getOriginalFilename());
                Files.write(path, bytes);
                PicNamesSortie picNamesSortie=new PicNamesSortie();
                picNamesSortie.setSortieId(picNamesSortieId.getSortieId());
                picNamesSortie.setFilename(uploadedFile.getOriginalFilename());
                picNameSortieManager.savePicNameSortie(picNamesSortie);

            }

            return "redirect:/sorties/"+picNamesSortieId.getSortieId()+"/details";
        }
    }

