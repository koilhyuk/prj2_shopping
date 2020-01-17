package user.view.content;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.controller.content.PayCompleteEvt;
import user.controller.content.UserGoodsMainEvt;
import user.view.login.ClientLoginView;
import user.vo.content.CompleteOrderInformDTO;

@SuppressWarnings("serial")
public class PayCompleteView extends JDialog {

	JButton btnOk;

	public PayCompleteView(CompleteOrderInformDTO coiDTO) {
		super(UserGoodsMainEvt.ugmv, "���� �Ϸ�", false);
		// �̹���
		JLabel jlImg = new JLabel((new ImageIcon(ClientLoginView.USER_FILE_PATH + "/rs_gds_" + coiDTO.getgImg())));// �̹���
		JLabel jlTitle = new JLabel("�����Ϸ�");
		jlTitle.setForeground(Color.white);

		// �����Ϸ�â ��Ʈ
		Font titleFont = new Font("���� ���", Font.BOLD, 40);
		jlTitle.setFont(titleFont);

		JLabel jlOrderFinish = new JLabel("�ֹ��� �Ϸ�Ǿ����ϴ�.", JLabel.CENTER);
		Font finishFont = new Font("����", Font.BOLD, 25);
		jlOrderFinish.setFont(finishFont);

		/////////////// ��ǰ ���� ///////////
		JLabel jlOCodeTag = new JLabel("�ֹ� ��ȣ", JLabel.LEFT);
		Font codeFont = new Font("����", Font.BOLD, 17);
		jlOCodeTag.setFont(codeFont);
		JLabel jlGoodsNameTag = new JLabel("��ǰ��", JLabel.LEFT);
		JLabel jlGoodsQuantityTag = new JLabel("�ֹ� ����", JLabel.LEFT);

		JLabel jlOCode = new JLabel(coiDTO.getoCode(), JLabel.LEFT);
		jlOCode.setFont(codeFont);
		JLabel jlGoodsName = new JLabel(coiDTO.getgName(), JLabel.LEFT);
		JLabel jlGoodsQuantity = new JLabel(coiDTO.getoQuantity() + " ��", JLabel.LEFT);

		//////////// ����� ���� ///////////
		JLabel jlOrderNameTag = new JLabel("������", JLabel.LEFT);
		JLabel jlReceiverTag = new JLabel("������", JLabel.LEFT);
		JLabel jlPhoneNumTag = new JLabel("����ó", JLabel.LEFT);
		JLabel jlAddrTag = new JLabel("�����", JLabel.LEFT);
		JLabel jlDeliRequestTag = new JLabel("��ۿ�û����", JLabel.LEFT);

		JLabel jlOrderName = new JLabel(coiDTO.getmName(), JLabel.LEFT);
		JLabel jlReceiver = new JLabel(coiDTO.getoAddressee(), JLabel.LEFT);
		JLabel jlPhoneNum = new JLabel(coiDTO.getoPhone(), JLabel.LEFT);
		JLabel jlAddr = new JLabel(coiDTO.getoDetailAddr(), JLabel.LEFT);
		JLabel jlDeliRequest = new JLabel(coiDTO.getoDemand(), JLabel.LEFT);

		/////////// ���� ���� /////////////
		JLabel jlOrderCardTag = new JLabel("�ֹ�����", JLabel.LEFT);
		JLabel jlOrderDateTag = new JLabel("�����ð�", JLabel.LEFT);
		JLabel jlTotalPriceTag = new JLabel("�����ݾ�", JLabel.LEFT);
		Font priceFont = new Font("����", Font.BOLD, 20);
		jlTotalPriceTag.setFont(priceFont);

		JLabel jlOrderCard = new JLabel(coiDTO.getpMethod(), JLabel.LEFT);
		JLabel jlOrderDate = new JLabel(coiDTO.getoDate(), JLabel.LEFT);
		DecimalFormat priceFormat = new DecimalFormat("###,###");
		JLabel jlTotalPrice = new JLabel(priceFormat.format(coiDTO.getoTotalMoney()) + " ��", JLabel.LEFT);
		jlTotalPrice.setFont(priceFont);

		btnOk = new JButton("Ȯ��");
		btnOk.setForeground(Color.white);
		btnOk.setBackground(new Color(0x043424));

		/////////////////////////////////////////////
		JPanel jpTitle = new JPanel();
		jpTitle.setBounds(0, 20, 550, 70);
		jpTitle.setBackground(new Color(0x352A26));

		jlImg.setBounds(40, 160, 130, 140);
		jlImg.setBorder(new LineBorder(Color.BLACK));

		//////////////// ��ǰ ����
		JPanel jpGoodsInform = new JPanel();
		jpGoodsInform.setBounds(180, 150, 310, 155);
		jpGoodsInform.setBorder(new TitledBorder(" �� ��ǰ ���� "));

		jpGoodsInform.setLayout(null);

		jlOCodeTag.setBounds(20, 30, 100, 40);
		jlGoodsNameTag.setBounds(20, 70, 100, 40);
		jlGoodsQuantityTag.setBounds(20, 110, 100, 40);

		jlOCode.setBounds(120, 30, 190, 40);
		jlOCode.setForeground(Color.blue);
		jlGoodsName.setBounds(120, 70, 190, 40);
		jlGoodsQuantity.setBounds(120, 110, 190, 40);

		jpGoodsInform.add(jlOCodeTag);
		jpGoodsInform.add(jlGoodsNameTag);
		jpGoodsInform.add(jlGoodsQuantityTag);
		jpGoodsInform.add(jlOCode);
		jpGoodsInform.add(jlGoodsName);
		jpGoodsInform.add(jlGoodsQuantity);

		//////////////// ����� ����
		JPanel jpDeliData = new JPanel();
		jpDeliData.setLayout(null);

		jlOrderNameTag.setBounds(20, 30, 100, 30);
		jlReceiverTag.setBounds(20, 65, 100, 30);
		jlPhoneNumTag.setBounds(20, 100, 100, 30);
		jlAddrTag.setBounds(20, 135, 100, 30);
		jlDeliRequestTag.setBounds(20, 170, 100, 30);

		jlOrderName.setBounds(130, 30, 320, 30);
		jlReceiver.setBounds(130, 65, 320, 30);
		jlPhoneNum.setBounds(130, 100, 320, 30);
		jlAddr.setBounds(130, 135, 320, 30);
		jlDeliRequest.setBounds(130, 170, 320, 30);

		jpDeliData.add(jlOrderNameTag);
		jpDeliData.add(jlReceiverTag);
		jpDeliData.add(jlPhoneNumTag);
		jpDeliData.add(jlAddrTag);
		jpDeliData.add(jlDeliRequestTag);

		jpDeliData.add(jlOrderName);
		jpDeliData.add(jlReceiver);
		jpDeliData.add(jlPhoneNum);
		jpDeliData.add(jlAddr);
		jpDeliData.add(jlDeliRequest);

		jpDeliData.setBounds(40, 313, 450, 220);
		jpDeliData.setBorder(new TitledBorder(" �� ��������� "));

		//////////////////// ��������
		JPanel jpPayData = new JPanel();
		jpPayData.setLayout(null);

		jlOrderCardTag.setBounds(20, 30, 100, 30);
		jlOrderDateTag.setBounds(20, 70, 100, 30);
		jlTotalPriceTag.setBounds(20, 115, 100, 30);

		jlOrderCard.setBounds(130, 30, 315, 30);
		jlOrderDate.setBounds(130, 70, 315, 30);
		jlTotalPrice.setBounds(130, 115, 315, 30);
		jlTotalPrice.setForeground(Color.RED);

		jpPayData.add(jlOrderCardTag);
		jpPayData.add(jlOrderDateTag);
		jpPayData.add(jlTotalPriceTag);

		jpPayData.add(jlOrderCard);
		jpPayData.add(jlOrderDate);
		jpPayData.add(jlTotalPrice);

		jpPayData.setBounds(40, 540, 450, 170);
		jpPayData.setBorder(new TitledBorder(" �� �������� "));
		setLayout(null);
		jlTitle.setBounds(170, 0, 200, 100);

		jlOrderFinish.setBounds(0, 90, 545, 60);

		btnOk.setBounds(230, 715, 70, 30);

		PayCompleteEvt pcEvt = new PayCompleteEvt(this);
		btnOk.addActionListener(pcEvt);

		// ��ġ
		jpTitle.add(jlTitle);
		add(jlImg);
		add(jpDeliData);
		add(jpPayData);
		add(jpTitle);
		add(jlOrderFinish);
		add(jpGoodsInform);
		add(btnOk);
		setBounds(500, 50, 550, 800);
		jpDeliData.setBackground(Color.white);
		jpPayData.setBackground(Color.white);
		jpGoodsInform.setBackground(Color.white);
		this.getContentPane().setBackground(Color.white);
		setVisible(true);
		setResizable(false);

	}// PayFinishView

	public JButton getBtnOk() {
		return btnOk;
	}

}// class
