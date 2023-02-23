//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository;
import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;
import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

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
public class CuDanManager extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField name_text,sodt_text,email_text,address_text,textSearch;
	private JLabel bigLabel,nameLabel,sodtLabel,lblEmail,lbladdress,lblcanho;
	private JButton btnUpdate,deletebtn,btnSave,btnTimKiem;
	private JComboBox typeBox;
	private String[] vecHeader = {"Mã cư dân","Họ tên","Email","Số điện thoại","Quê quán","Loại","Mã căn hộ"};
	private String[][] cudans;
	private static IResidentRepository repository = new ResidentRepository();
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblmacd;
	private JTextField maCD_text;
	private JTextField maCH_text;
	/**
	 * Create the panel.
	 */
	public CuDanManager() {
		setLayout(null);
		
		bigLabel = new JLabel("Danh sách cư dân");
		bigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bigLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bigLabel.setBounds(304, 25, 349, 30);
		add(bigLabel);
		
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				name_text.setEditable(true);
				sodt_text.setEditable(true);
				email_text.setEditable(true);
				address_text.setEditable(true);
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(95, 348, 118, 44);
		add(btnUpdate);
		
		deletebtn = new JButton("Xóa");
		deletebtn.setEnabled(false);
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				CuDanDAO.deleteCuDan(id);
				JOptionPane.showMessageDialog(table, "Xóa thành công");
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deletebtn.setBounds(304, 348, 118, 44);
		add(deletebtn);
		
		nameLabel = new JLabel("Họ tên:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setBounds(43, 472, 101, 35);
		add(nameLabel);
		
		name_text = new JTextField();
		name_text.setEditable(false);
		name_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name_text.setBounds(177, 472, 220, 35);
		add(name_text);
		name_text.setColumns(10);
		
		sodtLabel = new JLabel("Số điện thoại:");
		sodtLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sodtLabel.setBounds(43, 536, 101, 35);
		add(sodtLabel);
		
		sodt_text = new JTextField();
		sodt_text.setEditable(false);
		sodt_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sodt_text.setColumns(10);
		sodt_text.setBounds(177, 536, 220, 35);
		add(sodt_text);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(502, 472, 101, 35);
		add(lblEmail);
		
		email_text = new JTextField();
		email_text.setEditable(false);
		email_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email_text.setColumns(10);
		email_text.setBounds(596, 472, 220, 35);
		add(email_text);
		
		lbladdress = new JLabel("Quê quán:");
		lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbladdress.setBounds(502, 536, 89, 35);
		add(lbladdress);
		
		address_text = new JTextField();
		address_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		address_text.setEditable(false);
		address_text.setColumns(10);
		address_text.setBounds(596, 536, 220, 35);
		add(address_text);
		
		typeBox = new JComboBox();
		typeBox.setEnabled(false);
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"RENTER", "OWNER", " "}));
		typeBox.setSelectedIndex(0);
		typeBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeBox.setBounds(833, 348, 101, 44);
		add(typeBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 69, 695, 269);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(40);
		table.setCellSelectionEnabled(false);
		table.getColumnModel().setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			vecHeader
		));
		
		textSearch = new JTextField();
		textSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem.doClick();
			}
		});
		textSearch.setBounds(755, 69, 205, 35);
		add(textSearch);
		textSearch.setColumns(10);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);
				String text = textSearch.getText();
				if(text.length() == 0) {
					sorter.setRowFilter(null);
				}else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(text));
						
					}catch(PatternSyntaxException pse) {
						System.out.println("Khong tim thay ket qua");
					}
				}
			}
		});
		btnTimKiem.setBounds(787, 161, 134, 44);
		add(btnTimKiem);
		
		lblmacd = new JLabel("Mã cư dân:");
		lblmacd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmacd.setBounds(43, 424, 118, 30);
		add(lblmacd);
		
		maCD_text = new JTextField();
		maCD_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maCD_text.setEditable(false);
		maCD_text.setColumns(10);
		maCD_text.setBounds(177, 422, 220, 35);
		add(maCD_text);
		
		lblcanho = new JLabel("Mã căn hộ:");
		lblcanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcanho.setBounds(502, 424, 89, 30);
		add(lblcanho);
		
		maCH_text = new JTextField();
		maCH_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maCH_text.setEditable(false);
		maCH_text.setColumns(10);
		maCH_text.setBounds(596, 419, 220, 35);
		add(maCH_text);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(btnSave, "Cap nhat thanh cong");
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(506, 349, 118, 43);
		add(btnSave);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				btnUpdate.setEnabled(true);
				deletebtn.setEnabled(true);
				String macd = table.getValueAt(row, 0).toString();
				String hoten = table.getValueAt(row, 1).toString();
				String email = table.getValueAt(row, 2).toString();
				String sodt = table.getValueAt(row, 3).toString();
				String que = table.getValueAt(row, 4).toString();
				String loai = table.getValueAt(row, 5)!=null?table.getValueAt(row, 5).toString():"";
				String maCH = table.getValueAt(row, 6)!=null?table.getValueAt(row, 6).toString():"";
				
				maCD_text.setText(macd);
				name_text.setText(hoten);
				email_text.setText(email);
				sodt_text.setText(sodt);
				address_text.setText(que);
				if(loai.equals("RENTER"))
					typeBox.setSelectedIndex(0);
				else if(loai.equals("OWNER"))
					typeBox.setSelectedIndex(1);
				else typeBox.setSelectedIndex(2);
				maCH_text.setText(maCH);
			}
		});
		
		//quy dịnh dữ liệu
		cudans = repository.printAllResident();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		for(int i = 0;i<cudans.length;i++) {
			model.addRow(cudans[i]);
		}
	}
	
	public void update() {
		int macd = Integer.parseInt(maCD_text.getText());
		String hoten = name_text.getText();
		String email = email_text.getText();
		String sodt = sodt_text.getText();
		String quequan = address_text.getText();
		CuDanDAO.updateCuDan(macd, hoten, email, sodt, quequan);
	}
}
