package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.Information;

import java.util.List;

public interface InformationService {

    public List<Information> findAll();
    public Information findByListName(String listName);
    public void save(Information information);
    public void delete(Information information);

}
