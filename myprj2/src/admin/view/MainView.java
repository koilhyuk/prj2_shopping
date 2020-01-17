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

	private List<String> idList;/////////////////// 로그인 되어있는
	private JTabbedPane jtpMain;
	private JButton jbtnSett, jbtnLogout;

	public MainView(String id, String ip) {
		super(id + "님 접속 메인 뷰");

		idList = new ArrayList<String>();

		StaticCla sc = new StaticCla(this, id, ip);

		setLayout(new BorderLayout());
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpNorth.setLayout(null);


		jbtnSett = new JButton("비밀번호 재설정");
		jbtnSett.setBackground(Color.white);
		jbtnSett.setForeground(new Color(0x3F4040));
		jbtnSett.setBorder(new LineBorder(Color.white));		
		jbtnLogout = new JButton("로그아웃");
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
		jtpMain.addTab("상품보기", new AdGoodsMainView());
		jtpMain.addTab("회원관리", acmv);
		jtpMain.addTab("상품관리", new AdGoodsManageView());
		jtpMain.addTab("주문관리", new AdOrderManageView());
		jtpMain.setForeground(Color.white);
		jtpMain.setBackground(new Color(0x352A26));

		jpNorth.setBackground(new Color(0x352A26));
		jpCenter.setBackground(new Color(0x352A26));
		// 텝 테두리 없애기 //
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

		if (lss == null) {// 소켓 연결 스레드
			lss = new LoginStateServer(acmv);
			lss.start();
		}

		if (mlt == null) {// 로그인 된 id를 읽어 뿌려주는 스레드
			mlt = new MemLoginThread(acmv);
			mlt.start();
		} // end if
		MainEvt mae = new MainEvt(lss, mlt);
		
		////////////////////////////////
		ImageIcon logoImg= new ImageIcon(StaticCla.FILE_PATH+"/prj2_logo_back_gray_footer.png");
//		ImageIcon logoImg= new ImageIcon("C:/Users/hyebin/git/prj2_shopping/myprj2/src/admin/img"+"/prj2_logo_back_gray_footer.png");
		JLabel jlTitle = new JLabel(logoImg);
		Font f1 = new Font("맑은 고딕", Font.PLAIN, 15);
		jlTitle.setBounds(45, 20, 250, 60);
		JLabel jladdr = new JLabel("서울특별시 강남구  테헤란로 132(역삼동) 한독약품 빌딩 8층 ST쇼핑몰");
		jladdr.setBounds(550, 10, 650, 25);
		jladdr.setForeground(Color.white);
		jladdr.setFont(f1);

		JLabel jljob = new JLabel("사업자 번호 : 214-89-29543  |  대표 : 고일혁  |  개인정보처리관리책임자 : 김혜빈");
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
