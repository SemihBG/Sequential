package com.semihbkgr.sequential.server.controller;

import com.semihbkgr.sequential.server.entity.Information;
import com.semihbkgr.sequential.server.exception.NonexistentTableException;
import com.semihbkgr.sequential.server.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/information")
@RequiredArgsConstructor
public class InformationController {

    private final InformationService informationService;

    @GetMapping
    public List<Information> getAllInformation(){
        return informationService.findAll();
    }

    @GetMapping("/{listName}")
    public Information getInformation(@PathVariable("listName")String listName){
        Information information=informationService.findByListName(listName);
        if(information==null){
            throw new NonexistentTableException("No such table exists, table name = "+listName);
        }
        return information;
    }

}
