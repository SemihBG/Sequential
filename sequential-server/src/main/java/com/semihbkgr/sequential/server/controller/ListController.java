package com.semihbkgr.sequential.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.semihbkgr.sequential.server.entity.Vocabulary;
import com.semihbkgr.sequential.server.exception.NonexistentTableException;
import com.semihbkgr.sequential.server.service.InformationService;
import com.semihbkgr.sequential.server.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;
    private final InformationService informationService;
    private final ObjectMapper objectMapper;

    @GetMapping("/{listName}")
    public List<Vocabulary> gelAll(@PathVariable("listName") String listName) {
        if (null == informationService.findByListName(listName))
            throw new NonexistentTableException("No such table exists, table name = " + listName);
        return listService.findAll(listName);
    }

}
