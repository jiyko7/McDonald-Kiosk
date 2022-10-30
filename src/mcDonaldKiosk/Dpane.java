package mcDonaldKiosk;

import java.awt.BorderLayout;
import java.awt.Color;
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

import food.Drink;
import subManager.DrinkMgr;

class Dpane extends JPanel {
	DrinkMgr drinkMgr = DrinkMgr.getInstance();

	public Dpane() {
		ArrayList<Drink> drinkList = drinkMgr.mList;

		int row = drinkList.size() % 3;
		if (row == 0) {
			row = drinkList.size() / 3;
		} else {
			row = drinkList.size() / 3 + 1;
		}

		this.setLayout(new GridLayout(row, 3, 5, 5));

		JLabel labels[] = new JLabel[(drinkList.size())];
		JLabel pricelabel, bestLabel;
		JPanel menuP[] = new JPanel[(drinkList.size())];
		for (int i = 0; i < drinkList.size(); i++) {

			Drink Btemp = drinkList.get(i);
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
			bestLabel.setForeground(Color.green.darker());
			bestLabel.setHorizontalAlignment(JLabel.CENTER);
			Itemp = Btemp.name;
			pricelabel = new JLabel();
			labels[i] = new JLabel(Icon);
			labels[i].setText(Itemp);
			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			pricelabel.setText(Btemp.price + "원");
			pricelabel.setForeground(Color.red);
			pricelabel.setHorizontalAlignment(JLabel.CENTER);

			labels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel label = (JLabel) e.getSource();
					findAdd(label);
					if (Kiosk01.noSet) {
						JOptionPane.showMessageDialog(null, label.getText() + "가 장바구니에 추가되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, label.getText() + "가 음료로 선택되었습니다.\n사이드를 골라주세요");
						Kiosk01.Spane = new Spane();
						Kiosk01.change("Spane", Kiosk01.panel);
						Kiosk01.setPlease();
					}
				}
			});
			bestLabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			labels[i].setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			pricelabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 15));

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
		Drink temp = drinkMgr.find(label.getText());
		Kiosk01.add(temp);
	}
}
