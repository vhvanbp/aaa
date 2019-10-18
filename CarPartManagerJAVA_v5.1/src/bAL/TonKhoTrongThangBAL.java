package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.TonKhoTrongThang;

public class TonKhoTrongThangBAL {
	//Region - TonKhoTrongThang CRUD
	String tableColumns = "MaTon, TongSLNhap, TongSLTon, TongSLXuat, TongChiPhiXuat, ThangTon, NamTon";

	//Lay thong tin tat ca TonKhoTrongThang
	public List<TonKhoTrongThang> getAll_TKTT() {
		List<TonKhoTrongThang> ds_TKTT = new ArrayList<TonKhoTrongThang>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " FROM TonKhoTrongThang");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_TKTT.add(new TonKhoTrongThang(rs.getString("MaTon"), rs.getInt("TongSLNhap")
												, rs.getInt("TongSLTon"), rs.getInt("TongSLXuat")
												, rs.getDouble("TongChiPhiXuat"), rs.getInt("ThangTon")
												, rs.getInt("NamTon")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_TKTT;
	}

	// Lay thong tin TonKhoTrongThang
	public TonKhoTrongThang get_TKTTByID(String maTon) {
		TonKhoTrongThang tktt = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " "
										+ "FROM TonKhoTrongThang "
										+ "where MaTon =?");
			stmt.setString(1, maTon); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				tktt = new TonKhoTrongThang(rs.getString("MaTon"), rs.getInt("TongSLNhap")
											, rs.getInt("TongSLTon"), rs.getInt("TongSLXuat")
											, rs.getDouble("TongChiPhiXuat"), rs.getInt("ThangTon")
											, rs.getInt("NamTon"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return tktt;
	}
			
	// Lay thong tin TonKhoTrongThang theo thang nam
	public TonKhoTrongThang get_TKTTByID_Thang(int thang, int nam) {
		TonKhoTrongThang tktt = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " " 
								+ "FROM TonKhoTrongThang " 
								+ "where ThangTon =? and NamTon =?");
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			rs = stmt.executeQuery();

			while (rs.next()) {
				tktt = new TonKhoTrongThang(rs.getString("MaTon"), rs.getInt("TongSLNhap"), rs.getInt("TongSLTon"),
						rs.getInt("TongSLXuat"), rs.getDouble("TongChiPhiXuat"), rs.getInt("ThangTon"),
						rs.getInt("NamTon"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return tktt;
	}
				
	// them TonKhoTrongThang
	public boolean create(TonKhoTrongThang tktt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into TonKhoTrongThang(" + tableColumns + ") "
															+ "values(?, ?, ?, ?, ?, ?, ?)");
				
			stmt.setString(1, tktt.getMaTon());
			stmt.setInt(2, tktt.getTongSLNhap());
			stmt.setInt(3, tktt.getTongSLTon());
			stmt.setInt(4, tktt.getTongSLXuat());
			stmt.setDouble(5, tktt.getTongChiPhiXuat());
			stmt.setInt(6, tktt.getThangTon());
			stmt.setInt(7, tktt.getNamTon());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	// sua TonKhoTrongThang
	public boolean update(TonKhoTrongThang tktt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update TonKhoTrongThang "
										+ "set TongSLNhap =? "
										+ ", TongSLTon =? "
										+ ", TongSLXuat =? "
										+ ", TongChiPhiXuat =? "
										+ ", ThangTon =? "
										+ ", NamTon =? "
										+ "where MaTon =?");
			
			stmt.setInt(1, tktt.getTongSLNhap());
			stmt.setInt(2, tktt.getTongSLTon());
			stmt.setInt(3, tktt.getTongSLXuat());
			stmt.setDouble(4, tktt.getTongChiPhiXuat());
			stmt.setInt(5, tktt.getThangTon());
			stmt.setInt(6, tktt.getNamTon());
			stmt.setString(7, tktt.getMaTon());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}

	//xoa thong tin 
	public boolean delete(String maTKTT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from TonKhoTrongThang where MaTon = ?");
			stmt.setString(1, maTKTT);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	//EndRegion

}
