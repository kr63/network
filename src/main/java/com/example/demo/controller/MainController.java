package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.Views;
import com.example.demo.repo.MessageRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo messageRepo;
    private final ObjectWriter writer;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo, ObjectMapper mapper) {
        this.messageRepo = messageRepo;

        writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String main(Model model,
                       @AuthenticationPrincipal User user)
            throws JsonProcessingException {

        Map<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            String messages = writer.writeValueAsString(messageRepo.findAll());
            model.addAttribute("messages", messages);
        } else {
            model.addAttribute("messages" ,"[]");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
