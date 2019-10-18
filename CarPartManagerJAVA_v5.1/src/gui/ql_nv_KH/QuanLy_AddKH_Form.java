package gui.ql_nv_KH;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.midi.ShortMessage;
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
import helpers.UI_Helpers;

public class QuanLy_AddKH_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	private JTextField txtTenTV;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnThem = new JButton("Them");
	/**
	 *@param Đăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtSoHangMua;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtLoaiTV;
	
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private KhachHangBAL khBAL = new KhachHangBAL();
	String loaiTV = "Khach hang"; // loai la mac dinh
	private JTextField txtLoaiXeDangDung;
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_AddKH_Form window = new QuanLy_AddKH_Form();
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
	public QuanLy_AddKH_Form() {
		initialize();
	}
	
	public QuanLy_AddKH_Form(String tenLogin) {
		get_Login_Text =tenLogin;
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
		getFrame().setBounds(100, 100, 658, 356);
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

		//TODO - need ICON
//		Image img = new ImageIcon(this.getClass().getResource("/add_icon.jpg")).getImage().getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(235, 241, 165, 43);
		getFrame().getContentPane().add(btnThem);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(464, 241, 165, 43);
		frm.getContentPane().add(btnDangXuat);
		
		JLabel lblNhpTn = new JLabel("Nhập tên nhà cung cấp:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(10, 133, 144, 14);
		frm.getContentPane().add(lblNhpTn);
		
		txtTenTV = new JTextField();
		txtTenTV.setBounds(164, 130, 161, 20);
		frm.getContentPane().add(txtTenTV);
		txtTenTV.setColumns(10);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);
				
				JLabel lblSoHang = new JLabel("Nhập số hàng mua:");
				lblSoHang.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSoHang.setBounds(335, 164, 123, 14);
				frm.getContentPane().add(lblSoHang);
				
				txtSoHangMua = new JTextField();
				txtSoHangMua.setColumns(10);
				txtSoHangMua.setBounds(468, 161, 161, 20);
				frm.getContentPane().add(txtSoHangMua);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(20, 241, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpEmail = new JLabel("Nhập email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(10, 164, 144, 14);
				frm.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(164, 161, 161, 20);
				frm.getContentPane().add(txtEmail);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(468, 99, 161, 20);
				frm.getContentPane().add(txtSDT);
				
				JLabel lblSdt = new JLabel("Nhập số điện thoại:");
				lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSdt.setBounds(346, 102, 112, 14);
				frm.getContentPane().add(lblSdt);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(10, 99, 144, 23);
				frm.getContentPane().add(lblLoaiTV);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setColumns(10);
				txtLoaiTV.setBounds(164, 99, 161, 20);
				frm.getContentPane().add(txtLoaiTV);
				
				//TODO - Register before perform action
				btnThem.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				txtLoaiTV.setText(loaiTV);
				
				txtLoaiXeDangDung = new JTextField();
				txtLoaiXeDangDung.setColumns(10);
				txtLoaiXeDangDung.setBounds(468, 133, 161, 20);
				frm.getContentPane().add(txtLoaiXeDangDung);
				
				JLabel lblLoaiXeDung = new JLabel("Nhập xe đang dùng:");
				lblLoaiXeDung.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiXeDung.setBounds(346, 136, 112, 14);
				frm.getContentPane().add(lblLoaiXeDung);
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	//xoa rong
	private void xoaRong_Txt(){
		txtTenTV.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTenTV.requestFocus();
		txtSoHangMua.setText("");
		txtLoaiXeDangDung.setText("");
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Thêm khách hàng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		
		if(o.equals(btnThem)){ //Them thong tin khach hang
			//Region - get text from ui
			int newID = UI_Helpers.addNewID("ThanhVien"); //tao index tiep theo cho list

			String mancc = "TV" + newID;
			String tenTV =	txtTenTV.getText();
			String email =	txtEmail.getText();
			String sdt =	txtSDT.getText();
			String taiKhoan =	"";
			String password =	"";
			String mua = txtSoHangMua.getText();
			String loaiXeDung = txtLoaiXeDangDung.getText();
			//EndRegion
			
			//Region - function create with condition
			if( tenTV.equals("") 	|| email.equals("") 	|| sdt.equals("")
			|| mua.equals("")
			|| loaiXeDung.equals(""))
				
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( tvBAL.Check_name(taiKhoan)==1)
//				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				//Region - make conversion when data is available
				int soHangMua = Integer.parseInt(mua);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng này?"
																	, "Title on Box"
																	, dialogButton);
				if(dialogResult == 0) {
					KhachHang kh = new KhachHang(mancc, tenTV, loaiTV, email, 
													sdt, taiKhoan, password, soHangMua, loaiXeDung);
					if(khBAL.create(kh)){
						JOptionPane.showMessageDialog(null, "Bạn vừa thêm khách hàng!");
						
						UI_Helpers.updateNewID("ThanhVien", newID);
						
						frm.dispose();
						QuanLy_Form_KH qlKH = new QuanLy_Form_KH(get_Login_Text);
						qlKH.frame.setVisible(true);
					}
				} else {
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa rỗng để nhập lại?",
							"Title on Box", dialogButton);
					if (dialogResult == 0) {
						xoaRong_Txt();
					}
				}
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frm);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			QuanLy_Form_KH qlNCC = new QuanLy_Form_KH(get_Login_Text);
			qlNCC.frame.setVisible(true);
		}
		
	}
}
