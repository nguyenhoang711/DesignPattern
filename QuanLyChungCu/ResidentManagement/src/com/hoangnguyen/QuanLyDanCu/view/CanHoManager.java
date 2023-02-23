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
import com.hoangnguyen.QuanLyDanCu.dao.CanHoDAO;
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
public class CanHoManager extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField toa_text,tang_text,dientich_text,gia_text,textSearch;
	private JLabel bigLabel,toaLabel,sodtLabel,lbldientich,lblgia,lblsophong;
	private JButton btnUpdate,deletebtn,btnSave,btnTimKiem;
	private JComboBox statusBox;
	private String[] vecHeader = {"Mã căn hộ","Số phòng","Tòa","Tầng","Diện tích","Giá","Trạng thái"};
	private String[][] canhos;
	private static IResidentRepository repository = new ResidentRepository();
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblmach;
	private JTextField mach_text;
	private JTextField sophong_text;
	/**
	 * Create the panel.
	 */
	public CanHoManager() {
		setLayout(null);
		
		bigLabel = new JLabel("Danh sách căn hộ");
		bigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bigLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bigLabel.setBounds(304, 25, 349, 30);
		add(bigLabel);
		
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				statusBox.setEnabled(true);
				gia_text.setEditable(true);
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(96, 360, 118, 42);
		add(btnUpdate);
		
		deletebtn = new JButton("Xóa");
		deletebtn.setEnabled(false);
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				CanHoDAO.deleteCanHo(id);
				JOptionPane.showMessageDialog(table, "Xóa thành công");
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deletebtn.setBounds(304, 360, 118, 48);
		add(deletebtn);
		
		toaLabel = new JLabel("Tòa:");
		toaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toaLabel.setBounds(43, 486, 101, 35);
		add(toaLabel);
		
		toa_text = new JTextField();
		toa_text.setEditable(false);
		toa_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toa_text.setBounds(177, 486, 220, 35);
		add(toa_text);
		toa_text.setColumns(10);
		
		sodtLabel = new JLabel("Tầng:");
		sodtLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sodtLabel.setBounds(43, 535, 101, 35);
		add(sodtLabel);
		
		tang_text = new JTextField();
		tang_text.setEditable(false);
		tang_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tang_text.setColumns(10);
		tang_text.setBounds(177, 535, 220, 35);
		add(tang_text);
		
		lbldientich = new JLabel("Diện tích");
		lbldientich.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldientich.setBounds(500, 486, 101, 35);
		add(lbldientich);
		
		dientich_text = new JTextField();
		dientich_text.setEditable(false);
		dientich_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dientich_text.setColumns(10);
		dientich_text.setBounds(596, 486, 220, 35);
		add(dientich_text);
		
		lblgia = new JLabel("Giá");
		lblgia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgia.setBounds(500, 535, 89, 35);
		add(lblgia);
		
		gia_text = new JTextField();
		gia_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gia_text.setEditable(false);
		gia_text.setColumns(10);
		gia_text.setBounds(596, 535, 220, 35);
		add(gia_text);
		
		statusBox = new JComboBox();
		statusBox.setEnabled(false);
		statusBox.setModel(new DefaultComboBoxModel(new String[] {"AVAILABLE", "RENTED", "SELLED", "ON_HOLD"}));
		statusBox.setSelectedIndex(0);
		statusBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		statusBox.setBounds(798, 296, 118, 42);
		add(statusBox);
		
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
		
		lblmach = new JLabel("Mã căn hộ:");
		lblmach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmach.setBounds(43, 437, 118, 30);
		add(lblmach);
		
		mach_text = new JTextField();
		mach_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mach_text.setEditable(false);
		mach_text.setColumns(10);
		mach_text.setBounds(177, 435, 220, 35);
		add(mach_text);
		
		lblsophong = new JLabel("Số phòng:");
		lblsophong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblsophong.setBounds(500, 437, 89, 30);
		add(lblsophong);
		
		sophong_text = new JTextField();
		sophong_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sophong_text.setEditable(false);
		sophong_text.setColumns(10);
		sophong_text.setBounds(596, 435, 220, 35);
		add(sophong_text);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(btnSave, "Cap nhat thanh cong");
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(513, 360, 118, 42);
		add(btnSave);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				btnUpdate.setEnabled(true);
				deletebtn.setEnabled(true);
				String mach = table.getValueAt(row, 0).toString();
				String sophong = table.getValueAt(row, 1).toString();
				String toa = table.getValueAt(row, 2).toString();
				String tang = table.getValueAt(row, 3).toString();
				String dientich = table.getValueAt(row, 4).toString();
				String gia = table.getValueAt(row, 5).toString();
				String loai = table.getValueAt(row, 6).toString();
				
				mach_text.setText(mach);
				toa_text.setText(toa);
				dientich_text.setText(dientich);
				tang_text.setText(tang);
				gia_text.setText(gia);
				if(loai.equals("AVAILABLE"))
					statusBox.setSelectedIndex(0);
				else if(loai.equals("RENTED"))
					statusBox.setSelectedIndex(1);
				else if(loai.equals("SELLED"))
					statusBox.setSelectedIndex(2);
				else statusBox.setSelectedIndex(3);
				sophong_text.setText(sophong);
			}
		});
		
		textSearch = new JTextField();
		textSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem.doClick();
			}
		});
		textSearch.setBounds(748, 69, 205, 42);
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
		btnTimKiem.setBounds(781, 144, 134, 42);
		add(btnTimKiem);
		
		//quy dịnh bang dữ liệu
		canhos = repository.printAllApartment();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		for(int i = 0;i<canhos.length;i++) {
			model.addRow(canhos[i]);
		}
	}
	
	public void update() {
		String mach = mach_text.getText();
		float gia = Float.parseFloat(gia_text.getText());
		String loai = statusBox.getSelectedItem().toString();
		CanHoDAO.updateCanHo(mach, gia, loai);
	}
}
