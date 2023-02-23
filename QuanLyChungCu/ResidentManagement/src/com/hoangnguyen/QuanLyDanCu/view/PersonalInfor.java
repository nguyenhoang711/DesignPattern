//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;
import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.dao.NhanVienDAO;
import com.hoangnguyen.QuanLyDanCu.dao.QuanLyDAO;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 24, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 24, 2022
 */
public class PersonalInfor extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField hoten_field;
	private JTextField soTextField;
	private JTextField emailTextField;
	private JTextField userNameText;
	private JTextField passwordField;
	private JLabel contentLabel,hotenLabel,soDT_label,mail_label,tenTKLabel,passwordLabel;
	private JButton updateBtn,btnConfirm;
	private String password = AccountDAO.getAccountInfo(Login.accountname);
	private String[] infor = ResidentRepository.roleConverter(Login.accountname).equals("nhanvien")?NhanVienDAO.getInfor(Login.accountname):QuanLyDAO.getInfor(Login.accountname);
	/**
	 * Create the panel.
	 */
	public PersonalInfor() {
		setLayout(null);
		
		contentLabel = new JLabel("Quản lý thông tin cá nhân");
		contentLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentLabel.setBounds(277, 35, 234, 58);
		add(contentLabel);
		
		hotenLabel = new JLabel("Họ tên: ");
		hotenLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hotenLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hotenLabel.setBounds(51, 159, 108, 35);
		add(hotenLabel);
		
		soDT_label = new JLabel("Số điện thoại: ");
		soDT_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		soDT_label.setBounds(51, 238, 108, 24);
		add(soDT_label);
		
		mail_label = new JLabel("Email: ");
		mail_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mail_label.setBounds(51, 322, 108, 24);
		add(mail_label);
		
		hoten_field = new JTextField();
		hoten_field.setEditable(false);
		hoten_field.setText(infor[0]);
		hoten_field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hoten_field.setBounds(210, 159, 197, 35);
		add(hoten_field);
		hoten_field.setColumns(10);
		
		soTextField = new JTextField();
		soTextField.setEditable(false);
		soTextField.setText(infor[1]);
		soTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		soTextField.setColumns(10);
		soTextField.setBounds(210, 233, 197, 35);
		add(soTextField);
		
		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setText(infor[2]);
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailTextField.setColumns(10);
		emailTextField.setBounds(210, 317, 197, 35);
		add(emailTextField);
		
		tenTKLabel = new JLabel("Tên đăng nhập");
		tenTKLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tenTKLabel.setBounds(536, 133, 149, 21);
		add(tenTKLabel);
		
		userNameText = new JTextField();
		userNameText.setEditable(false);
		userNameText.setText(Login.accountname);
		userNameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userNameText.setBounds(536, 177, 224, 35);
		add(userNameText);
		userNameText.setColumns(10);
		
		passwordLabel = new JLabel("Mật khẩu");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(536, 259, 149, 24);
		add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setEditable(false);
		passwordField.setText(password);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(536, 311, 234, 35);
		add(passwordField);
		
		updateBtn = new JButton("Chỉnh sửa");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hoten_field.setEditable(true);
				soTextField.setEditable(true);
				emailTextField.setEditable(true);
				passwordField.setEditable(true);
				btnConfirm.setEnabled(true);
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateBtn.setBounds(453, 454, 102, 30);
		add(updateBtn);
		
		btnConfirm = new JButton("Cập nhật");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountDAO.updateAccountInfo(Login.accountname, soTextField.getText(), passwordField.getText());
				if(ResidentRepository.roleConverter(Login.accountname).equals("nhanvien")) {
					NhanVienDAO.updateNhanVien(Login.accountname, hoten_field.getText(), soTextField.getText(), emailTextField.getText());
				}else {
					QuanLyDAO.updateQuanLy(Login.accountname, hoten_field.getText(), soTextField.getText(), emailTextField.getText());
				}
				JOptionPane.showMessageDialog(passwordLabel, "Cap nhat thanh cong");
			}
		});
		btnConfirm.setEnabled(false);
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirm.setBounds(654, 454, 106, 30);
		add(btnConfirm);
		

	}
}
