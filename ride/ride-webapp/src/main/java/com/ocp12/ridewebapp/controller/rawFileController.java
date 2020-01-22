package com.ocp12.ridewebapp.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin("*")


public class rawFileController {
    @GetMapping(value = "kml/{filename}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    byte[] getFile(@PathVariable("filename") String filename) throws IOException {
        FileInputStream in = new FileInputStream("upload-dir/" + filename);
        return IOUtils.toByteArray(in);
    }
}
