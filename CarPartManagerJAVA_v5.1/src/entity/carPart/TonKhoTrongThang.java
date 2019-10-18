package entity.carPart;

public class TonKhoTrongThang {
	//Region - properties
	private String maTon; 
	private int tongSLNhap;
	private int tongSLTon;
	private int tongSLXuat;
	private double tongChiPhiXuat;
	private int thangTon;
	private int namTon;
	//EndRegion
	
	//Region - constructors
	public TonKhoTrongThang(String maTon, int tongSLNhap, int tongSLTon, int tongSLXuat, double tongChiPhiXuat,
			int thangTon, int namTon) {
		super();
		this.maTon = maTon;
		this.tongSLNhap = tongSLNhap;
		this.tongSLTon = tongSLTon;
		this.tongSLXuat = tongSLXuat;
		this.tongChiPhiXuat = tongChiPhiXuat;
		this.thangTon = thangTon;
		this.namTon = namTon;
	}
	//EndRegion

	//Region - setter getter
	public String getMaTon() {
		return maTon;
	}
	
	public void setMaTon(String maTon) {
		this.maTon = maTon;
	}
	
	public int getTongSLNhap() {
		return tongSLNhap;
	}
	
	public void setTongSLNhap(int tongSLNhap) {
		this.tongSLNhap = tongSLNhap;
	}
	
	public int getTongSLTon() {
		return tongSLTon;
	}
	
	public void setTongSLTon(int tongSLTon) {
		this.tongSLTon = tongSLTon;
	}
	
	public int getTongSLXuat() {
		return tongSLXuat;
	}
	
	public void setTongSLXuat(int tongSLXuat) {
		this.tongSLXuat = tongSLXuat;
	}
	
	public double getTongChiPhiXuat() {
		return tongChiPhiXuat;
	}
	
	public void setTongChiPhiXuat(double tongChiPhiXuat) {
		this.tongChiPhiXuat = tongChiPhiXuat;
	}
	
	public int getThangTon() {
		return thangTon;
	}
	
	public void setThangTon(int thangTon) {
		this.thangTon = thangTon;
	}
	
	public int getNamTon() {
		return namTon;
	}
	
	public void setNamTon(int namTon) {
		this.namTon = namTon;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "TonKhoTrongThang [maTon=" + maTon + ", tongSLNhap=" + tongSLNhap + ", tongSLTon=" + tongSLTon
				+ ", tongSLXuat=" + tongSLXuat + ", tongChiPhiXuat=" + tongChiPhiXuat + ", thangTon=" + thangTon
				+ ", namTon=" + namTon + "]";
	}
	//EndRegion

}
