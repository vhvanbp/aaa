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
import entity.carPart.PhieuXuat;

public class PhieuXuatBAL {
	//Region - Phieu Xuat CRUD
	String tableColumns = "MaPX, MaPT, SoLuongXuat, NgayXuatHang, ChiPhi";

	//Lay thong tin tat ca Phieu Xuat
	public List<PhieuXuat> getAll_PX() {
		List<PhieuXuat> ds_PX = new ArrayList<PhieuXuat>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " FROM PhieuXuat");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_PX.add(new PhieuXuat(rs.getString("MaPX"), rs.getString("MaPT")
									, rs.getInt("SoLuongXuat"), rs.getDate("NgayXuatHang")
									, rs.getDouble("ChiPhi")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_PX;
	}

	// Lay thong tin Phieu Xuat
	public PhieuXuat get_PhieuXuatByID(String maPT) {
		PhieuXuat px = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " "
										+ "FROM PhieuXuat "
										+ "where MaPT =?");
			stmt.setString(1, maPT); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				px = new PhieuXuat(rs.getString("MaPX"), rs.getString("MaPT")
										, rs.getInt("SoLuongXuat"), rs.getDate("NgayXuatHang")
										, rs.getDouble("ChiPhi"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return px;
	}
			
	// them Phieu Xuat
	public boolean create(PhieuXuat px) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into PhieuXuat(" + tableColumns + ") "
															+ "values(?, ?, ?, ?, ?)");
				
			Date sqlDate =new Date(px.getNgayXuatHang().getTime());
			stmt.setString(1, px.getMaPX());
			stmt.setString(2, px.getMaPT());
			stmt.setInt(3, px.getSoLuongXuat());
			stmt.setDate(4, sqlDate);
			stmt.setDouble(5, px.getChiPhi());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	// sua Phieu Xuat
	public boolean update(PhieuXuat px) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update PhieuXuat "
										+ "set MaPT =? "
										+ ", SoLuongXuat =? "
										+ ", NgayXuatHang =? "
										+ ", ChiPhi =? "
										+ "where MaPX =?");
			
			Date sqlDate =new Date(px.getNgayXuatHang().getTime());
			stmt.setString(1, px.getMaPT());
			stmt.setInt(2, px.getSoLuongXuat());
			stmt.setDate(3, sqlDate);
			stmt.setDouble(4, px.getChiPhi());
			stmt.setString(5, px.getMaPX());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	//xoa thong tin 
	public boolean delete(String maPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from PhieuXuat where MaPX = ?");
			stmt.setString(1, maPT);
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
