package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.ketnoibo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FRMketnoi extends JFrame {
	public static FRMtkmk f;
	private JPanel contentPane;
	private JTextField txtsv;
	private JTextField txtQlsach;
	private JTextField txtSa;
	private JPasswordField txtpw;
	 static FRMketnoi  frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new FRMketnoi();
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
	public FRMketnoi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sever name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 30, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Database");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 82, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 139, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 187, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		txtsv = new JTextField();
		txtsv.setText("LAPTOP-E4D1FUO1\\SQLEXPRESS");
		txtsv.setBounds(107, 27, 187, 26);
		contentPane.add(txtsv);
		txtsv.setColumns(10);
		
		txtQlsach = new JTextField();
		txtQlsach.setText("QLSach");
		txtQlsach.setBounds(107, 76, 187, 27);
		contentPane.add(txtQlsach);
		txtQlsach.setColumns(10);
		
		txtSa = new JTextField();
		txtSa.setText("sa");
		txtSa.setBounds(107, 133, 187, 27);
		contentPane.add(txtSa);
		txtSa.setColumns(10);
		
		txtpw = new JPasswordField();
		txtpw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
				try {
					
					ketnoibo kn=new ketnoibo();
					kn.ketnoi(txtsv.getText(), txtQlsach.getText(),txtSa.getText() ,new String( txtpw.getPassword()));
					JOptionPane.showMessageDialog(null,"Connected!");
					
					f=new FRMtkmk();
					f.setVisible(true);
					frame.setVisible(false);//ẩn form đăng nhập
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
				
			}
		});
		txtpw.setBounds(107, 181, 187, 26);
		contentPane.add(txtpw);
		
		JButton btnNewButton = new JButton("Connect");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ketnoibo kn=new ketnoibo();
					
					kn.ketnoi(txtsv.getText(), txtQlsach.getText(),txtSa.getText() ,new String( txtpw.getPassword()));
					JOptionPane.showMessageDialog(null,"Connected!");
					new FRMtkmk().setVisible(true);
					frame.setVisible(false);//ẩn form đăng nhập
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(172, 231, 106, 23);
		contentPane.add(btnNewButton);
	}
}
