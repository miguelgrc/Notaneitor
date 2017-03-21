package com.sdi.presentation;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.sdi.dto.Category;

@ManagedBean(name="menuTasks")
@SessionScoped
public class MenuTasksBean {

	private MenuModel mb = new DefaultMenuModel();
	
	public MenuModel getMb() {
		return mb;
	}

	public void setMb(MenuModel mb) {
		this.mb = mb;
	}

	@ManagedProperty(value = "#{tasks}")
	private TasksBean tasksInfo;
	
	public MenuTasksBean(){
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		
		String inbox = bundle.getString("inbox");
		String today = bundle.getString("today");
		String weekly = bundle.getString("weekly");
		String categories =  bundle.getString("categories");
		
		DefaultMenuItem inboxItem = new DefaultMenuItem(inbox);
		inboxItem.setId("inbox_item");
		inboxItem.setCommand("#{tasks.listarInbox}");
		
		DefaultMenuItem todayItem = new DefaultMenuItem(today);
		todayItem.setId("today_item");
		todayItem.setCommand("#{tasks.listarHoy}");
		
		DefaultMenuItem weeklyItem = new DefaultMenuItem(weekly);
		weeklyItem.setId("weekly_item");
		weeklyItem.setCommand("#{tasks.listarSemanal}");
		
		DefaultSubMenu categoriesMenu = new DefaultSubMenu(categories);
		categoriesMenu.setId("categories_submenu");
		
		for(Category c: tasksInfo.getCategories()){
			DefaultMenuItem categItem = new DefaultMenuItem(c.getName());
			categItem.setId("category_"+c.getId());
			categItem.setCommand("#{tasks.listarCategoria(c)}");
			categoriesMenu.addElement(categItem);
		}
		
		this.mb.addElement(inboxItem);
		this.mb.addElement(todayItem);
		this.mb.addElement(categoriesMenu);
	}
}