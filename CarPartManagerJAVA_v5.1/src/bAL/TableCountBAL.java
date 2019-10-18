package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.others.TableCount;

public class TableCountBAL {
	//Region - Table Count CRUD
	//get all Table Count
	public static List<TableCount> getAll_TC() {
		List<TableCount> ds_TC = new ArrayList<TableCount>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT TableID, Quantity FROM TableCount");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_TC.add(new TableCount(rs.getString("TableID"), rs.getInt("Quantity")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_TC;
	}

	// Lay thong tin loai phu tung
	public TableCount get_TCByID(String tcID) {
		TableCount tc = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT TableID, Quantity "
										+ "FROM TableCount "
										+ "where TableID =?");
			stmt.setString(1, tcID); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				tc = new TableCount(rs.getString("TableID"), rs.getInt("Quantity"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return tc;
	}
			
	// them loai phu tung
	public boolean create(TableCount tc) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into TableCount(TableID, Quantity) "
															+ "values(?, ?)");
					
			stmt.setString(1, tc.getTableID());
			stmt.setLong(2, tc.getQuantity());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		

	// sua phu tung
	public boolean update(TableCount tc) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update TableCount "
										+ "set Quantity =? "
										+ "where TableID =?");
			
			stmt.setLong(1, tc.getQuantity());
			stmt.setString(2, tc.getTableID());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}

	//EndRegion
	
	//Region - Other functions
	// check Table
	public int Check_table(String tcID){ // refator later -> redundant code
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from TableCount where TableID =?");
			stmt.setString(1, tcID);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return 0;
	}
	//EndRegion
}
