package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import bean.loaibean;
import bean.sachbean;
import bo.loaibo;
import bo.sachbo;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmBanHang extends JFrame {
	JList jlist = new JList();
	JComboBox cmbmasach = new JComboBox();
	private JPanel contentPane;
	private JTextField txttensach;
	private JTextField txttacgia;
	private JTextField txtgia;
	private JTextField txtsoluong;
	private JTextField txttt;
	private JTable table;
	private JTextField txtsl;
	loaibo lbo=new loaibo();
	sachbo sbo= new sachbo();
	ArrayList<sachbean> ds;
	ArrayList<sachbean> dsduocchon;
	void Napbang(ArrayList<sachbean> ds ) {
		DefaultTableModel sv=new DefaultTableModel();
			sv.addColumn("Mã sách");
			sv.addColumn("Tên sách");
			sv.addColumn("Số lượng");
			sv.addColumn("Giá");
			sv.addColumn("Số tập");
			sv.addColumn("Ngày nhập");
			sv.addColumn("Tác giả");
			for(sachbean h: ds) {
				Object[] t=new Object[7];
				t[0]=h.getMasach();
				t[1]=h.getTensach();
				t[2]=h.getSoluong();
				t[3]=h.getGia();
				t[4]=h.getSotap();
				t[5]=h.getNgayNhap();
				t[6]=h.getTacgia();
				sv.addRow(t);}
			table.setModel(sv);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBanHang frame = new FrmBanHang();
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
	public FrmBanHang() {
		this.setTitle("Xin chao"+FRMtkmk.un);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
					
					ds=sbo.getsach();
				//thêm đối tượng vào list loai sach
					DefaultListModel<loaibean> mh=new DefaultListModel<>();
					for(loaibean l:lbo.getloai())
						mh.addElement(l);		
					jlist.setModel(mh);
					}catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chọn loại");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBounds(35, 15, 104, 24);
		contentPane.add(lblNewLabel);

		jlist.addListSelectionListener(new ListSelectionListener() {
	 // Mỗi lần chọn vào 1 đối tượng của list loại sách thì sẽ hiện các mã sách tương tứng ơ combobox
			public void valueChanged(ListSelectionEvent e) {
					cmbmasach.removeAllItems();
				loaibean loai=(loaibean)jlist.getSelectedValue();
				
				txtsoluong.setText("");
				txtsoluong.grabFocus();
				try {
					
					dsduocchon=sbo.TimMaloai(loai.getMaloai());
					Napbang(dsduocchon);
//			//**********
//					cmbmasach.addItem(" ");//th
			//**********
					for(sachbean s:dsduocchon) {
						cmbmasach.addItem(s.getMasach());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		jlist.setBounds(10, 36, 153, 300);
		contentPane.add(jlist);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(188, 25, 78, 14);
		contentPane.add(lblNewLabel_1);
		cmbmasach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sachbean s;
			    //*********** 
			     ArrayList<sachbean> sach=new ArrayList<>();
			    //*********** 
			     
			//tô đậm dòng thông tin chứa mã sách trong bảng
				try {
					int rowIndex = -1; // chỉ số dòng chứa dữ liệu được tìm thấy
					// duyệt qua từng dòng trong bảng để tìm kiếm dữ liệu
					for (int i = 0; i < table.getRowCount(); i++) {
					    Object value = table.getValueAt(i, 0); // lấy giá trị của ô dữ liệu tại dòng i, cột 0
					    //lấy ra mã sách ở combobox sau đó so sánh với từng mã trong bảng đối tượng nào có mã trùng thì tô đậm
					    if (cmbmasach.getSelectedItem().toString().toLowerCase().equals(value.toString().toLowerCase())) { // nếu giá trị được tìm kiếm được tìm thấy
					        rowIndex = i; // lưu lại chỉ số dòng chứa dữ liệu được tìm thấy
					        break; // thoát khỏi vòng lặp
					    }
					}

					if (rowIndex >= 0) { // nếu tìm thấy dữ liệu
					    table.setRowSelectionInterval(rowIndex, rowIndex); // chọn và tô đậm dòng chứa dữ liệu đó
					}
					
					
					
					
	             //khi chọn ma sach ở cmbox thì hiện thông tin sách đó ở các textfield
					s = sbo.TimMasach(cmbmasach.getSelectedItem().toString());
				 //*********** 
//					sach.add(s);
//					Napbang(sach);
				 //*********** 
					txttensach.setText(s.getTensach());;
					txtgia.setText(s.getGia().toString());
					txtsl.setText(s.getSoluong().toString());;
					txttacgia.setText(s.getTacgia());
				} catch (Exception e1) {
					
				}	
			}
		});
		
		cmbmasach.setBounds(276, 17, 257, 24);
		contentPane.add(cmbmasach);
		
		JLabel lblNewLabel_2 = new JLabel("Tên Sách");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(188, 68, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		txttensach = 	new JTextField();
		txttensach.setHorizontalAlignment(SwingConstants.LEFT);
		txttensach.setForeground(Color.BLACK);
		txttensach.setBounds(276, 60, 257, 31);
		contentPane.add(txttensach);
		txttensach.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tác giả");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(188, 104, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		txttacgia = new JTextField();
		txttacgia.setBounds(276, 102, 257, 28);
		contentPane.add(txttacgia);
		txttacgia.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Giá");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(188, 143, 48, 14);
		contentPane.add(lblNewLabel_4);
		
		txtgia = new JTextField();
		txtgia.setBounds(276, 141, 257, 29);
		contentPane.add(txtgia);
		txtgia.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Nhập số lượng");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(169, 237, 97, 14);
		contentPane.add(lblNewLabel_5);
		
		txtsoluong = new JTextField();
		txtsoluong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					txttt.grabFocus();
					try{
						//nếu định dạng số lượng đúng thì tính tiền  
						if(sbo.soluong(txtsoluong.getText())) {
							Long thanhtien=Long.parseLong(txtsoluong.getText())*Long.parseLong(txtgia.getText());
							txttt.setText(thanhtien.toString());
						}
						//ngược lại thì báo lỗi và nhập lại
						else {
						JOptionPane.showMessageDialog(null, "Định dạng số lượng không đúng!", "Erro",JOptionPane.ERROR_MESSAGE);
						txtsoluong.setText("");
						txtsoluong.grabFocus();
					}
						}catch (Exception e1) {
					
					}
				}
				
			}
		});
		txtsoluong.setBounds(276, 226, 257, 29);
		contentPane.add(txtsoluong);
		txtsoluong.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Thành tiền");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(173, 279, 97, 14);
		contentPane.add(lblNewLabel_6);
		
		txttt = new JTextField();
		txttt.setForeground(Color.BLACK);
		txttt.setHorizontalAlignment(SwingConstants.CENTER);
		txttt.setFont(new Font("Tahoma", Font.BOLD, 15));
		txttt.setEnabled(false);
		txttt.setBounds(276, 275, 257, 24);
		contentPane.add(txttt);
		txttt.setColumns(10);
		
		JButton btnban = new JButton("BÁN");
		btnban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txttensach.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Chưa nhập chọn mã sách", "ERORR",JOptionPane.ERROR_MESSAGE);
						txtsoluong.grabFocus();
						
					}else {
					if(sbo.soluong(txtsoluong.getText())&&Double.parseDouble(txtsoluong.getText())<=Double.parseDouble(txtsl.getText())) {
					sbo.Sua(cmbmasach.getSelectedItem().toString(),Long.parseLong(txtsoluong.getText()));
					Long soluongmoi=Long.parseLong(txtsl.getText())-Long.parseLong(txtsoluong.getText());
					txtsl.setText(soluongmoi.toString());
					Napbang(dsduocchon);
					txttt.setText("");
					}else {
						
						if(txtsoluong.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,"Chưa nhập số lượng,vui lòng nhập lại!", "ERORR",JOptionPane.ERROR_MESSAGE);
							txtsoluong.grabFocus();
						}
						else {
							JOptionPane.showMessageDialog(null,"Số lượng yêu cầu vượt quá số lượng hiện có!!", "ERORR",JOptionPane.ERROR_MESSAGE);
							JOptionPane.showMessageDialog(null,"Nhập lại số lượng !" );
							txtsoluong.grabFocus();
							txtsoluong.setText("");
						}
					}
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnban.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnban.setBounds(605, 16, 89, 25);
		contentPane.add(btnban);
		
		JButton btnfind = new JButton("Tìm mã sách");
		btnfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=JOptionPane.showInputDialog("Nhập mã sách muốn tìm kiếm");
				ArrayList<sachbean> x=new ArrayList<>();
				sachbean o;
				try {
					o = sbo.TimMasach(str);
					if(o!=null){
						x.add(o);
						txttensach.setText(o.getTensach());;
						txtgia.setText(o.getGia().toString());
						txtsl.setText(o.getSoluong().toString());;
						txttacgia.setText(o.getTacgia());
						txtsoluong.setText("");
						
				//duyệt qua tất cả phần tử của jlist 
						for (int i = 0; i < jlist.getModel().getSize(); i++) {
						    loaibean element = (loaibean)jlist.getModel().getElementAt(i);
						    ArrayList<sachbean> tam;
							tam=sbo.TimMaloai(element.getMaloai());//tìm tất cả sách có ma loai tuong ung
							for(sachbean s:tam) {//tìm kiếm xem với mỗi mã loại có mã sách đang muốn tìm không
								if(s.getMasach()==o.getMasach()) {
									jlist.setSelectedIndex(i);//nếu có mã sách tương ứng trong loại đó thì tô đậm phần tử mã loại trong j list
									cmbmasach.setSelectedItem(o.getMasach());
								}
							}
						}
					//nạp bảng chứa thông tin mã sách muốn tìm
						Napbang(x);
						
					}else {
						JOptionPane.showMessageDialog(null,"Không có mã sách muốn tìm kiếm!!", "ERORR",JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
							}
		});
		btnfind.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnfind.setBounds(573, 62, 142, 27);
		contentPane.add(btnfind);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 339, 734, 196);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();

		tabbedPane.addTab("Thông tin Sách theo Loại", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//lấy chỉ số hàng được chọn
				int d=table.getSelectedRow();
				Object a= table.getValueAt(table.getSelectedRow(),0).toString();
				txttensach.setText(table.getValueAt(d,1).toString());
				txtsl.setText(table.getValueAt(d,2).toString());
				txtgia.setText(table.getValueAt(d,3).toString());
				txttacgia.setText(table.getValueAt(d,6).toString());
				cmbmasach.setSelectedItem(a);
				txttt.setText("");
				txtsoluong.setText("");
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_7 = new JLabel("Số lượng");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(188, 190, 60, 14);
		contentPane.add(lblNewLabel_7);
		
		txtsl = new JTextField();
		txtsl.setBounds(276, 181, 257, 24);
		contentPane.add(txtsl);
		txtsl.setColumns(10);
	}
}
