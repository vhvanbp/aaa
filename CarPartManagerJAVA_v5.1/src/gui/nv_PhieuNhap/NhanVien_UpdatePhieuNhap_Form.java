package gui.nv_PhieuNhap;

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
import bAL.PhieuNhapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.users.NhanVien;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class NhanVien_UpdatePhieuNhap_Form extends JFrame implements ActionListener {

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
	
	private String get_maPT_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtMaPT;
	private final JLabel label = new JLabel("Nhập ngày:");
	private final JTextField txtDay = new JTextField();
	private final JLabel label_1 = new JLabel("/");
	private final JTextField txtMonth = new JTextField();
	private final JLabel label_2 = new JLabel("/");
	private final JTextField txtYear = new JTextField();
	private final JTextField txtSL = new JTextField();
	private final JLabel label_3 = new JLabel("Nhập số lượng:");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	private PhieuNhap pn;
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_UpdatePhieuNhap_Form window = new NhanVien_UpdatePhieuNhap_Form();
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
	public NhanVien_UpdatePhieuNhap_Form() {
		initialize();
	}

	public NhanVien_UpdatePhieuNhap_Form(String tenLogin, String maPT) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maPT_Text = maPT;
		
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
		getFrame().setBounds(100, 100, 574, 322);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Nhân viên:");
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(198, 197, 165, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(392, 197, 166, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(10, 197, 166, 43);
				frame.getContentPane().add(btnQuayLai);
				
				txtMaPT = new JTextField();
				txtMaPT.setEditable(false);
				txtMaPT.setColumns(10);
				txtMaPT.setBounds(132, 69, 161, 24);
				frame.getContentPane().add(txtMaPT);
				
				JLabel lblMaLPT = new JLabel("Mã phiếu nhập:");
				lblMaLPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaLPT.setBounds(10, 77, 112, 14);
				frame.getContentPane().add(lblMaLPT);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setBounds(10, 141, 112, 14);
				
				frame.getContentPane().add(label);
				txtDay.setColumns(10);
				txtDay.setBounds(132, 138, 30, 20);
				
				frame.getContentPane().add(txtDay);
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_1.setBounds(161, 138, 12, 21);
				
				frame.getContentPane().add(label_1);
				txtMonth.setColumns(10);
				txtMonth.setBounds(183, 138, 30, 20);
				
				frame.getContentPane().add(txtMonth);
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_2.setBounds(212, 138, 12, 21);
				
				frame.getContentPane().add(label_2);
				txtYear.setColumns(10);
				txtYear.setBounds(234, 138, 59, 20);
				
				frame.getContentPane().add(txtYear);
				txtSL.setColumns(10);
				txtSL.setBounds(132, 104, 161, 24);
				
				frame.getContentPane().add(txtSL);
				label_3.setHorizontalAlignment(SwingConstants.RIGHT);
				label_3.setBounds(14, 109, 112, 14);
				
				frame.getContentPane().add(label_3);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layPN_tuForm();
				napPN_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layPN_tuForm() {
		 pn = pnBAL.get_PhieuNhapByID(get_maPT_Text);
	}

	// nap du lieu tu bang vao Textfield
	private void napPN_VaoTextfields() {
		txtMaPT.setText(get_maPT_Text);
		txtSL.setText(pn.getSoLuongNhap()+"");
		Calendar cal = Calendar.getInstance(); // get day, month, year
			cal.setTime(pn.getNgayNhapHang());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar) bat dau tu 0
			int day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay.setText(day + "");
		txtMonth.setText(month  + "");
		txtYear.setText(year + "");
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa phiếu nhập");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin TV

			//Region - get text from ui
			String sl =	txtSL.getText();
			String day =	txtDay.getText();
			String month =	txtMonth.getText();
			String year =	txtYear.getText();
			//EndRegion

			//Region - function create with condition
			if(  day.equals("") || month.equals("") || year.equals("") || sl.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( loaiptBAL.Check_name(tenlpt)==1 && !tenlpt.equals(lpt.getLoaiPT()))
//				JOptionPane.showMessageDialog(null, "Loại phụ tùng " + tenlpt + " đã tồn tại, hãy nhập loại khác");
			else{
				//Region - make conversion when data is available
				int soLuong = Integer.parseInt(sl);

				String ngay_Nhap = day + "/" + month + "/" + year;
				Date ngayNhapPhieu=null;
				try {
					ngayNhapPhieu = dateformat.parse(ngay_Nhap);
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Bạn chưa điền ... - error" + e);
				}
				
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin phiếu nhập này?"
																	, "Title on Box"
																	, dialogButton);
				
				if(dialogResult == 0) {
					PhieuNhap pnNew = new PhieuNhap(get_maPT_Text, soLuong, ngayNhapPhieu);
					
					 if(pnBAL.update(pnNew)){
						 JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin phiếu nhập!");
							
						frame.dispose();
						NhanVien_Form_PhieuNhap qlNV = new NhanVien_Form_PhieuNhap(get_Login_Text);
						qlNV.frame.setVisible(true);
					 }  
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napPN_VaoTextfields();
					}
				} 			
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			NhanVien_Form_PhieuNhap frmNV = new NhanVien_Form_PhieuNhap(get_Login_Text);
			frmNV.frame.setVisible(true);
		}
	}
	//EndRegion
	
}
