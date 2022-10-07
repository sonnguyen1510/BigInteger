import javax.naming.spi.DirStateFactory.Result;

public class BigInteger {
	public Node BigInterger;
	public boolean isPositiveNum;
	public int length;


	public BigInteger(String Number,boolean isPositiveNum){
		this.BigInterger = CreateNumber(Number);
		this.isPositiveNum = isPositiveNum;
	}

	public BigInteger() {

	}

	public Node CreateNumber(String Number){
		
		if(isInteger(Number)){
			Node current = new Node();
			Node Head = current;
			
			for (String i : Number.split("")){
				if(Character.isLetter(i.charAt(0))){
					if(current == null){
						current = new Node(Integer.parseInt(i),new Node());
					}
					else{
						current.setData(Integer.parseInt(i));
						current.setNextNode(new Node());
						current.getNextNode().setPrevNode(current);
					}

					current = current.getNextNode();
				}
				
				
			}
			return Head;
		}
		else{
			System.out.print("Insert a valid number!");
			return null;
		}
	}


	//---------------------------------CALCULATE-------------------------------
	public BigInteger add(BigInteger AddNumber){
		
		BigInteger Result = new BigInteger();

		int Num1Length = this.length();
		int Num2Length = AddNumber.length();

		if(Num1Length > Num2Length){
			for(int i = 0 ; i < Num1Length - Num2Length ; i++){
				
			}		
		}

		Node Num1 = this.ReverstNum();
		Node Num2 = AddNumber.ReverstNum();


		
	}

	//
	public void RemoveZero(){
		Node current = this.BigInterger;
		while(this.BigInterger.getData() == 0){
			this.BigInterger = current.getNextNode();
		}
	}

	public void AddZero(int size){
		Node current = this.BigInterger;
		for(int i = 0 ; i < size ; i++){
			Node temp = new Node();
			temp.setData(0);
			temp.setNextNode(this.BigInterger);
			this.BigInterger.setPrevNode(temp);

			this.BigInterger = temp;
		}

	}

	public int length(){
		int count = 0;
		Node Head= this.BigInterger;
		while (Head != null){
			count++;
		}

		return count;
	}

	public Node ReverstNum(){
		Node oldNode = this.BigInterger;
		
		Node current = new Node();
		Node newNode = new Node();

		newNode = current;

		while(oldNode!=null){
			current.setData(oldNode.getData());
			current.setNextNode(new Node());

			current = current.getNextNode();
			oldNode = oldNode.getNextNode();
		}
		
		return newNode;
}


	//-----------------------------------PRINT--------------------------------

	

	
	
	public void printNumber(){
		System.out.print(printNumber(this.BigInterger));
	}

	private String printNumber(Node current){
		if(current == null){
			return "";
		}
		else{
			return current.getData()+printNumber(current.nextNode);
		}
	}

	//---------------------------ANOTHER FUNCTION-----------------------
	private boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	
	 */
}
