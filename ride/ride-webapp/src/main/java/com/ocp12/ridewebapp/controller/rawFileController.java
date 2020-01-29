package com.ocp12.ridewebapp.controller;

import com.ocp12.ridebusiness.PicNameSortieManager;
import com.ocp12.ridemodele.Picnamessortie;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



@RestController
@CrossOrigin("*")


public class rawFileController {
    @Autowired
    private PicNameSortieManager picNameSortieManager;


    @GetMapping(value = "kml/{filename}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    byte[] getFile(@PathVariable("filename") String filename) throws IOException {
        FileInputStream in = new FileInputStream("upload-dir/" + filename);
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "pictures/{picId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("picId") Integer picId) throws IOException {

             Picnamessortie picnamessortie = picNameSortieManager.findById(picId);

            String folder = "upload-dir/pics/";
            String picName = picnamessortie.getFilename();
            InputStream in = new FileInputStream(folder + picName);




    return IOUtils.toByteArray(in);
    }
}
