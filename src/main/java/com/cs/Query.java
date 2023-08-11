package com.cs;

import java.sql.*;
import java.util.*;

public class Query {

    public Map<Integer, Double> query(Boolean b) {
        Map<Integer, Double> blue = new HashMap<>();

        String table = b ? "cs_prob_red" : "cs_prob_blue";

        String sql = "SELECT * FROM " + table;

        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int num = resultSet.getInt("cs_num");
                double poss = resultSet.getDouble("cs_poss");
                blue.put(num, poss);
            }

        } catch (SQLException e) {
            // Proper error handling, you can log the exception details or perform other actions here.
            e.printStackTrace();
        }

        return blue;
    }


}
