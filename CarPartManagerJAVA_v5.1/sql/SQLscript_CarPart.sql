create database CarPartManager_DoAn

use CarPartManager_DoAn

begin -- create tables
	begin -- User tables
create table ThanhVien (	--Thanh Vien
	MaTV varchar(20) primary key,
	HoTen varchar(20) not null,
	LoaiThanhVien nvarchar(60),
	Email nvarchar(100),
	Sdt varchar(20),
	TaiKhoan varchar(20) not null,
	MatKhau varchar(20) not null
)

create table QuanLiVien(	--Quan Li Vien
	MaTV varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES ThanhVien(MaTV)
					 ON DELETE CASCADE,
	MoTaTV varchar(20)
)

create table NhanVien(	--Nhan Vien
 	MaTV varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES ThanhVien(MaTV)
					 ON DELETE CASCADE,
	NgayVaoLam date,
	MucLuong int,
	TienLuong money
)

create table KhachHang(		--Khach Hang
	MaTV varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES ThanhVien(MaTV)
					 ON DELETE CASCADE,
	SoHangMua int, 
	LoaiXeDangDung nvarchar(40)
)

create table NhaCungCap(	--Nha Cung Cap
	MaTV varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES ThanhVien(MaTV)
					 ON DELETE CASCADE,
	CongTy nvarchar(40)
)
	end

	begin -- Car part tables

create table LoaiPhuTung (	--Loai Phu Tung
	MaLoai varchar(20) primary key,
	LoaiPT varchar(20) not null
)

create table PhuTung (	--Phu Tung
	MaPT varchar(20) primary key,
	TenPT varchar(60) not null,
	GiaTien money,
	MaNCC varchar(20) FOREIGN KEY 
							 REFERENCES NhaCungCap(MaTV),
	MaLoaiPTX varchar(20)	FOREIGN KEY 
							 REFERENCES LoaiPhuTung(MaLoai),
	LoaiXe varchar(60),
	Hang varchar(60) 
)
ALTER TABLE PhuTung
ADD SoLuong int
ALTER TABLE PhuTung
ADD MaKhu varchar(20) FOREIGN KEY 
					  REFERENCES TonKho(MaKhu)

create table KhuyenMai (	--Khuyen Mai
	MaKM varchar(20) primary key,
	TenKM nvarchar(60) not null,
	MaPT varchar(20) FOREIGN KEY 
						REFERENCES PhuTung(MaPT),
	MaNCC varchar(20) FOREIGN KEY 
						REFERENCES NhaCungCap(MaTV),
	NgayBatDau date,
	NgayKetThuc date,
	MoTaKM nvarchar(120)
)
ALTER TABLE KhuyenMai
ADD GiaTienKM money

create table TonKho (	--Ton kho / khu
	MaKhu varchar(20) primary key,
	SoLuong int,
	SoLuongToiDa int
)

create table PhieuNhap (	--Phieu Nhap 
	MaPT varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES PhuTung(MaPT)
					 ON DELETE CASCADE,
	SoLuongNhap int,
	NgayNhapHang date
)

create table PhieuXuat (	--Phieu Nhap 
	MaPX varchar(20) primary key,
	MaPT varchar(20) FOREIGN KEY 
					 REFERENCES PhuTung(MaPT)
					 ON DELETE CASCADE,
	SoLuongXuat int,
	NgayXuatHang date,
	ChiPhi money
)

create table PhuTungTon (	--so luong sau khi xuat
							--cho chuc nang chon 1 TonKhoTrongThang 
							-- -> danh sach pt nhap xuat trong thang
	MaPT varchar(20) primary key 
					 FOREIGN KEY 
					 REFERENCES PhuTung(MaPT)
					 ON DELETE CASCADE,
	SoLuongBanDau int,
	SoLuongHienTai int
)
ALTER TABLE PhuTungTon
ADD ThangNhap int
ALTER TABLE PhuTungTon
ADD NamNhap int

