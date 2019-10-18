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
import gui.ql_NhanVien.QuanLy_Form_NV;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;
import gui.test.Form_Test;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class NCC_Form_Main extends JFrame implements ActionListener {

	//Region - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnQLKM = new JButton("Quan li khuyen mai");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	JButton btnQLYC_Nhap = new JButton("Quan li yeu cau nhap");
	//EndRegion
	
	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NCC_Form_Main window = new NCC_Form_Main();
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
	public NCC_Form_Main() {
		initialize();
	}
	
	public NCC_Form_Main(String ten) {
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
		getFrame().setBounds(100, 100, 360, 402);
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

		btnQLKM.setBackground(Color.CYAN);
		btnQLKM.setBounds(76, 79, 178, 43);
		getFrame().getContentPane().add(btnQLKM);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(76, 233, 178, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(178, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				btnQLKM.addActionListener(this);
				btnDangXuat.addActionListener(this);

				btnQLYC_Nhap.setBackground(Color.CYAN);
				btnQLYC_Nhap.setBounds(76, 156, 178, 43);
				frame.getContentPane().add(btnQLYC_Nhap);
	}
	//EndRegion
		
	//Region - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Giao diện nhà cung cấp");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - event handler
	// TODO Chuc Nang button
	@Override
	public void actionPerformed(ActionEvent arg0) { // NOTE: change form constructor LATER
		Object o = arg0.getSource();
		String ms = get_Login_Text;
		
		if(o.equals(btnQLYC_Nhap)){ //quan li yeu cau nhap phu tung

		}else if(o.equals(btnQLKM)){ //quan li khuyen mai
			frame.dispose();
			Ncc_Form_KM ncc_KM = new Ncc_Form_KM(ms);
			ncc_KM.frame.setVisible(true);
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			frame.dispose();
			LoginGUI Lg = new LoginGUI();
			Lg.frame.setVisible(true);
		}
		
	}
	//EndRegion
}
