package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
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



    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCard () {
        CatCard randomCard = new CatCard();
        CatPic randomPic = catPicService.getPic();
        CatFact randomFact = catFactService.getFact();
        randomCard.setImgUrl(randomPic.getFile());
        randomCard.setCatFact(randomFact.getText());
        return randomCard;
    }




    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public void saveCardToCollection(@Valid @RequestBody CatCard cardToSave) {
        if (!catCardDao.save(cardToSave)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No card found");
        }
        catCardDao.save(cardToSave);
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public void updateCard(@PathVariable("id") long id, @Valid @RequestBody CatCard updatedCard) {
        if (!catCardDao.update(id, updatedCard)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No card found");
        }
        catCardDao.update(id, updatedCard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable("id") long id) {
        if (!catCardDao.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No card found");
        }
        catCardDao.delete(id);
    }

}


