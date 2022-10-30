package mcDonaldKiosk;

import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class PanelAdmin extends JFrame {

	private Admin A = null;
	public Kiosk00 kiosk00 = null;
	public Kiosk01 kiosk01 = null;

	public void change(String panelName) {
		if (panelName.equals("Kiosk00")) {
			getContentPane().removeAll();
			getContentPane().add(kiosk00);
			revalidate();
			repaint();
		} else if (panelName.equals("Kiosk02")) {
			getContentPane().removeAll();
			getContentPane().add(Kiosk01.k2);
			revalidate();
			repaint();
		} else if (panelName.equals("Kiosk01")) {
			getContentPane().removeAll();
			kiosk01 = new Kiosk01(A, this);
			getContentPane().add(kiosk01);
			revalidate();
			repaint();
		}
	}

	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, f);
			}
		}
	}

	public PanelAdmin(Admin admin) {
		setTitle("McDonaldKiosk");
		A = admin;
		kiosk00 = new Kiosk00(this);
		kiosk01 = new Kiosk01(admin, this);
		add(kiosk01);
		add(kiosk00);
		setSize(950, 1050);
		setPreferredSize(new Dimension(950, 1050));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		this.setLocationRelativeTo(null);

	}
}
