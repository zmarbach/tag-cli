package com.improving.tagcli.database;

import com.improving.tagcli.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
    public class ItemDAO {
        private final JdbcTemplate jdbcTemplate;
        private static final Logger logger = LoggerFactory.getLogger(EmoteDAO.class);

        public ItemDAO(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        //Create
        public boolean insertItem(Item item) {
            try {
                int rowsAffected = jdbcTemplate.update(
                        "INSERT INTO item (Name, Weight, Value, Burnable, MagicQuality) VALUES (?, ?, ?, ?, ?)",  item.getName(), item.getWeight(), item.getValue(), item.isBurnable(), item.getMagicQuality());

                logger.info("Rows Affected: {} ", rowsAffected);
                return true;
            } catch (DataAccessException e) {
                logger.error("Exception thrown in JDBC: ", e);
                System.out.println("Failure.");
            }
            return false;
        }

        //Read
        public Item findItemByName(String itemName) {
            try {
                List<Item> items = jdbcTemplate.query("SELECT * FROM item WHERE Name = '" + itemName + "'",
                        (result, rowNum) -> new Item(result.getString("Name"),
                                result.getInt("Weight"),
                                result.getInt("Value"),
                                result.getBoolean("Burnable"),
                                result.getString("MagicQuality")));

                items.forEach(item -> logger.info("Item Name: {}, Item Weight: {}, Item Value: {}, Item Burnable: {}, Item MagicQuality: {}",
                        item.getName(),item.getWeight(), item.getValue(), item.isBurnable(), item.getMagicQuality()));
                return items.get(0);
            } catch (DataAccessException e) {
                logger.error("Error: ", e);
            }
            return null;
        }

        //Update
        public boolean updateItem(Item item) {//boolean cuz want to know if update was successful or not
            throw new RuntimeException("Not implemented yet.");

        }

        //Delete
        public boolean deleteItem(Item item) {
            throw new RuntimeException("Not implemented yet.");
        }
}