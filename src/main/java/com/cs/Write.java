package com.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class Write {

    public void save() {
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PWD);

            insert(connection, Public.NUMS);

            // 关闭资源
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(Connection coon, int[] nums) {
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
                    System.out.println("数据插入成功到表1！");
                } else {
                    System.out.println("数据插入失败到表1！");
                }
                // 关闭资源
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
