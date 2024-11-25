// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan=  Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n =Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the ending balance of the loan, given a periodical payment
		double payment = bruteForceSolver(loan, rate, n, epsilon);
		//double endBalance = endBalance(loan, rate, n,payment );
		//System.out.println(" periodical payment is " + String.format("%.2f", payment) + ", your ending balance is: " + (int) endBalance);
		
		// Computes the periodical payment using brute force search
		System.out.println("Periodical payment, using brute force: "+String.format("%.2f",  payment));
		System.out.println("number of iterations: " + iterationCounter);

		// // Computes the periodical payment using bisection search
		payment =bisectionSolver(loan, rate, n, epsilon);
		System.out.println("Periodical payment, using bi-section search: " +String.format("%.2f", payment));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		for(int i = 0; i <n;i++){
			balance = (balance - payment);
			balance *=(1+rate/100);
		}
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter=0;
    	double payment = loan/n;
		while(endBalance(loan, rate, n, payment)>0){
			payment += epsilon;
			iterationCounter++;
		}

		return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter= 0;
        double lo = loan/n;
		double hi = loan*(1+rate/100);
		while(hi-lo>epsilon){
			double mid = (hi+lo)/2;
			if(endBalance(loan, rate, n, mid)>0){
				lo = mid;
			}
			else hi = mid;

			iterationCounter++;
		}
		return (lo+hi)/2;
    }
}