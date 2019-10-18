package gui.nv_PhieuXuat;

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
import bAL.PhieuXuatBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.carPart.LoaiPhuTungXe;
import entity.carPart.PhieuNhap;
import entity.carPart.PhieuXuat;
import gui.main.NhanVien_Form_Main;
import gui.main.QuanLy_Form_Main;
import helpers.UI_Helpers;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class NhanVien_Form_PhieuXuat extends JFrame implements ActionListener {

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
	JButton btnXoaLPT = new JButton("Xoa phieu xuat");
	/**
	 *@param Sửa Thông Tin loai phu tung
	 */
	JButton btnSuaLPT = new JButton("Sua phieu xuat");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");
	
	private DefaultTableModel dataModel;
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtTim;
	JComboBox<String> cboLPT = new JComboBox<String>();
	JButton btnThemLPT = new JButton("Them phieu xuat");
	private final JButton btnQuayLai = new JButton("Quay lai");
	
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private PhieuXuatBAL pxBAL = new PhieuXuatBAL();	//Khi chương trình chạy, nạp toàn bộ danh sách lên bảng
	// Tao dataModel
	String[] headers = {"Mã phiếu xuất", "Mã phụ tùng", "Số lượng xuất", "Ngày xuất hàng", "Chi phí"};	
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
					NhanVien_Form_PhieuXuat window = new NhanVien_Form_PhieuXuat();
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
	public NhanVien_Form_PhieuXuat() {
		initialize();
	}
	
	public NhanVien_Form_PhieuXuat(String ten) {
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
		
		JLabel lblDanhSch = new JLabel("Danh sách phiếu xuất :");
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
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tìm phiếu xuất", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
								String selection[] = new String[]{"MaPX", "MaPT","SoLuongXuat"
																,"NgayXuatHang","ChiPhi"};
								
								stmt = con.prepareStatement("SELECT MaPX as '"+ headers[0] + "'"
																	+ ", MaPT as '"+ headers[1] + "'"
																	+ ", SoLuongXuat as '"+ headers[2] + "'"
																	+ ", NgayXuatHang as '"+ headers[3] + "'"
																	+ ", ChiPhi as '"+ headers[4] + "'"
																	+ "FROM PhieuXuat "
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
		List<PhieuXuat> list = pxBAL.getAll_PX();
		for (PhieuXuat px : list) {
			Object[] row = { px.getMaPX(), px.getMaPT(), px.getSoLuongXuat()
							, px.getNgayXuatHang(), px.getChiPhi()};
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
		frame.setTitle("Quản lí phiếu xuất");	 // TODO - note: tao title o day de ko thay doi bien frame
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
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phiếu xuất này?", 
																		"Title on Box", 
																		dialogButton);
				if(dialogResult == 0) {
					String mapx = (String) table.getValueAt(row, 0);

					if(pxBAL.delete(mapx)){
						dataModel.removeRow(row);
						JOptionPane.showMessageDialog(null, "xóa thành công!");
					}else
						JOptionPane.showMessageDialog(null, "Phiếu xuất này không xóa được");
				}
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phiếu xuất để xóa!");
		}else if(o.equals(btnThemLPT)){ //them thong tin phieu nhap
			frame.dispose();
			NhanVien_AddPhieuXuat_Form ql_AddLPT = new NhanVien_AddPhieuXuat_Form(get_Login_Text);
			ql_AddLPT.frm.setVisible(true);
		}else if(o.equals(btnSuaLPT)){ //Sua thong tin phieu nhap
			int row = table.getSelectedRow();
			if(row>=0){
				frame.dispose();
				String maPX =	(String) table.getValueAt(row, 0);
				NhanVien_UpdatePhieuXuat_Form ql_UpdatLPT = new NhanVien_UpdatePhieuXuat_Form(get_Login_Text,maPX);
				ql_UpdatLPT.frame.setVisible(true);				
			}else
				JOptionPane.showMessageDialog(null, "Hãy chọn phiếu xuất để sửa!");
		}else if(o.equals(btnXuatExcel)){ // Xuat Excel
			UI_Helpers.exportToExcel("PhieuXuat");
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
