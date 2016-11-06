package org.qltv.prime.faces.beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.csdl.DailyPaperDaoImpl;
import org.nhom8.csdl.NewsPaperDaoImpl;
import org.nhom8.dttn.BaoNgay;
import org.nhom8.dttn.Sach;
import org.nhom8.dttn.TapChi;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    @ManagedProperty("#{bookService}")
	private BookDaoImpl bookService;
	
	
	@ManagedProperty("#{dailyService}")
	private DailyPaperDaoImpl dailyService;
	
	@ManagedProperty("#{paperService}")
	private NewsPaperDaoImpl paperService;
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
     
    private void createPieModels() {
        createPieModel1();
    }
 
    public BookDaoImpl getBookService() {
		return bookService;
	}

	public void setBookService(BookDaoImpl bookService) {
		this.bookService = bookService;
	}

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

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Sách", bookService.countAll("Sach"));
        pieModel1.set("Báo ngày", dailyService.countAll("BaoNgay"));
        pieModel1.set("Tạp chí", paperService.countAll("TapChi"));
         
        pieModel1.setTitle("Thống kê");
        pieModel1.setLegendPosition("w");
    }
     
     
}