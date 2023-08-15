package com.cs;

import com.cs.entity.Ball;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Write {

    int[] nums;

    public Write(int[] nums) {
        this.nums = nums;
    }

    public synchronized void save() {
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);

            //插入球值
            insert(connection);
            //更新概率
            update(connection);

            // 关闭资源
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(Connection coon) {
        try {   // 要执行的插入语句
            String insertQuery = "";
            String uid = UUID.randomUUID().toString();
            for (int i = 1; i < 8; i++) {
                insertQuery = "INSERT INTO cs_o_" + i + " (id, num) VALUES ('" + uid + "', '" + nums[i - 1] + "');";

                // 创建PreparedStatement对象
                PreparedStatement statement = coon.prepareStatement(insertQuery);

                // 执行插入操作
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("数据插入成功！");
                } else {
                    System.out.println("数据插入失败！");
                }
                // 关闭资源
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(Connection coon) {
        Query Q = new Query();
        Possible P = new Possible();
        Map<String, List<Ball>> rb = P.CacuPoss(Q.queryRB(), nums);
        List<Ball> red = rb.get("red");
        List<Ball> blue = rb.get("blue");
        try {   // 要执行的插入语句
            String updateQuery = "";
            for (Ball b : red) {
                updateQuery = "UPDATE cs_prob_red "
                        + "SET cs_poss = " + b.getCsPoss() + ", cs_count = " + b.getCsCount() + ", cs_ratio = " + b.getCsRatio()
                        + " WHERE cs_num = " + b.getCsNum();

                // 创建PreparedStatement对象
                PreparedStatement statement = coon.prepareStatement(updateQuery);

                // 执行更新操作
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("数据插入成功！");
                } else {
                    System.out.println("数据插入失败！");
                }
                // 关闭资源
                statement.close();
            }
            for (Ball b : blue) {
                updateQuery = "UPDATE cs_prob_blue "
                        + "SET cs_poss = " + b.getCsPoss() + ", cs_count = " + b.getCsCount() + ", cs_ratio = " + b.getCsRatio()
                        + " WHERE cs_num = " + b.getCsNum();

                // 创建PreparedStatement对象
                PreparedStatement statement = coon.prepareStatement(updateQuery);

                // 执行更新操作
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("数据插入成功！");
                } else {
                    System.out.println("数据插入失败！");
                }
                // 关闭资源
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
