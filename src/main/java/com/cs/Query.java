package com.cs;

import com.cs.entity.Ball;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Query {

    public synchronized void qCount() {
        String sql = "SELECT COUNT(0) co FROM cs_o_1;";
        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);
             PreparedStatement statementR = connection.prepareStatement(sql);
             ResultSet resultSetR = statementR.executeQuery();
        ) {

            while (resultSetR.next()) {
                Float c = resultSetR.getFloat("co");
                Public.SUM = c + 1.0f;
            }
        } catch (SQLException e) {
            // Proper error handling, you can log the exception details or perform other actions here.
            e.printStackTrace();
        }
        System.out.println(Public.SUM);
    }

    public Map<String, List<Ball>> queryRB() {

        List<Ball> red = new ArrayList<>();
        List<Ball> blue = new ArrayList<>();
        Map<String, List<Ball>> res = new HashMap<>();

        String sqlr = "SELECT * FROM " + "cs_prob_red";
        String sqlb = "SELECT * FROM " + "cs_prob_blue";

        try (Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);
             PreparedStatement statementR = connection.prepareStatement(sqlr);
             ResultSet resultSetR = statementR.executeQuery();
             PreparedStatement statementB = connection.prepareStatement(sqlb);
             ResultSet resultSetB = statementB.executeQuery();
        ) {

            while (resultSetR.next()) {
                Ball b = new Ball();
                b.setCsNum(resultSetR.getInt("cs_num"));
                b.setCsPoss(resultSetR.getFloat("cs_poss"));
                b.setCsCount(resultSetR.getInt("cs_count"));
                b.setCsRatio(resultSetR.getFloat("cs_ratio"));
                red.add(b);
            }
            while (resultSetB.next()) {
                Ball b = new Ball();
                b.setCsNum(resultSetB.getInt("cs_num"));
                b.setCsPoss(resultSetB.getFloat("cs_poss"));
                b.setCsCount(resultSetB.getInt("cs_count"));
                b.setCsRatio(resultSetB.getFloat("cs_ratio"));
                blue.add(b);
            }
            res.put("red", red);
            res.put("blue", blue);
        } catch (SQLException e) {
            // Proper error handling, you can log the exception details or perform other actions here.
            e.printStackTrace();
        }

        this.display(res);
        return res;
    }

    public void display(Map<String, List<Ball>> rb) {
        List<Ball> red = rb.get("red");
        List<Ball> blue = rb.get("blue");

        System.out.println("===========Red===========");
        for (Ball b : red) {
            int num = b.getCsNum();
            float poss = b.getCsPoss();
            Integer count = b.getCsCount();
            float ratio = b.getCsRatio();
            System.out.println(num + "->" + poss + "->" + count + "->" + ratio);
        }
        System.out.println("===========Blue===========");
        for (Ball b : blue) {
            int num = b.getCsNum();
            float poss = b.getCsPoss();
            Integer count = b.getCsCount();
            float ratio = b.getCsRatio();
            System.out.println(num + "->" + poss + "->" + count + "->" + ratio);
        }
    }

}
