package dAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DbUtils {
	public static void close(Connection con) {
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(ResultSet rs, Statement stmt) {
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	//danh cho chuc nang tim kiem
    public static TableModel resultSetToTableModel(ResultSet rs) {
	try {
	    ResultSetMetaData metaData = rs.getMetaData();
	    int numberOfColumns = metaData.getColumnCount();
	    Vector<String> columnNames = new Vector<String>();

	    // Get the column names
	    for (int column = 0; column < numberOfColumns; column++) {
		columnNames.addElement(metaData.getColumnLabel(column + 1));
	    }

	    // Get all rows.
	    Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

	    while (rs.next()) {
		Vector<Object> newRow = new Vector<Object>();

		for (int i = 1; i <= numberOfColumns; i++) {
		    newRow.addElement(rs.getObject(i));
		}

		rows.addElement(newRow);
	    }

	    return new DefaultTableModel(rows, columnNames);
	} catch (Exception e) {
	    e.printStackTrace();

	    return null;
	}
    }
}
