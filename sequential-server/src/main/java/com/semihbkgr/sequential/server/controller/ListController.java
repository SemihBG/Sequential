package com.semihbkgr.sequential.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semihbkgr.sequential.server.entity.Vocabulary;
import com.semihbkgr.sequential.server.exception.NonexistentTableException;
import com.semihbkgr.sequential.server.service.InformationService;
import com.semihbkgr.sequential.server.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{list_name}")
    public List<Vocabulary> gelAll(@PathVariable("list_name") String listName) throws JsonProcessingException {
        if(null==informationService.findByListName(listName)){
            throw new NonexistentTableException("No such table exists, table name = "+listName);
        }
        return listService.findAll(listName);
    }



}
