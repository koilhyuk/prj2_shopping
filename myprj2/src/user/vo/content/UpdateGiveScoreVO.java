package user.vo.content;

public class UpdateGiveScoreVO {
	
	private String o_code;
	private int o_score;
	public UpdateGiveScoreVO(String o_code, int o_score) {
		super();
		this.o_code = o_code;
		this.o_score = o_score;
	}
	public String getO_code() {
		return o_code;
	}
	public int getO_score() {
		return o_score;
	}
	
	

}
