package com.automation.database;

import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.omg.CORBA.SystemException;

public class CoreDA {

	/**
	 * This method counts the results in a table
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param condition
	 * @throws SystemException
	 */
	public int getTableCount(String tableName, String fieldName, String condition) throws Exception {
		int ret = 0;
		AbstractDbUnit dbUnit = new AbstractDbUnit();

		try {

			final String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = '" + condition + "' ";
			QueryDataSet queryDataSet = dbUnit.getQueryDataSet(true);
			queryDataSet.addTable(tableName, query);
			ret = queryDataSet.getTable(tableName).getRowCount();
		}

		catch (DataSetException de) {
			throw new Exception("DataSet Exception ." + de);
		} finally {
			dbUnit.closeIDBConnection();
			dbUnit = null;
		}
		return ret;
	}

}