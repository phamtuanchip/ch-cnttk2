--------------------------------------------------------
--  File created - Tuesday-December-29-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type DICH_VU_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."DICH_VU_OBJTYP" AS OBJECT (
  Ma_DV					VARCHAR2(20),
  Ten_DV				VARCHAR2(200),
  Loai_DV			REF LOAI_DICH_VU_objtyp
);

/
--------------------------------------------------------
--  DDL for Type GIAO_DICH_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."GIAO_DICH_OBJTYP" AS OBJECT (
  Ma_giao_dich 			VARCHAR2(20),
  Ngay_giao_dich		DATE,
  Dich_vu				REF DICH_VU_objtyp,
  Nhan_vien				REF NHAN_VIEN_objtyp,
  Khach_hang			REF	KHACH_HANG_objtyp
);

/
--------------------------------------------------------
--  DDL for Type HOA_DON_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."HOA_DON_OBJTYP" AS OBJECT (
  Ma_hoa_don			VARCHAR2(20),
  Loai_hoa_don			VARCHAR2(10),
  So_tien				NUMBER,
  Trang_thai			VARCHAR2(10),
  Ngay_thue				DATE,
  Ngay_tra				DATE,
  Tien_dat_coc			NUMBER,
  Tien_con_lai			NUMBER,
  Giao_dich				REF GIAO_DICH_objtyp
);

/
--------------------------------------------------------
--  DDL for Type KHACH_HANG_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."KHACH_HANG_OBJTYP" AS OBJECT (
  Ma_khach_hang			VARCHAR2(20),
  Ten_khach_hang		VARCHAR2(100),
  Ngay_sinh				DATE,
  Gioi_tinh				VARCHAR2(5),
  Dia_chi				VARCHAR2(200),
  SDT					VARCHAR2(20)
);

/
--------------------------------------------------------
--  DDL for Type LOAI_DICH_VU_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."LOAI_DICH_VU_OBJTYP" AS OBJECT (
  Ma_loai_DV 			VARCHAR2(20),
  Ten_loai_DV			VARCHAR2(200),
  Phong_ban			REF PHONG_BAN_objtyp
);

/
--------------------------------------------------------
--  DDL for Type NHAN_VIEN_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."NHAN_VIEN_OBJTYP" AS OBJECT (
  Ma_nhan_vien          VARCHAR2(20),
  Ten_nhan_vien	        VARCHAR2(200),
  Ngay_sinh      		DATE,
  Gioi_tinh			    VARCHAR2(3),
  SDT					VARCHAR2(20),
  Dia_chi				VARCHAR2(100),
  Ngay_bat_dau_lam		DATE,
  Phong_ban			REF PHONG_BAN_objtyp
);

/
--------------------------------------------------------
--  DDL for Type PHONG_BAN_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."PHONG_BAN_OBJTYP" AS OBJECT (
  Ma_phong_ban 			VARCHAR2(20),
  Ten_phong_ban			VARCHAR2(200),
  Ma_truong_phong		VARCHAR2(20)
);

/
--------------------------------------------------------
--  DDL for Type SAN_BAY_OBJTYP
--------------------------------------------------------

  CREATE OR REPLACE TYPE "HR"."SAN_BAY_OBJTYP" AS OBJECT (
  Ma_san_bay 			VARCHAR2(20),
  Ten_san_bay			VARCHAR2(200),
  Dia_chi				VARCHAR2(200),
  SDT					VARCHAR2(20)
);

