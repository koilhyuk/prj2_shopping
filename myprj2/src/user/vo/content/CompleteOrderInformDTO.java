package user.vo.content;

public class CompleteOrderInformDTO {
	// 상품 사진, 구매자, 주문 일자
	// 상품 명(sniDTO gName), 수량(sniDTO mQuantity), 주문번호(orderCode), 연락처(orderPhone),
	// 수취인(sniDTO mName),
	// 배송지(delivery + sniDTO.getmDetailAddr() + (zipcode),
	// 요청사항(deliveryDemand) 주문 수단(cardMethod), , 총 결제
	// 금액(sniDTO totalMoney)

	private String gImg, mName, oDate, gName, oCode, oPhone, oAddressee, oDetailAddr, oDemand, pMethod;
	private int oQuantity, oTotalMoney;

	public String getgImg() {
		return gImg;
	}

	public void setgImg(String gImg) {
		this.gImg = gImg;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getoDate() {
		return oDate;
	}

	public void setoDate(String oDate) {
		this.oDate = oDate;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getoCode() {
		return oCode;
	}

	public void setoCode(String oCode) {
		this.oCode = oCode;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoAddressee() {
		return oAddressee;
	}

	public void setoAddressee(String oAddressee) {
		this.oAddressee = oAddressee;
	}

	public String getoDetailAddr() {
		return oDetailAddr;
	}

	public void setoDetailAddr(String oDetailAddr) {
		this.oDetailAddr = oDetailAddr;
	}

	public String getoDemand() {
		return oDemand;
	}

	public void setoDemand(String oDemand) {
		this.oDemand = oDemand;
	}

	public String getpMethod() {
		return pMethod;
	}

	public void setpMethod(String pMethod) {
		this.pMethod = pMethod;
	}

	public int getoQuantity() {
		return oQuantity;
	}

	public void setoQuantity(int oQuantity) {
		this.oQuantity = oQuantity;
	}

	public int getoTotalMoney() {
		return oTotalMoney;
	}

	public void setoTotalMoney(int oTotalMoney) {
		this.oTotalMoney = oTotalMoney;
	}

	@Override
	public String toString() {
		return "CompleteOrderInformDTO [gImg=" + gImg + ", mName=" + mName + ", oDate=" + oDate + ", gName=" + gName
				+ ", oCode=" + oCode + ", oPhone=" + oPhone + ", oAddressee=" + oAddressee + ", oDetailAddr="
				+ oDetailAddr + ", oDemand=" + oDemand + ", pMethod=" + pMethod + ", oQuantity=" + oQuantity
				+ ", oTotalMoney=" + oTotalMoney + "]";
	}

}// class
