package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.dangnhapbean;
import bo.dangnhapbo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FRMtkmk extends JFrame {

	private JPanel contentPane;
	private JTextField txttdn;
	private JTextField txtmk;
	dangnhapbo dnbo=new dangnhapbo();
	static FRMtkmk frame;
	 public static String un="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMtkmk frame = new FRMtkmk();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FRMtkmk() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(59, 42, 100, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(59, 89, 100, 20);
		contentPane.add(lblNewLabel_1);
		
		txttdn = new JTextField();
		txttdn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					txtmk.grabFocus();
				}
			}
		});
		txttdn.setBounds(163, 42, 167, 20);
		contentPane.add(txttdn);
		txttdn.setColumns(10);
		
		txtmk = new JTextField();
		txtmk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					try {
						dangnhapbean a= dnbo.kt(txttdn.getText(), txtmk.getText());
						
						if(a!=null){
							JOptionPane.showMessageDialog(null,"Ketnoi thanh cong");
							new FrmBanHang().setVisible(true);
							FRMketnoi.f.setVisible(false);
							un=a.getTendangnhap();
							
						}else {
							JOptionPane.showMessageDialog(null,"Sai Tên đăng nhập hoặc Mật khẩu");
							txtmk.setText("");
							txttdn.setText("");
							txttdn.grabFocus();
						}
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}}
		});
		txtmk.setBounds(163, 89, 167, 20);
		contentPane.add(txtmk);
		txtmk.setColumns(10);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					dangnhapbean a= dnbo.kt(txttdn.getText(), txtmk.getText());
					
					if(a!=null){
						JOptionPane.showMessageDialog(null,"Dang nhap thanh cong");
						new FrmBanHang().setVisible(true);
						FRMketnoi.f.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null,"Sai TK Mk");
					}
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(157, 172, 119, 32);
		contentPane.add(btnNewButton);
	}
}
