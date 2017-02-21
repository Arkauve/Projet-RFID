package fr.catpass.database;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Jordan on 21/02/2017.
 */
public class DataBaseAccessImpl implements DataBaseAccess {

    private final static String DB_DATE_FORMAT
            = "yyyy-MM-dd HH:mm:ss";


    private Connection currentConnection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatment = null;
    private static DataBaseAccessImpl database;
    private ResultSet result = null;


    private DataBaseAccessImpl() {
        // Get informations for database with configuration file

        try {
            InitialContext ic = new InitialContext();
            DataSource databaseSource = (DataSource)ic.lookup("java:comp/env/jdbc/catpass");
            this.currentConnection = databaseSource.getConnection();

        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static synchronized DataBaseAccessImpl getDbConnection() {
        if(database == null) {
            database = new DataBaseAccessImpl();
        }

        return database;
    }

    @Override
    public String getDbDateFormat(){
        return DB_DATE_FORMAT;
    }


    @Override
    public ResultSet query(String query) throws SQLException {
        statement = currentConnection.createStatement();
        result = statement.executeQuery(query);
        return result;
    }

    @Override
    public Map<String, String> findOneAsMap(String query) throws SQLException {
        ResultSet resultSet = this.query(query);

        return toMap(resultSet);
    }

    @Override
    public ArrayList<Map<String, String>> findAllAsMap(String query) throws SQLException {
        ResultSet resultSet = this.query(query);

        return toArrayOfMap(resultSet);
    }

    @Override
    public ResultSet concurrentQuery(String query) throws SQLException {
        statement = currentConnection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        result = statement.executeQuery(query);
        return result;
    }


    @Override
    public boolean update(String query) throws SQLException {
        statement = currentConnection.createStatement();
        int result = statement.executeUpdate(query);
        return result > 0;
    }

    @Override
    public void trustedInsertFromMap(Map<String, String> data) throws SQLException {
        // TODO
    }

    @Override
    public boolean callUpdateProcedure(String query) throws SQLException {
        preparedStatment = currentConnection.prepareCall(query);
        return preparedStatment.execute();
    }

    @Override
    public Map<String, String> callSelectProcedureAsMap(String query) throws SQLException {
        preparedStatment = currentConnection.prepareCall(query);
        ResultSet rs = preparedStatment.executeQuery();
        return toMap(rs);
    }

    @Override
    public void close() throws Exception {

        if (result != null){ result.close(); result = null; }

        if (statement != null){ statement.close(); statement = null; }

        if (preparedStatment != null){ preparedStatment.close(); preparedStatment = null; }

        database = null;

        currentConnection.close();
    }

    private Map<String, String> toMap(ResultSet rs) throws SQLException {

        if (!rs.first()) return null;

        return openedResultToMap(rs);
    }


    private ArrayList<Map<String, String>> toArrayOfMap(ResultSet rs) throws SQLException {
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();

        while (rs.next()){
            result.add(openedResultToMap(rs));
        }

        return result;
    }

    private Map<String, String> openedResultToMap(ResultSet rs) throws SQLException {

        Map<String, String> result = new HashMap<String, String>();

        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 0; i < rsmd.getColumnCount(); i++){
            result.put(rsmd.getColumnName(i), rs.getString(i));
        }

        return result;
    }
}
