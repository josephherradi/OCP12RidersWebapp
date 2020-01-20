package com.ocp12.ridewebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller

public class KmlUploadController2 {
    //destination folder to upload the files
    private static String UPLOAD_FOLDER = "upload-dir/";

    @RequestMapping("/uploadKml")
    public ModelAndView showUpload(@RequestParam("sortieId") Integer sortieId) {
        return new ModelAndView("upload","sortieId",sortieId);
    }

    @PostMapping("/uploadKml")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("sortieId") Integer sortieId, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            return new ModelAndView("status", "message", "Please select a file and try again");
        }

        try {
            File newFolder= new File("upload-dir/"+sortieId);
            newFolder.mkdir();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER+sortieId+"/" + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("status", "message", "File Uploaded sucessfully");
    }

}
