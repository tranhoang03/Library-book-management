package bo;

import java.util.ArrayList;

import bean.dangnhapbean;
import dao.dangnhapdao;

public class dangnhapbo {
	dangnhapdao dndao=new dangnhapdao();
	public dangnhapbean kt(String Tendangnhap,String MatKhau)throws Exception{
		return dndao.kt(Tendangnhap, MatKhau);
		
	}
}
