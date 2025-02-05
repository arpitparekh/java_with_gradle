package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnect {

  public static void main(String[] args) {

    // database connection

    try {

      Connection con = DatabaseUtil.connectDatabase("fromjdbc");

      DatabaseUtil.createTable();
      // insert data
      // DatabaseUtil.insertData(con, "Kiran");
      // DatabaseUtil.insertData(con, "Sumit");
      // DatabaseUtil.insertData(con, "Pradip");

      DatabaseUtil.readData();

      DatabaseUtil.closeStatement();
      DatabaseUtil.closeConnection();

    } catch (SQLException e) {
      System.out.println(e);
    }

  }

}
