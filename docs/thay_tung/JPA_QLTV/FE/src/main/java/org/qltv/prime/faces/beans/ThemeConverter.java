package org.qltv.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.nhom8.csdl.BookDaoImpl;
import org.nhom8.dttn.Sach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;
 
@FacesConverter("themeConverter")
@ManagedBean
public class ThemeConverter implements Converter {
	 @ManagedProperty("#{bookService}")
	    public static BookDaoImpl bookService;
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	System.out.println("-------------" + value);
            	//bookService = ((AutoCompleteView) fc.getCurrentInstance().
        		//getExternalContext().getApplicationMap().get("autoCompleteView")).getBookService();
            	//BookDaoImpl service = (BookDaoImpl) fc.getExternalContext().getApplicationMap().get("bookService");
            	
                return getProductService(fc).read(Long.parseLong(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid book."));
            } catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Sach) object).getId());
        }
        else {
            return null;
        }
    }   
    public BookDaoImpl getBookService() {
		return bookService;
	}

    private BookDaoImpl getProductService(FacesContext context){ 
        return (BookDaoImpl)FacesContextUtils.getWebApplicationContext(context).getBean("bookService"); 
    }  

	public void setBookService(BookDaoImpl bookService) {
		this.bookService = bookService;
	}

}     