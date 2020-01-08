package user.vo.content;

public class MemberCardInformVO {
	private String cardMethod, carNum;

	public MemberCardInformVO(String cardMethod, String carNum) {
		this.cardMethod = cardMethod;
		this.carNum = carNum;
	}

	public String getCardMethod() {
		return cardMethod;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCardMethod(String cardMethod) {
		this.cardMethod = cardMethod;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

}
