package gui.ql_NCC;

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

import bAL.NhaCungCapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.NhaCungCap;
import entity.users.ThanhVien;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class QuanLy_Form_NCC extends JFrame implements ActionListener {

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
	JButton btnXoaNCC = new JButton("Xoa nha cung cap");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnSuaNCC = new JButton("Sua Thong Tin nha cung cap");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnChiTietNCC = new JButton("Xem chi tiet nha cung cap");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboNCC = new JComboBox<String>();
	JButton btnThemNCC = new JButton("Them nha cung cap");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private NhaCungCapBAL nccBAL;
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	// Tao dataModel
	String[] headers = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Email", "Công ty"};
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
					QuanLy_Form_NCC window = new QuanLy_Form_NCC();
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
	public QuanLy_Form_NCC() {
		initialize();
	}
	
	public QuanLy_Form_NCC(String ten) {
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
		
		btnChiTietNCC.setBackground(Color.CYAN);
		btnChiTietNCC.setBounds(16, 69, 189, 23);
		getFrame().getContentPane().add(btnChiTietNCC);

		btnSuaNCC.setBackground(Color.CYAN);
		btnSuaNCC.setBounds(186, 103, 200, 23);
		getFrame().getContentPane().add(btnSuaNCC);
		
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
		
		
		btnXoaNCC.setBackground(Color.CYAN);
		btnXoaNCC.setBounds(16, 103, 160, 23);
		frame.getContentPane().add(btnXoaNCC);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(622, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm nhà cung cấp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
								int cboIndex = cboNCC.getSelectedIndex();
								String selection[] = new String[]{"tv.MaTV","tv.HoTen","tv.Email","ncc.CongTy"};
								
								stmt = con.prepareStatement("SELECT tv.MaTV as '"+ headers[0] + "'"
																	+ ", tv.HoTen as '"+ headers[1] + "'"
																	+ ", tv.Email as '"+ headers[2] + "'"
																	+ ", ncc.CongTy as '"+ headers[3] + "'"
																	+ "FROM   NhaCungCap AS ncc INNER JOIN "
																	+ "ThanhVien AS tv ON ncc.MaTV = tv.MaTV "
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
				cboNCC.setBounds(87, 16, 160, 22);
				panel.add(cboNCC);
				

				cboNCC.setModel(new DefaultComboBoxModel<String>(new String[] {"Ma nha cung cap", "Ten nha cung cap", "Email", "Cong ty"}));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);

				btnThemNCC.setBackground(Color.CYAN);
				btnThemNCC.setBounds(215, 69, 171, 23);
				frame.getContentPane().add(btnThemNCC);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(493, 30, 119, 43);
		
				frame.getContentPane().add(btnQuayLai);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(16, 137, 160, 23);
				
				frame.getContentPane().add(btnXuatExcel);
								
				//TODO - Register before perform action
				btnXoaNCC.addActionListener(this);
				btnThemNCC.addActionListener(this);
				btnSuaNCC.addActionListener(this);
				btnChiTietNCC.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXuatExcel.addActionListener(this);
				
				// methods from support region
				createTable();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	//TODO Cac phuong thuc ho tro
	// tao bang tu DS 
		private void createTable() {
			// Tao dataModel & table 
			dataModel = new DefaultTableModel(headers, 0);
			table.setModel(dataModel);
			
			nccBAL = new NhaCungCapBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách ncc lên bảng
			napDuLieuChoBang();
		}
		
	// nap DS
	private void napDuLieuChoBang() {
		List<ThanhVien> list = nccBAL.getAll_NCC();
		for (ThanhVien tv : list) {
			NhaCungCap ncc = (NhaCungCap) tv;
			Object[] row = { ncc.getMaTV(), ncc.getHoTen(), ncc.getEmail(), ncc.getCongTy() };
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
		frame.setTitle("Quản lí nhà cung cấp");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		int row;
		if(o.equals(btnXoaNCC)){ //Xoa nha cung cap
			row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhà cung cấp này?", 
																		"Title on Box", 
																		dialogButton);
				
				if(dialogResult == 0) {
					String matv = (String) table.getValueAt(row, 0);

					if(tvBAL.delete(matv)){
						dataModel.removeRow(row);
					}else
						JOptionPane.showMessageDialog(null, "Nhà cung cấp này không xóa được");
				}
				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhà cung cấp để xóa!");
		}else if(o.equals(btnThemNCC)){ //them thong tin nha cung cap
			frame.dispose();
			QuanLy_AddNCC_Form ql_AddNCC = new QuanLy_AddNCC_Form(get_Login_Text);
			ql_AddNCC.frm.setVisible(true);
		}else if(o.equals(btnSuaNCC)){ //Sua thong tin nha cung cap
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNCC =	(String) table.getValueAt(row, 0);
				QuanLy_UpdateNCC_Form ql_UpdateNCC = new QuanLy_UpdateNCC_Form(get_Login_Text, maNCC);
				ql_UpdateNCC.frame.setVisible(true);
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhà cung cấp để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnChiTietNCC)){ // Xem chi tiet nha cung cap
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNCC =	(String) table.getValueAt(row, 0);
				QuanLy_DetailNCC_Form ql_DetailNCC = new QuanLy_DetailNCC_Form(get_Login_Text, maNCC);
				ql_DetailNCC.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn nhà cung cấp để xem!");
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("NhaCungCap");
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