/
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074598$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074598$ as object( "MA_SAN_BAY" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074603$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074603$ as object( "MA_PHONG_BAN" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074618$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074618$ as object( "MA_NHAN_VIEN" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074620$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074620$ as object( "SYS_NC00011$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074625$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074625$ as object( "MA_LOAI_DV" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074627$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074627$ as object( "SYS_NC00006$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074632$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074632$ as object( "MA_DV" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074634$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074634$ as object( "SYS_NC00006$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074680$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074680$ as object( "MA_KHACH_HANG" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074684$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074684$ as object( "MA_GIAO_DICH" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074686$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074686$ as object( "SYS_NC00008$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074688$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074688$ as object( "SYS_NC00009$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074690$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074690$ as object( "SYS_NC00010$" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074694$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074694$ as object( "MA_HOA_DON" VARCHAR2(20 BYTE))
-- Unable to render TYPE DDL for object HR.SYS_YOID0000074696$ with DBMS_METADATA attempting internal generator.
CREATE TYPE      SYS_YOID0000074696$ as object( "SYS_NC00012$" VARCHAR2(20 BYTE))
--------------------------------------------------------
--  DDL for Sequence DEPARTMENTS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HR"."DEPARTMENTS_SEQ"  MINVALUE 1 MAXVALUE 9990 INCREMENT BY 10 START WITH 280 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EMPLOYEES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HR"."EMPLOYEES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 207 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LOCATIONS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HR"."LOCATIONS_SEQ"  MINVALUE 1 MAXVALUE 9900 INCREMENT BY 100 START WITH 3300 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table DICH_VU_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."DICH_VU_OBJTAB" OF "HR"."DICH_VU_OBJTYP" 
   ( PRIMARY KEY ("MA_DV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	SCOPE FOR ("LOAI_DV") IS "HR"."LOAI_DICH_VU_OBJTAB" 
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GIAO_DICH_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."GIAO_DICH_OBJTAB" OF "HR"."GIAO_DICH_OBJTYP" 
   ( PRIMARY KEY ("MA_GIAO_DICH")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	SCOPE FOR ("DICH_VU") IS "HR"."DICH_VU_OBJTAB" , 
	SCOPE FOR ("NHAN_VIEN") IS "HR"."NHAN_VIEN_OBJTAB" , 
	SCOPE FOR ("KHACH_HANG") IS "HR"."KHACH_HANG_OBJTAB" 
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table HOA_DON_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."HOA_DON_OBJTAB" OF "HR"."HOA_DON_OBJTYP" 
   ( PRIMARY KEY ("MA_HOA_DON")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	SCOPE FOR ("GIAO_DICH") IS "HR"."GIAO_DICH_OBJTAB" 
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KHACH_HANG_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."KHACH_HANG_OBJTAB" OF "HR"."KHACH_HANG_OBJTYP" 
   ( PRIMARY KEY ("MA_KHACH_HANG")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LOAI_DICH_VU_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."LOAI_DICH_VU_OBJTAB" OF "HR"."LOAI_DICH_VU_OBJTYP" 
   ( PRIMARY KEY ("MA_LOAI_DV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	SCOPE FOR ("PHONG_BAN") IS "HR"."PHONG_BAN_OBJTAB" 
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table NHAN_VIEN_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."NHAN_VIEN_OBJTAB" OF "HR"."NHAN_VIEN_OBJTYP" 
   ( PRIMARY KEY ("MA_NHAN_VIEN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	SCOPE FOR ("PHONG_BAN") IS "HR"."PHONG_BAN_OBJTAB" 
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PHONG_BAN_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."PHONG_BAN_OBJTAB" OF "HR"."PHONG_BAN_OBJTYP" 
   ( PRIMARY KEY ("MA_PHONG_BAN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SAN_BAY_OBJTAB
--------------------------------------------------------

  CREATE TABLE "HR"."SAN_BAY_OBJTAB" OF "HR"."SAN_BAY_OBJTYP" 
   ( PRIMARY KEY ("MA_SAN_BAY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) OBJECT IDENTIFIER IS PRIMARY KEY 
   PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into HR.DICH_VU_OBJTAB
SET DEFINE OFF;
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('11','Dich vu lam thu tuc hang khong','HR.LOAI_DICH_VU_OBJTYP(''1'',''Dich vu hang khong trong nha ga'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('12','Dich vu ban ve, dat cho, giu cho bo sung','HR.LOAI_DICH_VU_OBJTYP(''1'',''Dich vu hang khong trong nha ga'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('13','Dich vu van chuyen hanh khach, hanh ly','HR.LOAI_DICH_VU_OBJTYP(''1'',''Dich vu hang khong trong nha ga'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('14','Dich vu tra hanh ly','HR.LOAI_DICH_VU_OBJTYP(''1'',''Dich vu hang khong trong nha ga'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('15','Dich vu hanh khach qua canh','HR.LOAI_DICH_VU_OBJTYP(''1'',''Dich vu hang khong trong nha ga'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('21','Dich vu khong luu','HR.LOAI_DICH_VU_OBJTYP(''2'',''Dich vu dam bao hoat dong bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('22','Dich vu thong tin dan duong giam sat','HR.LOAI_DICH_VU_OBJTYP(''2'',''Dich vu dam bao hoat dong bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('23','Dich vu khi tuong','HR.LOAI_DICH_VU_OBJTYP(''2'',''Dich vu dam bao hoat dong bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('24','Dich vu tim kiem cuu nan','HR.LOAI_DICH_VU_OBJTYP(''2'',''Dich vu dam bao hoat dong bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('32','Dich vu bao ve tau bay, nha ga, khu bay','HR.LOAI_DICH_VU_OBJTYP(''3'',''Dich vu dam bao an ninh'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('31','Dich vu kiem tra hanh khach, hanh ly, hang hoa','HR.LOAI_DICH_VU_OBJTYP(''3'',''Dich vu dam bao an ninh'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('33','Dich vu ap tai hanh ly, hang hoa','HR.LOAI_DICH_VU_OBJTYP(''3'',''Dich vu dam bao an ninh'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('34','Dich vu trong giu hanh khach vi pham xuat nhap canh','HR.LOAI_DICH_VU_OBJTYP(''3'',''Dich vu dam bao an ninh'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('41','Dich vu phuc vu hanh khach','HR.LOAI_DICH_VU_OBJTYP(''4'',''Dich vu phuc vu mat dat'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('42','Dich vu ho tro','HR.LOAI_DICH_VU_OBJTYP(''4'',''Dich vu phuc vu mat dat'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('43','Dich vu danh tin hieu may bay','HR.LOAI_DICH_VU_OBJTYP(''4'',''Dich vu phuc vu mat dat'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('44','Dich vu hang hoa','HR.LOAI_DICH_VU_OBJTYP(''4'',''Dich vu phuc vu mat dat'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('51','Dich vu cat co','HR.LOAI_DICH_VU_OBJTYP(''5'',''Dich vu khai thac khu bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('52','Dich vu nao vet cong ranh','HR.LOAI_DICH_VU_OBJTYP(''5'',''Dich vu khai thac khu bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('53','Dich vu lam sach duong bay','HR.LOAI_DICH_VU_OBJTYP(''5'',''Dich vu khai thac khu bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('54','Dich vu tay sach cao su, son ke, ve tin hieu','HR.LOAI_DICH_VU_OBJTYP(''5'',''Dich vu khai thac khu bay'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('61','Dich vu van chuyen hanh khach bang taixi','HR.LOAI_DICH_VU_OBJTYP(''6'',''Dich vu van chuyen hanh khach bang xe o to'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('71','Dich vu an uong','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('72','Dich vu ban le','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('73','Dich vu sach bao','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('74','Dich vu ngan hang','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('75','Dich vu y te','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('76','Dich vu kinh doanh hang mien thue','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
Insert into HR.DICH_VU_OBJTAB (MA_DV,TEN_DV,LOAI_DV) values ('77','Dich vu khach san','HR.LOAI_DICH_VU_OBJTYP(''7'',''Dich vu phi hang khong'',''oracle.sql.REF@6a23747b'')');
REM INSERTING into HR.GIAO_DICH_OBJTAB
SET DEFINE OFF;
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('1',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''11'',''Dich vu lam thu tuc hang khong'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''1'',''Pham Xuan Nam Chinh'',''1995-01-24 00:00:00.0'',''NAM'',''0988092745'',''45 Le Loi - Ha Noi'',''2015-01-24 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''1'',''Wayne Rooney'',''1985-07-22 00:00:00.0'',''NAM'',''Manchester'',''09152459'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('2',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''12'',''Dich vu ban ve, dat cho, giu cho bo sung'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''9'',''Tran Dinh Nguyen'',''1995-02-16 00:00:00.0'',''NAM'',''098434532'',''45 Dong Phu - Ha Noi'',''2015-01-24 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''1'',''Wayne Rooney'',''1985-07-22 00:00:00.0'',''NAM'',''Manchester'',''09152459'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('3',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''13'',''Dich vu van chuyen hanh khach, hanh ly'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''10'',''Hoang Minh Nghia'',''1995-06-30 00:00:00.0'',''NAM'',''0983456782'',''28 Tran Khac Chan - Ha Noi'',''2015-01-24 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''1'',''Wayne Rooney'',''1985-07-22 00:00:00.0'',''NAM'',''Manchester'',''09152459'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('4',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''21'',''Dich vu khong luu'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''2'',''Nguyen Xuan Hoa'',''1995-04-30 00:00:00.0'',''NAM'',''0912395677'',''28 Phung Xuan - Bo Trach'',''2015-01-24 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''2'',''Nguyen Van Hoa'',''1995-06-21 00:00:00.0'',''NAM'',''19 Cau Giay'',''09134523432'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('5',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''22'',''Dich vu thong tin dan duong giam sat'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''4'',''Pham Hong Duy'',''1994-12-30 00:00:00.0'',''NAM'',''0925342245'',''24 Pho vien - Ha Noi'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''2'',''Nguyen Van Hoa'',''1995-06-21 00:00:00.0'',''NAM'',''19 Cau Giay'',''09134523432'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('6',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''23'',''Dich vu khi tuong'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''4'',''Pham Hong Duy'',''1994-12-30 00:00:00.0'',''NAM'',''0925342245'',''24 Pho vien - Ha Noi'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''2'',''Nguyen Van Hoa'',''1995-06-21 00:00:00.0'',''NAM'',''19 Cau Giay'',''09134523432'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('7',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''31'',''Dich vu kiem tra hanh khach, hanh ly, hang hoa'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''5'',''Tran Trung Chuyen'',''1976-06-20 00:00:00.0'',''NAM'',''0915356245'',''2 Hoang Quoc Viet - Ha Noi'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''3'',''Le Tran Nhat Tan'',''1995-04-14 00:00:00.0'',''NAM'',''19 Hoang Hoa Tham'',''09132692300'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('8',to_date('30-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''32'',''Dich vu bao ve tau bay, nha ga, khu bay'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''5'',''Tran Trung Chuyen'',''1976-06-20 00:00:00.0'',''NAM'',''0915356245'',''2 Hoang Quoc Viet - Ha Noi'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''4'',''Nguyen Manh Tuan'',''1995-12-11 00:00:00.0'',''NAM'',''183 Pho Vong'',''09132692244'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('9',to_date('29-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''33'',''Dich vu ap tai hanh ly, hang hoa'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''5'',''Tran Trung Chuyen'',''1976-06-20 00:00:00.0'',''NAM'',''0915356245'',''2 Hoang Quoc Viet - Ha Noi'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''5'',''Dang Trung Hieu'',''1995-12-23 00:00:00.0'',''NAM'',''129 Trieu Khuc'',''091673456462'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('10',to_date('29-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''51'',''Dich vu cat co'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''7'',''Do Thi Hoai Thu'',''1994-07-22 00:00:00.0'',''NU'',''09159872422'',''918 Tan Tay Do - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''6'',''Pham Ngoc Tuan'',''1995-12-20 00:00:00.0'',''NAM'',''129 Trieu Khuc'',''091673451112'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('11',to_date('28-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''52'',''Dich vu nao vet cong ranh'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''7'',''Do Thi Hoai Thu'',''1994-07-22 00:00:00.0'',''NU'',''09159872422'',''918 Tan Tay Do - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''6'',''Pham Ngoc Tuan'',''1995-12-20 00:00:00.0'',''NAM'',''129 Trieu Khuc'',''091673451112'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('12',to_date('28-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''53'',''Dich vu lam sach duong bay'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''7'',''Do Thi Hoai Thu'',''1994-07-22 00:00:00.0'',''NU'',''09159872422'',''918 Tan Tay Do - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''6'',''Pham Ngoc Tuan'',''1995-12-20 00:00:00.0'',''NAM'',''129 Trieu Khuc'',''091673451112'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('13',to_date('28-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''54'',''Dich vu tay sach cao su, son ke, ve tin hieu'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''7'',''Do Thi Hoai Thu'',''1994-07-22 00:00:00.0'',''NU'',''09159872422'',''918 Tan Tay Do - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''6'',''Pham Ngoc Tuan'',''1995-12-20 00:00:00.0'',''NAM'',''129 Trieu Khuc'',''091673451112'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('14',to_date('20-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''71'',''Dich vu an uong'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''8'',''Kim Thuy Tien'',''1994-07-22 00:00:00.0'',''NU'',''0915322459'',''125 Pham Van Dong - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''7'',''Dinh Ngoc Trinh'',''1990-10-20 00:00:00.0'',''NU'',''129 Trieu Khuc'',''0980734522'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('15',to_date('20-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''72'',''Dich vu ban le'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''8'',''Kim Thuy Tien'',''1994-07-22 00:00:00.0'',''NU'',''0915322459'',''125 Pham Van Dong - Ha Noi'',''2012-03-04 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''8'',''Hoang Kieu Trinh'',''1987-01-20 00:00:00.0'',''NU'',''23 Le Duan'',''0984595665'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('17',to_date('20-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''73'',''Dich vu sach bao'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''11'',''Hoang Thuy Linh'',''1987-07-30 00:00:00.0'',''NU'',''0983456782'',''23 Pho Vien - Ha Noi'',''2000-01-24 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''9'',''Tran Kim Ngan'',''1998-01-25 00:00:00.0'',''NU'',''233 Le Duan'',''0984591234'')');
Insert into HR.GIAO_DICH_OBJTAB (MA_GIAO_DICH,NGAY_GIAO_DICH,DICH_VU,NHAN_VIEN,KHACH_HANG) values ('18',to_date('20-DEC-15','DD-MON-RR'),'HR.DICH_VU_OBJTYP(''74'',''Dich vu ngan hang'',''oracle.sql.REF@df4cb36b'')','HR.NHAN_VIEN_OBJTYP(''12'',''May J Lee'',''1990-10-11 00:00:00.0'',''NU'',''0983456782'',''235 Pham Van Dong'',''2010-02-10 00:00:00.0'',''oracle.sql.REF@6a23747b'')','HR.KHACH_HANG_OBJTYP(''10'',''Ung Hoang Phuc'',''1980-03-25 00:00:00.0'',''NAM'',''233 Pho Vien'',''0984234223'')');
REM INSERTING into HR.HOA_DON_OBJTAB
SET DEFINE OFF;
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('1','CHI',10000,null,null,null,null,null,'HR.GIAO_DICH_OBJTYP(''10'',''2015-12-29 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('2','CHI',20000,null,null,null,null,null,'HR.GIAO_DICH_OBJTYP(''11'',''2015-12-28 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('3','CHI',50000,null,null,null,null,null,'HR.GIAO_DICH_OBJTYP(''12'',''2015-12-28 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('4','THU',50000,'DU',to_date('15-JAN-16','DD-MON-RR'),to_date('15-APR-16','DD-MON-RR'),20000,30000,'HR.GIAO_DICH_OBJTYP(''14'',''2015-12-20 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('5','THU',50000,'DU',to_date('15-JAN-16','DD-MON-RR'),to_date('15-APR-16','DD-MON-RR'),20000,30000,'HR.GIAO_DICH_OBJTYP(''15'',''2015-12-20 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('6','THU',20000,'CHUA',to_date('15-JAN-16','DD-MON-RR'),to_date('15-APR-16','DD-MON-RR'),10000,10000,'HR.GIAO_DICH_OBJTYP(''17'',''2015-12-20 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
Insert into HR.HOA_DON_OBJTAB (MA_HOA_DON,LOAI_HOA_DON,SO_TIEN,TRANG_THAI,NGAY_THUE,NGAY_TRA,TIEN_DAT_COC,TIEN_CON_LAI,GIAO_DICH) values ('7','THU',100000,'DU',to_date('15-JAN-16','DD-MON-RR'),to_date('15-APR-16','DD-MON-RR'),30000,70000,'HR.GIAO_DICH_OBJTYP(''17'',''2015-12-20 00:00:00.0'',''oracle.sql.REF@7e970a01'',''oracle.sql.REF@19796800'',''oracle.sql.REF@5b85525f'')');
REM INSERTING into HR.KHACH_HANG_OBJTAB
SET DEFINE OFF;
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('1','Wayne Rooney',to_date('22-JUL-85','DD-MON-RR'),'NAM','Manchester','09152459');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('2','Nguyen Van Hoa',to_date('21-JUN-95','DD-MON-RR'),'NAM','19 Cau Giay','09134523432');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('3','Le Tran Nhat Tan',to_date('14-APR-95','DD-MON-RR'),'NAM','19 Hoang Hoa Tham','09132692300');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('4','Nguyen Manh Tuan',to_date('11-DEC-95','DD-MON-RR'),'NAM','183 Pho Vong','09132692244');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('5','Dang Trung Hieu',to_date('23-DEC-95','DD-MON-RR'),'NAM','129 Trieu Khuc','091673456462');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('6','Pham Ngoc Tuan',to_date('20-DEC-95','DD-MON-RR'),'NAM','129 Trieu Khuc','091673451112');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('7','Dinh Ngoc Trinh',to_date('20-OCT-90','DD-MON-RR'),'NU','129 Trieu Khuc','0980734522');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('8','Hoang Kieu Trinh',to_date('20-JAN-87','DD-MON-RR'),'NU','23 Le Duan','0984595665');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('9','Tran Kim Ngan',to_date('25-JAN-98','DD-MON-RR'),'NU','233 Le Duan','0984591234');
Insert into HR.KHACH_HANG_OBJTAB (MA_KHACH_HANG,TEN_KHACH_HANG,NGAY_SINH,GIOI_TINH,DIA_CHI,SDT) values ('10','Ung Hoang Phuc',to_date('25-MAR-80','DD-MON-RR'),'NAM','233 Pho Vien','0984234223');
REM INSERTING into HR.LOAI_DICH_VU_OBJTAB
SET DEFINE OFF;
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('3','Dich vu dam bao an ninh','HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('1','Dich vu hang khong trong nha ga','HR.PHONG_BAN_OBJTYP(''1'',''Phong 1'',''1'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('2','Dich vu dam bao hoat dong bay','HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('4','Dich vu phuc vu mat dat','HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('5','Dich vu khai thac khu bay','HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('6','Dich vu van chuyen hanh khach bang xe o to','HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
Insert into HR.LOAI_DICH_VU_OBJTAB (MA_LOAI_DV,TEN_LOAI_DV,PHONG_BAN) values ('7','Dich vu phi hang khong','HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
REM INSERTING into HR.NHAN_VIEN_OBJTAB
SET DEFINE OFF;
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('1','Pham Xuan Nam Chinh',to_date('24-JAN-95','DD-MON-RR'),'NAM','0988092745','45 Le Loi - Ha Noi',to_date('24-JAN-15','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''1'',''Phong 1'',''1'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('2','Nguyen Xuan Hoa',to_date('30-APR-95','DD-MON-RR'),'NAM','0912395677','28 Phung Xuan - Bo Trach',to_date('24-JAN-15','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('3','Nguyen Manh Hung',to_date('11-AUG-94','DD-MON-RR'),'NAM','0988132745','24 Kim Lan - Ha Noi',to_date('24-JAN-15','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''1'',''Phong 1'',''1'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('4','Pham Hong Duy',to_date('30-DEC-94','DD-MON-RR'),'NAM','0925342245','24 Pho vien - Ha Noi',to_date('10-FEB-10','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('5','Tran Trung Chuyen',to_date('20-JUN-76','DD-MON-RR'),'NAM','0915356245','2 Hoang Quoc Viet - Ha Noi',to_date('10-FEB-10','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('6','Hoang Hong Trang',to_date('22-APR-96','DD-MON-RR'),'NU','09159872422','123 Tran Cung - Ha Noi',to_date('10-FEB-10','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''2'',''Phong 2'',''2'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('7','Do Thi Hoai Thu',to_date('22-JUL-94','DD-MON-RR'),'NU','09159872422','918 Tan Tay Do - Ha Noi',to_date('04-MAR-12','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('8','Kim Thuy Tien',to_date('22-JUL-94','DD-MON-RR'),'NU','0915322459','125 Pham Van Dong - Ha Noi',to_date('04-MAR-12','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('9','Tran Dinh Nguyen',to_date('16-FEB-95','DD-MON-RR'),'NAM','098434532','45 Dong Phu - Ha Noi',to_date('24-JAN-15','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''1'',''Phong 1'',''1'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('10','Hoang Minh Nghia',to_date('30-JUN-95','DD-MON-RR'),'NAM','0983456782','28 Tran Khac Chan - Ha Noi',to_date('24-JAN-15','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''1'',''Phong 1'',''1'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('11','Hoang Thuy Linh',to_date('30-JUL-87','DD-MON-RR'),'NU','0983456782','23 Pho Vien - Ha Noi',to_date('24-JAN-00','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
Insert into HR.NHAN_VIEN_OBJTAB (MA_NHAN_VIEN,TEN_NHAN_VIEN,NGAY_SINH,GIOI_TINH,SDT,DIA_CHI,NGAY_BAT_DAU_LAM,PHONG_BAN) values ('12','May J Lee',to_date('11-OCT-90','DD-MON-RR'),'NU','0983456782','235 Pham Van Dong',to_date('10-FEB-10','DD-MON-RR'),'HR.PHONG_BAN_OBJTYP(''3'',''Phong 3'',''3'')');
REM INSERTING into HR.PHONG_BAN_OBJTAB
SET DEFINE OFF;
Insert into HR.PHONG_BAN_OBJTAB (MA_PHONG_BAN,TEN_PHONG_BAN,MA_TRUONG_PHONG) values ('1','Phong 1','1');
Insert into HR.PHONG_BAN_OBJTAB (MA_PHONG_BAN,TEN_PHONG_BAN,MA_TRUONG_PHONG) values ('2','Phong 2','2');
Insert into HR.PHONG_BAN_OBJTAB (MA_PHONG_BAN,TEN_PHONG_BAN,MA_TRUONG_PHONG) values ('3','Phong 3','3');
REM INSERTING into HR.SAN_BAY_OBJTAB
SET DEFINE OFF;
Insert into HR.SAN_BAY_OBJTAB (MA_SAN_BAY,TEN_SAN_BAY,DIA_CHI,SDT) values ('1','NOI BAI','PHU MINH, SOC SON, HA NOI, VIET NAM','84 4 3886 5047');
--------------------------------------------------------
--  DDL for Index SYS_C0011058
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011058" ON "HR"."SAN_BAY_OBJTAB" ("MA_SAN_BAY") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011059
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011059" ON "HR"."PHONG_BAN_OBJTAB" ("MA_PHONG_BAN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011062
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011062" ON "HR"."NHAN_VIEN_OBJTAB" ("MA_NHAN_VIEN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011064
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011064" ON "HR"."LOAI_DICH_VU_OBJTAB" ("MA_LOAI_DV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011066
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011066" ON "HR"."DICH_VU_OBJTAB" ("MA_DV") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011127
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011127" ON "HR"."KHACH_HANG_OBJTAB" ("MA_KHACH_HANG") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011128
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011128" ON "HR"."GIAO_DICH_OBJTAB" ("MA_GIAO_DICH") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011132
--------------------------------------------------------

  CREATE UNIQUE INDEX "HR"."SYS_C0011132" ON "HR"."HOA_DON_OBJTAB" ("MA_HOA_DON") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table DICH_VU_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."DICH_VU_OBJTAB" ADD PRIMARY KEY ("MA_DV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GIAO_DICH_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."GIAO_DICH_OBJTAB" ADD PRIMARY KEY ("MA_GIAO_DICH")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table HOA_DON_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."HOA_DON_OBJTAB" ADD PRIMARY KEY ("MA_HOA_DON")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table KHACH_HANG_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."KHACH_HANG_OBJTAB" ADD PRIMARY KEY ("MA_KHACH_HANG")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LOAI_DICH_VU_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."LOAI_DICH_VU_OBJTAB" ADD PRIMARY KEY ("MA_LOAI_DV")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table NHAN_VIEN_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."NHAN_VIEN_OBJTAB" ADD PRIMARY KEY ("MA_NHAN_VIEN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PHONG_BAN_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."PHONG_BAN_OBJTAB" ADD PRIMARY KEY ("MA_PHONG_BAN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SAN_BAY_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."SAN_BAY_OBJTAB" ADD PRIMARY KEY ("MA_SAN_BAY")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DICH_VU_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."DICH_VU_OBJTAB" ADD FOREIGN KEY ("LOAI_DV")
	  REFERENCES "HR"."LOAI_DICH_VU_OBJTAB"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GIAO_DICH_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."GIAO_DICH_OBJTAB" ADD FOREIGN KEY ("DICH_VU")
	  REFERENCES "HR"."DICH_VU_OBJTAB"  ENABLE;
 
  ALTER TABLE "HR"."GIAO_DICH_OBJTAB" ADD FOREIGN KEY ("NHAN_VIEN")
	  REFERENCES "HR"."NHAN_VIEN_OBJTAB"  ENABLE;
 
  ALTER TABLE "HR"."GIAO_DICH_OBJTAB" ADD FOREIGN KEY ("KHACH_HANG")
	  REFERENCES "HR"."KHACH_HANG_OBJTAB"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOA_DON_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."HOA_DON_OBJTAB" ADD FOREIGN KEY ("GIAO_DICH")
	  REFERENCES "HR"."GIAO_DICH_OBJTAB"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table LOAI_DICH_VU_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."LOAI_DICH_VU_OBJTAB" ADD FOREIGN KEY ("PHONG_BAN")
	  REFERENCES "HR"."PHONG_BAN_OBJTAB"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table NHAN_VIEN_OBJTAB
--------------------------------------------------------

  ALTER TABLE "HR"."NHAN_VIEN_OBJTAB" ADD FOREIGN KEY ("PHONG_BAN")
	  REFERENCES "HR"."PHONG_BAN_OBJTAB"  ENABLE;
--------------------------------------------------------
--  DDL for Procedure ADD_JOB_HISTORY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "HR"."ADD_JOB_HISTORY" 
  (  p_emp_id          job_history.employee_id%type
   , p_start_date      job_history.start_date%type
   , p_end_date        job_history.end_date%type
   , p_job_id          job_history.job_id%type
   , p_department_id   job_history.department_id%type
   )
IS
BEGIN
  INSERT INTO job_history (employee_id, start_date, end_date,
                           job_id, department_id)
    VALUES(p_emp_id, p_start_date, p_end_date, p_job_id, p_department_id);
END add_job_history;

/
--------------------------------------------------------
--  DDL for Procedure SECURE_DML
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "HR"."SECURE_DML" 
IS
BEGIN
  IF TO_CHAR (SYSDATE, 'HH24:MI') NOT BETWEEN '08:00' AND '18:00'
        OR TO_CHAR (SYSDATE, 'DY') IN ('SAT', 'SUN') THEN
	RAISE_APPLICATION_ERROR (-20205,
		'You may only make changes during normal office hours');
  END IF;
END secure_dml;

/
