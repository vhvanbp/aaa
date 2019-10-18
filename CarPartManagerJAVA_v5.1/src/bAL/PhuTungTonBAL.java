package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.PhuTungTon;

public class PhuTungTonBAL {
	//Region - Phu Tung Ton CRUD
	String tableColumns = "MaPT, SoLuongBanDau, SoLuongHienTai, ThangNhap, NamNhap";

	//Lay thong tin tat ca PhuTungTon
	public List<PhuTungTon> getAll_PTT() {
		List<PhuTungTon> ds_PTT = new ArrayList<PhuTungTon>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " FROM PhuTungTon");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_PTT.add(new PhuTungTon(rs.getString("MaPT"), rs.getInt("SoLuongBanDau")
										, rs.getInt("SoLuongHienTai"), rs.getInt("ThangNhap")
										, rs.getInt("NamNhap")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_PTT;
	}

	//Lay thong tin tat ca PhuTungTon theo thang, nam
		public List<PhuTungTon> getAll_PTT(int thang, int nam) {
			List<PhuTungTon> ds_PTT = new ArrayList<PhuTungTon>();
			Connection con = DataBase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				stmt = con.prepareStatement("SELECT " + tableColumns + " FROM PhuTungTon "
											+ "where ThangNhap = ? and NamNhap = ?");
				stmt.setInt(1, thang); 
				stmt.setInt(2, nam); 
				rs = stmt.executeQuery();
				while (rs.next()) {
					ds_PTT.add(new PhuTungTon(rs.getString("MaPT"), rs.getInt("SoLuongBanDau")
											, rs.getInt("SoLuongHienTai"), rs.getInt("ThangNhap")
											, rs.getInt("NamNhap")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbUtils.close(rs, stmt);
			}
			return ds_PTT;
		}
		
	// Lay thong tin PhuTungTon
	public PhuTungTon get_PTTonByID(String maPT) {
		PhuTungTon ptt = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT " + tableColumns + " "
										+ "FROM PhuTungTon "
										+ "where MaPT =?");
			stmt.setString(1, maPT); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				ptt = new PhuTungTon(rs.getString("MaPT"), rs.getInt("SoLuongBanDau")
									, rs.getInt("SoLuongHienTai"), rs.getInt("ThangNhap")
									, rs.getInt("NamNhap"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ptt;
	}
			
	// them Phieu Xuat
	public boolean create(PhuTungTon ptt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into PhuTungTon(" + tableColumns + ") "
															+ "values(?, ?, ?, ?, ?)");
				
			stmt.setString(1, ptt.getMaPT());
			stmt.setInt(2, ptt.getSoLuongBanDau());
			stmt.setInt(3, ptt.getSoLuongHienTai());
			stmt.setInt(4, ptt.getThangNhap());
			stmt.setInt(5, ptt.getNamNhap());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		

	// sua PhuTungTon
	public boolean update(PhuTungTon ptt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update PhuTungTon "
										+ "set SoLuongBanDau =? "
										+ ", SoLuongHienTai =? "
										+ ", ThangNhap =? "
										+ ", NamNhap =? "
										+ "where MaPT =?");
			
			stmt.setInt(1, ptt.getSoLuongBanDau());
			stmt.setInt(2, ptt.getSoLuongHienTai());
			stmt.setInt(3, ptt.getThangNhap());
			stmt.setInt(4, ptt.getNamNhap());
			stmt.setString(5, ptt.getMaPT());

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
			stmt = con.prepareStatement("delete from PhuTungTon where MaPT = ?");
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
