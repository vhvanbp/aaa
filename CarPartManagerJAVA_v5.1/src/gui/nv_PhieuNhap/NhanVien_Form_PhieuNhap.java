package gui.nv_PhieuNhap;

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
import bAL.PhieuNhapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class NhanVien_Form_PhieuNhap extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTable table;
	/**
	 *@param Xóa loai phu tung
	 */
	JButton btnXoaLPT = new JButton("Xoa phieu nhap");
	/**
	 *@param Sửa Thông Tin loai phu tung
	 */
	JButton btnSuaLPT = new JButton("Sua phieu nhap");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboLPT = new JComboBox<String>();
	JButton btnThemLPT = new JButton("Them phieu nhap");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private PhieuNhapBAL pnBAL = new PhieuNhapBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách lên bảng
	// Tao dataModel
	String[] headers = {"Mã phiếu nhập", "Số lượng nhập", "Ngày nhập hàng"};	
	private final JButton btnXuatExcel = new JButton("Xuat bao cao");
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_Form_PhieuNhap window = new NhanVien_Form_PhieuNhap();
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
	public NhanVien_Form_PhieuNhap() {
		initialize();
	}
	
	public NhanVien_Form_PhieuNhap(String ten) {
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
		getFrame().setBounds(100, 100, 685, 478);
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

		btnSuaLPT.setBackground(Color.CYAN);
		btnSuaLPT.setBounds(179, 123, 163, 43);
		getFrame().getContentPane().add(btnSuaLPT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 659, 214);
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
		
		JLabel lblDanhSch = new JLabel("Danh sách phiếu nhập :");
		lblDanhSch.setBounds(10, 190, 144, 23);
		frame.getContentPane().add(lblDanhSch);
		
		
		btnXoaLPT.setBackground(Color.CYAN);
		btnXoaLPT.setBounds(10, 69, 163, 42);
		frame.getContentPane().add(btnXoaLPT);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(506, 11, 163, 43);
		frame.getContentPane().add(btnDangXuat);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm phiếu nhập", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel.setBounds(444, 137, 225, 76);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
				txtTim = new JTextField();
				txtTim.setBounds(87, 49, 128, 20);
				panel.add(txtTim);
				//TODO Chuc nang Tim Kiem
				txtTim.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						if(txtTim.getText().equalsIgnoreCase("")){
							createTable();
						}else {	
							Connection con = DataBase.getConnection();
							PreparedStatement stmt = null;
							
							try {
								int cboIndex = cboLPT.getSelectedIndex();
								String selection[] = new String[]{"MaPT","SoLuongNhap","NgayNhapHang"};
								
								stmt = con.prepareStatement("SELECT MaPT as '"+ headers[0] + "'"
																	+ ", SoLuongNhap as '"+ headers[1] + "'"
																	+ ", NgayNhapHang as '"+ headers[2] + "'"
																	+ "FROM PhieuNhap "
																	+ "where "+selection[cboIndex]+" = ?");
								stmt.setString(1, txtTim.getText());
								ResultSet rs = stmt.executeQuery();
								
								table.setModel(DbUtils.resultSetToTableModel(rs));
								
								stmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
					}
				});
				txtTim.setColumns(10);
				cboLPT.setBounds(87, 16, 128, 22);
				panel.add(cboLPT);
				

				cboLPT.setModel(new DefaultComboBoxModel<String>(headers));
				
				JLabel lblTmTheo = new JLabel("Tìm theo:");
				lblTmTheo.setBounds(6, 16, 66, 23);
				panel.add(lblTmTheo);
				
				btnThemLPT.setBackground(Color.CYAN);
				btnThemLPT.setBounds(179, 69, 163, 43);
				frame.getContentPane().add(btnThemLPT);
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(506, 69, 163, 43);
				
				frame.getContentPane().add(btnQuayLai);
				btnXuatExcel.setBackground(Color.CYAN);
				btnXuatExcel.setBounds(10, 122, 163, 43);
				
				frame.getContentPane().add(btnXuatExcel);
								
				//TODO - Register before perform action
				btnXoaLPT.addActionListener(this);
				btnThemLPT.addActionListener(this);
				btnSuaLPT.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXuatExcel.addActionListener(this);
				
				// methods from support region
				createTable();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// tao bang tu DS 
	private void createTable() {
		// Tao dataModel & table 
		dataModel = new DefaultTableModel(headers, 0);
		table.setModel(dataModel);
		
		napDuLieuChoBang();
	}
	// nap DS
	private void napDuLieuChoBang() {
		List<PhieuNhap> list = pnBAL.getAll_PhieuNhap();
		for (PhieuNhap pn : list) {
			Object[] row = { pn.getMaPT(), pn.getSoLuongNhap(), pn.getNgayNhapHang()};
			dataModel.addRow(row);
		}
	}
		
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Quản lí phiếu nhập");	 // TODO - note: tao title o day de ko thay doi bien frame
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnXoaLPT)){ //Xoa phieu nhap
			int row = table.getSelectedRow();
			if(row >= 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phiếu nhập này?", 
																		"Title on Box", 
																		dialogButton);
				if(dialogResult == 0) {
					String mapn = (String) table.getValueAt(row, 0);

					if(pnBAL.delete(mapn)){
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "xóa thành công!");
					}else
						JOptionPane.showMessageDialog(null, "Phiếu nhập này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập để xóa!");
		}else if(o.equals(btnThemLPT)){ //them thong tin phieu nhap
			frame.dispose();
			NhanVien_AddPhieuNhap_Form ql_AddLPT = new NhanVien_AddPhieuNhap_Form(get_Login_Text);
			ql_AddLPT.frm.setVisible(true);
		}else if(o.equals(btnSuaLPT)){ //Sua thong tin phieu nhap
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maPT =	(String) table.getValueAt(row, 0);
				NhanVien_UpdatePhieuNhap_Form ql_UpdatLPT = new NhanVien_UpdatePhieuNhap_Form(get_Login_Text,maPT);
				ql_UpdatLPT.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phiếu nhập để sửa!");
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("PhieuNhap");
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frame.dispose();
			NhanVien_Form_Main frmMain = new NhanVien_Form_Main(get_Login_Text);
			frmMain.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
