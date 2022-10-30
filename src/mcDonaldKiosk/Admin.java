package mcDonaldKiosk;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Scanner;
import subManager.BurgerMgr;
import subManager.DrinkMgr;
import subManager.SetMenuMgr;
import subManager.SideMgr;
import subManager.SideOtherMgr;
import subManager.SpecialMgr;

public class Admin {
	Scanner scan = new Scanner(System.in);

	SubFood subFoodManager = new SubFood();
	SubSet subSetManager = new SubSet();

	void readData() {
		subFoodManager.readFile();
		subSetManager.readFile();
		readPassword();

	}

	void savePassword() {
		File file = new File("PASSWORD.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(password.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 쓰기 실패");
		}

	}

	public void readPassword() {
		Scanner filein = null;
		try {
			filein = new Scanner(new File("PASSWORD.txt"));
			password = filein.nextLine();
		} catch (Exception e) {
			System.out.println("PASSWORD.txt : 파일 없음");
			System.exit(0);
		}
		filein.close();
	}

	String password = "0000";

	void run() {
		readData();
		if (receivePassword()) {
			while (true) {
				printMenu();
			}

		}
	}

	static void saveFile() {
		sort();
		BurgerMgr.getInstance().saveAll("Burger.txt");
		DrinkMgr.getInstance().saveAll("Drink.txt");
		SideMgr.getInstance().saveAll("SideMenu.txt");
		SideOtherMgr.getInstance().saveAll("OtherMenu.txt");
		SetMenuMgr.getInstance().saveAll("Set.txt");
		SpecialMgr.getInstance().saveAll("Special.txt");

	}

	void printMenu() {
		subFoodManager.menuPrint();
		subSetManager.setPrint();
		subSetManager.deleteSp();
		while (true) {

			saveFile();
			
			int temp = 0;
			sort();
			System.out.print("======================\n");
			System.out.print("맥날관리자시스템입니다.\n");
			System.out.print("메뉴 관련 : 1번\n");
			System.out.print("세트 관련 : 2번\n");
			System.out.print("행사 관련 : 3번\n");
			System.out.print("파일 저장 : 4번\n");
			System.out.print("관리자 비밀번호 변경 : 5번\n");
			System.out.print("키오스크 ON : 6번\n");
			System.out.print("메뉴출력 : 7번\n");
			System.out.print("세트출력 : 8번\n");
			System.out.print("종료 0번 \n");
			System.out.print("==>");
			try {
				temp = Integer.valueOf(scan.next());
			} catch (NumberFormatException e) {
				temp = 35;
			}

			switch (temp) {
			case 0:
				sort();
				saveFile();
				System.exit(0);
			case 1:
				foodView(scan);
				break;
			case 2:
				setView(scan);
				break;
			case 3:
				eventView(scan);
				break;
			case 4:
				saveFile();
				break;
			case 5:
				modifiedPassword();
				break;
			case 6:
				sort();
				new PanelAdmin(this);
				break;
			case 7:
				sort();
				subFoodManager.menuPrint();
				break;
			case 8:
				sort();
				subSetManager.setPrint();
				break;
			default:
				System.out.println("존재하지 않는 번호입니다.");
				break;
			}
			
		}

	}

	void foodView(Scanner scan) {
		System.out.printf("=========== 메뉴보기 =======\n");
		System.out.printf("메뉴추가 1번\n");
		System.out.printf("메뉴수정 2번\n");
		System.out.printf("메뉴삭제 3번\n");
		System.out.printf("===그 이외는 이전으로 돌아가기====\n");
		System.out.printf("==>");
		int temp = 35;
		try {
			temp = Integer.valueOf(scan.next());
		} catch (NumberFormatException e) {
			temp = 35;
		}
		switch (temp) {
		case 1:
			subFoodManager.addMenu(scan);
			break;
		case 2:
			subFoodManager.modifiedMenu(scan, subSetManager);
			break;
		case 3:
			subFoodManager.deleteMenu(scan, subSetManager);
			break;
		default:
			return;
		}
	}

	void setView(Scanner scan) {
		System.out.printf("=========== 세트메뉴 =======\n");
		System.out.printf("세트추가 1번\n");
		System.out.printf("세트수정 2번\n");
		System.out.printf("세트삭제 3번\n");
		System.out.printf("===그 이외는 이전으로 돌아가기====\n");
		System.out.printf("==>");
		int temp = 35;
		try {
			temp = Integer.valueOf(scan.next());
		} catch (NumberFormatException e) {
			temp = 35;
		}
		switch (temp) {
		case 1:
			subSetManager.addSet(scan);
			break;
		case 2:
			subSetManager.modifySet(scan);
			break;
		case 3:
			subSetManager.deleteSet(scan);
			break;
		default:
			return;
		}
	}

	void eventView(Scanner scan) {
		System.out.printf("=========== 행사메뉴 =======\n");
		System.out.printf("행사추가 1번\n");
		System.out.printf("행사수정 2번\n");
		System.out.printf("행사삭제 3번\n");
		System.out.printf("===그 이외는 이전으로 돌아가기====\n");
		System.out.printf("==>");
		int temp = 35;
		try {
			temp = Integer.valueOf(scan.next());
		} catch (NumberFormatException e) {
			temp = 35;
		}
		switch (temp) {
		case 1:
			subSetManager.addSp(scan);
			break;
		case 2:
			subSetManager.modifySp(scan);
			break;
		case 3:
			subSetManager.deleteSp();
			break;
		default:
			return;
		}
	}

	public static void sort() {
		Collections.sort(BurgerMgr.getInstance().mList);
		Collections.sort(DrinkMgr.getInstance().mList);
		Collections.sort(SideMgr.getInstance().mList);

	}

	private Boolean receivePassword() {
		while (true) {
			System.out.println("관리자용 비밀번호를 입력하십시오 ");
			String temp = scan.next();
			if (temp.length() == password.length()) {
				if (temp.contentEquals(password))
					return true;
			}
			System.out.println("틀렸습니다 다시 입력하세요");
		}

	}

	String passwordReturn() {
		return password;
	}

	public void modifiedPassword() {
		if (receivePassword()) {
			System.out.println("변경하고 싶은 비밀번호를 입력하십시오: ");
			password = scan.next();
			System.out.println("비밀번호를 변경하였습니다.");
			savePassword();
		}
	}

	public static void main(String[] args) {
		Admin a = new Admin();
		a.run();
	}
}