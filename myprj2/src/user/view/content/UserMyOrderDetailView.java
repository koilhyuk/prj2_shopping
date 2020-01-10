package user.view.content;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import admin.run.StaticCla;
import admin.vo.SelectOrderDetailDTO;

@SuppressWarnings("serial")
public class UserMyOrderDetailView extends JDialog implements ActionListener {
//	private JTextField jtfOrderer, jtfReceive, jtfRecAddr, jtfPhone, jtfGoodsName, jtfbuyNum, jtfbuyMethod,

	private JLabel jtfOrderer, jtfReceive, jtfRecAddr, jtfPhone, jtfGoodsName, jtfbuyNum, jtfbuyMethod, jtfTotalPrice,
			jtfOrderDate, jtfDeliveryFlag, jtfDeliverMsg, jtfScore;

	private JButton jbtnClose;
	private JLabel jlImg;

	private SelectOrderDetailDTO odDTO;

	public UserMyOrderDetailView(SelectOrderDetailDTO odDTO) {
		super(StaticCla.mv, "상품관리-상세보기", true);
		this.odDTO = odDTO;
		// 이미지
		ImageIcon iiImage = new ImageIcon(StaticCla.FILE_PATH+"/gd_" + odDTO.getG_img());
		Image img = iiImage.getImage();
		Image chgimg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);// 이미지 사이즈변경
		ImageIcon newImg = new ImageIcon(chgimg);
		jlImg = new JLabel(newImg);
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED)); // border돌출
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		Font fontCon = new Font("맑은 고딕", Font.BOLD, 14);
		// 라벨
		JTextField jlTitle = new JTextField(" 주문정보  ");
		Font titleFont = new Font("맑은 고딕", Font.BOLD, 20);
		jlTitle.setFont(titleFont);
		jlTitle.setBackground(Color.black);
		jlTitle.setForeground(Color.white);
		jlTitle.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel jlCusTitle = new JLabel("※회원정보 ※");
		jlCusTitle.setForeground(Color.white);
		JLabel jlPayTitle = new JLabel("※상품 및 결제정보 ※ ");
		jlPayTitle.setForeground(Color.white);
		JLabel jlDeliTitle = new JLabel("※배송정보 ※ ");
		jlDeliTitle.setForeground(Color.white);
		jlTitle.setFont(font);
		
		
		JLabel jlOrderer = new JLabel("회원아이디:");//
		jlTitle.setFont(fontCon);
		
		JLabel jlReceiver = new JLabel("받는이 :");//
		jlReceiver.setFont(fontCon);
		JLabel jlRecAddr = new JLabel("배송지 :");//
		jlRecAddr.setFont(fontCon);
		JLabel jlPhone = new JLabel("연락처 :");//
		jlPhone.setFont(fontCon);

		JLabel jlGoodsName = new JLabel("상품명 :");//
		jlGoodsName.setFont(fontCon);
		
		JLabel jlbuyNum = new JLabel("구매수량 :");//
		jlbuyNum.setFont(fontCon);
		
		JLabel jlscore = new JLabel("상품평점 :");//
		jlscore.setFont(fontCon);
		
		JLabel jlbuyMethod = new JLabel("결제방식 :");//
		jlbuyMethod.setFont(fontCon);
		
		JLabel jlTotalPrice = new JLabel("총 결제금액 :");//
		jlTotalPrice.setFont(fontCon);
		
		JLabel jlOrderDate = new JLabel("주문날짜 :");//
		jlOrderDate.setFont(fontCon);
		
		JLabel jlDeliveryFlag = new JLabel("배송여부 :");//
		jlDeliveryFlag.setFont(fontCon);
		
		JLabel jlDeliverMsg = new JLabel("배송요청사항 :");//
		jlDeliverMsg.setFont(fontCon);

		// T.F
