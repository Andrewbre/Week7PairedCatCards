package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;

public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;

    }

    @RequestMapping(path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> listOfCardsInUserCollect(){
        return catCardDao.list();
    }


    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCatCardById(@PathVariable long id){
        if (catCardDao.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat Card Not Found");
        } else {
            return catCardDao.get(id);
        }
    }

    }


