//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CanHoDAO;
import com.hoangnguyen.QuanLyDanCu.utils.ScannerUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;

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
public class NewApartment extends JPanel implements FocusListener, ActionListener{
	private JTextField phong_text,tang_text,gia_text;
	private JLabel lbltitle,lblSophong,lbltoa,lblgia,lbltang,lblstate,lbldetail;
	private JButton btnSave;
	private JTextArea detail_text;
	private String toa,trangthai;
	private JComboBox trangthaiBox,toaBox;
	private JLabel lbldientich;
	private JTextField dientich_text;

	/**
	 * Create the panel.
	 */
	public NewApartment() {
		setLayout(null);
		
		lbltitle = new JLabel("Quản lý căn hộ");
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltitle.setBounds(412, 51, 282, 47);
		add(lbltitle);
		
		lblSophong = new JLabel("Số phòng:");
		lblSophong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSophong.setBounds(99, 153, 125, 35);
		add(lblSophong);
		
		phong_text = new JTextField();
		phong_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phong_text.setBounds(271, 154, 141, 37);
		phong_text.addFocusListener(this);
		phong_text.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(btnSave != null) {
					tang_text.requestFocus();
				}
				
			}
		});
		add(phong_text);
		phong_text.setColumns(10);
		
		toaBox = new JComboBox();
		toaBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toaBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		toaBox.setSelectedIndex(0);
		toaBox.setBounds(271, 226, 87, 28);
		add(toaBox);
		
		lbltoa = new JLabel("Tòa:");
		lbltoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltoa.setBounds(99, 219, 125, 35);
		add(lbltoa);
		
		lbltang = new JLabel("Tầng:");
		lbltang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltang.setBounds(99, 288, 125, 35);
		add(lbltang);
		
		tang_text = new JTextField();
		tang_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tang_text.setBounds(271, 288, 141, 35);
		tang_text.addFocusListener(this);
		tang_text.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(btnSave != null) {
					gia_text.requestFocus();
				}
			}
		});
		add(tang_text);
		tang_text.setColumns(10);
		
		lblgia = new JLabel("Giá:");
		lblgia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgia.setBounds(99, 360, 125, 35);
		add(lblgia);
		
		gia_text = new JTextField();
		gia_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gia_text.setColumns(10);
		gia_text.setBounds(271, 360, 141, 35);
		add(gia_text);
		gia_text.addFocusListener(this);
		gia_text.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(btnSave != null) {
					dientich_text.requestFocus();
				}
			}
		});
		
		// private JComboBox trangthaiBox
		trangthaiBox = new JComboBox();
		trangthaiBox.setModel(new DefaultComboBoxModel(new String[] {"AVAILABLE", "SELLED", "RENTED", "ON_HOLD"}));
		trangthaiBox.setSelectedIndex(0);
		trangthaiBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		trangthaiBox.setBounds(748, 153, 112, 35);
		add(trangthaiBox);
		
		lblstate = new JLabel("Trạng thái:");
		lblstate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblstate.setBounds(559, 153, 125, 35);
		add(lblstate);
		
		lbldientich = new JLabel("Diện tích (m2):");
		lbldientich.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldientich.setBounds(559, 219, 125, 35);
		add(lbldientich);
		
		dientich_text = new JTextField();
		dientich_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dientich_text.setColumns(10);
		dientich_text.setBounds(726, 217, 141, 37);
		dientich_text.addFocusListener(this);
		dientich_text.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(btnSave != null) {
					detail_text.requestFocus();
				}
			}
		});
		add(dientich_text);
		
		lbldetail = new JLabel("Mô tả:");
		lbldetail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldetail.setBounds(528, 288, 96, 35);
		add(lbldetail);
		
		
		detail_text = new JTextArea();
		detail_text.setBounds(634, 295, 305, 222);
		add(detail_text);
		
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(417, 528, 85, 28);
		btnSave.addActionListener(this);
		
		add(btnSave);
		

	}

	/* 
	* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	*/
	public void actionPerformed(ActionEvent e) {
		toa = toaBox.getSelectedItem().toString();
		trangthai = trangthaiBox.getSelectedItem().toString();
		if( detail_text.getText().replaceAll("\\s+", "").equals("") ||
				!ScannerUtils.inputGiaControl(Float.parseFloat(gia_text.getText()))||
				!ScannerUtils.inputTangControl(Integer.parseInt(tang_text.getText())) ) {
				
				JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin\nkhông được để rỗng", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} else {
				String text = "\n Số phòng :\t" + phong_text.getText() + "\n\n" + " Tòa  : \t" + toa + 
						"\n\n" + "  Tầng : \t" + tang_text.getText() + "\n\n" + " Dien tich: \t" +  "\n";
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
					
					if(CanHoDAO.addNewCanHo(toa,Integer.parseInt(phong_text.getText()), Integer.parseInt(phong_text.getText()), Integer.parseInt(dientich_text.getText()),Float.parseFloat(gia_text.getText()),trangthai,detail_text.getText())) {
						
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
			if(e.getSource() == tang_text) {
				if(ScannerUtils.inputTangControl(Integer.parseInt(tang_text.getText()))) {
					color = Color.white;
				} else {
					color = Color.red;
				}
			}
			
			if(e.getSource() == gia_text) {
				if(ScannerUtils.inputGiaControl(Float.parseFloat(gia_text.getText()))) {
					color = Color.white;
				}else {
					color = Color.red;
				}
			}
			
			if(e.getSource() == phong_text) {
				if(ScannerUtils.inputPhongControl(Integer.parseInt(phong_text.getText()))) {
					color = Color.white;
				}else {
					color = Color.red;
				}
			}
			
			if(e.getSource() == dientich_text) {
				if(ScannerUtils.inputTangControl(Integer.parseInt(dientich_text.getText()))) {
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
		
		phong_text.setText("");
		toaBox.setSelectedIndex(0);
		tang_text.setText("");
		gia_text.setText("");
		trangthaiBox.setSelectedIndex(0);
		dientich_text.setText("");
		detail_text.setText("");
		
		phong_text.setBorder(new LineBorder(Color.white));
		tang_text.setBorder(new LineBorder(Color.white));
		gia_text.setBorder(new LineBorder(Color.white));
		dientich_text.setBorder(new LineBorder(Color.white));
	}
}
