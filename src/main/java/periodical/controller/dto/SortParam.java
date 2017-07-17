package periodical.controller.dto;

public enum SortParam {
NAME("periodical.name"),
COST("periodical.cost");
	private String SqlColumnName;
	
	SortParam(String param){
		this.SqlColumnName=param;
	}
	public String getColumnName(){
		return SqlColumnName;
	}
}
