package entity.carPart;

import java.util.Date;

public class PhieuNhap {
	//Region - properties
	private String maPT;
	private PhuTungXe ptx;
	private int soLuongNhap;
	private Date ngayNhapHang;
	//EndRegion
	
	//Region - constructors
	public PhieuNhap(String maPT, int soLuongNhap, Date ngayNhapHang) {
		super();
		this.maPT = maPT;
		this.soLuongNhap = soLuongNhap;
		this.ngayNhapHang = ngayNhapHang;
	}
	//EndRegion

	//Region - setter getter
	public String getMaPT() {
		return maPT;
	}
	
	public void setMaPT(String maPT) {
		this.maPT = maPT;
	}
	
	public PhuTungXe getPtx() {
		return ptx;
	}
	
	public void setPtx(PhuTungXe ptx) {
		this.ptx = ptx;
	}
	
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	
	public Date getNgayNhapHang() {
		return ngayNhapHang;
	}
	
	public void setNgayNhapHang(Date ngayNhapHang) {
		this.ngayNhapHang = ngayNhapHang;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "PhieuNhap [maPT=" + maPT + ", ptx=" + ptx + ", soLuongNhap="
				+ soLuongNhap + ", ngayNhapHang=" + ngayNhapHang + "]";
	}
	//EndRegion
	
}
