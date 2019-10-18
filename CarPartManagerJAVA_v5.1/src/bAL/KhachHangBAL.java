package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.users.KhachHang;
import entity.users.ThanhVien;
import helpers.BAL_Helpers;

public class KhachHangBAL extends BAL_Helpers{
	//Region - Khach hang CRUD
	//Lay thong tin tat ca khach hang
	public List<ThanhVien> getAll_KH() {
		List<ThanhVien> ds_KH = new ArrayList<ThanhVien>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// USE JOIN ...
			stmt = con.prepareStatement("Select tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
											 + "tv.Sdt, tv.TaiKhoan, tv.MatKhau, kh.SoHangMua, kh.LoaiXeDangDung "
											 + "from KhachHang AS kh INNER JOIN "
											 + "ThanhVien AS tv ON kh.MaTV = tv.MaTV"); 
			rs = stmt.executeQuery();
			while(rs.next()){
				ds_KH.add(new KhachHang(rs.getString("MaTV"), rs.getString("HoTen")
										, rs.getString("LoaiThanhVien"), rs.getString("Email")
										, rs.getString("Sdt"), rs.getString("TaiKhoan")
										, rs.getString("MatKhau"), rs.getInt("SoHangMua")
										, rs.getString("LoaiXeDangDung")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ds_KH;
	}
	
	// Lay thong tin khach hang
	public ThanhVien get_KHByID(String maKH) {
		ThanhVien kh = new ThanhVien();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, kh.SoHangMua, kh.LoaiXeDangDung "
												+ "FROM   KhachHang AS kh INNER JOIN "
												+ "ThanhVien AS tv ON kh.MaTV = tv.MaTV "
												+ "where tv.MaTV =?");
			stmt.setString(1, maKH); 
			rs = stmt.executeQuery();
			
			while(rs.next()){
				kh = new KhachHang(rs.getString("MaTV"), rs.getString("HoTen")
									, rs.getString("LoaiThanhVien"), rs.getString("Email")
									, rs.getString("Sdt"), rs.getString("TaiKhoan")
									, rs.getString("MatKhau"), rs.getInt("SoHangMua")
									, rs.getString("LoaiXeDangDung"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return kh;
	}
	
	// them khach hang
	public boolean create(KhachHang kh) {
			Connection con = DataBase.getConnection();
			PreparedStatement stmt = null;

			tvbal.create(kh);
			
			int n = 0;
			try {
				stmt = con.prepareStatement("insert into KhachHang(MaTV,SoHangMua,LoaiXeDangDung) "
									+ "values(?, ?, ?)");
			
				stmt.setString(1, kh.getMaTV());
				stmt.setInt(2, 0);
				stmt.setString(3, kh.getLoaiXeDangDung());

				n = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtils.close(stmt);
			}
			return n > 0;
	}
	
	// sua khach hang
	public boolean update(KhachHang kh) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		tvbal.update(kh);

		try {
			stmt = con.prepareStatement("update KhachHang " 
										+ "set SoHangMua =?, LoaiXeDangDung=? " 
										+ "where MaTV =?");
			stmt.setInt(1, kh.getSoHangMua());
			stmt.setString(2, kh.getLoaiXeDangDung());
			stmt.setString(3, kh.getMaTV());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	//EndRegion

}
