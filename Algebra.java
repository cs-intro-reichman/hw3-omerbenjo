// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	  //  Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,-5));    // 5 / 5  
   		System.out.println(div(25,-7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	public static int plus(int x1, int x2) {
		
		if(x2<0){
			for(int i=0; i>x2; i--){
				x1--;
			}
			return x1;
		}
		for (int i = 0; i <x2; i++){
			x1++; 
		}
		return x1;
	}

	public static int minus(int x1, int x2) {
		if(x2<0){
			for (int i = 0; i >x2; i--) x1++; 
			return x1;
		}
		for (int i = 0; i <x2; i++)x1--; 
		return x1;
	}

	public static int times(int x1, int x2) {
		if (x2==0) return 0;
		if(x2 ==1) return x1;
		if(x1<0&&x2<0){
			x2=  Math.abs(x2);
			x1 = Math.abs(x1);
		}
		int x3 = x1;
		int x4 = x1;
			for (int i = 1; i <x2; i++) {
				x1 = plus(x3, x4);
				x3 = x1;
			}
		return x1;
	}

	public static int pow(int x, int n) {
		if(n==0) return 1;
		int x1 = x;
		int x2 = x;
		for (int i = 1; i < n; i++) {
            x1 = times(x,x2);   
			x2 = x1;
		}
		return x1;
	}

	// Returns the integer part of x1 / x2 |x1 =-15 x2 =3| => x3 = 15 x2 = -15 x1 = 15
	public static int div(int x1, int x2) {
		if(x2==0) return (int)Double.NaN;
		if(x2==1) return  x1;
		int counter = 0;
		int x3 = x1;
		if(x1<0&&x2<0){
			x1 = Math.abs(x1);
			x2 = Math.abs(x2);
			x3 =x1;
		}
		else if(x1<0||x2<0){
			x1 = Math.abs(x1);
			x2 = Math.abs(x2);
			x3 = x1;
			while(x1 >0 && x3>=x2){
				x1 = minus(x3, x2);
				x3 = x1;
				counter++;
			}
			return times(-1,counter); 
		}
		// if(x2<0){
		// 	while(x1 >0 && x3>=x2){
		// 		x1 = plus(x3, x2);
		// 		x3 = x1;
		// 		counter--;
		// 	}
		// 	return counter;
		// }
		
		while(x1 >0 && x3>=x2){
			x1 = minus(x3, x2);
			x3 = x1;
			counter++;
		}

		return counter;
	}

	public static int mod(int x1, int x2) {
		int x3;
		int result;
		x3 = div(x1, x2);
		result =  minus(x1,times(x2,x3));
		return result;
	}	
	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int tempNum =0;
		while(times(tempNum, tempNum) < x) tempNum++;
		if (times(tempNum, tempNum) > x) tempNum--;
		return tempNum;
	}	  	  
}