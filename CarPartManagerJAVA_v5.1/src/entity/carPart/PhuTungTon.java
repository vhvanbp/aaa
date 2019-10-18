package entity.carPart;

public class PhuTungTon {
	//Region - properties
	private String maPT; 
	private PhuTungXe ptx; 
	private int soLuongBanDau;
	private int soLuongHienTai;
	private int thangNhap;
	private int namNhap;
	//EndRegion
	
	//Region - constructors
	public PhuTungTon(String maPT, int soLuongBanDau, int soLuongHienTai, int thangNhap, int namNhap) {
		super();
		this.maPT = maPT;
		this.soLuongBanDau = soLuongBanDau;
		this.soLuongHienTai = soLuongHienTai;
		this.thangNhap = thangNhap;
		this.namNhap = namNhap;
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

	public int getSoLuongBanDau() {
		return soLuongBanDau;
	}
	
	public void setSoLuongBanDau(int soLuongBanDau) {
		this.soLuongBanDau = soLuongBanDau;
	}
	
	public int getSoLuongHienTai() {
		return soLuongHienTai;
	}
	
	public void setSoLuongHienTai(int soLuongHienTai) {
		this.soLuongHienTai = soLuongHienTai;
	}
	
	public int getThangNhap() {
		return thangNhap;
	}
	
	public void setThangNhap(int thangNhap) {
		this.thangNhap = thangNhap;
	}
	
	public int getNamNhap() {
		return namNhap;
	}
	
	public void setNamNhap(int namNhap) {
		this.namNhap = namNhap;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "PhuTungTon [maPT=" + maPT + ", ptx=" + ptx + ", soLuongBanDau=" + soLuongBanDau + ", soLuongHienTai="
				+ soLuongHienTai + ", thangNhap=" + thangNhap + ", namNhap=" + namNhap + "]";
	}
	//EndRegion

}
