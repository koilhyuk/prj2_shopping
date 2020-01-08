package user.vo.content;

public class SelectRecentGoodsVO {

	private String g_code, g_name, g_img;

	public SelectRecentGoodsVO(String g_code, String g_name, String g_img) {
		super();
		this.g_code = g_code;
		this.g_name = g_name;
		this.g_img = g_img;
	}

	public String getG_code() {
		return g_code;
	}

	public String getG_name() {
		return g_name;
	}

	public String getG_img() {
		return g_img;
	}



}
