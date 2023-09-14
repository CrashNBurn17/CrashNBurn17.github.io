package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import com.techelevator.model.PlayDateDto;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public interface PlayDateDao {

    List<PlayDate> getPlayDates();

    PlayDate getEventById(int eventId);

    PlayDate getEventByDate(LocalDate eventDate);
    PlayDate getEventByTime(String eventTime);

    PlayDate getEventByLocation(String eventLocation);
    PlayDate getEventByOrganizer(String organizer);

    PlayDate scheduleEvent(PlayDateDto playDate);
    PlayDate updatedEvent(PlayDate playDate);
    int deleteEventById(int eventId);

}
