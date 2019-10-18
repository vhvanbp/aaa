package gui.ql_nv_KH;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.KhachHangBAL;
import bAL.NhaCungCapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.KhachHang;
import entity.users.NhaCungCap;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class QuanLy_UpdateKH_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTextField txtMaTV;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnSua = new JButton("Sua");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtLoaiTV;
	
	private String get_maTV_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtTenTV;
	private JTextField txtEmail;
	private JTextField txtSHmua;
	private JTextField txtSDT;
	JTextField txtLoaiXeDangDung = new JTextField();

	private ThanhVien tv;
	private KhachHang kh;
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private KhachHangBAL khBAL = new KhachHangBAL();
	String loaiTV = "Khach hang";

	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_UpdateKH_Form window = new QuanLy_UpdateKH_Form();
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
	public QuanLy_UpdateKH_Form() {
		initialize();
	}
	
	public QuanLy_UpdateKH_Form(String tenLogin, String maTV) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maTV_Text = maTV;
		
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
		getFrame().setBounds(100, 100, 672, 350);
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

		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(231, 241, 160, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(475, 241, 160, 43);
		frame.getContentPane().add(btnDangXuat);
		
		txtMaTV = new JTextField();
		txtMaTV.setEditable(false);
		txtMaTV.setBounds(157, 69, 161, 20);
		frame.getContentPane().add(txtMaTV);
		txtMaTV.setColumns(10);
		
		JLabel lblMaTV = new JLabel("Mã thành viên:");
		lblMaTV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaTV.setBounds(24, 72, 124, 14);
		frame.getContentPane().add(lblMaTV);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(24, 100, 119, 23);
				frame.getContentPane().add(lblLoaiTV);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setBounds(157, 100, 161, 20);
				frame.getContentPane().add(txtLoaiTV);
				txtLoaiTV.setColumns(10);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(24, 241, 160, 43);
				frame.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpTn = new JLabel("Nhập tên nhà cung cấp:");
				lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTn.setBounds(10, 134, 137, 14);
				frame.getContentPane().add(lblNhpTn);
				
				txtTenTV = new JTextField();
				txtTenTV.setColumns(10);
				txtTenTV.setBounds(157, 131, 161, 20);
				frame.getContentPane().add(txtTenTV);
				
				JLabel lblNhpEmail = new JLabel("Nhập email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(24, 165, 124, 14);
				frame.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(157, 162, 161, 20);
				frame.getContentPane().add(txtEmail);
				
				JLabel lblSHmua = new JLabel("Nhập số hàng mua:");
				lblSHmua.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSHmua.setBounds(341, 168, 124, 14);
				frame.getContentPane().add(lblSHmua);
				
				txtSHmua = new JTextField();
				txtSHmua.setColumns(10);
				txtSHmua.setBounds(474, 165, 161, 20);
				frame.getContentPane().add(txtSHmua);
				
				txtSDT = new JTextField();
				txtSDT.setText((String) null);
				txtSDT.setColumns(10);
				txtSDT.setBounds(474, 100, 161, 20);
				frame.getContentPane().add(txtSDT);
				
				JLabel lblSDT = new JLabel("Nhập số điện thoại:");
				lblSDT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSDT.setBounds(347, 102, 117, 14);
				frame.getContentPane().add(lblSDT);
				
				JLabel lblLoaiXeDung = new JLabel("Nhập loại xe đang dùng:");
				lblLoaiXeDung.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiXeDung.setBounds(328, 137, 137, 14);
				frame.getContentPane().add(lblLoaiXeDung);
				
				txtLoaiXeDangDung.setText((String) null);
				txtLoaiXeDangDung.setColumns(10);
				txtLoaiXeDangDung.setBounds(474, 134, 161, 20);
				frame.getContentPane().add(txtLoaiXeDangDung);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
		
				// methods from support region
				layNCC_tuForm();
				napTV_VaoTextfields();
	}
	//EndRegion
	
	//Region - TODO - support methods
	private void layNCC_tuForm() {
		 tv = khBAL.get_KHByID(get_maTV_Text);
		 kh = (KhachHang)tv;
	}
		
	// nap du lieu tu bang vao Textfield
	private void napTV_VaoTextfields() {
		txtMaTV.setText(get_maTV_Text);
		txtTenTV.setText(kh.getHoTen());
		txtLoaiTV.setText(kh.getLoaiThanhVien());
		txtEmail.setText(kh.getEmail());
		txtSDT.setText(kh.getSdt());
		txtSHmua.setText(kh.getSoHangMua()+"");
		txtLoaiXeDangDung.setText(kh.getLoaiXeDangDung());
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa khách hàng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin nha cung cap

			//Region - get text from ui
			String tenTV =	txtTenTV.getText();
			String email =	txtEmail.getText();
			String sdt =	txtSDT.getText();
			String taiKhoan =	"";
			String password =	"";
			String mua =	txtSHmua.getText();
			String loaixeDung =	txtLoaiXeDangDung.getText();
			//EndRegion

			//Region - function create with condition
			if( tenTV.equals("") 	|| email.equals("") 	|| sdt.equals("")
			|| mua.equals("")
			|| loaixeDung.equals("") )
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( tvBal.Check_name(taiKhoan)==1 && !taiKhoan.equals(tv.getTaiKhoan()))
//				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				//Region - make conversion when data is available
				int soHangMua = Integer.parseInt(mua);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin khách hàng này?"
						, "Title on Box"
						, dialogButton);
				
				if(dialogResult == 0) {
					KhachHang khNew = new KhachHang(tv.getMaTV(), tenTV, loaiTV, email
														, sdt, taiKhoan, password, soHangMua, loaixeDung);

					if (khBAL.update(khNew)) {
						JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin khách hàng!");

						frame.dispose();
						QuanLy_Form_KH qlKH = new QuanLy_Form_KH(get_Login_Text);
						qlKH.frame.setVisible(true);
					}
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napTV_VaoTextfields();						
					}
				} 		
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			QuanLy_Form_KH frmNCC = new QuanLy_Form_KH(get_Login_Text);
			frmNCC.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
