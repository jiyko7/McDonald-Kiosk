package mcDonaldKiosk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import food.Burger;
import food.Drink;
import food.Food;
import food.SetMenu;
import food.SideMenu;
import food.Special;
import subManager.BurgerMgr;
import subManager.DrinkMgr;
import subManager.SetMenuMgr;
import subManager.SideMgr;
import subManager.SideOtherMgr;
import subManager.SpecialMgr;

@SuppressWarnings("serial")
public class Kiosk01 extends JPanel {
	static ArrayList<Food> BBasket = new ArrayList<>();
	static ArrayList<SetMenu> SetList = new ArrayList<>();
	static Kiosk02 k2 = null;
	static int SetPrice = 0;
	static boolean isClose = false;
	static JPanel panel;
	static DefaultTableModel tableModel;
	static JScrollPane scrollPane;
	static boolean noSet = true;
	static Bpane Bpane;
	static Dpane Dpane;
	static Spane Spane;
	static Mpane Mpane;
	static int Allprice = 0;
	static int sellNumber = 1;
	static ImageIcon logoPicture;
	static JLabel logo;
	static JButton BB;
	static JButton DB;
	static JButton SB;
	static JButton MB;
	static JButton calcBtn;
	String password = null;

	PanelAdmin PAd;
	JPanel BottomPane = new JPanel();
	String adminPassWord = null;

	public Kiosk01(Admin admin, PanelAdmin P_Admin) {

		PAd = P_Admin;
		adminPassWord = admin.passwordReturn();
		ArrayList<String> beforeMenuList = new ArrayList<>();
		startKiosk(beforeMenuList);
	}

	public Kiosk01(ArrayList<String> beforeMenuList, PanelAdmin P_Admin) {
		PAd = P_Admin;
		startKiosk(beforeMenuList);
	}

	static void logoPlease() {

		logoPicture = new ImageIcon("images/title/로고.png");
		logo.setIcon(logoPicture);
	}

	static void setPlease() {
		logoPicture = new ImageIcon("images/title/세트구매.png");
		logo.setIcon(logoPicture);
	}

