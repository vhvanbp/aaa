package entity.carPart;

public class TonKho {
	//Region - properties
	private String maKhu;
	private int soLuong;
	private int soLuongToiDa;
//	private String trangThai;
	//EndRegion
	
	//Region - constructors
	public TonKho(String maKhu, int soLuong, int soLuongToiDa) {
		super();
		this.maKhu = maKhu;
		this.soLuong = soLuong;
		this.soLuongToiDa = soLuongToiDa;
	}	
	
	public TonKho() {
		super();
	}
	//EndRegion

	//Region - setter getter
	public String getMaKhu() {
		return maKhu;
	}
	
	public void setMaKhu(String maKhu) {
		this.maKhu = maKhu;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public int getSoLuongToiDa() {
		return soLuongToiDa;
	}
	
	public void setSoLuongToiDa(int soLuongToiDa) {
		this.soLuongToiDa = soLuongToiDa;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "TonKho [maKhu=" + maKhu + ", soLuong=" + soLuong + ", soLuongToiDa=" + soLuongToiDa + "]";
	}	
	//EndRegion
}
