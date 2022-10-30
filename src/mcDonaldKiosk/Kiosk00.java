package mcDonaldKiosk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Kiosk00 extends JPanel implements ActionListener {

	PanelAdmin PAd;

	public Kiosk00(PanelAdmin P_admin) {
		this.PAd = P_admin;
		startKiosk();
	}

	public void startKiosk() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(950, 1000));
		ImageIcon title1 = new ImageIcon("images/title/Title01.png");
		ImageIcon title2 = new ImageIcon("images/title/Title02.png");
		ImageIcon title3 = new ImageIcon("images/title/Title03.png");

		JLabel topPane1 = new JLabel(title1);
		JLabel topPane2 = new JLabel(title2);
		JButton button = new JButton("시작버튼", title3);

		topPane1.setPreferredSize(new Dimension(900, 150));
		topPane2.setPreferredSize(new Dimension(900, 700));
		button.setPreferredSize(new Dimension(900, 150));
		button.addActionListener(this);
		add(topPane1, BorderLayout.PAGE_START);
		add(topPane2, BorderLayout.CENTER);
		add(button, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		PAd.change("Kiosk01");
	}
}
