package main;

import java.awt.image.BufferedImage;

public class Node {
	
	int x, y, row, col, w, h;
	
	int contains =0;
	
	/*
	 * contains ->
	 * 	0 = empty or ground
	 * 	1 = wall
	 * 	2 = food
	 * 	3 = enemy/competitive pups
	 * 	4 = blank
	 * 	5 = hero dog 
	 */
	
	BufferedImage img = null;
	String name;
	String abbr;
	
	public Node(int row, int col, String name) {
		
		this.row = row;
		this.col = col;
		
		this.x = 32 * this.col;
		this.y = 32 * this.row;
		
		this.w = 32;
		this.h = 32;
		
		this.name = name;
		
		switch(this.name) {
		
		case "WALL":
			this.contains = 1;
			break;
		case "FOOD":
			this.contains = 2;
			break;
		case "ENEMY":
			this.contains = 3;
			break;
		case "DOG":
			this.contains = 5;
			break;
	
		default:
			this.contains = 0;
			break;
		} //end of switch for names
		
		this.abbr = Loader.abbrMap.get(this.contains);
	}//end of constructor

	@Override
	public String toString() {
		return "[" + this.abbr + "]";
	}
	
}//end of class