drop database if exists btlqldancu;
create database btlqldancu;

use btlqldancu;


create table CuDan(
	id int primary key auto_increment,
    hoten nvarchar(45) not null,
    email varchar(40) UNIQUE KEY NOT NULL,
    SDT char(10) UNIQUE KEY NOT NULL,
    quequan nvarchar(35),
    loai enum('RENTER','OWNER','')
);

create table QuanLy(
	id int primary key auto_increment,
    hoten nvarchar(45) NOT NULL,
    soDT char(10) UNIQUE KEY NOT NULL,
    email varchar(45)
);

create table CanHo(
	id varchar(6) primary key,
    sophong int,
    toa varchar(2) not null,
    tang int,
    dientich float,
    gia float,
    trangthai enum('RENTED','SELLED','ON_HOLD','AVAILABLE') DEFAULT 'AVAILABLE',
    chitiet varchar(100)
);

create table HopDong(
	id tinyint auto_increment primary key,
    loai enum('RENT','BUY'),
    kihan float,
    ngayKy date,
    cudan_id int,
    admin_id int,
    canho_id varchar(6) UNIQUE KEY,
    mota nvarchar(300),
    foreign key(cudan_id) references CuDan(id),
    foreign key(admin_id) references QuanLy(id),
    foreign key(canho_id) references CanHo(id)
);


create table nhanvien(
	id int primary key auto_increment,
    hoten nvarchar(45),
    soDT char(10),
    email varchar(45)
);


create table taikhoan(
	id int primary key auto_increment,
    username char(10) not null unique key,
    pass varchar(20),
    `role` enum('NHANVIEN','QUANLY')
);
