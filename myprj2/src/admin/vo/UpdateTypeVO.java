package admin.vo;

public class UpdateTypeVO {
	private String newType, d_type;

	public UpdateTypeVO(String newType, String d_type) {
		super();
		this.newType = newType;
		this.d_type = d_type;
	}

	public String getnewType() {
		return newType;
	}

	public String getD_type() {
		return d_type;
	}
	

}
