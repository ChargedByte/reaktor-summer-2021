package dev.chargedbyte.wim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {
    private final Logger log = LoggerFactory.getLogger(AppController.class);

    @GetMapping({"/", ""})
    public String index() {
        //noinspection SpringMVCViewInspection
        return "/index.html";
    }
}
