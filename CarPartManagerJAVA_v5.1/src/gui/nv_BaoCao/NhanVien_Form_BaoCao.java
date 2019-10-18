package gui.nv_BaoCao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import bAL.LoaiPhuTungBAL;
import bAL.PhuTungBAL;
import bAL.QuanLiVienBAL;
import bAL.ThanhVienBAL;
import bAL.TonKhoBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.TonKho;
import entity.users.QuanLiVien;
import entity.users.ThanhVien;
import gui.main.LoginGUI;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import gui.ql_KM.QuanLy_Form_KM;
import gui.ql_NhanVien.QuanLy_Form_NV;
import gui.ql_TonKho.QuanLy_Form_Kho;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;
import gui.ql_nv_PhuTung.QuanLy_UpdatePT_Form;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class NhanVien_Form_BaoCao extends JFrame implements ActionListener {

	//Region - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	/**
	 *@param Xóa thành viên
	 */
	JButton btnQLBH = new JButton("Bao cao ban hang");
	/**
	 *@param Sửa Thông Tin phu tung
	 */
	JButton btnKM = new JButton("Bao cao khuyen mai");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	/**
	 *@param Xem Danh sách Tác giả Đăng Ký
	 */
	JButton btnBCPTtheoKhu = new JButton("Bao cao phu tung theo khu");
	
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	/**
	 *@param nap loại bảng
	 */
	JButton btnBCPT = new JButton("Bao cao phu tung");
	JButton btnQLHoaDon = new JButton("Bao cao hoa don");
	private final JButton btnQLTK = new JButton("Bao cao ton kho");
	JButton btnQuayLai = new JButton("Quay lai");
	JButton btnQLKH = new JButton("Bao cao khach hang");
	JComboBox<String> cboLPT = new JComboBox<String>();

	private PhuTungBAL ptBAL = new PhuTungBAL();
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private LoaiPhuTungBAL loaiPtBAL = new LoaiPhuTungBAL();
	private TonKhoBAL tkBAL = new TonKhoBAL();
	
	//EndRegion
	
	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_Form_BaoCao window = new NhanVien_Form_BaoCao();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//EndRegion

	//Region - UI components
	/**
	 * Create the application.
	 */
	public NhanVien_Form_BaoCao() {
		initialize();
	}
	
	public NhanVien_Form_BaoCao(String ten) {
		get_Login_Text =ten;
		lblTenlogin.setText(get_Login_Text);
		
		initialize();
	}
	//EndRegion

	//Region - init components
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
		getFrame().setBounds(100, 100, 651, 406);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(24, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNV = new JLabel("Nhân viên:");
		lblNV.setBounds(66, 44, 102, 14);
		getFrame().getContentPane().add(lblNV);
		
		btnBCPTtheoKhu.setBackground(Color.CYAN);
		btnBCPTtheoKhu.setBounds(24, 119, 199, 43);
		getFrame().getContentPane().add(btnBCPTtheoKhu);

		btnKM.setBackground(Color.CYAN);
		btnKM.setBounds(245, 193, 177, 43);
		getFrame().getContentPane().add(btnKM);
		
		
		btnQLBH.setBackground(Color.CYAN);
		btnQLBH.setBounds(444, 193, 177, 43);
		frame.getContentPane().add(btnQLBH);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(444, 24, 177, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(178, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);	

				btnBCPT.setBounds(24, 193, 199, 43);
				btnBCPT.setBackground(Color.CYAN);
				frame.getContentPane().add(btnBCPT);

				btnQLKH.setBackground(Color.CYAN);
				btnQLKH.setBounds(24, 247, 199, 43);
				frame.getContentPane().add(btnQLKH);
				
				btnQLHoaDon.setBackground(Color.CYAN);
				btnQLHoaDon.setBounds(245, 247, 177, 43);
				frame.getContentPane().add(btnQLHoaDon);
				btnQLTK.setBackground(Color.CYAN);
				btnQLTK.setBounds(444, 247, 177, 43);
				
				frame.getContentPane().add(btnQLTK);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(444, 78, 177, 43);
				frame.getContentPane().add(btnQuayLai);
				
				JLabel lblKhu = new JLabel("Khu:");
				lblKhu.setHorizontalAlignment(SwingConstants.CENTER);
				lblKhu.setBounds(245, 119, 63, 43);
				frame.getContentPane().add(lblKhu);
				
				// add to cbo pt
				String[] lstKhuVuc = napCboBoxKhu(); //TODO - fill cbo loai PT xe
				cboLPT.setModel(new DefaultComboBoxModel<String>(lstKhuVuc));
				
				cboLPT.setBounds(318, 119, 104, 43);
				frame.getContentPane().add(cboLPT);
				
				btnQLBH.addActionListener(this);
				btnKM.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnBCPTtheoKhu.addActionListener(this);
				btnBCPT.addActionListener(this);
				btnKM.addActionListener(this);
				btnQLTK.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnQLKH.addActionListener(this);
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// nap cbo loai
	private String[] napCboBoxKhu() {
		List<TonKho> lstKhu = tkBAL.getAll_Khu();
		int countKhu = tvBAL.Count("TonKho"); // dem ma kho
		String[] lstMaKhu = new String[countKhu]; 
		int i=0;
		
		for (TonKho tk : lstKhu) {
			lstMaKhu[i] = tk.getMaKhu();
			i++;
		}
		return lstMaKhu;
	}
	//EndRegion
	
	//Region - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Báo Cáo");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - event handler
	// TODO Chuc Nang button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnBCPT)){ //quan li phu tung
			UI_Helpers.exportToExcel("PhuTung");
		}else if(o.equals(btnBCPTtheoKhu)){ //quan li pt (khu)
			String khu = cboLPT.getSelectedItem().toString();
			
			try {
				UI_Helpers.exportToExcel("PhuTung", "MaKhu", khu);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy!");
				e.printStackTrace();
			}
			
		}else if(o.equals(btnQLBH)){ //quan li BH
			
		}else if(o.equals(btnKM)){ //Sua thong tin TV
			UI_Helpers.exportToExcel("KhuyenMai");
		}else if(o.equals(btnQLKH)){ // quan li khuyen mai
			UI_Helpers.exportToExcel("KhacHang");
		}else if(o.equals(btnQLHoaDon)){ // quan li hoa don
			
		}else if(o.equals(btnQLTK)){ // quan li hoa don
			UI_Helpers.exportToExcel("TonKho");
		}else if(o.equals(btnQuayLai)){ // Dang Xuat
			frame.dispose();
			NhanVien_Form_Main NVfrmMain = new NhanVien_Form_Main(get_Login_Text);
			NVfrmMain.frame.setVisible(true);			
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			frame.dispose();
			LoginGUI Lg = new LoginGUI();
			Lg.frame.setVisible(true);
		}
		
//		else if(o.equals(btnTuVan)){ // quan li Tu Van
//			
//		}
		
	}
}
