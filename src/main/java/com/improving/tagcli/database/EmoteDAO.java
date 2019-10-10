package com.improving.tagcli.database;

import com.improving.tagcli.models.Emote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


//Data Access Object for Emote. anytime we want to do stuff to Emote from database, we do it through this (with CRUD operations).
// SINGLE RESPONSIBILITY
@Component
public class EmoteDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(EmoteDAO.class);

    public EmoteDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //Create
    public boolean insertEmote(Emote emote) { //boolean cuz want to know if insert successful or not
            try {
                int rowsAffected = jdbcTemplate.update(
                        "INSERT INTO emote (Prompt, Text) VALUES (?, ?)", emote.getPrompt(), emote.getText());
                logger.info("Rows Affected: {} ", rowsAffected);
                return true;
            } catch (DataAccessException e) {
                logger.error("Exception thrown in JDBC: ", e);
                System.out.println("Failure.");
            }
            return false;
        }

    //Read
    public Emote findEmoteByName(String prompt) {
            try{
                List<Emote> emotes = jdbcTemplate.query("SELECT * FROM emote WHERE Prompt = '" + prompt + "'",
                        (result, rowNum) -> new Emote (result.getString("Prompt"), result.getString("Text")));

                emotes.forEach(emote -> logger.info("Emote Prompt: {}, Emote Text: {}", emote.getPrompt(), emote.getText()));
                return emotes.get(0);
            }
            catch (DataAccessException e) {
                logger.error("Error: ", e);
            }
            return null;
        }

    //Update
    public boolean updateEmote (Emote emote) {//boolean cuz want to know if update was successful or not
        throw new RuntimeException("Not implemented yet.");

    }

    //Delete
    public boolean deleteEmote(Emote emote) {
        throw new RuntimeException("Not implemented yet.");
    }


}
