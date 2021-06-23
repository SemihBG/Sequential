package com.semihbkgr.sequential.server.dao;


import com.semihbkgr.sequential.server.entity.Vocabulary;

import java.util.List;

public interface ListDao {

    public List<Vocabulary> findAll(String listName);

    public void add(String listName, Vocabulary vocabulary);

}
