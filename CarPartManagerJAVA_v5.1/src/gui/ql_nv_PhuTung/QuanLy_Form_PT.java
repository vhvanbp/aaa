package gui.ql_nv_PhuTung;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

import bAL.KhuyenMaiBAL;
import bAL.LoaiPhuTungBAL;
import bAL.PhieuNhapBAL;
import bAL.PhieuXuatBAL;
import bAL.PhuTungBAL;
import bAL.PhuTungTonBAL;
import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import bAL.TonKhoTrongThangBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.KhuyenMai;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhieuXuat;
import entity.carPart.PhuTungTon;
import entity.carPart.PhuTungXe;
import entity.carPart.TonKho;
import entity.carPart.TonKhoTrongThang;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class QuanLy_Form_PT extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTable table;
	/**
	 *@param Xóa thành viên
	 */
	JButton btnXoa = new JButton("Xoa phu tung");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnSuaPT = new JButton("<html><center>"+"Sua Thong Tin " + "<br>" + "phu tung"+"</center></html>");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnChiTiet = new JButton("<html><center>"+"Xem chi tiet " + "<br>" + "phu tung"+"</center></html>");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTimtt;
	JComboBox<String> cboPT = new JComboBox<String>();
	JButton btnThemPT = new JButton("Them phu tung");
	JButton btnQuayLai = new JButton("Quay lai");
	JButton btnCapNhatGia = new JButton("Cap nhat gia tien");
	JComboBox<String> cbo_SapXep = new JComboBox<String>();
	JButton btnSapXep = new JButton("Sap xep");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	private PhuTungBAL ptBAL = new PhuTungBAL();
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private LoaiPhuTungBAL loaiPtBAL = new LoaiPhuTungBAL();
	private TonKhoBAL tonkhoBAL = new TonKhoBAL();	
	private KhuyenMaiBAL kmBAL = new KhuyenMaiBAL();	
	// Tao dataModel
	String[] headers = {"Mã phụ tùng", "Tên", "Giá tiền", "Tồn kho", "Loại phụ tùng"};
	private final JButton btnXuatExcel = new JButton("Xuat bao cao");
	private final JTextField txtSL = new JTextField();
	JButton btnXuatPhuTung = new JButton("Xuat phu tung");
	private TonKhoTrongThangBAL tkttBAL = new TonKhoTrongThangBAL();	
	private PhuTungTon ptton;
	private PhieuNhap pn;
	private PhuTungTonBAL pttonBAL = new PhuTungTonBAL();	
	private PhieuXuatBAL pxBAl = new PhieuXuatBAL();
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
					QuanLy_Form_PT window = new QuanLy_Form_PT();
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
	public QuanLy_Form_PT() {
		initialize();
	}
	
	public QuanLy_Form_PT(String ten) {
		get_Login_Text =ten;
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
		getFrame().setBounds(100, 100, 869, 613);
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
		
		btnChiTiet.setBackground(Color.CYAN);
		btnChiTiet.setBounds(16, 126, 144, 46);
		getFrame().getContentPane().add(btnChiTiet);

		btnSuaPT.setBackground(Color.CYAN);
		btnSuaPT.setBounds(170, 126, 144, 46);
		getFrame().getContentPane().add(btnSuaPT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 335, 837, 243);
		getFrame().getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 TV", "H\u1ECD T\u00EAn", "Ti\u1EC3u ban"
			}
		));
		
		JLabel lblDanhSch = new JLabel("Danh sách phụ tùng :");
		lblDanhSch.setBounds(16, 301, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setBounds(170, 69, 144, 46);
		frame.getContentPane().add(btnXoa);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(734, 30, 119, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panelTim = new JPanel();
				panelTim.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm phụ tùng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelTim.setBounds(628, 200, 225, 90);
				frame.getContentPane().add(panelTim);
				panelTim.setLayout(null);
				
				txtTimtt = new JTextField();
				txtTimtt.setBounds(86, 49, 128, 20);
				panelTim.add(txtTimtt);
				//TODO Chuc nang Tim Kiem
				txtTimtt.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						if(txtTimtt.getText().equalsIgnoreCase("")){
							createTable();
						}else {
							Connection con = DataBase.getConnection();
							PreparedStatement stmt = null;
							
							try {
								int cboIndex = cboPT.getSelectedIndex();
								String selection[] = new String[]{"MaPT","TenPT","LoaiPT","MaKhu"};
								
								stmt = con.prepareStatement("select pt.MaPT as '" + headers[0] + "'"
																+ ", pt.TenPT as '" + headers[1] + "'"
																+ ", pt.GiaTien as '" + headers[2] + "'"
																+ ", pt.SoLuong as '" + headers[3] + "' "
																+ ", lpt.LoaiPT as '" + headers[4] + "' "
																+ ", pt.Makhu as '" + "Mã khu vực" + "' "
																+ "from PhuTung AS pt INNER JOIN "
																+ "LoaiPhuTung AS lpt ON pt.MaLoaiPTX = lpt.MaLoai " 
																+ "where "+selection[cboIndex]+" = ?");
								stmt.setString(1, txtTimtt.getText());
								ResultSet rs = stmt.executeQuery();
								
								table.setModel(DbUtils.resultSetToTableModel(rs));
								
								stmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}			
						}
						
					}
				});
				txtTimtt.setColumns(10);
				cboPT.setBounds(86, 16, 128, 22);
				panelTim.add(cboPT);
				

				cboPT.setModel(new DefaultComboBoxModel<String>(new String[] {"Mã phụ tùng", "Tên phụ tùng"
																			, "Loại phụ tùng", "Khu"}));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(10, 16, 66, 23);
				panelTim.add(lblTmTheo);
				
				btnThemPT.setBackground(Color.CYAN);
				btnThemPT.setBounds(16, 69, 144, 46);
				frame.getContentPane().add(btnThemPT);
				
				JPanel panel_SapXep = new JPanel();
				panel_SapXep.setLayout(null);
				panel_SapXep.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sắp xếp phụ tùng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_SapXep.setBounds(16, 198, 239, 90);
				frame.getContentPane().add(panel_SapXep);
				
				// add to cbo sap xep
				String[] lstLoaiPTX = napCboBoxLPT(); //TODO - fill cbo loai PT xe
				String[] lstLoai2 = new String[lstLoaiPTX.length+1];
				lstLoai2[0] = "Tat ca";
				int lstIndex = 1;
				for (String lpt : lstLoaiPTX) {
					lstLoai2[lstIndex] = lpt;
					lstIndex++;
				}

				cbo_SapXep.setModel(new DefaultComboBoxModel<String>(lstLoai2));
				cbo_SapXep.setBounds(98, 16, 131, 22);
				panel_SapXep.add(cbo_SapXep);
				// TODO - IS THIS CORRECT ???
				//Region - cbo listener
