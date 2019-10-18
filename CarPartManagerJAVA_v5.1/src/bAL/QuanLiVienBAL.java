package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.users.QuanLiVien;
import entity.users.ThanhVien;
import helpers.BAL_Helpers;

public class QuanLiVienBAL extends BAL_Helpers {
	//Region - Nha cung cap CRUD
	//Lay thong tin tat ca quan li vien
	public List<ThanhVien> getAll_QLV() {
		List<ThanhVien> ds_QLV = new ArrayList<ThanhVien>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT  tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, qlv.MoTaTV "
												+ "FROM    QuanLiVien AS qlv INNER JOIN "
												+ "ThanhVien AS tv ON qlv.MaTV = tv.MaTV");
			rs = stmt.executeQuery();
			while(rs.next()){
				ds_QLV.add(new QuanLiVien(rs.getString("MaTV"), rs.getString("HoTen")
										, rs.getString("LoaiThanhVien"), rs.getString("Email")
										, rs.getString("Sdt"), rs.getString("TaiKhoan")
										, rs.getString("MatKhau"), rs.getString("MoTaTV")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ds_QLV;
	}
	

	// Lay thong tin quan li vien
	public ThanhVien get_QLVByID(String maQLV) {
		ThanhVien qlv = new ThanhVien();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, qlv.MoTaTV "
												+ "FROM QuanLiVien AS qlv INNER JOIN "
												+ "ThanhVien AS tv ON qlv.MaTV = tv.MaTV "
												+ "where tv.MaTV =?");
			stmt.setString(1, maQLV); 
			rs = stmt.executeQuery();
			
			while(rs.next()){
				qlv = new QuanLiVien(rs.getString("MaTV"), rs.getString("HoTen"), rs.getString("LoaiThanhVien")
									, rs.getString("Email"), rs.getString("Sdt"), rs.getString("TaiKhoan")
									, rs.getString("MatKhau"), rs.getString("MoTaTV"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return qlv;
	}
	
	// them quan li vien
	public boolean create(QuanLiVien qlv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		tvbal.create(qlv);
			
		try {
			stmt = con.prepareStatement("insert into QuanLiVien(MaTV,MoTaTV) "
										+ "values(?, ?)");
			stmt.setString(1, qlv.getMaTV());
			stmt.setString(2, qlv.getMoTaQLV());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua nha cung cap 
	public boolean update(QuanLiVien qlv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		tvbal.update(qlv);
		
		try {
			stmt = con.prepareStatement("update QuanLiVien "
										+ "set MoTaTV =? "
										+ "where MaTV =?");
			stmt.setString(1, qlv.getMoTaQLV());
			stmt.setString(2, qlv.getMaTV());
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
