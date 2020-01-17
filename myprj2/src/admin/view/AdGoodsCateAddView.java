package admin.view;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import admin.controller.AdGoodsCateAddEvt;
import admin.controller.AdGoodsCateManageEvt;
import admin.run.StaticCla;

@SuppressWarnings("serial")
public class AdGoodsCateAddView extends JDialog{

	private JTabbedPane jtp;
	private JButton jbtnBAdd, jbtnBClose, jbtnBImg,jbtnCAdd, jbtnCClose;
	private JLabel jlImg;
	private JTextField jtfBrand, jtfCategory; 
	private CheckboxGroup cgCategory;
	private Checkbox cbOuter,cbTop,cbBottom,cbDress,cbAcc,cbBag,cbShoes,cbHead;
	private AdGoodsCateManageEvt gcme;
	
	public AdGoodsCateAddView(AdGoodsCateManageEvt gcme) {
		super(StaticCla.mv,"카테고리 추가",true);
		this.gcme=gcme;

		
//		ImageIcon iiImag= new ImageIcon("C:/dev/workspace/myprj2/src/img/");
		jlImg= new JLabel("브랜드 이미지",JLabel.CENTER); //이미지 
		jlImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		jbtnBAdd= new JButton("브랜드 추가");
		jbtnBClose= new JButton("닫기");
		jbtnBImg= new JButton("이미지 추가");
		
		
		jtfBrand = new JTextField("브랜드입력");
		JLabel jlBrand = new JLabel("브랜드명 ");
		JLabel jlCategoty = new JLabel("카테고리명 ");
		
		
		jtfCategory= new JTextField("카테고리 입력");
		jbtnCAdd= new JButton("추가");
		jbtnCClose= new JButton("닫기");

		cgCategory= new CheckboxGroup();
		cbOuter= new Checkbox("OUTER",cgCategory,true);
		cbTop= new Checkbox("TOP",cgCategory,false);
		cbBottom= new Checkbox("BOTTOM",cgCategory,false);
		cbDress= new Checkbox("DRESS",cgCategory,false);
		cbAcc= new Checkbox("ACCESSORY",cgCategory,false);
		cbBag= new Checkbox("BAG",cgCategory,false);
		cbShoes= new Checkbox("SHOES",cgCategory,false);
		cbHead= new Checkbox("HEADWEAR",cgCategory,false);
		
		
		JPanel jpType= new JPanel();
		jpType.add(cbOuter);
		jpType.add(cbTop);
		jpType.add(cbBottom);
		jpType.add(cbDress);
		jpType.add(cbAcc);
		jpType.add(cbBag);
		jpType.add(cbShoes);
		jpType.add(cbHead);
		jpType.setBorder(new TitledBorder("※ 추가하실 타입을 선택해주세요."));
		jpType.setBackground(Color.white);
		
		JPanel jpBrand = new JPanel();
		jpBrand.setLayout(null);
		jpBrand.setBounds(100, 100, 300, 300);
		jlBrand.setBounds(230, 70, 120, 30);
		jtfBrand.setBounds(310, 70, 170, 30);
		jlImg.setBounds(20, 5, 150, 150);
		jbtnBAdd.setBounds(230, 150, 120, 30);
		jbtnBClose.setBounds(370, 150, 120, 30);
		jbtnBImg.setBounds(20, 165, 150, 20);
		jpBrand.add(jlImg);
		jpBrand.add(jlBrand);
		jpBrand.add(jbtnBAdd);
		jpBrand.add(jtfBrand);
		jpBrand.add(jbtnBClose);
		jpBrand.add(jbtnBImg);
		
		jbtnBAdd.setBackground(new Color(0x352A26));
		jbtnBAdd.setForeground(Color.white);
		jbtnBClose.setBackground(new Color(0x352A26));
		jbtnBClose.setForeground(Color.white);
		jbtnBImg.setBackground(new Color(0x352A26));
		jbtnBImg.setForeground(Color.white);
		
		JPanel jpCategory= new JPanel();
		jpCategory.setLayout(null);
		jlCategoty.setBounds(120, 150, 90, 30);
		jpType.setBounds(50, 20, 400, 100);
		jtfCategory.setBounds(200, 150, 200, 30);
		jbtnCAdd.setBounds(150, 200, 100, 30);
		jbtnCClose.setBounds(270, 200, 100, 30);
		jpCategory.add(jtfCategory);
		jpCategory.add(jbtnCAdd);
		jpCategory.add(jbtnCClose);
		jpCategory.add(jpType);
		jpCategory.add(jlCategoty);
	
		jbtnCAdd.setBackground(new Color(0x352A26));
		jbtnCAdd.setForeground(Color.white);
		jbtnCClose.setBackground(new Color(0x352A26));
		jbtnCClose.setForeground(Color.white);
		
		
		//jtp
		jtp= new JTabbedPane();
		jpBrand.setBackground(Color.white);
		jpCategory.setBackground(Color.white);
		jtp.addTab("브랜드 추가", jpBrand);
		jtp.addTab("카테고리추가", jpCategory);
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
		
		add("Center",jtp);
		AdGoodsCateAddEvt ace= new AdGoodsCateAddEvt(this);
		//액션 
		jbtnBAdd.addActionListener(ace);
		jbtnBClose.addActionListener(ace);
		jbtnBImg.addActionListener(ace);
		jbtnCAdd.addActionListener(ace);
		jbtnCClose.addActionListener(ace);
		
		jtfBrand.addMouseListener(ace);
		jtfCategory.addMouseListener(ace);
		

		setBounds(100, 100, 550, 310);
		setVisible(true);
		setResizable(false);

	}//ModifyCategoryView

	public JTabbedPane getJtp() {
		return jtp;
	}

	public JButton getJbtnBAdd() {
		return jbtnBAdd;
	}

	public JButton getJbtnBClose() {
		return jbtnBClose;
	}

	public JButton getJbtnBImg() {
		return jbtnBImg;
	}

	public JButton getJbtnCAdd() {
		return jbtnCAdd;
	}

	public JButton getJbtnCClose() {
		return jbtnCClose;
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

	public CheckboxGroup getCgCategory() {
		return cgCategory;
	}

	public Checkbox getCbOuter() {
		return cbOuter;
	}

	public Checkbox getCbTop() {
		return cbTop;
	}

	public Checkbox getCbBottom() {
		return cbBottom;
	}

	public Checkbox getCbDress() {
		return cbDress;
	}

	public Checkbox getCbAcc() {
		return cbAcc;
	}

	public Checkbox getCbBag() {
		return cbBag;
	}

	public Checkbox getCbShoes() {
		return cbShoes;
	}

	public Checkbox getCbHead() {
		return cbHead;
	}

	public AdGoodsCateManageEvt getGcme() {
		return gcme;
	}


}//class
