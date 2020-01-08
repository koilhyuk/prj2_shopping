package admin.vo;

public class UpdateGoodsVO {

	private String g_code, g_img, g_strong;
	private int g_price, g_inventory;
	public UpdateGoodsVO(String g_code, String g_img, String g_strong, int g_price, int g_inventory) {
		super();
		this.g_code = g_code;
		this.g_img = g_img;
		this.g_strong = g_strong;
		this.g_price = g_price;
		this.g_inventory = g_inventory;
	}
	public String getG_code() {
		return g_code;
	}
	public String getG_img() {
		return g_img;
	}
	public String getG_strong() {
		return g_strong;
	}
	public int getG_price() {
		return g_price;
	}
	public int getG_inventory() {
		return g_inventory;
	}

	
}//class
