package entity.carPart;

import entity.users.NhaCungCap;

public class PhuTungXe {	
	//Region - properties
	private String maPT;
	private String tenPT;
	private double giaTien;
	private String maNhaCungCap;
	private NhaCungCap nhaCungCap;
	private String maLoaiPTX;
	private LoaiPhuTungXe loaiPTX;
	private String loaiXe;
	private String hang;
	private int soLuong;
	private String maKhu;
	//EndRegion

	//Region - constructors
	public PhuTungXe(String maPT, String tenPT, double giaTien, String maNhaCungCap, String maLoaiPTX, String loaiXe,
			String hang) {
		super();
		this.maPT = maPT;
		this.tenPT = tenPT;
		this.giaTien = giaTien;
		this.maNhaCungCap = maNhaCungCap;
		this.maLoaiPTX = maLoaiPTX;
		this.loaiXe = loaiXe;
		this.hang = hang;
	}
	
	public PhuTungXe(String maPT, String tenPT, double giaTien, String maNhaCungCap,
			String maLoaiPTX, String loaiXe, String hang, int soLuong, String maKhu) {
		super();
		this.maPT = maPT;
		this.tenPT = tenPT;
		this.giaTien = giaTien;
		this.maNhaCungCap = maNhaCungCap;
		this.maLoaiPTX = maLoaiPTX;
		this.loaiXe = loaiXe;
		this.hang = hang;
		this.soLuong = soLuong;
		this.maKhu = maKhu;
	}

	public PhuTungXe() {
		
	}
	//EndRegion

	//Region - setter getter
	public String getMaPT() {
		return maPT;
	}
	
	public void setMaPT(String maPT) {
		this.maPT = maPT;
	}
	
	public String getTenPT() {
		return tenPT;
	}
	
	public void setTenPT(String tenPT) {
		this.tenPT = tenPT;
	}
	
	public double getGiaTien() {
		return giaTien;
	}
	
	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}
	
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
	public String getMaLoaiPTX() {
		return maLoaiPTX;
	}
	
	public void setMaLoaiPTX(String maLoaiPTX) {
		this.maLoaiPTX = maLoaiPTX;
	}
	
	public LoaiPhuTungXe getLoaiPTX() {
		return loaiPTX;
	}
	
	public void setLoaiPTX(LoaiPhuTungXe loaiPTX) {
		this.loaiPTX = loaiPTX;
	}
	
	public String getLoaiXe() {
		return loaiXe;
	}
	
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	
	public String getHang() {
		return hang;
	}
	
	public void setHang(String hang) {
		this.hang = hang;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public String getMaKhu() {
		return maKhu;
	}
	
	public void setMaKhu(String maKhu) {
		this.maKhu = maKhu;
	}

	
	//EndRegion

	//Region - toString	
	@Override
	public String toString() {
		return "PhuTungXe [maPT=" + maPT + ", tenPT=" + tenPT + ", giaTien=" + giaTien + ", maNhaCungCap="
				+ maNhaCungCap + ", nhaCungCap=" + nhaCungCap + ", maLoaiPTX=" + maLoaiPTX + ", loaiPTX=" + loaiPTX
				+ ", loaiXe=" + loaiXe + ", hang=" + hang + ", soLuong=" + soLuong + ", maKhu=" + maKhu + "]";
	}
	//EndRegion
}
