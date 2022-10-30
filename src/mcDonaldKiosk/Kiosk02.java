package mcDonaldKiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import food.SetMenu;
import subManager.SetMenuMgr;

public class Kiosk02 extends JPanel {
	static ArrayList<String> nowMenuList;
	static PanelAdmin PAd;
	static ArrayList<String> tempList = new ArrayList<>();

	public Kiosk02(ArrayList<String> beforeMenuList, int nowPrice, int salePrice, int setAmount, PanelAdmin P_Admin) {
		this.setLayout(new BorderLayout());

		PAd = P_Admin;
		nowMenuList = new ArrayList<>();
		JTextField reciptTitle = new JTextField();
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(1000, 120));
		JPanel title = new JPanel();
		JPanel Center = new JPanel();
		Center.setLayout(new BorderLayout());
		Center.setPreferredSize(new Dimension(1000, 700));
		reciptTitle.setHorizontalAlignment(JTextField.CENTER);
		reciptTitle.setPreferredSize(new Dimension(800, 50));
		reciptTitle.setText("접수번호  " + Kiosk01.sellNumber);
		reciptTitle.setEditable(false);
		reciptTitle.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));
		top.add(reciptTitle, BorderLayout.NORTH);

		JTextField BTitle = new JTextField();
		BTitle.setHorizontalAlignment(JTextField.CENTER);
		BTitle.setPreferredSize(new Dimension(500, 50));
		BTitle.setText("주문하셨던 물품");
		BTitle.setEditable(false);

		BTitle.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));

		JTextField CTitle = new JTextField();
		CTitle.setHorizontalAlignment(JTextField.CENTER);
		CTitle.setPreferredSize(new Dimension(500, 50));
		CTitle.setText("저렴계산적용");
		CTitle.setEditable(false);

		CTitle.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));

		title.add(BTitle, BorderLayout.WEST);
		title.add(CTitle, BorderLayout.EAST);

		top.add(title, BorderLayout.SOUTH);
		this.add(top, BorderLayout.NORTH);

		BasketPane B = new BasketPane(beforeMenuList);

		JScrollPane scrollPane1 = new JScrollPane(B);
		scrollPane1.setPreferredSize(new Dimension(465, 690));

		Center.add(scrollPane1, BorderLayout.WEST);
		JTextField CheckCalc = new JTextField();
		
		
		
		for (String st : beforeMenuList) {
			if (!st.contains("세트")) {
				nowMenuList.add(st);
			}
			if (st.contains("세트")) {
				SetMenu temp = SetMenuMgr.getInstance().find(st);
				if (temp != null) {
					nowPrice = nowPrice - temp.getPrice();
				}
			}
		}

		
		
		JTextField BPrice = new JTextField();
		BPrice.setHorizontalAlignment(JTextField.CENTER);
		BPrice.setPreferredSize(new Dimension(500, 50));
		BPrice.setText("할인전가격 : " + nowPrice);

		BPrice.setEditable(false);
		BPrice.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));

		if (salePrice == nowPrice) {
			nowMenuList = beforeMenuList;
			CheckCalc.setText("최고의 선택입니다");
		} else {
			Iterator<SetMenu> tempSet = Kiosk01.SetList.iterator();

			for (int i = 0; i < setAmount; i++) {
				nowMenuList.add(tempSet.next().name);
			}
			int tempPrice = nowPrice - salePrice;
			CheckCalc.setText("저렴결제가 적용되었습니다 \n할인된 가격은 " + tempPrice + "원 입니다");
		}
		Collections.sort(nowMenuList,new Comparator<String>() {
			public int compare(String o1, String o2) {
				if (o1.contains("세트"))
					return -1;
				return 1;
			}
		});
	
		CheckCalc.setHorizontalAlignment(JTextField.CENTER);
		CheckCalc.setPreferredSize(new Dimension(700, 50));

		
		CheckCalc.setForeground(Color.red);
		CheckCalc.setFont(new Font("NanumBarunGothic", Font.BOLD, 20));
		CheckCalc.setEditable(false);

		JTextField CPrice = new JTextField();
		CPrice.setHorizontalAlignment(JTextField.CENTER);
		CPrice.setPreferredSize(new Dimension(500, 50));
		CPrice.setText("저렴결제 적용가격 : " + salePrice);
		CPrice.setEditable(false);
		CPrice.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));

		
		BasketPane c = new BasketPane(nowMenuList);
		JScrollPane scrollPane2 = new JScrollPane(c);
		scrollPane2.setPreferredSize(new Dimension(465, 690));
		Center.add(scrollPane2, BorderLayout.EAST);
		this.add(Center);

		JButton calcBtn = new JButton("결제하기");
		calcBtn.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));
		calcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (String st : nowMenuList) {
					SubFood.sellUp(st);
				}
				Kiosk01.sellNumber++;
				JLabel label = new JLabel("결제완료되었습니다");
				JLabel label2 = new JLabel("이용해주셔서 감사합니다");
				label.setFont(new Font("NanumBarunGothi", Font.BOLD, 30));
				JOptionPane.showMessageDialog(null, label.getText() + "\n" + label2.getText());
				Kiosk01.SetList.clear();
				Admin.saveFile();
				PAd.change("Kiosk00");

			}
		});
		JButton back = new JButton("이전으로");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Kiosk01.SetList.clear();
				PAd.change("Kiosk01");

			}
		});

		calcBtn.setPreferredSize(new Dimension(220, 50));
		JPanel End = new JPanel();
		End.setPreferredSize(new Dimension(1000, 190));
		
		JPanel CompPrice = new JPanel();
		CompPrice.add(BPrice, BorderLayout.WEST);
		CompPrice.add(CPrice, BorderLayout.EAST);
		JPanel Check = new JPanel();
		JTextField ShowPrice = new JTextField();
		ShowPrice.setHorizontalAlignment(JTextField.CENTER);
		ShowPrice.setPreferredSize(new Dimension(500, 50));
		ShowPrice.setText("총 결제금액 : " + salePrice);
		ShowPrice.setFont(new Font("NanumBarunGothic", Font.BOLD, 30));
		ShowPrice.setEditable(false);
		
		reciptTitle.setBackground(Color.white);
		back.setBackground(Color.white);
		calcBtn.setBackground(Color.pink);
		CompPrice.setBackground(Color.white);
		ShowPrice.setBackground(Color.white);
		title.setBackground(Color.white);
		top.setBackground(Color.white);
		Center.setBackground(Color.white);
		CheckCalc.setBackground(Color.white);
		BTitle.setBackground(Color.white);
		CTitle.setBackground(Color.white);
		BPrice.setBackground(Color.white);
		CPrice.setBackground(Color.white);
		B.setBackground(Color.white);
		c.setBackground(Color.white);
		End.setBackground(Color.white);
		Check.setBackground(Color.white);
		
		Check.add(ShowPrice, BorderLayout.WEST);
		Check.add(calcBtn, BorderLayout.EAST);
		End.add(CompPrice, BorderLayout.NORTH);
		End.add(CheckCalc, BorderLayout.CENTER);
		End.add(Check, BorderLayout.SOUTH);

		End.add(back, BorderLayout.EAST);

		this.add(End, BorderLayout.SOUTH);

	}

}
