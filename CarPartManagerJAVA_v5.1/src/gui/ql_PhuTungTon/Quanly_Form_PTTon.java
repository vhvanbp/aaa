package gui.ql_PhuTungTon;

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

import bAL.LoaiPhuTungBAL;
import bAL.PhieuNhapBAL;
import bAL.PhieuXuatBAL;
import bAL.PhuTungTonBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhieuXuat;
import entity.carPart.PhuTungTon;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import gui.ql_TKTT.Quanly_Form_TKTT;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class Quanly_Form_PTTon extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTable table;
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboLPT = new JComboBox<String>();
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private PhuTungTonBAL pttBAL = new PhuTungTonBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách lên bảng
	// Tao dataModel
	String[] headers = {"Mã phụ tùng", "Số lượng ban dầu", "Số lượng tồn", "Tháng", "Năm"};	
	String[] headersTim = {"Mã phụ tùng", "Tên phụ tùng", "Khu"};	
	private final JButton btnXuatExcel = new JButton("Xuat bao cao");
	private int get_thangTon_Text;
	private int get_namTon_Text;
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quanly_Form_PTTon window = new Quanly_Form_PTTon();
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
	public Quanly_Form_PTTon() {
		initialize();
	}
	
	public Quanly_Form_PTTon(String ten) {
		get_Login_Text =ten;
		lblTenlogin.setText(get_Login_Text);
		
		initialize();
	}
	
	public Quanly_Form_PTTon(String ten, int thangTon, int namTon) {
		get_Login_Text =ten;
		lblTenlogin.setText(get_Login_Text);
		get_thangTon_Text  = thangTon;
		get_namTon_Text = namTon;
		
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
		getFrame().setBounds(100, 100, 685, 478);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người quản lí:");
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 659, 214);
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
		
		JLabel lblDanhSch = new JLabel("Danh sách phụ tùng tồn kho :");
		lblDanhSch.setBounds(10, 190, 188, 23);
		frame.getContentPane().add(lblDanhSch);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(506, 11, 163, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm phụ tùng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(444, 137, 225, 76);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
				txtTim = new JTextField();
				txtTim.setBounds(87, 49, 128, 20);
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
								int cboIndex = cboLPT.getSelectedIndex();
								String selection[] = new String[]{"MaPT","TenPT","MaKhu"};
								
								stmt = con.prepareStatement("SELECT PhuTungTon.MaPT as '"+ headers[0] + "'"
																	+ ", SoLuongBanDau as '"+ headers[1] + "'"
																	+ ", SoLuongHienTai as '"+ headers[2] + "'"
																	+ ", ThangNhap as '"+ headers[3] + "'"
																	+ ", NamNhap as '"+ headers[4] + "'"
																	+ "FROM PhuTungTon INNER JOIN "
																	+ "PhuTung ON PhuTungTon.MaPT = PhuTung.MaPT " 
																	+ "where PhuTungTon."+selection[cboIndex]+" = ?");
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
				cboLPT.setBounds(87, 16, 128, 22);
				panel.add(cboLPT);
				

				cboLPT.setModel(new DefaultComboBoxModel<String>(headersTim));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(506, 69, 163, 43);
				
				frame.getContentPane().add(btnQuayLai);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(10, 122, 163, 43);
				
				frame.getContentPane().add(btnXuatExcel);
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
		
		napDuLieuChoBang();
	}
	// nap DS
	private void napDuLieuChoBang() {
		List<PhuTungTon> list = pttBAL.getAll_PTT(get_thangTon_Text, get_namTon_Text);
		for (PhuTungTon px : list) {
			Object[] row = { px.getMaPT(), px.getSoLuongBanDau(), px.getSoLuongHienTai()
							, px.getThangNhap(), px.getNamNhap()};
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
		frame.setTitle("Quản lí phụ tùng tồn");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("PhuTungTon", "ThangNhap", "NamNhap"
									, get_thangTon_Text, get_namTon_Text);
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			Quanly_Form_TKTT frmMain = new Quanly_Form_TKTT(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
