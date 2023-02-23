//
package com.hoangnguyen.QuanLyDanCu.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 13, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 13, 2022
 */
public class Login {
	/**
	 * login window width
	 */
	public static final int W_FRAME = 540;
	
	/**
	 * login window height
	 */
	public static final int H_FRAME = 360;
	
	private ResidentRepository repos;
	private JFrame frmLogin;
	private JTextField textField_username;
	private JPasswordField passwordField_password;
	public static String accountname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		this.repos = new ResidentRepository();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setSize(W_FRAME, H_FRAME);
		frmLogin.setBounds(100, 100, 690, 395);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ KHU DÂN CƯ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(196, 49, 296, 52);
		frmLogin.getContentPane().add(lblNewLabel);
		
		//label cho field nhap ten tai khoan
		JLabel lblNewLabel_1 = new JLabel("Tài khoản:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(110, 147, 104, 25);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		
		//label cho field nhap mat khau
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(110, 197, 104, 25);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		
		//o nhap ten tai khoan
		textField_username = new JTextField();
		textField_username.setBounds(255, 141, 205, 30);
		frmLogin.getContentPane().add(textField_username);
		textField_username.setColumns(10);
		
		
		//o nhap mat khau
		passwordField_password = new JPasswordField();
		passwordField_password.setBounds(255, 197, 205, 25);
		frmLogin.getContentPane().add(passwordField_password);
		
		//nut dang nhap
		final JButton btn_login = new JButton("Đăng nhập");
		btn_login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_login.setBounds(332, 276, 110, 30);
		frmLogin.getContentPane().add(btn_login);
		
		//set action cho truong nhap mat khau
		passwordField_password.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btn_login.doClick();
			}
		});
		
		//thong bao loi
		final JLabel errorLabel_pass = new JLabel("");
		errorLabel_pass.setForeground(Color.RED);
		errorLabel_pass.setBounds(234, 244, 226, 22);
		frmLogin.getContentPane().add(errorLabel_pass);
		final String errorText = "Sai tên hoặc mật khẩu";
		
		
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField_username.getText();
				String password = new String(passwordField_password.getPassword());
				if(textField_username.getText().equals("") || String.valueOf(passwordField_password.getPassword()).equals("")) {
					errorLabel_pass.setText(errorText);
				}
				else{
					if(repos.Login(username, password) == 1) {
						accountname = username;
						JOptionPane.showMessageDialog(frmLogin, "Đăng nhập thành công!");
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								Login.this.frmLogin.dispose();
								new AdminPanel();
							}
						});
					}
					else JOptionPane.showMessageDialog(frmLogin, "Đăng nhập thất bại!");
					System.out.println(repos.Login(errorText, password));
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(484, 276, 85, 30);
		frmLogin.getContentPane().add(btnNewButton_1);
	}
}
