use btlqldancu;

insert into cudan(hoten,SDT,email,quequan,loai) VALUES
(N'Phạm Gia Bảo','0987234801','giabao88@gmail.com',N'Quảng Ninh','OWNER'),
(N'Trần Kiều Trang','0321234502','trangkieu4502@gmail.com',N'Bắc Ninh',''),
(N'Phan Gia Huy','0987199202','phangiahuy001@gmail.com',N'Hưng Yên','RENTER');
/*
(N'Đào Tuấn Hải','0977222104','hait3204@gmail.com',N'Thái Bình',''),
(N'Phạm Minh Anh','0321724809','manhmanh01@gmail.com',N'Hải Dương',''),
(N'Nguyễn Thái Uy','098quantri7234903','thaiuynguyen03@gmail.com',N'Vĩnh Phúc',''),
(N'Kiều Minh Tuấn','0987232842','kieutuan8402@gmail.com',N'Thanh Hóa',''),
(N'Phạm Thị Huyền','0978588123','huyenphamthi3200@gmail.com',N'Nghệ An',''),
(N'Nguyễn Văn Bách','0323849283','bachkhoatoanthu1@gmail.com',N'Hà Nội',''),
(N'Phạm Kiên Cường','0987234463','cuongkien2999@gmail.com',N'Hà Tĩnh',''),
(N'Phạm Thị Mỹ Duyên','0926912160','myduyen092@gmail.com',N'Nghệ An',''),
(N'Trần Thị Thanh Hằng','0964884398','nguyennhang2003@gmail.com',N'Bắc Ninh',''),
(N'Nguyễn Thị Hà My','0375514751','my12022002@gmail.com',N'Hưng Yên',''),
(N'Trần Thị Thu Hiền','0971926651','tranhien20@gmail.com',N'Thái Bình',''),
(N'Nguyễn Vân Anh','0336101685','nguyenvananh0909@gmail.com',N'Nam Định',''),
(N'Tạ Trần Phương Linh','0389770227','talinh1132@gmail.com',N'Nghệ An',''),
(N'Nguyễn Xuân Đạt','0386580682','xuandatpro3@gmail.com',N'Thanh Hóa',''),
(N'Phạm Thị Hương','0325317131','phamthihuong001@gmail.com',N'Bắc Giang',''),
(N'Nguyễn Thu Hằng','0384853715','hangthu567@gmail.com',N'Hà Nội',''),
(N'Nguyễn Hồng Nhung','0389992506','nguyennhung2003@gmail.com',N'Phú Thọ','');
*/


insert into quanly(hoten,soDT,email) values
(N'Nguyễn Tiến Hải','0375514751','hainguyen123@gmail.com'),
(N'Đinh Viết Thắng','0321466722','thangdv099@gmail.com');

INSERT INTO nhanvien(hoten,soDT,email) VALUES
(N'Trần Danh Trung','0987234801','trungnguyen@gmail.com');
insert into canho(id,sophong,toa,tang,dientich,gia,trangthai) values
('B704',704,'B',7,25,120,'RENTED'),
('A102','102','A',1,45,220,'AVAILABLE'),
('C-402','402','C',4,55,300,default);

INSERT INTO hopdong(loai,kihan,ngayKy,mota,cudan_id,admin_id,canho_id) VALUES
('RENT',2,'2018-10-10',N'hợp đồng đầu tiên',3,1,'B704'),
('BUY',0,'2016-05-12',N'mua dài hạn',1,2,'A102');


insert into taikhoan(username,pass,`role`) VALUES
('0987234801','123qwe','NHANVIEN'),
('0375514751','123@abc','QUANLY'),
('0321466722','123@abc','QUANLY');