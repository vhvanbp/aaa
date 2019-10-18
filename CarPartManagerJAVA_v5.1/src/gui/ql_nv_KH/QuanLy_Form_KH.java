package gui.ql_nv_KH;

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

import bAL.KhachHangBAL;
import bAL.NhaCungCapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.KhachHang;
import entity.users.NhaCungCap;
import entity.users.ThanhVien;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import gui.nv_BaoCao.NhanVien_Form_BaoCao;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class QuanLy_Form_KH extends JFrame implements ActionListener {

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
	JButton btnXoaKH = new JButton("Xoa khach hang");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnSuaKH = new JButton("Sua Thong Tin khach hang");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnChiTietKH = new JButton("Xem chi tiet khach hang");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboKH = new JComboBox<String>();
	JButton btnThemKH = new JButton("Them khach hang");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private KhachHangBAL khBAL = new KhachHangBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách ncc lên bảng
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	// Tao dataModel
	String[] headers = {"Mã khách hàng", "Tênkhách hàng", "Email", "Loại xe đang dùng"};
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
					QuanLy_Form_KH window = new QuanLy_Form_KH();
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
	public QuanLy_Form_KH() {
		initialize();
	}
	
	public QuanLy_Form_KH(String ten) {
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
		
		btnChiTietKH.setBackground(Color.CYAN);
		btnChiTietKH.setBounds(16, 69, 189, 23);
		getFrame().getContentPane().add(btnChiTietKH);

		btnSuaKH.setBackground(Color.CYAN);
		btnSuaKH.setBounds(186, 103, 200, 23);
		getFrame().getContentPane().add(btnSuaKH);
		
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
		
		JLabel lblDanhSch = new JLabel("Danh sách khách hàng :");
		lblDanhSch.setBounds(6, 180, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		
		btnXoaKH.setBackground(Color.CYAN);
		btnXoaKH.setBounds(16, 103, 160, 23);
		frame.getContentPane().add(btnXoaKH);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(622, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
								int cboIndex = cboKH.getSelectedIndex();
								String selection[] = new String[]{"tv.MaTV","tv.HoTen","tv.Email","kh.LoaiXeDangDung"};
								
								stmt = con.prepareStatement("SELECT tv.MaTV as '"+ headers[0] + "'"
																	+ ", tv.HoTen as '"+ headers[1] + "'"
																	+ ", tv.Email as '"+ headers[2] + "'"
																	+ ", kh.LoaiXeDangDung as '"+ headers[3] + "'"
																	+ "FROM   KhachHang AS kh INNER JOIN "
																	+ "ThanhVien AS tv ON kh.MaTV = tv.MaTV "
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
				cboKH.setBounds(87, 16, 128, 22);
				panel.add(cboKH);
				

				cboKH.setModel(new DefaultComboBoxModel<String>(headers));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);

				btnThemKH.setBackground(Color.CYAN);
				btnThemKH.setBounds(215, 69, 171, 23);
				frame.getContentPane().add(btnThemKH);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(493, 30, 119, 43);
		
				frame.getContentPane().add(btnQuayLai);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(16, 137, 160, 23);
				
				frame.getContentPane().add(btnXuatExcel);
								
				//TODO - Register before perform action
				btnXoaKH.addActionListener(this);
				btnThemKH.addActionListener(this);
				btnSuaKH.addActionListener(this);
				btnChiTietKH.addActionListener(this);
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
			
			
			napDuLieuChoBang();
		}
		
	// nap DS
	private void napDuLieuChoBang() {
		List<ThanhVien> list = khBAL.getAll_KH();
		for (ThanhVien tv : list) {
			KhachHang kh = (KhachHang) tv;
			Object[] row = { kh.getMaTV(), kh.getHoTen(), kh.getEmail(), kh.getLoaiXeDangDung() };
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
		frame.setTitle("Quản lí khách hàng");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		int row;
		if(o.equals(btnXoaKH)){ //Xoa khach hang
			row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này?", 
																		"Title on Box", 
																		dialogButton);
				
				if(dialogResult == 0) {
					String matv = (String) table.getValueAt(row, 0);

					if(tvBAL.delete(matv)){
						dataModel.removeRow(row);
					}else
						JOptionPane.showMessageDialog(null, "Khách hàng này không xóa được");
				}
				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khách hàng để xóa!");
		}else if(o.equals(btnThemKH)){ //them thong tin khach hang
			frame.dispose();
			QuanLy_AddKH_Form ql_AddNCC = new QuanLy_AddKH_Form(get_Login_Text);
			ql_AddNCC.frm.setVisible(true);
		}else if(o.equals(btnSuaKH)){ //Sua thong tin khach hang
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNCC =	(String) table.getValueAt(row, 0);
				QuanLy_UpdateKH_Form ql_UpdateNCC = new QuanLy_UpdateKH_Form(get_Login_Text, maNCC);
				ql_UpdateNCC.frame.setVisible(true);
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khách hàng để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnChiTietKH)){ // Xem chi tiet khach hang
			row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maNCC =	(String) table.getValueAt(row, 0);
				QuanLy_DetailKH_Form ql_DetailNCC = new QuanLy_DetailKH_Form(get_Login_Text, maNCC);
				ql_DetailNCC.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khách hàng để xem!");
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("KhachHang");
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			NhanVien_Form_Main frmMain = new NhanVien_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
