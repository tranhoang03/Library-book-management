package bean;

public class dangnhapbean {
	String Tendangnhap;
	String MatKhau;
	Boolean Quyen;
	public dangnhapbean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public dangnhapbean(String tendangnhap, String matKhau, Boolean quyen) {
		super();
		Tendangnhap = tendangnhap;
		MatKhau = matKhau;
		Quyen = quyen;
	}



	public String getTendangnhap() {
		return Tendangnhap;
	}
	public void setTendangnhap(String tendangnhap) {
		Tendangnhap = tendangnhap;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	
	@Override
	public String toString() {
		return "dangnhapbean [Tendangnhap=" + Tendangnhap + ", MatKhau=" + MatKhau + "]";
	}
	
}
