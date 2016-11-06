package org.nhom8.test;
import java.util.List;

import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.dttn.Employee;
import org.nhom8.dttn.Sach;
import org.qltv.spring.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import junit.framework.TestCase;

public class QltvTest extends TestCase {
	ClassPathXmlApplicationContext context;
	EmployeeService employeeService;
	BookDaoImpl bookService;
//	BookDaoImpl bDao;
//	NewsPaperDaoImpl npDao;
//	DailyPaperDaoImpl dpDao;
//	ReaderDaoImpl rDao;
//	ReserveDaoImpl rsDao;
//	BuyDaoImpl buyDao;
//	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub

		super.setUp();
		
		context = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/applicationContext.xml");
//		bDao = (BookDaoImpl) context.getBean("bookDao");
//		npDao = (NewsPaperDaoImpl) context.getBean("newsPDao");
//		dpDao = (DailyPaperDaoImpl) context.getBean("dailyPDao");
//		rDao  = (ReaderDaoImpl) context.getBean("rDao");
//		rsDao = (ReserveDaoImpl) context.getBean("rsDao");
//		buyDao = (BuyDaoImpl) context.getBean("buyDao");
		employeeService = (EmployeeService) context.getBean("employeeService");
		bookService = (BookDaoImpl) context.getBean("bookService");
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
 		context.close();
	}
	public void testSaveEmployee() {
//		employeeService = (EmployeeService) context.getBean("employeeService");
		Employee e = new Employee();
		e.setEmployeeName("unitest1");
		employeeService.register(e);
		
	}
	public void testSaveBook() {
		Sach s = new Sach();
		s.setName("JPA for dummy1000");
		s.setAuthor("tuanpham");
		s.setNumOfPage(2);
		s.setPublish("NXB HN");
		s.setNum(10);
		Long id = bookService.save(s);
//		List<Sach> sl = bookService.getAll();
//		for (Sach sb : sl) {
//			System.out.println("================created book" +sb);
//		}

		Sach savedS = bookService.read(id);    
		System.out.println("================saved book" +savedS);
	}
//	
//	public void testSaveNewsPaper() {
//		TapChi s = new TapChi();
//		s.setName("Bao nhan dan");
//		s.setNum(10);
//		s.setYear(2016);
////		s.setAuthor("tuanpham");
////		s.setNumOfPage(2);
////		s.setPublish("NXB HN");
//		Long id = npDao.save(s);
////		List<Sach> sl = bDao.getAll();
////		for (Sach sb : sl) {
////			System.out.println("================created book" +sb);
////		}
//
//		TapChi savedS = npDao.read(id);    
//		System.out.println("================saved TapChi" +savedS);
//	}
//	
//	public void testSaveDailyPaper() {
//		BaoNgay s = new BaoNgay();
//		s.setName("Mua ban");
//		s.setDate(new Date());
////		s.setAuthor("tuanpham");
////		s.setNumOfPage(2);
////		s.setPublish("NXB HN");
//		Long id = dpDao.save(s);
////		List<Sach> sl = bDao.getAll();
////		for (Sach sb : sl) {
////			System.out.println("================created book" +sb);
////		}
//
//		BaoNgay savedS = dpDao.read(id);    
//		System.out.println("================saved Baongay" +savedS);
//	}
//	
//	public void testSaveBandoc() {
//		NguoiMuon s = new NguoiMuon();
//		s.setName("Pham Tuan");
////		s.setDate(new Date());
////		s.setAuthor("tuanpham");
////		s.setNumOfPage(2);
////		s.setPublish("NXB HN");
//		Long id = rDao.save(s);
////		List<Sach> sl = bDao.getAll();
////		for (Sach sb : sl) {
////			System.out.println("================created book" +sb);
////		}
//
//		NguoiMuon savedS = rDao.read(id);    
//		System.out.println("================saved Baongay" +savedS);
//	}
//	
//	public void testSaveDatMua() {
//		BaoNgay s = new BaoNgay();
//		s.setName("Mua ban");
//		s.setDate(new Date());
//		s.setNum(10);
//		
//		TapChi s2 = new TapChi();
//		s2.setName("Bao nhan dan");
//		s2.setNum(10);
//		s2.setYear(2016);
//		s2.setNum(10);
//		
//		Sach s3 = new Sach();
//		s3.setName("JPA for dummy");
//		s3.setAuthor("tuanpham");
//		s3.setNumOfPage(2);
//		s3.setPublish("NXB HN");
//		s3.setNum(10);
//		
//		PhieuDatMua mua = new PhieuDatMua();
//		mua.addItem(s);
//		mua.addItem(s2);
//		mua.addItem(s3);
//		mua.setPublisher("NXB Hanoi");
//		Long idb = buyDao.save(mua);
//		
//		System.out.println("================saved Datmua" +mua);
//	}	
//	public void testSaveDatMuon() {
//		BaoNgay s = new BaoNgay();
//		s.setName("Mua ban");
//		s.setDate(new Date());
//		s.setNum(10);
//		Long id = dpDao.save(s);
//		BaoNgay savedS = dpDao.read(id);    
//		System.out.println("================saved Baongay" +savedS);
//		NguoiMuon r = new NguoiMuon();
//		r.setName("Pham Tuan");
//		r.setAddress("Hanoi");
//		r.setCardNum("pt110100011");
//		id = rDao.save(r);
//		NguoiMuon rd = rDao.read(id);    
//		
//		System.out.println("================saved Nguoi muon" +rd);
//		
//		PhieuDatMuon p = new PhieuDatMuon();
//		p.setResevDate(new Date());
//		p.setReaderId(rd.getId());
//		p.setResevId(rd.getId());
////		HashSet<DauMuc> hs = new HashSet<DauMuc>();
////		hs.add(savedS);
//		//p.setItems(hs);
//		p.addItem(savedS);
//		
//		p.setItemId(savedS.getId());
//		BaoNgay b = new BaoNgay();
//		b.setName("VNEXPRESS");
//		b.setNum(100);
//		Long bid = dpDao.save(b);
//		p.addItem(b);
////		b = dpDao.read(bid);
////		b.setNum(b.getNum()-1);
////		dpDao.save(b);
//		
//		id = rsDao.save(p);
//		
//		p = rsDao.read(id);
//		System.out.println("================saved Phieu" +p);
//		
//		PhieuDatMuon p2 =  rsDao.read(p);
//		System.out.println("================Nguoi Muon " +p2.getReader().getName() + "Ngay Muon " + p2.getResevDate());
//		int i =1;
//		for(DauMuc d : p2.getItems()) {
//			System.out.println(i++ +")================saved Item === " +d.getName() + "num left " + d.getNum());
//			
//	   }
//		
//	}
}
