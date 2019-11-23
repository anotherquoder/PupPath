package main;

import java.util.HashSet;
import java.util.Scanner;

public class Game {

	public static Node [][] world = new Node[12][16];
	
	public static Dog pup;
	
	public static void main (String [] args) {
		
		System.out.println("\n\nWelcome to the pup path.com\n");
		Loader.pupulateNameMap();
		pupulateMaze();
		displayWorld();
		
		Dog dupePup = new Dog(pup.row + 1, pup.col,"Dupe", pup.mem);
		
		world[pup.row][pup.col] = dupePup;
		dupePup.walkRecursive();
		
		//TODO This is just for now and in the for loop.  change later to a boolean
		//int tC = 10;
		
		/*while (pup.ftracker<pup.maxFood && !pup.stuck) {
			
			try {
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println("/n The pup will take a turn from 0-3, representing east, north, west, south respectively");
			pup.displayPup();
			int direction = (int) (Math.random()*4);
			System.out.println("Pup will choose the direction of " + Loader.dirMap[direction]);
			pup.walk(direction);
			displayWorld();
			
			Scanner sc = new Scanner(System.in);
			pup.displayPup();
			System.out.println("please take your term in terms of 0-3, representing east, north, west, south, respectively");
			
			int direction = sc.nextInt();
			
			System.out.println("You chose the direction of " + Loader.dirMap[direction]);
			
			pup.walk(direction);
			displayWorld();*/
		}
	//	for (int i = 0; i< tC; i ++) {
			


	//	}
		
		
	//} end of main driver method
	
	public static void pupulateMaze() { //fill the maze
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[0].length; col++) {
				
				int roll = (int) (Math.random()* 3);
				
				Node node = new Node(row, col, Loader.nameMap.get(roll));
				world[row][col] = node;
				
			}
		}
		
		int testRow = (int) (Math.random() * world.length);
		int testCol = (int) (Math.random() * world[0].length);
		
		 pup = new Dog(testRow, testCol, "DOG", new HashSet<Pos>());
		world[testRow][testCol] = pup;
	}	
	
	public static void displayWorld() {
		for (int i= 0; i<40; i++) {
			System.out.println("");
		}
		
		System.out.println("\n");
		System.out.println("The WORLD");
		System.out.println("\n");
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[0].length; col++) {
				
				System.out.print(world[row][col]);
				
			}
			System.out.println(" ROW " + row);
		}
		
		for (int col = 0; col <world[0].length; col++) {
			int c = col % 10;
			System.out.print("["+c+"]");
		}
		
		System.out.println("");
	}
	
	
	public static int countFood() {
		
		int counter = 0;
		
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[0].length; col++) {
				if (world[row][col].contains == 2 ) {
					  counter ++;
				}
			}
		}
		
		//TODO - just used to mark where this is. TODO making this a boolean accessed line tho
		//System.out.println("There are " + counter + " foods in the map");
		return counter;

	}
} //end of class