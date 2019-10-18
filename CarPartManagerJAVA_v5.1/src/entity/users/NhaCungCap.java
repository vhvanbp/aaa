package entity.users;

public class NhaCungCap extends ThanhVien{
	//Region - properties
	private String congTy;
	//EndRegion
	
	//Region - constructors
	public NhaCungCap(String maTV, String hoTen, String loaiThanhVien, String email, 
					  String sdt, String taiKhoan, String matKhau, String congTy) {
		super(maTV, hoTen, loaiThanhVien, email, sdt, taiKhoan, matKhau);
		this.congTy = congTy;
	}
	
	public NhaCungCap(String maTV, String hoTen, String email, String congTy) {
		super(maTV, hoTen, email);
		this.congTy = congTy;
	}
	//EndRegion

	//Region - setter getter
	public String getCongTy() {
		return congTy;
	}
	public void setCongTy(String congTy) {
		this.congTy = congTy;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "NhaCungCap [congTy=" + congTy + "]";
	}
	//EndRegion

}
