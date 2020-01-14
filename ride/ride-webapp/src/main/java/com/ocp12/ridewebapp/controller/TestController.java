package com.ocp12.ridewebapp.controller;

import com.ocp12.rideconsumer.kmlparser.KmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
@Autowired
private KmlParser kmlParser;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String firstview(Model model){

        String s="hello";
        model.addAttribute("parameter",s);
        return "view";
    }
}
