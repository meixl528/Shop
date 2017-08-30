package Test.Abstract;

public abstract class Abs extends aa{

	public Abs() {
		super();
	}
	
	abstract void method();
	
	public static void get(){
		System.err.println(111);
	}
	
	public static void main(String[] args) {
		get();
		String aa = new String("11");
		String bb = new String("11");
		System.out.println(aa==bb);
		
		String cc = "1";
		String dd = "1";
		System.out.println(cc==dd);
		
		String ee = "111";
		String ff = new String("111");
		System.out.println(ee==ff);
		
		
	}

	
	
}
