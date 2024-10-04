package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.dangnhapbean;
import bean.sachbean;

public class dangnhapdao {
	
	public dangnhapbean kt(String Tendangnhap,String MatKhau)throws Exception{
		

		String sql="select *from DangNhap where TenDangNhap=? and MatKhau=?";
		
		//B3: tao cau lenh

		PreparedStatement cmd=Ketnoi.cn.prepareStatement(sql);

		
		cmd.setString(1, Tendangnhap);
		cmd.setString(2, MatKhau);
		ResultSet rs= cmd.executeQuery();
		//b4: Thuc hien cau lenh
		
		dangnhapbean dt=null;
		
		if(rs.next()==true) {
			Boolean Quyen=rs.getBoolean("Quyen");
			dt=new dangnhapbean(Tendangnhap,MatKhau, false);
			return dt;
		}else {
			return dt;
		}
	
		
	}
}
