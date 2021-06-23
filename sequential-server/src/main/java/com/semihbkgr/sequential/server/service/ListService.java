package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.Vocabulary;

import java.util.List;

public interface ListService {

    List<Vocabulary> findAll(String listName);

    void save(String listName, Vocabulary vocabulary);

}
