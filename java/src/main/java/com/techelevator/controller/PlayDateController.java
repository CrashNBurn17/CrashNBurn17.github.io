package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.RestTemplate;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin

public class PlayDateController {
    private final RestTemplate restTemplate = new RestTemplate();
    private PlayDateDao playDateDao;
    private PetDao petDao;

    public PlayDateController(PlayDateDao playDateDao, PetDao petDao) {
        this.playDateDao = playDateDao;
        this.petDao = petDao;
    }
    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public List<PlayDate> listPlayDates() {
        return playDateDao.getPlayDates();
    }

    @RequestMapping(path = "/schedule/{id}", method = RequestMethod.GET)
    public PlayDate getEventById (@PathVariable int eventId) {
        PlayDate playDate = playDateDao.getEventById(eventId);
        if (playDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Play Date not found.");
        } else {
            return playDate;
        }
    }
    @RequestMapping(path = "/schedule/{eventDate}", method = RequestMethod.GET)
    public PlayDate getEventByDate (@PathVariable LocalDate eventDate) {
        PlayDate playDate = playDateDao.getEventByDate(eventDate);
        if (playDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Play Date not found.");
        } else {
            return playDate;
        }
    }

    @RequestMapping(path = "/schedule/{eventTime}", method = RequestMethod.GET)
    public PlayDate getEventByTime (@PathVariable String eventTime) {
        PlayDate playDate = playDateDao.getEventByTime(eventTime);
        if (playDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Play Date not found.");
        } else {
            return playDate;
        }
    }

    @RequestMapping(path = "/schedule/{eventLocation}", method = RequestMethod.GET)
    public PlayDate getEventByLocation (@PathVariable String eventLocation) {
        PlayDate playDate = playDateDao.getEventByLocation(eventLocation);
        if (playDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Play Date Location not found.");
        } else {
            return playDate;
        }
    }
    @RequestMapping(path = "/schedule/{organizer}", method = RequestMethod.GET)
    public PlayDate getEventByOrganizer (@PathVariable String organizer) {
        PlayDate playDate = playDateDao.getEventByOrganizer(organizer);
        if (playDate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found.");
        } else {
            return playDate;
        }
    }
    //*******************INSERT CALENDAR API********************************
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/schedule", method = RequestMethod.POST)
    public PlayDate scheduleEvent(@RequestBody PlayDateDto newPlayDate) {
        return playDateDao.scheduleEvent(newPlayDate);
    }
    @RequestMapping(path = "/schedule/{id}", method = RequestMethod.PUT)
    public PlayDate update(@Valid @RequestBody PlayDate playDate, @PathVariable int eventId) {
        playDate.setEventId(eventId);
        try {
            PlayDate updatedPlayDate = playDateDao.updatedEvent(playDate);
            return updatedPlayDate;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/schedule/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int eventId) {
        playDateDao.deleteEventById(eventId);
    }
    //PET PORTION
    @RequestMapping(path = "/pet", method = RequestMethod.GET)
    public List<Pet> getPets() {
        return petDao.getPets();
    }

    @RequestMapping(path = "/pet/{petName}", method = RequestMethod.GET)
    public Pet getPetByName (@PathVariable String petName) {
        Pet pet = petDao.getPetByName(petName);
        if (pet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet Name not found.");
        } else {
            return pet;
        }
    }
    @RequestMapping(path = "/pet/{id}", method = RequestMethod.GET)
    public Pet getPetById (@PathVariable int petId) {
        Pet pet = petDao.getPetById(petId);
        if (pet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found.");
        } else {
            return pet;
        }
    }

    @RequestMapping(path = "/pet/{petLocation}", method = RequestMethod.GET)
    public Pet getPetByLocation (@PathVariable int petLocation) {
        Pet pet = petDao.getPetByLocation(petLocation);
        if (pet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet location not found.");
        } else {
            return pet;
        }
    }
    @RequestMapping(path = "/pet/{petByPersonality}", method = RequestMethod.GET)
    public Pet getPetByPersonality (@PathVariable String petPersonality) {
        Pet pet = petDao.getPetByPersonality(petPersonality);
        if (pet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet personality not found.");
        } else {
            return pet;
        }
    }
    //*****************************INSERT BREED API*********************************************//
//    @RequestMapping(path = "/pet/petBreed", method = RequestMethod.GET)
//    public Pet[] getPetByBreed() {
//        Pet[] pets = null;
//        pets = restTemplate.getForObject("https://dog.ceo/api/breeds/list/all" + "/pet/petBreed", Pet[].class);
//        return pets;
//    }

}