//				cbo_SapXep.addKeyListener(new KeyAdapter() { 
//					@Override
//					public void keyReleased(KeyEvent arg0) {
//						Connection con = DataBase.getConnection();
//						PreparedStatement stmt = null;
//
//						try {
//							String selection = (String) cboPT.getSelectedItem();
//							stmt = con.prepareStatement("select * from THANHVIEN where "+selection+" = ?");
//							stmt.setString(1, txtTimtt.getText());
//							ResultSet rs = stmt.executeQuery();
//							
//							table.setModel(DbUtils.resultSetToTableModel(rs));
//						
//								stmt.close();
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						
//					}
//				});
				//EndRegion
				
				JLabel lbl_SapXep = new JLabel("Sắp xếp theo:");
				lbl_SapXep.setBounds(10, 16, 81, 23);
				panel_SapXep.add(lbl_SapXep);

				btnSapXep.setBackground(Color.CYAN);
				btnSapXep.setBounds(98, 49, 131, 23);
				panel_SapXep.add(btnSapXep);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(734, 83, 119, 43);
				frame.getContentPane().add(btnQuayLai);	

				btnCapNhatGia.setBackground(Color.CYAN);
				btnCapNhatGia.setBounds(324, 69, 144, 46);
				frame.getContentPane().add(btnCapNhatGia);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(324, 126, 144, 46);
				
				frame.getContentPane().add(btnXuatExcel);
				
				JPanel panel = new JPanel();
				panel.setLayout(null);
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Xuất phụ tùng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(319, 200, 252, 90);
				frame.getContentPane().add(panel);
				
				JLabel lblNhpSLng = new JLabel("Nhập số lượng:");
				lblNhpSLng.setBounds(10, 16, 94, 23);
				panel.add(lblNhpSLng);
				
				btnXuatPhuTung.setBackground(Color.CYAN);
				btnXuatPhuTung.setBounds(111, 48, 131, 23);
				panel.add(btnXuatPhuTung);
				txtSL.setColumns(10);
				txtSL.setBounds(114, 16, 128, 20);
				
				panel.add(txtSL);
				
				//TODO - Register before perform action
				btnXoa.addActionListener(this);
				btnSuaPT.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnChiTiet.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnThemPT.addActionListener(this);
				btnCapNhatGia.addActionListener(this);
				btnSapXep.addActionListener(this);
				btnXuatExcel.addActionListener(this);
				btnXuatPhuTung.addActionListener(this);
				// methods from support region
				createTable();
	}
	//EndRegion
	
	//Region - TODO - support methods
	// tao bang tu DS 
	private void createTable() {
		// Tao dataModel & table 
		dataModel = new DefaultTableModel(headers, 0);
					
		napDuLieuChoBang();
		table.setModel(dataModel);

		ptBAL = new PhuTungBAL(); // Khi chương trình chạy, nạp toàn bộ danh
										// sách sv lên bảng
	}
	
	// nap DS
	private void napDuLieuChoBang() {
		List<PhuTungXe> list = ptBAL.getAll_PT();
		String loaipt = "";
		for (PhuTungXe pt : list) {
			loaipt = ptBAL.RetrieveLoaiPT(pt.getMaLoaiPTX());	// get loaiPT by maLoai
			Object[] row = { pt.getMaPT(), pt.getTenPT(), pt.getGiaTien(), pt.getSoLuong(), loaipt};
			dataModel.addRow(row);
		}
	}
	
	// update khu
	private TonKho updateKhu_DeletedPT(String mapt) {
		PhuTungXe deletedPT = ptBAL.get_PTByID(mapt); // delete sl pt trong kho
		TonKho tk = tonkhoBAL.get_KhuByID(deletedPT.getMaKhu());
		TonKho newKhu = new TonKho(tk.getMaKhu()
								, tk.getSoLuong()-deletedPT.getSoLuong()
								, tk.getSoLuongToiDa());
		return newKhu;
	}

	
	// nap cbo loai
	private String[] napCboBoxLPT() {
		String loaipt = "";
		List<LoaiPhuTungXe> lstLoaiPTX = loaiPtBAL.getAll_LoaiPT();
		int countLoaiPT = tvBAL.Count("LoaiPhuTung"); // dem ma loai pt
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
	
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Quản lí phụ tùng");  // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		if(o.equals(btnXoa)){ //Xoa phu tung
			int row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phụ tùng này?"
																	, "Title on Box"
																	, dialogButton);
				
				String maPT =	(String) table.getValueAt(row, 0); //get before delete
				ptton = pttonBAL.get_PTTonByID(maPT);
				PhuTungXe ptDelete = ptBAL.get_PTByID(maPT);
				
				if(dialogResult == 0) {
					String mapt = (String) table.getValueAt(row, 0);
					if(tonkhoBAL.update(updateKhu_DeletedPT(mapt))){
						if(ptBAL.delete(mapt)){
							dataModel.removeRow(row);
							
						////// cap nhat vao phieu nhap, pt ton,  ton trong thang
						//////
						//////
							
							
							TonKhoTrongThang tktt;
							tktt = tkttBAL.get_TKTTByID_Thang(ptton.getThangNhap(), ptton.getNamNhap());
							
							TonKhoTrongThang tkttNew =  new TonKhoTrongThang(tktt.getMaTon()
																	, tktt.getTongSLNhap() - ptDelete.getSoLuong()
																	, tktt.getTongSLTon() - ptDelete.getSoLuong()
																	, tktt.getTongSLXuat(), tktt.getTongChiPhiXuat()
																	, tktt.getThangTon(), tktt.getNamTon());;
							if (tkttBAL.update(tkttNew)) {
								System.out.println("update ton_kho_thang success");
							}
							//////
							//////
							////// cap nhat vao phieu nhap, pt ton,  ton trong thang
							
							JOptionPane.showMessageDialog(null, "xóa thành công!");							
						}
					}
					
					else
						JOptionPane.showMessageDialog(null, "Phụ tùng này có không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phụ tùng để xóa!");
		}else if(o.equals(btnThemPT)){ //them thong tin phu tung
			frame.dispose();
			QuanLy_AddPT_Form ql_AddPT = new QuanLy_AddPT_Form(get_Login_Text);
			ql_AddPT.frmThmPhTng.setVisible(true); // FIX name LATER
		}else if(o.equals(btnSuaPT)){ //Sua thong tin phu tung
			int row = table.getSelectedRow();
			if(row >= 0){
				frame.dispose();
				String maPT =	(String) table.getValueAt(row, 0);
				QuanLy_UpdatePT_Form ql_UpdatPT = new QuanLy_UpdatePT_Form(get_Login_Text, maPT);
				ql_UpdatPT.frame.setVisible(true);
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phụ tùng để cập nhật!");
		}else if(o.equals(btnChiTiet)){ // Xem chi tiet phu tung
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maPT = (String) table.getValueAt(row, 0);
				QuanLy_DetailPT_Form ql_DetailPT = new QuanLy_DetailPT_Form(get_Login_Text, maPT);
				ql_DetailPT.frm.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phụ tùng để xem!");
		}else if (o.equals(btnSapXep)) {
			String cboItem = (String) cbo_SapXep.getSelectedItem();
			if(cboItem.equalsIgnoreCase("Tat ca")){
				createTable();
			}else {
				Connection con = DataBase.getConnection();
				PreparedStatement stmt = null;
				
				try {
					stmt = con.prepareStatement("select pt.MaPT as '" + headers[0] + "'"
							+ ", pt.TenPT as '" + headers[1] + "'"
							+ ", pt.GiaTien as '" + headers[2] + "'"
							+ ", pt.SoLuong as '" + headers[3] + "' "
							+ ", lpt.LoaiPT as '" + headers[4] + "' "
							+ ", pt.Makhu as '" + "Mã khu vực" + "' "
							+ "from PhuTung AS pt INNER JOIN "
							+ "LoaiPhuTung AS lpt ON pt.MaLoaiPTX = lpt.MaLoai " 
							+ "where lpt.LoaiPT = ?");
					stmt.setString(1, cboItem);
					ResultSet rs = stmt.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
			
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("PhuTung");
		}else if(o.equals(btnXuatPhuTung)){ // Xuat pt

			int row = table.getSelectedRow();
			if(row>=0){
				String maPT = (String) table.getValueAt(row, 0);
				int soLuongXuat = Integer.parseInt(txtSL.getText());
				PhuTungXe ptXuat = ptBAL.get_PTByID(maPT);
				ptton = pttonBAL.get_PTTonByID(maPT);
				pn = pnBAL.get_PhieuNhapByID(maPT);
				
				if( txtSL.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Bạn hãy điền số lượng!");
				else if(ptXuat.getSoLuong() < soLuongXuat )
						 JOptionPane.showMessageDialog(null, "Bạn xuất nhiều hơn số hiện có, hãy nhập lại");
				else {

					int newID = UI_Helpers.addNewID("PhieuXuat"); //tao index tiep theo cho list
					String maPX = "PX" + newID;
					double chiPhi = ptXuat.getGiaTien() * soLuongXuat;
					Calendar today = Calendar.getInstance(); 
					PhieuXuat px = new PhieuXuat(maPX, maPT, soLuongXuat, today.getTime(), chiPhi);
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất phụ tùng này?"
																		, "Title on Box"
																		, dialogButton);
					if(dialogResult == 0) {
						if(pxBAl.create(px)){
							JOptionPane.showMessageDialog(null, "Bạn vừa xuất phụ tùng!");
							
							UI_Helpers.updateNewID("PhieuXuat", newID);
							
							////// sua phieu nhap, pt ton,  ton trong thang & PT
							//////
							//////
							PhuTungXe ptXuatNew = new PhuTungXe(maPT, ptXuat.getTenPT()
									, ptXuat.getGiaTien(), ptXuat.getMaNhaCungCap()
									, ptXuat.getMaLoaiPTX(), ptXuat.getLoaiXe(), ptXuat.getHang()
									, ptXuat.getSoLuong()- soLuongXuat, ptXuat.getMaKhu());
							PhieuNhap pnNew = new PhieuNhap(pn.getMaPT()
														, pn.getSoLuongNhap() - soLuongXuat
														, pn.getNgayNhapHang());

							PhuTungTon pttonNew = new PhuTungTon(ptton.getMaPT(), ptton.getSoLuongBanDau()
																, ptton.getSoLuongHienTai() - soLuongXuat
																, ptton.getThangNhap(), ptton.getNamNhap());	 

							if (ptBAL.update(ptXuatNew)) {
								System.out.println("update ptXuatNew success");
								createTable();
							}
							if (pnBAL.update(pnNew)) {
								System.out.println("update pn success");
							}
							if (pttonBAL.create(pttonNew)) {
								System.out.println("create ptton success");
							}

							TonKhoTrongThang tktt;
							tktt = tkttBAL.get_TKTTByID_Thang(ptton.getThangNhap(), ptton.getNamNhap());
							
								TonKhoTrongThang tkttNew =  new TonKhoTrongThang(tktt.getMaTon()
																	, tktt.getTongSLNhap()
																	, tktt.getTongSLTon() - soLuongXuat
																	, tktt.getTongSLXuat()+ soLuongXuat, tktt.getTongChiPhiXuat()
																	, tktt.getThangTon(), tktt.getNamTon());;
								if (tkttBAL.update(tkttNew)) {
									System.out.println("update ton_kho_thang success");
								}
							
							TonKho tk;
							tk = tonkhoBAL.get_KhuByID(ptXuat.getMaKhu());
							
							TonKho tkNew =  new TonKho(tk.getMaKhu()
													, tk.getSoLuong() - soLuongXuat
													, tk.getSoLuongToiDa());
							if (tonkhoBAL.update(tkNew)) {
								System.out.println("update TonKho success");
							}
						
							//////
							//////
							////// sua phieu nhap, pt ton,  ton trong thang
						}
					}
				}

			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phụ tùng để xuất!");
			
			
		}else if(o.equals(btnCapNhatGia)){ // Cap nhat gia
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật giá tiền theo khuyến mãi này?"
																, "Title on Box"
																, dialogButton);
			
			if(dialogResult == 0) {				
				List<KhuyenMai> listKM = kmBAL.getAll_KM();
				for (KhuyenMai km : listKM) {
					ptBAL.updatePT_KM(km.getMaPT(), km.getGiaTienKM() + (km.getGiaTienKM()/10));
					
				}
			}
			
			createTable();
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
				QuanLy_Form_Main frmMain = new QuanLy_Form_Main(get_Login_Text);
				frmMain.frame.setVisible(true);				
			}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
				NhanVien_Form_Main frmMain = new NhanVien_Form_Main(get_Login_Text);
				frmMain.frame.setVisible(true);	
			}
		}
//		else if(o.equals(btnNhapPT)){ // nhap so luong phu tung
//		if(btnNhapPT.getText()=="Nhap phu tung"){ // TODO - Undone
//			btnNhapPT.setText("Xong");
////			txtNhapPT.setEnabled(true);
//			txtNhapPT.setEditable(true);
//		}else if(btnNhapPT.getText()=="Xong"){
//			btnNhapPT.setText("Nhap phu tung");
////			txtNhapPT.setEnabled(false);
//			txtNhapPT.setEditable(false);
//		}
//	}
		
	}
}
