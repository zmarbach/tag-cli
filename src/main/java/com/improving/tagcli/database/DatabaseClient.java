package com.improving.tagcli.database;


import com.improving.tagcli.models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseClient {

    @Autowired
    // FIELD INJECTION - using Autowired to tell Spring that I want it to
    // set the value for this (instead of passing it through in the constructor for DatabaseClient)
    // really should NOT do this, unless absolutely necessary, bad for testing cuz the field if PRIVATE, so you have to write a setter that will only be run for testing
    // JUST STICK TO NORMAL CONSTRUCTOR INJECTION;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseClient.class);
    private JdbcTemplate jdbcTemplate;

    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Weapon> readDataFromTable(){
        try{
            List<Weapon> weapons = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
                    (result, rowNum) ->
                            new Weapon(result.getInt("Id"),
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));

            weapons.forEach(weapon -> logger.info("Weapon ID: {}, Name: {}", weapon.getId(), weapon.getName()));
            return weapons;
        }
        catch (DataAccessException e) {
            logger.error("Error: ", e);
        }
        return Collections.EMPTY_LIST;
    }
    public void insertIntoTable() {
        try {
            logger.info("GOT IT!");
            int rowsAfftected = jdbcTemplate.update(
                    "INSERT INTO weapon (Name, Area, ItemType) VALUES ('SMALLISH DAGGER', 'Dagger Shop', 'Weapon')");
            logger.info("Rows Affected: {} ", rowsAfftected);
        } catch (DataAccessException e) {
            logger.error("Exception thrown in JDBC: ", e);
        }
    }
}
