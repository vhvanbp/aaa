package bAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.PhieuNhap;

public class PhieuNhapBAL {
	//Region - Phieu Nhap CRUD
	String tableColumns = "MaPT, SoLuongNhap, NgayNhapHang";
	
	//Lay thong tin tat ca Phieu Nhap
	public List<PhieuNhap> getAll_PhieuNhap() {
		List<PhieuNhap> ds_PN = new ArrayList<PhieuNhap>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " FROM PhieuNhap");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_PN.add(new PhieuNhap(rs.getString("MaPT")
									, rs.getInt("SoLuongNhap"), rs.getDate("NgayNhapHang")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_PN;
	}

	// Lay thong tin Phieu Nhap
	public PhieuNhap get_PhieuNhapByID(String maPT) {
		PhieuNhap pn = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " "
										+ "FROM PhieuNhap "
										+ "where MaPT =?");
			stmt.setString(1, maPT); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				pn = new PhieuNhap(rs.getString("MaPT")
									, rs.getInt("SoLuongNhap"), rs.getDate("NgayNhapHang"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return pn;
	}
			
	// them Phieu Nhap
	public boolean create(PhieuNhap pn) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into PhieuNhap(" + tableColumns + ") "
															+ "values(?, ?, ?)");
					
			Date sqlDate =new Date(pn.getNgayNhapHang().getTime());
			stmt.setString(1, pn.getMaPT());
			stmt.setInt(2, pn.getSoLuongNhap());
			stmt.setDate(3, sqlDate);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	// sua PhieuNhap
	public boolean update(PhieuNhap pn) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update PhieuNhap "
										+ "set SoLuongNhap =? "
										+ ", NgayNhapHang =? "
										+ "where MaPT =?");
			
			Date sqlDate =new Date(pn.getNgayNhapHang().getTime());
			stmt.setInt(1, pn.getSoLuongNhap());
			stmt.setDate(2, sqlDate);
			stmt.setString(3, pn.getMaPT());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	//xoa thong tin 
	public boolean delete(String maPN) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from PhieuNhap where MaPT = ?");
			stmt.setString(1, maPN);
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
		// Kiem tra PN
		public int Check_id(String ma){ // refator later -> redundant code
			Connection con = DataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = con.prepareStatement("Select * from PhieuNhap where MaPT =?");
				stmt.setString(1, ma);
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
