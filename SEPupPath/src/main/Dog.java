package main;

import java.util.HashSet;

public class Dog extends Node{
	/*
	 * Rule of this world:
	 * - dog can't smell past walls; can smell past everything else
	 */
	int ftracker; //this is to track the food 
	int maxFood;
	int stuckCur;
	int stuckMax;
	boolean stuck;
	boolean debug;
	
	//recursive one will not need to backtrack
	public Node [][] memWorld = new Node[12][16];
	
	int dir;
	HashSet<Pos> mem = new HashSet<Pos>();
	
	public Dog(int row, int col, String name, HashSet<Pos> mem) {
		super(row, col, name);	
		this.ftracker = 0;
		this.maxFood = Game.countFood();
		this.stuckCur = 0;
		this.stuckMax = 4;
		this.stuck = false;
		this.debug = false;
		
	}//end of constructor
	
	
	
	
	public void initialMemory(HashSet<Pos> mem) {
		for (Pos p:mem) {
			this.mem.add(p);
		}	
	}//end of the remember method
	
	public void displayPup() {
		System.out.println("puppy row is " + this.row + " and pup col is " + this.col );
		
	}
	
	public void displayMemory() {
		
		System.out.println("\n *** DOG MEMORY ***");
		for (Pos p:mem) {
			System.out.println(p);
		}
	}
	
	public boolean verifyNotInMem(int dir) {
		int R = this.row;
		int C = this.col;
		
		switch(dir) {
		
		case 0:
			C ++;
			break;
			
		case 1:
			R --;
			break;
			
		case 2:
			C--;
			break;
		
		case 3:
			R++;
			break;
		
		}
		
		//create a temporary for the check for the position that we want to check 
		Pos check = new Pos(R, C);
		
		//for everyone position (p)in our dog's memory... 
		for (Pos p: this.mem) {
			if (p.equals(check)) {
				System.out.println("\n this dog has already been here, to the row of " +p.row + " and the col to " + p.col);
				return false;
			}
		}
		
		//return true only if this attempted postion is not in the dog's mem
		return true;
	}
	
	public boolean canIWalkThere(int dir) {
		int checkRow = this.row;
		int checkCol = this.col;
		
		switch(dir) {
		case 0:
			if (debug) System.out.println("the pup wants to walk to the east");
			checkRow = this.row;
			checkCol = this.col + 1;
			break;
		case 1:
			if (debug)System.out.println("the pup wants to walk to the north");
			checkRow = this.row-1;
			checkCol = this.col;
			break;
		case 2:
			if (debug)System.out.println("the pup wants to walk to the west");
			checkRow = this.row;
			checkCol = this.col - 1;
			break;
		case 3:
			if (debug)System.out.println("the pup wants to walk to the south");
			checkRow = this.row+1;
			checkCol = this.col;
			break;
		
		default:
			System.out.println("e r r e u r");
			break;
		}
		
		if (checkRow <0 || checkRow>= Game.world.length || checkCol < 0 || checkCol >= Game.world[0].length ) {
			if (debug)System.out.println("pup at end of world/they bark at the moon loudly/it is not too nice");
			this.stuckCur ++;
			if (this.stuckCur <= this.stuckMax) this.stuck = true;
			return false;
		}
		
		System.out.println("this is check row " + checkRow + " this is check col " + checkCol);
		
		Node ground = Game.world[checkRow][checkCol];
		int c = ground.contains;
		
		if (debug)System.out.println("the dog sees " + Loader.nameMap.get(c) + "to the " + Loader.dirMap[dir]);
		
		
		if (this.verifyNotInMem(dir) && c!= 1 && c!= 5) {
			if (debug)System.out.println("the dog can walk to the " + Loader.dirMap[dir]);
			//add this data to hashset and then walk
			
			return true ;
		}
		else {
			if (debug)System.out.println("WOOF i've been here before !!!! or i hit a wall");
			
			return false;
			
		}
		
	}
	
