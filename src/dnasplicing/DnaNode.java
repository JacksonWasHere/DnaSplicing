package dnasplicing;

public class DnaNode {
	public String dnaSequence;
	public DnaNode previous;
	public DnaNode next;


	public DnaNode(String initialDnaSequence) {
		dnaSequence = initialDnaSequence;
	}


	public String getDnaSequence() {
		return dnaSequence;
	}


	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
	}


	public DnaNode getPrevious() {
		return previous;
	}


	public void setPrevious(DnaNode previous) {
		this.previous = previous;
	}


	public DnaNode getNext() {
		return next;
	}


	public void setNext(DnaNode next) {
		this.next = next;
	}
}
