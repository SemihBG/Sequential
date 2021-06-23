package com.semihbkgr.sequential.server.repository;

import com.semihbkgr.sequential.server.entity.VocabularyList;
import com.semihbkgr.sequential.server.entity.projection.VocabularyListInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VocabularyListRepository extends CrudRepository<VocabularyList, Integer> {

    List<VocabularyListInfo> findAllBy();

    VocabularyList findByName(String name);

}
