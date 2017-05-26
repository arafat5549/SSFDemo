package test.demo;

public class StringDemo {

	public static void main(String[] args) {
		String  text="11111#222#444#444";
		String regex ="#";
		
		for (int i = 0; i < 100; i++) {
			text.split(regex);
		}
		
	}
}
