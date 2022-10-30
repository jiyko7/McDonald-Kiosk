package mcDonaldKiosk;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;

public class Manager<T extends Manageable> {
	public ArrayList<T> mList = new ArrayList<>();

	public T find(String kwd) {
		for (T m : mList)
			if (m.matches(kwd))
				return m;
		return null;
	}

	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	public void printAll() {
		int number = 0;
		for (T m : mList) {
			System.out.printf("%d.", number);
			m.print();
			number++;
		}
	}

	public void search(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (T m : mList) {
				if (m.matches(kwd))
					m.print();
			}
		}
	}

	public Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println(filename + ": 파일 없음");
			System.exit(0);
		}
		return filein;
	}

	public void saveAll(String filename) {
		File file = new File(filename);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while (true) {
				for (T m : mList) {
					String data = null;
					data = m.getLine();
					if (data.isEmpty()) {
						break;
					}
					fos.write(data.getBytes());
				}
				break;
			}
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 쓰기 실패");
		}

	}

	public void newAdd(Scanner scan, Factory<T> fac) {
		T m = null;
		m = fac.create();
		m.add(scan);
		mList.add(m);
	}

	public void remove(Scanner scan) {
		System.out.println("삭제할 번호 입력하세요>>");
		try {
			mList.remove(scan.nextInt());
		} catch (InputMismatchException e) {
			return;
		}

	}

	public void modifyFinder(Scanner scan) {
		System.out.println("수정할 번호를 입력하세요.>>");
		int findIndex;
		try {
			findIndex = scan.nextInt();
		} catch (InputMismatchException e) {
			return;
		}
		mList.get(findIndex).modify(scan);
	}

	public T popObject(int kwd) {
		T temp = mList.get(kwd);
		mList.remove(kwd);
		return temp;
	}

}
