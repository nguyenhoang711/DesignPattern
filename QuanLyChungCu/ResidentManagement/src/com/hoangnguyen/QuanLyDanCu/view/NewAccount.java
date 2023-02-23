//
package com.hoangnguyen.QuanLyDanCu.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;
import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.utils.ScannerUtils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 29, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 29, 2022
 */
public class NewAccount extends JPanel implements FocusListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField username_text,password_text;
	private JLabel bigLabel,usernameLabel,passwordLabel,roleLabel;
	private JRadioButton employeeRadioButton,adminRadioButton;
	private final ButtonGroup group = new ButtonGroup();
	private JButton btnSave;
	private String selected = ""; //luu gia tri radiobutton duoc chon

	/**
	 * Create the panel.
	 */
	public NewAccount() {
		setLayout(null);
		
		bigLabel = new JLabel("NHẬP THÔNG TIN TÀI KHOẢN");
		bigLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		bigLabel.setBounds(340, 42, 340, 38);
		add(bigLabel);
		
		usernameLabel = new JLabel("Tên đăng nhập:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameLabel.setBounds(167, 124, 180, 38);
		add(usernameLabel);
		
		username_text = new JTextField();
		username_text.setBounds(435, 124, 262, 38);
		add(username_text);
		username_text.addFocusListener(this);
		username_text.setColumns(10);
		username_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					password_text.requestFocus();
				}
			}
		});
		
		passwordLabel = new JLabel("Mật khẩu:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLabel.setBounds(167, 194, 180, 38);
		add(passwordLabel);
		
		password_text = new JTextField();
		password_text.setBounds(435, 194, 262, 38);
		add(password_text);
		password_text.setText("123abc");
		password_text.setColumns(10);

		roleLabel = new JLabel("Vai trò:");
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roleLabel.setBounds(167, 264, 180, 30);
		add(roleLabel);
		
		employeeRadioButton = new JRadioButton("Nhân viên");
		employeeRadioButton.setSelected(true);
		employeeRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		employeeRadioButton.setBounds(435, 273, 103, 21);
		add(employeeRadioButton);
		group.add(employeeRadioButton);
		
		adminRadioButton = new JRadioButton("Quản lý");
		adminRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adminRadioButton.setBounds(594, 273, 103, 21);
		add(adminRadioButton);
		group.add(adminRadioButton);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(409, 344, 112, 38);
		btnSave.addActionListener(this);
		add(btnSave);

	}

	/* 
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	public void actionPerformed(ActionEvent e) {
		
		//set gia tri cho selected
		Enumeration elements = group.getElements();
        while (elements.hasMoreElements()) {
          AbstractButton button = (AbstractButton) elements.nextElement();
          if (button.isSelected()) {
        	selected = button.getText();
            break;
          }
        }
        group.setSelected(null, true);
        
        selected = selected.equals("Nhân viên")?"NHANVIEN": "QUANLY";
        
		if( password_text.getText().replaceAll("\\s+", "").equals("") || 
			!ScannerUtils.phoneNumberControl(username_text.getText()) ) {
			
			JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin\nkhông được để rỗng", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		} else {
			String text = "\n Tên đăng nhập :\t" + username_text.getText() + "\n\n" + "  Mật khẩu : \t" + password_text.getText() + 
					"\n\n" + "  Vai trò : \t" + selected + "\n";
			Object[] pane = {
					new JLabel("Will be saved as"),
					new JTextArea(text) {
						public boolean isEditable() {
							return false;
						};
					}
				
			};
			
			int result = JOptionPane.showOptionDialog(this, pane, "DOCUMENT", 1, 1, 
					new ImageIcon("icons\\accounting_icon_1_32.png"), new Object[] {"SAVE", "CANCEL"}, "CANCEL");
			
			// System.out.println(result);
			// 0 -> SAVE
			// 1 -> CANCEL
					
			if(result == 0) {
				
				if(AccountDAO.insertAccountControl(username_text.getText(), password_text.getText(), selected)) {
					
					JOptionPane.showMessageDialog(this, "SAVED");
					clearPanel();
					
				} else {
					JOptionPane.showMessageDialog(this, "NOT SAVED", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
				
				}
			}
			
		}
	}

	/* 
	* @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	*/
	public void focusGained(FocusEvent e) {
		((JTextField)e.getSource()).setBorder(new LineBorder(Color.blue));
	}

	/* 
	* @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	*/
	public void focusLost(FocusEvent e) {
		Color color = Color.green;
		if( ((JTextField) e.getSource()).getText().replaceAll("\\s+", "").equals("")) {
			
			color = Color.red;	
			
		} else {
			if(e.getSource() == username_text) {
				if(ScannerUtils.phoneNumberControl(username_text.getText())) {
					color = Color.white;
				} else {
					color = Color.red;
				}
			}
		}
			
		((JTextField) e.getSource()).setBorder(new LineBorder(color));
		
	}
	
	//ham clear
		private void clearPanel() {
			
			username_text.setText("");
			password_text.setText("123abc");
			employeeRadioButton.setSelected(true);
			
			username_text.setBorder(new LineBorder(Color.white));
			password_text.setBorder(new LineBorder(Color.white));
			
		}
}
