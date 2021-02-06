package com.semih.SequentialWebService.dao;


import com.semih.SequentialWebService.entity.Vocabulary;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ListDao {

    public List<Vocabulary> findAll(String listName);
    public void add(String listName,Vocabulary vocabulary);

}
