package gui.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import gui.ncc_KhuyenMai.Ncc_Form_KM;
import gui.nv_BaoCao.NhanVien_Form_BaoCao;
import gui.nv_PhieuNhap.NhanVien_Form_PhieuNhap;
import gui.nv_PhieuXuat.NhanVien_Form_PhieuXuat;
import gui.ql_KM.QuanLy_Form_KM;
import gui.ql_TonKho.QuanLy_Form_Kho;
import gui.ql_nv_KH.QuanLy_Form_KH;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;
import gui.ql_nv_PhuTung.QuanLy_UpdatePT_Form;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class NhanVien_Form_Main extends JFrame implements ActionListener {

	//Region - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	/**
	 *@param Xóa thành viên
	 */
	JButton btnQLPhieuNhap = new JButton("Quan li phieu nhap");
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
	JButton btnTuVan = new JButton("Tu van khach hang");
	
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	/**
	 *@param nap loại bảng
	 */
	JButton btnQLPT = new JButton("Quan li phu tung");
	JButton btnQLPhieuXuat = new JButton("Quan li phieu xuat");
	private final JButton btnQLTK = new JButton("Quan li ton kho");
	JButton btnBaoCao = new JButton("Bao Cao");
	JButton btnQLKH = new JButton("Quan li khach hang");

	//EndRegion
	
	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_Form_Main window = new NhanVien_Form_Main();
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
	public NhanVien_Form_Main() {
		initialize();
	}
	
	public NhanVien_Form_Main(String ten) {
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
		getFrame().setBounds(100, 100, 443, 429);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(24, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNV = new JLabel("Nhân viên:");
		lblNV.setBounds(66, 44, 102, 14);
		getFrame().getContentPane().add(lblNV);
		
		btnTuVan.setBackground(Color.CYAN);
		btnTuVan.setBounds(24, 142, 177, 43);
		getFrame().getContentPane().add(btnTuVan);

		btnKM.setBackground(Color.CYAN);
		btnKM.setBounds(235, 142, 177, 43);
		getFrame().getContentPane().add(btnKM);
		
		
		btnQLPhieuNhap.setBackground(Color.CYAN);
		btnQLPhieuNhap.setBounds(235, 88, 177, 43);
		frame.getContentPane().add(btnQLPhieuNhap);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(122, 326, 175, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(178, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);	

				btnQLPT.setBounds(24, 88, 175, 43);
				btnQLPT.setBackground(Color.CYAN);
				frame.getContentPane().add(btnQLPT);
				
				btnQLKH.setBackground(Color.CYAN);
				btnQLKH.setBounds(24, 196, 177, 43);
				frame.getContentPane().add(btnQLKH);
				
				btnQLPhieuXuat.setBackground(Color.CYAN);
				btnQLPhieuXuat.setBounds(235, 196, 177, 43);
				frame.getContentPane().add(btnQLPhieuXuat);
				
				btnBaoCao.setBackground(Color.CYAN);
				btnBaoCao.setBounds(24, 250, 177, 43);
				frame.getContentPane().add(btnBaoCao);
				btnQLTK.setBackground(Color.CYAN);
				btnQLTK.setBounds(235, 250, 177, 43);
				
				frame.getContentPane().add(btnQLTK);
				
				btnQLPhieuNhap.addActionListener(this);
				btnKM.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnTuVan.addActionListener(this);
				btnQLPT.addActionListener(this);
				btnQLTK.addActionListener(this);
				btnBaoCao.addActionListener(this);
				btnQLPhieuXuat.addActionListener(this);
				btnQLKH.addActionListener(this);
				
	}
	//EndRegion
		
	//Region - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Giao diện nhân viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - event handler
	// TODO Chuc Nang button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnQLPT)){ //quan li phu tung
			frame.dispose();
			QuanLy_Form_PT qlPT = new QuanLy_Form_PT(get_Login_Text);
			qlPT.frame.setVisible(true);
		}else if(o.equals(btnQLPhieuNhap)){ //quan li phieu nhap
			frame.dispose();
			NhanVien_Form_PhieuNhap nvPN = new NhanVien_Form_PhieuNhap(get_Login_Text);
			nvPN.frame.setVisible(true);
		}else if(o.equals(btnTuVan)){ //tu van -> send mail

		}else if(o.equals(btnKM)){ // quan li khuyen mai
			frame.dispose();
			Ncc_Form_KM nccKM = new Ncc_Form_KM(get_Login_Text);
			nccKM.frame.setVisible(true);
		}else if(o.equals(btnQLPhieuXuat)){ // quan li phieu xuat (thay vi hoa don), 
											// xuat hang ben quan li phu tung
			frame.dispose();
			NhanVien_Form_PhieuXuat nvPx = new NhanVien_Form_PhieuXuat(get_Login_Text);
			nvPx.frame.setVisible(true);
		}else if(o.equals(btnQLTK)){ // quan li hoa don
			frame.dispose();
			QuanLy_Form_Kho qlKho = new QuanLy_Form_Kho(get_Login_Text);
			qlKho.frame.setVisible(true);
		}else if(o.equals(btnBaoCao)){ // quan li hoa don
			frame.dispose();
			NhanVien_Form_BaoCao nvBC = new NhanVien_Form_BaoCao(get_Login_Text);
			nvBC.frame.setVisible(true);
		}else if(o.equals(btnQLKH)){ // Dang Xuat
			frame.dispose();
			QuanLy_Form_KH Lg = new QuanLy_Form_KH();
			Lg.frame.setVisible(true);
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			frame.dispose();
			LoginGUI Lg = new LoginGUI();
			Lg.frame.setVisible(true);
		}
		
//		else if(o.equals(btnTuVan)){ // quan li Tu Van
//			
//		}
		
	}
}
