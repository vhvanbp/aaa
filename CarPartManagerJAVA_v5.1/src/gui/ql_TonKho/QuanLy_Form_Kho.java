package gui.ql_TonKho;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.TonKho;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class QuanLy_Form_Kho extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTable table;
	/**
	 *@param Sửa Thông Tin loai phu tung
	 */
	JButton btnSuaKhu = new JButton("Mo rong khu vuc");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JButton btnThemKhu = new JButton("Them khu vuc");
	private final JButton btnQuayLai = new JButton("Quay lai");
	JButton btnXoaKhuVuc = new JButton("Xoa khu vuc");
	JButton btnDenQLPT = new JButton("Quan li phu tung");

	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private TonKhoBAL tonkhoBAL = new TonKhoBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách lên bảng

	// Tao dataModel
	String[] headers = {"Khu vực", "Tồn kho", "Số lượng tối đa"};	
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
					QuanLy_Form_Kho window = new QuanLy_Form_Kho();
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
	public QuanLy_Form_Kho() {
		initialize();
	}
	
	public QuanLy_Form_Kho(String ten) {
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
		getFrame().setBounds(100, 100, 685, 515);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		//TODO - change lbl name by User
		if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
			lblNgiQunL = new JLabel(" Người Quản Lý:");	
		}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
			lblNgiQunL = new JLabel(" Nhân viên:");	
		}
		
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		btnSuaKhu.setBackground(Color.CYAN);
		btnSuaKhu.setBounds(164, 72, 140, 43);
		getFrame().getContentPane().add(btnSuaKhu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 275, 659, 200);
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
		
		JLabel lblDanhSch = new JLabel("Danh sách khu vực :");
		lblDanhSch.setBounds(10, 242, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(521, 30, 144, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm khu vực", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(412, 208, 253, 56);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
				txtTim = new JTextField();
				txtTim.setBounds(82, 17, 160, 20);
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
								
								stmt = con.prepareStatement("SELECT MaKhu as '"+ headers[0] + "'"
																	+ ", SoLuong as '"+ headers[1] + "'"
																	+ ", SoLuongToiDa as '"+ headers[2] + "'"
																	+ "FROM TonKho "
																	+ "where MaKhu = ?");
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
				
				JLabel lblTmTheo = new JLabel("khu:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);
				
				btnThemKhu.setBackground(Color.CYAN);
				btnThemKhu.setBounds(10, 72, 144, 43);
				frame.getContentPane().add(btnThemKhu);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(521, 84, 144, 43);
				
				frame.getContentPane().add(btnQuayLai);
				frame.getContentPane().add(btnXoaKhuVuc);
				
				btnXoaKhuVuc.setBackground(Color.CYAN);
				btnXoaKhuVuc.setBounds(10, 126, 144, 43);
				
				btnDenQLPT.setBackground(Color.CYAN);
				btnDenQLPT.setBounds(164, 126, 140, 43);
				frame.getContentPane().add(btnDenQLPT);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(10, 180, 144, 43);
				
				frame.getContentPane().add(btnXuatExcel);
				btnThemKhu.addActionListener(this);
				btnSuaKhu.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnDenQLPT.addActionListener(this);
				btnXoaKhuVuc.addActionListener(this);
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
		
		napDuLieuChoBang();
	}
	// nap DS
	private void napDuLieuChoBang() {
		List<TonKho> list = tonkhoBAL.getAll_Khu();
		for (TonKho tk : list) {
			Object[] row = { tk.getMaKhu(), tk.getSoLuong(), tk.getSoLuongToiDa()};
			// add trang thai ...
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
		frame.setTitle("Quản lí tồn kho");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnXoaKhuVuc)){ //Xoa khu
			int row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khu vực này?", 
																		"Title on Box", 
																		dialogButton);
				if(dialogResult == 0) {
					String makhu = (String) table.getValueAt(row, 0);

					if(tonkhoBAL.delete(makhu)){
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "xóa thành công!");
					}else
						JOptionPane.showMessageDialog(null, "Khu vực này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khu vực để xóa!");
		}else if(o.equals(btnThemKhu)){ //them thong tin khu
			frame.dispose();
			QuanLy_AddKho_Form ql_AddKhu = new QuanLy_AddKho_Form(get_Login_Text);
			ql_AddKhu.frm.setVisible(true);
		}else if(o.equals(btnSuaKhu)){ //Sua thong tin khu
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maKhu =	(String) table.getValueAt(row, 0);
				QuanLy_UpdateKho_Form ql_UpdatKhu = new QuanLy_UpdateKho_Form(get_Login_Text,maKhu);
				ql_UpdatKhu.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khu vực để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
				QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
				frmMain.frame.setVisible(true);				
			}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
				NhanVien_Form_Main frmMain = new NhanVien_Form_Main(get_Login_Text);
				frmMain.frame.setVisible(true);	
			}
		}else if(o.equals(btnDenQLPT)){ // Quay lai form pt
			frame.dispose();
			QuanLy_Form_PT frmPT = new QuanLy_Form_PT(get_Login_Text);
			frmPT.frame.setVisible(true);				
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("TonKho");
		}

	}
}