	public void startKiosk(ArrayList<String> beforeMenuList) {
		PanelAdmin.setUIFont(new javax.swing.plaf.FontUIResource("NanumBarunGothic", java.awt.Font.BOLD, 30));
		setPreferredSize(new Dimension(950, 1000));
		panel = new JPanel();
		Bpane = new Bpane();
		Dpane = new Dpane();
		Spane = new Spane();
		Mpane = new Mpane();
		logo = new JLabel(logoPicture);
		logoPlease();
		logo.setPreferredSize(new Dimension(700, 50));
		add(logo);
		setLayout(new FlowLayout());
		JButton callAdmin = new JButton("관리자호출");
		callAdmin.setPreferredSize(new Dimension(200, 50));
		callAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					password = JOptionPane.showInputDialog("비밀번호를 입력하세요");
					if (password.equals(adminPassWord)) {
						Allprice = 0;
						PAd.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
					}
				} catch (NullPointerException e1) {
					password = "";
				}
			}
		});
		add(callAdmin);

		BB = new JButton("버거");
		BB.setPreferredSize(new Dimension(220, 20));
		BB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kiosk01.change("Bpane", panel);
			}
		});

		add(BB);
		DB = new JButton("음료");
		DB.setPreferredSize(new Dimension(220, 20));
		DB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kiosk01.change("Dpane", panel);
			}
		});
		add(DB);
		SB = new JButton("사이드");
		SB.setPreferredSize(new Dimension(220, 20));
		SB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kiosk01.change("Spane", panel);

			}
		});
		add(SB);
		MB = new JButton("행사 및 세트");
		MB.setPreferredSize(new Dimension(220, 20));
		MB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kiosk01.change("Mpane", panel);
				Kiosk01.setPlease();
			}
		});
		add(MB);
		BB.setFont(new Font("NanumBarunGothic", java.awt.Font.BOLD, 20));
		DB.setFont(new Font("NanumBarunGothic", java.awt.Font.BOLD, 20));
		SB.setFont(new Font("NanumBarunGothic", java.awt.Font.BOLD, 20));
		MB.setFont(new Font("NanumBarunGothic", java.awt.Font.BOLD, 20));

		panel.add(Bpane);
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(940, 790));
		add(scroll, BorderLayout.CENTER);
		scroll.setBackground(Color.white);

		BottomPane.setPreferredSize(new Dimension(940, 140));
		String col[] = { "상품", "가격" };

		tableModel = new DefaultTableModel(col, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable table = new JTable(tableModel);
		table.setBackground(Color.white);
		table.setFont(new Font("NanumBarunGothic", java.awt.Font.BOLD, 15));
		if (beforeMenuList == null) {

		} else {
			addBeforeMenuList(beforeMenuList);
		}

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(690, 120));

		JPanel controlBar = new JPanel();
		controlBar.setPreferredSize(new Dimension(230, 105));
		calcBtn = new JButton("결제하기");
		calcBtn.setFont(new Font("NanumBarunGothic", Font.BOLD, 25));
		calcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int burgerAmount = chekBurger(tableModel);
				int drinkAmount = chekDrink(tableModel);
				int sideAmount = chekSide(tableModel);
				int priceAll = Allprice;
				Basket bas = new Basket(burgerAmount, drinkAmount, sideAmount, priceAll);
				priceAll = bas.CalculateBasket();
				callBasketKiosk(tableModel, Allprice, priceAll);
				Allprice = 0;
				bas.initBasket();
				tableModel.setNumRows(0);

			}
		});

		calcBtn.setPreferredSize(new Dimension(220, 50));
		calcBtn.setBackground(new Color(15, 223, 0));
		JButton cancelBtn = new JButton("삭제");
		cancelBtn.setFont(new Font("NanumBarunGothic", Font.BOLD, 25));
		cancelBtn.setPreferredSize(new Dimension(220, 30));
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					return;
				}

				Object value = table.getValueAt(table.getSelectedRow(), 1);
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);

				if (chekSet(tableModel) > 0) {
					StringBuffer a = new StringBuffer();
					a.append(name);
					a.append("세트");
					try {
						for (int i = 0; i < tableModel.getRowCount(); i++) {
							if (a.toString().equals((String) table.getValueAt(i, 0))) {
								tableModel.removeRow(i);
							}
						}

					} catch (ArrayIndexOutOfBoundsException o) {
					}
				}

				int price = Integer.valueOf(value.toString());
				tableModel.removeRow(table.getSelectedRow());
				if (price < 0)
					return;
				Allprice -= price;
				if (price != Allprice) {
					while (Integer.min(chekSide(tableModel), chekDrink(tableModel)) < chekSet(tableModel)) {
						deleteSub(tableModel);
					}
				}

			}
		});
		BottomPane.add(scrollPane, BorderLayout.WEST);
		scrollPane.setBackground(Color.white);
		calcBtn.setBackground(Color.pink);
		cancelBtn.setBackground(Color.white);
		controlBar.add(calcBtn, BorderLayout.NORTH);
		controlBar.add(cancelBtn, BorderLayout.SOUTH);
		controlBar.setBackground(Color.white);
		BottomPane.add(controlBar, BorderLayout.EAST);
		BottomPane.setBackground(Color.white);
		panel.setBackground(Color.white);

		add(BottomPane, BorderLayout.PAGE_END);
		panel.setBackground(Color.white);

		setVisible(true);

	}

	void callBasketKiosk(DefaultTableModel dt, int nowPrice, int salePrice) {
		ArrayList<String> tempBasket = new ArrayList<>();

		for (int i = 0; i < dt.getRowCount(); i++) {
			tempBasket.add(String.valueOf(dt.getValueAt(i, 0)));
		}
		k2 = new Kiosk02(tempBasket, nowPrice, salePrice,
				checkSetMenu(dt), PAd);
		PAd.change("Kiosk02");
	}

	int chekBurger(DefaultTableModel t) {
		int checkBurger = 0;
		for (int i = 0; i < t.getRowCount(); i++) {
			String temp = (String) t.getValueAt(i, 0);
			Burger f = (Burger) BurgerMgr.getInstance().find(temp);
			if (f != null) {
				Special sp = (Special) SpecialMgr.getInstance().find(temp);
				if (sp == null) {
					SetMenu s = (SetMenu) SetMenuMgr.getInstance().find(temp);
					if (s != null) {
						BBasket.add(f);
						checkBurger++;
					}
				} else {
					BBasket.add(f);
					checkBurger++;
				}
			}
		}
		return checkBurger;
	}

	void deleteSub(DefaultTableModel T) {
		int minSet = -50000;
		for (int i = 0; i < T.getRowCount(); i++) {
			Integer temp = (Integer) T.getValueAt(i, 1);
			if (temp > 0)
				continue;
			if (minSet < temp)
				minSet = temp;
		}
		for (int i = 0; i < T.getRowCount(); i++) {
			if ((Integer) T.getValueAt(i, 1) == minSet) {
				tableModel.removeRow(i);
				break;
			}
		}
	}

	public static void buttonSwitch(Boolean temp) {
		BB.setEnabled(temp);
		DB.setEnabled(temp);
		SB.setEnabled(temp);
		MB.setEnabled(temp);
		calcBtn.setEnabled(temp);
	}

	public static void change(String panelName, JPanel panel) {
		if (panelName.equals("Bpane")) {
			logoPlease();
			panel.removeAll();
			panel.add(Bpane);
			panel.revalidate();
			panel.repaint();
		}
		if (panelName.equals("Dpane")) {
			logoPlease();
			buttonSwitch(noSet);
			panel.removeAll();
			panel.add(Dpane);
			panel.revalidate();
			panel.repaint();
		}
		if (panelName.equals("Spane")) {
			logoPlease();
			buttonSwitch(noSet);
			panel.removeAll();
			panel.add(Spane);
			panel.revalidate();
			panel.repaint();
		}
		if (panelName.equals("Mpane")) {
			buttonSwitch(noSet);
			panel.removeAll();
			panel.add(Mpane);
			panel.revalidate();
			panel.repaint();
			setPlease();

		}

	}

	int checkSetMenu(DefaultTableModel t) {
		int checkCount = 0;
		for (int i = 0; i < t.getRowCount(); i++) {
			String temp = (String) t.getValueAt(i, 0);
			for (Burger b : BurgerMgr.getInstance().mList) {
				if (temp.equals(b.name))
					checkCount++;
			}
		}
		int temp = chekDrink(t);
		if(checkCount>=temp)
			checkCount = temp;
		temp = chekSide(t);
		if(checkCount>=temp)
			checkCount = temp;
		return checkCount;
	}
	
	
	int chekSet(DefaultTableModel t) {
		int checkSet = 0;
		for (int i = 0; i < t.getRowCount(); i++) {
			if (((String) t.getValueAt(i, 0)).contains("세트"))
				checkSet++;
		}
		return checkSet;
	}

	int chekSide(DefaultTableModel t) {
		int sideCount = 0;
		for (int i = 0; i < t.getRowCount(); i++) {
			String temp = (String) t.getValueAt(i, 0);
			for (SideMenu sm : SideMgr.getInstance().mList) {
				if (temp.equals(sm.name))
					sideCount++;
			}
		}
		return sideCount;
	}

	int chekDrink(DefaultTableModel t) {
		int drinkCount = 0;
		for (int i = 0; i < t.getRowCount(); i++) {
			String temp = (String) t.getValueAt(i, 0);
			for (Drink dr : DrinkMgr.getInstance().mList) {
				if (temp.equals(dr.name))
					drinkCount++;
			}
		}
		return drinkCount;
	}

	public static void add(Food temp) {
		Object[] data = { temp.name, temp.price };
		Allprice += temp.price;
		tableModel.addRow(data);
		scrollPane.revalidate();
		scrollPane.repaint();

	}

	public static void addSet(SetMenu set) {
		Object[] data = { set.name, set.getPrice() * -1 };
		tableModel.addRow(data);
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	public static void addBeforeMenuList(ArrayList<String> beforeMenuList) {
		for (String s : beforeMenuList) {
			if (s.contains("세트")) {
				SetMenu set = SpecialMgr.getInstance().find(s);
				if (set == null) {
					set = SetMenuMgr.getInstance().find(s);
				}
				addSet(set);
			} else {
				Food f = BurgerMgr.getInstance().find(s);
				if (f == null) {
					f = DrinkMgr.getInstance().find(s);
				}
				if (f == null) {
					f = SideMgr.getInstance().find(s);
				}
				if (f == null) {
					f = SideOtherMgr.getInstance().find(s);
				}
				add(f);
			}

		}
	}

}
