package gui.ad_QLV;

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

import bAL.QuanLiVienBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.QuanLiVien;
import entity.users.ThanhVien;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class Admin_Form_QLV extends JFrame implements ActionListener {

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
	JButton btnXoaQLV = new JButton("Xoa quan li");
	/**
	 *@param Sửa Thông Tin ql
	 */
	JButton btnSuaQLV = new JButton("Sua Thong Tin quan li");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnChiTietQLV = new JButton("Xem chi tiet quan li");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboQLV = new JComboBox<String>();
	JButton btnThemQLV = new JButton("Them quan li");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private QuanLiVienBAL qlvBAL;
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	// Tao dataModel
	String[] headers = {"Mã quản lí", "Tên quản lí", "Email", "Mô tả quan lí"};
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Form_QLV window = new Admin_Form_QLV();
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
	public Admin_Form_QLV() {
		initialize();
	}
	
	public Admin_Form_QLV(String ten) {
		get_Login_Text = ten;
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
		
		btnChiTietQLV.setBackground(Color.CYAN);
		btnChiTietQLV.setBounds(16, 69, 176, 23);
		getFrame().getContentPane().add(btnChiTietQLV);

		btnSuaQLV.setBackground(Color.CYAN);
		btnSuaQLV.setBounds(170, 103, 194, 23);
		getFrame().getContentPane().add(btnSuaQLV);
		
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
		
		
		btnXoaQLV.setBackground(Color.CYAN);
		btnXoaQLV.setBounds(16, 103, 144, 23);
		frame.getContentPane().add(btnXoaQLV);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(622, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm quản lí", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(496, 93, 253, 76);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
				//TODO Chuc nang Tim Kiem
				txtTim = new JTextField();
				txtTim.setBounds(87, 49, 160, 20);
				panel.add(txtTim);
				txtTim.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						if(txtTim.getText().equalsIgnoreCase("")){
							createTable();
						}else {	
							Connection con = DataBase.getConnection();
							PreparedStatement stmt = null;
							
							try {
								int cboIndex = cboQLV.getSelectedIndex();
								String selection[] = new String[]{"tv.MaTV","tv.HoTen","tv.Email","qlv.MoTaTV"};
								
								stmt = con.prepareStatement("SELECT tv.MaTV as '"+ headers[0] + "'"
																	+ ", tv.HoTen as '"+ headers[1] + "'"
																	+ ", tv.Email as '"+ headers[2] + "'"
																	+ ", qlv.MoTaTV as '"+ headers[3] + "'"
																	+ "FROM   QuanLiVien AS qlv INNER JOIN "
																	+ "ThanhVien AS tv ON qlv.MaTV = tv.MaTV "
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
				cboQLV.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã quản lí", "Tên quản lí", "Email", "Mô tả quan lí"}));
				cboQLV.setBounds(87, 16, 128, 22);
				panel.add(cboQLV);
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);

				btnThemQLV.setBackground(Color.CYAN);
				btnThemQLV.setBounds(202, 69, 163, 23);
				frame.getContentPane().add(btnThemQLV);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(493, 30, 119, 43);
		
				frame.getContentPane().add(btnQuayLai);
								
				//TODO - Register before perform action
				btnXoaQLV.addActionListener(this);
				btnThemQLV.addActionListener(this);
				btnSuaQLV.addActionListener(this);
				btnChiTietQLV.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
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
			
			qlvBAL = new QuanLiVienBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách ncc lên bảng
			napDuLieuChoBang();
		}
		
	// nap DS
	private void napDuLieuChoBang() {
		List<ThanhVien> list = qlvBAL.getAll_QLV();
		for (ThanhVien tv : list) {
			QuanLiVien qlv = (QuanLiVien) tv;
			Object[] row = { qlv.getMaTV(), qlv.getHoTen(), qlv.getEmail(), qlv.getMoTaQLV() };
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
		frame.setTitle("Quản lí quản lí viên");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		int row;
		if(o.equals(btnXoaQLV)){ //Xoa quan li vien
			row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa quản lí viên này?", 
																		"Title on Box", 
																		dialogButton);
				
				if(dialogResult == 0) {
					String matv = (String) table.getValueAt(row, 0);

					if(tvBAL.delete(matv)){
						dataModel.removeRow(row);
					}else
						JOptionPane.showMessageDialog(null, "Quản lí viên này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn quản lí viên để xóa!");
		}else if(o.equals(btnThemQLV)){ //them thong tin quan li vien
			frame.dispose();
			Admin_AddQLV_Form ad_AddQLV = new Admin_AddQLV_Form(get_Login_Text);
			ad_AddQLV.frm.setVisible(true);
		}else if(o.equals(btnSuaQLV)){ //Sua thong tin quan li vien
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maQLV =	(String) table.getValueAt(row, 0);
				Admin_UpdateQLV_Form ad_UpdateQLV = new Admin_UpdateQLV_Form(get_Login_Text, maQLV);
				ad_UpdateQLV.frame.setVisible(true);
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn quản lí viên để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnChiTietQLV)){ // Xem chi tiet nha cung cap
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maQLV =	(String) table.getValueAt(row, 0);
				Admin_DetailQLV_Form ad_DetailQLV = new Admin_DetailQLV_Form(get_Login_Text, maQLV);
				ad_DetailQLV.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn quản lí viên để xem!");
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
