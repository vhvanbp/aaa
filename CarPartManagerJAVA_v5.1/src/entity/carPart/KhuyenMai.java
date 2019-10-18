package entity.carPart;

import java.util.Date;

import entity.users.NhaCungCap;

public class KhuyenMai {
	// Region - properties
	private String maKM;
	private String tenKM;
	private String maPT;
	private PhuTungXe phuTung;
	private String maNCC;
	private NhaCungCap nhaCC;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String moTaKM;
	private double giaTienKM;
	// EndRegion

	// Region - constructors
	public KhuyenMai(String maKM, String tenKM, String maPT, String maNCC, 
					Date ngayBatDau, Date ngayKetThuc, String moTaKM, Double giaTienKM) {
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.maPT = maPT;
		this.maNCC = maNCC;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.moTaKM = moTaKM;
		this.giaTienKM = giaTienKM;
	}	
	
	public KhuyenMai() {	
		
	}
	// EndRegion

	// Region - setter getter
	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	
	public String getTenKM() {
		return tenKM;
	}
	
	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}
	
	public String getMaPT() {
		return maPT;
	}

	public void setMaPT(String maPT) {
		this.maPT = maPT;
	}

	public PhuTungXe getPhuTung() {
		return phuTung;
	}

	public void setPhuTung(PhuTungXe phuTung) {
		this.phuTung = phuTung;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public NhaCungCap getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(NhaCungCap nhaCC) {
		this.nhaCC = nhaCC;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
	public String getMoTaKM() {
		return moTaKM;
	}
	
	public void setMoTaKM(String moTaKM) {
		this.moTaKM = moTaKM;
	}
	
	public double getGiaTienKM() {
		return giaTienKM;
	}
	
	public void setGiaTienKM(double giaTienKM) {
		this.giaTienKM = giaTienKM;
	}
	// EndRegion

	// Region - toString
	@Override
	public String toString() {
		return "KhuyenMai [maKM=" + maKM + ", tenKM=" + tenKM + ", maPT=" + maPT + ", phuTung=" + phuTung + ", maNCC="
				+ maNCC + ", nhaCC=" + nhaCC + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", moTaKM=" + moTaKM + ", giaTienKM=" + giaTienKM + "]";
	}	
	// EndRegion
	
}	
