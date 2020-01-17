package user.view.content;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import user.controller.content.UserGoodsMainEvt;
import user.controller.content.UserMyPageEvt;
import user.view.login.ClientLoginView;

@SuppressWarnings("serial")
public class UserMyPageView extends JDialog {

	private JLabel jlMyPage;
	private JTextArea jtaName;
	private JButton jbtOrderList, jbtMyData, jbtWithdrawal, jbtOk, jbtJJim;
	// 카드등록, 찜하기, 주문내역, 내정보변경

	public UserMyPageView(String id) {
		super(UserGoodsMainEvt.ugmv, "마이페이지", false);

		Font mFont = new Font("맑은고딕", Font.BOLD, 15);
		Font jtFont = new Font("맑은고딕", Font.BOLD, 13);
		jlMyPage = new JLabel("▒  마이페이지  ▒");
		jlMyPage.setFont(mFont);
		jtaName = new JTextArea("\n\t♡♡'" + id + "'회원님 환영합니다. ♡♡\n");
		jtaName.setFont(jtFont);
		jtaName.setEditable(false);

		jbtOrderList = new JButton("주문내역");
		jbtOrderList.setFont(mFont);
		jbtOrderList.setForeground(Color.white);
		jbtOrderList.setBackground(new Color(0x043424));

		jbtMyData = new JButton("내 정보 변경");
		jbtMyData.setFont(mFont);
		jbtMyData.setForeground(Color.white);
		jbtMyData.setBackground(new Color(0x043424));

		jbtWithdrawal = new JButton("카드등록");
		jbtWithdrawal.setFont(mFont);
		jbtWithdrawal.setForeground(Color.white);
		jbtWithdrawal.setBackground(new Color(0x043424));

		jbtJJim = new JButton("찜한 상품");
		jbtJJim.setFont(mFont);
		jbtJJim.setForeground(Color.white);
		jbtJJim.setBackground(new Color(0x043424));

		jbtOk = new JButton("닫기");
		jbtOk.setForeground(Color.white);
		jbtOk.setBackground(new Color(0x043424));

		// 이미지 // 광고
		ImageIcon iImage = new ImageIcon(ClientLoginView.USER_FILE_PATH + "/mypagebanner.jpg");
		JLabel jtaImage = new JLabel(iImage);
		// 수동
		setLayout(null);
		jlMyPage.setBounds(30, 15, 200, 40);
		jtaName.setBounds(40, 70, 400, 50);
		jbtOrderList.setBounds(40, 140, 250, 50);// 주문내역
		jbtMyData.setBounds(40, 200, 250, 50);// 내정보변경
		jbtWithdrawal.setBounds(40, 260, 250, 50); // 카드등록
		jbtJJim.setBounds(40, 320, 250, 50);// 찜하기
		jbtOk.setBounds(100, 390, 100, 30);
		jtaImage.setBounds(315, 140, 220, 220);

		add(jlMyPage);
		add(jtaName);
		add(jbtOrderList);
		add(jbtMyData);
		add(jbtWithdrawal);
		add(jbtJJim);
		add(jbtOk);
		add(jtaImage);

		UserMyPageEvt mde = new UserMyPageEvt(this, id);
		jbtOrderList.addActionListener(mde);
		jbtMyData.addActionListener(mde);
		jbtWithdrawal.addActionListener(mde);
		jbtOk.addActionListener(mde);
		jbtJJim.addActionListener(mde);

		this.getContentPane().setBackground(Color.white);
		setBounds(100, 100, 560, 500);
		// 가시화
		setVisible(true);
		setResizable(false);

	}// mypageView

	public JLabel getJlMyPage() {
		return jlMyPage;
	}

	public JTextArea getJtaName() {
		return jtaName;
	}

	public JButton getJbtOrderList() {
		return jbtOrderList;
	}

	public JButton getJbtMyData() {
		return jbtMyData;
	}

	public JButton getJbtWithdrawal() {
		return jbtWithdrawal;
	}

	public JButton getJbtOk() {
		return jbtOk;
	}

	public JButton getJbtJJim() {
		return jbtJJim;
	}

}// class
