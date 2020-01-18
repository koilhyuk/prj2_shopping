package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import admin.dao.AdDAO;
import admin.run.StaticCla;
import admin.view.AdCashCalcView;
import admin.vo.SelectCashCalcVO;

public class AdCashCalcEvt implements ActionListener {

	public final static int CALDAY = 0;
	public final static int CALMON = 1;
	public final static int CALYEAR = 2;
	public final static int CALTOTAL = 3;

	private AdCashCalcView cv;

	public AdCashCalcEvt(AdCashCalcView cv) {
		this.cv = cv;
		calculateTotal();
	}// AdCashCalcEvt

	public void calculateTotal() {
		DecimalFormat priceFormat = new DecimalFormat("###,###");

		SelectCashCalcVO cVO = null;
		AdDAO uDAO = AdDAO.getInstance();

		try {
			for (int i = 0; i < 4; i++) {
				cVO = uDAO.selectCalTotal(i);
				switch (i) {
				case CALDAY:
					cv.getJtfDprice().setText(priceFormat.format(cVO.getPrice()) + " ��");
					cv.getJtfDcount().setText(priceFormat.format(cVO.getSaleCnt()) + " ��");
					cVO = null;
					break;
				case CALMON:
					cv.getJtfMprice().setText(priceFormat.format(cVO.getPrice()) + " ��");
					cv.getJtfMcount().setText(priceFormat.format(cVO.getSaleCnt()) + " ��");
					cVO = null;
					break;
				case CALYEAR:
					cv.getJtfYprice().setText(priceFormat.format(cVO.getPrice()) + " ��");
					cv.getJtfYcount().setText(priceFormat.format(cVO.getSaleCnt()) + " ��");
					cVO = null;
					break;
				case CALTOTAL:
					cv.getJtfAllPrice().setText(priceFormat.format(cVO.getPrice()) + " ��");
					cv.getJtfAllCount().setText(priceFormat.format(cVO.getSaleCnt()) + " ��");
					cVO = null;
					break;
				}// end switch
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(StaticCla.mv, "�����ڴ� ���񽺰� ��Ȱ���� �ʽ��ϴ�. �˼��մϴ�.");
			e.printStackTrace();
		} // end catch
	}// calculateTotal

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cv.getJbtClose()) {
			cv.dispose();
		} // end if

	}// actionPerformed

}// class
