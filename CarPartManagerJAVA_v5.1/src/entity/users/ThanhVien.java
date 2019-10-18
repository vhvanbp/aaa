package entity.users;

public class ThanhVien {
	//Region - properties
	protected String maTV;
	protected String hoTen;
	protected String loaiThanhVien;
	protected String email;
	protected String sdt;
	protected String taiKhoan;
	protected String matKhau;
	//EndRegion
	
	//Region - constructors	
	public ThanhVien(String maTV, String hoTen, String loaiThanhVien, String email, 
					 String sdt, String taiKhoan, String matKhau) {
		this.maTV = maTV;
		this.hoTen = hoTen;
		this.loaiThanhVien = loaiThanhVien;
		this.email = email;
		this.sdt = sdt;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}
	
	public ThanhVien(String maTV, String hoTen) {
		this.maTV = maTV;
		this.hoTen = hoTen;
	}
	
	public ThanhVien(String maTV, String hoTen, String email) {
		this.maTV = maTV;
		this.hoTen = hoTen;
		this.email = email;
	}
		
	public ThanhVien(String maTV) {
		this(maTV,"ho Ten","loai thanh vien","email", "sdt","tai khoan","mat khau");
	}

	public ThanhVien() {
		this("ma TV","ho Ten","loai thanh vien","email", "sdt","tai khoan","mat khau");
	}
	//EndRegion
	
	//Region - setter getter
	public String getMaTV() {
		return maTV;
	}
	public void setMaTV(String maTV) {
		this.maTV = maTV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getLoaiThanhVien() {
		return loaiThanhVien;
	}
	public void setLoaiThanhVien(String loaiThanhVien) {
		this.loaiThanhVien = loaiThanhVien;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "ThanhVien [maTV=" + maTV + ", hoTen=" + hoTen + ", loaiThanhVien=" + loaiThanhVien + ", email=" + email
				+ ", sdt=" + sdt + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + "]";
	}
	//EndRegion 

	//Region - hashCode & equals : maTV, taiKhoan
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTV == null) ? 0 : maTV.hashCode());
		result = prime * result + ((taiKhoan == null) ? 0 : taiKhoan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThanhVien other = (ThanhVien) obj;
		if (maTV == null) {
			if (other.maTV != null)
				return false;
		} else if (!maTV.equals(other.maTV))
			return false;
		if (taiKhoan == null) {
			if (other.taiKhoan != null)
				return false;
		} else if (!taiKhoan.equals(other.taiKhoan))
			return false;
		return true;
	}
	//EndRegion

}
