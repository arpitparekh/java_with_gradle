package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
  static Connection con = null;
  static Statement st = null;
  static PreparedStatement ps = null;

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

  static void closePrepareStatement() throws SQLException {
    ps.close();
  }

  static void createTable() throws SQLException {

    st.execute("create table if not exists student(id int primary key auto_increment, name varchar(20))");

    System.out.println("Table created");

  }

  static void insertData(String name) throws SQLException {

    st.execute("insert into student(name) values('" + name + " ')");
    System.out.println("Data inserted");

  }

  static void insertDataPrepareStatement(String[] name) throws SQLException {

    ps = con.prepareStatement("insert into student(name) values(?)");

    for (String data : name) { // demo demo1 demo2
      ps.setString(1, data);
      ps.addBatch();
    }

    ps.executeBatch();

  }

  static void readData() throws SQLException {

    ResultSet result = st.executeQuery("select * from student");
    while (result.next()) {
      System.out.println(result.getInt(1) + " " + result.getString(2));
    }

    result.close();

  }

  public static void updateData(int id, String name) throws SQLException {
    st.execute("update student set name = '" + name + "' where id = " + id);
    System.out.println("Data updated");
  }

  static void updateDataPrepareStatement(HashMap<Integer,String> data) throws SQLException {

    ps = con.prepareStatement("update student set name = ? where id = ?");

    // ps.setString(1, name);
    // ps.setInt(2, id);

    for(Map.Entry<Integer,String> i : data.entrySet()){
      ps.setString(1, i.getValue());
      ps.setInt(2, i.getKey());
      ps.addBatch();
    }

    ps.executeBatch();

  }

  public static void deleteData(int i) throws SQLException{
    st.execute("delete from student where id = " + i);
    System.out.println("Data deleted");
  }

}
