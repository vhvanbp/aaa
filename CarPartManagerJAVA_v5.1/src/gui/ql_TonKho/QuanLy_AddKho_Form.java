package gui.ql_TonKho;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.TonKho;
import helpers.UI_Helpers;

public class QuanLy_AddKho_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	private JTextField txtSLTD;
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnThem = new JButton("Them");
	/**
	 *@param Đăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JButton btnQuayLai = new JButton("Quay lai");
	
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
					QuanLy_AddKho_Form window = new QuanLy_AddKho_Form();
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
	public QuanLy_AddKho_Form() {
		initialize();
	}
	
	public QuanLy_AddKho_Form(String tenLogin) {
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
		getFrame().setBounds(100, 100, 438, 351);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		//TODO - change lbl name by User
		if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
			lblNgiQunL = new JLabel(" Người Quản Lý:");	
		}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
			lblNgiQunL = new JLabel(" Nhân viên:");	
		}
		
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		//TODO - need ICON
//		Image img = new ImageIcon(this.getClass().getResource("/add_icon.jpg")).getImage().getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(153, 121, 165, 43);
		getFrame().getContentPane().add(btnThem);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(153, 252, 165, 43);
		frm.getContentPane().add(btnDangXuat);
		
		JLabel lblNhpSLTD = new JLabel("Nhập sô lượng tối đa:");
		lblNhpSLTD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpSLTD.setBounds(14, 77, 129, 14);
		frm.getContentPane().add(lblNhpSLTD);
		
		txtSLTD = new JTextField();
		txtSLTD.setBounds(153, 69, 161, 31);
		frm.getContentPane().add(txtSLTD);
		txtSLTD.setColumns(10);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(153, 186, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				//TODO - Register before perform action
				btnThem.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	//xoa rong
	private void xoaRong_Txt(){
		txtSLTD.setText("");
		txtSLTD.requestFocus();
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Thêm khu vực");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		if(o.equals(btnThem)){ //Them thong tin loai phu tung
			//Region - get text from ui
			int newID = UI_Helpers.addNewID("TonKho"); //tao index tiep theo cho list
			String maKhu = "K" + newID;
			String sltd =	txtSLTD.getText();

			//EndRegion
			
			//Region - function create with condition
			if( sltd.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
//			else if( loaiptBAL.Check_name(tenKhu)==1 )
//				JOptionPane.showMessageDialog(null, "Loại phụ tùng " + tenKhu + " đã tồn tại, hãy nhập loại khác");
			else{
				//Region - make conversion when data is available
				int soLuongToiDa = Integer.parseInt(sltd);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khu vực này?"
																	, "Title on Box"
																	, dialogButton);
					
				if(dialogResult == 0) {
					TonKho khuNew = new TonKho(maKhu,0, soLuongToiDa);
						
					if(tkBAL.create(khuNew)){
						JOptionPane.showMessageDialog(null, "Bạn vừa thêm khu vực!");
						
						UI_Helpers.updateNewID("TonKho", newID);
						
						frm.dispose();
						qlKho = new QuanLy_Form_Kho(get_Login_Text);
						qlKho.frame.setVisible(true);
					}
				}else{
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa rỗng để nhập lại?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 0) {
						xoaRong_Txt();
					}
				}
			}
			//EndRegion
		
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frm);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			qlKho.frame.setVisible(true);
		}
	}
	//EndRegion
}
