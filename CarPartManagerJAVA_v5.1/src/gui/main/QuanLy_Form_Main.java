package gui.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dAL.DataBase;
import dAL.DbUtils;
import gui.ad_QLV.Admin_Form_QLV;
import gui.ql_KM.QuanLy_Form_KM;
import gui.ql_LoaiPhuTung.QuanLy_Form_LoaiPT;
import gui.ql_NCC.QuanLy_Form_NCC;
import gui.ql_NhanVien.QuanLy_Form_NV;
import gui.ql_PhuTungTon.Quanly_Form_PTTon;
import gui.ql_TKTT.Quanly_Form_TKTT;
import gui.ql_TonKho.QuanLy_Form_Kho;
import gui.ql_nv_KH.QuanLy_Form_KH;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;

public class QuanLy_Form_Main extends JFrame implements ActionListener {

	//Region - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	/**
	 *@param Xóa thành viên
	 */
	JButton btnQLKH = new JButton("Quan li khach hang");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnKM = new JButton("Chuong trinh khuyen mai");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnQLNV = new JButton("Quan li nhan vien");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	/**
	 *@param nap loại bảng
	 */
	JButton btnQLPT = new JButton("Quan li phu tung");
	JButton btnQLNCC = new JButton("Quan li nha cung cap");
	JButton btnQL_LPT = new JButton("Quan li loai phu tung");
	private final JButton btnQLTK = new JButton("Quan li ton kho");
	JButton btnTruyCapNV = new JButton("Truy cap chuc nang nhan vien");
	JButton btnQLV = new JButton("Quan li quan li vien");
	private final JButton btnTKTT = new JButton("Ton kho trong thang");

	//EndRegion
	
	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_Form_Main window = new QuanLy_Form_Main();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//EndRegion

	//Region - UI components
	/**
	 * Create the application.
	 */
	public QuanLy_Form_Main() {
		initialize();
	}
	
	public QuanLy_Form_Main(String ten) {
		get_Login_Text =ten;
		lblTenlogin.setText(get_Login_Text);
		
		initialize();
	}
	//EndRegion

	//Region - init components
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
		getFrame().setBounds(100, 100, 545, 476);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(24, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		lblNgiQunL.setBounds(66, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);
		
		btnQLNV.setBackground(Color.CYAN);
		btnQLNV.setBounds(285, 89, 209, 43);
		getFrame().getContentPane().add(btnQLNV);

		btnKM.setBackground(Color.CYAN);
		btnKM.setBounds(39, 197, 209, 43);
		getFrame().getContentPane().add(btnKM);
		
		
		btnQLKH.setBackground(Color.CYAN);
		btnQLKH.setBounds(285, 143, 209, 43);
		frame.getContentPane().add(btnQLKH);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(157, 359, 209, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(178, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);

				btnQLPT.setBounds(39, 89, 209, 43);
				btnQLPT.setBackground(Color.CYAN);
				frame.getContentPane().add(btnQLPT);

				btnQLNCC.setBackground(Color.CYAN);
				btnQLNCC.setBounds(285, 197, 209, 43);
				frame.getContentPane().add(btnQLNCC);
				
				btnQL_LPT.setBackground(Color.CYAN);
				btnQL_LPT.setBounds(39, 143, 209, 43);
				frame.getContentPane().add(btnQL_LPT);
				btnQLTK.setBackground(Color.CYAN);
				btnQLTK.setBounds(39, 251, 209, 43);
				
				frame.getContentPane().add(btnQLTK);
				
				btnTruyCapNV.setBackground(Color.CYAN);
				btnTruyCapNV.setBounds(285, 251, 209, 43);
				frame.getContentPane().add(btnTruyCapNV);
				
				btnQLV.setBackground(Color.CYAN);
				btnQLV.setBounds(39, 305, 209, 43);
				frame.getContentPane().add(btnQLV);
				btnTKTT.setBackground(Color.CYAN);
				btnTKTT.setBounds(285, 305, 209, 43);
				
				frame.getContentPane().add(btnTKTT);
				
				btnQLKH.addActionListener(this);
				btnKM.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQLNV.addActionListener(this);
				btnQLPT.addActionListener(this);
				btnQL_LPT.addActionListener(this);
				btnQLNCC.addActionListener(this);
				btnQLTK.addActionListener(this);
				btnTruyCapNV.addActionListener(this);
				btnQLV.addActionListener(this);
				btnTKTT.addActionListener(this);
			
	}
	//EndRegion
		
	//Region - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Giao diện quản lí");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - event handler
	// TODO Chuc Nang button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		String ms = get_Login_Text;
		
		if(o.equals(btnQLPT)){ // quan li phu tung
			frame.dispose();
			QuanLy_Form_PT qlPT = new QuanLy_Form_PT(ms);
			qlPT.frame.setVisible(true);
		}else if(o.equals(btnQL_LPT)){ // quan li loai phu tung
			frame.dispose();
			QuanLy_Form_LoaiPT qlLPT = new QuanLy_Form_LoaiPT(ms);
			qlLPT.frame.setVisible(true);
		}else if(o.equals(btnQLNV)){ // quan li nhan vien
			frame.dispose();
			QuanLy_Form_NV qlNV = new QuanLy_Form_NV(ms);
			qlNV.frame.setVisible(true);
		}else if(o.equals(btnQLKH)){ //quan li KH
			frame.dispose();
			QuanLy_Form_KH qlKH = new QuanLy_Form_KH(ms);
			qlKH.frame.setVisible(true);
		}else if(o.equals(btnQLNCC)){ //quan li nha cung cap
			frame.dispose();
			QuanLy_Form_NCC qlNCC = new QuanLy_Form_NCC(ms);
			qlNCC.frame.setVisible(true);
		}else if(o.equals(btnKM)){ //quan li khuyen mai
			frame.dispose();
			QuanLy_Form_KM qlNCC = new QuanLy_Form_KM(ms);
			qlNCC.frame.setVisible(true);
		}else if(o.equals(btnQLTK)){ //quan li khuyen mai
			frame.dispose();
			QuanLy_Form_Kho qlKho = new QuanLy_Form_Kho(ms);
			qlKho.frame.setVisible(true);
		}else if(o.equals(btnTruyCapNV)){ //quan li cac chuc nang cua nhan vien
			frame.dispose();
			NhanVien_Form_Main nvMain = new NhanVien_Form_Main(ms);
			nvMain.frame.setVisible(true);
		}else if(o.equals(btnQLV)){ //quan li khuyen mai
			frame.dispose();
			Admin_Form_QLV qlKho = new Admin_Form_QLV(ms);
			qlKho.frame.setVisible(true);
		}else if(o.equals(btnTKTT)){ //ton trong thang
			frame.dispose();
			Quanly_Form_TKTT qltktt = new Quanly_Form_TKTT(ms);
			qltktt.frame.setVisible(true);
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			frame.dispose();
			LoginGUI Lg = new LoginGUI();
			Lg.frame.setVisible(true);
		}
//		else if(o.equals(btnTestTest)){ // test
//			frame.dispose();
//			Form_Test tt = new Form_Test();
//			tt.frame.setVisible(true);
//		}
		
	}
}
