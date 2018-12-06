package com.galvanize.badgevisit.controller;


import com.galvanize.badgevisit.entity.Visit;
import com.galvanize.badgevisit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @GetMapping("/lookup")
    public Iterable<Visit> getAll() {
        return visitService.getAll();
    }

}
