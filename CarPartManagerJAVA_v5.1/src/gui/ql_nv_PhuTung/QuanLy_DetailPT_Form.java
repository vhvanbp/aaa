package gui.ql_nv_PhuTung;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.PhieuNhapBAL;
import bAL.PhuTungBAL;
import bAL.PhuTungTonBAL;
import bAL.ThanhVienBAL;
import bAL.TonKhoTrongThangBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.PhieuNhap;
import entity.carPart.PhuTungTon;
import entity.carPart.PhuTungXe;

public class QuanLy_DetailPT_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnSua = new JButton("Sua");
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	JButton btnQuayLai = new JButton("Quay lai");
	JButton btnXoa = new JButton("Xoa");
	private String get_maPT_Text;
	private JTextField txtHang;
	private JTextField txtGiaTien;
	private JTextField txtTenPT;
	private JTextField txtLoaiXe;
	private JTextField txtMaPT;
	private JTextField txtNCC;
	private JTextField txtLoaiPT;

	private PhuTungXe pt;
	private PhuTungBAL ptBAL = new PhuTungBAL();
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private JTextField txtSL;
	private JTextField txtKhu;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();	
	private PhuTungTonBAL pttonBAL = new PhuTungTonBAL();	
	private TonKhoTrongThangBAL tkttBAL = new TonKhoTrongThangBAL();	
	private PhieuNhap pn;
	private PhuTungTon ptton;
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_DetailPT_Form window = new QuanLy_DetailPT_Form();
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
	public QuanLy_DetailPT_Form() {
		initialize();
	}
	
	public QuanLy_DetailPT_Form(String tenLogin, String maNV) {
		get_Login_Text =tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maPT_Text = maNV;
		pn = pnBAL.get_PhieuNhapByID(maNV); // lay phieu tu ma pt
		ptton = pttonBAL.get_PTTonByID(maNV); // lay ptt tu ma pt
		
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
		getFrame().setBounds(100, 100, 636, 360);
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
		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(430, 256, 165, 43);
		getFrame().getContentPane().add(btnSua);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(29, 256, 165, 43);
				frm.getContentPane().add(btnQuayLai);

				btnXoa.setBackground(Color.CYAN);
				btnXoa.setBounds(228, 256, 165, 43);
				frm.getContentPane().add(btnXoa);
				
				txtHang = new JTextField();
				txtHang.setEditable(false);
				txtHang.setColumns(10);
				txtHang.setBounds(131, 163, 161, 20);
				frm.getContentPane().add(txtHang);
				
				txtGiaTien = new JTextField();
				txtGiaTien.setEditable(false);
				txtGiaTien.setColumns(10);
				txtGiaTien.setBounds(131, 131, 161, 20);
				frm.getContentPane().add(txtGiaTien);
				
				JLabel lblGiaTien = new JLabel("Giá tiền:");
				lblGiaTien.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGiaTien.setBounds(9, 134, 112, 14);
				frm.getContentPane().add(lblGiaTien);
				
				JLabel lblHang = new JLabel("Hãng:");
				lblHang.setHorizontalAlignment(SwingConstants.RIGHT);
				lblHang.setBounds(9, 166, 113, 14);
				frm.getContentPane().add(lblHang);
				
				JLabel lblTenPT = new JLabel("Tên phụ tùng:");
				lblTenPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTenPT.setBounds(9, 103, 112, 14);
				frm.getContentPane().add(lblTenPT);
				
				txtTenPT = new JTextField();
				txtTenPT.setEditable(false);
				txtTenPT.setColumns(10);
				txtTenPT.setBounds(131, 100, 161, 20);
				frm.getContentPane().add(txtTenPT);
				
				JLabel lblLoaiPT = new JLabel("Loại phụ tùng:");
				lblLoaiPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiPT.setBounds(312, 75, 113, 22);
				frm.getContentPane().add(lblLoaiPT);
				
				JLabel lblNCC = new JLabel("Nhà cung cấp:");
				lblNCC.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNCC.setBounds(312, 106, 112, 14);
				frm.getContentPane().add(lblNCC);
				
				JLabel lblLoaiXe = new JLabel("Loại xe:");
				lblLoaiXe.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiXe.setBounds(313, 134, 112, 14);
				frm.getContentPane().add(lblLoaiXe);
				
				txtLoaiXe = new JTextField();
				txtLoaiXe.setEditable(false);
				txtLoaiXe.setColumns(10);
				txtLoaiXe.setBounds(434, 131, 161, 20);
				frm.getContentPane().add(txtLoaiXe);
				
				txtMaPT = new JTextField();
				txtMaPT.setText((String) null);
				txtMaPT.setEditable(false);
				txtMaPT.setColumns(10);
				txtMaPT.setBounds(132, 72, 160, 20);
				frm.getContentPane().add(txtMaPT);
				
				JLabel lblMaPT = new JLabel("Mã phụ tùng:");
				lblMaPT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaPT.setBounds(9, 75, 113, 14);
				frm.getContentPane().add(lblMaPT);
				
				txtNCC = new JTextField();
				txtNCC.setEditable(false);
				txtNCC.setColumns(10);
				txtNCC.setBounds(434, 103, 161, 20);
				frm.getContentPane().add(txtNCC);
				
				txtLoaiPT = new JTextField();
				txtLoaiPT.setEditable(false);
				txtLoaiPT.setColumns(10);
				txtLoaiPT.setBounds(434, 75, 161, 20);
				frm.getContentPane().add(txtLoaiPT);
				
				txtSL = new JTextField();
				txtSL.setEditable(false);
				txtSL.setColumns(10);
				txtSL.setBounds(434, 163, 161, 20);
				frm.getContentPane().add(txtSL);
				
				JLabel lblSL = new JLabel("Số lượng:");
				lblSL.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSL.setBounds(312, 166, 113, 14);
				frm.getContentPane().add(lblSL);
				
				JLabel lblKhu = new JLabel("Khu vực:");
				lblKhu.setHorizontalAlignment(SwingConstants.RIGHT);
				lblKhu.setBounds(9, 198, 112, 14);
				frm.getContentPane().add(lblKhu);
				
				txtKhu = new JTextField();
				txtKhu.setText((String) null);
				txtKhu.setEditable(false);
				txtKhu.setColumns(10);
				txtKhu.setBounds(131, 194, 161, 20);
				frm.getContentPane().add(txtKhu);
				
				txtDay = new JTextField();
				txtDay.setText("29");
				txtDay.setEditable(false);
				txtDay.setColumns(10);
				txtDay.setBounds(434, 191, 30, 20);
				frm.getContentPane().add(txtDay);
				
				JLabel label = new JLabel("Ngày nhập:");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setBounds(312, 194, 112, 14);
				frm.getContentPane().add(label);
				
				txtMonth = new JTextField();
				txtMonth.setText("7");
				txtMonth.setEditable(false);
				txtMonth.setColumns(10);
				txtMonth.setBounds(485, 191, 30, 20);
				frm.getContentPane().add(txtMonth);
				
				JLabel label_1 = new JLabel("/");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_1.setBounds(463, 191, 12, 21);
				frm.getContentPane().add(label_1);
				
				JLabel label_2 = new JLabel("/");
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_2.setBounds(514, 191, 12, 21);
				frm.getContentPane().add(label_2);
				
				txtYear = new JTextField();
				txtYear.setText("2018");
				txtYear.setEditable(false);
				txtYear.setColumns(10);
				txtYear.setBounds(536, 191, 59, 20);
				frm.getContentPane().add(txtYear);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXoa.addActionListener(this);
				
				// methods from support region
				layPT_tuForm();
				napPT_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layPT_tuForm() {
		 pt = ptBAL.get_PTByID(get_maPT_Text);
	}

	// nap du lieu tu bang vao Textfield
	private void napPT_VaoTextfields() {
		txtMaPT.setText(get_maPT_Text);
		txtTenPT.setText(pt.getTenPT());
		txtGiaTien.setText(pt.getGiaTien()+"");
		String tenNCC = ptBAL.RetrieveNCC(pt.getMaNhaCungCap()); // get ten NCC
		txtNCC.setText(tenNCC);
		String tenLPT = ptBAL.RetrieveLoaiPT(pt.getMaLoaiPTX()); // get ten LPT
		txtLoaiPT.setText(tenLPT);
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
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Chi tiết phụ tùng");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin phu tung
			frm.dispose();
			QuanLy_UpdatePT_Form ql_UpdatePT = new QuanLy_UpdatePT_Form(get_Login_Text,get_maPT_Text);
			ql_UpdatePT.frame.setVisible(true);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			if(tvBAL.Retrieve(get_Login_Text).equals("Quan li")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);			
			}else if(tvBAL.Retrieve(get_Login_Text).equals("Nhan vien")){
				QuanLy_Form_PT pt = new QuanLy_Form_PT(get_Login_Text);
				pt.frame.setVisible(true);	
			}
		}else if(o.equals(btnXoa)){ // xoa phu tung
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phụ tùng này?", 
																	"Title on Box", 
																	dialogButton);
			if(dialogResult == 0) {
				String mapt = txtMaPT.getText();
				if(ptBAL.delete(mapt)){
					JOptionPane.showMessageDialog(null, "xóa thành công!");
					
					frm.dispose();
					QuanLy_Form_PT qlPT = new QuanLy_Form_PT(get_Login_Text);
					qlPT.frame.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Phụ tùng này không xóa được");
			}
			
		}
		
	}
}
