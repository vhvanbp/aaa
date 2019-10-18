package gui.ql_NhanVien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bAL.NhanVienBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.NhanVien;
import entity.users.ThanhVien;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class QuanLy_Form_NV extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTable table;
	/**
	 *@param Xóa thành viên
	 */
	JButton btnXoaNV = new JButton("Xoa nhan vien");
	/**
	 *@param Sửa Thông Tin nhan vien
	 */
	JButton btnSuaNV = new JButton("Sua Thong Tin nhan vien");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem chi tiet nhan vien
	 */
	JButton btnChiTietNV = new JButton("Xem chi tiet nhan vien");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboNV = new JComboBox<String>();
	JButton btnThemNV = new JButton("Them nhan vien");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private NhanVienBAL nvBAL;
	// Tao dataModel
	String[] headers = {"Mã nhân viên", "Tên nhân viên", "Ngày vào làm"
						, "Mức lương", "Lương"};	
	private final JButton btnXuatExcel = new JButton("Xuat bao cao");
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_Form_NV window = new QuanLy_Form_NV();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//EndRegion

	//Region - TODO - UI components
	/**
	 * Create the application.
	 */
	public QuanLy_Form_NV() {
		initialize();
	}
	
	public QuanLy_Form_NV(String ten) {
		get_Login_Text =ten;
		lblTenlogin.setText(get_Login_Text);
		
		initialize();
	}
	//EndRegion

	//Region - TODO - init components
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO close DB
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DbUtils.close(DataBase.getConnection());
				System.exit(0);
			}
		});
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 765, 477);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);
		
		btnChiTietNV.setBackground(Color.CYAN);
		btnChiTietNV.setBounds(16, 69, 177, 23);
		getFrame().getContentPane().add(btnChiTietNV);

		btnSuaNV.setBackground(Color.CYAN);
		btnSuaNV.setBounds(186, 103, 177, 23);
		getFrame().getContentPane().add(btnSuaNV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 214, 735, 214);
		getFrame().getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 TV", "H\u1ECD T\u00EAn", "Ti\u1EC3u ban"
			}
		));
		
		JLabel lblDanhSch = new JLabel("Danh sách nhân viên :");
		lblDanhSch.setBounds(6, 180, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		
		btnXoaNV.setBackground(Color.CYAN);
		btnXoaNV.setBounds(16, 103, 160, 23);
		frame.getContentPane().add(btnXoaNV);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(622, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(496, 93, 253, 76);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
				txtTim = new JTextField();
				txtTim.setBounds(87, 49, 160, 20);
				panel.add(txtTim);
				//TODO Chuc nang Tim Kiem
				txtTim.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						if(txtTim.getText().equalsIgnoreCase("")){
							createTable();
						}else {	
							Connection con = DataBase.getConnection();
							PreparedStatement stmt = null;
							
							try {
								int cboIndex = cboNV.getSelectedIndex();
								String selection[] = new String[]{"tv.MaTV","tv.HoTen","nv.MucLuong"};
								
								stmt = con.prepareStatement("SELECT tv.MaTV as '"+ headers[0] + "'"
																	+ ", tv.HoTen as '"+ headers[1] + "'"
																	+ ", nv.NgayVaoLam as '"+ headers[2] + "'"
																	+ ", nv.MucLuong as '"+ headers[3] + "'"
																	+ ", nv.TienLuong  as '"+ headers[4] + "' "
																	+ "FROM   NhanVien AS nv INNER JOIN "
																	+ "ThanhVien AS tv ON nv.MaTV = tv.MaTV "
																	+ "where "+selection[cboIndex]+" = ?");
								stmt.setString(1, txtTim.getText());
								ResultSet rs = stmt.executeQuery();
								
								table.setModel(DbUtils.resultSetToTableModel(rs));
								
								stmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
					}
				});
				txtTim.setColumns(10);
				cboNV.setBounds(87, 16, 128, 22);
				panel.add(cboNV);
				

				cboNV.setModel(new DefaultComboBoxModel<String>(new String[] {"Ma nhan vien", "Ten nhan vien", "Muc luong"}));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);
				
				btnThemNV.setBackground(Color.CYAN);
				btnThemNV.setBounds(203, 69, 160, 23);
				frame.getContentPane().add(btnThemNV);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(493, 30, 119, 43);
				
				frame.getContentPane().add(btnQuayLai);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(16, 137, 160, 23);
				
				frame.getContentPane().add(btnXuatExcel);
								
				//TODO - Register before perform action
				btnXoaNV.addActionListener(this);
				btnThemNV.addActionListener(this);
				btnSuaNV.addActionListener(this);
				btnChiTietNV.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXuatExcel.addActionListener(this);
				
				// methods from support region
				createTable();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// tao bang tu DS 
	private void createTable() {
		// Tao dataModel & table 
		dataModel = new DefaultTableModel(headers, 0);
		table.setModel(dataModel);
		
		nvBAL = new NhanVienBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách nv lên bảng
		napDuLieuChoBang();
	}
	// nap DS
	private void napDuLieuChoBang() {
		List<ThanhVien> list = nvBAL.getAll_NV();
		for (ThanhVien tv : list) {
			NhanVien nv = (NhanVien) tv;
			Object[] row = { nv.getMaTV(), nv.getHoTen(), nv.getNgayVaoLam(), nv.getMucLuong(),
					nv.getTienLuong() + " $" };
			dataModel.addRow(row);
		}
	}
		
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Quản lí nhân viên");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
//		int countNV_ByType = tvBal.Count("ThanhVien"); // dem thanh vien

		if(o.equals(btnXoaNV)){ //Xoa nhan vien
			int row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này?", 
																		"Title on Box", 
																		dialogButton);
				if(dialogResult == 0) {
					String matv = (String) table.getValueAt(row, 0);

					if(tvBAL.delete(matv)){
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "xóa thành công!");
					}else
						JOptionPane.showMessageDialog(null, "Nhân viên này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhân viên để xóa!");
		}else if(o.equals(btnThemNV)){ //them thong tin nhan vien
			frame.dispose();
			QuanLy_AddNV_Form ql_AddNV = new QuanLy_AddNV_Form(get_Login_Text);
			ql_AddNV.frm.setVisible(true);
		}else if(o.equals(btnSuaNV)){ //Sua thong tin nhan vien
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNV =	(String) table.getValueAt(row, 0);
				QuanLy_UpdateNV_Form ql_UpdatNV = new QuanLy_UpdateNV_Form(get_Login_Text,maNV);
				ql_UpdatNV.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhân viên để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnChiTietNV)){ // Xem chi tiet nhan vien
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNV =	(String) table.getValueAt(row, 0);
				QuanLy_DetailNV_Form ql_DetailNV = new QuanLy_DetailNV_Form(get_Login_Text, maNV);
				ql_DetailNV.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhân viên để xem!");
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("NhanVien");
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}
		//Region - for test func
//		else if(o.equals(btnTestCountAcc)){ // Test acc -> delete later
//			JOptionPane.showMessageDialog(null, "count thanh vien: " + countNV_ByType);
//			int newC = countNV_ByType + 1;
//			String newTV = "TV"+ newC;
//			JOptionPane.showMessageDialog(null, "next thanh vien is: " + newTV);
//		}else if(o.equals(btnTestRow)){ // Test row -> delete later
//			int row = table.getSelectedRow();
//			String tennv =	(String) table.getValueAt(row, 1);
//			JOptionPane.showMessageDialog(null, "row: " + row + " - name:" + tennv);
//		}
		//EndRegion

	}
	//EndRegion
	
}
