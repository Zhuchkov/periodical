package periodical.model.entity;

import java.math.BigDecimal;

public class Periodical {
	private int id;
	private String name;
	private BigDecimal cost;
	private UserDetails publisher;
	private Category category;
	
	Periodical(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public UserDetails getPublisher() {
		return publisher;
	}

	public void setPublisher(UserDetails publisher) {
		this.publisher = publisher;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public static class Builder{
		private Periodical instance = new Periodical();
		
		public Builder setId(int id){
			instance.id=id;
			return this;
		}
		public Builder setName(String name){
			instance.name = name;
			return this;
		}
		public Builder setCost(BigDecimal cost){
			instance.cost=cost;
			return this;
		}
		public Builder setPublisher(UserDetails user){
			instance.publisher=user;
			return this;
		}
		public Builder setCategory(Category category){
			instance.category=category;
			return this;
		}
		public Periodical build(){
			return instance;
		}
	}


	@Override
	public String toString() {
		return "Periodical [id=" + id + ", name=" + name + ", cost=" + cost + ", publisher=" + publisher + ", category="
				+ category + "]";
	}
	

}
