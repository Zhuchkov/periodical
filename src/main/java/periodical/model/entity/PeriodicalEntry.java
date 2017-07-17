package periodical.model.entity;

import java.time.LocalDateTime;

public class PeriodicalEntry {

	private int id;
	private String data;
	private String name;
	private Periodical periodical;
	private LocalDateTime creationTime;
	
	PeriodicalEntry(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Periodical getPeriodical() {
		return periodical;
	}
	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static class Builder{
		private PeriodicalEntry instance = new PeriodicalEntry();
		
		public Builder setId(int id){
			instance.id=id;
			return this;
		}
		public Builder setName(String name){
			instance.name=name;
			return this;
		}
		public Builder setData(String data){
			instance.data=data;
			return this;
		}
		public Builder setPeriodical(Periodical periodical){
			instance.periodical=periodical;
			return this;
		}
		public Builder setCreationTime(LocalDateTime creationTime){
			instance.creationTime = creationTime;
			return this;
		}
		public PeriodicalEntry build(){
			return instance;
		}
	}
	@Override
	public String toString() {
		return "PeriodicalEntry [id=" + id + ", data=" + data + ", name=" + name + ", periodical=" + periodical
				+ ", creationTime=" + creationTime + "]";
	}
	

}
