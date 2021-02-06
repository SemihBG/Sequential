package com.semih.SequentialWebService.service;

import com.semih.SequentialWebService.entity.Vocabulary;

import java.util.List;

public interface ListService {

    public List<Vocabulary> findAll(String listName);
    public void save(String listName, Vocabulary vocabulary);

}
