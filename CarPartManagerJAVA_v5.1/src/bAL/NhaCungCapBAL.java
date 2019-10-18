package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.users.NhaCungCap;
import entity.users.ThanhVien;
import helpers.BAL_Helpers;

public class NhaCungCapBAL extends BAL_Helpers {
//	ThanhVienBAL tvbal = new ThanhVienBAL();
	
	//Region - Nha cung cap CRUD
	//Lay thong tin tat ca nha cung cap
	public List<ThanhVien> getAll_NCC() {
		List<ThanhVien> ds_NCC = new ArrayList<ThanhVien>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.Email, ncc.CongTy "
												+ "FROM   NhaCungCap AS ncc INNER JOIN "
												+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV");
			rs = stmt.executeQuery();
			while(rs.next()){
				ds_NCC.add(new NhaCungCap(rs.getString("MaTV"), rs.getString("HoTen")
										, rs.getString("Email"), rs.getString("CongTy")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ds_NCC;
	}

	// Lay thong tin nha cung cap
	public ThanhVien get_NCCByID(String maNCC) {
		ThanhVien ncc = new ThanhVien();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, ncc.CongTy "
												+ "FROM   NhaCungCap AS ncc INNER JOIN "
												+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV "
												+ "where tv.MaTV =?");
			stmt.setString(1, maNCC); 
			rs = stmt.executeQuery();
			
			while(rs.next()){
				ncc = new NhaCungCap(rs.getString("MaTV"), rs.getString("HoTen"), rs.getString("LoaiThanhVien")
									, rs.getString("Email"), rs.getString("Sdt"), rs.getString("TaiKhoan")
									, rs.getString("MatKhau"), rs.getString("CongTy"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ncc;
	}
	
	// them nha cung cap
	public boolean create(NhaCungCap ncc) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		tvbal.create(ncc);
			
		try {
			stmt = con.prepareStatement("insert into NhaCungCap(MaTV,CongTy) "
										+ "values(?, ?)");
			stmt.setString(1, ncc.getMaTV());
			stmt.setString(2, ncc.getCongTy());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua nha cung cap 
	public boolean update(NhaCungCap ncc) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		tvbal.update(ncc);
		
		try {
			stmt = con.prepareStatement("update NhaCungCap "
										+ "set CongTy =? "
										+ "where MaTV =?");
			stmt.setString(1, ncc.getCongTy());
			stmt.setString(2, ncc.getMaTV());
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
	public ThanhVien get_NCCByAccount(String taiKhoan) {
		ThanhVien ncc = new ThanhVien();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, ncc.CongTy "
												+ "FROM   NhaCungCap AS ncc INNER JOIN "
												+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV "
												+ "where TaiKhoan =?");
			stmt.setString(1, taiKhoan);
//			stmt.executeUpdate();
			
			rs = stmt.executeQuery();
			while(rs.next()){
				ncc = new NhaCungCap(rs.getString("MaTV"), rs.getString("HoTen"), rs.getString("LoaiThanhVien")
									, rs.getString("Email"), rs.getString("Sdt"), rs.getString("TaiKhoan")
									, rs.getString("MatKhau"), rs.getString("CongTy"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ncc;
	}
	
	//EndRegion


}
