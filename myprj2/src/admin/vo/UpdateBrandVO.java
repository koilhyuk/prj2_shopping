package admin.vo;

public class UpdateBrandVO {
	String b_img, b_name, b_code;

	public UpdateBrandVO(String b_img, String b_name, String b_code) {
		super();
		this.b_img = b_img;
		this.b_name = b_name;
		this.b_code = b_code;
	}

	public String getB_img() {
		return b_img;
	}

	public String getB_name() {
		return b_name;
	}

	public String getB_code() {
		return b_code;
	}

	
}
