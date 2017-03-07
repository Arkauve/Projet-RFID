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
    public boolean update(String query) throws SQLException {
        statement = currentConnection.createStatement();
        int result = statement.executeUpdate(query);
        return result > 0;
    }

    @Override
    public int insert(String query) throws SQLException {
        statement = currentConnection.createStatement();
        statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next())
            return generatedKeys.getInt(1);
        else
            return 0;
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

        for (int i = 1; i <= rsmd.getColumnCount(); i++){
            result.put(rsmd.getColumnName(i), rs.getString(i));
        }

        return result;
    }
}
