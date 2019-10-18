package entity.users;

import java.util.Date;

public class NhanVien extends ThanhVien {
	//Region - properties
	private Date ngayVaoLam;
	private int mucLuong;
	private double tienLuong;
	//EndRegion
	
	//Region - constructors
	public NhanVien(String maTV, String hoTen, String loaiThanhVien, String email, 
					String sdt, String taiKhoan, String matKhau,
					Date ngayVaoLam, int mucLuong, double tienLuong) {
		super(maTV, hoTen, loaiThanhVien, email, sdt, taiKhoan, matKhau);
		this.ngayVaoLam = ngayVaoLam;
		this.mucLuong = mucLuong;
		this.tienLuong = tienLuong;
	}
	
	public NhanVien(String maTV, String hoTen, Date ngayVaoLam, int mucLuong, double tienLuong) {
		super(maTV, hoTen);
		this.ngayVaoLam = ngayVaoLam;
		this.mucLuong = mucLuong;
		this.tienLuong = tienLuong;
	}
	//EndRegion

	//Region - setter getter
	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}
	
	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	
	public int getMucLuong() {
		return mucLuong;
	}
	
	public void setMucLuong(int mucLuong) {
		this.mucLuong = mucLuong;
	}
	
	public double getTienLuong() {
		return tienLuong;
	}
	
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "NhanVien [ngayVaoLam=" + ngayVaoLam + ", mucLuong=" + mucLuong + ", tienLuong=" + tienLuong + "]";
	}
	//EndRegion

}
