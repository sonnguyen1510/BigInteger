public class Node {
	public int data;
	public Node nextNode;
	public Node previousNode;
	
	public Node(int data , Node nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	public Node() {
	}

	public Node(int parseInt) {
	}

	public Node getNextNode() {
		return this.nextNode;
	}

	public int getData() {
		return this.data;
	}

	public Node getPrevNode() {
		return this.previousNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setPrevNode(Node prevNode) {
		this.previousNode = prevNode;

	}


} 