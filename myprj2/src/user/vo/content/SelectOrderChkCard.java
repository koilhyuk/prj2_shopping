package user.vo.content;

public class SelectOrderChkCard {
	private String transCardNum, transCardCVC, cardMethod;

	public SelectOrderChkCard(String transCardNum, String transCardCVC, String cardMethod) {
		super();
		this.transCardNum = transCardNum;
		this.transCardCVC = transCardCVC;
		this.cardMethod = cardMethod;
	}

	public String getTransCardNum() {
		return transCardNum;
	}

	public String getTransCardCVC() {
		return transCardCVC;
	}

	public String getCardMethod() {
		return cardMethod;
	}

}
