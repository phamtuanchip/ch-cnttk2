package org.qltv.prime.faces.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.csdl.DailyPaperDaoImpl;
import org.nhom8.csdl.NewsPaperDaoImpl;
import org.nhom8.csdl.ReaderDaoImpl;
import org.nhom8.csdl.ReserveDaoImpl;
import org.nhom8.dttn.BaoNgay;
import org.nhom8.dttn.NguoiMuon;
import org.nhom8.dttn.PhieuDatMua;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.dttn.Sach;
import org.nhom8.dttn.TapChi;

@ManagedBean
@SessionScoped
public class HomeView {
	
	
	private Sach sach = new Sach();
	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public TapChi getTapchi() {
		return tapchi;
	}

	public void setTapchi(TapChi tapchi) {
		this.tapchi = tapchi;
	}

	public BaoNgay getBaongay() {
		return baongay;
	}

	public void setBaongay(BaoNgay baongay) {
		this.baongay = baongay;
	}

	private TapChi  tapchi = new TapChi();
	private BaoNgay baongay = new BaoNgay();
	
	@ManagedProperty("#{bookService}")
	private BookDaoImpl bookService;
	
	
	@ManagedProperty("#{dailyService}")
	private DailyPaperDaoImpl dailyService;
	
	@ManagedProperty("#{paperService}")
	private NewsPaperDaoImpl paperService;
	
	 @ManagedProperty("#{readerService}")
	    private ReaderDaoImpl readerService;
	    
	    @ManagedProperty("#{reserveService}")
	    private ReserveDaoImpl reserveService;
	
	public DailyPaperDaoImpl getDailyService() {
		return dailyService;
	}

	public void setDailyService(DailyPaperDaoImpl dailyService) {
		this.dailyService = dailyService;
	}

	public NewsPaperDaoImpl getPaperService() {
		return paperService;
	}

	public void setPaperService(NewsPaperDaoImpl paperService) {
		this.paperService = paperService;
	}

	public List<BaoNgay> getDailies() {
		return dailies;
	}

	public void setDailies(List<BaoNgay> dailies) {
		this.dailies = dailies;
	}

	public List<TapChi> getPapers() {
		return papers;
	}

	public void setPapers(List<TapChi> papers) {
		this.papers = papers;
	}

	public void setBooks(List<Sach> books) {
		this.books = books;
	}

	List<Sach> books;
	List<BaoNgay> dailies;
	List<TapChi> papers;
	private List<PhieuDatMuon> datmuon ;
	private List<PhieuNhacTra> nhactra ;
	private List<PhieuDatMua> datmua ;
	private List<NguoiMuon> bandoc;
	
	 @PostConstruct
	   public void init() {
		 books = bookService.getAll();
		 dailies = dailyService.getAll();
		 papers = paperService.getAll();
		     datmuon = reserveService.getAll();
			 // nhactra ;
			//  datmua ;
		     bandoc = readerService.getAll();
	  }
	 
	public BookDaoImpl getBookService() {
		return bookService;
	}

	public void setBookService(BookDaoImpl bookService) {
		this.bookService = bookService;
	}

	public void book() {
		bookService.save(sach);
		books = bookService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Sách "+sach.getName()+" đã thêm thành công"));
		sach = new Sach();
	}
	
	public void paper() {
		paperService.save(tapchi);
		papers = paperService.getAll();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Tạp chí "+tapchi.getName()+"đã thêm thành công"));
		tapchi = new TapChi();
	}
	
	public void daily() {
		dailyService.save(baongay);
		dailies = dailyService.getAll(); 
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Báo "+baongay.getName()+" đã thêm thành công"));
		baongay = new BaoNgay();
	}
	
	
	public void search() {
		bookService.read(sach);
	}
	
	public List<Sach> getBooks() {
		 return books;
	}

	public ReaderDaoImpl getReaderService() {
		return readerService;
	}

	public void setReaderService(ReaderDaoImpl readerService) {
		this.readerService = readerService;
	}

	public ReserveDaoImpl getReserveService() {
		return reserveService;
	}

	public void setReserveService(ReserveDaoImpl reserveService) {
		this.reserveService = reserveService;
	}

	public List<PhieuDatMuon> getDatmuon() {
		return datmuon;
	}

	public void setDatmuon(List<PhieuDatMuon> datmuon) {
		this.datmuon = datmuon;
	}

	public List<PhieuNhacTra> getNhactra() {
		return nhactra;
	}

	public void setNhactra(List<PhieuNhacTra> nhactra) {
		this.nhactra = nhactra;
	}

	public List<PhieuDatMua> getDatmua() {
		return datmua;
	}

	public void setDatmua(List<PhieuDatMua> datmua) {
		this.datmua = datmua;
	}

	public List<NguoiMuon> getBandoc() {
		return bandoc;
	}

	public void setBandoc(List<NguoiMuon> bandoc) {
		this.bandoc = bandoc;
	}
}