-- table TonKhoTrongThang -- co tong chi phi lay tu phieu xuat
create table TonKhoTrongThang (	--so luong tong ton kho 
								-- day tat ca so luong tinh chung trong thang
	MaTon varchar(20) primary key,
	TongSLNhap int,
	TongSLTon int,
	TongSLXuat int,
	TongChiPhiXuat money,
	ThangTon int,
	NamTon int
)
	
	end

	begin -- Others tables
create table TableCount (	--table Count Table record
	TableID varchar(20) primary key,
	Quantity int
)
	end

end

begin -- select tables
	begin -- Simple select
select*from ThanhVien	--Thanh Vien
select*from QuanLiVien	--Quan Li Vien
select*from NhanVien	--Nhan Vien
select*from KhachHang	--Khach Hang
select*from NhaCungCap	--Nha Cung Cap
select*from LoaiPhuTung	--Loai Phu Tung
select*from PhuTung		--Phu Tung
select*from KhuyenMai	--Khuyen Mai
select*from PhieuNhap	--Phieu Nhap
select*from PhieuXuat	--Phieu Xuat
select*from PhuTungTon --Phu Tung Ton
select*from TonKhoTrongThang --Ton Kho Trong Thang 
select*from TableCount	--Others tables
select*from TonKho	--Ton Kho
	end

	begin -- Complex select
SELECT  tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email,	--Thanh Vien && QuanLiVien
		tv.TaiKhoan, tv.MatKhau, qlv.MoTaTV
FROM    QuanLiVien AS qlv INNER JOIN
        ThanhVien AS tv ON qlv.MaTV = tv.MaTV

SELECT tv.MaTV, tv.HoTen, nv.NgayVaoLam,	--Thanh Vien && Nhan Vien
	   nv.MucLuong, nv.TienLuong
FROM   NhanVien AS nv INNER JOIN
       ThanhVien AS tv ON nv.MaTV = tv.MaTV
--	   where nv.MucLuong=1

SELECT tv.MaTV, tv.HoTen, tv.LoaiThanhVien, tv.Email,	--Thanh Vien && Nhan Vien ( 1 person)
	tv.Sdt, tv.TaiKhoan, tv.MatKhau, nv.NgayVaoLam, 
	nv.MucLuong, nv.TienLuong 
	FROM   NhanVien AS nv INNER JOIN 
	ThanhVien AS tv ON nv.MaTV = tv.MaTV 
	where tv.MaTV = 'TV4'

Select COUNT(*) as row_count from ThanhVien		--Count Thanh Vien
Select COUNT(*) as row_count from KhuyenMai		--Count Thanh Vien

Select * from LoaiPhuTung where MaLoai = 'LPT2' --select by id

SELECT HoTen FROM NhaCungCap AS ncc INNER JOIN	 --Thanh Vien && Nha Cung Cap 
              ThanhVien AS tv ON ncc.MaTV = tv.MaTV

SELECT * FROM PhuTung AS pt INNER JOIN		--Phu Tung && Loai Phu Tung
			  LoaiPhuTung AS lpt ON pt.MaLoaiPTX = lpt.MaLoai
			  where pt.MaPT = 'PT1'

SELECT        MaKM, TenKM, MaPT, MaNCC, NgayBatDau, NgayKetThuc, MoTaKM
FROM            KhuyenMai

SELECT        KM.MaKM, KM.TenKM, PT.TenPT, TV.HoTen
FROM            KhuyenMai AS KM INNER JOIN
                PhuTung AS PT ON KM.MaPT = PT.MaPT INNER JOIN
                NhaCungCap AS NCC ON KM.MaNCC = NCC.MaTV AND 
				PT.MaNCC = NCC.MaTV INNER JOIN
                ThanhVien AS TV ON NCC.MaTV = TV.MaTV
where	TV.TaiKhoan = 'EDao1'

SELECT SoLuong, SoLuongToiDa 
FROM TonKho 
where MaKhu = 'K2'
	end

end

