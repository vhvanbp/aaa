package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.PhuTungXe;

public class PhuTungBAL {
	//Region - Phu tung CRUD
	//Lay thong tin tat ca phu tung
	public List<PhuTungXe> getAll_PT() {
		List<PhuTungXe> ds_PT = new ArrayList<PhuTungXe>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaPT, TenPT, GiaTien, MaNCC" 
											+ ", MaLoaiPTX, LoaiXe, Hang, SoLuong, MaKhu " 
											+ "FROM PhuTung");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_PT.add(new PhuTungXe(rs.getString("MaPT"), rs.getString("TenPT"), rs.getDouble("GiaTien"),
										rs.getString("MaNCC"), rs.getString("MaLoaiPTX"), rs.getString("LoaiXe"),
										rs.getString("Hang"), rs.getInt("SoLuong"), rs.getString("MaKhu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_PT;
	}
	
	// Lay thong tin phu tung
	public PhuTungXe get_PTByID(String maPT) {
		PhuTungXe pt = new PhuTungXe();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaPT, TenPT, GiaTien, MaNCC, "
										+ "MaLoaiPTX, LoaiXe, Hang, SoLuong, MaKhu "
										+ "FROM PhuTung "
										+ "where MaPT =?");
			stmt.setString(1, maPT); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				pt = new PhuTungXe(rs.getString("MaPT"), rs.getString("TenPT"), rs.getDouble("GiaTien")
								, rs.getString("MaNCC"), rs.getString("MaLoaiPTX"), rs.getString("LoaiXe")
								, rs.getString("Hang"), rs.getInt("SoLuong"), rs.getString("MaKhu"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return pt;
	}
			
	// them phu tung
	public boolean create(PhuTungXe pt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into PhuTung(MaPT, TenPT, GiaTien, MaNCC"
															+ ", MaLoaiPTX, LoaiXe, Hang, SoLuong, MaKhu) "
															+ "values(?, ?, ?, ?"
																+ ", ?, ?, ?, ?, ?)");
					
			stmt.setString(1, pt.getMaPT());
			stmt.setString(2, pt.getTenPT());
			stmt.setDouble(3, pt.getGiaTien());
			stmt.setString(4, pt.getMaNhaCungCap());
			stmt.setString(5, pt.getMaLoaiPTX());
			stmt.setString(6, pt.getLoaiXe());
			stmt.setString(7, pt.getHang());
			stmt.setInt(8, pt.getSoLuong());
			stmt.setString(9, pt.getMaKhu());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	// sua phu tung
	public boolean update(PhuTungXe pt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update PhuTung "
										+ "set TenPT =?, GiaTien =?, MaNCC =? "
										+ ", MaLoaiPTX =?, LoaiXe =?, Hang =?, SoLuong =?, MaKhu =? "
										+ "where MaPT =?");
			
			stmt.setString(1, pt.getTenPT());
			stmt.setDouble(2, pt.getGiaTien());
			stmt.setString(3, pt.getMaNhaCungCap());
			stmt.setString(4, pt.getMaLoaiPTX());
			stmt.setString(5, pt.getLoaiXe());
			stmt.setString(6, pt.getHang());
			stmt.setInt(7, pt.getSoLuong());
			stmt.setString(8, pt.getMaKhu());
			stmt.setString(9, pt.getMaPT());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua phu tung voi gia tien km
	public boolean updatePT_KM(String mapt, double tien) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update PhuTung "
										+ "set GiaTien =? "
										+ "where MaPT =?");
			
			stmt.setDouble(1, tien);
			stmt.setString(2, mapt);
			
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
			stmt = con.prepareStatement("delete from PhuTung where MaPT = ?");
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
	
	//Region - Other functions
	// lay loai pT tu ma loai
	public String RetrieveLoaiPT(String maLoaiPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String loaiPT="";
		try {
			stmt = con.prepareStatement("Select * from LoaiPhuTung where MaLoai = ?");
			stmt.setString(1, maLoaiPT);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				loaiPT = rs.getString("LoaiPT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return loaiPT;
	} 
	
	// lay ma loai pt tu ten loai
	public String RetrieveMaLoai(String tenLoaiPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String maloaiPT="";
		try {
			stmt = con.prepareStatement("Select * from LoaiPhuTung where LoaiPT = ?");
			stmt.setString(1, tenLoaiPT);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				maloaiPT = rs.getString("MaLoai");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return maloaiPT;
	} 
	
	// lay ncc tu ma ncc
	public String RetrieveNCC(String maNCC) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String tenNCC="";
		try {
			stmt = con.prepareStatement("Select HoTen from NhaCungCap AS ncc INNER JOIN "
													+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV "
													+ "where tv.MaTV = ?");
			stmt.setString(1, maNCC);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				tenNCC = rs.getString("HoTen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return tenNCC;
	} 

	// lay ma ncc tu ten ncc
	public String Retrieve_MaNCC(String tenNCC) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String maNCC="";
		try {
			stmt = con.prepareStatement("Select * from NhaCungCap AS ncc INNER JOIN "
													+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV "
													+ "where tv.HoTen = ?");
			stmt.setString(1, tenNCC);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				maNCC = rs.getString("MaTV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return maNCC;
	} 
	
	//EndRegion
}
