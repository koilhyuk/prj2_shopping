package test.address;
//package test.address;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JTextField;
//
//@SuppressWarnings("serial")
//public class ZipcodeTestButton extends JFrame implements ActionListener {
//	private JTextField jtfZipcode;
//	private JTextField jtfAddress;
//	private JTextField jtfDetailAddr;
//	private JButton jbtn, jbtnjoin;
//
//	public ZipcodeTestButton() {
//		super("test");
//
//		setLayout(null);
//
//		jbtn = new JButton("주소 검색");
//		jbtnjoin = new JButton("회원가입");
//
//		jtfZipcode = new JTextField();
//		jtfAddress = new JTextField();
//		jtfDetailAddr = new JTextField();
//
//		jbtn.addActionListener(this);
//		jbtnjoin.addActionListener(this);
//
//		jtfZipcode.setBounds(50, 100, 120, 40);
//		jbtn.setBounds(200, 100, 120, 40);
//		jbtnjoin.setBounds(100, 400, 120, 40);
//
//		jtfAddress.setBounds(50, 150, 400, 40);
//		jtfDetailAddr.setBounds(50, 200, 400, 40);
//
//		add(jtfZipcode);
//		add(jbtn);
//		add(jbtnjoin);
//		add(jtfAddress);
//		add(jtfDetailAddr);
//
//		setBounds(400, 200, 600, 600);
//		setVisible(true);
//	}// test
//
//	private void addrSearchSeq(String zipcode, String addr) {
//		int seq = 0;
//
//		SelectAddrDAO saDAO = SelectAddrDAO.getInstance();
//		
//		try {
//			seq=saDAO.seqSearch(zipcode, addr);
//			System.out.println("주소에 대한 Seq 값은 : "+seq);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}// end catch
//		
//	}//addrSearchSeq
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == jbtn) {
//			new ZipcodeSearchView(this);
//		} // end if
//		if (e.getSource() == jbtnjoin) {
//			addrSearchSeq(jtfZipcode.getText().trim(), jtfAddress.getText().trim());
//		} // end if
//
//	}// actionPerformed
//
//	public JTextField getJtfZipcode() {
//		return jtfZipcode;
//	}
//
//	public JTextField getJtfAddress() {
//		return jtfAddress;
//	}
//
///////////////////////////// main ///////////////////////////////
//	public static void main(String[] args) {
//		new ZipcodeTestButton();
//	}// main
//
//}// class
