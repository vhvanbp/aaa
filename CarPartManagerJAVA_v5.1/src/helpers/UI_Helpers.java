package helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import bAL.TableCountBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.others.TableCount;
import gui.main.LoginGUI;

public class UI_Helpers {
	static TableCountBAL tcBAL = new TableCountBAL();
	
	public static void DangXuat(JFrame frm){ // Dang Xuat
		frm.dispose();
		LoginGUI Lg = new LoginGUI();
		Lg.frame.setVisible(true);
	}
	
	public void QuayLai(JFrame frame, UI_frame form) { // Need an interface???
//		frm.dispose();
//		QuanLy_Form QL = new QuanLy_Form();
//		QL.frame.setVisible(true);
	}
	
	//count Table
	public static int addNewID(String table) {
		TableCount tc = tcBAL.get_TCByID(table);
		TableCount tcNew = new TableCount(table, tc.getQuantity() + 1);
		
		return tcNew.getQuantity();
	}
	
	//update after count Table
	public static void updateNewID(String table, int quanity) {
		TableCount newTC = new TableCount(table, quanity);
		
		if (tcBAL.update(newTC))
			System.out.println("new " + table + " id -> update success");
	}
	
	//xuat excel
	public static void toExcel(JTable table, File file){
	    try{
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter(file);

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ 
	    	System.out.println(e); 
	    }
	}
	
	//xuat tu table ra excel 
	public static JTable createDataForTable(String table) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		JTable tableToExcel = new JTable();

		try {
			stmt = con.prepareStatement("select * from " + table);
			ResultSet rs = stmt.executeQuery();

			tableToExcel.setModel(DbUtils.resultSetToTableModel(rs));

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tableToExcel;
	}
	
	//xuat tu table ra excel + choose path
	public static void exportToExcel(String table) {
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(fc);
        if(option == JFileChooser.APPROVE_OPTION){
            String filename = fc.getSelectedFile().getName(); 
            String path = fc.getSelectedFile().getParentFile().getPath();

			int len = filename.length();
			String ext = "";
			String file = "";

			if(len > 4){
				ext = filename.substring(len-4, len);
			}

			if(ext.equals(".xls")){
				file = path + "\\" + filename; 
			}else{
				file = path + "\\" + filename + ".xls"; 
			}
			toExcel(createDataForTable(table), new File(file));
		}
        
//		File fi = new File("F:\\test excel data\\test2.xls");
//		toExcel(createDataForTable(table),fi);
	}
	
	// excel funcs (overload)
	//xuat tu table ra excel 
	public static JTable createDataForTable(String table, String column, String value) {
		Connection con = DataBase.getConnection();
		PreparedStatement stmt = null;
		JTable tableToExcel = new JTable();

		try {
			stmt = con.prepareStatement("select * from " + table
										+ " where " + column + " = ?");
			stmt.setString(1, value);
			ResultSet rs = stmt.executeQuery();

			tableToExcel.setModel(DbUtils.resultSetToTableModel(rs));

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tableToExcel;
	}
	
	//xuat tu table ra excel + choose path 
	public static void exportToExcel(String table, String column, String value) {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(fc);
		if (option == JFileChooser.APPROVE_OPTION) {
			String filename = fc.getSelectedFile().getName();
			String path = fc.getSelectedFile().getParentFile().getPath();

			int len = filename.length();
			String ext = "";
			String file = "";

			if (len > 4) {
				ext = filename.substring(len - 4, len);
			}

			if (ext.equals(".xls")) {
				file = path + "\\" + filename;
			} else {
				file = path + "\\" + filename + ".xls";
			}
			toExcel(createDataForTable(table, column, value) , new File(file));
		}
	}
	
	
	//xuat tu table ra excel -- rieng cho phu tung ton
		public static JTable createDataForTable(String table, String column, String column2, int thang, int nam) {
			Connection con = DataBase.getConnection();
			PreparedStatement stmt = null;
			JTable tableToExcel = new JTable();

			try {
				stmt = con.prepareStatement("select * from " + table
											+ " where " + column + " = ? "
												+ "and " + column2 + " = ? ");
				stmt.setInt(1, thang);
				stmt.setInt(2, nam);
				
				ResultSet rs = stmt.executeQuery();

				tableToExcel.setModel(DbUtils.resultSetToTableModel(rs));

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return tableToExcel;
		}
		
		//xuat tu table ra excel + choose path 
		public static void exportToExcel(String table, String column, String column2, int thang, int nam) {
			JFileChooser fc = new JFileChooser();
			int option = fc.showSaveDialog(fc);
			if (option == JFileChooser.APPROVE_OPTION) {
				String filename = fc.getSelectedFile().getName();
				String path = fc.getSelectedFile().getParentFile().getPath();

				int len = filename.length();
				String ext = "";
				String file = "";

				if (len > 4) {
					ext = filename.substring(len - 4, len);
				}

				if (ext.equals(".xls")) {
					file = path + "\\" + filename;
				} else {
					file = path + "\\" + filename + ".xls";
				}
				toExcel(createDataForTable(table, column, column2, thang, nam) , new File(file));
			}
		}
}
