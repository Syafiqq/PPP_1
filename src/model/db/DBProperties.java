package model.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import model.pattern.observer.Observer;
import model.pattern.observer.Subject;

/**
 * This <PPP_1> project in package <model.db> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 7:12 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class DBProperties implements Subject
{
    private static DBProperties ourInstance = new DBProperties();

    private final String                                      PATH;
    private final String                                      STORAGE_PATH;
    private final String                                      DATABASE_EXTENSION;
    private final String                                      SEPARATOR;
    private final String                                      URL;
    public        String                                      dbName;
    public        Connection                                  connection;
    public        Statement                                   statement;
    public        PreparedStatement                           preparedStatement;
    public        ResultSet                                   result;
    public        boolean                                     isActive;
    private       LinkedHashMap<String, LinkedList<Observer>> observers;

    private DBProperties()
    {
        this.PATH = System.getProperty("user.dir");
        this.STORAGE_PATH = "storage";
        this.DATABASE_EXTENSION = ".mcrypt";
        this.SEPARATOR = File.separator;
        this.URL = "jdbc:sqlite:";
        this.isActive = false;
        this.observers = new LinkedHashMap<>();
    }

    public static DBProperties getInstance()
    {
        return ourInstance;
    }

    public void connect() throws SQLException
    {
        this.connection = DriverManager.getConnection(this.URL + this.PATH + this.SEPARATOR + this.STORAGE_PATH + this.SEPARATOR + this.dbName + this.DATABASE_EXTENSION);
        this.isActive = true;
        this.setUpDatabase();
        this.notifyObserver("databaseState");
        System.out.println("Connection to SQLite has been established.");
    }

    private void setUpDatabase() throws SQLException
    {
        this.createUserTable();
        this.createRekamMedisTable();
    }

    private void createRekamMedisTable() throws SQLException
    {
        String query = "" +
                "CREATE TABLE IF NOT EXISTS `rekamMedis`\n" +
                "(\n" +
                "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  `user` INTEGER,\n" +
                "  `height` DOUBLE,\n" +
                "  `weight` DOUBLE,\n" +
                "  `bloodPressure` DOUBLE,\n" +
                "  `date` DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `result` TEXT,\n" +
                "  FOREIGN KEY(`user`) REFERENCES `user`(`id`)\n" +
                ")";

        DBProperties.getInstance().excuteQuery(query);
        System.out.println("Create REKAM MEDIS Table Successfully");
    }

    private void createUserTable() throws SQLException
    {
        String query = "" +
                "CREATE TABLE IF NOT EXISTS `user`\n" +
                "(\n" +
                "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  `fullName` TEXT,\n" +
                "  `nickName` TEXT,\n" +
                "  `birthDate` TEXT,\n" +
                "  `gender` TEXT,\n" +
                "  `avatar` BLOB,\n" +
                "  `thumbnail` BLOB,\n" +
                "  `strokeDisease` BLOB\n" +
                ")";

        DBProperties.getInstance().excuteQuery(query);
        System.out.println("Create USER Table Successfully");
    }

    private void excuteQuery(String query) throws SQLException
    {
        if(this.isActive)
        {
            this.statement = this.connection.createStatement();
            this.statement.execute(query);
        }
    }

    public void disconnect() throws SQLException
    {
        if(this.connection != null)
        {
            this.connection.close();
            this.isActive = false;
            this.notifyObserver("databaseState");
            System.out.println("Connection to SQLite has been disconnect.");
        }
    }

    @Override public void registerObserver(String type, Observer observer)
    {
        this.observers.putIfAbsent(type, new LinkedList<>());
        this.observers.get(type).addLast(observer);
    }

    @Override public void removeObserver(String type, Observer observer)
    {
        if(this.observers.get(type) != null)
        {
            this.observers.get(type).remove(observer);
        }
    }

    @Override public void notifyObserver(String type)
    {
        if(this.observers.get(type) != null)
        {
            this.observers.get(type).forEach(Observer::update);
        }
    }

    @Override public void notifyAllObserver()
    {
        for(Map.Entry<String, LinkedList<Observer>> e : this.observers.entrySet())
        {
            this.observers.get(e.getKey()).forEach(Observer::update);
        }
    }
}
