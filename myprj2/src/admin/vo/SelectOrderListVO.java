package admin.vo;

/**
 * 주문관리 VO
 * @author hyebin
 *
 */
public class SelectOrderListVO {
	private String o_code,	o_phone,o_delivery,	o_date,	o_person,m_id, g_name;	
	private int o_quantity, o_buypay;
	public SelectOrderListVO(String o_code,String m_id, String o_person,String o_phone, String g_name, int o_quantity, int o_buypay,
			String o_delivery, String o_date) {
		super();
		this.o_code = o_code;
		this.o_phone = o_phone;
		this.o_delivery = o_delivery;
		this.o_date = o_date;
		this.o_person = o_person;
		this.m_id = m_id;
		this.g_name = g_name;
		this.o_quantity = o_quantity;
		this.o_buypay = o_buypay;
	}
	public String getO_code() {
		return o_code;
	}
	public String getO_phone() {
		return o_phone;
	}
	public String getO_delivery() {
		return o_delivery;
	}
	public String getO_date() {
		return o_date;
	}
	public String getO_person() {
		return o_person;
	}
	public String getM_id() {
		return m_id;
	}
	public String getG_name() {
		return g_name;
	}
	public int getO_quantity() {
		return o_quantity;
	}
	public int getO_buypay() {
		return o_buypay;
	}
	
	
	}// class
