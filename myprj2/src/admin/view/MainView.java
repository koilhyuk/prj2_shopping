package admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import admin.controller.MainEvt;
import admin.run.StaticCla;
import admin.thread2.LoginStateServer;
import admin.thread2.MemLoginThread;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private MemLoginThread mlt;
	private LoginStateServer lss;

	private List<String> idList;/////////////////// �α��� �Ǿ��ִ�
	private JTabbedPane jtpMain;
	private JButton jbtnSett, jbtnLogout;

	public MainView(String id, String ip) {
		super(id + "�� ���� ���� ��");

		idList = new ArrayList<String>();

		StaticCla sc = new StaticCla(this, id, ip);

		setLayout(new BorderLayout());
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpNorth.setLayout(null);


		jbtnSett = new JButton("��й�ȣ �缳��");
		jbtnSett.setBackground(Color.white);
		jbtnSett.setForeground(new Color(0x3F4040));
		jbtnSett.setBorder(new LineBorder(Color.white));		
		jbtnLogout = new JButton("�α׾ƿ�");
		jbtnLogout.setBackground(Color.white);
		jbtnLogout.setForeground(new Color(0x3F4040));
		jbtnLogout.setBorder(new LineBorder(Color.white));

		jbtnSett.setBounds(1220, 0, 140, 30);
		jbtnLogout.setBounds(1380, 0, 90, 30);

		jpNorth.add(jbtnSett);
		jpNorth.add(jbtnLogout);

		jpNorth.setPreferredSize(new Dimension(1500, 30));

		jtpMain = new JTabbedPane();
		AdCusManageView acmv = new AdCusManageView();
		jtpMain.addTab("��ǰ����", new AdGoodsMainView());
		jtpMain.addTab("ȸ������", acmv);
		jtpMain.addTab("��ǰ����", new AdGoodsManageView());
		jtpMain.addTab("�ֹ�����", new AdOrderManageView());
		jtpMain.setForeground(Color.white);
		jtpMain.setBackground(new Color(0x352A26));

		jpNorth.setBackground(new Color(0x352A26));
		jpCenter.setBackground(new Color(0x352A26));
		// �� �׵θ� ���ֱ� //
		jtpMain.setUI(new BasicTabbedPaneUI() {
			private final Insets borderInsets = new Insets(0, 0, 0, 0);

			@Override
			protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
			}

			@Override
			protected Insets getContentBorderInsets(int tabPlacement) {
				return borderInsets;
			}
		});

		jpCenter.add("Center", jtpMain);

		if (lss == null) {// ���� ���� ������
			lss = new LoginStateServer(acmv);
			lss.start();
		}

		if (mlt == null) {// �α��� �� id�� �о� �ѷ��ִ� ������
			mlt = new MemLoginThread(acmv);
			mlt.start();
		} // end if
		MainEvt mae = new MainEvt(lss, mlt);
		
		////////////////////////////////
		ImageIcon logoImg= new ImageIcon(StaticCla.FILE_PATH+"/prj2_logo_back_gray_footer.png");
//		ImageIcon logoImg= new ImageIcon("C:/Users/hyebin/git/prj2_shopping/myprj2/src/admin/img"+"/prj2_logo_back_gray_footer.png");
		JLabel jlTitle = new JLabel(logoImg);
		Font f1 = new Font("���� ���", Font.PLAIN, 15);
		jlTitle.setBounds(45, 20, 250, 60);
		JLabel jladdr = new JLabel("����Ư���� ������  ������� 132(���ﵿ) �ѵ���ǰ ���� 8�� ST���θ�");
		jladdr.setBounds(550, 10, 650, 25);
		jladdr.setForeground(Color.white);
		jladdr.setFont(f1);

		JLabel jljob = new JLabel("����� ��ȣ : 214-89-29543  |  ��ǥ : ������  |  ��������ó������å���� : ������");
		jljob.setBounds(550, 40, 800, 25);
		jljob.setForeground(Color.white);
		jljob.setFont(f1);

		JLabel jlinfo = new JLabel("Copyright (C) 2012 ST Instituete of System Technolgy ., ALL Right Reserved");
		jlinfo.setBounds(550, 70, 800, 25);
		jlinfo.setForeground(Color.white);
		jlinfo.setFont(f1);

		JPanel jpbanner = new JPanel();
		jpbanner.setBackground(new Color(0x3F4040));
		jpbanner.setBounds(0, 50, 1600, 140);
		JPanel jpline = new JPanel();
		jpline.setBackground(Color.white);
		jpline.setBounds(330, 13, 3, 130);

		jpbanner.add(jlTitle);
		jpbanner.add(jpline);
		jpbanner.add(jladdr);
		jpbanner.add(jljob);
		jpbanner.add(jlinfo);
		jpbanner.setPreferredSize(new Dimension(1600,110));
		jpbanner.setLayout(null);
		setResizable(false);
		////////////////////////////////

		jtpMain.addMouseListener(mae);
		jbtnSett.addActionListener(mae);
		jbtnLogout.addActionListener(mae);
	
		add("North", jpNorth);
		add("Center", jpCenter);
		add("South", jpbanner);
		
		this.getContentPane().setBackground(Color.white);
		setBounds(0, 5, 1600, 1000);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// MainView

	public MemLoginThread getMlt() {
		return mlt;
	}

	public LoginStateServer getLss() {
		return lss;
	}

	public List<String> getIdList() {
		return idList;
	}

	public JTabbedPane getJtpMain() {
		return jtpMain;
	}

	public JButton getJbtnSett() {
		return jbtnSett;
	}

	public JButton getJbtnLogout() {
		return jbtnLogout;
	}

}// class
