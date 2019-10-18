package gui.ql_TKTT;

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
import bAL.TonKhoTrongThangBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhieuXuat;
import entity.carPart.PhuTungTon;
import entity.carPart.TonKhoTrongThang;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import gui.ql_PhuTungTon.Quanly_Form_PTTon;
import gui.ql_nv_PhuTung.QuanLy_DetailPT_Form;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class Quanly_Form_TKTT extends JFrame implements ActionListener {

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
	
	private TonKhoTrongThangBAL tkttBAL = new TonKhoTrongThangBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách lên bảng
	// Tao dataModel
	String[] headers = {"Mã tồn", "Tổng nhập", "Tổng hàng tồn", "Tổng xuất", "Tổng chi phí", "Tháng", "Năm"};	
	private final JButton btnXuatExcel = new JButton("Xuat bao cao");
	JButton btnXemPTtktt = new JButton("Xem phu tung ton kho");
	String[] headersTim = {"Mã tồn", "Tháng", "Năm"};	
	
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quanly_Form_TKTT window = new Quanly_Form_TKTT();
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
	public Quanly_Form_TKTT() {
		initialize();
	}
	
	public Quanly_Form_TKTT(String ten) {
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
		
		JLabel lblDanhSch = new JLabel("Danh sách tồn kho tháng:");
		lblDanhSch.setBounds(10, 190, 188, 23);
		frame.getContentPane().add(lblDanhSch);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(506, 11, 163, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm tồn kho", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
								String selection[] = new String[]{"MaTon","ThangTon","NamTon"};
								
								stmt = con.prepareStatement("SELECT MaTon as '"+ headers[0] + "'"
																	+ ", TongSLNhap as '"+ headers[1] + "'"
																	+ ", TongSLTon as '"+ headers[2] + "'"
																	+ ", TongSLXuat as '"+ headers[3] + "'"
																	+ ", TongChiPhiXuat as '"+ headers[4] + "'"
																	+ ", ThangTon as '"+ headers[5] + "'"
																	+ ", NamTon as '"+ headers[6] + "'"
																	+ "FROM TonKhoTrongThang "
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
				
				btnXemPTtktt.setBackground(Color.CYAN);
				btnXemPTtktt.setBounds(10, 69, 163, 43);
				frame.getContentPane().add(btnXemPTtktt);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXuatExcel.addActionListener(this);
				btnXemPTtktt.addActionListener(this);
				
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
		List<TonKhoTrongThang> list = tkttBAL.getAll_TKTT();
		for (TonKhoTrongThang px : list) {
			Object[] row = { px.getMaTon(), px.getTongSLNhap(), px.getTongSLTon()
							, px.getTongSLXuat(), px.getTongChiPhiXuat(), px.getThangTon()
							, px.getNamTon()};
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
		frame.setTitle("Quản lí tồn kho tháng");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("TonKhoTrongThang");
		}else if(o.equals(btnXemPTtktt)){ // xem phu tung ton
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				int thangTon = (Integer) table.getValueAt(row, 5);
				int namTon = (Integer) table.getValueAt(row, 6);
				
//				int thangTon = Integer.parseInt(thang);
//				int namTon = Integer.parseInt(nam);
				
				Quanly_Form_PTTon ql_PTT = new Quanly_Form_PTTon(get_Login_Text, thangTon, namTon);
				ql_PTT.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn tồn kho để xem!");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
}
