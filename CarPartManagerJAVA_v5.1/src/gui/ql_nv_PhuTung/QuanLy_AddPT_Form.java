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
import gui.ql_TonKho.QuanLy_Form_Kho;
import gui.ql_TonKho.QuanLy_UpdateKho_Form;
import helpers.UI_Helpers;

public class QuanLy_AddPT_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frmThmPhTng;
	private JTextField txtTenPT;
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
	JComboBox<String> cboLoaiPT = new JComboBox<String>();
	private JTextField txt_Hang;
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private JTextField txtGiaTien;
	private JTextField txtLoaiXe;
	JButton btnQuayLai = new JButton("Quay lai");
	JComboBox<String> cboNCC = new JComboBox<String>();
	private JTextField txtSL;
	JComboBox<String> cboKhu = new JComboBox<String>();
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

	private PhuTungBAL ptBAL = new PhuTungBAL();
	private LoaiPhuTungBAL loaiPtBAL = new LoaiPhuTungBAL();
	private NhaCungCapBAL nccBAL = new NhaCungCapBAL();
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private TonKhoBAL tonkhoBAL = new TonKhoBAL();	
	
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();	
	private PhuTungTonBAL pttonBAL = new PhuTungTonBAL();	
	private TonKhoTrongThangBAL tkttBAL = new TonKhoTrongThangBAL();	
	
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
					QuanLy_AddPT_Form window = new QuanLy_AddPT_Form();
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
	public QuanLy_AddPT_Form() {
		initialize();
	}
	
	public QuanLy_AddPT_Form(String tenLogin) {
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
		getFrame().setBounds(100, 100, 622, 409);
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

		//TODO - Add ICON
//		Image img = new ImageIcon(this.getClass().getResource("/add_icon.jpg")).getImage().getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(222, 283, 165, 43);
		getFrame().getContentPane().add(btnThem);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(435, 283, 165, 43);
		frmThmPhTng.getContentPane().add(btnDangXuat);
		
		JLabel lblNhpTn = new JLabel("Nhập tên phụ tùng:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(14, 104, 112, 14);
		frmThmPhTng.getContentPane().add(lblNhpTn);
		
		txtTenPT = new JTextField();
		txtTenPT.setBounds(136, 101, 161, 20);
		frmThmPhTng.getContentPane().add(txtTenPT);
		txtTenPT.setColumns(10);
		
		JLabel lblLoaiPT = new JLabel("Chọn loại phụ tùng:");
		lblLoaiPT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoaiPT.setBounds(317, 101, 113, 23);
		frmThmPhTng.getContentPane().add(lblLoaiPT);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frmThmPhTng.getContentPane().add(lblTenlogin);
		
				String[] lstLoaiPTX = napCboBoxLPT(); //TODO - fill cbo loai PT xe
				cboLoaiPT.setModel(new DefaultComboBoxModel<String>(lstLoaiPTX));
				

				cboLoaiPT.setBounds(440, 101, 160, 22);
				frmThmPhTng.getContentPane().add(cboLoaiPT);
				
				JLabel lblNcc = new JLabel("Chọn nhà cung cấp:");
				lblNcc.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNcc.setBounds(317, 138, 112, 14);
				frmThmPhTng.getContentPane().add(lblNcc);
				
				JLabel lbl_Hang = new JLabel("Nhập hãng:");
				lbl_Hang.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_Hang.setBounds(14, 167, 113, 14);
				frmThmPhTng.getContentPane().add(lbl_Hang);
				
				txt_Hang = new JTextField();
				txt_Hang.setColumns(10);
				txt_Hang.setBounds(136, 164, 161, 20);
				frmThmPhTng.getContentPane().add(txt_Hang);
				
				txtGiaTien = new JTextField();
				txtGiaTien.setColumns(10);
				txtGiaTien.setBounds(136, 132, 161, 20);
				frmThmPhTng.getContentPane().add(txtGiaTien);
				
				JLabel lblGiaTien = new JLabel("Nhập giá tiền:");
				lblGiaTien.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGiaTien.setBounds(14, 135, 112, 14);
				frmThmPhTng.getContentPane().add(lblGiaTien);
				
				JLabel lblLoaiXe = new JLabel("Nhập loại xe:");
				lblLoaiXe.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiXe.setBounds(318, 172, 112, 15);
				frmThmPhTng.getContentPane().add(lblLoaiXe);
				
				txtLoaiXe = new JTextField();
				txtLoaiXe.setColumns(10);
				txtLoaiXe.setBounds(439, 169, 161, 20);
				frmThmPhTng.getContentPane().add(txtLoaiXe);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(14, 283, 165, 43);
				frmThmPhTng.getContentPane().add(btnQuayLai);
				
				String[] lstNCC = napCboBoxNCC(); //TODO - fill cbo nha cung cap
				cboNCC.setModel(new DefaultComboBoxModel<String>(lstNCC));

				cboNCC.setBounds(440, 134, 160, 22);
				frmThmPhTng.getContentPane().add(cboNCC);
				
				txtSL = new JTextField();
				txtSL.setColumns(10);
				txtSL.setBounds(136, 195, 161, 20);
				frmThmPhTng.getContentPane().add(txtSL);
				
				JLabel lblSL = new JLabel("Nhập số lượng:");
				lblSL.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSL.setBounds(14, 198, 113, 14);
				frmThmPhTng.getContentPane().add(lblSL);
				
				String[] lstKhu = napCboBoxKhu(); //TODO - fill cbo khu
				cboKhu.setModel(new DefaultComboBoxModel<String>(lstKhu));

				cboKhu.setBounds(440, 198, 160, 22);
				frmThmPhTng.getContentPane().add(cboKhu);
				
				JLabel lblKhu = new JLabel("Chọn khu vực:");
				lblKhu.setHorizontalAlignment(SwingConstants.RIGHT);
				lblKhu.setBounds(317, 202, 112, 14);
				frmThmPhTng.getContentPane().add(lblKhu);
				
				JLabel lblNhpNgy = new JLabel("Nhập ngày:");
				lblNhpNgy.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpNgy.setBounds(14, 229, 112, 14);
				frmThmPhTng.getContentPane().add(lblNhpNgy);
				
				txtDay = new JTextField();
				txtDay.setColumns(10);
				txtDay.setBounds(136, 226, 30, 20);
				frmThmPhTng.getContentPane().add(txtDay);
				
				JLabel label_1 = new JLabel("/");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_1.setBounds(165, 226, 12, 21);
				frmThmPhTng.getContentPane().add(label_1);
				
				txtMonth = new JTextField();
				txtMonth.setColumns(10);
				txtMonth.setBounds(187, 226, 30, 20);
				frmThmPhTng.getContentPane().add(txtMonth);
				
				JLabel label_2 = new JLabel("/");
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_2.setBounds(216, 226, 12, 21);
				frmThmPhTng.getContentPane().add(label_2);
				
				txtYear = new JTextField();
				txtYear.setColumns(10);
				txtYear.setBounds(238, 226, 59, 20);
				frmThmPhTng.getContentPane().add(txtYear);
				
				//TODO - Register before perform action
				btnThem.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);

	}
	//EndRegion
	
	//Region - TODO - support methods
	//xoa rong
	private void xoaRong_Txt(){
		txtTenPT.setText("");
		txtGiaTien.setText("");
		cboNCC.setSelectedIndex(0);
		cboLoaiPT.setSelectedIndex(0);
		txtLoaiXe.setText("");
		txt_Hang.setText("");
		txtSL.setText("");
		cboKhu.setSelectedIndex(0);
		txtDay.setText("");
		txtMonth.setText("");
		txtYear.setText("");
		
		txtTenPT.requestFocus();
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
	
	private boolean updateKhu_SL(int soLuong, String maKhu) { //tinh tong & update khu -> cho pt moi
		TonKho khu = tonkhoBAL.get_KhuByID(maKhu);
		int sum = khu.getSoLuong() + soLuong;
		
		if(kiemTraSucChua(sum)==false){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Các Khu đều không đủ chứa số lượng hiện tại"
																	+ ", Bạn có muốn cập nhập khu vực?"
																, "Title on Box"
																, dialogButton);
			if(dialogResult == 0) {
				int dialogButton2 = JOptionPane.YES_NO_OPTION;
				int dialogResult2 = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm  phụ tùng này vào khu vực mới?"
																	, "Title on Box"
																	, dialogButton2);
				if(dialogResult2 == 0) {
					int newID = UI_Helpers.addNewID("TonKho"); //tao index tiep theo cho list
					String newMaKhu = "K" + newID;
					TonKho newKhu_SL = new TonKho(newMaKhu, soLuong, soLuong+100); //ko lay sl khu cu wa khu moi
					tonkhoBAL.create(newKhu_SL);
//					maKhu = newMaKhu;  // doi ma khu moi -> does it work?? -> it not
					newKhu = newMaKhu;
					return true;
				}else {
					int dialogButton3 = JOptionPane.YES_NO_OPTION;
					int dialogResult3 = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật khu vực này?"
																		, "Title on Box"
																		, dialogButton3);
					if(dialogResult3 == 0) {
						frmThmPhTng.dispose();
						QuanLy_UpdateKho_Form updatekho = new QuanLy_UpdateKho_Form(get_Login_Text, maKhu);
						updatekho.frame.setVisible(true);	
						return false;
					}else {
						return false;
					}
				}
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
		return frmThmPhTng;
	}

	public void setFrame(JFrame frame) {
		this.frmThmPhTng = frame;
		frmThmPhTng.setTitle("Thêm phụ tùng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnThem)){ //Them thong tin phu tung
			//Region - get text from ui
			int newID = UI_Helpers.addNewID("PhuTung"); //tao index tiep theo cho list
			String maPT = "PT" + newID;
			String tenpt =	txtTenPT.getText();
			String tien = txtGiaTien.getText();
			
			String loaipt = (String) cboLoaiPT.getSelectedItem();
				String maLPT = ptBAL.RetrieveMaLoai(loaipt); // get ma PT
			String ncc = (String) cboNCC.getSelectedItem();
				String maNCC = ptBAL.Retrieve_MaNCC(ncc); // get ma NCC
			
			String loaiXe = txtLoaiXe.getText();
			String hang =	txt_Hang.getText();
			String sl = txtSL.getText();
			String maKhu = cboKhu.getSelectedItem().toString();
			
			//Region - get date from ui
			String day = txtDay.getText();
			String month = txtMonth.getText();
			String year = txtYear.getText();
			//EndRegion
			
			//EndRegion
			
			//Region - function create with condition
			if( tenpt.equals("") 	|| tien.equals("") 	
			|| loaiXe.equals("")	|| hang.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
//			else if( tvBal.Check_name(taiKhoan)==1 )
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
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin phụ tùng này?"
																	, "Title on Box"
																	, dialogButton);
				if(dialogResult == 0) {
					if (updateKhu_SL(soLuong, maKhu)==true) {
						if(!newKhu.equals("")){
							maKhu = newKhu;
						}
						PhuTungXe pt = new PhuTungXe(maPT, tenpt, giaTien, maNCC
								,maLPT, loaiXe, hang, soLuong, maKhu);							
						
						if(ptBAL.create(pt)){
							JOptionPane.showMessageDialog(null, "Bạn vừa thêm phụ tùng!");
							
							UI_Helpers.updateNewID("PhuTung", newID);
							
							////// them vao phieu nhap, pt ton,  ton trong thang
							//////
							//////
							PhieuNhap pn = new PhieuNhap(maPT, soLuong, ngayNhapHang);
							
							int thangNhap = Integer.parseInt(month);
							int namNhap = Integer.parseInt(year);
							PhuTungTon ptton = new PhuTungTon(maPT, soLuong, soLuong, thangNhap, namNhap);	 

							if (pnBAL.create(pn)) {
								System.out.println("create pn success");
							}
							if (pttonBAL.create(ptton)) {
								System.out.println("create ptton success");
							}

							TonKhoTrongThang tktt;
							tktt = tkttBAL.get_TKTTByID_Thang(thangNhap, namNhap);
							if(tktt == null){
								int newTkttID = UI_Helpers.addNewID("TonKhoTrongThang"); //tao index tiep theo cho list
								String matktt = "TKTT" + newTkttID;
								tktt = new TonKhoTrongThang(matktt, soLuong, soLuong, 0, 0, thangNhap, namNhap);

								if (tkttBAL.create(tktt)) {
									System.out.println("create ton_kho_thang success");
								}								
							}else {
								TonKhoTrongThang tkttNew =  new TonKhoTrongThang(tktt.getMaTon()
																	, tktt.getTongSLNhap() + soLuong
																	, tktt.getTongSLTon() + soLuong
																	, tktt.getTongSLXuat(), tktt.getTongChiPhiXuat()
																	, tktt.getThangTon(), tktt.getNamTon());;
								if (tkttBAL.update(tkttNew)) {
									System.out.println("update ton_kho_thang success");
								}
							}
							//////
							//////
							////// them vao phieu nhap, pt ton,  ton trong thang
							
							frmThmPhTng.dispose();
							QuanLy_Form_PT ptForm = new QuanLy_Form_PT(get_Login_Text);
							ptForm.frame.setVisible(true);
						}
					}
					
					
				}else { 
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
			UI_Helpers.DangXuat(frmThmPhTng);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frmThmPhTng.dispose();
			if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);			
			}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);	
			}
		}
	}
}
