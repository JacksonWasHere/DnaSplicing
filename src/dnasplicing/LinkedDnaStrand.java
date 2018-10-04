package dnasplicing;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class LinkedDnaStrand implements DnaStrand {
	private int nodeCount = 0;
	private int appendNodeCount = 0;
	private DnaNode head;
	private DnaNode tail;


	public LinkedDnaStrand(String dna) {
		this.head = new DnaNode(dna);
		this.tail = this.head;
		this.nodeCount++;
	}


	public LinkedDnaStrand() {
		this.head = null;
		this.tail = this.head;
	}


	@Override
	public DnaNode getFirstDnaNode() {
		return this.head;
	}


	@Override
	public int getDnaNodeCount() {
		return this.nodeCount;
	}


	@Override
	public String toString() {
		var output = "";
		var cursor = this.head;

		while (cursor != null) {
			output += cursor.getDnaSequence();
			cursor = cursor.getNext();
		}

		return output;
	}


	@Override
	public void append(String dnaToAppend) {
		// TODO Auto-generated method stub
		var newNode = new DnaNode(dnaToAppend);
		newNode.previous = this.tail;

		this.tail.next = newNode;
		this.tail = newNode;

		this.nodeCount++;
		this.appendNodeCount++;
	}


	@Override
	public int getAppendCount() {

		return this.appendNodeCount;
	}


	@Override
	public long getNucleotideCount() {
		var sequence = "";
		var cursor = this.head;

		while (cursor != null) {
			sequence += cursor.getDnaSequence();
			cursor = cursor.getNext();
		}

		return sequence.length();
	}


	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		var newDna = this.toString().split(enzyme);
		var newStrand = new LinkedDnaStrand(newDna[0]);
		var hadFirst = false;
		if (newDna[0].equals("")) {
			hadFirst = true;
			newStrand = new LinkedDnaStrand(splicee);
		}
		for (var i = 1; i < newDna.length; i++) {
			if (!(hadFirst && i == 1)) {
				newStrand.append(splicee);
			}
			newStrand.append(newDna[i]);
		}

		// I heard that you can do lots with brackets
		{
			var str = this.toString();
			var endOf = str.substring(str.length() - enzyme.length());

			{
				if (endOf.equals(enzyme)) {
					{
						newStrand.append(splicee);
					}
				}
			}
		}

		return newStrand;
	}


	@Override
	public LinkedDnaStrand createReversedDnaStrand() {
		var firstNode = "";
		for (var c : this.tail.dnaSequence.toCharArray()) {
			firstNode = c + firstNode;
		}

		var reversedBoi = new LinkedDnaStrand(firstNode);
		var cursor = this.tail.previous;

		while (cursor != null) {
			var newSequence = "";

			for (var c : cursor.dnaSequence.toCharArray()) {
				newSequence = c + newSequence;
			}

			reversedBoi.append(newSequence);
			cursor = cursor.previous;
		}

		return reversedBoi;
	}
}
