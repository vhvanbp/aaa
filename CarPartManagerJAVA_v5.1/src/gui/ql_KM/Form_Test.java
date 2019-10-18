package gui.ql_KM;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import bAL.QuanLiVienBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.QuanLiVien;
import entity.users.ThanhVien;
import gui.main.LoginGUI;
import gui.ql_NhanVien.QuanLy_Form_NV;
import gui.ql_nv_PhuTung.QuanLy_Form_PT;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;;

public class Form_Test extends JFrame implements ActionListener {

	//Region - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	//EndRegion
	
	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Test window = new Form_Test();
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
	public Form_Test() {
		initialize();
	}
	
	public Form_Test(String ten) {
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
		getFrame().setBounds(100, 100, 354, 477);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("TEST TEST");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(24, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		lblNgiQunL.setBounds(66, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);
		

		lblTenlogin.setBounds(178, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);

	}
	//EndRegion
		
	//Region - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setResizable(false);
	}
	//EndRegion

	//Region - event handler
	// TODO Chuc Nang button
	@Override
	public void actionPerformed(ActionEvent arg0) { // NOTE: change form constructor LATER
		Object o = arg0.getSource();
		String ms = get_Login_Text;
		
		
	}
}
