package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterPetDto;
import com.techelevator.model.Pet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class JdbcPetDao implements PetDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> getPets() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Pet pet = mapRowToPet(results);
                pets.add(pet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pets;

    }

    @Override
    public Pet getPetById(int petId) {
        Pet pet = null;
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets WHERE pet_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
            if (results.next()) {
                pet = mapRowToPet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }

    @Override
    public Pet getPetByName(String petName) {
        if (petName == null) throw new IllegalArgumentException("Pet name cannot be null");
        Pet pet = null;
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets WHERE pet_name = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, petName);
            if (rowSet.next()) {
                pet = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }

    @Override
    public Pet getPetByLocation(int petLocation) {
//        if (petLocation == null) throw new IllegalArgumentException("Pet location cannot be null");
        Pet pet = null;
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets WHERE pet_location = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, petLocation);
            if (rowSet.next()) {
                pet = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }
    @Override
    public Pet getPetByPersonality (String petPersonality) {
        Pet pet = null;
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets WHERE pet_personality = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, petPersonality);
            if (rowSet.next()) {
                pet = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }
    @Override
    public Pet getPetByBreed (String petBreed) {
        Pet pet = null;
        String sql = "SELECT pet_id, pet_name, pet_breed, pet_personality, pet_location FROM pets WHERE pet_breed = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, petBreed);
            if (rowSet.next()) {
                pet = mapRowToPet(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pet;
    }

    @Override
    public Pet registerPet(RegisterPetDto pet) {
        Pet newPet = new Pet();
        String insertPetSql = "INSERT INTO pets (pet_name, pet_breed, pet_personality, pet_location) values (?, ?, ?, ?) RETURNING pet_id";
        try {
            int newPetId = jdbcTemplate.queryForObject(insertPetSql, int.class, pet.getPetName(), pet.getPetBreed(), pet.getPetPersonality(), pet.getPetLocation());
            newPet = getPetById(newPetId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPet;
    }


    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setPetId(rs.getInt("pet_id"));
        pet.setPetName(rs.getString("pet_name"));
        pet.setPetBreed(rs.getString("pet_breed"));
        pet.setPetPersonality(rs.getString("pet_personality"));
        pet.setPetLocation(rs.getInt("pet_location"));
        return pet;
    }
}
