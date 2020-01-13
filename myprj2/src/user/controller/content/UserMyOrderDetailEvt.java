package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.dao.ClientDAO;
import user.view.content.UserMyOrderDetailView;
import user.vo.content.UpdateGiveScoreVO;

public class UserMyOrderDetailEvt implements ActionListener {
	
	private UserMyOrderDetailView umv;
	public UserMyOrderDetailEvt(UserMyOrderDetailView umv) {
	this.umv=umv;
		
	}//UserMyOrderDetailEvt
	
	public void modifyGiveScore() {
		int score=(int)umv.getJcbScore().getSelectedItem();
		UpdateGiveScoreVO ugVO= null;
		String code=umv.getJtfOrderCode().getText().trim();
		String gCode=umv.getJtfGoodsCode().getText().trim();
		
		try {
			ClientDAO cDAO= ClientDAO.getInstance();
			ugVO= new UpdateGiveScoreVO(code, score);
			if(cDAO.updateGiveScore(ugVO)) {
				if(cDAO.updateGoodeScore(gCode)) {
				JOptionPane.showMessageDialog(umv, "선택한 상품에 평점을 성공적으로 입력했습니다.");
				umv.getJbtnScore().setEnabled(false);
				umv.getJcbScore().setEnabled(false);
				}//end if
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//modifygiveScore

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == umv.getJbtnClose()) {
				umv.dispose();
		} // end if
		if (ae.getSource() == umv.getJbtnScore()) { //평점주기
			modifyGiveScore();
		} // end if
	}// actionPerformed

}//class
