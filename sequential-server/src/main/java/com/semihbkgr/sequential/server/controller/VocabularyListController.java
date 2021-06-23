package com.semihbkgr.sequential.server.controller;

import com.semihbkgr.sequential.server.entity.VocabularyList;
import com.semihbkgr.sequential.server.entity.projection.VocabularyListInfo;
import com.semihbkgr.sequential.server.exception.NonexistentTableException;
import com.semihbkgr.sequential.server.service.VocabularyListService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class VocabularyListController {

    private final VocabularyListService vocabularyListService;

    @GetMapping("/{listName}")
    public VocabularyList gelListByNAme(@PathVariable("listName") String listName) {
        if (null == vocabularyListService.findByName(listName))
            throw new NonexistentTableException("No such table exists, table name = " + listName);
        return vocabularyListService.findByName(listName);
    }

    @GetMapping()
    public List<VocabularyListInfo> getAllLists(){
        return vocabularyListService.findAllInfo();
    }

}