	public void walkRecursive() {
		for (int dir = 0; dir < 4; dir++) {
			if (this.canIWalkThere(dir)) {
				if (debug)System.out.println("Since I can walkt ot the " + Loader.dirMap[dir] + " I will spawn there :3");
				int checkRow = this.row;
				int checkCol = this.col;
				switch(dir) {
				case 0:
					checkCol = this.col + 1;
					break;
				case 1:
					checkRow = this.row-1;
					break;
				case 2:
					checkCol = this.col - 1;
					break;
				case 3:
					checkRow = this.row+1;
					break;
				}
				
				Pos memory = new Pos(checkRow, checkCol);
				this.mem.add(memory);
				
				Dog duplipup = new Dog(checkRow, checkCol, "DOG", this.mem);
				
				//we store the dog node in the world
				Game.world[checkRow][checkCol] = duplipup;
				
				//System.out.println(duplipup.mem);
				
				Dog oldPup = new Dog(this.row, this.col, "DOG", this.mem);
				Game.world[this.row][this.col] = oldPup;
				
				try {
					Thread.sleep(200);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				duplipup.walkRecursive();
			}
		}
		Game.displayWorld();
	
	}
	
	
	public void walk(int dir) {
		int checkRow = -999;
		int checkCol = -999;
		
		switch(dir) {
			case 0:
				System.out.println("the pup wants to walk to the east");
				checkRow = this.row;
				checkCol = this.col + 1;
				break;
			case 1:
				System.out.println("the pup wants to walk to the north");
				checkRow = this.row-1;
				checkCol = this.col;
				break;
			case 2:
				System.out.println("the pup wants to walk to the west");
				checkRow = this.row;
				checkCol = this.col - 1;
				break;
			case 3:
				System.out.println("the pup wants to walk to the south");
				checkRow = this.row+1;
				checkCol = this.col;
				break;
			
			default:
				System.out.println("e r r e u r");
				break;
		}
		
		if (checkRow <0 || checkRow>= Game.world.length || checkCol < 0 || checkCol >= Game.world[0].length ) {
			System.out.println("pup at end of world/they bark at the moon loudly/it is not too nice");
			this.stuckCur ++;
			if (this.stuckCur <= this.stuckMax) this.stuck = true;
			return;
		}
		
		System.out.println("this is check row " + checkRow + " this is check col " + checkCol);
		
		Node ground = Game.world[checkRow][checkCol];
		int c = ground.contains;
		
		System.out.println("the dog sees " + Loader.nameMap.get(c) + "to the " + Loader.dirMap[dir]);
		
		//first verify we havent been here
		
		if (this.verifyNotInMem(dir) && c!= 1) {
			System.out.println("the dog walks to the " + Loader.dirMap[dir]);
			//add this data to hashset and then walk
			Pos memory = new Pos(ground.row, ground.col);
			this.mem.add(memory);
			this.stuckCur = 0;
			if (c == 2) {
				ftracker ++;
				System.out.println("I have eaten " + ftracker + " pieces of food");

			}
			
			
			Game.world[checkRow][checkCol] = this;
			this.displayMemory();
			Node g = new Node(this.row, this.col, "GROUND");
			Game.world[this.row][this.col] = g;
			
			this.row = checkRow;
			this.col = checkCol;
			return;
		}
		else {
			System.out.println("WOOF i've been here before !!!!");
			
			this.stuckCur ++;
			if (this.stuckCur >= this.stuckMax)this.stuck = true;
		}
	
	
	}//end of walk method
	
	
	public void duplicateWorld(Node [][] world) {
		for (int row = 0; row < Game.world.length; row++) {
			for (int col = 0; col < Game.world[0].length; col++) {
				String name = world[row][col].name;
				Node tempNode = new Node(row, col, name);
				this.memWorld[row][col] = tempNode;
			}
			
		}
		
	}
	
	
}
