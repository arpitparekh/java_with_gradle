package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
  static Connection con = null;
  static Statement st = null;

  static Connection connectDatabase(String name) throws SQLException {

    System.out.println("Connecting to database " + name);
    con = DriverManager.getConnection("jdbc:mysql://localhost/" + name,
        "root", "Walden0042$$");

    System.out.println("Connected");
    st = con.createStatement();
    return con;
  }

  static void closeConnection() throws SQLException {
    con.close();
  }

  static void closeStatement() throws SQLException {
    st.close();
  }

  static void createTable() throws SQLException {

    st.execute("create table if not exists student(id int primary key auto_increment, name varchar(20))");

    System.out.println("Table created");

  }

  static void insertData(String name) throws SQLException {

    st.execute("insert into student(name) values('" + name + " ')");
    System.out.println("Data inserted");

  }

  static void readData() throws SQLException {

    ResultSet result = st.executeQuery("select * from student");
    while (result.next()) {
      System.out.println(result.getInt(1) + " " + result.getString(2));
    }

    result.close();

  }

}
