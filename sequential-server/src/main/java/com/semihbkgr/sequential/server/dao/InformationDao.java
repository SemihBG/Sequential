package com.semihbkgr.sequential.server.dao;

import com.semihbkgr.sequential.server.entity.Information;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformationDao extends CrudRepository<Information, Integer> {

    public List<Information> findAll();

    public Information findByListName(String listName);

}
