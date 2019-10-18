package gui.ql_TonKho;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.LoaiPhuTungBAL;
import bAL.NhanVienBAL;
import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.TonKho;
import entity.users.NhanVien;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class QuanLy_UpdateKho_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	/**
	 *@param Sửa Thông Tin loai phu tung
	 */
	JButton btnSua = new JButton("Sua");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	
	private String get_maKhu_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtSLTD;
	private JTextField txtMaKhu;
	private JTextField txtSL;
	
	private TonKho tk;
	private TonKhoBAL tkBAL = new TonKhoBAL();
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	QuanLy_Form_Kho qlKho = new QuanLy_Form_Kho(get_Login_Text);
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_UpdateKho_Form window = new QuanLy_UpdateKho_Form();
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
	public QuanLy_UpdateKho_Form() {
		initialize();
	}

	public QuanLy_UpdateKho_Form(String tenLogin, String makhu) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maKhu_Text = makhu;
		
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
		getFrame().setBounds(100, 100, 427, 441);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		//TODO - change lbl name by User
		if (tvBAL.Retrieve(get_Login_Text).equals("Quan li")) {
			lblNgiQunL = new JLabel(" Người Quản Lý:");
		} else if (tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")) {
			lblNgiQunL = new JLabel(" Nhân viên:");
		}
				
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(127, 205, 165, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(127, 336, 166, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(126, 269, 166, 43);
				frame.getContentPane().add(btnQuayLai);
				
				txtSLTD = new JTextField();
				txtSLTD.setColumns(10);
				txtSLTD.setBounds(132, 153, 161, 31);
				frame.getContentPane().add(txtSLTD);
				
				JLabel lblSLTD = new JLabel("Số lượng tối đa:");
				lblSLTD.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSLTD.setBounds(10, 161, 112, 14);
				frame.getContentPane().add(lblSLTD);
				
				txtMaKhu = new JTextField();
				txtMaKhu.setEditable(false);
				txtMaKhu.setColumns(10);
				txtMaKhu.setBounds(132, 69, 161, 31);
				frame.getContentPane().add(txtMaKhu);
				
				JLabel lblMaKhu = new JLabel("Mã khu:");
				lblMaKhu.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaKhu.setBounds(10, 77, 112, 14);
				frame.getContentPane().add(lblMaKhu);
				
				txtSL = new JTextField();
				txtSL.setText((String) null);
				txtSL.setEditable(false);
				txtSL.setColumns(10);
				txtSL.setBounds(132, 111, 161, 31);
				frame.getContentPane().add(txtSL);
				
				JLabel lblSL = new JLabel("Số lượng:");
				lblSL.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSL.setBounds(10, 119, 112, 14);
				frame.getContentPane().add(lblSL);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layKhu_tuForm();
				napKhu_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layKhu_tuForm() {
		 tk = tkBAL.get_KhuByID(get_maKhu_Text);
	}

	// nap du lieu tu bang vao Textfield
	private void napKhu_VaoTextfields() {
		txtMaKhu.setText(get_maKhu_Text);
		txtSL.setText(tk.getSoLuong()+"");
		txtSLTD.setText(tk.getSoLuongToiDa()+"");
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa khu vực");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin TV

			//Region - get text from ui
			String sltd = txtSLTD.getText();

			//EndRegion

			//Region - function create with condition
			if( sltd.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( loaiptBAL.Check_name(tenlpt)==1 && !tenlpt.equals(lpt.getLoaiPT()))
//				JOptionPane.showMessageDialog(null, "Loại phụ tùng " + tenlpt + " đã tồn tại, hãy nhập loại khác");
			else{
				//Region - make conversion when data is available
				int soLuongToiDa = Integer.parseInt(sltd);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin khu vực này?"
																	, "Title on Box"
																	, dialogButton);
				
				if(dialogResult == 0) {
					TonKho khuNew = new TonKho(tk.getMaKhu(), tk.getSoLuong(), soLuongToiDa);
					
					 if(tkBAL.update(khuNew)){
						 JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin khu vực!");
							
						frame.dispose();
						QuanLy_Form_Kho qlNV = new QuanLy_Form_Kho(get_Login_Text);
						qlNV.frame.setVisible(true);
					 }  
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napKhu_VaoTextfields();
					}
				} 			
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			QuanLy_Form_Kho frmNV = new QuanLy_Form_Kho(get_Login_Text);
			frmNV.frame.setVisible(true);
		}
	}
	//EndRegion
	
}
