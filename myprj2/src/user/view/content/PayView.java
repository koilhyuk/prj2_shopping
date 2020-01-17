package user.view.content;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import user.controller.content.PayEvt;
import user.controller.content.UserGoodsMainEvt;
import user.dao.UserDAO;
import user.vo.selectZipcodeVO;
import user.vo.content.MemberCardInformVO;
import user.vo.content.SellNextInformDTO;

@SuppressWarnings("serial")
public class PayView extends JDialog {

	private JTextField jtfProName, jtfOrder, jtfPhoneFront, jtfPhoneBehind, jtfDelivery, jtfFinalPrice, jtfDemand,
			jtfzipcode, getsu, jtfprice, jtfDetailDel;
	private JButton jbtnPay, jbtnCancel, jbtnaddr;

	private JPasswordField jtfCardCVC, jtfCardOne, jtfCardTwo, jtfCardThree, jtfCardFour;

	private DefaultComboBoxModel<String> dcbmPhone, dcbmCard;
	private JComboBox<String> jcbPhoneNum, jcbCard;

	public PayView(SellNextInformDTO sniDTO, selectZipcodeVO zipcodeData) {
		super(UserGoodsMainEvt.ugmv, "상품결제", false);
		// 핸드폰
		String[] phoneData = { "010", "011", "019", "017" };
		dcbmPhone = new DefaultComboBoxModel<String>(phoneData);
		jcbPhoneNum = new JComboBox<String>(dcbmPhone);
		dcbmCard = new DefaultComboBoxModel<String>();
		// 카드종류
		UserDAO uDAO = UserDAO.getInstance();
		MemberCardInformVO tempData = null;
		List<MemberCardInformVO> cardList = sniDTO.getCardList();
		for (int i = 0; i < cardList.size(); i++) {
			tempData = cardList.get(i);
			dcbmCard.addElement(tempData.getCardMethod());
		} // end for

		jcbCard = new JComboBox<String>(dcbmCard);

		// 라벨
		JLabel jlPay = new JLabel("결제창");
		Font f = new Font("맑은 고딕", Font.BOLD, 20);
		jlPay.setFont(f);
		jlPay.setForeground(Color.white);
		JLabel jlProName = new JLabel("상품명");
		JLabel jlOrder = new JLabel("수령인");
		JLabel jlPhone = new JLabel("전화번호");
		JLabel jlPhoneFront = new JLabel("-");
		JLabel jlPhoneBehind = new JLabel("-");
		JLabel jlZipcode = new JLabel("우편번호");
		JLabel jlDetailDel = new JLabel("상세주소");
		JLabel jlDelivery = new JLabel("주소");
		JLabel jlFinalPrice = new JLabel("최종결제금액");
		JLabel jlDemand = new JLabel("배송요청사항");
		JLabel jlCard = new JLabel("신용카드");
		JLabel jlCardNum = new JLabel("카드번호");
		JLabel jlCardCVS = new JLabel("CVC(3자리)");
		JLabel jiCardH1 = new JLabel("-");
		JLabel jiCardH2 = new JLabel("-");
		JLabel jiCardH3 = new JLabel("-");

		// 디자인
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x3F4040));

		// JTextField
		jtfProName = new JTextField(10);
		jtfOrder = new JTextField(100);
		jtfPhoneFront = new JTextField();
		jtfPhoneBehind = new JTextField();
		jtfDetailDel = new JTextField();
		jtfDelivery = new JTextField();
		jtfFinalPrice = new JTextField(100);
		jtfDemand = new JTextField(100);

		jtfCardOne = new JPasswordField();
		jtfCardTwo = new JPasswordField();
		jtfCardThree = new JPasswordField();
		jtfCardFour = new JPasswordField();

		jtfCardCVC = new JPasswordField();
		jtfzipcode = new JTextField(5);
		// 수정제한
		jtfCardOne.setEditable(false);
		jtfCardThree.setEditable(false);

		// JButton
		jbtnPay = new JButton("결제하기");
		jbtnCancel = new JButton("취소하기");
		jbtnaddr = new JButton("주소검색");

		jbtnPay.setBackground(new Color(0x043424));
		jbtnPay.setForeground(Color.white);

		jbtnaddr.setBackground(new Color(0x043424));
		jbtnaddr.setForeground(Color.white);

		jbtnCancel.setBackground(new Color(0x043424));
		jbtnCancel.setForeground(Color.white);

		JPanel jpCard = new JPanel();
		jpCard.setLayout(null);//
		jlCard.setBounds(30, 10, 80, 70);
		jpCard.add(jlCard);
		jcbCard.setBounds(120, 30, 100, 30);
		jpCard.add(jcbCard);
		jlCardNum.setBounds(30, 70, 80, 70);
		jpCard.add(jlCardNum);
		jtfCardOne.setBounds(120, 90, 60, 30);
		jpCard.add(jtfCardOne);
		jtfCardTwo.setBounds(200, 90, 60, 30);
		jpCard.add(jtfCardTwo);
		jtfCardThree.setBounds(280, 90, 60, 30);
		jpCard.add(jtfCardThree);
		jtfCardFour.setBounds(360, 90, 60, 30);
		jpCard.add(jtfCardFour);
		jlCardCVS.setBounds(30, 130, 80, 70);
		jpCard.add(jlCardCVS);
		jtfCardCVC.setBounds(120, 150, 100, 30);
		jpCard.add(jtfCardCVC);
		jiCardH1.setBounds(185, 95, 30, 30);
		jpCard.add(jiCardH1);
		jiCardH2.setBounds(265, 95, 30, 30);
		jpCard.add(jiCardH2);
		jiCardH3.setBounds(345, 95, 30, 30);
		jpCard.add(jiCardH3);

		setLayout(null); // 수동배치
		jpCard.setBorder(new TitledBorder("신용카드"));
		jpCard.setBounds(20, 420, 470, 200);

		// 라벨
		jlPay.setBounds(220, 10, 80, 30);
		jlProName.setBounds(70, 60, 80, 30);
		jlOrder.setBounds(70, 100, 80, 30);
		jlPhone.setBounds(70, 148, 80, 30);
		jlPhoneFront.setBounds(280, 148, 50, 30);
		jlPhoneBehind.setBounds(370, 148, 50, 30);
		jlZipcode.setBounds(70, 200, 80, 30);
		jlDelivery.setBounds(70, 240, 80, 30);
		jlDetailDel.setBounds(70, 290, 80, 30);
		jlFinalPrice.setBounds(70, 335, 80, 30);
		jlDemand.setBounds(70, 380, 80, 30);

		// t.f
		jtfProName.setBounds(200, 60, 260, 30);
		jtfOrder.setBounds(200, 100, 260, 30);
		jcbPhoneNum.setBounds(200, 148, 60, 30);
		jtfPhoneFront.setBounds(300, 148, 60, 30);
		jtfPhoneBehind.setBounds(400, 148, 60, 30);
		jtfzipcode.setBounds(200, 200, 130, 30);
		jtfDelivery.setBounds(200, 240, 260, 30);
		jtfDetailDel.setBounds(200, 290, 260, 30);
		jtfFinalPrice.setBounds(200, 335, 260, 30);
		jtfDemand.setBounds(200, 380, 260, 30);

		jbtnPay.setBounds(150, 650, 100, 30);
		jbtnCancel.setBounds(300, 650, 100, 30);
		jbtnaddr.setBounds(350, 200, 100, 30);

		jtfDetailDel.setText(sniDTO.getmDetailAddr());
		jtfProName.setText(sniDTO.getgName());
		jtfFinalPrice.setText(String.valueOf(sniDTO.getTotalMoney()));
		jtfOrder.setText(sniDTO.getmName());
		jtfPhoneFront.setText(
				sniDTO.getmPhone().substring(sniDTO.getmPhone().indexOf("-") + 1, sniDTO.getmPhone().indexOf("-") + 5));
		jtfPhoneBehind.setText(sniDTO.getmPhone().substring(sniDTO.getmPhone().lastIndexOf("-") + 1,
				sniDTO.getmPhone().lastIndexOf("-") + 5));
		jtfzipcode.setText(zipcodeData.getzZipcode());
		jtfDelivery.setText(zipcodeData.getzAddr());
		jtfCardOne.setText(sniDTO.getCardList().get(0).getCarNum().substring(0, 4));
		jtfCardThree.setText(sniDTO.getCardList().get(0).getCarNum().substring(
				sniDTO.getCardList().get(0).getCarNum().indexOf("-", 2) + 1,
				sniDTO.getCardList().get(0).getCarNum().indexOf("-", 2) + 5));

		// 수정 불가
		jtfProName.setEditable(false);
		jtfFinalPrice.setEditable(false);
		jtfDelivery.setEditable(false);
		jtfzipcode.setEditable(false);

		// 배치
		jp.setBounds(0, 0, 530, 40);

		// Label
		jp.add(jlPay);
		add(jlProName);
		add(jlOrder);
		add(jlPhone);
		add(jlPhoneFront);
		add(jlPhoneBehind);
		add(jlZipcode);
		add(jlDelivery);
		add(jlDetailDel);
		add(jlFinalPrice);
		add(jlDemand);
		add(jpCard);

		add(jp);

		// T.F
		add(jtfProName);
		add(jtfOrder);
		add(jtfPhoneFront);
		add(jtfPhoneBehind);
		add(jtfzipcode);
		add(jtfDelivery);
		add(jtfDetailDel);
		add(jtfFinalPrice);
		add(jtfDemand);
		add(jcbPhoneNum);

		// 버튼
		add(jbtnPay);
		add(jbtnCancel);
		add(jbtnaddr);
		setBackground(Color.white);

		////// 이벤트 처리
		PayEvt pfEvt = new PayEvt(this, sniDTO);

		jbtnPay.addActionListener(pfEvt);
		jbtnCancel.addActionListener(pfEvt);
		jcbCard.addActionListener(pfEvt);
		jbtnaddr.addActionListener(pfEvt);
		jtfDetailDel.addActionListener(pfEvt);

		jtfCardTwo.addKeyListener(pfEvt);
		jtfCardFour.addKeyListener(pfEvt);
		jtfCardCVC.addKeyListener(pfEvt);

		jtfPhoneFront.addKeyListener(pfEvt);
		jtfPhoneBehind.addKeyListener(pfEvt);
		jtfDemand.addKeyListener(pfEvt);

		jp.setBackground(Color.white);
		jpCard.setBackground(Color.white);
		this.getContentPane().setBackground(Color.white);

		// 가시화
		setBounds(100, 100, 530, 730);
		setVisible(true);
		setResizable(false);

	}// payView

	public JTextField getJtfProName() {
		return jtfProName;
	}

	public JTextField getJtfOrder() {
		return jtfOrder;
	}

	public JTextField getJtfPhoneFront() {
		return jtfPhoneFront;
	}

	public JTextField getJtfPhoneBehind() {
		return jtfPhoneBehind;
	}

	public JTextField getJtfDelivery() {
		return jtfDelivery;
	}

	public JTextField getJtfFinalPrice() {
		return jtfFinalPrice;
	}

	public JTextField getJtfDemand() {
		return jtfDemand;
	}

	public JTextField getJtfzipcode() {
		return jtfzipcode;
	}

	public JTextField getGetsu() {
		return getsu;
	}

	public JTextField getJtfprice() {
		return jtfprice;
	}

	public JTextField getJtfDetailDel() {
		return jtfDetailDel;
	}

	public JButton getJbtnPay() {
		return jbtnPay;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JButton getJbtnaddr() {
		return jbtnaddr;
	}

	public JPasswordField getJtfCardCVC() {
		return jtfCardCVC;
	}

	public JPasswordField getJtfCardOne() {
		return jtfCardOne;
	}

	public JPasswordField getJtfCardTwo() {
		return jtfCardTwo;
	}

	public JPasswordField getJtfCardThree() {
		return jtfCardThree;
	}

	public JPasswordField getJtfCardFour() {
		return jtfCardFour;
	}

	public DefaultComboBoxModel<String> getDcbmPhone() {
		return dcbmPhone;
	}

	public DefaultComboBoxModel<String> getDcbmCard() {
		return dcbmCard;
	}

	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}

	public JComboBox<String> getJcbCard() {
		return jcbCard;
	}

}// class