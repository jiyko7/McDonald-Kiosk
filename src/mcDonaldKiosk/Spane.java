package mcDonaldKiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import food.Food;
import subManager.SideMgr;
import subManager.SideOtherMgr;

class Spane extends JPanel {
	SideMgr sideMgr = SideMgr.getInstance();
	SideOtherMgr sideOtherMgr = SideOtherMgr.getInstance();

	public Spane() {
		ArrayList<Food> SideList = new ArrayList<>();

		SideList.addAll(sideMgr.mList);
		if (Kiosk01.noSet) {
			SideList.addAll(sideOtherMgr.mList);
		}
		Collections.sort(SideList);
		int row = SideList.size() % 3;
		if (row == 0) {
			row = SideList.size() / 3;
		} else {
			row = SideList.size() / 3 + 1;
		}
		this.setLayout(new GridLayout(row, 3, 5, 5));

		JLabel labels[] = new JLabel[(SideList.size())];
		JLabel pricelabel, bestLabel;
		JPanel menuP[] = new JPanel[(SideList.size())];

		for (int i = 0; i < SideList.size(); i++) {
			Food Btemp = SideList.get(i);
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
			bestLabel.setHorizontalAlignment(JLabel.CENTER);

			bestLabel = new JLabel();
			bestLabel.setText(Itemp);
			bestLabel.setHorizontalAlignment(JLabel.CENTER);
			Itemp = Btemp.name;
			pricelabel = new JLabel();
			labels[i] = new JLabel(Icon);
			labels[i].setText(Itemp);
			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			pricelabel.setText(Btemp.price + "원");
			pricelabel.setHorizontalAlignment(JLabel.CENTER);

			labels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel label = (JLabel) e.getSource();
					findAdd(label);
					if (Kiosk01.noSet) {
						JOptionPane.showMessageDialog(null, label.getText() + "가 장바구니에 추가되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, label.getText() + "가 사이드로 선택되었습니다.");
						Kiosk01.noSet = true;
						Kiosk01.Spane = new Spane();
						Kiosk01.change("Mpane", Kiosk01.panel);
						Kiosk01.setPlease();
					}
				}
			});
			pricelabel.setForeground(Color.red);
			bestLabel.setForeground(Color.green.darker());

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
		Food temp = sideMgr.find(label.getText());
		if (temp == null)
			temp = sideOtherMgr.find(label.getText());
		Kiosk01.add(temp);
	}
}