begin -- insert tables
  begin -- user tables
	begin --Thanh Vien
	INSERT INTO ThanhVien VALUES ('TV1', 'Nguyen Van A', 'Quan li',		-- Quan li
								'thvi@gmail.com', '0904321111', 
								'ANguyen1', '1234')
	INSERT INTO ThanhVien VALUES ('TV2', 'Tran Van B', 'Quan li', 
								'thvi2@gmail.com',  '0908761111', 
								'BTran1', '5678')
	INSERT INTO ThanhVien VALUES ('TV3', 'Doan Van C', 'Nhan vien',		-- Nhan vien
								'vandoan@gmail.com',  '0905432222', 
								'CDoan2', '2345')
	INSERT INTO ThanhVien VALUES ('TV4', 'Le Thi D', 'Nhan vien', 
								'thile@gmail.com',  '0909882222', 
								'DLe1', '6789')
	INSERT INTO ThanhVien VALUES ('TV5', 'Dao Van E', 'Nha cung cap',	-- Nhan cung cap
								'vandao1@gmail.com',  '0906443333', 
								'', '')
	INSERT INTO ThanhVien VALUES ('TV6', 'Dao Thi F', 'Nha cung cap', 
								'thidao@gmail.com',  '0909894444', 
								'', '')
	INSERT INTO ThanhVien VALUES ('TV7', 'Doan Minh Anh', 'Khach hang',	-- Khach Hang
								'minhAnh1@gmail.com',  '0906774433', 
								'', '')
	INSERT INTO ThanhVien VALUES ('TV8', 'Doan Thi Chi', 'Khach hang', 
								'thiChi2@gmail.com',  '0909695544', 
								'', '')
	INSERT INTO ThanhVien VALUES ('TV_Ad1', 'Do Van An', 'Admin',	-- Admin
								'admi1@gmail.com',  '090333464', 
								'Ad1An', '1012')
	INSERT INTO ThanhVien VALUES ('TV_Ad2', 'Luong Thi Dep', 'Admin', 
								'admi1@gmail.com',  '0904498944', 
								'Ad2Dep', '1314')
	end

	begin --Quan Li Vien
	INSERT INTO QuanLiVien VALUES ('TV1', 'mo ta Nguyen Van A')
	INSERT INTO QuanLiVien VALUES ('TV2', 'mo ta Tran Van B')
	end

	begin --Nhan Vien
	INSERT INTO NhanVien VALUES ('TV3', '2017-12-21', 1, 1000)
	INSERT INTO NhanVien VALUES ('TV4', '2018-1-22', 1, 2000)
	end
	
	begin --Nha Cung Cap
	INSERT INTO NhaCungCap VALUES ('TV5', 'honda HCM')
	INSERT INTO NhaCungCap VALUES ('TV6', 'XeMay Center')
	end
	
	begin --Khach hang
	INSERT INTO KhachHang VALUES ('TV7', 5, 'honda Dream')
	INSERT INTO KhachHang VALUES ('TV8', 8, 'Wave')
	end
  end

  begin -- phu tung table
	begin --Loai Phu Tung
	INSERT INTO LoaiPhuTung VALUES ('LPT1', 'Bugi')
	INSERT INTO LoaiPhuTung VALUES ('LPT2', 'Yen xe')
	end

	begin --Phu Tung
	INSERT INTO PhuTung VALUES ('PT1', 'Yen Exciter 100', '150',
								'TV5', 'LPT2', 'Dream', 'Honda', 16, 'K1')
	INSERT INTO PhuTung VALUES ('PT2', 'ExciterB 200', '170',		
								'TV6', 'LPT1', 'NVX', 'Yamaha', 33, 'K2')
	end

	begin --Khuyen Mai
	INSERT INTO KhuyenMai VALUES ('KM1', N'Giảm giá Yen Exciter', 'PT1', 
								'TV5', '2017-10-02', '2018-02-12',
								N'Giảm giá Yên xe Exciter xuống 50%', 75000)
	INSERT INTO KhuyenMai VALUES ('KM2', N'Giảm giá ExciterB 200', 'PT2', 
								'TV6', '2017-05-02', '2017-07-12',
								N'Giảm giá ExciterB 200 xuống 40%', 68000)
	INSERT INTO KhuyenMai VALUES ('KM4', N'Giảm ExciterB lần 2', 'PT2', 
								'TV6', '2017-10-05', '2018-12-10',
								 N'Giảm giá ExciterB 200 xuống 30%', 51000)
	end
  end
   
	begin --Table Count
	INSERT INTO TableCount VALUES ('ThanhVien', 8)
	INSERT INTO TableCount VALUES ('LoaiPhuTung', 3)
	INSERT INTO TableCount VALUES ('PhuTung', 4)
	INSERT INTO TableCount VALUES ('KhuyenMai', 4)
	INSERT INTO TableCount VALUES ('TonKho', 4)
	INSERT INTO TableCount VALUES ('PhieuXuat', 0)
	INSERT INTO TableCount VALUES ('TonKhoTrongThang', 3)
	end

  begin -- kho table
	begin --Ton Kho
	INSERT INTO TonKho VALUES ('K1', 16, 100)
	INSERT INTO TonKho VALUES ('K2', 33, 100)
	INSERT INTO TonKho VALUES ('K3', 18, 100)
	INSERT INTO TonKho VALUES ('K4', 22, 100)
	end

	begin --Phieu nhap
	INSERT INTO PhieuNhap VALUES ('PT1', 16, '2018-4-4')
	INSERT INTO PhieuNhap VALUES ('PT2', 33, '2018-4-24')
	INSERT INTO PhieuNhap VALUES ('PT3', 18, '2018-5-4')
	INSERT INTO PhieuNhap VALUES ('PT4', 22, '2018-6-4')
	end

	
	begin --Phieu xuat

	end

	begin --PhuTungTon
	INSERT INTO PhuTungTon VALUES ('PT1', 16, 16, 4, 2018)
	INSERT INTO PhuTungTon VALUES ('PT2', 33, 33, 4, 2018)
	INSERT INTO PhuTungTon VALUES ('PT3', 18, 18, 5, 2018)
	INSERT INTO PhuTungTon VALUES ('PT4', 22, 22, 6, 2018)
	end
		
	begin --TonKhoTrongThang
	INSERT INTO TonKhoTrongThang VALUES ('TKTT1', 49, 49, 0, 0, 4, 2018)
	INSERT INTO TonKhoTrongThang VALUES ('TKTT2', 18, 18, 0, 0, 5, 2018)
	INSERT INTO TonKhoTrongThang VALUES ('TKTT3', 22, 22, 0, 0, 6, 2018)
	end

  end
