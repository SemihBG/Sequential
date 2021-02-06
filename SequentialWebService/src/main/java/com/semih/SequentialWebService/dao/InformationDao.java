package com.semih.SequentialWebService.dao;

import com.semih.SequentialWebService.entity.Information;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformationDao extends CrudRepository<Information,Integer> {

    public List<Information> findAll();
    public Information findByListName(String listName);

}
