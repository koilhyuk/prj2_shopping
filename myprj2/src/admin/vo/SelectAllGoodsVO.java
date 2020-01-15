package admin.vo;

public class SelectAllGoodsVO {

	private String g_code, g_name, g_img, b_name;
	private int g_price, g_salenum, g_score, g_inventory;

	public SelectAllGoodsVO(String g_code, String g_name, String g_img, String b_name, int g_price, int g_salenum,
			int g_score, int g_inventory) {
		super();
		this.g_code = g_code;
		this.g_name = g_name;
		this.g_img = g_img;
		this.b_name = b_name;
		this.g_price = g_price;
		this.g_salenum = g_salenum;
		this.g_score = g_score;
		this.g_inventory = g_inventory;
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

	public String getB_name() {
		return b_name;
	}

	public int getG_price() {
		return g_price;
	}

	public int getG_salenum() {
		return g_salenum;
	}

	public int getG_score() {
		return g_score;
	}

	public int getG_inventory() {
		return g_inventory;
	}

	@Override
	public String toString() {
		return "SelectAllGoodsVO [g_code=" + g_code + ", g_name=" + g_name + ", g_img=" + g_img + ", b_name=" + b_name
				+ ", g_price=" + g_price + ", g_salenum=" + g_salenum + ", g_score=" + g_score + ", g_inventory="
				+ g_inventory + "]";
	}
	

}
