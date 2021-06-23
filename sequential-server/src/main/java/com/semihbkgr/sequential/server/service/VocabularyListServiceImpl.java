package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.projection.VocabularyListInfo;
import com.semihbkgr.sequential.server.repository.VocabularyListRepository;
import com.semihbkgr.sequential.server.entity.VocabularyList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyListServiceImpl implements VocabularyListService {

    private final VocabularyListRepository vocabularyListRepository;

    @Override
    public List<VocabularyListInfo> findAllInfo() {
        return vocabularyListRepository.findAllBy();
    }

    @Override
    public VocabularyList findByName(String listName) {
        return vocabularyListRepository.findByName(listName);
    }

}
