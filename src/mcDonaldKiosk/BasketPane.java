package mcDonaldKiosk;

import java.awt.GridLayout;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class BasketPane extends JPanel {
	public BasketPane(ArrayList<String> beforeMenuList) {

		this.setLayout(new GridLayout(beforeMenuList.size(), 1, 15, 5));

		JLabel labels[] = new JLabel[(beforeMenuList.size())];
		for (int i = 0; i < beforeMenuList.size(); i++) {

			String Btemp = beforeMenuList.get(i);
			String Itemp = "images/" + Btemp + ".png";
			File image = new File(Itemp);
			ImageIcon Icon = null;
			if (image.isFile()) {
				Icon = new ImageIcon(Itemp);

			} else {

				Icon = new ImageIcon("준비중.png");

			}

			labels[i] = new JLabel(Icon);
			labels[i].setText("" + Btemp);

			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			this.add(labels[i]);
		}
	}
}
