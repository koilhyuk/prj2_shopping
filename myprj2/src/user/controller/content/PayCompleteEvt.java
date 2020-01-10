package user.controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.view.content.PayCompleteView;

public class PayCompleteEvt implements ActionListener {

	private PayCompleteView pcv;

	public PayCompleteEvt(PayCompleteView pcv) {
		this.pcv = pcv;

	}// PayFinishEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pcv.getBtnOk()) {
			pcv.dispose();
		} // end if

	}// actionPerformed
}// class
