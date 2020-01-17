package user.controller.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import user.dao.UserDAO;
import user.helper.RecentThread;
import user.run.PhotoUploading;
import user.view.content.UserGoodsListPanelView;
import user.view.content.UserGoodsMainView;
import user.view.content.UserGoodsNotPanelView;
import user.view.content.UserMyPageView;
import user.view.login.ClientLoginView;
import user.view.login.LoginDialogView;
import user.vo.content.SelectAllGoodsVO;
import user.vo.content.SelectBestFiveGoodsVO;
import user.vo.content.SelectDetailChkVO;
import user.vo.content.SelectFiveCheckVO;
import user.vo.content.SelectGoodsCheckVO;

public class UserGoodsMainEvt extends MouseAdapter implements ActionListener {

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
	public static RecentThread rt;
	public static UserGoodsMainView ugmv;

	public UserGoodsMainEvt(UserGoodsMainView ugmv) {
		this.ugmv = ugmv;
		btnBest = ugmv.getBtnBest();
		btnOuter = ugmv.getBtnOuter();
		btnTop = ugmv.getBtnTop();
		btnBottom = ugmv.getBtnBottom();
		btnDress = ugmv.getBtnDress();
		///////////// 2019 -09-22/
		jpBestGoods = ugmv.getJpBestGoods();
		btnBag = ugmv.getBtnBag();
		btnShoes = ugmv.getBtnShoes();
		btnHeadWear = ugmv.getBtnHeadWear();
		btnAcc = ugmv.getBtnAcc();

		btnFlag = true;
		clothesType = "";
		detailType = "";
		selectCombo = "";
		searchData = "";
		brandCheck = ugmv.getJlChannel().getText().trim();
		rt = new RecentThread();
		rt.start();
		selectAllGoodsList();
		ugmv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
					deleteRecentTable();
					rt.setStop(true);// thread 종료
					ugmv.getMls().setStop(true);
					ugmv.dispose();// 뷰 종료
				} else {// 비회원
					deleteNmemRecent();
					deleteNonMember();
					rt.setStop(true);// thread 종료
					ugmv.dispose();// 뷰 종료
				} // end else
			}// windowClosing

			@Override
			public void windowClosed(WindowEvent e) {
				if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
					ugmv.getMls().setStop(true);
					try {
						if (ugmv.getMls().getDos() != null) {
							ugmv.getMls().getDos().close();
						}

						if (ugmv.getMls().getClient() != null) {
							ugmv.getMls().getClient().close();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} // end if
			}// windowClosed

		});// addWindowListener
	}// UserMainViewEvt

	private void selectAllGoodsList() {
		int goodsNum = 0;// 상품 갯수 세기
		SelectGoodsCheckVO sgck = null;
		JScrollPane jspGoods = ugmv.getJspGoods();
		jpGoods = ugmv.getJpGoods();
		jspGoods.getVerticalScrollBar().setValue(0);
		jspGoods.setVisible(false);
		jpGoods.removeAll();
		setSearchLabel();

		UserDAO uDAO = UserDAO.getInstance();
		try {
			sgck = new SelectGoodsCheckVO(brandCheck, clothesType, detailType, searchData.toUpperCase(), selectCombo);
			List<SelectAllGoodsVO> list = uDAO.selectAllGoods(sgck);
			if (list.isEmpty()) {
				ugmv.getJtfSearch().setText("");
				searchData = "";
				jpGoodsReset();
				jpGoods.add(new UserGoodsNotPanelView(ugmv));
				jpGoods.setPreferredSize(new Dimension(1000, 390));
				jspGoods.setVisible(true);
				ugmv.setVisible(true);
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
				jpGoods.add(new UserGoodsListPanelView(sagVO.getG_img(),
						sagVO.getG_name() + "(" + sagVO.getG_code() + ")", sagVO.getB_name(), sagVO.getG_price(),
						sagVO.getG_salenum() + tempInven, sagVO.getG_score()));
				goodsNum++;
			} // end for
			selectBestFive(goodsNum);
			int plusCnt = 0;
			if (goodsNum % 6 > 0) {// 6의배수가 아닐 때
				plusCnt = 1;
			}
			jpGoods.setPreferredSize(new Dimension(1000, 285 * ((goodsNum / 6) + plusCnt)));
			ugmv.setVisible(true);
			jspGoods.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ugmv, "서비스가 원활하지 않습니다. 죄송합니다.");
			e.printStackTrace();
		} // end catch
	}// selectAllGoodsList

	private void selectBestFive(int goodsNum) {
		int tempNum = 0;
		SelectFiveCheckVO sfchkVO = null;
		jpBestGoods = ugmv.getJpBestGoods();
		jpBestGoods.removeAll();

		if (goodsNum < 6) {
			tempNum = goodsNum;
		} else {
			tempNum = 6;
		}
		UserDAO uDAO = UserDAO.getInstance();

		try {
			sfchkVO = new SelectFiveCheckVO(brandCheck, clothesType, detailType, searchData.toUpperCase(), selectCombo,
					tempNum);
			List<SelectBestFiveGoodsVO> fiveBestList = uDAO.selectFiveGoods(sfchkVO);
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
				jpBestGoods.add(new UserGoodsListPanelView(sbfgVO.getG_img(),
						sbfgVO.getG_name() + "(" + sbfgVO.getG_code() + ")", sbfgVO.getB_name(), sbfgVO.getG_price(),
						sbfgVO.getG_salenum() + tempInven, 10));
			} // end for
			ugmv.setVisible(true);
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

		UserDAO uDAO = UserDAO.getInstance();
		try {
			int row = 0;
			int col = 0;
			int rowCnt = 0;

			if (originBtn == null || originBtn == btnBest) {// best 클릭
				detailTypeList = uDAO.selectBestDetail(brandCheck);
			} else {// 그 외 버튼 클릭
				sdcVO = new SelectDetailChkVO(clothesType, brandCheck);
				detailTypeList = uDAO.selectDeType(sdcVO);
			}
///////////////// 2019-09-22
			if (detailTypeList.isEmpty()) {
				JOptionPane.showMessageDialog(ugmv, "조회결과 없음");
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
			if (chkBtn != ugmv.getBtnType()) {
				btnPosition();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ugmv, "서비스가 원활하지 않습니다. 죄송합니다.");
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
			ugmv.getJpCloBtn().add(jtDetail);
			jtDetail.setBackground(new Color(0xFFFFFF));
			ugmv.setVisible(true);
			//////////// 2019-09-22
			if (originBtn == btnAcc) {
				ugmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70 + tableHeight);
				ugmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() + tableHeight - 50);
			} else {
				ugmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				ugmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
			}
		} else {// 토글
			for (int j = temp + 1; j < btnList.length; j++) {
				btnList[j].setBounds(0, btnList[j].getY() - tableHeight, originBtn.getWidth(), originBtn.getHeight());
			} // end for
				////////// 2019-09-22
			ugmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
			ugmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
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
				ugmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				ugmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);
				jtDetail.setVisible(true);
			} // end if
			btnFlag = true;
		} // end if
		if (newBtn != ugmv.getBtnType()) {
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
		JLabel jlSelect = ugmv.getJlSelect();
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
		DefaultTableModel dtmBrandKind = ugmv.getDtmBrandKind();
		dtmBrandKind.setRowCount(0);
		String inputBrand = ugmv.getJtfSearchBrand().getText().trim();
		String[] brandTemp = null;

		UserDAO uDAO = UserDAO.getInstance();

		try {
			List<String> outputData = uDAO.selectBrandKind(inputBrand);
			if (outputData.isEmpty()) {
				JOptionPane.showMessageDialog(ugmv, "조회 한 결과가 없습니다.");
				ugmv.getJtfSearchBrand().getText();
				ugmv.getJtfSearchBrand().setText("");
				ugmv.getJtfSearchBrand().requestFocus();
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
//////////////////////////////

	private void deleteRecentTable() {
		UserDAO uDAO = UserDAO.getInstance();

		try {
			uDAO.deleteRecent();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// deleteRecent

	private void deleteNonMember() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			uDAO.deleteNonMem();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// deleteNonMember

	private void deleteNmemRecent() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			uDAO.deleteNmemRe();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// deleteNonMember

	private void photoUploading() {
		PhotoUploading pu = new PhotoUploading();
		try {
			pu.sendFiles();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {// 카테고리 버튼 클릭 시 회원 비회원 나누기
		jtDetail = ugmv.getJtDetail();
		dtmDetail = ugmv.getDtmDetail();
		JScrollPane jspRecent = ugmv.getJspRecent();
		JTabbedPane jttpCate = ugmv.getJttpCate();

		if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
//			if (ae.getSource() == ugmv.getBtnCardUpLoad()) {
//				new UserCardUploadView(ugmv);
//			} // end if
			if (ae.getSource() == ugmv.getBtnOrderNe()) {
				new UserMyPageView(UserGoodsMainView.id);
			} // end if

		} // end if

		if (ae.getSource() == ugmv.getBtnLogout()) {// 로그아웃 - 회원 ///// 로그인 - 비회원
			if (UserGoodsMainView.id != null && !UserGoodsMainView.id.isEmpty()) {// 회원일때만
				switch (JOptionPane.showConfirmDialog(ugmv, "로그아웃 하시겠습니까?")) {
				case JOptionPane.YES_OPTION:
					try {
						deleteRecentTable();
						rt.setStop(true);// thread 종료
						ugmv.getMls().setStop(true);
						if (ugmv.getMls().getDos() != null) {
							ugmv.getMls().getDos().close();
						}
						if (ugmv.getMls().getClient() != null) {
							ugmv.getMls().getClient().close();
						}
						ugmv.dispose();// 뷰 종료
						new ClientLoginView();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				default:
					break;
				}// end switch
			} else {// 비회원
				switch (JOptionPane.showConfirmDialog(ugmv, "로그인 하시겠습니까?")) {
				case JOptionPane.YES_OPTION:
					deleteNmemRecent();
					deleteNonMember();
					rt.setStop(true);// thread 종료
					new LoginDialogView(ugmv, rt);
					break;
				default:
					break;
				}// end switch
			} // end else
		} // end if

		if (ae.getSource() == btnBest) {
			photoUploading();
			clothesType = btnBest.getText().toUpperCase().trim();
			setSearchLabel();
			btnClickReset(btnBest);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}

		if (ae.getSource() == btnOuter) {
			photoUploading();
			clothesType = btnOuter.getText().toUpperCase().trim();
			btnClickReset(btnOuter);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnTop) {
			photoUploading();
			clothesType = btnTop.getText().toUpperCase().trim();
			btnClickReset(btnTop);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnBottom) {
			photoUploading();
			clothesType = btnBottom.getText().toUpperCase().trim();
			btnClickReset(btnBottom);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnDress) {
			photoUploading();
			clothesType = btnDress.getText().toUpperCase().trim();
			btnClickReset(btnDress);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnBag) {
			photoUploading();
			clothesType = btnBag.getText().toUpperCase().trim();
			btnClickReset(btnBag);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnShoes) {
			photoUploading();
			clothesType = btnShoes.getText().toUpperCase().trim();
			btnClickReset(btnShoes);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnHeadWear) {
			photoUploading();
			clothesType = btnHeadWear.getText().toUpperCase().trim();
			btnClickReset(btnHeadWear);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}
		if (ae.getSource() == btnAcc) {
			photoUploading();
			clothesType = btnAcc.getText().toUpperCase().trim();
			btnClickReset(btnAcc);
			selectAllGoodsList();
			jspRecent.setBounds(jttpCate.getX(), jttpCate.getY() + jttpCate.getHeight() + 10, jspRecent.getWidth(),
					jspRecent.getHeight());
		}

		if (ae.getSource() == ugmv.getBtnType()) {// 전체 -> 브랜드 -> 보세
			photoUploading();
			JButton jbtType = ugmv.getBtnType();
			JLabel jlChannel = ugmv.getJlChannel();
			DefaultComboBoxModel<String> dcbmSearch = ugmv.getDcbmSearch();

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
				ugmv.getJttpCate().setBounds(10, 300, 200, btnAcc.getY() + 70);
				ugmv.getJspBrandKind().setBounds(10, 60, 180, btnAcc.getY() - 50);

				jtDetail.setVisible(true);
				btnFlag = false;
				selectDetailType(jbtType);
			}
			selectAllGoodsList();
		} // end if

		if (ae.getSource() == ugmv.getBtnSearch() || ae.getSource() == ugmv.getJtfSearch()) {
			photoUploading();
			if (!ugmv.getJtfSearch().getText().isEmpty()) {
				jpGoodsReset();
				searchData = "";
				selectCombo = ugmv.getJcbSearch().getSelectedItem().toString().trim();// 콤보박스 선택
				searchData = ugmv.getJtfSearch().getText().trim();
				selectAllGoodsList();
			} else {
				jpGoodsReset();
				searchData = "";
				selectAllGoodsList();
			} // end if
		} // end if

		/////////// 2019-09-22
		if (ae.getSource() == ugmv.getJtfSearchBrand() || ae.getSource() == ugmv.getBtnSearchBrand()) {
			photoUploading();
			searchBrandList();
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == ugmv.getJtDetail()) {// list 클릭 시
			photoUploading();
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
		if (me.getSource() == ugmv.getJtBrandKind()) {//////// 브랜드 종류
			photoUploading();
			JTable jtBrandKind = ugmv.getJtBrandKind();
			clothesType = "";
			detailType = "";
			selectCombo = "브랜드명";
			searchData = jtBrandKind.getValueAt(jtBrandKind.getSelectedRow(), jtBrandKind.getSelectedColumn())
					.toString().toUpperCase().trim();
			brandCheck = "BRAND";
			ugmv.getJlChannel().setText(brandCheck);
			ugmv.getBtnType().setText("SOHO로 가기");
			jpGoodsReset();
			ugmv.getDcbmSearch().removeAllElements();
			ugmv.getDcbmSearch().addElement("상품명");
			ugmv.getDcbmSearch().addElement("브랜드명");
			selectAllGoodsList();
		} // end if

		if (me.getSource() == ugmv.getJttpCate()) {
			JTextField jtfSearchBrand = ugmv.getJtfSearchBrand();
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

		if (me.getSource() == ugmv.getBtnLogout()) {
			ugmv.getBtnLogout().setForeground(Color.RED);
			ugmv.getBtnLogout().setBorder(new LineBorder(Color.red));
		}
//		if (me.getSource() == ugmv.getBtnCardUpLoad()) {
//			ugmv.getBtnCardUpLoad().setForeground(Color.RED);
//			ugmv.getBtnCardUpLoad().setBorder(new LineBorder(Color.red));
//		}
		if (me.getSource() == ugmv.getBtnType()) {
			ugmv.getBtnType().setForeground(Color.RED);
			ugmv.getBtnType().setBackground(new Color(0x3F4040));
		}
		if (me.getSource() == ugmv.getBtnSearch()) {
			ugmv.getBtnSearch().setForeground(Color.RED);
			ugmv.getBtnSearch().setBackground(new Color(0x3F4040));
		}

		if (me.getSource() == ugmv.getBtnOrderNe()) {
			ugmv.getBtnOrderNe().setForeground(Color.RED);
			ugmv.getBtnOrderNe().setBorder(new LineBorder(Color.red));
		} // end if

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

		if (me.getSource() == ugmv.getBtnLogout()) {
			ugmv.getBtnLogout().setForeground(Color.white);
			ugmv.getBtnLogout().setBorder(new LineBorder(new Color(0x3F4040)));
		}
//		if (me.getSource() == ugmv.getBtnCardUpLoad()) {
//			ugmv.getBtnCardUpLoad().setForeground(Color.white);
//			ugmv.getBtnCardUpLoad().setBorder(new LineBorder(new Color(0x3F4040)));
//		}
		if (me.getSource() == ugmv.getBtnType()) {
			ugmv.getBtnType().setForeground(new Color(0x3F4040));
			ugmv.getBtnType().setBackground(Color.white);
		}
		if (me.getSource() == ugmv.getBtnSearch()) {
			ugmv.getBtnSearch().setBackground(new Color(0x3F4040));
			ugmv.getBtnSearch().setForeground(Color.white);
		}
		if (me.getSource() == ugmv.getBtnOrderNe()) {
			ugmv.getBtnOrderNe().setForeground(Color.white);
			ugmv.getBtnOrderNe().setBorder(new LineBorder(new Color(0x3F4040)));
		} // end if

	}// mouseExited

}// class
