//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;
import com.hoangnguyen.QuanLyDanCu.utils.ScannerUtils;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 28, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 28, 2022
 */
public class NewResident extends JPanel implements FocusListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField inname_textfield,email_textfield,quequan_textfield,sodt_textField;
	private JLabel newCuDan_title,fullname_label,email_label,address_label,phonenumber_label,lbltype;
	private JButton btnSave;
	private JComboBox residenttype_box;

	/**
	 * Create the panel.
	 */
	public NewResident() {
		setLayout(null);
		
		newCuDan_title = new JLabel("THÔNG TIN CƯ DÂN");
		newCuDan_title.setHorizontalAlignment(SwingConstants.CENTER);
		newCuDan_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		newCuDan_title.setBounds(392, 41, 331, 43);
		add(newCuDan_title);
		
		fullname_label = new JLabel("Họ tên: ");
		fullname_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fullname_label.setBounds(276, 142, 141, 43);
		add(fullname_label);
		
		inname_textfield = new JTextField();
		inname_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inname_textfield.setBounds(533, 142, 231, 43);
		add(inname_textfield);
		inname_textfield.setColumns(10);
		inname_textfield.addFocusListener(this);
		inname_textfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					email_textfield.requestFocus();
				}
			}
		});
		
		email_label = new JLabel("Email: ");
		email_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email_label.setBounds(276, 212, 141, 43);
		add(email_label);
		
		
		email_textfield = new JTextField();
		email_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email_textfield.setColumns(10);
		email_textfield.setBounds(533, 212, 231, 43);
		email_textfield.addFocusListener(this);
		email_textfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					quequan_textfield.requestFocus();
				}
			}
		});
		add(email_textfield);
		
		
		address_label = new JLabel("Quê quán: ");
		address_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address_label.setBounds(276, 286, 141, 43);
		add(address_label);
		
		quequan_textfield = new JTextField();
		quequan_textfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quequan_textfield.setColumns(10);
		quequan_textfield.setBounds(533, 286, 231, 43);
		quequan_textfield.addFocusListener(this);
		quequan_textfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					sodt_textField.requestFocus();
				}
			}
		});
		add(quequan_textfield);
		
		phonenumber_label = new JLabel("Số điện thoại:");
		phonenumber_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phonenumber_label.setBounds(276, 359, 141, 43);
		add(phonenumber_label);
		
		sodt_textField = new JTextField();
		sodt_textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sodt_textField.setColumns(10);
		sodt_textField.setBounds(533, 359, 231, 43);
		sodt_textField.addFocusListener(this);
		sodt_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					btnSave.doClick();
				}	
			}
		});
		add(sodt_textField);
		
		
		btnSave = new JButton("Save");
		btnSave.setFocusPainted(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(454, 535, 141, 43);
		btnSave.addActionListener(this);
		add(btnSave);
		
		residenttype_box = new JComboBox();
		residenttype_box.setFont(new Font("Tahoma", Font.PLAIN, 14));
		residenttype_box.setModel(new DefaultComboBoxModel(new String[] {"RENTER", "OWNER", " "}));
		residenttype_box.setSelectedIndex(0);
		residenttype_box.setBounds(533, 448, 94, 29);
		add(residenttype_box);
		
		lbltype = new JLabel("Phân loại:");
		lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltype.setBounds(276, 441, 141, 43);
		add(lbltype);
		
	}

	/* 
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	public void actionPerformed(ActionEvent e) {
		String value = residenttype_box.getSelectedItem().toString();
		if(		inname_textfield.getText().replaceAll("\\s+", "").equals("") || 
				quequan_textfield.getText().replaceAll("\\s+", "").equals("") || 
				!ScannerUtils.inputEmailControl(email_textfield.getText()) ||
				!ScannerUtils.phoneNumberControl(sodt_textField.getText()) ) {
			
			JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin\nkhông được để rỗng", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			String text = "\n Họ tên :\t" + inname_textfield.getText().toUpperCase() + "\n\n" + "  Email : \t" + email_textfield.getText().toUpperCase() + 
					"\n\n" + "  Số điện thoại : \t" + sodt_textField.getText().toUpperCase() + "\n\n" + "  Quê quán :   " + quequan_textfield.getText().toUpperCase() +
					"\n\n" + "Loại: \t" + value + "\n";
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
				
				if(CuDanDAO.insertNewCuDan(inname_textfield.getText().toUpperCase(), email_textfield.getText().toUpperCase(), 
						sodt_textField.getText().toUpperCase(), quequan_textfield.getText().toUpperCase(),value.toUpperCase())) {
					
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
			if(e.getSource() == sodt_textField) {
				if(ScannerUtils.phoneNumberControl(sodt_textField.getText())) {
					color = Color.white;
				} else {
					color = Color.red;
				}
			}
			
			if(e.getSource() == email_textfield) {
				if(ScannerUtils.inputEmailControl(email_textfield.getText())) {
					color = Color.white;
				}else {
					color = Color.red;
				}
			}
		}
			
		((JTextField) e.getSource()).setBorder(new LineBorder(color));
		
	}
	
	
	//ham clear
	private void clearPanel() {
		
		inname_textfield.setText("");
		email_textfield.setText("");
		sodt_textField.setText("");
		quequan_textfield.setText("");
		
		inname_textfield.setBorder(new LineBorder(Color.white));
		email_textfield.setBorder(new LineBorder(Color.white));
		sodt_textField.setBorder(new LineBorder(Color.white));
		quequan_textfield.setBorder(new LineBorder(Color.white));
		
	}
}
