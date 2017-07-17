package periodical.model.entity;

public class Category {

	private int id;
	private String name;
	
	private Category(){
		
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
	public static class Builder{
		Category instance = new Category();
		
		public Builder setId(int id){
			instance.id=id;
			return this;
		}
		public Builder setName(String name){
			instance.name=name;
			return this;
		}
		public Category build(){
			return instance;
		}
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
