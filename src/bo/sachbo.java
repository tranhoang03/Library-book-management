package bo;

import java.util.ArrayList;

import bean.sachbean;
import dao.sachdao;

public class sachbo {
	//getsach
	ArrayList<sachbean> ds;
	sachdao sdao=new sachdao();
	public ArrayList<sachbean> getsach() throws Exception{
		ds=sdao.getsach();
		return ds;
	}
	//tim theo ma sach. tra ve 1 phan tu
	public sachbean TimMasach(String masach) throws Exception{
		for(sachbean s:ds) {
			if(s.getMasach().equals(masach)) {
				return s;
			}
		}
		return null;
		
	}
	//tim sach theo ma loai
	public ArrayList<sachbean> TimMaloai(String maloai) throws Exception{

		  ArrayList<sachbean> tam=new ArrayList<sachbean>();

		  for(sachbean s: ds) {

		  if(s.getMaloai().equals(maloai)) {

			  	tam.add(s);

		  }}

		  return tam;

		  }
	public ArrayList<sachbean> Sua(String masach, Long soluong) throws Exception{
		
		int n=ds.size();
		
		for(int i=0;i<n;i++)
		
		if(ds.get(i).getMasach().equals(masach)) {
		
		sachbean dv=ds.get(i);
		Long sl=dv.getSoluong();
		
		dv.setSoluong(sl-soluong);
		
		
		ds.set(i, dv);//Sua trong bo nho
		
		sdao.Sua(masach,soluong);//Sua trong csdl
		
		
		break;
		
		}
		
		return ds;
		
		}
	public Boolean soluong(String soluong) throws Exception{
		return sdao.soluong(soluong);
	}
}
