package com.semih.SequentialWebService.controller;

import com.semih.SequentialWebService.entity.Information;
import com.semih.SequentialWebService.exception.NonexistentTableException;
import com.semih.SequentialWebService.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping
    public List<Information> getAllInformation(){
        return informationService.findAll();
    }

    @GetMapping("/{list_name}")
    public Information getInformation(@PathVariable("list_name")String listName){
        Information information=informationService.findByListName(listName);
        if(information==null){
            throw new NonexistentTableException("No such table exists, table name = "+listName);
        }
        return information;
    }

}
