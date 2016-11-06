package org.qltv.prime.faces.beans;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
 
@ManagedBean
public class ToolbarView {
     
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void borrow() {
      addMessage("Success", "Data updated");
  	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
  	n.handleNavigation(FacesContext.getCurrentInstance(), null, "muon.xhtml");
  	FacesContext.getCurrentInstance().getRenderResponse();
  	
//    	Map<String,Object> options = new HashMap<String, Object>();
//        options.put("modal", true);
//        options.put("width", 640);
//        options.put("height", 340);
//        options.put("contentWidth", "100%");
//        options.put("contentHeight", "100%");
//        options.put("headerElement", "customheader");
//    RequestContext.getCurrentInstance().openDialog("muonpop", options, null);
  	
  	
  }
  public void pay() {
//    addMessage("Success", "Data updated");
	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	n.handleNavigation(FacesContext.getCurrentInstance(), null, "tra.xhtml");
	FacesContext.getCurrentInstance().getRenderResponse();
}
  public void buy() {
//    addMessage("Success", "Data updated");
	NavigationHandler n = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	n.handleNavigation(FacesContext.getCurrentInstance(), null, "mua.xhtml");
	FacesContext.getCurrentInstance().getRenderResponse();
}
}