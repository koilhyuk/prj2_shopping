package admin.vo;

public class SelectCashCalcVO {
	private int price, saleCnt;

	public SelectCashCalcVO(int price, int saleCnt) {
		super();
		this.price = price;
		this.saleCnt = saleCnt;
	}// CalculateVO

	public int getPrice() {
		return price;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

}// class
