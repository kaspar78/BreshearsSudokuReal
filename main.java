import java.util.function.Function;

public class main {

	public static void main(String[] args) {

		boolean gameIsWon = false;
		//make an array of size 81 and it will hold cell objects, which have
		//attributes of row, col, subrow, subcol, and value
		cell[] cells = new cell[81];

		//make 81 new cells and put them in the cells array
		for (int i = 0; i < 81; i++) {
			cells[i] = new cell(0);
		}
		
		//this is finally functional. I have given every single cell a col, row, subrow, and subcol
		//this will make it finally work and be very easy to solve
		cells = setValues(cells);	  	
		
	  	do {
	  		//doMove();
	  		gameIsWon = checkGameWon(cells);
	  	} while (!gameIsWon);
	}
	
	//this function was in the works for a while, but it works now
	//this function is just a final check, and we can validate the gameIsWon boolean with this function
	//if we say gameIsWon = checkGameWon(cells);
	static boolean checkGameWon(cell[] cellArr){
		
		//int[] allGroups = checkGroups(cellArr);
		
		//the starting point of the bigsum function is the result of the sum for other categories
		//this means there is a triple nested
		int bigsum = doSum(checkCols(cellArr), doSum(checkRows(cellArr), 0));
		//bigsum = doSum(checkGroups(cellArr), bigsum);
				
		return (bigsum == (45 * 9) * 3);
	}
	
	//a utility function for taking an array of ints and returning just 1 int, the sum of all
	static int doSum(int[] array, int start){
		//start the sum at the starting point
		int sum = start;
		//loop through every single element in the array
		for(int entry : array){
			//add them to the sum
			sum += entry;
		}
		//return the single int sum
		return sum;
	}
	
	//this is a function adding up each of the different rows
	//this function goes through each row, and it will add all of the sums of each different row up
	static int[] checkRows(cell[] cellArr){
		//intintialize the sums array, which keeps 9 sums, 1 for each row
		int[] sums = new int[10];
		//this first for loop is to go through the rows
		for(int i = 0; i < 3; i++){
			//this for loop goes through the subrow
			for(int k = 0; k < 3; k++){
				//this for loop goes through all of the cells
				for(cell c : cellArr){				
					//this means that if the row and subrow are the same, add up all of the
					//values from that certain row and subrow, into the respective sum index
					//which is based on counting in trinary
					if(c.getRow() == i && c.getSubrow() == k){
						sums[(3 * i) + k] += c.getValue();
					}
				}
			}
		}
		//return the sums array, which is 9 numbers
		//each correspinding to a row subrow pair
		return sums;
	}
	
	//this is the same function as the checkRows function, but added cols functionality
	static int[] checkCols(cell[] cellArr){
		//initialize this array of integers, which will store 9 integers, each
		//for each of the col, subcol pairs
		int[] sums = new int[9];
		//this first loop is to go through the master "col" value
		for(int i = 0; i < 3; i++){
			//this second loop represents the subcol values
			for(int k = 0; k < 3; k++){
				//this third loop goes through every single cell, checking whether
				//they have the right subcol and col values
				for(cell c : cellArr){				
					//c is the cell at that array index, so we are checking to see if the col
					//value for the cell c is equal to i, which loops three times
					//this means that i will be 0, and it will run through all the cells and ask
					//if each cell has that col and subcol. Once it has found a cell with these two values
					//it means it is in one vertical strip
					if(c.getCol() == i && c.getSubcol() == k){
						//the index formula is how to transform from 3^2 to anarray with 9 spaces
						//and the value for the valid cell gets added to this.
						sums[(3 * i) + k] += c.getValue();
					}
				}
			}
		}
		//the sums array ends up being 9 numbers, each corresponding to each
		//col and subcol pair. If they are all 45, then we gucci, but if not, the player has made some illegal moves
		return sums;
	}
	
	//this is utility function used during testing to print out values
	//it prints out any value corresponding to the cell object
	static void printCells(cell[] cellArr, Function<cell, Object> method) {
		//loop through all of the cells
		for (int i = 0; i < cellArr.length; i++) {
			//quality of life variable, makes code easy to read
			cell c = cellArr[i];
			//print out the method value we wanted as well as the iteration of the loop, because its helpful
			System.out.println(method.apply(c) + " : " + i);
		}
	}

	//this is a general function for setting cell rows, cols, subrows, and subcols.
	//this was really complicated and I am very proud of this function
	static cell[] setValues(cell[] cellArr) {  
		//4 dimensional looping! wooooooohoooooo
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {		
						//a util variable to make my life easier
						int t = (27 * i) + (9 * j) + (3 * k) + l; 
						//set the subrow and subcol
						//the subrow is j because j outputs first 9 0, second 9 1, and third 9 2, and then repeats
						cellArr[t].setSubrow(j);
						//we are setting the subcol to l because if we are traversing the grid top left to bottom right
						//like reading, we need the subcol values to go 0 1 2 0 1 2 0 1 2 until infinity
						cellArr[t].setSubcol(l);
						//The row is the i, which means first 27 are 0, next 27 are 1, and next 27 are 2
						cellArr[t].setRow(i);
						//we are setting the col to k because k outputs 000 111 222 000 111 222 000 111 222, which is exactly what we want
						//for the column value
						cellArr[t].setCol(k);
					}
				}
			}
		}
		//this returns the array, newly fixed
		return cellArr;
	 }
	
}
