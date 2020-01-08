package admin.vo;

/**
 * 상품상세보기 DTO 
 * @author hyebin
 */
public class SelectGoodsDetailDTO {

	private String  g_code, g_img, b_name, g_name, g_strong, d_type, c_type,g_inputdate ;
	private int  g_inventory, g_salenum, g_score, g_price;
	public void setG_code(String g_code) {
		this.g_code = g_code;
	}
	public void setG_img(String g_img) {
		this.g_img = g_img;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public void setG_strong(String g_strong) {
		this.g_strong = g_strong;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	public void setG_inputdate(String g_inputdate) {
		this.g_inputdate = g_inputdate;
	}
	public void setG_inventory(int g_inventory) {
		this.g_inventory = g_inventory;
	}
	public void setG_salenum(int g_salenum) {
		this.g_salenum = g_salenum;
	}
	public void setG_score(int g_score) {
		this.g_score = g_score;
	}
	public void setG_price(int g_price) {
		this.g_price = g_price;
	}
	public String getG_code() {
		return g_code;
	}
	public String getG_img() {
		return g_img;
	}
	public String getB_name() {
		return b_name;
	}
	public String getG_name() {
		return g_name;
	}
	public String getG_strong() {
		return g_strong;
	}
	public String getD_type() {
		return d_type;
	}
	public String getC_type() {
		return c_type;
	}
	public String getG_inputdate() {
		return g_inputdate;
	}
	public int getG_inventory() {
		return g_inventory;
	}
	public int getG_salenum() {
		return g_salenum;
	}
	public int getG_score() {
		return g_score;
	}
	public int getG_price() {
		return g_price;
	}

	
}//class
