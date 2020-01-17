package admin.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


import admin.controller.AdCashCalcEvt;
import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdCashCalcView extends JDialog {

	private JTextField jtfDprice, jtfDcount, jtfMprice, jtfMcount, jtfYprice, jtfYcount, jtfAllPrice, jtfAllCount,
			jtfcalStart, jtfcalEnd;

	private JButton jbtClose;

	public AdCashCalcView() {
		super(StaticCla.mv, "정산관리", true);
		// 라벨
		JLabel jlDprice = new JLabel("총 금액");
		JLabel jlDcount = new JLabel("총 수량");
		JLabel jlMprice = new JLabel("총금액");
		JLabel jlMcount = new JLabel("총 수량");
		JLabel jlYprice = new JLabel("총 금액");
		JLabel jlYcount = new JLabel("총 수량");
		JLabel jlAllPrice = new JLabel("전체 금액");
		JLabel jlAllCount = new JLabel("전체 수량");

		// T.F
		jtfDprice = new JTextField();
		jtfDcount = new JTextField();
		jtfMprice = new JTextField();
		jtfMcount = new JTextField();
		jtfYprice = new JTextField();
		jtfYcount = new JTextField();
		jtfAllPrice = new JTextField();
		jtfAllCount = new JTextField();
		jtfcalStart = new JTextField();
		jtfcalEnd = new JTextField();
		JTextField jtf = new JTextField("전체 정산 내역");

		Font fTitle = new Font("맑은 고딕", Font.BOLD, 14);
		JTextField jtfYear = new JTextField("년");
		jtfYear.setHorizontalAlignment(JTextField.CENTER);
		JTextField jtfYPrice = new JTextField("정산금액");
		jtfYPrice.setHorizontalAlignment(JTextField.CENTER);
		jtfYear.setFont(fTitle);
		jtfYPrice.setFont(fTitle);
		JTextField jtfMonth = new JTextField("월");
		JTextField jtfMPrice = new JTextField("정산금액");
		jtfMonth.setHorizontalAlignment(JTextField.CENTER);
		jtfMPrice.setHorizontalAlignment(JTextField.CENTER);
		jtfMonth.setFont(fTitle);
		jtfMPrice.setFont(fTitle);
		JTextField jtfDay = new JTextField("일");
		JTextField jtfDPrice = new JTextField("정산금액");
		jtfDay.setHorizontalAlignment(JTextField.CENTER);
		jtfDPrice.setHorizontalAlignment(JTextField.CENTER);
		jtfDay.setFont(fTitle);
		jtfDPrice.setFont(fTitle);
		
		
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		jtf.setForeground(Color.white);
		jtf.setBackground(new Color(0x352A26));
		jtf.setFont(font);
		jtf.setHorizontalAlignment(JTextField.CENTER);
		
		JPanel jpYear = new JPanel(); // 년
		jpYear.setLayout(null);
		jpYear.add(jlYprice);
		jpYear.add(jlYcount);
		jpYear.add(jtfYprice);
		jpYear.add(jtfYcount);
		jpYear.add(jtfYear);
		jpYear.add(jtfYPrice);
		jpYear.setBorder(new LineBorder(Color.white));

		jlYprice.setBounds(30, 50, 70, 30);
		jlYcount.setBounds(30, 90, 70, 30);
		jtfYprice.setBounds(100, 50, 200, 30);
		jtfYcount.setBounds(100, 90, 200, 30);
		jtfYear.setBounds(30, 10, 60, 30);
		jtfYPrice.setBounds(100, 10, 200, 30);

		jtfYear.setBackground(new Color(0x352A26));
		jtfYear.setForeground(Color.white);
		jtfYPrice.setBackground(new Color(0x352A26));
		jtfYPrice.setForeground(Color.white);

		JPanel jpMonth = new JPanel();// 월
		jpMonth.setLayout(null);
		jpMonth.add(jlMcount);
		jpMonth.add(jlMprice);
		jpMonth.add(jtfMprice);
		jpMonth.add(jtfMcount);
		jpMonth.add(jtfMonth);
		jpMonth.add(jtfMPrice);

		jpMonth.setBorder(new LineBorder(Color.white));

		jlMprice.setBounds(30, 50, 70, 30);
		jlMcount.setBounds(30, 90, 70, 30);
		jtfMprice.setBounds(100, 50, 200, 30);
		jtfMcount.setBounds(100, 90, 200, 30);
		jtfMonth.setBounds(30, 10, 60, 30);
		jtfMPrice.setBounds(100, 10, 200, 30);

		jtfMonth.setBackground(new Color(0x352A26));
		jtfMonth.setForeground(Color.white);
		jtfMPrice.setBackground(new Color(0x352A26));
		jtfMPrice.setForeground(Color.white);


		JPanel jpDay = new JPanel();// 일
		jpDay.setLayout(null);
		jpDay.add(jlDprice);
		jpDay.add(jlDcount);
		jpDay.add(jtfDprice);
		jpDay.add(jtfDcount);

		jpDay.add(jtfDay);
		jpDay.add(jtfDPrice);
		jpDay.setBorder(new LineBorder(Color.white));

		jlDprice.setBounds(30, 50, 70, 30);
		jlDcount.setBounds(30, 90, 70, 30);
		jtfDprice.setBounds(100, 50, 200, 30);
		jtfDcount.setBounds(100, 90, 200, 30);

		jtfDay.setBounds(30, 10, 60, 30);
		jtfDPrice.setBounds(100, 10, 200, 30);

		jtfDay.setBackground(new Color(0x352A26));
		jtfDay.setForeground(Color.white);
		jtfDPrice.setBackground(new Color(0x352A26));
		jtfDPrice.setForeground(Color.white);

		JPanel jpTotal = new JPanel();
		jpTotal.setLayout(null);
		jpTotal.add(jlAllPrice);
		jpTotal.add(jlAllCount);
		jpTotal.add(jtfAllPrice);
		jpTotal.add(jtfAllCount);
		jpTotal.setBorder(new LineBorder(Color.white));
		jlAllPrice.setBounds(30, 10, 100, 30);
		jlAllCount.setBounds(30, 50, 70, 30);
		jtfAllPrice.setBounds(100, 10, 200, 30);
		jtfAllCount.setBounds(100, 50, 200, 30);

		JPanel jp = new JPanel();
		jpDay.setBounds(10, 310, 350, 135);
		jpYear.setBounds(10, 10, 350, 135);
		jpMonth.setBounds(10, 160, 350, 135);
		
		jpYear.setBackground(Color.white);
		jpMonth.setBackground(Color.white);
		jpDay.setBackground(Color.white);
		jpTotal.setBackground(Color.white);
		
		
		jpTotal.setBounds(10, 460, 350, 100);
		jp.setLayout(null);
		jp.add(jpTotal);
		jp.add(jpDay);
		jp.add(jpMonth);
		jp.add(jpYear);
		jp.setBounds(15, 40, 370, 570);
		jp.setBorder(new LineBorder(new Color(0x352A26)));
		jp.setBackground(new Color(0x352A26));

		jbtClose = new JButton("닫기");
//		jbtClose.setBackground(new Color(0x352A26));
		jbtClose.setBackground(Color.white);
		
		jbtClose.setBounds(160, 620, 70, 30);
		jtf.setBounds(15, 5, 370, 30);

		jtfDprice.setEditable(false);
		jtfDcount.setEditable(false);
		jtfMprice.setEditable(false);
		jtfMcount.setEditable(false);
		jtfYprice.setEditable(false);
		jtfYcount.setEditable(false);
		jtfAllPrice.setEditable(false);
		jtfAllCount.setEditable(false);
		jtfcalStart.setEditable(false);
		jtfcalEnd.setEditable(false);
		
		this.getContentPane().setBackground(new Color(0x352A26));
		jtfDprice.setBackground(Color.white);
		jtfDcount.setBackground(Color.white);
		jtfMprice.setBackground(Color.white);
		jtfMcount.setBackground(Color.white);
		jtfYprice.setBackground(Color.white);
		jtfYcount.setBackground(Color.white);
		jtfAllPrice.setBackground(Color.white);
		jtfAllCount.setBackground(Color.white);
		
		
		jtfMprice.setHorizontalAlignment(JTextField.CENTER);
		jtfMcount.setHorizontalAlignment(JTextField.CENTER);
		jtfYprice.setHorizontalAlignment(JTextField.CENTER);
		jtfYcount.setHorizontalAlignment(JTextField.CENTER);
		jtfAllPrice.setHorizontalAlignment(JTextField.CENTER);
		jtfAllCount.setHorizontalAlignment(JTextField.CENTER);
		jtfDprice.setHorizontalAlignment(JTextField.CENTER);
		jtfDcount.setHorizontalAlignment(JTextField.CENTER);
		
		jtfYear.setEditable(false);
		jtfYPrice.setEditable(false);
		jtfMonth.setEditable(false);
		jtfMPrice.setEditable(false);
		jtfDay.setEditable(false);
		jtfDPrice.setEditable(false);
		jtf.setEditable(false);

		setLayout(null);
		add(jbtClose);
		add(jp);
		add(jtf);

		setBounds(100, 100, 420, 700);

		AdCashCalcEvt ce = new AdCashCalcEvt(this);
		jbtClose.addActionListener(ce);
		
		setVisible(true);
	}// calculateView

	public JTextField getJtfDprice() {
		return jtfDprice;
	}

	public JTextField getJtfDcount() {
		return jtfDcount;
	}

	public JTextField getJtfMprice() {
		return jtfMprice;
	}

	public JTextField getJtfMcount() {
		return jtfMcount;
	}

	public JTextField getJtfYprice() {
		return jtfYprice;
	}

	public JTextField getJtfYcount() {
		return jtfYcount;
	}

	public JTextField getJtfAllPrice() {
		return jtfAllPrice;
	}

	public JTextField getJtfAllCount() {
		return jtfAllCount;
	}

	public JTextField getJtfcalStart() {
		return jtfcalStart;
	}

	public JTextField getJtfcalEnd() {
		return jtfcalEnd;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}


}// class
