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
import entity.carPart.KhuyenMai;

public class KhuyenMaiBAL {
	//Region - CRUD
	//Lay thong tin tat ca khuyen mai
	public List<KhuyenMai> getAll_KM() {
		List<KhuyenMai> ds_KM = new ArrayList<KhuyenMai>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaKM, TenKM, MaPT, MaNCC, NgayBatDau, NgayKetThuc, MoTaKM, GiaTienKM "
										+ "FROM   KhuyenMai");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_KM.add(new KhuyenMai(rs.getString("MaKM"), rs.getString("TenKM"), rs.getString("MaPT"),
										rs.getString("MaNCC"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc"), 
										rs.getString("MoTaKM"), rs.getDouble("GiaTienKM")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_KM;
	}
	
	//Lay thong tin tat ca khuyen mai theo nha cung cap (TK)
	public List<KhuyenMai> getAll_KM(String taiKhoan) {
		List<KhuyenMai> ds_KM = new ArrayList<KhuyenMai>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT KM.MaKM, KM.TenKM, KM.MaPT, KM.MaNCC, "
										+ "KM.NgayBatDau, KM.NgayKetThuc, KM.MoTaKM, KM.GiaTienKM "
										+ "FROM   KhuyenMai  AS KM INNER JOIN "
//										+ "PhuTung AS PT ON KM.MaPT = PT.MaPT INNER JOIN "
										+ "NhaCungCap AS NCC ON KM.MaNCC = NCC.MaTV "
//										+ "AND "
//										+ "PT.MaNCC = NCC.MaTV "
										+ "INNER JOIN "
										+ "ThanhVien AS TV ON NCC.MaTV = TV.MaTV "
										+ "where	TV.TaiKhoan = ?");
			
			stmt.setString(1, taiKhoan); 
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_KM.add(new KhuyenMai(rs.getString("MaKM"), rs.getString("TenKM"), rs.getString("MaPT"),
										rs.getString("MaNCC"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc"), 
										rs.getString("MoTaKM"), rs.getDouble("GiaTienKM")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_KM;
	}
	
	// Lay thong tin khuyen mai
	public KhuyenMai get_KMByID(String maKM) {
		KhuyenMai km = new KhuyenMai();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaKM, TenKM, MaPT, MaNCC, "
										+ "NgayBatDau, NgayKetThuc, MoTaKM, GiaTienKM "
										+ "FROM KhuyenMai "
										+ "where MaKM =?");
			stmt.setString(1, maKM); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				km = new KhuyenMai(rs.getString("MaKM"), rs.getString("TenKM"), rs.getString("MaPT")
								, rs.getString("MaNCC"), rs.getDate("NgayBatDau"), rs.getDate("NgayKetThuc")
								, rs.getString("MoTaKM"), rs.getDouble("GiaTienKM"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return km;
	}
			
		
	// them khuyen mai
	public boolean create(KhuyenMai km) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into KhuyenMai(MaKM, TenKM, MaPT, MaNCC, "
															+ "NgayBatDau, NgayKetThuc, MoTaKM, GiaTienKM) "
															+ "values(?, ?, ?, ?, "
																	+ "?, ?, ?, ?)");
			
			Date sqlDateStart =new Date(km.getNgayBatDau().getTime());
			Date sqlDateEnd =new Date(km.getNgayKetThuc().getTime());
			stmt.setString(1, km.getMaKM());
			stmt.setString(2, km.getTenKM());
			stmt.setString(3, km.getMaPT());
			stmt.setString(4, km.getMaNCC());
			stmt.setDate(5, sqlDateStart);
			stmt.setDate(6, sqlDateEnd);
			stmt.setString(7, km.getMoTaKM());
			stmt.setDouble(8, km.getGiaTienKM());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		
	//xoa thong tin khuyen mai
	public boolean delete(String maKM) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from KhuyenMai where MaKM = ?");
			stmt.setString(1, maKM);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua khuyen mai
	public boolean update(KhuyenMai km) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update KhuyenMai " 
										+ "set TenKM =?, MaPT =?, MaNCC=?, " 
										+ "NgayBatDau =?, NgayKetThuc =?, MoTaKM=?, GiaTienKM=? " 
										+ "where MaKM =?");
			
			Date sqlDateStart =new Date(km.getNgayBatDau().getTime());
			Date sqlDateEnd =new Date(km.getNgayKetThuc().getTime());
			stmt.setString(1, km.getTenKM());
			stmt.setString(2, km.getMaPT());
			stmt.setString(3, km.getMaNCC());
			stmt.setDate(4,  sqlDateStart);
			stmt.setDate(5,  sqlDateEnd);
			stmt.setString(6, km.getMoTaKM());
			stmt.setDouble(7, km.getGiaTienKM());
			stmt.setString(8, km.getMaKM());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	//EndRegion
			
	//Region - Others fuctions
	// lay ten pT tu ma loai
	public String RetrievePT(String maPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String tenPT="";
		try {
			stmt = con.prepareStatement("Select * from PhuTung where MaPT = ?");
			stmt.setString(1, maPT);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				tenPT = rs.getString("TenPT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return tenPT;
	} 
	
	// lay ma pt tu ten loai
	public String RetrieveMaPT(String tenPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String maPT="";
		try {
			stmt = con.prepareStatement("Select * from PhuTung where TenPT = ?");
			stmt.setString(1, tenPT);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				maPT = rs.getString("MaPT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return maPT;
	} 

	// Dem khuyem mai theo ... -> Make change ?
	public int Count(String loai){
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count=0;
		try {
			stmt = con.prepareStatement("Select COUNT(*) AS rowcount from KhuyenMai where MaLoai =?");
			stmt.setString(1, loai);
			rs = stmt.executeQuery();
			
			rs.next();
			count = rs.getInt("rowcount");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return count;
	}	
	//EndRegion
	
}
