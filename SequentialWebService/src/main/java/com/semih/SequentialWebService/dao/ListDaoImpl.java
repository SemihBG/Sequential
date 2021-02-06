package com.semih.SequentialWebService.dao;

import com.semih.SequentialWebService.entity.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ListDaoImpl implements ListDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vocabulary> findAll(String listName) {
        Query query=entityManager.createNativeQuery("SELECT * FROM "+listName,Vocabulary.class);
        return query.getResultList();
    }

    @Override
    public void add(String listName,Vocabulary vocabulary) {
        entityManager.createNativeQuery(
                "INSERT INTO "+listName+"(eng,tr) VALUES ('"+vocabulary.getEng()+"','"+vocabulary.getTr()+"')").
                executeUpdate();
    }

}
