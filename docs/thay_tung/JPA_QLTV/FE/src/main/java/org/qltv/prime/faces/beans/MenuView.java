package org.qltv.prime.faces.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@RequestScoped
public class MenuView {
     
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
    
    public void borrow() {
//        addMessage("Success", "Data updated");
    	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    	n.handleNavigation(FacesContext.getCurrentInstance(), null, "muon.xhtml");
    	FacesContext.getCurrentInstance().getRenderResponse();
    }
    public void pay() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "tra.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
    public void viewbook() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "sach.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
    public void viewnewspaper() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "tapchi.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
    public void viewdaily() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "baongay.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
    
    public void viewborrow() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "danhsachmuontra.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
    
    public void viewreader() {
//      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "bandoc.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
