package entity.carPart;

public class LoaiPhuTungXe {
	//Region - properties
	private String maLoai; 
	private String loaiPT;
	//EndRegion
	
	//Region - constructors
	public LoaiPhuTungXe(String maLoai, String loaiPT) {
		super();
		this.maLoai = maLoai;
		this.loaiPT = loaiPT;
	}
	//EndRegion

	//Region - setter getter
	public String getMaLoai() {
		return maLoai;
	}
	
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	
	public String getLoaiPT() {
		return loaiPT;
	}
	
	public void setLoaiPT(String loaiPT) {
		this.loaiPT = loaiPT;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "LoaiPhuTungXe [maLoai=" + maLoai + ", loaiPT=" + loaiPT + "]";
	}
	//EndRegion

}
