package food;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import mgr.Manageable;
import subManager.BurgerMgr;

public class SetMenu implements Manageable,Comparable<SetMenu>{
   BurgerMgr burgerMgr = BurgerMgr.getInstance();
   
   
   public String name;
   private int price;
   public ArrayList<Food> haveBurger = new ArrayList<>();

   @Override
   public void read(Scanner scan) {
	   name = scan.next();
      setPrice(scan.nextInt());
      haveBurger.add(burgerMgr.find(scan.next()));
   }

   @Override
   public void print() {
      System.out.printf("%s-%d원 할인\n", name,getPrice());
      System.out.print("다음과 같은 버거 포함시 할인:\n");
      if(haveBurger == null)
      {
         System.out.print("오류");
         return;
      }
      for(Food o:haveBurger)
      {
         o.print();
      }
   }

   @Override
   public boolean matches(String kwd) {
         if(kwd.contentEquals(name))
            return true;
         for(Food f: haveBurger) {
            if(kwd.contentEquals(f.name))
               return true;
         }
            
         return false;
      }

   @Override
   public void add(Scanner scan) {
      String temp;
      System.out.print("세트메뉴 입력(세트가 자동으로 붙습니다 xx세트) : ");
      StringBuffer A = new StringBuffer();
	   A.append(scan.next());
	   A.append("세트");
	   name = A.toString();
	   A.delete(0,A.capacity());
      System.out.print("할인가격 입력:");
      try {
         setPrice(scan.nextInt());
      }
      catch(InputMismatchException e)
      {
         System.out.println("다시입력하세요");
         return;
      }

      System.out.print("세트메뉴에 포함할 버거들 입력 : ");
         temp = scan.next();
         Food tempBurger = burgerMgr.find(temp);
         if (tempBurger == null) {
            System.out.print("버거가 없습니다. 재 입력 바랍니다.");
         }
         haveBurger.add(tempBurger);
   }

   @Override
   public String getLine() {
      String a = name+" "+getPrice();
      for(Food b:haveBurger)
      {
         a=a+" "+b.name+"\n";
      }
      return a;
   }


   public void modify(Scanner scan) {
         ArrayList <Food> modifyList = new ArrayList<>();
         scan.nextLine();
         System.out.println("수정할 내용을 작성해주세요.");
         System.out.printf("세트메뉴  : %s -> ", name);
         String temp = scan.nextLine();
         if (temp.length() > 0)
            name = temp;

         System.out.printf("할인가격 : %d -> ", getPrice());
         temp = scan.nextLine();
         if (temp.length() > 0)
            try {
               setPrice(Integer.parseInt(temp));   
            }
            catch(NumberFormatException e){
               System.out.printf("기입오류입니다.\n");
               setPrice(0);
            }
            
         for (Food b : haveBurger) {
            while(true) {
               System.out.printf("버거  : %s -> ", b.name);
               temp = scan.nextLine();
               if (temp.length() > 0) {
                  Food tempBurger = burgerMgr.find(temp);
                  if (temp.equals("0"))
                     break;
                  if (tempBurger == null) {
                     System.out.print("버거가 없습니다. 재 입력 바랍니다.");
                     continue;
                  }
                  modifyList.add(tempBurger);
                  break;
               }
               modifyList.add(b);
               break;
            }
         }
         haveBurger = modifyList;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }
   @Override
   public int compareTo(SetMenu o) {
      if(this.price < o.price)
         return 1;
      else if(this.price == o.price)
         return 0;
      else {
         return -1;
      }
   }
}

   