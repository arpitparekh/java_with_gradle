package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPooling {

  public static void main(String[] args) {


    /*
     * Connection pooling in Java is a technique used to improve the performance of
     * database-driven applications. Instead of creating a new connection every time
     * the application needs to interact with the database, a pool of connections is
     * created and managed by a connection pool manager. This eliminates the
     * overhead of establishing a new connection each time, resulting in faster
     * response times and better resource utilization.
     *
     */

    HikariConfig cofig = new HikariConfig();
    cofig.setJdbcUrl("jdbc:mysql://localhost/bascom");
    cofig.setUsername("root");
    cofig.setPassword("Walden0042$$");
    cofig.setMaximumPoolSize(10);
    cofig.setMinimumIdle(5);
    cofig.setIdleTimeout(60000);
    cofig.setConnectionTimeout(30000);
    cofig.setMaxLifetime(1800000);

    HikariDataSource dataSource = new HikariDataSource(cofig);
    // System.out.println(dataSource.getHikariPoolMXBean().getActiveConnections());

    try {
      Connection conn = dataSource.getConnection();
      // System.out.println(dataSource.getHikariPoolMXBean().getActiveConnections());
      conn.close();


    } catch (SQLException e) {
      
      System.out.println(e);
    }



  }

}
