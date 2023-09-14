package com.techelevator.dao;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PlayDateDto;
import com.techelevator.model.PlayDate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPlayDateDao implements PlayDateDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayDate> getPlayDates() {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                PlayDate playDate = mapRowToPet(results);
                playDates.add(playDate);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDates;

    }

    @Override
    public PlayDate getEventById(int eventId) {
        PlayDate playDate = null;
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates WHERE event_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if (results.next()) {
                playDate = mapRowToPet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDate;
    }

    @Override
    public PlayDate getEventByDate(LocalDate eventDate) {
        PlayDate playDate = null;
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates WHERE event_date = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, eventDate);
            if (rowSet.next()) {
                playDate = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDate;
    }

    @Override
    public PlayDate getEventByTime(String eventTime) {
        PlayDate playDate = null;
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates WHERE event_time = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, eventTime);
            if (rowSet.next()) {
                playDate = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDate;
    }

    @Override
    public PlayDate getEventByLocation(String eventLocation) {
        PlayDate playDate = null;
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates WHERE event_location = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, eventLocation);
            if (rowSet.next()) {
                playDate = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDate;
    }

    @Override
    public PlayDate getEventByOrganizer(String organizer) {
        PlayDate playDate = null;
        String sql = "SELECT event_id, event_date, event_time, event_location, organizer FROM play_dates WHERE organizer = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, organizer);
            if (results.next()) {
                playDate = mapRowToPet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playDate;
    }

    @Override
    public PlayDate scheduleEvent(PlayDateDto playDate) {
        PlayDate newPlayDate = new PlayDate();
        String insertPlayDateSql = "INSERT INTO play_dates (event_date, event_time, event_location, organizer) values (?, ?, ?, ?) RETURNING event_id";
        try {
            int newEventId = jdbcTemplate.queryForObject(insertPlayDateSql, int.class, playDate.getEventDate(), playDate.getEventTime(), playDate.getEventLocation(),playDate.getOrganizer());
            newPlayDate = getEventById(newEventId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPlayDate;
    }

    @Override
    public PlayDate updatedEvent(PlayDate playDate) {
        PlayDate updatedEvent = null;
        String sql = "UPDATE play_dates SET event_date = ?, event_time = ?, event_location = ?, organizer = ? WHERE event_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, playDate.getEventDate(), playDate.getEventTime(), playDate.getEventLocation(), playDate.getOrganizer());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedEvent = getEventById(playDate.getEventId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedEvent;
    }
    @Override
    public int deleteEventById(int eventId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM play_dates WHERE event_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private PlayDate mapRowToPet(SqlRowSet rs) {
        PlayDate playDate = new PlayDate();
        playDate.setEventId(rs.getInt("event_id"));
        playDate.setEventDate(rs.getDate("event_date").toLocalDate());
        playDate.setEventTime(rs.getString("event_time"));
        playDate.setEventLocation(rs.getString("event_location"));
        playDate.setOrganizer(rs.getString("organizer"));
        return playDate;
    }

}