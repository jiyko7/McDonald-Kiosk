package mcDonaldKiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import food.Burger;
import subManager.BurgerMgr;

class Bpane extends JPanel {

	BurgerMgr burgerMgr = BurgerMgr.getInstance();

	public Bpane() {
		ArrayList<Burger> BurgerList = burgerMgr.mList;

		int row = BurgerList.size() % 3;
		if (row == 0) {
			row = BurgerList.size() / 3;
		} else {
			row = BurgerList.size() / 3 + 1;
		}
		this.setLayout(new GridLayout(row, 3, 5, 5));

		JLabel labels[] = new JLabel[(BurgerList.size())];
		JLabel pricelabel, bestLabel;
		JPanel menuP[] = new JPanel[(BurgerList.size())];
		for (int i = 0; i < BurgerList.size(); i++) {
			Burger Btemp = BurgerList.get(i);
			String Itemp = "images/" + Btemp.name + ".png";
			File image = new File(Itemp);
			ImageIcon Icon = null;
			if (image.isFile()) {
				Icon = new ImageIcon(Itemp);

			} else {

				Icon = new ImageIcon("준비중.png");

			}
			if (i < 3) {
				if (i == 0)
					Itemp = "<BEST>  ";
				else
					Itemp = "<HOT>  ";
			} else {
				Itemp = " ";
			}
			bestLabel = new JLabel();
			bestLabel.setText(Itemp);
			Color color1 = Color.green.darker();

			bestLabel.setForeground(color1);

			bestLabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			bestLabel.setHorizontalAlignment(JLabel.CENTER);
			Itemp = Btemp.name;
			pricelabel = new JLabel();
			labels[i] = new JLabel(Icon);
			labels[i].setText(Itemp);
			labels[i].setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			pricelabel.setText(Btemp.price + "원");
			pricelabel.setForeground(Color.red);
			pricelabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			pricelabel.setHorizontalAlignment(JLabel.CENTER);

			labels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel label = (JLabel) e.getSource();
					findAdd(label);
					JOptionPane.showMessageDialog(null, label.getText() + "가 장바구니에 추가되었습니다.");

				}
			});

			menuP[i] = new JPanel();
			menuP[i].setLayout(new BorderLayout());
			menuP[i].add(bestLabel, BorderLayout.NORTH);
			menuP[i].add(labels[i], BorderLayout.CENTER);
			menuP[i].add(pricelabel, BorderLayout.SOUTH);
			menuP[i].setBackground(Color.white);
			this.add(menuP[i]);
		}
	}

	public void findAdd(JLabel label) {
		Burger temp = burgerMgr.find(label.getText());
		Kiosk01.add(temp);
	}
}