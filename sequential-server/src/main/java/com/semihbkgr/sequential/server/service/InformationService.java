package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.entity.Information;

import java.util.List;

public interface InformationService {

    List<Information> findAll();

    Information findByListName(String listName);

    void save(Information information);

    void delete(Information information);

}
