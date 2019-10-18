package gui.ncc_KhuyenMai;

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
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.KhuyenMaiBAL;
import bAL.NhaCungCapBAL;
import bAL.PhuTungBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.KhuyenMai;
import entity.carPart.PhuTungXe;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class Ncc_UpdateKM_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTextField txtMaKM;
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
	
	private String get_maKM_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JComboBox<String> cboPT = new JComboBox<String>();
	private JTextField txtTenKM;
	private JTextField txtMoTa;
	private JTextField txtStartDay;
	private JTextField txtStartMonth;
	private JTextField txtStartYear;
	private JTextField txtEndYear;
	private JTextField txtEndMonth;
	private JTextField txtEndDay;
	private JLabel lbl_sdayEnd;
	JLabel lblNCC = new JLabel("Nhà cung cấp:");
	private JTextField txtNCC;
	
	private KhuyenMai km;
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private KhuyenMaiBAL kmBAL = new KhuyenMaiBAL();
	private PhuTungBAL ptBAL = new PhuTungBAL();
	private NhaCungCapBAL nccBAL = new NhaCungCapBAL();
	private JTextField txtGiatienKM;
	private JLabel lblGiaKM;
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ncc_UpdateKM_Form window = new Ncc_UpdateKM_Form();
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
	public Ncc_UpdateKM_Form() {
		initialize();
	}

	public Ncc_UpdateKM_Form(String tenLogin, String maKM) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maKM_Text = maKM;
		
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
		getFrame().setBounds(100, 100, 655, 357);
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
		btnSua.setBounds(237, 263, 160, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(460, 263, 160, 43);
		frame.getContentPane().add(btnDangXuat);
		
		txtMaKM = new JTextField();
		txtMaKM.setEditable(false);
		txtMaKM.setBounds(147, 69, 160, 20);
		frame.getContentPane().add(txtMaKM);
		txtMaKM.setColumns(10);
		
		JLabel lblNhpMKM = new JLabel("Mã khuyến mãi:");
		lblNhpMKM.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpMKM.setBounds(24, 72, 113, 14);
		frame.getContentPane().add(lblNhpMKM);
		
		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
		JLabel lblNhpTn = new JLabel("Nhập tên khuyến mãi:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(14, 103, 123, 14);
		frame.getContentPane().add(lblNhpTn);

		txtTenKM = new JTextField();
		txtTenKM.setBounds(147, 100, 161, 20);
		frame.getContentPane().add(txtTenKM);
		txtTenKM.setColumns(10);

		JLabel lblChnPT = new JLabel("Chọn phụ tùng:");
		lblChnPT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChnPT.setBounds(337, 103, 112, 23);
		frame.getContentPane().add(lblChnPT);

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);

		JLabel lblNgayBD = new JLabel("Nhập ngày bắt đầu:");
		lblNgayBD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayBD.setBounds(14, 137, 123, 14);
		frame.getContentPane().add(lblNgayBD);

		JLabel lblNhpMoTa = new JLabel("Nhập mô tả:");
		lblNhpMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpMoTa.setBounds(337, 175, 112, 14);
		frame.getContentPane().add(lblNhpMoTa);

		txtMoTa = new JTextField();
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(455, 172, 165, 20);
		frame.getContentPane().add(txtMoTa);

		JLabel lbl_sday = new JLabel("/");
		lbl_sday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_sday.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_sday.setBounds(176, 133, 12, 21);
		frame.getContentPane().add(lbl_sday);

		JLabel lbl_smonth = new JLabel("/");
		lbl_smonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_smonth.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_smonth.setBounds(227, 133, 12, 21);
		frame.getContentPane().add(lbl_smonth);

		txtStartDay = new JTextField();
		txtStartDay.setColumns(10);
		txtStartDay.setBounds(147, 133, 30, 20);
		frame.getContentPane().add(txtStartDay);

		txtStartMonth = new JTextField();
		txtStartMonth.setColumns(10);
		txtStartMonth.setBounds(198, 133, 30, 20);
		frame.getContentPane().add(txtStartMonth);

		txtStartYear = new JTextField();
		txtStartYear.setColumns(10);
		txtStartYear.setBounds(249, 133, 59, 20);
		frame.getContentPane().add(txtStartYear);

		lblNCC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNCC.setBounds(337, 137, 112, 23);
		frame.getContentPane().add(lblNCC);

		txtEndYear = new JTextField();
		txtEndYear.setColumns(10);
		txtEndYear.setBounds(249, 168, 59, 20);
		frame.getContentPane().add(txtEndYear);

		JLabel lbl_smonthEnd = new JLabel("/");
		lbl_smonthEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_smonthEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_smonthEnd.setBounds(227, 168, 12, 21);
		frame.getContentPane().add(lbl_smonthEnd);

		txtEndMonth = new JTextField();
		txtEndMonth.setColumns(10);
		txtEndMonth.setBounds(198, 168, 30, 20);
		frame.getContentPane().add(txtEndMonth);

		lbl_sdayEnd = new JLabel("/");
		lbl_sdayEnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_sdayEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_sdayEnd.setBounds(176, 168, 12, 21);
		frame.getContentPane().add(lbl_sdayEnd);

		txtEndDay = new JTextField();
		txtEndDay.setColumns(10);
		txtEndDay.setBounds(147, 168, 30, 20);
		frame.getContentPane().add(txtEndDay);

		JLabel lblNgayKetThuc = new JLabel("Nhập ngày kết thúc:");
		lblNgayKetThuc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayKetThuc.setBounds(14, 172, 123, 14);
		frame.getContentPane().add(lblNgayKetThuc);

		txtNCC = new JTextField();
		txtNCC.setEditable(false);
		txtNCC.setColumns(10);
		txtNCC.setBounds(455, 136, 165, 20);
		frame.getContentPane().add(txtNCC);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(24, 263, 160, 43);
				frame.getContentPane().add(btnQuayLai);

				String[] lstPTX = napCboBoxPT(); //TODO - fill cbo loai PT xe
				cboPT.setModel(new DefaultComboBoxModel<String>(lstPTX));
				cboPT.setBounds(455, 103, 165, 20);
				frame.getContentPane().add(cboPT);
				
				txtGiatienKM = new JTextField();
				txtGiatienKM.setColumns(10);
				txtGiatienKM.setBounds(147, 199, 161, 20);
				frame.getContentPane().add(txtGiatienKM);
				
				lblGiaKM = new JLabel("Nhập giá tiền:");
				lblGiaKM.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGiaKM.setBounds(13, 202, 123, 14);
				frame.getContentPane().add(lblGiaKM);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layKM_tuForm();
				napKM_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layKM_tuForm() {
		 km = kmBAL.get_KMByID(get_maKM_Text);
	}

	// nap du lieu tu bang vao Textfield
	private void napKM_VaoTextfields() {
		txtMaKM.setText(get_maKM_Text);
		txtTenKM.setText(km.getTenKM());
			Calendar cal = Calendar.getInstance(); // get day, month, year
			cal.setTime(km.getNgayBatDau());
			int yearStart = cal.get(Calendar.YEAR);
			int monthStart = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar)
															// bat dau tu 0
			int dayStart = cal.get(Calendar.DAY_OF_MONTH);
		txtStartDay.setText(dayStart + "");
		txtStartMonth.setText(monthStart + "");
		txtStartYear.setText(yearStart + "");
			cal.setTime(km.getNgayKetThuc());
			int yearEnd = cal.get(Calendar.YEAR);
			int monthEnd = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar)
														// bat dau tu 0
			int dayEnd = cal.get(Calendar.DAY_OF_MONTH);
		txtEndDay.setText(dayEnd + "");
		txtEndMonth.setText(monthEnd + "");
		txtEndYear.setText(yearEnd + "");
		cboPT.setSelectedItem(getTenPT());
		txtNCC.setText(getTenNCC());
		txtMoTa.setText(km.getMoTaKM());
		txtGiatienKM.setText(km.getGiaTienKM()+"");
	}
		
	
	// get loai
	private String[] napCboBoxPT() {
		String loaipt = "";
		List<PhuTungXe> lstPTX = ptBAL.getAll_PT();
		int countPT = tvBAL.Count("PhuTung"); // dem ma pt
		String[] lstTenPT = new String[countPT]; 
		int i=0;
		for (PhuTungXe ptx : lstPTX) {
			loaipt = kmBAL.RetrievePT(ptx.getMaPT()); // get PT by
															// maLoai
			lstTenPT[i] = loaipt;
			i++;
		}
		return lstTenPT;
	}
	
	
	// get 1 pt - loai 
	private String getTenPT() {
		PhuTungXe pt = ptBAL.get_PTByID(km.getMaPT());
		String tenPT = pt.getTenPT(); 
		return tenPT;
	}
	
	// get nha cung cap
	private String getTenNCC() {
		ThanhVien ncc = nccBAL.get_NCCByAccount(get_Login_Text);
		String tenNCC = ncc.getHoTen(); 
		return tenNCC;
	}
	
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa khuyến mãi");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin KM

			//Region - get text from ui
			String tenkm =	txtTenKM.getText();

			String moTa =	txtMoTa.getText();
			String phuTung = (String) cboPT.getSelectedItem();
			String maPT = kmBAL.RetrieveMaPT(phuTung);
			String maNCC = ptBAL.Retrieve_MaNCC(getTenNCC());
			//Region - get date from ui
			String dayStart = txtStartDay.getText();
			String monthStart = txtStartMonth.getText();
			String yearStart = txtStartYear.getText();
			String dayEnd = txtEndDay.getText();
			String monthEnd = txtEndMonth.getText();
			String yearEnd = txtEndYear.getText();
			//EndRegion
			String gia = txtGiatienKM.getText();
			
			//EndRegion
			
			//Region - function create with condition
			if( tenkm.equals("") 		|| moTa.equals("")
			|| dayStart.equals("")		|| monthStart.equals("")	|| yearStart.equals("")
			|| dayEnd.equals("")		|| monthEnd.equals("")		|| yearEnd.equals("") )
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( tvBal.Check_name(taiKhoan)==1 && !taiKhoan.equals(nv.getTaiKhoan()))
//				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				//Region - make conversion when data is available
				String start = dayStart + "/" + monthStart + "/" + yearStart;
				String end = dayEnd + "/" + monthEnd + "/" + yearEnd;
				Date ngay_BatDau=null, ngay_KetThuc=null;
				try {
					ngay_BatDau = dateformat.parse(start);
					ngay_KetThuc = dateformat.parse(end);
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Bạn chưa điền ... - error" + e);
				}
				
				double giatienKM = Double.parseDouble(gia);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin khuyến mãi này?"
																	, "Title on Box"
																	, dialogButton);
				
				if(dialogResult == 0) {
					KhuyenMai km = new KhuyenMai(get_maKM_Text, tenkm, maPT, maNCC
												, ngay_BatDau, ngay_KetThuc, moTa, giatienKM);
					
					 if(kmBAL.update(km)){
						 JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin khuyến mãi!");
							
						frame.dispose();
						Ncc_Form_KM frmKM = new Ncc_Form_KM(get_Login_Text);
						frmKM.frame.setVisible(true);
					 }  
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napKM_VaoTextfields();
					}
				} 			
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			Ncc_Form_KM frmKM = new Ncc_Form_KM(get_Login_Text);
			frmKM.frame.setVisible(true);
		}
	}
}
