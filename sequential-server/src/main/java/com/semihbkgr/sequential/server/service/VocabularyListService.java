package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.VocabularyList;

import java.util.List;

public interface VocabularyListService {

    List<VocabularyList> findAll();

    VocabularyList findByName(String listName);

}
