package com.automation.database;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;


public class AbstractDbUnit
    extends DBTestCase
{

    private ResultSet           rs;

    private PreparedStatement   stmt;

    private boolean             autoCommit = true;

    private IDatabaseTester     dbTester;

    private IDatabaseConnection conn;



    public IDatabaseConnection getConn()
    {
        try
        {
            init();

        }
        catch (Exception e)
        {
            //logger.error("getConn() method", e);
        }
        return conn;
    }

    private Properties dbProperties = new Properties();

    /**
     * This method get the values from properties file.
     * 
     * @throws IOException
     */
    public AbstractDbUnit()
    {

        try
        {
            dbProperties.load(this.getClass().getResourceAsStream(
                    "/db.properties"));
        }
        catch (IOException e)
        {
            //logger.error("AbstractDbUnit() constructor", e);
        }

        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                dbProperties.getProperty("driverClass"));
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                dbProperties.getProperty("url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                dbProperties.getProperty("user"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                dbProperties.getProperty("password"));
        System.setProperty("dbtype",
                dbProperties.getProperty("dbtype"));
        
        System.out.println(   dbProperties.getProperty("dbtype"));
        
    }

    /**
     * <br>
     * This method checks for the database connection. If the connection is null
     * then it connects to the database and also suppress the warning message
     * for Oracle and sql server databases.
     */
    private void init() throws Exception
    {

        try
        {
            if (conn == null)
            {
                dbTester = new PropertiesBasedJdbcDatabaseTester();
                conn = dbTester.getConnection();
                DatabaseConfig dbConfig = conn.getConfig();

                if (isOracle())
                {

                    dbConfig.setProperty(
                            DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                            new OracleDataTypeFactory());
                }
                else
                {

                    dbConfig.setProperty(
                            DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                            new MsSqlDataTypeFactory());
                }
            }
        }
        catch (Exception e)
        {
           // logger.error("init() method", e);
           // throw new Exception(e);
        }
    }

    /**
     * <br>
     * This method execute the statements
     * 
     * @param sqlString
     * @throws Exception
     */
    public void executeSQLStatement(String sqlString) throws Exception
    {
        init();

        try
        {

            stmt = conn.getConnection().prepareStatement(sqlString);

            if (autoCommit)
            {
                stmt.executeUpdate();
            }
            else
            {
                try
                {
                    if (conn.getConnection().getAutoCommit())
                    {
                        conn.getConnection().setAutoCommit(false);
                    }

                    stmt.executeUpdate();
                }
                catch (SQLException e)
                {
                    //logger.error("executeSQLStatement() method", e);
                    conn.getConnection().rollback();
                    throw e;
                }
            }

        }
        catch (SQLException se)
        {
            //logger.error("executeSQLStatement() method", se);
            throw new Exception(se);
        }
    }

    /**
     * <br>
     * This method execute the sql statements given in the parameter
     * 
     * @param sqlString
     * @throws Exception
     */
    public void executeQuery(String sqlString) throws Exception
    {
        init();

        try
        {

            stmt = conn.getConnection().prepareStatement(sqlString);

            rs = stmt.executeQuery();

        }
        catch (SQLException e)
        {
            //logger.error("executeQuery() method", e);
            throw new Exception(e);
        }
    }

    @Override
    protected IDataSet getDataSet()
    {
        return null;
    }

    public boolean isOracle()
    {
        return (getDBType() != null && getDBType().equalsIgnoreCase("oracle"));
    }

    public String getDBType()
    {
        return dbProperties.getProperty("dbtype");
    }

    /**
     * Releases database resources. Possible null parameter values are checked.
     */
    public void releaseResources()
    {
        releaseResources(true);
    }

    public void releaseResources(boolean closeConnection)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
                rs = null;
            }
            if (stmt != null)
            {
                stmt.close();
                stmt = null;
            }
            if (closeConnection)
            {
                closeIDBConnection();
            }
        }
        catch (Exception e)
        {
            //logger.error("releaseResources() method", e);
        }
    }

    /**
     * Disables autocommit. The <code>commit()</code> method should be used for
     * committing executed queries.
     */
    public void disableAutoCommit()
    {
        this.autoCommit = false;
    }

    /**
     * Enables autocommit.
     */
    public void enableAutoCommit()
    {
        this.autoCommit = true;
    }

    /**
     * Returns the current state of autocommit-option
     * 
     * @return <code>true</code> if autocommit is enabled; <code>false</code> if
     *         not.
     */
    public boolean isAutoCommitEnabled()
    {
        return this.autoCommit;
    }

    /**
     * Commits changes if autocommit was turned off. Enables autocommit after
     * commit. If an exception is thrown during the commit, rollback will be
     * executed.
     * 
     * @throws SQLException
     */
    public void commit() throws SQLException
    {
        if (!conn.getConnection().getAutoCommit())
        {
            try
            {
                conn.getConnection().commit();
            }
            catch (SQLException e)
            {
                conn.getConnection().rollback();
                throw e;
            }
        }
    }

    /**
     * Returns boolean value
     * 
     * @return <code>true</code> if the new current row is valid;
     *         <code>false</code> if there are no more rows
     * @exception SQLException if a database access error occurs
     */
    public boolean rsHasNext() throws SQLException
    {
        return rs.next();
    }

    /**
     * Retrieves the value of the designated column in the current row of the
     * <code>ResultSet</code> object as a <code>String</code> in the Java
     * programming language.
     * 
     * @param i parameter index
     * @return value as a <code>String</code>
     * @throws SQLException if a database access error occurs
     */
    public String getString(int i) throws SQLException
    {
        return rs.getString(i);
    }

    /**
     * Retrieves the value of the designated column in the current row of the
     * <code>ResultSet</code> object as a <code>String</code> in the Java
     * programming language.
     * 
     * @param column column name
     * @return value as a <code>String</code>
     * @throws SQLException if a database access error occurs
     */
    public String getString(String column) throws SQLException
    {
        return rs.getString(column);
    }

    /**
     * Retrieves the value of the designated column in the current row of the
     * <code>ResultSet</code> object as an <code>int</code> in the Java
     * programming language.
     * 
     * @param i parameter index
     * @return value as an <code>int</code>
     * @throws SQLException if a database access error occurs
     */
    public int getInt(int i) throws SQLException
    {
        return rs.getInt(i);
    }

    /**
     * Retrieves the value of the designated column in the current row of the
     * <code>ResultSet</code> object as an <code>String</code> in the Java
     * programming language.
     * 
     * @param column parameter index
     * @return value as an <code>int</code>
     * @throws SQLException if a database access error occurs
     */
    public int getInt(String column) throws SQLException
    {
        return rs.getInt(column);
    }

    /**
     * This method get the table names from the database.
     * 
     * @param caseSensitiveTableNames
     * @return queryDataSet
     * @throws Exception
     */
    public QueryDataSet getQueryDataSet(boolean caseSensitiveTableNames)
            throws Exception
    {

        QueryDataSet queryDataSet = null;

        try
        {
            init();
            queryDataSet = new QueryDataSet(conn, caseSensitiveTableNames);
        }
        catch (Exception e)
        {
            //logger.error("getQueryDataSet() method", e);
            throw new Exception(e);
        }

        return queryDataSet;
    }

    /**
     * This method is for closing all the Resultset objects
     * <code>ResultSet</code> object as an <code>String</code> in the Java
     * programming language.
     * 
     * @throws Exception if a database error occurs
     */
    public void closeIDBConnection()
    {
        try
        {
            if (conn != null)
            {
                conn.close();
                conn = null;
            }
            if (dbTester != null)
            {
                dbTester = null;
            }
        }
        catch (Exception e)
        {
            //logger.error("closeIDBConnection() method", e);
        }
    }

    /**
     * The dataset is inserted in to the database for the appropriate tables for
     * the Oracle and Sql server Databases
     * 
     * @param data
     * @throws Exception
     */
    public void executeInsertOperation(IDataSet data) throws Exception
    {
        init();

        try
        {
            if (!isOracle())
            {
                InsertIdentityOperation i = new InsertIdentityOperation(
                        InsertIdentityOperation.INSERT);
                i.execute(conn, data);
            }
            else
            {
                DatabaseOperation.INSERT.execute(conn, data);
            }
        }
        catch (Exception e)
        {
            //logger.error("executeInsertOperation() method", e);
            throw new Exception(e);
        }
        finally
        {
            releaseResources();
        }

    }

}
