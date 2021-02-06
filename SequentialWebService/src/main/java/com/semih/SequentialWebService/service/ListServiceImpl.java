package com.semih.SequentialWebService.service;

import com.semih.SequentialWebService.dao.ListDao;
import com.semih.SequentialWebService.entity.Vocabulary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListDao listDao;

    @Override
    public List<Vocabulary> findAll(String listName) {
        return listDao.findAll(listName);
    }

    @Override
    public void save(String listName, Vocabulary vocabulary) {
        listDao.add(listName,vocabulary);
        log.info("vocabulary saved, id = "+vocabulary.getId()+
                " , eng = "+vocabulary.getEng()+
                " , tr = "+vocabulary.getTr());
    }

}
