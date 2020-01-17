package admin.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import admin.dao.AdDAO;
import admin.run.StaticCla;
import admin.view.AdGoodsListPanelView;
import admin.view.AdGoodsMainView;
import admin.view.AdGoodsNotPanelView;
import admin.vo.SelectAllGoodsVO;
import admin.vo.SelectBestFiveGoodsVO;
import admin.vo.SelectDetailChkVO;
import admin.vo.SelectFiveCheckVO;
import admin.vo.SelectGoodsCheckVO;

public class AdGoodsMainEvt extends MouseAdapter implements ActionListener {

	private int tableHeight;
	private boolean btnFlag;
	private JButton originBtn;
	////////////////////// 옷 종류 //////////////////////
	private String clothesType;//
	private String brandCheck;// 브랜드
	private String detailType;// 상세

	private String selectCombo;// 콤보 선택
	private String searchData;// jtf 입력 값

	private JPanel jpGoods;
	private JPanel jpBestGoods;

	private JTable jtDetail;
	private DefaultTableModel dtmDetail;

	//////////// 2019 -09-22
	private JButton btnBest, btnOuter, btnTop, btnBottom, btnDress, btnBag, btnShoes, btnHeadWear, btnAcc;
	public static AdGoodsMainView agmv;

	public AdGoodsMainEvt(AdGoodsMainView agmv) {
		this.agmv = agmv;
		btnBest = agmv.getBtnBest();
		btnOuter = agmv.getBtnOuter();
		btnTop = agmv.getBtnTop();
		btnBottom = agmv.getBtnBottom();
		btnDress = agmv.getBtnDress();
		///////////// 2019 -09-22/
		jpBestGoods = agmv.getJpBestGoods();
		btnBag = agmv.getBtnBag();
		btnShoes = agmv.getBtnShoes();
		btnHeadWear = agmv.getBtnHeadWear();
		btnAcc = agmv.getBtnAcc();

		btnFlag = true;
		clothesType = "";
		detailType = "";
		selectCombo = "";
		searchData = "";
		brandCheck = agmv.getJlChannel().getText().trim();
		selectAllGoodsList();
		StaticCla.mv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				StaticCla.mv.dispose();// 뷰 종료
			}// windowClosing
		});// addWindowListener
	}// AdGoodsMainEvt

	private void selectAllGoodsList() {
		int goodsNum = 0;// 상품 갯수 세기
		SelectGoodsCheckVO sgck = null;
		JScrollPane jspGoods = agmv.getJspGoods();
		jpGoods = agmv.getJpGoods();
		jspGoods.getVerticalScrollBar().setValue(0);
		jspGoods.setVisible(false);
		jpGoods.removeAll();
		setSearchLabel();

		AdDAO aDAO = AdDAO.getInstance();
		try {
			sgck = new SelectGoodsCheckVO(brandCheck, clothesType, detailType, searchData.toUpperCase(), selectCombo);
			List<SelectAllGoodsVO> list = aDAO.selectAllGoods(sgck);

			if (list.isEmpty()) {
				agmv.getJtfSearch().setText("");
				searchData = "";
				jpGoodsReset();
				jpGoods.add(new AdGoodsNotPanelView(agmv));
				jpGoods.setPreferredSize(new Dimension(1000, 390));
				jspGoods.setVisible(true);
				agmv.setVisible(true);
				return;
			} // end if
			String tempInven = "";
			SelectAllGoodsVO sagVO = null;
			for (int i = 0; i < list.size(); i++) {
				sagVO = list.get(i);
				if (sagVO.getG_inventory() <= 5) {
					tempInven = " (※ 품절임박 ※)";
				} else {
					tempInven = "";
				} // end else
				jpGoods.add(new AdGoodsListPanelView(sagVO.getG_img(),
						sagVO.getG_name() + "(" + sagVO.getG_code() + ")", sagVO.getB_name(), sagVO.getG_price(),
						sagVO.getG_salenum() + tempInven, sagVO.getG_score()));
				goodsNum++;
			} // end for
			selectBestFive(goodsNum);
			int plusCnt = 0;
			if (goodsNum % 6 > 0) {// 6의배수가 아닐 때
				plusCnt = 1;
			} // end if
			jpGoods.setPreferredSize(new Dimension(1000, 285 * ((goodsNum / 6) + plusCnt)));
			agmv.setVisible(true);
			jspGoods.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(agmv, "서비스가 원활하지 않습니다. 죄송합니다.");
			e.printStackTrace();
		} // end catch
	}// selectAllGoodsList

	private void selectBestFive(int goodsNum) {
		int tempNum = 0;
		SelectFiveCheckVO sfchkVO = null;
		jpBestGoods = agmv.getJpBestGoods();
		jpBestGoods.removeAll();

		if (goodsNum < 6) {
			tempNum = goodsNum;
		} else {
			tempNum = 6;
		}
		AdDAO aDAO = AdDAO.getInstance();

		try {
			sfchkVO = new SelectFiveCheckVO(brandCheck, clothesType, detailType, searchData.toUpperCase(), selectCombo,
					tempNum);
			List<SelectBestFiveGoodsVO> fiveBestList = aDAO.selectFiveGoods(sfchkVO);
			if (fiveBestList.isEmpty()) {
				jpGoodsReset();
				return;
			} // end if

			String tempInven = "";
			SelectBestFiveGoodsVO sbfgVO = null;
			for (int i = 0; i < fiveBestList.size(); i++) {
				sbfgVO = fiveBestList.get(i);
				if (sbfgVO.getG_inventory() <= 5) {
					tempInven = " (※ 품절임박 ※)";
				} else {
					tempInven = "";
				}
				jpBestGoods.add(
						new AdGoodsListPanelView(sbfgVO.getG_img(), sbfgVO.getG_name() + "(" + sbfgVO.getG_code() + ")",
								sbfgVO.getB_name(), sbfgVO.getG_price(), sbfgVO.getG_salenum() + tempInven, 10));
			} // end for
			agmv.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// selectBestFive

	private void selectDetailType(JButton chkBtn) {// 대분류에 대한 상세 분류 조회
		dtmDetail.setRowCount(0);
		dtmDetail.setNumRows(0);
		List<String> detailTypeList = null;
		String[][] detailTemp = null;
		SelectDetailChkVO sdcVO = null;

		AdDAO aDAO = AdDAO.getInstance();
		try {
			int row = 0;
			int col = 0;
			int rowCnt = 0;

			if (originBtn == null || originBtn == btnBest) {// best 클릭
				detailTypeList = aDAO.selectBestDetail(brandCheck);
			} else {// 그 외 버튼 클릭
				sdcVO = new SelectDetailChkVO(clothesType, brandCheck);
				detailTypeList = aDAO.selectDeType(sdcVO);
			}
///////////////// 2019-09-22
			if (detailTypeList.isEmpty()) {
				JOptionPane.showMessageDialog(agmv, "조회결과 없음");
			}

			if (detailTypeList.size() % 2 == 0) {// 갯수가 짝수라면
				detailTemp = new String[detailTypeList.size() / 2][2];
			} else {// 홀수라면
				detailTemp = new String[(detailTypeList.size() / 2) + 1][2];
				detailTemp[(detailTypeList.size() / 2)][1] = "";
			}
			for (int i = 0; i < detailTypeList.size(); i++) {
				detailTemp[row][col] = detailTypeList.get(i);
				if (!(i % 2 == 0)) {// 홀수
					row++;
					col = 0;
				} else {// 짝수
					col++;
				} // end if
			} // end for
			for (int i = 0; i < detailTemp.length; i++) {
				rowCnt++;
				dtmDetail.addRow(detailTemp[i]);
			} // end for
			jtDetail.setModel(dtmDetail);
			tableHeight = rowCnt * 30;
			if (chkBtn != agmv.getBtnType()) {
				btnPosition();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(agmv, "서비스가 원활하지 않습니다. 죄송합니다.");
			e.printStackTrace();
		} // end catch
	}// selectDetailType

	private void btnPosition() {
		//////// 2019-09-22
		JButton[] btnList = { btnBest, btnOuter, btnTop, btnBottom, btnDress, btnBag, btnShoes, btnHeadWear, btnAcc };
		jpGoodsReset();
		int temp = 0;
		for (int i = 0; i < btnList.length; i++) {
			if (originBtn == btnList[i]) {
				temp = i;
			} // end if
		} // end for

		if (btnFlag) {// 토글 또는 전 과 다른 버튼 클릭 시
			//////////////// 2019- 09-22
			jtDetail.setBounds(0, originBtn.getY() + 40, 200, tableHeight);
			for (int j = temp + 1; j < btnList.length; j++) {
				btnList[j].setBounds(0, btnList[j].getY() + tableHeight, originBtn.getWidth(), originBtn.getHeight());
			} // end for
			agmv.getJpCloBtn().add(jtDetail);
			jtDetail.setBackground(new Color(0xFFFFFF));
			agmv.setVisible(true);
			//////////// 2019-09-22
			if (originBtn == btnAcc) {
				agmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70 + tableHeight);
				agmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() + tableHeight - 50);
			} else {
				agmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				agmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
			}
		} else {// 토글
			for (int j = temp + 1; j < btnList.length; j++) {
				btnList[j].setBounds(0, btnList[j].getY() - tableHeight, originBtn.getWidth(), originBtn.getHeight());
			} // end for
				////////// 2019-09-22
			agmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
			agmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
			dtmDetail.setRowCount(0);
			jtDetail.setBounds(0, 0, 0, 0);
			jtDetail.removeAll();
			jtDetail.setBackground(new Color(0xEEEEEE));
		} // end if
			/////// 2019-09-22
		jtDetail.setVisible(true);
	}// btnPosition

	private void btnClickReset(JButton newBtn) {
		if (originBtn == null) {
			originBtn = new JButton();
			originBtn.setSize(btnBest.getWidth(), btnBest.getHeight());
		}

		if (originBtn == newBtn) {// 같은 버튼을 눌렀을 때
			btnFlag = !btnFlag;
		} else {// 전 과 다른 버튼 클릭 btnPosition();
			if (btnFlag == true) {// 테이블이 켜져있을 때
				int temp = 0;
				// 2019-09-22
				JButton[] btnList = { btnBest, btnOuter, btnTop, btnBottom, btnDress, btnBag, btnShoes, btnHeadWear,
						btnAcc };
				for (int i = 0; i < btnList.length; i++) {
					if (originBtn == btnList[i]) {
						temp = i;
					} // end if
				} // end for
					////////////////////////////// 2019-09-22
				for (int j = temp + 1; j < btnList.length; j++) {
					btnList[j].setBounds(0, btnList[j].getY() - tableHeight, originBtn.getWidth(),
							originBtn.getHeight());
					dtmDetail.setRowCount(0);
					jtDetail.setBounds(0, 0, 0, 0);
					jtDetail.removeAll();
					jtDetail.setBackground(new Color(0xEEEEEE));
				} // end for
					/////// 2019-09-22
				agmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				agmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
				jtDetail.setVisible(true);
			} // end if
			btnFlag = true;
		} // end if
		if (newBtn != agmv.getBtnType()) {
			originBtn = newBtn;
			detailType = "";
		}
		selectDetailType(originBtn);
	}// btnClickReset

	private void jpGoodsReset() {
		jpBestGoods.setVisible(false);
		jpBestGoods.removeAll();
		jpBestGoods.setVisible(true);
		jpGoods.setVisible(false);
		jpGoods.removeAll();
		jpGoods.setVisible(true);
	}// jpGoodsReset

	private void setSearchLabel() {
		JLabel jlSelect = agmv.getJlSelect();
		StringBuilder selectSearch = new StringBuilder();
		selectSearch.append(brandCheck);
		if (!clothesType.isEmpty()) {
			selectSearch.append(" > " + clothesType);
		}
		if (!detailType.isEmpty()) {
			selectSearch.append(" > " + detailType);
		}
		if (!searchData.isEmpty()) {
			selectSearch.append(" > " + selectCombo).append(" > \"" + searchData.toUpperCase() + "\"");
		}
		jlSelect.setText(selectSearch.toString());
	}// setLabel

///////////////2019-09-22
	private void searchBrandList() {
		DefaultTableModel dtmBrandKind = agmv.getDtmBrandKind();
		dtmBrandKind.setRowCount(0);
		String inputBrand = agmv.getJtfSearchBrand().getText().trim();
		String[] brandTemp = null;
		AdDAO aDAO = AdDAO.getInstance();

		try {
			List<String> outputData = aDAO.selectBrandKind(inputBrand);
			if (outputData.isEmpty()) {
				JOptionPane.showMessageDialog(agmv, "조회 한 결과가 없습니다.");
				agmv.getJtfSearchBrand().getText();
				agmv.getJtfSearchBrand().setText("");
				agmv.getJtfSearchBrand().requestFocus();
				return;
			} // end if

			for (int i = 0; i < outputData.size(); i++) {
				brandTemp = new String[1];
				brandTemp[0] = outputData.get(i);
				dtmBrandKind.addRow(brandTemp);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// searchBrandList

	@Override
	public void actionPerformed(ActionEvent ae) {
		jtDetail = agmv.getJtDetail();
		dtmDetail = agmv.getDtmDetail();

		if (ae.getSource() == btnBest) {
			clothesType = btnBest.getText().toUpperCase().trim();
			setSearchLabel();
			btnClickReset(btnBest);
			selectAllGoodsList();
		}

		if (ae.getSource() == btnOuter) {
			clothesType = btnOuter.getText().toUpperCase().trim();
			btnClickReset(btnOuter);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnTop) {
			clothesType = btnTop.getText().toUpperCase().trim();
			btnClickReset(btnTop);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnBottom) {
			clothesType = btnBottom.getText().toUpperCase().trim();
			btnClickReset(btnBottom);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnDress) {
			clothesType = btnDress.getText().toUpperCase().trim();
			btnClickReset(btnDress);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnBag) {
			clothesType = btnBag.getText().toUpperCase().trim();
			btnClickReset(btnBag);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnShoes) {
			clothesType = btnShoes.getText().toUpperCase().trim();
			btnClickReset(btnShoes);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnHeadWear) {
			clothesType = btnHeadWear.getText().toUpperCase().trim();
			btnClickReset(btnHeadWear);
			selectAllGoodsList();
		}
		if (ae.getSource() == btnAcc) {
			clothesType = btnAcc.getText().toUpperCase().trim();
			btnClickReset(btnAcc);
			selectAllGoodsList();
		}

		if (ae.getSource() == agmv.getBtnType()) {// 전체 -> 브랜드 -> 보세
			JButton jbtType = agmv.getBtnType();
			JLabel jlChannel = agmv.getJlChannel();
			DefaultComboBoxModel<String> dcbmSearch = agmv.getDcbmSearch();

			switch (brandCheck) {
			case "SOHO":
				brandCheck = "ALL";
				jlChannel.setText(brandCheck);
				jbtType.setText("BRAND로 보기");
				jpGoodsReset();
				dcbmSearch.removeAllElements();
				dcbmSearch.addElement("상품명");
				dcbmSearch.addElement("브랜드명");
				break;
			case "ALL":
				brandCheck = "BRAND";
				jlChannel.setText(brandCheck);
				jbtType.setText("SOHO로 보기");
				jpGoodsReset();
				dcbmSearch.removeAllElements();
				dcbmSearch.addElement("상품명");
				dcbmSearch.addElement("브랜드명");
				break;
			case "BRAND":
				brandCheck = "SOHO";
				jlChannel.setText(brandCheck);
				jbtType.setText("ALL로 보기");
				jpGoodsReset();
				dcbmSearch.removeAllElements();
				dcbmSearch.addElement("상품명");
				break;
			}// end switch

			if (btnFlag == true) {
				int temp = 0;
				// 2019-09-22
				JButton[] btnList = { btnBest, btnOuter, btnTop, btnBottom, btnDress, btnBag, btnShoes, btnHeadWear,
						btnAcc };
				for (int i = 0; i < btnList.length; i++) {
					if (originBtn == btnList[i]) {
						temp = i;
					} // end if
				} // end for

				////////////////// 2019-09-22
				if (originBtn != null) {

					for (int j = temp + 1; j < btnList.length; j++) {
						btnList[j].setBounds(0, btnList[j].getY() - tableHeight, originBtn.getWidth(),
								originBtn.getHeight());
						jtDetail.setVisible(false);
						dtmDetail.setRowCount(0);
						jtDetail.removeAll();
						jtDetail.setBounds(0, 0, 0, 0);
						jtDetail.setBackground(new Color(0xEEEEEE));
					} // end for
				}
				///////////////////// 2019- 09-22
				agmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				agmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);

				jtDetail.setVisible(true);
				btnFlag = false;
				selectDetailType(jbtType);
			}
			selectAllGoodsList();
		} // end if

		if (ae.getSource() == agmv.getBtnSearch() || ae.getSource() == agmv.getJtfSearch()) {
			if (!agmv.getJtfSearch().getText().isEmpty()) {
				jpGoodsReset();
				searchData = "";
				selectCombo = agmv.getJcbSearch().getSelectedItem().toString().trim();// 콤보박스 선택
				searchData = agmv.getJtfSearch().getText().trim();
				selectAllGoodsList();
			} else {
				jpGoodsReset();
				searchData = "";
				selectAllGoodsList();
			} // end if
		} // end if

		/////////// 2019-09-22
		if (ae.getSource() == agmv.getJtfSearchBrand() || ae.getSource() == agmv.getBtnSearchBrand()) {
			searchBrandList();
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == agmv.getJtDetail()) {// list 클릭 시
			String detailSub = jtDetail.getValueAt(jtDetail.getSelectedRow(), jtDetail.getSelectedColumn()).toString()
					.trim();
			if (detailSub.isEmpty()) {
				detailType = detailSub;
			} else {
				detailType = detailSub.substring(0, detailSub.indexOf("(")).trim();
			}
			jpGoodsReset();// 메인 패널 초기화
			selectAllGoodsList();// 메인 조회
		} // end if
			/////////////////// 2019-09-22
		if (me.getSource() == agmv.getJtBrandKind()) {
			JTable jtBrandKind = agmv.getJtBrandKind();
			clothesType = "";
			detailType = "";
			selectCombo = "브랜드명";
			searchData = jtBrandKind.getValueAt(jtBrandKind.getSelectedRow(), jtBrandKind.getSelectedColumn())
					.toString().toUpperCase().trim();
			brandCheck = "BRAND";
			agmv.getJlChannel().setText(brandCheck);
			agmv.getBtnType().setText("SOHO로 가기");
			jpGoodsReset();
			agmv.getDcbmSearch().removeAllElements();
			agmv.getDcbmSearch().addElement("상품명");
			agmv.getDcbmSearch().addElement("브랜드명");
			selectAllGoodsList();
		} // end if

		if (me.getSource() == agmv.getJttpCate()) {
			JTextField jtfSearchBrand = agmv.getJtfSearchBrand();
			JTabbedPane jtpTemp = (JTabbedPane) me.getSource();
			if (jtpTemp.getSelectedIndex() == 1) {
				jtfSearchBrand.getText();
				jtfSearchBrand.setText("");
				jtfSearchBrand.requestFocus();
				searchBrandList();
			} // end if
		} // end if

	}// mouseClicked

	////////////////////////////// 2019-09-22
	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == btnBest) {
			btnBest.setForeground(Color.RED);
		}
		if (me.getSource() == btnOuter) {
			btnOuter.setForeground(Color.RED);
		}
		if (me.getSource() == btnTop) {
			btnTop.setForeground(Color.RED);
		}
		if (me.getSource() == btnBottom) {
			btnBottom.setForeground(Color.RED);
		}
		if (me.getSource() == btnDress) {
			btnDress.setForeground(Color.RED);
		}
		if (me.getSource() == btnAcc) {
			btnAcc.setForeground(Color.RED);
		}
		if (me.getSource() == btnBag) {
			btnBag.setForeground(Color.RED);
		}
		if (me.getSource() == btnHeadWear) {
			btnHeadWear.setForeground(Color.RED);
		}
		if (me.getSource() == btnShoes) {
			btnShoes.setForeground(Color.RED);
		}
	}// mouseEntered

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == btnBest) {
			btnBest.setForeground(Color.white);
		}
		if (me.getSource() == btnOuter) {
			btnOuter.setForeground(Color.white);
		}
		if (me.getSource() == btnTop) {
			btnTop.setForeground(Color.white);
		}
		if (me.getSource() == btnBottom) {
			btnBottom.setForeground(Color.white);
		}
		if (me.getSource() == btnDress) {
			btnDress.setForeground(Color.white);
		}
		if (me.getSource() == btnAcc) {
			btnAcc.setForeground(Color.white);
		}
		if (me.getSource() == btnBag) {
			btnBag.setForeground(Color.white);
		}
		if (me.getSource() == btnHeadWear) {
			btnHeadWear.setForeground(Color.white);
		}
		if (me.getSource() == btnShoes) {
			btnShoes.setForeground(Color.white);
		}
	}// mouseExited

}// class
