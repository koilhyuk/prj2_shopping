package admin.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import admin.controller.AdGoodsCateManageEvt;
import admin.controller.AdGoodsCateModifyEvt;
import admin.run.StaticCla;
import admin.vo.SelectGoodsDetailDTO;

@SuppressWarnings("serial")
public class AdGoodsCateModifyView extends JDialog {

	private JTabbedPane jtp;
	private JButton jbtnBModufy, jbtnBClose, jbtnBImg, jbtnBDelete, jbtnCAdd, jbtnCClose, jbtnCDelete;
	private JLabel jlImg;
	private JTextField jtfBrand, jtfCategory;
	private JTextField jtfTypeCode;//
	
	private DefaultComboBoxModel<String> dcbmBrand, dcbmType, dcbmDetailType;
	private JComboBox<String> jcbBrand, jcbType, jcbDetailType;
	private AdGoodsCateManageEvt gcme;

	public AdGoodsCateModifyView(AdGoodsCateManageEvt gcme) {
		super(StaticCla.mv, "카테고리 수정", true);
		this.gcme=gcme;
		
		
		jlImg = new JLabel("이미지",JLabel.CENTER); // 이미지
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		dcbmBrand = new DefaultComboBoxModel<String>();
		jcbBrand = new JComboBox<String>(dcbmBrand);

		dcbmType = new DefaultComboBoxModel<String>();
		jcbType = new JComboBox<String>(dcbmType);

		dcbmDetailType = new DefaultComboBoxModel<String>();
		jcbDetailType = new JComboBox<String>(dcbmDetailType);

		jbtnBModufy = new JButton("수정");
		jbtnBDelete = new JButton("브랜드삭제");
		jbtnBImg = new JButton("이미지 업로드");
		jbtnBClose = new JButton("닫기");

		jtfBrand = new JTextField("브랜드수정");
		jtfTypeCode = new JTextField("코드");
		JLabel jlBrand = new JLabel("※ 수정하실 브랜드명을 선택해주세요. ");
		JLabel jlCategoty = new JLabel("카테고리명 ");

		jtfCategory = new JTextField("카테고리 입력");
		jbtnCAdd = new JButton("수정");
		jbtnCClose = new JButton("닫기");
		jbtnCDelete = new JButton("삭제");
		
		JPanel jpType = new JPanel();
		jpType.add(jcbType);
		jpType.add(jcbDetailType);
		jpType.setBorder(new TitledBorder("※ 수정하실 소분류를 선택해주세요."));
		jpType.setBackground(Color.white);
		
		JPanel jpBrand = new JPanel();
		jpBrand.setLayout(null);
		jcbBrand.setBounds(250, 70, 150, 30);
		jpBrand.setBounds(100, 100, 300, 300);
		jlBrand.setBounds(230, 40, 250, 30);
		jtfBrand.setBounds(250, 120, 150, 30);
		jlImg.setBounds(40, 30, 150, 150);
		jbtnBModufy.setBounds(250, 170, 120, 30);
		jbtnBClose.setBounds(310, 210, 120, 30);
		jbtnBImg.setBounds(40, 200, 150, 20);
		jbtnBDelete.setBounds(380, 170, 120, 30);
		
		
		jpBrand.add(jcbBrand);
		jpBrand.add(jlImg);
		jpBrand.add(jlBrand);
		jpBrand.add(jbtnBModufy);
		jpBrand.add(jtfBrand);
		jpBrand.add(jbtnBClose);
		jpBrand.add(jbtnBImg);
		jpBrand.add(jbtnBDelete);

		jbtnBModufy.setBackground(new Color(0x352A26));
		jbtnBModufy.setForeground(Color.white);
		jbtnBClose.setBackground(new Color(0x352A26));
		jbtnBClose.setForeground(Color.white);
		jbtnBImg.setBackground(new Color(0x352A26));
		jbtnBImg.setForeground(Color.white);
		
		jbtnBDelete.setBackground(new Color(0x352A26));
		jbtnBDelete.setForeground(Color.white);
		
		
		JPanel jpCategory = new JPanel();
		jpCategory.setLayout(null);
		jlCategoty.setBounds(120, 90, 90, 30);
		jpType.setBounds(50, 50, 400, 60);
		jtfCategory.setBounds(190, 130, 150, 30);
		jbtnCAdd.setBounds(70, 180, 100, 30);
		jbtnCDelete.setBounds(190, 180, 100, 30);
		jbtnCClose.setBounds(300, 180, 100, 30);
		jpCategory.add(jbtnCAdd);
		jpCategory.add(jbtnCClose);
		jpCategory.add(jtfCategory);
		jpCategory.add(jbtnCDelete);
		jpCategory.add(jpType);
		jpCategory.add(jlCategoty);
		jpCategory.add(jtfTypeCode);

		jbtnCAdd.setBackground(new Color(0x352A26));
		jbtnCAdd.setForeground(Color.white);
		jbtnCClose.setBackground(new Color(0x352A26));
		jbtnCClose.setForeground(Color.white);
		jbtnCDelete.setBackground(new Color(0x352A26));
		jbtnCDelete.setForeground(Color.white);
		
		// jtp
		jtp = new JTabbedPane();
		jtp.setBackground(Color.white);
		jtp.setUI(new BasicTabbedPaneUI() {
			private final Insets borderInsets = new Insets(0, 0, 0, 0);

			@Override
			protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
			}

			@Override
			protected Insets getContentBorderInsets(int tabPlacement) {
				return borderInsets;
			}
		});
		
		
		this.getContentPane().setBackground(new Color(0x352A26)); //JDialog 배경색 변경 

