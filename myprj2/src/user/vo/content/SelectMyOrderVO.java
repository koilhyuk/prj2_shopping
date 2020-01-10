package user.vo.content;

public class SelectMyOrderVO {
	private String o_delivery  , o_date, g_name, b_name, o_code;
	private int g_price;
	public SelectMyOrderVO(String o_delivery, String o_date, String g_name, String b_name, String o_code, int g_price) {
		super();
		this.o_delivery = o_delivery;
		this.o_date = o_date;
		this.g_name = g_name;
		this.b_name = b_name;
		this.o_code = o_code;
		this.g_price = g_price;
	}
	public String getO_delivery() {
		return o_delivery;
	}
	public String getO_date() {
		return o_date;
	}
	public String getG_name() {
		return g_name;
	}
	public String getB_name() {
		return b_name;
	}
	public String getO_code() {
		return o_code;
	}
	public int getG_price() {
		return g_price;
	}
	
}
