//
package com.hoangnguyen.QuanLyDanCu.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;
import com.hoangnguyen.QuanLyDanCu.dao.HopDongDAO;

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
public class HopDongManager extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField kihan_text,ngayKy_text,machuho_text,maCanHo_text,textSearch;
	private JLabel bigLabel,kihanLabel,ngaykyLabel,lblMaCD,lblmacanho,lblnguoitao;
	private JButton btnUpdate,deletebtn,btnSave, btnTimKiem;
	private JComboBox typeBox;
	private String[] vecHeader = {"Mã hợp đồng","Loại","Kì hạn","Ngày ký","Mã chủ hộ","Mã người tạo","Mã căn hộ"};
	private String[][] hopdongs;
	private static IResidentRepository repository = new ResidentRepository();
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblmahd;
	private JTextField maHD_text;
	private JTextField nguoitao_text;
	/**
	 * Create the panel.
	 */
	public HopDongManager() {
		setLayout(null);
		
		bigLabel = new JLabel("Danh sách hợp đồng");
		bigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bigLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bigLabel.setBounds(304, 25, 349, 30);
		add(bigLabel);
		
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				kihan_text.setEditable(true);
				typeBox.setEnabled(true);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(90, 348, 118, 48);
		add(btnUpdate);
		
		deletebtn = new JButton("Xóa");
		deletebtn.setEnabled(false);
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deletebtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deletebtn.setBounds(287, 348, 118, 48);
		add(deletebtn);
		
		kihanLabel = new JLabel("Kì hạn (tháng):");
		kihanLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kihanLabel.setBounds(28, 473, 124, 35);
		add(kihanLabel);
		
		kihan_text = new JTextField();
		kihan_text.setEditable(false);
		kihan_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kihan_text.setBounds(220, 473, 220, 35);
		add(kihan_text);
		kihan_text.setColumns(10);
		
		ngaykyLabel = new JLabel("Ngày ký (YYYY-DD-MM):");
		ngaykyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ngaykyLabel.setBounds(28, 531, 182, 35);
		add(ngaykyLabel);
		
		ngayKy_text = new JTextField();
		ngayKy_text.setEditable(false);
		ngayKy_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ngayKy_text.setColumns(10);
		ngayKy_text.setBounds(220, 531, 220, 35);
		add(ngayKy_text);
		
		lblMaCD = new JLabel("Mã chủ hộ:");
		lblMaCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaCD.setBounds(509, 473, 101, 35);
		add(lblMaCD);
		
		machuho_text = new JTextField();
		machuho_text.setEditable(false);
		machuho_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		machuho_text.setColumns(10);
		machuho_text.setBounds(654, 473, 220, 35);
		add(machuho_text);
		
		lblmacanho = new JLabel("Mã căn hộ:");
		lblmacanho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmacanho.setBounds(509, 531, 89, 35);
		add(lblmacanho);
		
		maCanHo_text = new JTextField();
		maCanHo_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maCanHo_text.setEditable(false);
		maCanHo_text.setColumns(10);
		maCanHo_text.setBounds(654, 531, 220, 35);
		add(maCanHo_text);
		
		typeBox = new JComboBox();
		typeBox.setEnabled(false);
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"RENT", "BUY"}));
		typeBox.setSelectedIndex(0);
		typeBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeBox.setBounds(824, 288, 101, 48);
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
		
		lblmahd = new JLabel("Mã hợp đồng:");
		lblmahd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmahd.setBounds(28, 418, 118, 30);
		add(lblmahd);
		
		maHD_text = new JTextField();
		maHD_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maHD_text.setEditable(false);
		maHD_text.setColumns(10);
		maHD_text.setBounds(220, 416, 220, 35);
		add(maHD_text);
		
		lblnguoitao = new JLabel("Mã người tạo:");
		lblnguoitao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblnguoitao.setBounds(509, 418, 118, 30);
		add(lblnguoitao);
		
		nguoitao_text = new JTextField();
		nguoitao_text.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nguoitao_text.setEditable(false);
		nguoitao_text.setColumns(10);
		nguoitao_text.setBounds(654, 416, 220, 35);
		add(nguoitao_text);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				JOptionPane.showMessageDialog(btnSave, "Cap nhat thanh cong");
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(455, 348, 118, 51);
		add(btnSave);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				btnUpdate.setEnabled(true);
				deletebtn.setEnabled(true);
				String mahd = table.getValueAt(row, 0).toString();
				String loai = table.getValueAt(row, 1).toString();
				String kihan = table.getValueAt(row, 2).toString();
				String ngayky = table.getValueAt(row, 3).toString();
				String cudan_id = table.getValueAt(row, 4).toString();
				String nguoitao = table.getValueAt(row, 5).toString();
				String macanho = table.getValueAt(row, 6).toString();
				
				maHD_text.setText(mahd);
				kihan_text.setText(kihan);
				if(loai.equals("RENT"))
					typeBox.setSelectedIndex(0);
				else typeBox.setSelectedIndex(1);
				machuho_text.setText(cudan_id);
				ngayKy_text.setText(ngayky);
				maCanHo_text.setText(macanho);
				nguoitao_text.setText(nguoitao);
			}
		});
		
		textSearch = new JTextField();
		textSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTimKiem.doClick();
			}
		});
		textSearch.setBounds(762, 69, 205, 41);
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
		btnTimKiem.setBounds(791, 145, 134, 49);
		add(btnTimKiem);
		//quy dịnh dữ liệu
		hopdongs = repository.printAllContract();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		for(int i = 0;i<hopdongs.length;i++) {
			model.addRow(hopdongs[i]);
		}
	}
	
	public void update() {
		int mahd = Integer.parseInt(maHD_text.getText());
		Float kihan = Float.parseFloat(kihan_text.getText());
		String loai = typeBox.getSelectedItem().toString();
		HopDongDAO.updateHopDong(mahd, kihan, loai);
	}

}
