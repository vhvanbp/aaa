package bAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.TonKho;

public class TonKhoBAL {
	//Region - Ton Kho CRUD
	//Lay thong tin tat ca ton kho
	public List<TonKho> getAll_Khu() {
		List<TonKho> ds_Khu = new ArrayList<TonKho>();
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaKhu, SoLuong, SoLuongToiDa FROM TonKho");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ds_Khu.add(new TonKho(rs.getString("MaKhu")
									, rs.getInt("SoLuong")
									, rs.getInt("SoLuongToiDa")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return ds_Khu;
	}

	// Lay thong tin ton kho
	public TonKho get_KhuByID(String maKhu) {
		TonKho tk = null;
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT MaKhu, SoLuong, SoLuongToiDa "
										+ "FROM TonKho "
										+ "where MaKhu =?");
			stmt.setString(1, maKhu); 
			rs = stmt.executeQuery();

			while (rs.next()) {
				tk = new TonKho(rs.getString("MaKhu")
								, rs.getInt("SoLuong")
								, rs.getInt("SoLuongToiDa"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, stmt);
		}
		return tk;
	}
			
	// them khu
	public boolean create(TonKho khu) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;

		int n = 0;
		try {
			stmt = con.prepareStatement("insert into TonKho(MaKhu, SoLuong, SoLuongToiDa) "
															+ "values(?, ?, ?)");
					
			stmt.setString(1, khu.getMaKhu());
			stmt.setInt(2, khu.getSoLuong());
			stmt.setInt(3, khu.getSoLuongToiDa());
						
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
		

	// sua khu
	public boolean update(TonKho khu) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update TonKho "
										+ "set SoLuong =?, SoLuongToiDa =? "
										+ "where MaKhu =?");
			
			stmt.setInt(1, khu.getSoLuong());
			stmt.setInt(2, khu.getSoLuongToiDa());
			stmt.setString(3, khu.getMaKhu());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	
	//xoa thong tin 
	public boolean delete(String maKhu) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from TonKho where MaKhu = ?");
			stmt.setString(1, maKhu);
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