//		jtfOrderer = new JTextField(10);
//		jtfReceive = new JTextField(10);
//		jtfRecAddr = new JTextField(10);
//		jtfPhone = new JTextField(10);
//		jtfGoodsName = new JTextField(10);
//		jtfbuyNum = new JTextField(10);
//		jtfbuyMethod = new JTextField(10);
//		jtfTotalPrice = new JTextField(10);
//		jtfOrderDate = new JTextField(10);
//		jtfDeliveryFlag = new JTextField(10);
//		jtfDeliverMsg = new JTextField(10);
//		jtfScore=new JTextField(10);

		jtfOrderer = new JLabel();
		jtfReceive = new JLabel();
		jtfRecAddr = new JLabel();
		jtfPhone = new JLabel();
		jtfGoodsName = new JLabel();
		jtfbuyNum = new JLabel();
		jtfbuyMethod = new JLabel();
		jtfTotalPrice = new JLabel();
		jtfOrderDate = new JLabel();
		jtfDeliveryFlag = new JLabel();
		jtfDeliverMsg = new JLabel();
		jtfScore = new JLabel();

		jbtnClose = new JButton("확인");
		// setText
		jtfScore.setText(String.valueOf(odDTO.getO_score()));
		jtfOrderer.setText(odDTO.getM_id());// 주문자
		jtfReceive.setText(odDTO.getO_person());// 받는이
		jtfRecAddr.setText(odDTO.getO_addr());
		jtfPhone.setText(odDTO.getO_phone());
		jtfGoodsName.setText(odDTO.getG_name() + "(" + odDTO.getO_code() + ")");
		jtfbuyNum.setText(String.valueOf(odDTO.getO_quantity()));
		jtfbuyMethod.setText(odDTO.getP_method());
		jtfTotalPrice.setText(String.valueOf(odDTO.getO_buypay()));
		jtfOrderDate.setText(odDTO.getO_date());
		jtfDeliveryFlag.setText(odDTO.getO_delivery());
		jtfDeliverMsg.setText(odDTO.getO_delmsg());

		JPanel jpCuOrder = new JPanel();
		jpCuOrder.setLayout(null);

		jlOrderer.setBounds(15, 10, 80, 30);
		jpCuOrder.add(jlOrderer);

		jlReceiver.setBounds(200, 10, 60, 30);
		jpCuOrder.add(jlReceiver);

		jlRecAddr.setBounds(15, 40, 60, 30);
		jpCuOrder.add(jlRecAddr);

		jlPhone.setBounds(15, 70, 60, 30);
		jpCuOrder.add(jlPhone);

		jtfOrderer.setBounds(100, 10, 100, 30);
		jpCuOrder.add(jtfOrderer);

		jtfReceive.setBounds(260, 10, 100, 30);
		jpCuOrder.add(jtfReceive);

		jtfRecAddr.setBounds(80, 40, 250, 30);
		jpCuOrder.add(jtfRecAddr);

		jtfPhone.setBounds(80, 70, 230, 30);
		jpCuOrder.add(jtfPhone);

		JPanel jpGoods = new JPanel();
		jpGoods.setLayout(null);
		jlGoodsName.setBounds(15, 10, 60, 30);
		jpGoods.add(jlGoodsName);
		jlbuyNum.setBounds(15, 40, 100, 30);//
		jpGoods.add(jlbuyNum);//
		jlbuyMethod.setBounds(15, 70, 100, 30);
		jpGoods.add(jlbuyMethod);
		jlTotalPrice.setBounds(15, 100, 110, 30);
		jpGoods.add(jlTotalPrice);
		jlOrderDate.setBounds(15, 130, 80, 30);
		jpGoods.add(jlOrderDate);
		jtfGoodsName.setBounds(90, 10, 230, 30);
		jpGoods.add(jtfGoodsName);
		jtfbuyNum.setBounds(100, 40, 80, 30);//
		jpGoods.add(jtfbuyNum);//
		jtfbuyMethod.setBounds(100, 70, 230, 30);
		jpGoods.add(jtfbuyMethod);
		jtfTotalPrice.setBounds(140, 100, 230, 30);
		jpGoods.add(jtfTotalPrice);
		jtfOrderDate.setBounds(90, 130, 230, 30);
		jpGoods.add(jtfOrderDate);

		jlscore.setBounds(180, 40, 70, 30);
		jpGoods.add(jlscore);

		jtfScore.setBounds(250, 40, 100, 30);
		jpGoods.add(jtfScore);

		JPanel jpDelivery = new JPanel();
		jpDelivery.setLayout(null);
		jlDeliveryFlag.setBounds(10, 10, 150, 30);
		jpDelivery.add(jlDeliveryFlag);
		jlDeliverMsg.setBounds(10, 50, 150, 30);
		jpDelivery.add(jlDeliverMsg);
		jtfDeliveryFlag.setBounds(120, 10, 180, 30);
		jpDelivery.add(jtfDeliveryFlag);
		jtfDeliverMsg.setBounds(120, 50, 450, 30);
		jpDelivery.add(jtfDeliverMsg);

//		// 수정못하게 막음
//		jtfOrderer.setEditable(false);
//		jtfReceive.setEditable(false);
//		jtfRecAddr.setEditable(false);
//		jtfPhone.setEditable(false);
//		jtfGoodsName.setEditable(false);
//		jtfbuyNum.setEditable(false);
//		jtfbuyMethod.setEditable(false);
//		jtfTotalPrice.setEditable(false);
//		jtfOrderDate.setEditable(false);
//		jtfbuyMethod.setEditable(false);
//		jtfDeliveryFlag.setEditable(false);
//		jtfDeliverMsg.setEditable(false);
		jlTitle.setEditable(false);

		setLayout(null); // 수동
		jlTitle.setBounds(50, 10, 200, 30);
		jlImg.setBounds(10, 60, 270, 350);
		Font fontLabel = new Font("맑은 고딕", Font.BOLD, 17);
		jlCusTitle.setFont(fontLabel);
		jlPayTitle.setFont(fontLabel);
		jlDeliTitle.setFont(fontLabel);
		jlCusTitle.setBounds(300, 40, 200, 40);
		jlPayTitle.setBounds(300, 190, 200, 40);
		jlDeliTitle.setBounds(40, 410, 200, 40);

		jpCuOrder.setBounds(290, 80, 360, 110);
		jpCuOrder.setBorder(new LineBorder(Color.lightGray));
		jpGoods.setBounds(290, 240, 360, 170);
		jpGoods.setBorder(new LineBorder(Color.lightGray));
		jpDelivery.setBounds(15, 450, 630, 100);
		jpDelivery.setBorder(new LineBorder(Color.lightGray));
		jbtnClose.setBounds(280, 570, 120, 30);
		
		add(jlTitle);
		add(jlImg);
		add(jpCuOrder);
		add(jpGoods);
		add(jpDelivery);
		add(jbtnClose);
		add(jlCusTitle);
		add(jlPayTitle);
		add(jlDeliTitle);

		this.getContentPane().setBackground(new Color(0x3F4040)); //JDialog 배경색 변경 
		setBounds(100, 100, 700, 680);
		jbtnClose.addActionListener(this);

		setVisible(true);
		setResizable(false);

	}// AdOrderDetailView

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtnClose) {
			switch (JOptionPane.showConfirmDialog(this, "주문관리창을 닫으시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				dispose();
			}// end switch
		} // end if
	}// actionPerformed

}// class
