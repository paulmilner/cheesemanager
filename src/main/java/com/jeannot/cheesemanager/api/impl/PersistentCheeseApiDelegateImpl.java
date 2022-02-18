package com.jeannot.cheesemanager.api.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jeannot.cheesemanager.openapi.api.CheeseApiDelegate;
import com.jeannot.cheesemanager.openapi.model.Cheese;
import com.jeannot.cheesemanager.persistence.CheeseDao;

@Service
public class PersistentCheeseApiDelegateImpl implements CheeseApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentCheeseApiDelegateImpl.class);

    @Autowired
    Environment env;

    @Autowired
    CheeseDao cheeseDao;

    /**
     * @inheritDoc
     */
    public ResponseEntity<Cheese> addCheese(Cheese body) {
        LOGGER.info("addCheese called");
        Cheese created = cheeseDao.create(body);
        return new ResponseEntity<Cheese>(created, HttpStatus.CREATED);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public ResponseEntity<Cheese> getCheese(String id) {

        LOGGER.info("getCheese called for id={}", id);

        Cheese cheese = cheeseDao.get(id);
        
        if(cheese != null) {
            return new ResponseEntity<>(cheese, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @inheritDoc
     */
    public ResponseEntity<List<Cheese>> getCheeses() {
        LOGGER.info("getCheeses called");

        List<Cheese> cheeses = cheeseDao.getAll();
        
        if(cheeses != null) {
            return new ResponseEntity<>(cheeses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @inheritDoc
     */
    public ResponseEntity<Cheese> updateCheese(String id,Cheese cheese) {
    	//TODO doesn't yet support verifying update and avoiding a concurrent change
        LOGGER.info("updateCheese called for id=" + id);
        Cheese updated = cheeseDao.update(id,cheese);
        return new ResponseEntity<Cheese>(updated, HttpStatus.OK);
    }
    
    /**
     * @inheritDoc
     */
    public ResponseEntity<Void> deleteCheese(String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
    
    /**
     * @inheritDoc
     */
    public ResponseEntity<List<Cheese>> deleteCheeses() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    
}
