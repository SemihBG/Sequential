package com.semih.SequentialWebService.service;

import com.semih.SequentialWebService.entity.Information;

import java.util.List;

public interface InformationService {

    public List<Information> findAll();
    public Information findByListName(String listName);
    public void save(Information information);
    public void delete(Information information);

}
