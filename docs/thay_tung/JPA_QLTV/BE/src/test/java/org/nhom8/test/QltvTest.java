package org.nhom8.test;
import java.util.Date;
import java.util.Random;

import org.hibernate.loader.custom.Return;
import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.csdl.BuyDaoImpl;
import org.nhom8.csdl.DailyPaperDaoImpl;
import org.nhom8.csdl.EmployeeDaoImpl;
import org.nhom8.csdl.NewsPaperDaoImpl;
import org.nhom8.csdl.ReaderDaoImpl;
import org.nhom8.csdl.ReserveDaoImpl;
import org.nhom8.csdl.ReturnDaoImpl;
import org.nhom8.csdl.SystemDaoImpl;
import org.nhom8.dttn.BaoNgay;
import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.Employee;
import org.nhom8.dttn.NguoiMuon;
import org.nhom8.dttn.PhieuDatMua;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.dttn.Sach;
import org.nhom8.dttn.SystemLog;
import org.nhom8.dttn.TapChi;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class QltvTest extends TestCase {
	ClassPathXmlApplicationContext context;
	BookDaoImpl bDao;
	NewsPaperDaoImpl npDao;
	DailyPaperDaoImpl dpDao;
	ReaderDaoImpl rDao;
	ReserveDaoImpl rsDao;
	ReturnDaoImpl rtDao;
	BuyDaoImpl buyDao;
	EmployeeDaoImpl eDao;
	SystemDaoImpl sDao ;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub

		super.setUp();
		context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		bDao = (BookDaoImpl) context.getBean("bookDao");
		npDao = (NewsPaperDaoImpl) context.getBean("newsPDao");
		dpDao = (DailyPaperDaoImpl) context.getBean("dailyPDao");
		rDao  = (ReaderDaoImpl) context.getBean("rDao");
		rsDao = (ReserveDaoImpl) context.getBean("rsDao");
		rtDao = (ReturnDaoImpl) context.getBean("rtDao");
		buyDao = (BuyDaoImpl) context.getBean("buyDao");
		eDao = (EmployeeDaoImpl) context.getBean("eDao");
		sDao = (SystemDaoImpl) context.getBean("sDao");
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		context.close();
	}
	public void testSaveEm(){
		Employee e = new Employee();
		e.setEmployeeHireDate(new Date());
		e.setEmployeeName("Pham Tuan");
		e.setNote("Qua thu viec");
		eDao.save(e);
		SystemLog s = new SystemLog(e.toString(), "created");
		sDao.save(s);
	}
	
	public void testSaveBook() {
		Sach s = new Sach();
		s.setName("JPA for dummy");
		s.setAuthor("tuanpham");
		s.setNumOfPage(2);
		s.setPublish("NXB HN");
		s.setNum(10);
		Long id = bDao.save(s);
//		List<Sach> sl = bDao.getAll();
//		for (Sach sb : sl) {
//			System.out.println("================created book" +sb);
//		}

		Sach savedS = bDao.read(id);    
		System.out.println("================saved book" +savedS);
	}
	
	public void testSaveNewsPaper() {
		TapChi s = new TapChi();
		s.setName("Bao nhan dan");
		s.setNum(10);
		s.setYear(2016);
//		s.setAuthor("tuanpham");
//		s.setNumOfPage(2);
//		s.setPublish("NXB HN");
		Long id = npDao.save(s);
//		List<Sach> sl = bDao.getAll();
//		for (Sach sb : sl) {
//			System.out.println("================created book" +sb);
//		}

		TapChi savedS = npDao.read(id);    
		System.out.println("================saved TapChi" +savedS);
	}
	
	public void testSaveDailyPaper() {
		BaoNgay s = new BaoNgay();
		s.setName("Mua ban");
		s.setDate(new Date());
//		s.setAuthor("tuanpham");
//		s.setNumOfPage(2);
//		s.setPublish("NXB HN");
		Long id = dpDao.save(s);
//		List<Sach> sl = bDao.getAll();
//		for (Sach sb : sl) {
//			System.out.println("================created book" +sb);
//		}

		BaoNgay savedS = dpDao.read(id);    
		System.out.println("================saved Baongay" +savedS);
	}
	
	public void testSaveBandoc() {
		NguoiMuon s = new NguoiMuon();
		s.setName("Pham Tuan");
//		s.setDate(new Date());
//		s.setAuthor("tuanpham");
//		s.setNumOfPage(2);
//		s.setPublish("NXB HN");
		Long id = rDao.save(s);
//		List<Sach> sl = bDao.getAll();
//		for (Sach sb : sl) {
//			System.out.println("================created book" +sb);
//		}

		NguoiMuon savedS = rDao.read(id);    
		System.out.println("================saved Baongay" +savedS);
	}
	
	public void testSaveDatMua() {
		BaoNgay s = new BaoNgay();
		s.setName("Mua ban");
		s.setDate(new Date());
		s.setNum(10);
		//dpDao.save(s);
		TapChi s2 = new TapChi();
		s2.setName("Bao nhan dan");
		s2.setNum(10);
		s2.setYear(2016);
		s2.setNum(10);
		//npDao.save(s2);
		Sach s3 = new Sach();
		s3.setName("JPA for dummy");
		s3.setAuthor("tuanpham");
		s3.setNumOfPage(2);
		s3.setPublish("NXB HN");
		s3.setNum(10);
		//bDao.save(s3);
		
		PhieuDatMua mua = new PhieuDatMua();
		mua.addItem(s);
		mua.addItem(s2);
		mua.addItem(s3);
		mua.setPublisher("NXB Hanoi");
		Long idb = buyDao.save(mua);
		
		System.out.println("================saved Datmua" +mua);
	}	
	public void testSaveDatMuon() {
		BaoNgay s = new BaoNgay();
		s.setName("Mua ban");
		s.setDate(new Date());
		s.setNum(10);
		Long id = dpDao.save(s);
		BaoNgay savedS = dpDao.read(id);    
		System.out.println("================saved Baongay" +savedS);
		NguoiMuon r = new NguoiMuon();
		r.setName("Pham Tuan");
		r.setAddress("Hanoi");
		r.setCardNum("pt110100011");
		id = rDao.save(r);
		NguoiMuon rd = rDao.read(id);    
		
		System.out.println("================saved Nguoi muon" +rd);
		
		PhieuDatMuon p = new PhieuDatMuon();
		p.setDate(new Date());
		p.setReaderId(rd.getId());
		p.setResevId(rd.getId());
		p.setReader(rd);
//		HashSet<DauMuc> hs = new HashSet<DauMuc>();
//		hs.add(savedS);
		//p.setItems(hs);
		p.addItem(savedS);
		
		p.setItemId(savedS.getId());
		BaoNgay b = new BaoNgay();
		b.setName("VNEXPRESS");
		b.setNum(100);
		Long bid = dpDao.save(b);
		p.addItem(b);
//		b = dpDao.read(bid);
//		b.setNum(b.getNum()-1);
//		dpDao.save(b);
		
		id = rsDao.save(p);
		
		p = rsDao.read(id);
		System.out.println("================saved Phieu" +p);
		
		PhieuDatMuon p2 =  rsDao.read(p);
		// sqlite no support relation 
//		System.out.println("================Nguoi Muon " +p2.getReader().getName() + "Ngay Muon " + p2.getResevDate());
//		int i =1;
//		for(DauMuc d : p2.getItems()) {
//			System.out.println(i++ +")================saved Item === " +d.getName() + "num left " + d.getNum());
//			
//	   }
		
		
	}
	
	
	public void testSaveTra() {
		BaoNgay s = new BaoNgay();
		s.setName("Mua ban");
		s.setDate(new Date());
		s.setNum(10);
		Long id = dpDao.save(s);
		BaoNgay savedS = dpDao.read(id);    
		System.out.println("================saved Baongay" +savedS);
		NguoiMuon r = new NguoiMuon();
		r.setName("Pham Tuan");
		r.setAddress("Hanoi");
		r.setCardNum("TVVN"+ new Random().nextInt(10000));
		id = rDao.save(r);
		NguoiMuon rd = rDao.read(id);    
		
		System.out.println("================saved Nguoi muon" +rd);
		
		PhieuNhacTra p = new PhieuNhacTra();
		p.setDate(new Date());
		p.setNguoiMuon(r.getId()); 
		p.setTaiLieu(s.getId());
//		b = dpDao.read(bid);
//		b.setNum(b.getNum()-1);
//		dpDao.save(b);
		
		id = rtDao.save(p);
		
		p = rtDao.read(id);
		System.out.println("================saved Phieu Tra" +p);
		
		PhieuNhacTra p2 =  rtDao.read(p);
		// sqlite no support relation 
//		System.out.println("================Nguoi Muon " +p2.getReader().getName() + "Ngay Muon " + p2.getResevDate());
//		int i =1;
//		for(DauMuc d : p2.getItems()) {
//			System.out.println(i++ +")================saved Item === " +d.getName() + "num left " + d.getNum());
//			
//	   }
		
		
	}
	
	public void testAutoComplate() {
		NguoiMuon a = new NguoiMuon();
		a.setName("pham");
	 
		rDao.save(a);
		
		//assertTrue(null != rDao.read(a));
		
		
	}
	
	public void testAutoComplate2() {
		NguoiMuon a = new NguoiMuon();
		a.setCardNum("vpd1234");
		
		rDao.save(a);
		
		assertTrue(0<rDao.findAll(a).size());
		
		
	}
}
