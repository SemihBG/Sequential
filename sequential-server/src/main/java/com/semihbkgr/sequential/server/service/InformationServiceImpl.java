package com.semihbkgr.sequential.server.service;

import com.semihbkgr.sequential.server.dao.InformationDao;
import com.semihbkgr.sequential.server.entity.Information;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService{

    private final InformationDao informationDao;

    @Override
    public List<Information> findAll() {
        return informationDao.findAll();
    }

    @Override
    public Information findByListName(String listName) {
        return informationDao.findByListName(listName);
    }

    @Override
    public void save(Information information) {
        informationDao.save(information);
        log.info("information saved" +
                " , list_name = "+information.getListName()+
                " , list_size = "+information.getListSize());
    }

    @Override
    public void delete(Information information){
        informationDao.delete(information);
        log.info("information deleted, list_id = "+information.getListId()+
                " , list_name = "+information.getListName()+
                " , list_size = "+information.getListSize());
    }

}
