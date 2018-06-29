package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.DishRowMapper;
import com.sfu.cmpt470.pojo.Dish;

import java.sql.SQLException;
import java.util.ArrayList;

public class DishDAO extends BaseDAO {

    public DishDAO(DatabaseConnector connector) throws SQLException, ClassNotFoundException {
        super(connector);
    }

    public ArrayList<Dish> findDishesForRestaurant(long restaurantID) throws SQLException {
        _db.supplyQuery("SELECT dish_id, dish_name, description, restaurant_id, price, menu_flag \n" +
                "FROM dish\n" +
                "WHERE restaurant_id = ?");

        _db.setLong(restaurantID, 1);
        ArrayList<Dish> result = _db.queryList(new DishRowMapper());
        return result;
    }

    public ArrayList<Dish> findDishesForRestaurant(String restaurantName) throws SQLException {
        _db.supplyQuery("SELECT dish.dish_id, dish.dish_name, dish.description, dish.restaurant_name, dish.price, dish.menu_flag \n" +
                "FROM dish\n" +
                "WHERE dish.restaurant_name = ?");

        _db.setString(restaurantName, 1);
        ArrayList<Dish> result = _db.queryList(new DishRowMapper());
        return result;
    }
}
