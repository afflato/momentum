package org.afflato.momentum.controller;

import org.afflato.momentum.model.LoadIndices;
import org.afflato.momentum.model.MomentumDto;
import org.afflato.momentum.model.SelectionDto;
import org.afflato.momentum.service.MomentumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class MomentumController {
    @Autowired
    MomentumService service;

    @GetMapping("/api/v2/")
    public String showForm(Model model) {
        LoadIndices data = service.init();
        model.addAttribute("indices", data.getIndices());
        model.addAttribute("selectionDto", data.getSelectionDto());
        return "index";
    }

    @PostMapping("/api/v2/")
    public String showMomentumList(@ModelAttribute SelectionDto selectionDto, Model model) {

        LoadIndices data = service.init();
        model.addAttribute("indices", data.getIndices());
        model.addAttribute("message", "You selected: " + selectionDto.getTrackIndex());
        model.addAttribute("selectedTab", selectionDto.getSelectedTab());


        MomentumDto response = service.process(selectionDto);
        model.addAttribute("momentum", response);

        return "index";
    }
}
