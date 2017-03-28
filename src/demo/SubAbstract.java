package demo;

public abstract class SubAbstract extends AbstractClass{

	public abstract void submethod();
	
	@Override
	public void method1() {
		System.out.println("SubAbstract=method1");
	}
	@Override
	public void method2() {
		super.method2();
		System.out.println("SubAbstract=method2");
	}
	
	public static void main(String[] args) {
//		SubAbstract subAbstract = new SubAbstract();
//		subAbstract.method1();
//		subAbstract.method2();
	}
	
}
