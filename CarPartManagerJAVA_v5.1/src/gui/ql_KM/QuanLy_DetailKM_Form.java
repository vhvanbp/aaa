package gui.ql_KM;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.KhuyenMaiBAL;
import bAL.NhaCungCapBAL;
import bAL.PhuTungBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.KhuyenMai;
import entity.carPart.PhuTungXe;
import entity.users.ThanhVien;

public class QuanLy_DetailKM_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtMaKM;

	private JTextField txtTenKM;
	private JTextField txtStartDay;
	private JTextField txtStartMonth;
	private JTextField txtStartYear;
	private JTextField txtEndDay;
	private JTextField txtEndMonth;
	private JTextField txtEndYear;
	private JTextField txtNCC;
	private JTextField txtPT;
	
	JLabel lblTextMoTa = new JLabel("");
	
	private String get_maKM_Text;
	private KhuyenMai km;
	private KhuyenMaiBAL kmBAL = new KhuyenMaiBAL();
	private PhuTungBAL ptBAL = new PhuTungBAL();
	private NhaCungCapBAL nccBAL = new NhaCungCapBAL();
	private JTextField txtGiatienKM;
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_DetailKM_Form window = new QuanLy_DetailKM_Form();
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
	public QuanLy_DetailKM_Form() {
		initialize();
	}
	
	public QuanLy_DetailKM_Form(String tenLogin, String maKM) {
		get_Login_Text =tenLogin;
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
		getFrame().setBounds(100, 100, 636, 370);
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
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(235, 249, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblMaKM = new JLabel("Mã khuyến mãi:");
				lblMaKM.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaKM.setBounds(19, 72, 113, 14);
				frm.getContentPane().add(lblMaKM);
				
				txtMaKM = new JTextField();
				txtMaKM.setText("ma TV");
				txtMaKM.setEditable(false);
				txtMaKM.setColumns(10);
				txtMaKM.setBounds(143, 69, 160, 20);
				frm.getContentPane().add(txtMaKM);
				
				JLabel lblTnKhuynMi = new JLabel("Tên khuyến mãi:");
				lblTnKhuynMi.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTnKhuynMi.setBounds(10, 100, 123, 14);
				frm.getContentPane().add(lblTnKhuynMi);
				
				txtTenKM = new JTextField();
				txtTenKM.setEditable(false);
				txtTenKM.setColumns(10);
				txtTenKM.setBounds(143, 97, 161, 20);
				frm.getContentPane().add(txtTenKM);
				
				JLabel lblPhTng = new JLabel("Phụ tùng:");
				lblPhTng.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPhTng.setBounds(326, 69, 112, 23);
				frm.getContentPane().add(lblPhTng);
				
				JLabel lblNgyBtu = new JLabel("Ngày bắt đầu:");
				lblNgyBtu.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNgyBtu.setBounds(10, 134, 123, 14);
				frm.getContentPane().add(lblNgyBtu);
				
				txtStartDay = new JTextField();
				txtStartDay.setEditable(false);
				txtStartDay.setColumns(10);
				txtStartDay.setBounds(143, 130, 30, 20);
				frm.getContentPane().add(txtStartDay);
				
				JLabel label_3 = new JLabel("/");
				label_3.setHorizontalAlignment(SwingConstants.RIGHT);
				label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_3.setBounds(172, 130, 12, 21);
				frm.getContentPane().add(label_3);
				
				txtStartMonth = new JTextField();
				txtStartMonth.setEditable(false);
				txtStartMonth.setColumns(10);
				txtStartMonth.setBounds(194, 130, 30, 20);
				frm.getContentPane().add(txtStartMonth);
				
				JLabel label_4 = new JLabel("/");
				label_4.setHorizontalAlignment(SwingConstants.RIGHT);
				label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_4.setBounds(223, 130, 12, 21);
				frm.getContentPane().add(label_4);
				
				txtStartYear = new JTextField();
				txtStartYear.setEditable(false);
				txtStartYear.setColumns(10);
				txtStartYear.setBounds(245, 130, 59, 20);
				frm.getContentPane().add(txtStartYear);
				
				JLabel lblNhCungCp = new JLabel("Nhà cung cấp:");
				lblNhCungCp.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhCungCp.setBounds(326, 103, 112, 23);
				frm.getContentPane().add(lblNhCungCp);
				
				JLabel lblNgyKtThc = new JLabel("Ngày kết thúc:");
				lblNgyKtThc.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNgyKtThc.setBounds(10, 169, 123, 14);
				frm.getContentPane().add(lblNgyKtThc);
				
				txtEndDay = new JTextField();
				txtEndDay.setEditable(false);
				txtEndDay.setColumns(10);
				txtEndDay.setBounds(143, 165, 30, 20);
				frm.getContentPane().add(txtEndDay);
				
				txtEndMonth = new JTextField();
				txtEndMonth.setEditable(false);
				txtEndMonth.setColumns(10);
				txtEndMonth.setBounds(194, 165, 30, 20);
				frm.getContentPane().add(txtEndMonth);
				
				JLabel label_7 = new JLabel("/");
				label_7.setHorizontalAlignment(SwingConstants.RIGHT);
				label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_7.setBounds(172, 165, 12, 21);
				frm.getContentPane().add(label_7);
				
				JLabel label_8 = new JLabel("/");
				label_8.setHorizontalAlignment(SwingConstants.RIGHT);
				label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_8.setBounds(223, 165, 12, 21);
				frm.getContentPane().add(label_8);
				
				txtEndYear = new JTextField();
				txtEndYear.setEditable(false);
				txtEndYear.setColumns(10);
				txtEndYear.setBounds(245, 165, 59, 20);
				frm.getContentPane().add(txtEndYear);
				
				JLabel lblMT = new JLabel("Mô tả:");
				lblMT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMT.setBounds(326, 141, 112, 14);
				frm.getContentPane().add(lblMT);
				
				txtNCC = new JTextField();
				txtNCC.setEditable(false);
				txtNCC.setColumns(10);
				txtNCC.setBounds(444, 104, 165, 20);
				frm.getContentPane().add(txtNCC);
				
				txtPT = new JTextField();
				txtPT.setEditable(false);
				txtPT.setColumns(10);
				txtPT.setBounds(444, 69, 165, 20);
				frm.getContentPane().add(txtPT);
				lblTextMoTa.setVerticalAlignment(SwingConstants.TOP);

				lblTextMoTa.setBounds(444, 141, 165, 60);
				frm.getContentPane().add(lblTextMoTa);
				
				txtGiatienKM = new JTextField();
				txtGiatienKM.setText("0.0");
				txtGiatienKM.setEditable(false);
				txtGiatienKM.setColumns(10);
				txtGiatienKM.setBounds(142, 194, 161, 20);
				frm.getContentPane().add(txtGiatienKM);
				
				JLabel lblGiaKM = new JLabel("Giá tiền:");
				lblGiaKM.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGiaKM.setBounds(8, 197, 123, 14);
				frm.getContentPane().add(lblGiaKM);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layKM_tuForm();
				napKM_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu KM tu form truoc
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
			int monthStart = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar) bat dau tu 0
			int dayStart = cal.get(Calendar.DAY_OF_MONTH);
		txtStartDay.setText(dayStart + "");
		txtStartMonth.setText(monthStart + "");
		txtStartYear.setText(yearStart + "");
			cal.setTime(km.getNgayKetThuc());
			int yearEnd = cal.get(Calendar.YEAR);
			int monthEnd = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar) bat dau tu 0
			int dayEnd = cal.get(Calendar.DAY_OF_MONTH);
		txtEndDay.setText(dayEnd + "");
		txtEndMonth.setText(monthEnd + "");
		txtEndYear.setText(yearEnd + "");
		txtPT.setText(getTenPT());
		txtNCC.setText(getTenNCC());
		lblTextMoTa.setText("<html>"+ km.getMoTaKM() +"</html>");
		txtGiatienKM.setText(km.getGiaTienKM()+"");
	}
	
	// get loai
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
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Chi tiết khuyến mãi");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			QuanLy_Form_KM ql_KM = new QuanLy_Form_KM(get_Login_Text);
			ql_KM.frame.setVisible(true);
		}
	}
}
