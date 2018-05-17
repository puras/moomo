package me.puras.moomo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
public class Demo {
    static DataSource ds = null;
    static Connection conn = null;

    public static void main(String[] args) throws Exception {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://10.211.55.3:3306");
        config.setUsername("root");
        config.setPassword("123456");

        try {
            ds = new HikariDataSource(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        DatabaseMetaData dmd = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            conn = DriverManager.getConnection("jdbc:mysql://10.211.55.3:3306", "root", "123456");
            conn = ds.getConnection();
//            stmt = conn.createStatement();
            dmd = conn.getMetaData();
            rs = dmd.getCatalogs();
//            rs = stmt.executeQuery("SHOW DATABASES ");
            log.info("数据库列表");
            while (rs.next()) {
                log.info(rs.getString("TABLE_CAT"));
            }

            rs = dmd.getTables("moomo", null, "%", null);
            log.info("Moomo库下Table列表");
            while (rs.next()) {
                log.info(rs.getString(3));
            }

//            stmt.executeQuery("USE MOOMO");
//
//            rs = stmt.executeQuery("SHOW TABLES");
//            while (rs.next()) {
//                log.info(rs.getString(0));
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    rs = null;
                }
            }

            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    stmt = null;
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    conn = null;
                }
            }
        }

        config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://10.211.55.3:3306");
        config.setUsername("puras");
        config.setPassword("123456");

        try {
            ds = new HikariDataSource(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            conn = DriverManager.getConnection("jdbc:mysql://10.211.55.3:3306", "root", "123456");
            conn = ds.getConnection();
            dmd = conn.getMetaData();
            rs = dmd.getCatalogs();
//            rs = stmt.executeQuery("SHOW DATABASES ");
            log.info("数据库列表");
            while (rs.next()) {
                log.info(rs.getString("TABLE_CAT"));
            }

            rs = dmd.getTables("moomo", null, "%", null);
            log.info("Moomo库下Table列表");
            while (rs.next()) {
                log.info(rs.getString(3));
            }
            conn.setCatalog("moomo");
            stmt = conn.createStatement();

            stmt.executeQuery("USE moomo");
//
            rs = stmt.executeQuery("select * from mo_app_tab");
            while (rs.next()) {
                log.info("{}-{}", rs.getString("id"), rs.getString("name"));
//                log.info("{}-{}", rs.getString(0), rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    rs = null;
                }
            }

            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    stmt = null;
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    conn = null;
                }
            }
        }
    }
}
