package org.example.repository;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.example.service.Validation;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Named("db")
@ApplicationScoped
public class DB {
    private Connection connection;

    @Getter
    @Setter
    private String test;

    public DB() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        connection = DriverManager.getConnection(
            "jdbc:postgresql://%s:%s/%s".formatted(
                dotenv.get("POSTGRES_HOST"),
                dotenv.get("POSTGRES_PORT"),
                dotenv.get("POSTGRES_DB")
            ),
            dotenv.get("POSTGRES_USER"),
            dotenv.get("POSTGRES_PASSWORD")
        );
    }
    public List<List<String>> getResultTable() throws SQLException {
        List<List<String>> table = new ArrayList<>();
        String statement = "SELECT * FROM result_table ORDER BY id DESC \n" +
                "LIMIT 11;";
        PreparedStatement ps = connection.prepareStatement(statement);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            List<String> column = new ArrayList<>();
            column.add(String.valueOf(resultSet.getBoolean("result")));
            column.add(String.valueOf(resultSet.getDouble("x")));
            column.add(String.valueOf(resultSet.getDouble("y")));
            column.add(String.valueOf(resultSet.getDouble("z")));
            column.add(resultSet.getString("work_time"));
            column.add(resultSet.getString("time"));

            table.add(column);


        }

        return table;
    }

    public void addResult(Double x, Double y, Double r, long timeStart) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO result_table " +
                "(result, x, y, z, work_time, time)" +
                "VALUES (?, ?, ?, ?, ?, ?)");
        connection.setAutoCommit(false);

        ps.setBoolean(1, Validation.isHit(x, y, r));
        ps.setDouble(2, x);
        ps.setDouble(3, y);
        ps.setDouble(4, r);
        ps.setString(5, String.valueOf(System.currentTimeMillis() - timeStart));
        ps.setString(6, LocalDateTime.now().toString().split("\\.")[0]);


        ps.executeUpdate();

        connection.commit();
    }

}
