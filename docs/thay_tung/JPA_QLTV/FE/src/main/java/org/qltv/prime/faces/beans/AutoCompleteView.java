package org.qltv.prime.faces.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.csdl.ReaderDaoImpl;
import org.nhom8.csdl.ReserveDaoImpl;
import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.NguoiMuon;
import org.nhom8.dttn.PhieuDatMua;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.dttn.Sach;
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class AutoCompleteView {
     
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;
    private String txt7;
    private String txt8;
    private String option;  
    
    private NguoiMuon docgia = new NguoiMuon();
	private PhieuDatMuon phieudatmuon = new PhieuDatMuon();
	private PhieuNhacTra phieutra = new PhieuNhacTra();
	private PhieuDatMua phieumua = new PhieuDatMua() ;
	
	private List<Sach> borrowedBooks;
	
	public NguoiMuon getDocgia() {
		return docgia;
	}

	public void setDocgia(NguoiMuon docgia) {
		this.docgia = docgia;
	}

	public PhieuDatMuon getPhieudatmuon() {
		return phieudatmuon;
	}

	public void setPhieudatmuon(PhieuDatMuon phieudatmuon) {
		this.phieudatmuon = phieudatmuon;
	}



	private List<Sach> selectedThemes;
//    private Theme theme1;
//    private Theme theme2;
//    private Theme theme3;
//    private Theme theme4;
//    private List<Theme> selectedThemes;
     
    @ManagedProperty("#{bookService}")
    public static BookDaoImpl bookService;
    
    
    
    @ManagedProperty("#{readerService}")
    private ReaderDaoImpl readerService;
    
    @ManagedProperty("#{reserveService}")
    private ReserveDaoImpl reserveService;
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public List<String> completeName(String query) {
        List<String> results = new ArrayList<String>();
        NguoiMuon o = new NguoiMuon();
        o.setName(query);
        List<NguoiMuon> re = getReaderService().findAll(o);
        if(re != null && re.size() > 0)
       for(NguoiMuon n : getReaderService().findAll(o)){
    	   results.add(n.getName());
       }
      //  for(int i = 0; i < 10; i++) {
            //results.add(query + i);
      //  }
         
        return results;
    }
    public List<String> completeCard(String query) {
        List<String> results = new ArrayList<String>();
        NguoiMuon o = new NguoiMuon();
        o.setCardNum(query);
        List<NguoiMuon> re = getReaderService().findAll(o);
        if(re != null && re.size() > 0)
       for(NguoiMuon n : getReaderService().findAll(o)){
    	   results.add(n.getCardNum());
       }
      //  for(int i = 0; i < 10; i++) {
            //results.add(query + i);
      //  }
         
        return results;
    }
    public List<Sach> completeTheme(String query) {
        List<Sach> allThemes = bookService.getAll();
        List<Sach> filteredThemes = new ArrayList<Sach>();
         
        for (int i = 0; i < allThemes.size(); i++) {
            Sach skin = allThemes.get(i);
            if(skin.getName().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
    }
  
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
 
    public String getTxt1() {
        return txt1;
    }
 
    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }
 
    public String getTxt2() {
        return txt2;
    }
 
    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }
 
    public String getTxt3() {
        return txt3;
    }
 
    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }
 
    public String getTxt4() {
        return txt4;
    }
 
    public void setTxt4(String txt4) {
        this.txt4 = txt4;
    }
 
    public String getTxt5() {
        return txt5;
    }
 
    public void setTxt5(String txt5) {
        this.txt5 = txt5;
    }
 
    public String getTxt6() {
        return txt6;
    }
 
    public void setTxt6(String txt6) {
        this.txt6 = txt6;
    }
 
    public String getTxt7() {
        return txt7;
    }
 
    public void setTxt7(String txt7) {
        this.txt7 = txt7;
    }
 
    public String getTxt8() {
        return txt8;
    }
 
    public void setTxt8(String txt8) {
        this.txt8 = txt8;
    }
    public void setSelectedThemes(List<Sach> selectedThemes) {
        this.selectedThemes = selectedThemes;
    }
    public List<Sach> getSelectedThemes() {
        return selectedThemes;
    }



	public BookDaoImpl getBookService() {
		return bookService;
	}



	public void setBookService(BookDaoImpl bookService) {
		this.bookService = bookService;
	}

	public List<Sach> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<Sach> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public void book() {
		
		//for(book borrowedBooks)
	 
		if(getReaderService().read(docgia) == null){
		 
			docgia =	getReaderService().read(getReaderService().save(docgia));
			
		}
		phieudatmuon.setReader(docgia);
		//phieudatmuon.
		//phieudatmuon.setDate(date);
		borrowedBooks = getSelectedThemes();
		for(Sach s: borrowedBooks)
			phieudatmuon.addItem(s);
		
		getReserveService().save(phieudatmuon);
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Đã hoàn thành việc mượn"));
		 
	}
	public void buy() {
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Đã hoàn thành việc mua"));
		 
	}
	public PhieuNhacTra getPhieutra() {
		return phieutra;
	}

	public void setPhieutra(PhieuNhacTra phieutra) {
		this.phieutra = phieutra;
	}

	public PhieuDatMua getPhieumua() {
		return phieumua;
	}

	public void setPhieumua(PhieuDatMua phieumua) {
		this.phieumua = phieumua;
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
	
	 

 
}