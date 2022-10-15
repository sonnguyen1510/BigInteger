import java.util.ArrayList;

public class BigInteger {
    private DigitList digits;
    private int sign;

    public BigInteger() {
        this.digits = null;
        this.sign = 1;
    }

    public BigInteger(DigitList L) {
        this.digits = L;
        this.sign = 1;
    }

    public BigInteger(int i, DigitList L) {
        this.digits = L;
        this.sign = sgn(i);
    }

    public BigInteger(int i) {
        this.digits = DigitList.digitize(Math.abs(i));
        this.sign = sgn(i);
    }

    public BigInteger(String str) {
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            this.digits = DigitList.digitize(str);
            this.sign = -1;
        } else {
            this.digits = DigitList.digitize(str);
            this.sign = 1;
        }
    }

    public DigitList getDigits() {
        return this.digits;
    }

    public int getSign() {
        return this.sign;
    }

    public void setDigits(DigitList digits) {
        this.digits = digits;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    private int sgn(int i) {
        if (i < 0)
            return -1;
        else
            return 1;
    }

    public int length() {
        if (this.digits == null)
            return 0;
        else
            return this.digits.length();
    }

    public BigInteger copy() {
        if (this.digits == null)
            return new BigInteger(0);
        else
            return new BigInteger(this.sign, this.digits.copy());
    }

    public BigInteger trimDigit() {
        return new BigInteger(this.sign, DigitList.trimDigitList(this.digits));
    }

    public boolean equals(Object obj) {
        if (obj instanceof BigInteger) {
            BigInteger other = (BigInteger) obj;
            if (this.sign == other.sign && DigitList.compareDigitLists(this.digits, other.digits) == 0) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if (this.digits != null) {
            String integer = "";
            DigitList tmp = this.digits;
            integer = tmp.getDigit() + integer;
            tmp = tmp.getNextDigit();
            while (tmp != null) {
                integer = tmp.getDigit() + integer;
                tmp = tmp.getNextDigit();
            }
            return ((this.sign == -1) ? "-" : "") + integer;
        } else
            return "";
    }
	
	public BigInteger leftDigits(int n) {
        return new BigInteger(this.digits.leftDigits(n));
    }

    public BigInteger rightDigits(int n) {
        return new BigInteger(this.digits.rightDigits(n));
    }

    public BigInteger shift(int n) {
		if (n < 1)
            return this;
        else {
            BigInteger clone = this.copy();
            clone.digits = new DigitList(0, clone.digits);
            return clone.shift(n - 1);
        }
    }

    /******************************** STUDENT'S CODE ********************************/

    public BigInteger add(BigInteger other) {
    	///different sign
    	if (this.sign != other.sign) {
        	if(DigitList.compareDigitLists(this.getDigits(), other.getDigits()) >= 0) {// A > B
        		if(this.sign == -1)// -A + B = -C
        			return new BigInteger(-1, DigitList.trimDigitList(DigitList.subDigitLists(0, this.digits, other.digits)));
        		else// A +(- B) = C
        			return new BigInteger(1, DigitList.trimDigitList(DigitList.subDigitLists(0, this.digits, other.digits)));
        	}
        	else {
        		if(this.sign == -1)
        			return new BigInteger(1, DigitList.trimDigitList(DigitList.subDigitLists(0, other.digits,this.digits)));
        		else
        			return new BigInteger(-1, DigitList.trimDigitList(DigitList.subDigitLists(0, other.digits,this.digits)));
        	}
        }
    	else
            return new BigInteger(this.sign, DigitList.addDigitLists(0, this.digits, other.digits));
    }

    public BigInteger sub(BigInteger other) {
        int i =DigitList.compareDigitLists(this.getDigits(), other.getDigits()); 
    	///different sign
        if(this.sign != other.sign) {
    		if(this.sign ==-1) {//-A - B = -( A + B)
    			return new BigInteger(-1,DigitList.addDigitLists(0, this.digits,other.digits));
    		}
    		else//A-(-B) == A + B
    			return new BigInteger(1,DigitList.addDigitLists(0, this.digits,other.digits));
    	}
    	//same sign
        else {
        	if(this.sign == 1) {//A-B
        		if(i >= 0) //A - B = C
        			return new BigInteger(1,DigitList.trimDigitList(DigitList.subDigitLists(0, this.digits, other.digits)));
            	else//A - B = -C
        			return new BigInteger(-1,DigitList.trimDigitList(DigitList.subDigitLists(0, other.digits, this.digits)));
        	}
        	else { //-A -(-B)
        		if(i >= 0) //-A -(-B) = -C
        			return new BigInteger(-1,DigitList.trimDigitList(DigitList.subDigitLists(0, this.digits, other.digits)));
            	else//-A - (-B) = C
        			return new BigInteger(1,DigitList.trimDigitList(DigitList.subDigitLists(0, other.digits, this.digits)));
        	}
        }
    	
    	
    }
    
    public BigInteger mul(BigInteger other) {
        DigitList int1 = other.digits;
        DigitList int2 = this.digits;
        
        // fill 0 at the end of linked list
        if(this.length() > other.length()) {
	    	for(;this.length() > other.length();) {
	    		while(int1.getNextDigit() != null) 
	    			int1 = int1.getNextDigit();
	    		DigitList temp3 = new DigitList();
	    		int1.setNextDigit(temp3);
	    		
	    	}
    	}
        else {
        	for(;this.length() < other.length();) {
	    		while(int2.getNextDigit() != null) 
	    			int2 = int2.getNextDigit();
	    		DigitList temp3 = new DigitList();
	    		int2.setNextDigit(temp3);
        	}
        }
        
    	BigInteger result;
    	if(this.digits.getNextDigit() == null) {
    		result = new BigInteger(this.digits.getDigit() * other.digits.getDigit());
    	}
    	else {
    		BigInteger temp1,temp2,temp3,temp4,temp5;
    		int len = this.length();
    		int n = len/2;
    		temp1 = this.leftDigits(n).mul(other.leftDigits(n));
    		//-----------------------
    		temp2 = this.rightDigits(len-n).mul(other.leftDigits(n));
    		temp3 = this.leftDigits(n).mul(other.rightDigits(len-n));
    		temp4 = temp2.add(temp3);    		
    		//-----------------------
    		temp5 = this.rightDigits(len-n).mul(other.rightDigits(len-n));
    		
    		//10^n
    		temp4 = temp4.shift(n);
    		temp5 = temp5.shift(2*n);
    		
    		result = temp1.add(temp4.add(temp5));
    	}
    	// While A*null => A*0
    	if(result.digits ==null) {
    		result.digits = new DigitList();
    	}
    	
    	//trim 
    	result = result.trimDigit();
    	this.digits = DigitList.trimDigitList(this.digits);
    	
    	//sign
    	if((this.sign == other.sign) || (result.digits.getDigit() ==0) && result.digits.getNextDigit()==null) {
    		return new BigInteger(1,result.digits);
    	}
    	else
    		return new BigInteger(-1,result.digits);
       
    }

    public static BigInteger pow(BigInteger X, BigInteger Y) {
    	DigitList dg1 =  DigitList.digitize("1");//create a BigInteger with value is 1
    	if(Y.equals(new BigInteger(1,dg1))) {//Stop while X = 1
    		return X;
    	}
        return X.mul(pow(X,Y.sub(new BigInteger(1,dg1))));
    }

    public static BigInteger factorial(BigInteger X) {
    	DigitList dg1 =  DigitList.digitize("1");//create a BigInteger with value is 1
        if(X.equals(new BigInteger(1,dg1))) {//Stop while X = 1
        	return X;
        }
        return X.mul(factorial(X.sub(new BigInteger(1,dg1))));
    }

    public static BigInteger computeValue(ArrayList<BigInteger> operandArr, ArrayList<Character> operatorArr) {
		// complete - and * operator
		BigInteger output = operandArr.get(0);
        for (int j = 0; j < operatorArr.size(); j++) {
            switch (operatorArr.get(j)) {
                case '+':
                    output = output.add(operandArr.get(j + 1));
                    break;
                case '-':
                	output = output.sub(operandArr.get(j + 1));
                    break;
                case '*':
                	output = output.mul(operandArr.get(j + 1));
                    break;
                default:
                    break;
            }
        }
        return output;
    }
}