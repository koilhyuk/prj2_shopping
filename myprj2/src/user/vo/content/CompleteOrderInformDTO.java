package user.vo.content;

public class CompleteOrderInformDTO {
	// ��ǰ ����, ������, �ֹ� ����
	// ��ǰ ��(sniDTO gName), ����(sniDTO mQuantity), �ֹ���ȣ(orderCode), ����ó(orderPhone),
	// ������(sniDTO mName),
	// �����(delivery + sniDTO.getmDetailAddr() + (zipcode),
	// ��û����(deliveryDemand) �ֹ� ����(cardMethod), , �� ����
	// �ݾ�(sniDTO totalMoney)

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
