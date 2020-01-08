//package user.newtest;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.TitledBorder;
//
//public class PayFinishView extends JDialog implements ActionListener{
//	
//	JTextField  jtfProduct, jtfProPrice, jtfBrand, jtfOrderName, jtfAddr, jtfDeliRequest,jtfOrderNum,jtfOrderCard,
//		jtfOrderDate, jtfOrderStatus,jtfTotalPrice;
//	JButton btnOk;
//	private UserGoodsDetailEvt ugd_evt;
//	
//	
//	public PayFinishView() {
//		this.ugd_evt=ugd_evt;
//		//이미지 
//		ImageIcon iimage= new ImageIcon("이미지넣어주세");
//		JLabel jlImg= new JLabel((new ImageIcon("C:/dev/workspace/prj2/src/images/dhh.jpg")));//이미지
//		
//		JLabel jlTitle = new JLabel("결제완료");
//		jlTitle.setForeground(Color.white);
//		
//		//결제완료창 폰트 
//		Font f= new Font("맑은 고딕" ,Font.BOLD,40);
//		jlTitle.setFont(f);
//		
////		JLabel jlOrderFinish= new JLabel("주문이 완료되었습니다.");
//		JLabel jlBrand= new JLabel();
//		JLabel jlOrderName = new JLabel("구매자");
//		JLabel jlAddr = new JLabel("주소지");
//		JLabel jlDeliRequest= new JLabel("배송요청사항");
////		JLabel jlOrderNum= new JLabel("주문번호");
//		JLabel jlOrderCard= new JLabel("주문수단");
//		JLabel jlOrderDate= new JLabel("결제시각");
//		JLabel jlOrderStatus= new JLabel("결제상태");
//		JLabel jlOrdersuccess= new JLabel("결제성공");
//		
//		//시간
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		Date date = new Date();
//
//		//받은 값 라벨로 생성
//		JLabel jlg_name = new JLabel(/*String.valueOf( p_view.getJtfProName().getText() )*/);
////		JLabel jlg_getsu=new JLabel("갯수 : "+p_view.getGetsu().getText()+" 개");//갯수
//		JLabel jlg_getsu = new JLabel("수량 : " + /* ugd_view.getJtf().getText() */" 개");//갯수
//		JLabel jlre_addr = new JLabel(/*
//										 * p_view.getJtfDelivery().getText()+" "+p_view.getJtfDetailDel().getText()+"("+
//										 * p_view.getJtfzipcode().getText()+")"
//										 */);
//		JLabel jlo_person=new JLabel(/*p_view.getJtfOrder().getText()*/);
//		JLabel jldmsg = new JLabel(/* p_view.getJtfDemand().getText() */);
//		JLabel jlTotalPrice = new JLabel(/* p_view.getJtfFinalPrice().getText()+ */"원");
//		JLabel jlProPrice = new JLabel(/* ugd_view.getJlg_price().getText()+ */"원");
//		JLabel jlOrderCardSudan = new JLabel(/* String.valueOf(p_view.getDcbmCard().getSelectedItem())+"카드" */);
//		JLabel jlpaytime=new JLabel(dateFormat.format(date));
//		
////		oVO.setO_QUANTITY(ugd_view.getJtf().getText());
////		oVO.setO_BUYPAY(jlTotalPrice.getText());
////		oVO.setO_DELMSG(jldmsg.getText());
//		
//		
////		jtfProduct=new JTextField(10);
////		jtfBrand= new JTextField(10);
////		jtfOrderName= new JTextField(10);
////		jtfAddr= new JTextField(10);
////		jtfDeliRequest= new JTextField(10);
////		jtfOrderNum= new JTextField(10);
////		jtfOrderCard= new JTextField(5);
////		jtfOrderDate= new JTextField(10);
////		jtfOrderStatus= new JTextField(10);
////		jtfProPrice= new JTextField(10);
////		jtfTotalPrice= new JTextField(10);
//		
//		btnOk = new JButton("확인");
//		btnOk.setForeground(Color.white);
//		btnOk.setBackground(new Color(0x3F4040));
//		
//		/////////////////////////////////////////////
//		//디자인
//		JPanel jpds=new JPanel();
//		jpds.setBounds(0, 20, 550, 70);
//		jpds.setBackground(new Color(0x3F4040));
//		
//		//////////////////////////////////////////////////////
//		//배송지 정보 
//		JPanel jpDeliData = new JPanel();
//		jpDeliData.setLayout(null);
//		
////		jlImg.setBounds(100,100,200,200);
//		jlOrderName.setBounds(50, 30, 200, 30);
//		jlo_person.setBounds(130,30,200,30);
////		jtfOrderName.setBounds(180, 30, 250, 30);
//		jlAddr.setBounds(50, 70, 200, 30);
//		jlre_addr.setBounds(130, 70, 250, 30);
////		jtfAddr.setBounds(180, 70, 250, 30);
//		jlDeliRequest.setBounds(35, 110, 200, 30);
////		jtfDeliRequest.setBounds(180, 110, 250, 30);
//		jldmsg.setBounds(130,110,200,30);
//		
//		jpDeliData.add(jlOrderName);
////		jpDeliData.add(jtfOrderName);
//		jpDeliData.add(jlAddr);
////		jpDeliData.add(jtfAddr);
//		jpDeliData.add(jlDeliRequest);
////		jpDeliData.add(jtfDeliRequest);
//		jpDeliData.add(jlre_addr);
//		jpDeliData.add(jlo_person);
//		jpDeliData.add(jldmsg);
//		
//		jpDeliData.setBounds(40, 330, 450,170);
//		jpDeliData.setBorder(new TitledBorder("※ 배송지정보 "));
//		
//		//결제정보 
//		JPanel jpPayData= new JPanel();
//		jpPayData.setLayout(null);
//		
////		jlOrderNum.setBounds(30, 30, 100, 30);
////		jtfOrderNum.setBounds(100, 30, 170, 30);
//		jlOrderCard.setBounds(30, 40, 100, 30);
//		jlOrderCardSudan.setBounds(110,40,100,30);
////		jtfOrderCard.setBounds(100, 70, 170, 30);
//		jlOrderDate.setBounds(30, 90, 100, 30);
//		jlpaytime.setBounds(100, 90, 100, 30);
////		jtfOrderDate.setBounds(100, 110, 170, 30);
//		jlOrderStatus.setBounds(30, 150, 100, 30);
//		jlOrdersuccess.setBounds(110, 150, 100, 30);
////		jtfOrderStatus.setBounds(100, 150, 170, 30);
//		
//		jpPayData.add(jlOrdersuccess);
////		jpPayData.add(jlOrderNum);
////		jpPayData.add(jtfOrderNum);
//		jpPayData.add(jlOrderCard);
//		jpPayData.add(jlOrderCardSudan);
////		jpPayData.add(jtfOrderCard);
//		jpPayData.add(jlOrderDate);
//		jpPayData.add(jlpaytime);
////		jpPayData.add(jtfOrderDate);
//		jpPayData.add(jlOrderStatus);
////		jpPayData.add(jtfOrderStatus);
//		
//		
//		jpPayData.setBounds(40, 498, 300, 200);
//		jpPayData.setBorder(new TitledBorder("※ 결제정보 "));
//		
//		//결제금액
//		JPanel jpPay= new JPanel();
//		jpPay.add(jlProPrice);
////		jpPay.add(jtfProPrice);
//		
//		jpPay.setBounds(340, 498, 150, 100);
//		jpPay.setBorder(new TitledBorder("※ 결제금액"));
//		
//		//총 결제금액 
//		JPanel jpTotalPay= new JPanel();
//		
//		jpTotalPay.add(jlTotalPrice);
////		jpTotalPay.add(jtfTotalPrice);
//		jpTotalPay.setBounds(340, 598,150, 100);
//		jpTotalPay.setBorder(new TitledBorder("※ 총 결제 금액"));
//		
//		
//		setLayout(null);
//		jlTitle.setBounds(170, 0, 200, 100);
////		jlOrderFinish.setBounds(200, 80, 200, 30);
//		jlImg.setBounds(20, 120, 150, 150);
//		jlBrand.setBounds(200, 190, 100, 30);
//		jlg_name.setBounds(200,110,200,30);
//		jlg_getsu.setBounds(400,110,100,30);
//		
////		jtfProduct.setBounds(200, 140, 320, 30);
////		jtfBrand.setBounds(270, 190, 100, 30);
//		btnOk.setBounds(230, 715, 70, 30);
//		
//		//배치
//		jpds.add(jlTitle);
////		add(jlOrderFinish);
//		add(jlImg);
//		add(jpDeliData);
//		add(jpPayData);
//		add(jpPay);
//		add(jpTotalPay);
//		add(jlg_name);
//		add(jlg_getsu);
////		add(jtfProduct);
//		add(jlBrand);
////		add(jtfBrand);
//		add(btnOk);
//		add(jpds);
//		
////		PayFinishEvt p_evt= new PayFinishEvt(this);
//		
//		
//		
//		setBounds(100, 100, 550, 800);
//		setVisible(true);
//		setResizable(false);
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
////		btnOk.addActionListener(p_evt);
//		btnOk.addActionListener(this);
//		
//		
//		
//	}//PayFinishView
////	public static void main(String[] args) {
////		new PayFinishView();
////	}//main
//
//	public JTextField getJtfProduct() {
//		return jtfProduct;
//	}
//
//	public JTextField getJtfProPrice() {
//		return jtfProPrice;
//	}
//
//	public JTextField getJtfBrand() {
//		return jtfBrand;
//	}
//
//	public JTextField getJtfOrderName() {
//		return jtfOrderName;
//	}
//
//	public JTextField getJtfAddr() {
//		return jtfAddr;
//	}
//
//	public JTextField getJtfDeliRequest() {
//		return jtfDeliRequest;
//	}
//
//	public JTextField getJtfOrderNum() {
//		return jtfOrderNum;
//	}
//
//	public JTextField getJtfOrderCard() {
//		return jtfOrderCard;
//	}
//
//	public JTextField getJtfOrderDate() {
//		return jtfOrderDate;
//	}
//
//	public JTextField getJtfOrderStatus() {
//		return jtfOrderStatus;
//	}
//
//	public JTextField getJtfTotalPrice() {
//		return jtfTotalPrice;
//	}
//
//	public JButton getBtnOk() {
//		return btnOk;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		this.dispose();
//	}
//
//	
//
//
//}//class
