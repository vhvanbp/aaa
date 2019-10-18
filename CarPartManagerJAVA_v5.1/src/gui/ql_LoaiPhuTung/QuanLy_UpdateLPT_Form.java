package gui.ql_LoaiPhuTung;

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
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.users.NhanVien;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class QuanLy_UpdateLPT_Form extends JFrame implements ActionListener {

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
	
	private String get_maLPT_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtLPT;
	private JTextField txtMaLPT;
	
	private LoaiPhuTungXe lpt;
	private LoaiPhuTungBAL loaiptBAL = new LoaiPhuTungBAL();
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_UpdateLPT_Form window = new QuanLy_UpdateLPT_Form();
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
	public QuanLy_UpdateLPT_Form() {
		initialize();
	}

	public QuanLy_UpdateLPT_Form(String tenLogin, String maLPT) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maLPT_Text = maLPT;
		
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
		getFrame().setBounds(100, 100, 427, 408);
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
		btnSua.setBounds(132, 172, 165, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(132, 303, 166, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(131, 236, 166, 43);
				frame.getContentPane().add(btnQuayLai);
				
				txtLPT = new JTextField();
				txtLPT.setColumns(10);
				txtLPT.setBounds(132, 120, 161, 31);
				frame.getContentPane().add(txtLPT);
				
				JLabel lblLPT = new JLabel("Loại phụ tùng:");
				lblLPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLPT.setBounds(10, 128, 112, 14);
				frame.getContentPane().add(lblLPT);
				
				txtMaLPT = new JTextField();
				txtMaLPT.setEditable(false);
				txtMaLPT.setColumns(10);
				txtMaLPT.setBounds(132, 69, 161, 31);
				frame.getContentPane().add(txtMaLPT);
				
				JLabel lblMaLPT = new JLabel("Mã phụ tùng:");
				lblMaLPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaLPT.setBounds(10, 77, 112, 14);
				frame.getContentPane().add(lblMaLPT);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layLPT_tuForm();
				napLPT_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layLPT_tuForm() {
		 lpt = loaiptBAL.get_LoaiPTByID(get_maLPT_Text);
	}

	// nap du lieu tu bang vao Textfield
	private void napLPT_VaoTextfields() {
		txtMaLPT.setText(get_maLPT_Text);
		txtLPT.setText(lpt.getLoaiPT());
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa loại phụ tùng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin TV

			//Region - get text from ui
			String tenlpt =	txtLPT.getText();

			//EndRegion

			//Region - function create with condition
			if( tenlpt.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
			else if( loaiptBAL.Check_name(tenlpt)==1 && !tenlpt.equals(lpt.getLoaiPT()))
				JOptionPane.showMessageDialog(null, "Loại phụ tùng " + tenlpt + " đã tồn tại, hãy nhập loại khác");
			else{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin loại phụ tùng này?"
																	, "Title on Box"
																	, dialogButton);
				
				if(dialogResult == 0) {
					LoaiPhuTungXe lptNew = new LoaiPhuTungXe(lpt.getMaLoai(), tenlpt);
					
					 if(loaiptBAL.update(lptNew)){
						 JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin loại phụ tùng!");
							
						frame.dispose();
						QuanLy_Form_LoaiPT qlNV = new QuanLy_Form_LoaiPT(get_Login_Text);
						qlNV.frame.setVisible(true);
					 }  
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napLPT_VaoTextfields();
					}
				} 			
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			QuanLy_Form_LoaiPT frmNV = new QuanLy_Form_LoaiPT(get_Login_Text);
			frmNV.frame.setVisible(true);
		}
	}
	//EndRegion
	
}