		jtp.addTab("브랜드 수정", jpBrand);
		jtp.addTab("카테고리수정", jpCategory);
		jpBrand.setBackground(Color.white);
		jpCategory.setBackground(Color.white);

		add("Center", jtp);
		AdGoodsCateModifyEvt gcm = new AdGoodsCateModifyEvt(this);
		jcbBrand.addActionListener(gcm);
		jcbDetailType.addActionListener(gcm);
		jcbType.addActionListener(gcm);
		
		jbtnBClose.addActionListener(gcm);
		jbtnBImg.addActionListener(gcm);
		jbtnBModufy.addActionListener(gcm);
		jbtnBDelete.addActionListener(gcm);
		
		jbtnCAdd.addActionListener(gcm);
		jbtnCClose.addActionListener(gcm);
		jbtnCDelete.addActionListener(gcm);
		jtfCategory.addMouseListener(gcm);

		setBounds(100, 100, 550, 370);
		setVisible(true);
		setResizable(false);

	}// ModifyCategoryView

	public AdGoodsCateManageEvt getGcme() {
		return gcme;
	}


	public JTabbedPane getJtp() {
		return jtp;
	}

	public void setJtp(JTabbedPane jtp) {
		this.jtp = jtp;
	}

	public JButton getJbtnBModufy() {
		return jbtnBModufy;
	}

	public JButton getJbtnBClose() {
		return jbtnBClose;
	}

	public JButton getJbtnBImg() {
		return jbtnBImg;
	}

	public JButton getJbtnBDelete() {
		return jbtnBDelete;
	}

	public JButton getJbtnCAdd() {
		return jbtnCAdd;
	}

	public JButton getJbtnCClose() {
		return jbtnCClose;
	}

	public JButton getJbtnCDelete() {
		return jbtnCDelete;
	}

	public JLabel getJlImg() {
		return jlImg;
	}

	public JTextField getJtfBrand() {
		return jtfBrand;
	}

	public JTextField getJtfCategory() {
		return jtfCategory;
	}

	public DefaultComboBoxModel<String> getDcbmBrand() {
		return dcbmBrand;
	}

	public DefaultComboBoxModel<String> getDcbmType() {
		return dcbmType;
	}

	public DefaultComboBoxModel<String> getDcbmDetailType() {
		return dcbmDetailType;
	}

	public JComboBox<String> getJcbBrand() {
		return jcbBrand;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public JComboBox<String> getJcbDetailType() {
		return jcbDetailType;
	}


	public JTextField getJtfTypeCode() {
		return jtfTypeCode;
	}
	
	

}// class
