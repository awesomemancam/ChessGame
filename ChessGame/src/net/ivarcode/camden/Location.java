package net.ivarcode.camden;

public class Location {

	private int rank;
	private char file;
	
	public Location(char file, int rank) {
		setRank(rank);
		setFile(file);
	}
	
	public Location(int f, int rank) {
		char file = 'z';
		if (f == 0) {
			file = 'a';
		} else if (f == 1) {
			file = 'b';
		} else if (f == 2) {
			file = 'c';
		} else if (f == 3) {
			file = 'd';
		} else if (f == 4) {
			file = 'e';
		} else if (f == 5) {
			file = 'f';
		} else if (f == 6) {
			file = 'g';
		} else if (f == 7) {
			file = 'h';
		}
		
		setRank(rank);
		setFile(file);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public char getFile() {
		return file;
	}

	public void setFile(char file) {
		this.file = file;
	}

	public int getFileByInt() {
		int i = -1; //throws error if case not 'a'-'h'
		if (file == 'a') {
			i = 0;
		} else if (file == 'b') {
			i = 1;
		} else if (file == 'c') {
			i = 2;
		} else if (file == 'd') {
			i = 3;
		} else if (file == 'e') {
			i = 4;
		} else if (file == 'f') {
			i = 5;
		} else if (file == 'g') {
			i = 6;
		} else if (file == 'h') {
			i = 7;
		} 
		return i;
	}
	
	public void print() {
		System.out.println(file + (rank+1));
	}
	
}
