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
import entity.users.NhanVien;
import entity.users.ThanhVien;
import helpers.BAL_Helpers;

public class NhanVienBAL extends BAL_Helpers{
//	ThanhVienBAL tvbal = new ThanhVienBAL();
	
	//Region - Nhan vien CRUD
	//Lay thong tin tat ca thanh vien
	public List<ThanhVien> getAll_NV() {
		List<ThanhVien> ds_NV = new ArrayList<ThanhVien>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, nv.NgayVaoLam, nv.MucLuong, nv.TienLuong "
												+ "FROM   NhanVien AS nv INNER JOIN "
												+ "ThanhVien AS tv ON nv.MaTV = tv.MaTV");
			rs = stmt.executeQuery();
			while(rs.next()){
				ds_NV.add(new NhanVien(rs.getString("MaTV"), rs.getString("HoTen")
										, rs.getDate("NgayVaoLam"), rs.getInt("MucLuong")
										, rs.getDouble("TienLuong")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return ds_NV;
	}
	
	// Lay thong tin nhan vien 
//	tvBal.Retrieve(tenLogin);
	public ThanhVien get_NVByID(String maNV) {
		ThanhVien nv = new ThanhVien();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email, "
												+ "tv.Sdt, tv.TaiKhoan, tv.MatKhau, nv.NgayVaoLam, "
												+ "nv.MucLuong, nv.TienLuong "
												+ "FROM   NhanVien AS nv INNER JOIN "
												+ "ThanhVien AS tv ON nv.MaTV = tv.MaTV "
												+ "where tv.MaTV =?"); // + " 'tenNV' " ???
			stmt.setString(1, maNV); // ? -> this work?
			rs = stmt.executeQuery();
			
			while(rs.next()){
				nv = new NhanVien(rs.getString("MaTV"), rs.getString("HoTen"), rs.getString("LoaiThanhVien")
						, rs.getString("Email"), rs.getString("Sdt"), rs.getString("TaiKhoan")
						, rs.getString("MatKhau"), rs.getDate("NgayVaoLam"), rs.getInt("MucLuong")
						, rs.getDouble("TienLuong"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return nv;
	}
	
	// them nhan vien 
	public boolean create(NhanVien nv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		tvbal.create(nv);
			
		try {
			stmt = con.prepareStatement("insert into NhanVien(MaTV,NgayVaoLam,MucLuong,TienLuong) "
										+ "values(?, ?, ?, ?)");
//			DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
			
			Date sqlDate =new Date(nv.getNgayVaoLam().getTime());
			stmt.setString(1, nv.getMaTV());
			stmt.setDate(2, sqlDate);
			stmt.setInt(3, nv.getMucLuong());
			stmt.setDouble(4, nv.getTienLuong());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua nhan vien 
	public boolean update(NhanVien nv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		tvbal.update(nv);
		
		try {
			stmt = con.prepareStatement("update NhanVien "
										+ "set NgayVaoLam =?, MucLuong =?, TienLuong=? "
										+ "where MaTV =?");
			Date sqlDate =new Date(nv.getNgayVaoLam().getTime());
			stmt.setDate(1, sqlDate);
			stmt.setInt(2, nv.getMucLuong());
			stmt.setDouble(3, nv.getTienLuong());
			stmt.setString(4, nv.getMaTV());
			
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
//	public ThanhVien searchNVByCbo(int cboIndex, String keyword) { //search nhan vien trong combo box & input text
//		ThanhVien nv = new ThanhVien();
//		Connection con = DataBase.getConnection();
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			String selection[] = new String[]{"tv.MaTV","tv.HoTen","nv.MucLuong"};
//			stmt = con.prepareStatement("SELECT tv.MaTV, tv.HoTen, nv.NgayVaoLam"
//												+ ", nv.MucLuong, nv.TienLuong "
//												+ "FROM   NhanVien AS nv INNER JOIN "
//												+ "ThanhVien AS tv ON nv.MaTV = tv.MaTV "
//												+ "where "+selection[cboIndex]+" = ?");
//			stmt.setString(1, keyword);
//			rs = stmt.executeQuery();
////			table.setModel(DbUtils.resultSetToTableModel(rs));
//			
//			
//			stmt.close();
//			
//			while(rs.next()){
//				nv = new NhanVien(rs.getString("MaTV"), rs.getString("HoTen"), rs.getString("LoaiThanhVien")
//						, rs.getString("Email"), rs.getString("Sdt"), rs.getString("TaiKhoan")
//						, rs.getString("MatKhau"), rs.getDate("NgayVaoLam"), rs.getInt("MucLuong")
//						, rs.getDouble("TienLuong"));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DbUtils.close(rs, stmt);
//		}
//		return nv;
//	}
	
	//EndRegion
}
