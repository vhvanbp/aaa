package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dAL.DataBase;
import dAL.DbUtils;
import entity.users.ThanhVien;

public class ThanhVienBAL {
	//Region - CRUD
	// them thanh vien
	public boolean create(ThanhVien tv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ThanhVien(MaTV,HoTen,LoaiThanhVien,Email,Sdt,TaiKhoan,MatKhau) "
								+ "values(?, ?, ?, ?, ?, ?, ?)");
					
			stmt.setString(1, tv.getMaTV());
			stmt.setString(2, tv.getHoTen());
			stmt.setString(3, tv.getLoaiThanhVien());
			stmt.setString(4, tv.getEmail());
			stmt.setString(5, tv.getSdt());
			stmt.setString(6, tv.getTaiKhoan());
			stmt.setString(7, tv.getMatKhau());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	// sua thanh vien 
	public boolean update(ThanhVien tv) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update ThanhVien "
									+ "set HoTen =?, LoaiThanhVien =?, Email=?,"
									+ "Sdt=?, TaiKhoan=?, MatKhau=? "
									+ "where MaTV =?");
			stmt.setString(1, tv.getHoTen());
			stmt.setString(2, tv.getLoaiThanhVien());
			stmt.setString(3, tv.getEmail());
			stmt.setString(4, tv.getSdt());
			stmt.setString(5, tv.getTaiKhoan());
			stmt.setString(6, tv.getMatKhau());
			stmt.setString(7, tv.getMaTV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	//xoa thong tin - chi co nay danh cho tat ca cac loai thanh vien???
	public boolean delete(String maTV) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from ThanhVien where MaTV = ?");
			stmt.setString(1, maTV);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	//EndRegion
			
	//Region - Login & other fuctions
	// Kiem tra hoten, pass
	public int Check_ID(String taiKhoan, String pass, String loaiTV){
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from ThanhVien where TaiKhoan =? "
																+ "and MatKhau =? "
																+ "and LoaiThanhVien =?");
			stmt.setString(1, taiKhoan);
			stmt.setString(2, pass);
			stmt.setString(3, loaiTV);
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

	// Kiem tra tai Khoan
	public int Check_name(String taiKhoan){
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from ThanhVien where TaiKhoan =?");
			stmt.setString(1, taiKhoan);
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
	
	// Kiem tra pass
	public int Check_pass(String pass){
		
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from ThanhVien where MatKhau =?");
			stmt.setString(1, pass);
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
	
	// Kiem tra loai thanh vien => xuat trang theo loai TV
	public String Retrieve(String taiKhoan) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String loaiTV="";
		try {
			stmt = con.prepareStatement("Select * from ThanhVien where TaiKhoan = ?");
			stmt.setString(1, taiKhoan);
			rs = stmt.executeQuery();
		
			while(rs.next()){
				loaiTV = rs.getString("LoaiThanhVien");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		
		return loaiTV;
	} 
	
	// Dem tai Khoan theo ...
	public int Count(String table){
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count=0;
		try {
			stmt = con.prepareStatement("Select COUNT(*) as row_count from "+table); //original ThanhVien
//			stmt.setString(1, loai);
			rs = stmt.executeQuery();
			
			rs.next();
			count = rs.getInt("row_count");
			rs.close();
				
//			while(rs.next()){
//				count++;
//			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return count;
	}	
	//EndRegion
	
}
