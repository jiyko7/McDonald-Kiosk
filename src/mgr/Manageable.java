package mgr;

import java.util.Scanner;

public interface Manageable  {
    void read(Scanner scan);
    void print();
    boolean matches(String kwd);
    void add(Scanner scan);
    void modify(Scanner scan);
	String getLine();
}
