package admin.vo;

/**
 * 상품등록 VO
 * @author hyebin
 */
public class InsertGoodsAddVO {
	private String g_img, b_name, d_type,g_name, g_strong;
	private int g_price, g_inventory;
	public InsertGoodsAddVO(String g_img, String b_name, String d_type, String g_name,int g_price,
			int g_inventory,String g_strong) {
		super();
		this.g_img = g_img;
		this.b_name = b_name;
		this.d_type = d_type;
		this.g_name = g_name;
		this.g_strong = g_strong;
		this.g_price = g_price;
		this.g_inventory = g_inventory;
	}
	public String getG_img() {
		return g_img;
	}
	public String getB_name() {
		return b_name;
	}
	public String getD_type() {
		return d_type;
	}
	public String getG_name() {
		return g_name;
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
