//
package com.hoangnguyen.QuanLyDanCu.view;
import javax.swing.JPanel;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 23, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 23, 2022
 */
public class NhanVienGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel backgroundImage;
	
	public NhanVienGUI() {
		setLayout(null);
		backgroundImage = new JLabel();
		backgroundImage.setBounds(10, 10,
				AdminPanel.W_FRAME - AdminPanel.INSETS.left - AdminPanel.INSETS.right,
				AdminPanel.H_FRAME - AdminPanel.INSETS.bottom - AdminPanel.INSETS.top);
		
		backgroundImage.setIcon(new ImageIcon("D:/HoangJava/QuanLyDanCu/src/main/java/com/hoangnguyen/QuanLyDanCu/icon/phan-mem-quan-ly-luong-9.jpg"));
		
		add(backgroundImage);
		
	}
}
