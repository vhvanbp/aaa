package entity.users;

public class QuanLiVien extends ThanhVien {
	//Region - properties
	private String moTaQLV;
	//EndRegion
	
	//Region - constructors
	public QuanLiVien(String maTV, String hoTen, String loaiThanhVien, String email,
			 		  String sdt, String taiKhoan, String matKhau,
			 		  String moTaQLV) {
		super(maTV, hoTen, loaiThanhVien, email, sdt, taiKhoan, matKhau);
		this.moTaQLV = moTaQLV;
	}
	//EndRegion
	
	//Region - setter getter
	public String getMoTaQLV() {
		return moTaQLV;
	}
	public void setMoTaQLV(String moTaQLV) {
		this.moTaQLV = moTaQLV;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "QuanLiVien [moTaQLV=" + moTaQLV + "]";
	}
	//EndRegion

}
