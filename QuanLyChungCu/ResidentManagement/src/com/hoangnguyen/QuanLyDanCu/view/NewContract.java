//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JTextField;

import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;
import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CanHoDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;
import com.hoangnguyen.QuanLyDanCu.dao.HopDongDAO;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 30, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 30, 2022
 */
public class NewContract extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField kihan_text;
	private JTextField textField;
	private JLabel lblhopdong,lblkihan,lblngayky,lbltype,lblmota,lblcudan,lblcanho;
	private JRadioButton rdbtRent,rdbtbuy;
	private JTextArea mota_text;
	private JComboBox cudanBox,canhoBox;
	private JButton btnSave;
	private final ButtonGroup group = new ButtonGroup();
	private Object[] names = CuDanDAO.getAllName();
	private Object[] macanho = CanHoDAO.getAllMaCanHo();
	private String selected = ""; //luu gia tri radiobutton duoc chon
	/**
	 * Create the panel.
	 */
	public NewContract() {
		setLayout(null);
		
		lblhopdong = new JLabel("Thông tin hợp đồng");
		lblhopdong.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblhopdong.setBounds(391, 37, 322, 53);
		add(lblhopdong);
		
		kihan_text = new JTextField();
		kihan_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kihan_text.setBounds(296, 176, 169, 35);
		add(kihan_text);
		kihan_text.setColumns(10);
		
		lblkihan = new JLabel("Kì hạn(tháng):");
		lblkihan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblkihan.setBounds(79, 176, 138, 35);
		add(lblkihan);
		
		lblngayky = new JLabel("Ngày tạo(YYYY-DD-MM):");
		lblngayky.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblngayky.setBounds(79, 246, 188, 35);
		add(lblngayky);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(296, 246, 169, 35);
		add(textField);
		
		lbltype = new JLabel("Loại:");
		lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltype.setBounds(79, 106, 87, 35);
		add(lbltype);
		
		rdbtRent = new JRadioButton("RENT");
		rdbtRent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtRent.setBounds(210, 113, 103, 21);
		group.add(rdbtRent);
		add(rdbtRent);
		
		rdbtbuy = new JRadioButton("BUY");
		rdbtbuy.setSelected(true);
		rdbtbuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtbuy.setBounds(349, 113, 103, 21);
		group.add(rdbtbuy);
		add(rdbtbuy);
		
		mota_text = new JTextArea();
		mota_text.setBounds(210, 328, 310, 167);
		add(mota_text);
		
		lblmota = new JLabel("Mô tả:");
		lblmota.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmota.setBounds(79, 321, 87, 35);
		add(lblmota);
		
		cudanBox = new JComboBox();
		cudanBox.setMaximumRowCount(8);
		for(int i = 0;i<names.length;i++) {
			cudanBox.addItem(names[i]);
		}
		cudanBox.setBounds(706, 169, 177, 42);
		add(cudanBox);
		
		lblcudan = new JLabel("Tên cư dân");
		lblcudan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcudan.setBounds(550, 176, 87, 35);
		add(lblcudan);
		
		canhoBox = new JComboBox();
		canhoBox.setMaximumRowCount(8);
		for(int i = 0;i<macanho.length;i++) {
			canhoBox.addItem(macanho[i]);
		}
		canhoBox.setBounds(706, 244, 177, 42);
		add(canhoBox);
		
		lblcanho = new JLabel("Mã căn hộ:");
		lblcanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcanho.setBounds(550, 246, 87, 35);
		add(lblcanho);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
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
		        if( kihan_text.getText().replaceAll("\\s+", "").equals("") ||
		        		textField.getText().replaceAll("\\s+", "").equals("")
		    			) {
		    			
		    			JOptionPane.showMessageDialog(kihan_text, "Vui lòng điền đầy đủ thông tin\nkhông được để rỗng", "ERROR", JOptionPane.ERROR_MESSAGE);
		    			
		    		} else {
		    			String text = "\n Ki han :\t" + kihan_text.getText() + "\n\n" + "  Ngay ky : \t" + textField.getText() + 
		    					"\n\n" + "  Loại: \t" + selected + "\n";
		    			Object[] pane = {
		    					new JLabel("Will be saved as"),
		    					new JTextArea(text) {
		    						public boolean isEditable() {
		    							return false;
		    						};
		    					}
		    				
		    			};
		    			
		    			int result = JOptionPane.showOptionDialog(kihan_text, pane, "DOCUMENT", 1, 1, 
		    					new ImageIcon("icons\\accounting_icon_1_32.png"), new Object[] {"SAVE", "CANCEL"}, "CANCEL");
		    			
		    			// System.out.println(result);
		    			// 0 -> SAVE
		    			// 1 -> CANCEL
		    					
		    			if(result == 0) {
		    				HopDongDAO.addHopDong(selected, Float.parseFloat(kihan_text.getText()), textField.getText(), mota_text.getText(), ResidentRepository.nameToMaCD(cudanBox.getSelectedItem().toString()), AccountDAO.nameToMaCD(Login.accountname), canhoBox.getSelectedItem().toString());
		    				JOptionPane.showMessageDialog(kihan_text, "SAVED");
	    					clearPanel();
		    			}
		    			
		    		}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(510, 523, 103, 42);
		add(btnSave);

	}
	//ham clear
			private void clearPanel() {
				
				kihan_text.setText("");
				mota_text.setText("");
				
				
			}
}
