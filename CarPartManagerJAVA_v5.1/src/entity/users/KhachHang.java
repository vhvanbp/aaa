package entity.users;

public class KhachHang extends ThanhVien {
	//Region - properties
	private int soHangMua; 
	private String loaiXeDangDung;
	//EndRegion
	
	//Region - constructors
	public KhachHang(String maTV, String hoTen, String loaiThanhVien, String email, 
					 String sdt, String taiKhoan, String matKhau, int soHangMua, 
					 String loaiXeDangDung) {
		super(maTV, hoTen, loaiThanhVien, email , sdt, taiKhoan, matKhau);
		this.soHangMua = soHangMua;
		this.loaiXeDangDung = loaiXeDangDung;
	}	
	
	public KhachHang(String maTV, String hoTen, String loaiThanhVien, String email, 
			 		 String sdt, String taiKhoan, String matKhau, String loaiXeDangDung) {
		super(maTV, hoTen, loaiThanhVien, email, sdt,taiKhoan, matKhau);
		this.loaiXeDangDung = loaiXeDangDung;
	}
	//EndRegion

	//Region - setter getter
	public int getSoHangMua() {
		return soHangMua;
	}
	public void setSoHangMua(int soHangMua) {
		this.soHangMua = soHangMua;
	}
	public String getLoaiXeDangDung() {
		return loaiXeDangDung;
	}
	public void setLoaiXeDangDung(String loaiXeDangDung) {
		this.loaiXeDangDung = loaiXeDangDung;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "KhachHang [soHangMua=" + soHangMua + ", loaiXeDangDung=" + loaiXeDangDung + "]";
	}	
	//EndRegion
	

}
