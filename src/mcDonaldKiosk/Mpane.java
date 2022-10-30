package mcDonaldKiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import food.Food;
import food.SetMenu;
import subManager.SetMenuMgr;
import subManager.SpecialMgr;

class Mpane extends JPanel {
	public Mpane() {

		Set<SetMenu> SetMenuSet = new HashSet<>();
		SetMenuSet.addAll(SetMenuMgr.getInstance().mList);
		SetMenuSet.addAll(SpecialMgr.getInstance().mList);
		ArrayList<SetMenu> SetMenuList = new ArrayList(SetMenuSet);

		int row = SetMenuList.size() % 3;
		if (row == 0) {
			row = SetMenuList.size() / 3;
		} else {
			row = SetMenuList.size() / 3 + 1;
		}
		this.setLayout(new GridLayout(row, 3, 5, 7));

		JLabel labels[] = new JLabel[(SetMenuList.size())];
		JLabel nullLabel, nullLabel1;
		JPanel menuP[] = new JPanel[(SetMenuList.size())];

		for (int i = 0; i < SetMenuList.size(); i++) {
			SetMenu Btemp = SetMenuList.get(i);
			String Itemp = "images/" + Btemp.name + ".png";
			File image = new File(Itemp);
			ImageIcon Icon = null;
			if (image.isFile()) {
				Icon = new ImageIcon(Itemp);

			} else {
				Icon = new ImageIcon("준비중.png");
			}

			nullLabel1 = new JLabel();
			nullLabel1.setText("");
			nullLabel1.setHorizontalAlignment(JLabel.CENTER);
			Itemp = Btemp.name;
			nullLabel = new JLabel();
			labels[i] = new JLabel(Icon);
			labels[i].setText(Itemp);
			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			nullLabel.setText(" ");
			nullLabel.setHorizontalAlignment(JLabel.CENTER);
			labels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					JLabel label = (JLabel) e.getSource();
					SetMenu temp = SetMenuMgr.getInstance().find(label.getText());
					if (temp==null)
						temp = SpecialMgr.getInstance().find(label.getText());
					Kiosk01.addSet(temp);
					JOptionPane.showMessageDialog(null, temp.name + "를 선택하셨습니다.\n 음료를 골라주세요");
					for (Food B : temp.haveBurger) {
						Kiosk01.add(B);
						Kiosk01.noSet = false;
						Kiosk01.change("Dpane", Kiosk01.panel);
						Kiosk01.setPlease();
					}

				}
			});
			nullLabel.setForeground(Color.red);
			labels[i].setFont(new Font("NanumBarunGothic", Font.BOLD, 15));
			menuP[i] = new JPanel();
			menuP[i].setLayout(new BorderLayout());
			menuP[i].add(nullLabel1, BorderLayout.NORTH);
			menuP[i].add(labels[i], BorderLayout.CENTER);
			menuP[i].add(nullLabel, BorderLayout.SOUTH);
			menuP[i].setBackground(Color.white);
			this.add(menuP[i]);
		}
	}
}
