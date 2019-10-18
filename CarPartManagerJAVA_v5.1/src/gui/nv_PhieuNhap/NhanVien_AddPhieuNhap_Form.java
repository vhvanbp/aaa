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
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.LoaiPhuTungBAL;
import bAL.PhieuNhapBAL;
import bAL.PhuTungBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhuTungXe;
import helpers.UI_Helpers;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NhanVien_AddPhieuNhap_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
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
	private JTextField txtSL;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;				
	JComboBox<String> cboPT = new JComboBox<String>();
	
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	NhanVien_Form_PhieuNhap qlLPT = new NhanVien_Form_PhieuNhap(get_Login_Text);
	private PhuTungBAL ptBAL = new PhuTungBAL();
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_AddPhieuNhap_Form window = new NhanVien_AddPhieuNhap_Form();
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
	public NhanVien_AddPhieuNhap_Form() {
		initialize();
	}
	
	public NhanVien_AddPhieuNhap_Form(String tenLogin) {
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
		getFrame().setBounds(100, 100, 556, 320);
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

		//TODO - need ICON
//		Image img = new ImageIcon(this.getClass().getResource("/add_icon.jpg")).getImage().getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(189, 206, 165, 43);
		getFrame().getContentPane().add(btnThem);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(364, 206, 165, 43);
		frm.getContentPane().add(btnDangXuat);
		
		JLabel lblNhpTn = new JLabel("Chọn phụ tùng:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(14, 77, 112, 14);
		frm.getContentPane().add(lblNhpTn);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(14, 206, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblSL = new JLabel("Nhập số lượng:");
				lblSL.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSL.setBounds(14, 107, 112, 14);
				frm.getContentPane().add(lblSL);
				
				txtSL = new JTextField();
				txtSL.setColumns(10);
				txtSL.setBounds(132, 102, 161, 24);
				frm.getContentPane().add(txtSL);

				String[] lstPTX = napCboBoxPT(); //TODO - fill cbo loai PT xe
				cboPT.setModel(new DefaultComboBoxModel<String>(lstPTX));
				cboPT.setBounds(132, 74, 161, 20);
				frm.getContentPane().add(cboPT);
				
				JLabel lblNhpNgy = new JLabel("Nhập ngày:");
				lblNhpNgy.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpNgy.setBounds(10, 139, 112, 14);
				frm.getContentPane().add(lblNhpNgy);
				
				txtDay = new JTextField();
				txtDay.setColumns(10);
				txtDay.setBounds(132, 136, 30, 20);
				frm.getContentPane().add(txtDay);
				
				JLabel label_1 = new JLabel("/");
				label_1.setHorizontalAlignment(SwingConstants.RIGHT);
				label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_1.setBounds(161, 136, 12, 21);
				frm.getContentPane().add(label_1);
				
				txtMonth = new JTextField();
				txtMonth.setColumns(10);
				txtMonth.setBounds(183, 136, 30, 20);
				frm.getContentPane().add(txtMonth);
				
				JLabel label_2 = new JLabel("/");
				label_2.setHorizontalAlignment(SwingConstants.RIGHT);
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				label_2.setBounds(212, 136, 12, 21);
				frm.getContentPane().add(label_2);
				
				txtYear = new JTextField();
				txtYear.setColumns(10);
				txtYear.setBounds(234, 136, 59, 20);
				frm.getContentPane().add(txtYear);
				
				//TODO - Register before perform action
				btnThem.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	//xoa rong
	private void xoaRong_Txt(){
		cboPT.setSelectedIndex(0);
		txtSL.setText("");
		txtDay.setText("");
		txtMonth.setText("");
		txtYear.setText("");
		txtSL.requestFocus();
	}
	
	// nap cbo pt
	private String[] napCboBoxPT() {
		List<PhuTungXe> lstPTX =ptBAL.getAll_PT();
		int countPT = tvBal.Count("PhuTung"); // dem ma 
		String[] lstmaPT = new String[countPT];
		int i = 0;
		for (PhuTungXe pt : lstPTX) {
			lstmaPT[i] = pt.getMaPT();
			i++;
		}
		return lstmaPT;
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Thêm phiếu nhập");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();

		if(o.equals(btnThem)){ //Them thong tin loai phu tung
			//Region - get text from ui
			String maPT = cboPT.getSelectedItem().toString();
			String day =	txtDay.getText();
			String month =	txtMonth.getText();
			String year =	txtYear.getText();
			String sl =	txtSL.getText();
			//EndRegion
			
			//Region - function create with condition
			if( day.equals("") || month.equals("") || year.equals("") || sl.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
			else if( pnBAL.Check_id(maPT)==1 )
				JOptionPane.showMessageDialog(null, "Phụ tùng này đã có phiếu nhập!" );
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
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm phiếu nhập này?"
																	, "Title on Box"
																	, dialogButton);
					
				if(dialogResult == 0) {
					PhieuNhap lpt = new PhieuNhap(maPT, soLuong, ngayNhapPhieu);
						
					if(pnBAL.create(lpt)){
						JOptionPane.showMessageDialog(null, "Bạn vừa thêm phiếu nhập!");

						frm.dispose();
						qlLPT = new NhanVien_Form_PhieuNhap(get_Login_Text);
						qlLPT.frame.setVisible(true);
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
			qlLPT.frame.setVisible(true);
		}
	}
	//EndRegion
	
}
