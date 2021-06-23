package com.semihbkgr.sequential.server.repository;

import com.semihbkgr.sequential.server.entity.VocabularyList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VocabularyListRepository extends CrudRepository<VocabularyList, Integer> {

    List<VocabularyList> findAll();

    VocabularyList findByName(String name);

}
