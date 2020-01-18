package admin.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.view.AdCusDetailView;
import admin.view.ZipcodeSearchView;
import admin.vo.UpdateCustomerVO;
import admin.vo.updateStopVO;

/**
 * 닫기 , 수정, 정지
 * @author owner
 */
public class AdCusDetailEvt implements ActionListener {
	private AdCusDetailView sdv;
	private AdCusManageEvt cme;

	public AdCusDetailEvt(AdCusDetailView sdv) {
		this.sdv = sdv;
	}// AdCusDetailEvt

	/**
	 * 회원정보를 수정
	 */
	public void modifyCus() {
		String name = sdv.getJtfCusName().getText().trim(); // 회원이름
		switch (JOptionPane.showConfirmDialog(sdv, name + " 님의 회원정보를 수정하시겠습니까?")) {
		case JOptionPane.OK_OPTION: // 확인
			String code = sdv.getJtfID().getText().trim();
//			String name=sdv.getJtfCusName().getText().trim();
			String birth = sdv.getJtfBirth().getText().trim();
			String gender = sdv.getJtfGender().getText().trim();
			if(gender.equals("남자")) {
				gender="M";
			}else {
				gender="F";
			}//end if
			String phone = sdv.getJtfPhone().getText().trim();
			String detail_addr = sdv.getJtfCusAddr2().getText().trim();
			String img = new File(sdv.getJlImg().getIcon().toString()).getName();
			String email = sdv.getJtfEmail().getText().trim();
			String zipcode = sdv.getJtfCusbunzi().getText().trim();
			String addr = sdv.getJtfCusAddr().getText().trim();
			UpdateCustomerVO ucVO = new UpdateCustomerVO(code, name, birth, gender, phone, detail_addr, img, email,
					zipcode, addr);
			AdminDAO aDAO = AdminDAO.getInstance();
			try {
				if (aDAO.updateDetailCus(ucVO)) {
					JOptionPane.showMessageDialog(sdv, "회원정보를 수정완료했습니다");
				} // end if
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(sdv, "수정하실 항목을 올바르게 입력해주세요");
			} // end catch
		}// end switch
	}// modifyCus

	/**
	 * 회원 정지에 대한 method
	 */
	public void stopCustomer() {

		String status = sdv.getJtfStop().getText().trim();
		String code = sdv.getJtfCusNum().getText().trim();
		String name= sdv.getJtfCusName().getText().trim();
		AdminDAO aDAO = AdminDAO.getInstance();
		if (status.equals("Y")||status.equals("활성화계정")) { // 정지X -> 정지시킬때
			switch (JOptionPane.showConfirmDialog(sdv, name + " 회원님을 정지시키겠습니까?")) {
			case JOptionPane.OK_OPTION:
				try {
					status = "N"; // 정지시킨다.
					String reason = JOptionPane.showInputDialog("사유 입력\n 예) 거친언행, 도배, 잘못된 코딩 ");
					updateStopVO usVO = new updateStopVO(status, reason, code);
					if (!aDAO.updateStopFlag(usVO)) {// 정지에 실패하면
						JOptionPane.showMessageDialog(sdv, name + " 회원님의 정지상태에 실패하셨습니다.");
					} else {// 성공하면
						sdv.getJtfStop().setText(status);
						sdv.getJlreason().setText("비활성화계정  [정지사유 : " + reason + "]");
						JOptionPane.showMessageDialog(sdv, name + " 회원님을 정지시켰습니다.");
					} // end else
				} catch (SQLException e) {
					e.printStackTrace();
				} // end catch
			}// end switch
		
		} else {// 정지를 풀때
			switch (JOptionPane.showConfirmDialog(sdv, name + " 회원님의 정지를 해체하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				try {
					String reason = "";
					status = "Y";
					updateStopVO usVO = new updateStopVO(status, reason, code);
					if (!aDAO.updateStopFlag(usVO)) {
						JOptionPane.showMessageDialog(sdv, name + " 회원님의 정지해체에 실패하셨습니다.");
					} else {// 성공하면
						JOptionPane.showMessageDialog(sdv, name + " 회원님의 정지를 해제시켰습니다.");
						sdv.getJtfStop().setText(status);
						sdv.getJlreason().setText("활성화계정");
					} // end else
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} // end if
			} // end else
		} // end switch
	}// stopCustomer

	@Override
	public void actionPerformed(ActionEvent ae) {// 닫기
		if (ae.getSource() == sdv.getJbtnClose()) {
			switch (JOptionPane.showConfirmDialog(sdv, "회원관리창을 닫으시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				sdv.dispose();
			}// end switch
		} // end if

		if (ae.getSource() == sdv.getJbtnModify()) {// 수정
			modifyCus();
		} // end if
		if (ae.getSource() == sdv.getJbtnAddr()) {// 주소버튼
			new ZipcodeSearchView(sdv);
		} // end if
		if (ae.getSource() == sdv.getJbtnStop()) {// 정지
			stopCustomer();
		} // end if
		
	}// actionPerformed

}
