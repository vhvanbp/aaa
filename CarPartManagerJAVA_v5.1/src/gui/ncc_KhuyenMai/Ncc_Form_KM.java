package gui.ncc_KhuyenMai;

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

import bAL.KhuyenMaiBAL;
import bAL.PhuTungBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.KhuyenMai;
import gui.main.NCC_Form_Main;
import gui.main.NhanVien_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class Ncc_Form_KM extends JFrame implements ActionListener {

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
	JButton btnXoaKM = new JButton("Xoa khuyen mai");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnSuaKM = new JButton("Sua Thong Tin khuyen mai");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnChiTietKM = new JButton("Xem chi tiet khuyen mai");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboKM = new JComboBox<String>();
	JButton btnThemKM = new JButton("Them khuyen mai");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private KhuyenMaiBAL kmBAL = new KhuyenMaiBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách nv lên bảng;
	private PhuTungBAL ptBAL = new PhuTungBAL();
	// Tao dataModel
	String[] headers = {"Mã khuyến mãi", "Tên khuyến mãi"
						, "Phụ tùng", "Nhà cung cấp", "Giá tiền"};	
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ncc_Form_KM window = new Ncc_Form_KM();
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
	public Ncc_Form_KM() {
		initialize();
	}
	
	public Ncc_Form_KM(String ten) {
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
		
		JLabel lblNgiQunL = new JLabel(" Nhân viên:");
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);
		
		btnChiTietKM.setBackground(Color.CYAN);
		btnChiTietKM.setBounds(16, 69, 177, 23);
		getFrame().getContentPane().add(btnChiTietKM);

		btnSuaKM.setBackground(Color.CYAN);
		btnSuaKM.setBounds(170, 103, 193, 23);
		getFrame().getContentPane().add(btnSuaKM);
		
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
		
		JLabel lblDanhSch = new JLabel("Danh sách khuyến mãi :");
		lblDanhSch.setBounds(6, 180, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		
		btnXoaKM.setBackground(Color.CYAN);
		btnXoaKM.setBounds(16, 103, 144, 23);
		frame.getContentPane().add(btnXoaKM);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(622, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
								int cboIndex = cboKM.getSelectedIndex();
								String selection[] = new String[]{"KM.MaKM","KM.TenKM","PT.TenPT","TV.HoTen"};
								
								stmt = con.prepareStatement("SELECT "+selection[0]+" as '"+ headers[0] + "'"
															   + ", "+selection[1]+" as '"+ headers[1] + "'"
															   + ", "+selection[2]+" as '"+ headers[2] + "'"
															   + ", "+selection[3]+" as '"+ headers[3] + "'"
															   + "FROM   KhuyenMai AS KM INNER JOIN "
															   + "PhuTung AS PT ON KM.MaPT = PT.MaPT INNER JOIN "
															   + "NhaCungCap AS NCC ON KM.MaNCC = NCC.MaTV AND "
															   + "PT.MaNCC = NCC.MaTV INNER JOIN "
															   + "ThanhVien AS TV ON NCC.MaTV = TV.MaTV "
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
				cboKM.setBounds(87, 16, 128, 22);
				panel.add(cboKM);
				
				
				cboKM.setModel(new DefaultComboBoxModel<String>(headers));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);
				
				btnThemKM.setBackground(Color.CYAN);
				btnThemKM.setBounds(203, 69, 160, 23);
				frame.getContentPane().add(btnThemKM);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(493, 30, 119, 43);
				
				frame.getContentPane().add(btnQuayLai);
								
				//TODO - Register before perform action
				btnXoaKM.addActionListener(this);
				btnThemKM.addActionListener(this);
				btnSuaKM.addActionListener(this);
				btnChiTietKM.addActionListener(this);
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
		 Runnable napDuLieuChoBang = () -> {
			 List<KhuyenMai> list = kmBAL.getAll_KM();
			 String tenpt = "", tenncc = "";
				for (KhuyenMai km : list) {
					tenpt = kmBAL.RetrievePT(km.getMaPT());	// get loaiPT by mapt
					tenncc = ptBAL.RetrieveNCC(km.getMaNCC());	// get ncc by ma ncc
					
					Object[] row = { km.getMaKM(), km.getTenKM()
									, tenpt, tenncc, km.getGiaTienKM()};
					dataModel.addRow(row);
				}
	        };
	     napDuLieuChoBang.run();
	        
//		napDuLieuChoBang();
	}
	
	// nap DS
//	private void napDuLieuChoBang() {
//		List<KhuyenMai> list = nvBal.getAll_NV();
//		for (ThanhVien tv : list) {
//			NhanVien nv = (NhanVien) tv;
//			Object[] row = { nv.getMaTV(), nv.getHoTen(), nv.getNgayVaoLam(), nv.getMucLuong(),
//					nv.getTienLuong() + " $" };
//			dataModel.addRow(row);
//		}
//	}
		
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Quản lí khuyến mãi");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		if(o.equals(btnXoaKM)){ //Xoa khuyen mai
			int row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khuyến mãi này?", 
																		"Title on Box", 
																		dialogButton);
				if(dialogResult == 0) {
					String makm = (String) table.getValueAt(row, 0);

					if(kmBAL.delete(makm)){
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "xóa thành công!");
					}else
						JOptionPane.showMessageDialog(null, "Khuyến mãi này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khuyến mãi để xóa!");
		}else if(o.equals(btnThemKM)){ //them thong tin khuyen mai
			frame.dispose();
			Ncc_AddKM_Form ql_AddKM = new Ncc_AddKM_Form(get_Login_Text);
			ql_AddKM.frm.setVisible(true);
		}else if(o.equals(btnSuaKM)){ //Sua thong tin khuyen mai
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String makm =	(String) table.getValueAt(row, 0);
				Ncc_UpdateKM_Form ncc_UpdateKM = new Ncc_UpdateKM_Form(get_Login_Text,makm);
				ncc_UpdateKM.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khuyến mãi để sửa!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnChiTietKM)){ // Xem chi tiet khuyen mai
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String makm =	(String) table.getValueAt(row, 0);
				Ncc_DetailKM_Form ncc_DetailKM = new Ncc_DetailKM_Form(get_Login_Text, makm);
				ncc_DetailKM.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn khuyến mãi để xem!");
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			NhanVien_Form_Main frmNCC_Main = new NhanVien_Form_Main(get_Login_Text);
			frmNCC_Main.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
