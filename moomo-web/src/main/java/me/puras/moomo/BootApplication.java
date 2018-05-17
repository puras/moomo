package me.puras.moomo;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

@SpringBootApplication
@Slf4j
public class BootApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        @Cleanup Connection conn = jdbcTemplate.getDataSource().getConnection();
        DatabaseMetaData dmd = conn.getMetaData();
        @Cleanup ResultSet rs = dmd.getCatalogs();
        while (rs.next()) {
            log.info(rs.getString("TABLE_CAT"));
        }
    }
}
