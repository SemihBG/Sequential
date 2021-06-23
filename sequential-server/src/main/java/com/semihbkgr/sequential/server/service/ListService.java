package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.Vocabulary;

import java.util.List;

public interface ListService {

    public List<Vocabulary> findAll(String listName);
    public void save(String listName, Vocabulary vocabulary);

}
