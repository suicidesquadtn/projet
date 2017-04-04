package tn.esprit.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import tn.esprit.entities.Attribution;
import tn.esprit.entities.AttributionPk;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Users;
import tn.esprit.paginator.RepeatPaginator;
import tn.esprit.services.ManageAttribution;

@ManagedBean(name="attributionBean")
@ViewScoped
public class AttributionBean {
	
	private List<Badge> bList = new ArrayList<Badge>();
	private Badge bSelected = new Badge();
	private List<Users> uList = new ArrayList<Users>();
	private Users uSelected = new Users();
	private List<SelectItem> selectItemsForBadges;
	private int selectedBadgesId ;
	private List<SelectItem> selectItemsForUsers;
	private int selectedUsersId ;
	private Attribution attribution;
	private List<Attribution> attributions = new ArrayList<Attribution>();
	private List<Attribution> test1 = new ArrayList<Attribution>();
	private boolean visible=false;
	private Date dates=null;
	private RepeatPaginator paginator;
	@EJB
	ManageAttribution theService;

	
	
	
	@PostConstruct
	public void init(){ 
		attribution = new Attribution();
		attributions = theService.getAllattribution();
		List<Badge> bList = theService.getAllbadges();
		List<Users> uList = theService.getAllusers();
		selectItemsForBadges = new ArrayList<SelectItem>(bList.size());
		selectItemsForUsers = new ArrayList<SelectItem>(uList.size());
		for(Badge badge:bList){
			selectItemsForBadges.add(new SelectItem(badge.getBadgeId(),badge.getName()));
		}
		for(Users users:uList){
			selectItemsForUsers.add(new SelectItem(users.getUserId(),users.getNom()));
		}
		
}
	
	public String initialiser(){
		setVisible(true);
		return null;
		
	}
	public String Addatribution(){
		theService.AffectUserBadge(uSelected, bSelected);
		setVisible(false);
		init();
		return null;
	}
	public String Affect(){
		AttributionPk apk = new AttributionPk();
		apk.setBadgeId(bSelected.getBadgeId());
		apk.setUserId(uSelected.getUserId());
		attribution.setAttributionId(apk);
		Date date=new Date();
		attribution.setDateAttribution(date);
		theService.CreateAttribution(attribution);
		setVisible(false);
		attributions = theService.getAllattribution();
		init();
		return null;
	}
	public void FilterByDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(dates);
		System.out.println(format);
		test1 =getAttributions().stream().filter(p -> p.getDateAttribution().toString().compareTo(format)==0).collect(Collectors.toList());
	    paginator = new RepeatPaginator(test1);
	
	}
	
	
	
	public List<SelectItem> getSelectItemsForBadges() {
		return selectItemsForBadges;
	}

	
	public void setSelectItemsForPisteTypes(List<SelectItem> selectItemsForBadges) {
		this.selectItemsForBadges = selectItemsForBadges;
	}

	
	public int getSelectedBadgesId() {
		return selectedBadgesId;
	}

	
	public void setSelectedPisteTypeId(int selectedBadgesId) {
		this.selectedBadgesId = selectedBadgesId;
	}


	public List<Badge> getbList() {
		return bList;
	}


	public void setbList(List<Badge> bList) {
		this.bList = bList;
	}

	
	public Attribution getAttribution() {
		return attribution;
	}

	
	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

	
	public List<Users> getuList() {
		return uList;
	}

	
	public void setuList(List<Users> uList) {
		this.uList = uList;
	}

	/**
	 * @return the selectItemsForUsers
	 */
	public List<SelectItem> getSelectItemsForUsers() {
		return selectItemsForUsers;
	}

	/**
	 * @param selectItemsForUsers the selectItemsForUsers to set
	 */
	public void setSelectItemsForUsers(List<SelectItem> selectItemsForUsers) {
		this.selectItemsForUsers = selectItemsForUsers;
	}

	/**
	 * @return the selectedUsersId
	 */
	public int getSelectedUsersId() {
		return selectedUsersId;
	}

	/**
	 * @param selectedUsersId the selectedUsersId to set
	 */
	public void setSelectedUsersId(int selectedUsersId) {
		this.selectedUsersId = selectedUsersId;
	}

	/**
	 * @return the attributions
	 */
	public List<Attribution> getAttributions() {
		return attributions;
	}

	/**
	 * @param attributions the attributions to set
	 */
	public void setAttributions(List<Attribution> attributions) {
		this.attributions = attributions;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
	
	
	

	public Badge getbSelected() {
		return bSelected;
	}
	
	public void setbSelected(Badge bSelected) {
		this.bSelected = bSelected;
	}
	

	public Users getuSelected() {
		return uSelected;
	}
	
	public void setuSelected(Users uSelected) {
		this.uSelected = uSelected;
	}

	/**
	 * @return the test1
	 */
	public List<Attribution> getTest1() {
		return test1;
	}

	/**
	 * @param test1 the test1 to set
	 */
	public void setTest1(List<Attribution> test1) {
		this.test1 = test1;
	}

	/**
	 * @return the dates
	 */
	public Date getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(Date dates) {
		this.dates = dates;
	}

	/**
	 * @return the paginator
	 */
	public RepeatPaginator getPaginator() {
		return paginator;
	}

	/**
	 * @param paginator the paginator to set
	 */
	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}
	
	
	

}
