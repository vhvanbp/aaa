package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhuTungXe;

public class LoaiPhuTungBAL {
	//Region - Loai Phu tung CRUD
	//Lay thong tin tat ca loai phu tung
	public List<LoaiPhuTungXe> getAll_LoaiPT() {
		List<LoaiPhuTungXe> ds_LPT = new ArrayList<LoaiPhuTungXe>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaLoai, LoaiPT FROM LoaiPhuTung");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_LPT.add(new LoaiPhuTungXe(rs.getString("MaLoai"), rs.getString("LoaiPT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_LPT;
	}

	// Lay thong tin loai phu tung
	public LoaiPhuTungXe get_LoaiPTByID(String maLoaiPT) {
//		LoaiPhuTungXe pt = new LoaiPhuTungXe();
		LoaiPhuTungXe loaiPT = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaLoai, LoaiPT "
										+ "FROM LoaiPhuTung "
										+ "where MaLoai =?");
			stmt.setString(1, maLoaiPT); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				loaiPT = new LoaiPhuTungXe(rs.getString("MaLoai"), rs.getString("LoaiPT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return loaiPT;
	}
			
	// them loai phu tung
	public boolean create(LoaiPhuTungXe lpt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoaiPhuTung(MaLoai, LoaiPT) "
															+ "values(?, ?)");
					
			stmt.setString(1, lpt.getMaLoai());
			stmt.setString(2, lpt.getLoaiPT());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		

	// sua phu tung
	public boolean update(LoaiPhuTungXe lpt) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update LoaiPhuTung "
										+ "set LoaiPT =? "
										+ "where MaLoai =?");
			
			stmt.setString(1, lpt.getLoaiPT());
			stmt.setString(2, lpt.getMaLoai());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	
	//xoa thong tin 
	public boolean delete(String maloaiPT) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LoaiPhuTung where MaLoai = ?");
			stmt.setString(1, maloaiPT);
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
	// Kiem tra LPT
	public int Check_name(String lpt){ // refator later -> redundant code
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from LoaiPhuTung where LoaiPT =?");
			stmt.setString(1, lpt);
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
