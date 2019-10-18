package gui.ql_nv_PhuTung;

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

import bAL.LoaiPhuTungBAL;
import bAL.NhaCungCapBAL;
import bAL.PhieuNhapBAL;
import bAL.PhuTungBAL;
import bAL.PhuTungTonBAL;
import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import bAL.TonKhoTrongThangBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhuTungTon;
import entity.carPart.PhuTungXe;
import entity.carPart.TonKho;
import entity.carPart.TonKhoTrongThang;
import entity.users.NhaCungCap;
import entity.users.ThanhVien;
import gui.ql_TonKho.QuanLy_UpdateKho_Form;
import helpers.UI_Helpers;

public class QuanLy_UpdatePT_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTextField txtMaPT;
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
	
	private String get_maPT_Text;
	private JTextField txtTenPT;
	private JTextField txtHang;
	private JTextField txtLoaiXe;
	private JTextField txtGiaTien;
	JButton btnQuayLai = new JButton("Quay lai");
	JComboBox<String> cboNCC = new JComboBox<String>();
	JComboBox<String> cboLoaiPT = new JComboBox<String>();
	private JTextField txtSL;
	private JTextField txtKhu;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	private PhuTungXe pt;
//	private LoaiPhuTungXe loaipt;
//	private NhaCungCap ncc;
	private PhuTungBAL ptBAL = new PhuTungBAL();
	private LoaiPhuTungBAL loaiPtBAL = new LoaiPhuTungBAL();
	private NhaCungCapBAL nccBAL = new NhaCungCapBAL();
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private TonKhoBAL tonkhoBAL = new TonKhoBAL();
	
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();	
	private PhuTungTonBAL pttonBAL = new PhuTungTonBAL();	
	private TonKhoTrongThangBAL tkttBAL = new TonKhoTrongThangBAL();	
	private PhieuNhap pn;
	private PhuTungTon ptton;
	
	String newKhu = "";
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_UpdatePT_Form window = new QuanLy_UpdatePT_Form();
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
	public QuanLy_UpdatePT_Form() {
		initialize();
	}
	
	public QuanLy_UpdatePT_Form(String tenLogin, String maPT) {
		get_Login_Text =tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maPT_Text = maPT;
		pn = pnBAL.get_PhieuNhapByID(maPT); // lay phieu tu ma pt
		ptton = pttonBAL.get_PTTonByID(maPT); // lay ptt tu ma pt
		
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
		getFrame().setBounds(100, 100, 623, 361);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		//TODO - change lbl name by User
		if(tvBal.Retrieve(get_Login_Text).equals("Quan li")){
			lblNgiQunL = new JLabel(" Người Quản Lý:");	
		}else if(tvBal.Retrieve(get_Login_Text).equals("Nhan vien")){
			lblNgiQunL = new JLabel(" Nhân viên:");	
		}
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(225, 244, 160, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(438, 244, 160, 43);
		frame.getContentPane().add(btnDangXuat);
		
		txtMaPT = new JTextField();
		txtMaPT.setEditable(false);
		txtMaPT.setBounds(138, 69, 161, 20);
		frame.getContentPane().add(txtMaPT);
		txtMaPT.setColumns(10);
		
		JLabel lblNhpMPt = new JLabel("Mã phụ tùng:");
		lblNhpMPt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpMPt.setBounds(35, 72, 92, 14);
		frame.getContentPane().add(lblNhpMPt);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
		
		String[] lstLoaiPTX = napCboBoxLPT(); //TODO - fill cbo loai PT xe
		cboLoaiPT.setModel(new DefaultComboBoxModel<String>(lstLoaiPTX));
		cboLoaiPT.setBounds(437, 69, 160, 22);
		frame.getContentPane().add(cboLoaiPT);
		
		JLabel lblCboLoaiPT = new JLabel("Chọn loại phụ tùng:");
		lblCboLoaiPT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCboLoaiPT.setBounds(314, 69, 113, 23);
		frame.getContentPane().add(lblCboLoaiPT);
		
		txtTenPT = new JTextField();
		txtTenPT.setColumns(10);
		txtTenPT.setBounds(138, 100, 161, 20);
		frame.getContentPane().add(txtTenPT);
		
		JLabel lblTenPT = new JLabel("Nhập tên phụ tùng:");
		lblTenPT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenPT.setBounds(16, 103, 112, 14);
		frame.getContentPane().add(lblTenPT);
		
		JLabel lblNcc = new JLabel("Chọn nhà cung cấp:");
		lblNcc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNcc.setBounds(314, 102, 112, 14);
		frame.getContentPane().add(lblNcc);
		
		txtHang = new JTextField();
		txtHang.setColumns(10);
		txtHang.setBounds(138, 162, 161, 20);
		frame.getContentPane().add(txtHang);
		
		JLabel lblHang = new JLabel("Nhập hãng:");
		lblHang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHang.setBounds(16, 165, 113, 14);
		frame.getContentPane().add(lblHang);
		
		JLabel lblLoaiXe = new JLabel("Nhập loại xe:");
		lblLoaiXe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoaiXe.setBounds(314, 134, 112, 14);
		frame.getContentPane().add(lblLoaiXe);
		
		txtLoaiXe = new JTextField();
		txtLoaiXe.setColumns(10);
		txtLoaiXe.setBounds(436, 134, 161, 20);
		frame.getContentPane().add(txtLoaiXe);
		
		txtGiaTien = new JTextField();
		txtGiaTien.setColumns(10);
		txtGiaTien.setBounds(138, 131, 161, 20);
		frame.getContentPane().add(txtGiaTien);
		
		JLabel lblGiaTien = new JLabel("Nhập giá tiền:");
		lblGiaTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiaTien.setBounds(16, 134, 112, 14);
		frame.getContentPane().add(lblGiaTien);

		btnQuayLai.setBackground(Color.CYAN);
		btnQuayLai.setBounds(16, 244, 165, 43);
		frame.getContentPane().add(btnQuayLai);
		
		String[] lstNCC = napCboBoxNCC(); //TODO - fill cbo nha cung cap
		cboNCC.setModel(new DefaultComboBoxModel<String>(lstNCC));

		cboNCC.setBounds(437, 102, 160, 22);
		frame.getContentPane().add(cboNCC);
		
		txtSL = new JTextField();
		txtSL.setColumns(10);
		txtSL.setBounds(437, 165, 161, 20);
		frame.getContentPane().add(txtSL);
		
		JLabel lblSL = new JLabel("Nhập số lượng:");
		lblSL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSL.setBounds(315, 168, 113, 14);
		frame.getContentPane().add(lblSL);
		
		JLabel lblKhu = new JLabel("Khu vực:");
		lblKhu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKhu.setBounds(13, 197, 112, 14);
		frame.getContentPane().add(lblKhu);
		
		txtKhu = new JTextField();
		txtKhu.setEditable(false);
		txtKhu.setText((String) null);
		txtKhu.setColumns(10);
		txtKhu.setBounds(138, 193, 161, 20);
		frame.getContentPane().add(txtKhu);
		
		JLabel lblNgyNhp = new JLabel("Ngày nhập:");
		lblNgyNhp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgyNhp.setBounds(315, 199, 112, 14);
		frame.getContentPane().add(lblNgyNhp);
		
		txtDay = new JTextField();
		txtDay.setEditable(false);
		txtDay.setColumns(10);
		txtDay.setBounds(437, 196, 30, 20);
		frame.getContentPane().add(txtDay);
		
		JLabel label_1 = new JLabel("/");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(466, 196, 12, 21);
		frame.getContentPane().add(label_1);
		
		txtMonth = new JTextField();
		txtMonth.setEditable(false);
		txtMonth.setColumns(10);
		txtMonth.setBounds(488, 196, 30, 20);
		frame.getContentPane().add(txtMonth);
		
		JLabel label_2 = new JLabel("/");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(517, 196, 12, 21);
		frame.getContentPane().add(label_2);
		
		txtYear = new JTextField();
		txtYear.setEditable(false);
		txtYear.setColumns(10);
		txtYear.setBounds(539, 196, 59, 20);
		frame.getContentPane().add(txtYear);
		
		String[] lstKhu = napCboBoxKhu();
			
		// TODO - Register before perform action
		btnSua.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnQuayLai.addActionListener(this);
		
		// methods from support region
		layPT_tuForm();
		napPT_VaoTextfields();
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu PT tu form truoc
		private void layPT_tuForm() {
			 pt = ptBAL.get_PTByID(get_maPT_Text);
		}

	// nap du lieu tu bang vao Textfield
	private void napPT_VaoTextfields() {
		txtMaPT.setText(get_maPT_Text);
		txtTenPT.setText(pt.getTenPT());
		txtGiaTien.setText(pt.getGiaTien()+"");
		String tenNCC = ptBAL.RetrieveNCC(pt.getMaNhaCungCap()); // get ten NCC
		cboNCC.setSelectedItem(tenNCC);
		String tenLPT = ptBAL.RetrieveLoaiPT(pt.getMaLoaiPTX()); // get ten LPT
		cboLoaiPT.setSelectedItem(tenLPT);
		txtLoaiXe.setText(pt.getLoaiXe());
		txtHang.setText(pt.getHang());
		txtSL.setText(pt.getSoLuong()+"");
		txtKhu.setText(pt.getMaKhu());
		Calendar cal = Calendar.getInstance(); // get day, month, year
		cal.setTime(pn.getNgayNhapHang());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar) bat dau tu 0
			int day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay.setText(day + "");
		txtMonth.setText(month  + "");
		txtYear.setText(year + "");
	}

	// nap cbo loai
	private String[] napCboBoxLPT() {
		String loaipt = "";
		List<LoaiPhuTungXe> lstLoaiPTX = loaiPtBAL.getAll_LoaiPT();
		int countLoaiPT = tvBal.Count("LoaiPhuTung"); // dem ma loai pt
		String[] lstTenLoaiPT = new String[countLoaiPT]; 
		int i=0;
		for (LoaiPhuTungXe lptx : lstLoaiPTX) {
			loaipt = ptBAL.RetrieveLoaiPT(lptx.getMaLoai()); // get loaiPT by
																// maLoai
			lstTenLoaiPT[i] = loaipt;
			i++;
		}
		return lstTenLoaiPT;
	}
	
	// nap cbo nha cung cap
	private String[] napCboBoxNCC() {
		String nhaCC = "";
		List<ThanhVien> lstNCC = nccBAL.getAll_NCC();
		int countNCC = tvBal.Count("NhaCungCap"); // dem ma ncc
		String[] lstTenNCC = new String[countNCC]; 
		int i=0;
		for (ThanhVien tv : lstNCC) {
			NhaCungCap ncc = (NhaCungCap) tv;
			nhaCC = ptBAL.RetrieveNCC(ncc.getMaTV()); // get ten NCC by
																// ma NCC
			lstTenNCC[i] = nhaCC;
			i++;
		}
		return lstTenNCC;
	}
	
	// nap cbo khu
	private String[] napCboBoxKhu() {
		List<TonKho> lstKhu = tonkhoBAL.getAll_Khu();
		int countKhu = tvBal.Count("TonKho"); // dem ma khu
		String[] lstMaKhu = new String[countKhu]; 
		int i=0;
		for (TonKho tk : lstKhu) {
			lstMaKhu[i] = tk.getMaKhu(); // get ten NCC by
			i++;
		}
		return lstMaKhu;
	}

	private boolean kiemTraSucChua(int tongPT) { // sl them + tong hien tai
													// kt ko khu nao chua dc nua
		List<TonKho> lstKhu = tonkhoBAL.getAll_Khu();
		int count = 0;
		for (TonKho tk : lstKhu) {
			if(tongPT > tk.getSoLuongToiDa()){
				count++;
			}
		}
		
		if(count==tvBal.Count("TonKho")){
			return false;			
		}
		return true;
	}
	
	private boolean updateKhu_SL(int oldSL, int newSL,  String maKhu) { //tinh tong & update khu
		TonKho khu = tonkhoBAL.get_KhuByID(maKhu);
		int sum = khu.getSoLuong()- oldSL + newSL;
		
		if(kiemTraSucChua(sum)==false){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Các Khu đều không đủ chứa số lượng hiện tại"
																	+ ", Bạn có muốn cập nhập khu vực?"
																, "Title on Box"
																, dialogButton);
			//Region - work later
//			if(dialogResult == 0) {
//				int dialogButton2 = JOptionPane.YES_NO_OPTION;
//				int dialogResult2 = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm  phụ tùng này vào khu vực mới?"
//																	, "Title on Box"
//																	, dialogButton2);
//				if(dialogResult2 == 0) {
//					int newID = UI_Helpers.addNewID("TonKho"); //tao index tiep theo cho list
//					String newMaKhu = "K" + newID;
//					TonKho newKhu_SL = new TonKho(newMaKhu, newSL - oldSL, newSL - oldSL + 100); //ko lay sl khu cu wa khu moi
//					tonkhoBAL.create(newKhu_SL);
////					maKhu = newMaKhu;  // doi ma khu moi -> does it work??
//					newKhu = newMaKhu;
//					return true;
//				}else {
//				}
//				
//			
//			}else {
//				return false;
//			}
			//EndRegion
			
//			int dialogButton3 = JOptionPane.YES_NO_OPTION;
//			int dialogResult3 = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật khu vực này?"
//					, "Title on Box"
//					, dialogButton3);
			if(dialogResult == 0) { // old : dialogButton3
				frame.dispose();
				QuanLy_UpdateKho_Form updatekho = new QuanLy_UpdateKho_Form(get_Login_Text, maKhu);
				updatekho.frame.setVisible(true);		
				return false;
			}else {
				return false;
			}
		} else {
			if(sum <= khu.getSoLuongToiDa()){
				TonKho newKhu_SL = new TonKho(khu.getMaKhu(), sum, khu.getSoLuongToiDa());
				tonkhoBAL.update(newKhu_SL);		
			}else {
				JOptionPane.showMessageDialog(null, "Khu đã đầy, mời bạn chọn khu vực khác!");
				return false;
			}
		}
		
		return true;
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa phụ tùng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin PT
			//Region - get text from ui
			String tenpt =	txtTenPT.getText();
			String tien = txtGiaTien.getText();
			
			String loaipt = (String) cboLoaiPT.getSelectedItem();
				String maLPT = ptBAL.RetrieveMaLoai(loaipt); // get ma PT
			String ncc = (String) cboNCC.getSelectedItem();
				String maNCC = ptBAL.Retrieve_MaNCC(ncc); // get ma NCC
			
			String loaiXe = txtLoaiXe.getText();
			String hang = txtHang.getText();
			String sl = txtSL.getText();
			String maKhu = pt.getMaKhu(); //
			//Region - get date from ui
			String day = txtDay.getText();
			String month = txtMonth.getText();
			String year = txtYear.getText();
			//EndRegion
			
			//EndRegion
			
			//Region - function create with condition
			if( tenpt.equals("") 	|| tien.equals("") 	
			|| loaiXe.equals("")	|| hang.equals("")
			|| day.equals("")		|| month.equals("")		|| year.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
//			else if( tvBal.Check_name(taiKhoan)==1 && !taiKhoan.equals(nv.getTaiKhoan()))
//				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				//Region - make conversion when data is available
				double giaTien = Double.parseDouble(tien);
				int soLuong = Integer.parseInt(sl);
				
				String ngay_Vao = day + "/" + month + "/" + year;
				Date ngayNhapHang =null;
				try {
					ngayNhapHang = dateformat.parse(ngay_Vao);
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Bạn chưa điền ... - error" + e);
				}
				
				//EndRegion
				
				//// old sl
				int sl_Old = pt.getSoLuong(); 
				////
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin phụ tùng này?"
						, "Title on Box"
						, dialogButton);
				
				if(dialogResult == 0) {
					if (updateKhu_SL(pt.getSoLuong(), soLuong, maKhu)==true) {
						System.out.println("\n ma khu = " + maKhu);
						
						PhuTungXe ptNew = new PhuTungXe(pt.getMaPT(), tenpt, giaTien, maNCC, 
								maLPT, loaiXe, hang, soLuong, maKhu);
						
						if(ptBAL.update(ptNew)){
							JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin phụ tùng!");
							
							////// cap nhat vao phieu nhap, pt ton,  ton trong thang
							//////
							//////
							PhieuNhap pnNew = new PhieuNhap(pt.getMaPT(), soLuong, pn.getNgayNhapHang());

							PhuTungTon pttonNew = new PhuTungTon(pt.getMaPT(), soLuong, soLuong
																, ptton.getThangNhap(), ptton.getNamNhap());	 

							if (pnBAL.update(pnNew)) {
								System.out.println("create pn success");
							}
							if (pttonBAL.update(pttonNew)) {
								System.out.println("create ptton success");
							}

							TonKhoTrongThang tktt;
							tktt = tkttBAL.get_TKTTByID_Thang(ptton.getThangNhap(), ptton.getNamNhap());
							
							TonKhoTrongThang tkttNew =  new TonKhoTrongThang(tktt.getMaTon()
																	, tktt.getTongSLNhap() - sl_Old + soLuong
																	, tktt.getTongSLTon() - sl_Old + soLuong
																	, tktt.getTongSLXuat(), tktt.getTongChiPhiXuat()
																	, tktt.getThangTon(), tktt.getNamTon());;
							if (tkttBAL.update(tkttNew)) {
								System.out.println("update ton_kho_thang success");
							}
							
							//////
							//////
							////// cap nhat vao phieu nhap, pt ton,  ton trong thang
							
							frame.dispose();
							QuanLy_Form_PT qlPT = new QuanLy_Form_PT(get_Login_Text);
							qlPT.frame.setVisible(true);
						}  
					}
					
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napPT_VaoTextfields();
					}
				} 			
			}
			//EndRegion
			
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			if(tvBal.Retrieve(get_Login_Text).equals("Quan li")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);			
			}else if(tvBal.Retrieve(get_Login_Text).equals("Nhan vien")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);	
			}
		}
		
	}
	//EndRegion
	
}
