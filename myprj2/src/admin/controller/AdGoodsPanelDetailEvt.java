package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.view.AdGoodsPanelDetailView;

public class AdGoodsPanelDetailEvt implements ActionListener {

	private AdGoodsPanelDetailView agpdv;

	public AdGoodsPanelDetailEvt(AdGoodsPanelDetailView agpdv) {
		this.agpdv = agpdv;
	}// UserProductDetailEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == agpdv.getJbtnClose()) {
			agpdv.dispose();
		} // end if
	}// actionPerformed
}// class
