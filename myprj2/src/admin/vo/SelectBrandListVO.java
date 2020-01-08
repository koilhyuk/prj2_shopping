package admin.vo;

public class SelectBrandListVO {
	private String b_name, b_code;

	public SelectBrandListVO(String b_name, String b_code) {
		super();
		this.b_name = b_name;
		this.b_code = b_code;
	}

	public String getB_name() {
		return b_name;
	}

	public String getB_code() {
		return b_code;
	}


}
