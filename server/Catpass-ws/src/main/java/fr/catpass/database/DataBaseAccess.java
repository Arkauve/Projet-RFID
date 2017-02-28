package fr.catpass.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
/**
 * Created by Jordan on 21/02/2017.
 */
public interface DataBaseAccess extends AutoCloseable
{
    String getDbDateFormat();

    ResultSet query(String query) throws SQLException;

    Map<String, String> findOneAsMap(String query) throws SQLException;

    ArrayList<Map<String, String>> findAllAsMap(String query) throws SQLException;

    boolean update(String query) throws SQLException;

    int insert(String query) throws SQLException;

}
