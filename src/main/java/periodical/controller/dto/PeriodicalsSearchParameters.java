package periodical.controller.dto;

import periodical.model.entity.Category;

public class PeriodicalsSearchParameters {
	
	private String publisherName;
	private String periodicalName;
	private long maxCost;
	private Category category;
	private SortParam sortParam;
	private boolean descending ;
	
	private PeriodicalsSearchParameters(){
		
	}
	
	public boolean hasPublisherName(){
		return (publisherName!=null||!publisherName.isEmpty());
	}
	public boolean hasPeriodicalName(){
		return (periodicalName!=null||!periodicalName.isEmpty()); 
	}
	public boolean hasMaxCost(){
		return maxCost>0;
	}
	public boolean hasCategory(){
		return category!=null;
	}
	public boolean hasWhereConstraints(){
		return hasCategory()||hasPublisherName()||hasPeriodicalName()||hasMaxCost();
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPeriodicalName() {
		return periodicalName;
	}
	public void setPeriodicalName(String name) {
		this.periodicalName = name;
	}
	public long getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(long maxPrice) {
		this.maxCost = maxPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public SortParam getSortParam() {
		return sortParam;
	}

	public void setSortParam(SortParam sortParam) {
		this.sortParam = sortParam;
	}

	public boolean isDescending() {
		return descending;
	}

	public void setDescending(boolean descending) {
		this.descending = descending;
	}

	public static class Builder{
		private PeriodicalsSearchParameters instance = new PeriodicalsSearchParameters();
		
		public Builder setPublisherName(String publisherName){
			instance.publisherName=publisherName;
			return this;
		}
		public Builder setPeriodicalName(String periodicalName){
			instance.periodicalName=periodicalName;
			return this;
		}
		public Builder setMaxCost(long maxCost){
			instance.maxCost=maxCost;
			return this;
		}
		public Builder setCategory(Category category){
			instance.category=category;
			return this;
		}
		public Builder setSortParam(SortParam sortParam){
			instance.sortParam=sortParam;
			return this;
		}
		public Builder setDescending(boolean desc){
			instance.descending=desc;
			return this;
		}
		
		public PeriodicalsSearchParameters build(){
			return instance;
		}
	}

	@Override
	public String toString() {
		return "PeriodicalsSearchParameters [publisherName=" + publisherName + ", periodicalName=" + periodicalName
				+ ", maxCost=" + maxCost + ", category=" + category + ", sortParam=" + sortParam + ", descending="
				+ descending + "]";
	}
	
	
}
