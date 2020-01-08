package admin.vo;

public class InsertGoodsTypeVO {
	private String d_type,c_type;

	public InsertGoodsTypeVO(String d_type, String c_type) {
		super();
		this.d_type = d_type;
		this.c_type = c_type;
	}

	public String getD_type() {
		return d_type;
	}

	public String getC_type() {
		return c_type;
	}
	
}
