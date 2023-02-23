package com.hoangnguyen.QuanLyDanCu.view;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 2, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 2, 2022
 */
public class AdminPanel extends JFrame implements ActionListener{

private static final long serialVersionUID = 1L;

	public static final String NAME = "Management System";
	
	/**
	 * AdminPanel window width
	 */
	public static int W_FRAME = 960;
	
	/**
	 * AdminPanel window height
	 */
	public static int H_FRAME = 2 * W_FRAME / 3;
	
	/**
	 * frame edge
	 */
	public static Insets INSETS;
	
	private JMenuBar menuBar_menubar;
	private JMenu homePage_menu, cudan_menu, canho_menu, hopdong_menu, system_menu, account_menu;
	private JMenuItem showResident_item, addResident_item;
	private JMenuItem showCanHo_item,addCanHo_item;
	private JMenuItem showHopDong_item,addHopDong_item;
	private JMenuItem addAccount_item, showAccounts_item;
	private JMenuItem personalInfor_item, reset_item, logout_item;
	private NhanVienGUI homePage;
	private ArrayList<JPanel> components = new ArrayList<JPanel>();
	private int currentComponent;
	
	public AdminPanel() {
		this(0);
	}
	
	public AdminPanel(int component) {
		
		super("Management System");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:/HoangJava/QuanLyDanCu/src/main/java/com/hoangnguyen/QuanLyDanCu/icon/phan-mem-quan-ly-luong-9.jpg"));
		setSize(W_FRAME, H_FRAME);
		setResizable(false);
		setLocationRelativeTo(null);
		setLocation(getX() - 40, getY() - 20);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		this.currentComponent = component;
		
		INSETS = getInsets();
		
		GUI();
	}
	
