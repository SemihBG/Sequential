package com.semih.SequentialWebService.controller;
/*
import com.semih.SequentialWebService.entity.Information;
import com.semih.SequentialWebService.entity.Vocabulary;
import com.semih.SequentialWebService.exception.DuplicatedListNameException;
import com.semih.SequentialWebService.exception.NonexistentTableException;
import com.semih.SequentialWebService.exception.WrongAuthenticationCodeException;
import com.semih.SequentialWebService.service.InformationService;
import com.semih.SequentialWebService.service.ListService;
import com.semih.SequentialWebService.transaction.ListTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private ListService listService;

    @Autowired
    private ListTransaction listTransaction;

    @PostMapping("/{authenticationCode}/createList/{listName}")
    public void createList(@PathVariable("authenticationCode") String authenticationCode,
                           @RequestBody(required = true) List<Vocabulary> vocabularies,
                           @PathVariable("listName") String listName,
                           HttpServletResponse response) throws IOException {

        if(!controlAuthenticationCode(authenticationCode)){
            throw new WrongAuthenticationCodeException("Wrong authentication code, authentication code = "+authenticationCode);
        }

        if(null!=informationService.findByListName(listName)){
            throw new DuplicatedListNameException("List name duplicated, list name = "+listName);
        }

        listTransaction.createTable(listName);
        informationService.save(new Information(listName,vocabularies.size()));
        vocabularies.forEach((item)->{
            listService.save(listName,item);
        });

        response.sendRedirect("/information/"+listName);

    }

    @PostMapping("/{authenticationCode}/deleteList/{listName}")
    public void deleteList(@PathVariable("authenticationCode") String authenticationCode,
                           @PathVariable("listName") String listName,
                           HttpServletResponse response) throws IOException {

        if(!controlAuthenticationCode(authenticationCode)){
            throw new WrongAuthenticationCodeException("Wrong authentication code, authentication code = "+authenticationCode);
        }

        if(null==informationService.findByListName(listName)){
            throw new NonexistentTableException("No such table exists, table name = "+listName);
        }

        listTransaction.deleteTable(listName);
        informationService.delete(informationService.findByListName(listName));

        response.sendRedirect("/information");

    }

    //Control authenticationCode, return true if true
    private boolean controlAuthenticationCode(String authenticationCode){
        return true;
    }

}
*/