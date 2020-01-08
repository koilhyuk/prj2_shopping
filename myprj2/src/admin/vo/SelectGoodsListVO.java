package admin.vo;

/**
 * 상품관리 Value Object 
 * @author owner
 */
public class SelectGoodsListVO {
	
	private String g_code,g_name,g_inputdate,d_type,b_img, b_name;
	private int g_salenum,g_score,g_inventory,g_price;
	public SelectGoodsListVO(String g_code, String g_name,String b_img, String b_name, String g_inputdate, String d_type,
			 int g_price,int g_salenum, int g_score, int g_inventory) {
		super();
		this.g_code = g_code;
		this.g_name = g_name;
		this.g_inputdate = g_inputdate;
		this.d_type = d_type;
		this.b_img = b_img;
		this.b_name = b_name;
		this.g_salenum = g_salenum;
		this.g_score = g_score;
		this.g_inventory = g_inventory;
		this.g_price = g_price;
	}
	public String getG_code() {
		return g_code;
	}
	public String getG_name() {
		return g_name;
	}
	public String getG_inputdate() {
		return g_inputdate;
	}
	public String getD_type() {
		return d_type;
	}
	public String getB_img() {
		return b_img;
	}
	public String getB_name() {
		return b_name;
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
	public int getG_price() {
		return g_price;
	}

}//class