	private void GUI() {
		
		createMenus();
		createComponents();
		init();
		
	}
	
	
	private void createMenus() {
		menuBar_menubar = new JMenuBar();
		
		homePage_menu = new JMenu("Home page");
		homePage_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				currentComponent = 0;
				init();
				
			}
		});
		
		cudan_menu = new JMenu("Cư dân");
		canho_menu = new JMenu("Căn hộ");
		hopdong_menu = new JMenu("Hợp đồng");
		system_menu = new JMenu("System");
		account_menu = new JMenu("Account");
		
		showResident_item = new JMenuItem("Danh sách CD");
		cudan_menu.add(showResident_item);
		showResident_item.addActionListener(this);
		
		addResident_item = new JMenuItem("Thêm CD");
		cudan_menu.add(addResident_item);
		addResident_item.addActionListener(this);
		
		showCanHo_item = new JMenuItem("Danh sách CH");
		canho_menu.add(showCanHo_item);
		showCanHo_item.addActionListener(this);
		
		addCanHo_item = new JMenuItem("Thêm CH");
		canho_menu.add(addCanHo_item);
		addCanHo_item.addActionListener(this);
		
			
			
		showHopDong_item = new JMenuItem("Danh sách HD");
		hopdong_menu.add(showHopDong_item);
		showHopDong_item.addActionListener(this);
		
		addHopDong_item = new JMenuItem("Thêm HD");
		hopdong_menu.add(addHopDong_item);
		addHopDong_item.addActionListener(this);
		
		
		personalInfor_item = new JMenuItem("Cá nhân");
		system_menu.add(personalInfor_item);
		personalInfor_item.addActionListener(this);
		
		reset_item = new JMenuItem("Reset");
		system_menu.add(reset_item);
		reset_item.addActionListener(this);
		
		logout_item = new JMenuItem("Logout");
		system_menu.add(logout_item);
		logout_item.addActionListener(this);
		
		showAccounts_item = new JMenuItem("Danh sách Acc");
		account_menu.add(showAccounts_item);
		showAccounts_item.addActionListener(this);
		
		addAccount_item = new JMenuItem("Thêm Acc");
		account_menu.add(addAccount_item);
		addAccount_item.addActionListener(this);
		
		menuBar_menubar.add(homePage_menu);
		menuBar_menubar.add(cudan_menu);
		menuBar_menubar.add(canho_menu);
		menuBar_menubar.add(hopdong_menu);
		menuBar_menubar.add(account_menu);
		menuBar_menubar.add(system_menu);
		
		setJMenuBar(menuBar_menubar);
	}
	
	
	private void createComponents() {
		//ArrayList<JPanel> components = new ArrayList<JPanel>()
		components.add(new NhanVienGUI());
		components.add(new NewApartment());
		components.add(new NewAccount());
		components.add(new NewResident());
		components.add(new CuDanManager());
		components.add(new NewContract());
		components.add(new PersonalInfor());
		components.add(new CanHoManager());
		components.add(new HopDongManager());
		components.add(new AccountManagement());
	}
	
	
	private void init() {
		
		setContentPane(components.get(currentComponent));
		System.out.println("Current component = " + currentComponent);
		this.setTitle(NAME + " - " + components.get(currentComponent).toString());
		this.revalidate();
		this.repaint();
		
	}

	/* 
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	public void actionPerformed(ActionEvent e) {
		if( ((JMenuItem)e.getSource()).getText().equals(addCanHo_item.getText())) {
			
			if(currentComponent == 1) {
				components.set(currentComponent, new NewApartment());
			} else {
				currentComponent = 1;
			}
			
			init();
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(addAccount_item.getText())) {
			
			if(currentComponent == 2) {
				components.set(currentComponent, new NewAccount());
				init();
			} else {
				if(ResidentRepository.roleConverter(Login.accountname).equals("NHANVIEN")) {
					JOptionPane.showMessageDialog(this, "Ban khong the thuc hien chuc nang nay");
				}
				else{
					currentComponent = 2;
					init();
				}
			}
			
			
		
		} else if( ((JMenuItem)e.getSource()).getText().equals(addResident_item.getText())) {

			if(currentComponent == 3) {
				components.set(currentComponent, new NewResident());
			} else {
				currentComponent = 3;
			}
			
			init();
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(showResident_item.getText())) {
			
			if(currentComponent == 4) {
				components.set(currentComponent, new CuDanManager());
			} else {
				currentComponent = 4;
			}
			
			init();
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(addHopDong_item.getText())) {

			if(currentComponent == 5) {
				components.set(currentComponent, new NewContract());
			} else {
				currentComponent = 5;
			}
			
			init();
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(personalInfor_item.getText())) {

			if(currentComponent == 6) {
				components.set(currentComponent, new PersonalInfor());
			} else {
				currentComponent = 6;
			}
			
			init();
		
		} else if( ((JMenuItem)e.getSource()).getText().equals(showCanHo_item.getText())) {

			if(currentComponent == 7) {
				components.set(currentComponent, new CanHoManager());
			} else {
				currentComponent = 7;
			}
			
			init();
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(showHopDong_item.getText())) {

			if(currentComponent == 8) {
				components.set(currentComponent, new HopDongManager());
			} else {
				currentComponent = 8;
			}
			
			init();
		
		} else if( ((JMenuItem)e.getSource()).getText().equals(showAccounts_item.getText())) {

			if(currentComponent == 9) {
				components.set(currentComponent, new AccountManagement());
				init();
			} if(ResidentRepository.roleConverter(Login.accountname).equals("NHANVIEN")) {
				JOptionPane.showMessageDialog(this, "Ban khong the thuc hien chuc nang nay");
			}
			else{
				currentComponent = 9;
				init();
			}
			
		} else if( ((JMenuItem)e.getSource()).getText().equals(reset_item.getText())) {
			
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					AdminPanel.this.dispose();
					try {
						Thread.sleep(350);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new AdminPanel(currentComponent);

				}
			});
		} else if( ((JMenuItem)e.getSource()).getText().equals(logout_item.getText())) {
			
			this.dispose();
			
		}
	}

}
