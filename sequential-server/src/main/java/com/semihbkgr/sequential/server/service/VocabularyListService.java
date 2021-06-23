package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.VocabularyList;
import com.semihbkgr.sequential.server.entity.projection.VocabularyListInfo;

import java.util.List;

public interface VocabularyListService {

    List<VocabularyListInfo> findAllInfo();

    VocabularyList findByName(String listName);

}
