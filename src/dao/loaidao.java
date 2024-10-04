	package dao;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import bean.loaibean;

public class loaidao {

		public ArrayList<loaibean> getloai() throws Exception{

			try {

			ArrayList<loaibean> ds= new ArrayList<loaibean>();

			//b1: Ket noi vao csdl

//			Ketnoi kn= new Ketnoi();
//
//			kn.ketnoi();
			

			//B2: Tao cau lenh sql

			String sql="select * from loai";

			//B3: tao cau lenh

			PreparedStatement cmd=Ketnoi.cn.prepareStatement(sql);

			//b4: Thuc hien cau lenh

			ResultSet rs= cmd.executeQuery();

			//b5: Duyet qua rs

			while(rs.next()) {

			String maloai=rs.getString("maloai");

			String tenloai=rs.getString("tenloai");

			ds.add(new loaibean(maloai, tenloai));

			 

			} rs.close();
			
			return ds;

			} catch (Exception e) {

			e.printStackTrace();

			return null;

			}

			}
	}
	
