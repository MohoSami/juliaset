/**
 * @author      Mohammed Sami <mohammed.sami23@trinityschoolnyc.org>
 * @version     1.0
 * @since       2022-02-28
 */
public class ComplexNumber {

	private double a;
	private double b;

	/**
	 * Creates a new ComplexNumber with both real and imaginary components
	 * @param a the real component of the complex number
	 * @param b the imaginary component of the complex number
	 */
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;
	}

	/**
	 * creates a basic ComplexNumber value when provided with no params. Has a 0 value for real and imaginary parts
	 */
	public ComplexNumber(){
		this.a = 0;
		this.b = 0;
	}

	/**
	 * The "copy constructor"
	 * Creates a new ComplexNumber from an existing ComplexNumber
	 * @param c a ComplexNumber
	 */
	public ComplexNumber(ComplexNumber c){
		a = c.getReal();
		b = c.getImaginary();
	}

	/**
	 * returns the given ComplexNumber as a string in simplest form
	 * @return String of ComplexNumber
	 */
	public String toString(){
		String returnString = "";
		/*
		String tA = this.a;
		String tB = this.b;
		String shortA;
		String short
		*/
		if(this.a == 0 && this.b == 0){
			return "0.0";
		}
		if(this.a != 0){
			returnString = returnString + this.a;
		}
		if(this.b != 0){
		if(this.b != 0 && this.b > 0 && this.a != 0){
			returnString = returnString + "+";
		}
		if(this.b != 1 && this.b != -1){
			returnString = returnString + this.b;
		}
		if(this.b != -1){
			returnString = returnString + "i";
		}
		if(this.b == -1){
			returnString = returnString + "-i";
		}
	}
		return returnString;
	}

	/**
	 * An "accessor" method
	 * Returns the real component of this ComplexNumber
	 * @return a the private real component of this ComplexNumber
	 */
	public double getReal(){
		return this.a;
	}

	/**
	 * An "accessor" method
	 * Returns the imaginary component of this ComplexNumber
	 * @return b the private imaginary component of this ComplexNumber
	 */
	public double getImaginary(){
		return this.b;
	}

	/**
	 * instance method that returns the distance from the origin to the ComplexNumber on a plane
	 * @return double value of magnitude of the ComplexNumber
	 */
  public double magnitude(){
    double a_squared = this.a * this.a;
    double b_squared = this.b * this.b;
    double square_root = Math.sqrt(a_squared + b_squared);
    return square_root;
  }

	/**
	 * Compares magnitude of instance ComplexNumber to parameter ComplexNumber
	 * @param ComplexNumber c the ComplexNumber to compare the magnitude to
	 * @return int 1, 0, or -1 to indicate whether the instance ComplexNumber's magnitude is greater, equal, or less, respectively
	 */
  public int compareTo(ComplexNumber c){
    double c_magnitude = c.magnitude();
    double this_magnitude = this.magnitude();
    if(this_magnitude < c_magnitude){
      return -1;
    }
    if(this_magnitude > c_magnitude){
      return 1;
    }
    return 0;
  }

	/**
	 * Instance method. Adds instance ComplexNumber to parameter ComplexNumber
	 * @param ComplexNumber c to add the instance ComplexNumber to
	 * @return ComplexNumber sum
	 */
	public ComplexNumber add(ComplexNumber c){
		ComplexNumber toReturn = new ComplexNumber(this.a + c.getReal(), this.b + c.getImaginary());
		return toReturn;
	}

	/**
	 * Static method to add two parameter ComplexNumber values together
	 * @param  ComplexNumber c to add
	 * @param  ComplexNumber d to add
	 * @return ComplexNumber sum
	 */
	public static ComplexNumber add(ComplexNumber c, ComplexNumber d){
		ComplexNumber toReturn = new ComplexNumber(c.getReal() + d.getReal(), c.getImaginary() + d.getImaginary());
		return toReturn;
	}

	/**
	 * Instance method to subratract parameter ComplexNumber from instance ComplexNumber
	 * @param ComplexNumber c to subtract
	 * @return ComplexNumber result of subtraction
	 */
	public ComplexNumber subtract(ComplexNumber c){
		ComplexNumber toReturn = new ComplexNumber(this.a - c.getReal(), this.b - c.getImaginary());
		return toReturn;
	}

	/**
	 * Static method to subtract second parameter ComplexNumber from first parameter ComplexNumber
	 * @param ComplexNumber c from which to subtract
	 * @param ComplexNumber d to subtract
	 * @return ComplexNumber result of subtraction
	 */
	public static ComplexNumber subtract(ComplexNumber c, ComplexNumber d){
		ComplexNumber toReturn = new ComplexNumber(c.getReal() - d.getReal(), c.getImaginary() - d.getImaginary());
		return toReturn;
	}

	/**
	 * Instance method to multiply instance ComplexNumber by parameter ComplexNumber
	 * @param ComplexNumber c multiplier
	 * @return ComplexNumber product
	 */
	public ComplexNumber multiply(ComplexNumber c){
		double First = this.a * c.getReal(); //real
		double Outer = this.a * c.getImaginary(); //imaginary
		double Inner = this.b * c.getReal(); //imaginary
		double Last = this.b * c.getImaginary(); //real
		Last = Last * -1;
		double realPart = First + Last;
		double imaginaryPart = Outer + Inner;
		ComplexNumber toReturn = new ComplexNumber(realPart, imaginaryPart);
		return toReturn;
	}

	/**
	 * Static method to multiply first parameter ComplexNumber by second parameter ComplexNumber
	 * @param ComplexNumber c to multiply
	 * @param ComplexNumber d multiplier
	 * @return ComplexNumber product
	 */
	public static ComplexNumber multiply(ComplexNumber c, ComplexNumber d){
		double First = c.getReal() * d.getReal(); //real
		double Outer = c.getReal() * d.getImaginary(); //imaginary
		double Inner = c.getImaginary() * d.getReal(); //imaginary
		double Last = c.getImaginary() * d.getImaginary(); //real
		Last = Last * -1;
		double realPart = First + Last;
		double imaginaryPart = Outer + Inner;
		ComplexNumber toReturn = new ComplexNumber(realPart, imaginaryPart);
		return toReturn;
	}

	/**
	 * Internal Instance method to find a conjugate for ComplexNumber, needed to divide
	 * @return ComplexNumber conjugate
	 */
	private ComplexNumber conjugate(){
		ComplexNumber toReturn = new ComplexNumber(this.a, this.b * -1);
		return toReturn;
	}

	/**
	 * Instance method to Divide instance ComplexNumber by parameter ComplexNumber
	 * @param ComplexNumber c divisor
	 * @return ComplexNumber quotient
	 */
	public ComplexNumber divide(ComplexNumber c){
		ComplexNumber numerator = this;
		ComplexNumber denominator = c;
		ComplexNumber den_conjugate = c.conjugate();
		numerator = numerator.multiply(den_conjugate);
		denominator = denominator.multiply(den_conjugate);
		double returnDen = numerator.getImaginary()/denominator.getReal();
		if(numerator.getImaginary() == 0 || denominator.getReal() == 0){
			throw new ArithmeticException("undefined due to 0 in a denominator");
		}
		ComplexNumber toReturn = new ComplexNumber(numerator.getReal()/denominator.getReal(), returnDen);
		return toReturn;
	}

	/**
	 * Static method to divide first parameter ComplexNumber by second parameter ComplexNumber
	 * @param ComplexNumber c dividend
	 * @param ComplexNumber d divisor
	 * @return ComplexNumber quotient
	 */
	public static ComplexNumber divide(ComplexNumber c, ComplexNumber d){
		ComplexNumber numerator = c;
		ComplexNumber denominator = d;
		ComplexNumber den_conjugate = d.conjugate();
		numerator = numerator.multiply(den_conjugate);
		denominator = denominator.multiply(den_conjugate);
		double returnDen = numerator.getImaginary()/denominator.getReal();
		if(numerator.getImaginary() == 0 || denominator.getReal() == 0){
			throw new ArithmeticException("undefined due to 0 in a denominator");
		}
		ComplexNumber toReturn = new ComplexNumber(numerator.getReal()/denominator.getReal(), returnDen);
		return toReturn;
	}

	/**
	 * Instance method to square the instnce ComplexNumber
	 * @return ComplexNumber square of ComplexNumber
	 */
	public ComplexNumber square(){
		return this.multiply(this);
	}

	/**
	 * Static method to square the parameter ComplexNumber
	 * @param ComplexNumber to square
	 * @return ComplexNumber square of ComplexNumber
	 */
	public static ComplexNumber square(ComplexNumber c){
		return c.multiply(c);
	}

	/**
	 * A tester method
	 * @param args
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(3, 2);
		ComplexNumber newthing = c1.square();
		ComplexNumber new2 = newthing.square();
		System.out.println(new2.toString());
	}
}
