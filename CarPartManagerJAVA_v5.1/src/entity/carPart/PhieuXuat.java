package entity.carPart;

import java.util.Date;


public class PhieuXuat {
	//Region - properties
	private String maPX;
	private String maPT;
	private PhuTungXe ptx;
	private int soLuongXuat;
	private Date ngayXuatHang;
	private double chiPhi;
	//EndRegion

	//Region - constructors
	
	public PhieuXuat(String maPX, String maPT, int soLuongXuat, Date ngayXuatHang, double chiPhi) {
		super();
		this.maPX = maPX;
		this.maPT = maPT;
		this.soLuongXuat = soLuongXuat;
		this.ngayXuatHang = ngayXuatHang;
		this.chiPhi = chiPhi;
	}
	//EndRegion

	//Region - setter getter
	public String getMaPX() {
		return maPX;
	}
	
	public void setMaPX(String maPX) {
		this.maPX = maPX;
	}
	
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
	
	public int getSoLuongXuat() {
		return soLuongXuat;
	}
	
	public void setSoLuongXuat(int soLuongXuat) {
		this.soLuongXuat = soLuongXuat;
	}
	
	public Date getNgayXuatHang() {
		return ngayXuatHang;
	}
	
	public void setNgayXuatHang(Date ngayXuatHang) {
		this.ngayXuatHang = ngayXuatHang;
	}
	
	public double getChiPhi() {
		return chiPhi;
	}
	
	public void setChiPhi(double chiPhi) {
		this.chiPhi = chiPhi;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "PhieuXuat [maPT=" + maPT + ", ptx=" + ptx + ", soLuongXuat="
				+ soLuongXuat + ", ngayXuatHang=" + ngayXuatHang + ", chiPhi=" + chiPhi + "]";
	}
	//EndRegion
}