end

begin -- update tables
--Thanh Vien
--update ThanhVien 
--set HoTen = 'Nguyen Van A', LoaiThanhVien = 'Quan li', Email='thvi1@gmail.com', 
--	TaiKhoan='ANguyen1', MatKhau='1234' 
--where MaTV = 'TV1'
--Quan Li Vien

--Nhan Vien
--update PhuTung 
--set SoLuong = 10
--where MaPT = 'PT4'
--Khach Hang

--Nha Cung Cap

--Loai Phu Tung

--Phu Tung/ Ton kho
--update TonKho 
--set SoLuong = 33
--where MaKhu = 'k2'
--update PhuTung 
--set MaKhu = 'K6'
--where MaPT = 'PT6'
--update TableCount 
--set Quantity = 6
--where TableID = 'TonKho'
--update KhuyenMai 
--set GiaTienKM = 121000
--where MaKM = 'KM7'
end

begin -- delete tables
--delete from TonKho where MaKhu = 'D'
end

begin -- drop tables
--Thanh Vien
--drop table ThanhVien
--Quan Li Vien
--drop table QuanLiVien
--Nhan Vien
--drop table NhanVien
--Khach Hang
--drop table KhachHang
--Nha Cung Cap
--drop table NhaCungCap
--Loai Phu Tung
--drop table LoaiPhuTung
--Phu Tung
--drop table PhuTung
--Khuyen Mai
--drop table KhuyenMai
--PHieu Nhap
--drop table PhieuNhap
--PhieuXuat
--drop table PhieuXuat
--Table Count
--drop table TableCount
--Kho Chua
--drop table KhoChua
end