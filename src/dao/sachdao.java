package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.sachbean;

public class sachdao {
	public ArrayList<sachbean> getsach() throws Exception{

		ArrayList<sachbean> ds= new ArrayList<sachbean>();

		//b1: Ket noi vao csdl
//
//		Ketnoi kn= new Ketnoi();
//
//		kn.ketnoi();

		//B2: Tao cau lenh sql

		String sql="select * from sach";

		//B3: tao cau lenh

		PreparedStatement cmd=Ketnoi.cn.prepareStatement(sql);

		//b4: Thuc hien cau lenh

		ResultSet rs= cmd.executeQuery();

		//b5: Duyet qua rs

		while(rs.next()) {

			//String masach,String tensach Long soluong, Long gia, String maloai, String sotap, String anh, Date ngayNhap,String tacgia

		 

		String masach=rs.getString("masach");
		String tensach=rs.getString("tensach");
		Long gia=rs.getLong("gia");
		Long soluong=rs.getLong("soluong");
		String maloai=rs.getString("maloai");
		String sotap=rs.getString("sotap");
		String anh=rs.getString("anh");
		Date nn=rs.getDate("NgayNhap");

		SimpleDateFormat d= new SimpleDateFormat("dd/MM/yyyy");

		Date NgayNhap=new java.sql.Date(nn.getTime());

		String tacgia=rs.getString("tacgia");

		ds.add(new sachbean(masach,tensach,soluong,gia,maloai,sotap,anh,NgayNhap,tacgia));

		} 

		rs.close();

		return ds;

		}
	//Sua so luong
	public int Sua(String masach,Long soluong) {
		try {

		//b1: Ket noi vao csdl

	

		//B2: Tao cau lenh sql
		String cl="select * from sach";
		String sql="update sach set soluong=? where masach=?";

		//B3: tao cau lenh
		PreparedStatement c=Ketnoi.cn.prepareStatement(cl);
		ResultSet rs=c.executeQuery();
		
		while(rs.next()) {
			if(rs.getString("masach").equals(masach)) {
			PreparedStatement cmd=Ketnoi.cn.prepareStatement(sql);
			cmd.setLong(1, rs.getLong("soluong")-soluong);
			cmd.setString(2, masach);
			int kq= cmd.executeUpdate();
			rs.close();
			return kq;
			}
			}
		//b4: Thuc hien cau lenh

		Ketnoi.cn.close();

		} catch (Exception e) {
		e.printStackTrace();
		
		}
		return 0;
		}
	public Boolean soluong(String soluong) throws Exception{
		try {
			Long.parseLong(soluong);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
