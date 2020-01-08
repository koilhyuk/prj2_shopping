package user.helper;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import user.controller.content.UserGoodsMainEvt;
import user.dao.UserDAO;
import user.view.content.UserGoodsRecentPanelView;
import user.vo.content.SelectRecentGoodsVO;

public class RecentThread extends Thread {

//	private static RecentThread rt;
	private boolean threadFlag;

	public RecentThread() {

	}

//	public static RecentThread getInstance() {
//		if (rt == null) {
//			rt = new RecentThread();
//		}
//		return rt;
//	}

	public void setStop(boolean threadFlag) {
		this.threadFlag = threadFlag;
	}

	@Override
	public void run() {
		JPanel jpRecent = UserGoodsMainEvt.ugmv.getJpRecent();
		UserDAO uDAO = UserDAO.getInstance();

		try {
			while (!threadFlag) {
				jpRecent.removeAll();
				List<SelectRecentGoodsVO> outputData = uDAO.selectRecent();
				int cnt = 0;
				SelectRecentGoodsVO srgVO = null;
				for (int i = 0; i < outputData.size(); i++) {
					srgVO = outputData.get(i);
					jpRecent.add(new UserGoodsRecentPanelView(outputData.get(i).getG_img(),
							outputData.get(i).getG_name() + "(" + outputData.get(i).getG_code() + ")"));
				} // end for
//				jpRecent.setPreferredSize(new Dimension(200,480));
				jpRecent.setVisible(false);
				jpRecent.setVisible(true);

				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(UserGoodsMainEvt.ugmv, "DBMS에 문제발생");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// run
}
