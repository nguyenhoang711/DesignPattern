//
package com.hoangnguyen.QuanLyDanCu.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository;
import com.hoangnguyen.QuanLyDanCu.controller.ResidentRepository;
import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class AccountManagement extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField pass_text;
	private JLabel bigLabel,lblmatkhau,lblusername;
	private JButton btnUpdate,deletebtn,btnSave,btnTimKiem;
	private String[] vecHeader = {"Mã tk","Tên đăng nhập", "Mật khẩu", "Chức vụ"};
	private String[][] taikhoans;
	private static IResidentRepository repository = new ResidentRepository();
	private JComboBox roleBox;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblmatk;
	private JTextField maTK_text;
	private JTextField username_text;
	private JLabel lblROLE;
	private JTextField textSearch;
	/**
	 * Create the panel.
	 */
	public AccountManagement() {
		setLayout(null);
		
		bigLabel = new JLabel("Danh sách tài khoản");
		bigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bigLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bigLabel.setBounds(304, 25, 349, 30);
		add(bigLabel);
		
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				roleBox.setEnabled(true);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(80, 373, 134, 43);
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
		deletebtn.setBounds(317, 371, 118, 46);
		add(deletebtn);
		
		lblmatkhau = new JLabel("Mật khẩu:");
		lblmatkhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmatkhau.setBounds(444, 508, 145, 35);
		add(lblmatkhau);
		
		pass_text = new JTextField();
		pass_text.setEditable(false);
		pass_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pass_text.setColumns(10);
		pass_text.setBounds(613, 508, 220, 35);
		add(pass_text);
		
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
		
		lblmatk = new JLabel("Mã tài khoản:");
		lblmatk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmatk.setBounds(43, 510, 118, 30);
		add(lblmatk);
		
		maTK_text = new JTextField();
		maTK_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maTK_text.setEditable(false);
		maTK_text.setColumns(10);
		maTK_text.setBounds(165, 508, 220, 35);
		add(maTK_text);
		
		lblusername = new JLabel("Tên đăng nhập:");
		lblusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblusername.setBounds(444, 451, 145, 30);
		add(lblusername);
		
		username_text = new JTextField();
		username_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username_text.setEditable(false);
		username_text.setColumns(10);
		username_text.setBounds(600, 446, 220, 35);
		add(username_text);
		
		roleBox = new JComboBox();
		roleBox.setMaximumRowCount(2);
		roleBox.setEnabled(false);
		roleBox.setModel(new DefaultComboBoxModel(new String[] {"NHANVIEN", "QUANLY"}));
		roleBox.setSelectedIndex(0);
		roleBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roleBox.setBounds(227, 449, 118, 35);
		add(roleBox);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(btnSave, "Cap nhat thanh cong");
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(539, 373, 134, 43);
		add(btnSave);
		
		lblROLE = new JLabel("Chức vụ:");
		lblROLE.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblROLE.setBounds(43, 451, 118, 30);
		add(lblROLE);
		
		textSearch = new JTextField();
		textSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem.doClick();
			}
		});
		textSearch.setBounds(758, 72, 205, 35);
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
		btnTimKiem.setBounds(798, 134, 134, 43);
		add(btnTimKiem);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				btnUpdate.setEnabled(true);
				deletebtn.setEnabled(true);
				String matk = table.getValueAt(row, 0).toString();
				String username = table.getValueAt(row, 1).toString();
				String pass = table.getValueAt(row, 2).toString();
				String loai = table.getValueAt(row, 3).toString();
				
				maTK_text.setText(matk);
				pass_text.setText(pass);
				username_text.setText(username);
				if(loai.equals("NHANVIEN"))
					roleBox.setSelectedIndex(0);
				else roleBox.setSelectedIndex(1);
			}
		});
		
		//quy dịnh dữ liệu
		taikhoans = repository.printAllAccount();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		for(int i = 0;i<taikhoans.length;i++) {
			model.addRow(taikhoans[i]);
		}
	}
	
	public void update() {
		int matk = Integer.parseInt(maTK_text.getText());
		String role = roleBox.getSelectedItem().toString();
		AccountDAO.updateAccount(matk,role);
	}
	
}
