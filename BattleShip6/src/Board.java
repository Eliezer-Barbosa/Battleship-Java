import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Board {
	
	private int rows, columns;//number of rows and columns
	private int[][] boardSeaArea;//an bidimensional array for the grid
	private Scanner scanner = new Scanner(System.in);//scanner for get the user input
	private int[] shoot = new int[2];//array of 2 positions for shoot: 0 for row and 1 for column
	private int[][] ships;//an array for place the ships into the board
	private int shipsQtd;//quantity of ships
	private int rowPosition, columnPosition, counter = 0;
	private int numberOfPlayers;
	private ArrayList<Player> players = new ArrayList<>();
	
	//custom constructor
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.boardSeaArea = new int [rows][columns];//the size of the board will be the size given by the user.
	}
	//default constructor
	public Board() {
		
	}

	//getters
	public int[][] getShips() {
		return ships;
	}
	public int[][] getBoardSeaArea() {
		return boardSeaArea;
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	
	//methods
	/***
	 * @author Marta
	 * @return an integer value: number of players
	 */
	public int initPlayersQuantity() {
  
    	try {
        	
        	System.out.println("How many players are going to play?\n\n"
        			+ "1 - one\n2 - two\n3 - three\n4 - four\n0 - Exit\n");
        	numberOfPlayers = Integer.parseInt(scanner.nextLine());
        	 
    	}catch (NumberFormatException e) {
    		//if the user input is a special caracter or a letter, anything that is not a number
    		System.out.println("Invalid option...not a number.");
    		initPlayersQuantity();
		}
        	
        	if(numberOfPlayers == 0) {
        	//
        	System.out.println("Finish...");
        		System.exit(0);
        	}
        	if(numberOfPlayers < 0 || numberOfPlayers > 4) {
        		System.out.println("Invalid option...");
        		initPlayersQuantity();
        	}
        	if(numberOfPlayers > 0 && numberOfPlayers <= 4) {
        		System.out.println(numberOfPlayers + " players selected.");
        	}
    	
    	return numberOfPlayers;
        		
    }
	/***
	 * @author Marta
	 * Inicializes the players and add them into an ArrayList of {@link Player}
	 */
	public void initPlayers() {
		
		boolean isValidEmail, isValidAge = false;
		String firstName, lastName, email;
		int age = 0;
		for (int i = 1; i <= numberOfPlayers; i++) {
			
			Player p = new Player();
			
			System.out.println("Player " + i);
			
			System.out.println("First name: ");
			firstName = scanner.next();
			p.setFirstName(firstName);
			
			System.out.println("Last name: ");
			lastName = scanner.next();
			p.setLastName(lastName);
			
			boolean isValidName = Player.validateName(p);
			if(isValidName == false) {
				System.out.println("Invalid name..");
				initPlayers();
			}
			do {
			System.out.println("Email: ");
			email = scanner.next();
			p.setEmail(email);
			
			isValidEmail = Player.validateEmail(p);
			if(isValidEmail == false) {
				System.out.println("Invalid email..");
				}
			}while(isValidEmail == false);
			
			initAge(p, age, isValidAge);
			
			p.setNumber(i);
			players.add(p);
			
		}
		System.out.println("Players:");
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + players.get(i).getNumber() + "\n" + 
				"Name: " + players.get(i).getFullName() + "\n" +
		"e-mail: " + players.get(i).getEmail() + "\n" + "Age: " + players.get(i).getAge());
		}	
	}
	/***
	 * @author Marta
	 * @param p: a {@link Player} object
	 * @param age: integer value 
	 * @param isValidAge: boolean value that retuns true if the age is valid
	 */
	public void initAge(Player p, int age, boolean isValidAge) {
		try {
			System.out.println("Age: ");
			age = Integer.parseInt(scanner.nextLine());
			p.setAge(age);
		}catch (NumberFormatException e) {
				System.out.println("Not a number!");
				initAge(p, age, isValidAge);
		}
		if(Player.validateAge(p) == false) {
			System.out.println("You must be over 12 and newer than 100 year old to play this game.");
			initAge(p, age, isValidAge);
		}
		
	}
	/***
	 * @author Marta
	 * Initializes the board rows number
	 * 
	 */
	public void initRows() {
			try {
				System.out.println("Number of rows: ");
				rows = Integer.parseInt(scanner.nextLine());
			}catch (NumberFormatException e) {
					System.out.println("Not a number!");
					initRows();
			}
			if(validateRows(rows) == false) {
				System.out.println("Rows has to be from 10 up to 20.");
				initRows();
			}
	}
	/***
	 * @author Marta
	 * Initializes the board columns numbers
	 */
	public void initColumns() {
		try {
			System.out.println("Number of columns: ");
			columns = Integer.parseInt(scanner.nextLine());
		}catch (NumberFormatException e) {
				System.out.println("Not a number!");
				initColumns();
		}
		if(validateColumns(columns) == false) {
			System.out.println("Columns has to be from 10 up to 20.");
			initColumns();
		}
	}
    /***
     * @author Marta
     * @param rows: integer value 
     * @return a boolean value
     */
    public boolean validateRows(int rows) {
    	if(rows < 10 || rows > 20) {
    		return false;
    	}
    	return true;
    }
    /***
     * @author Marta
     * @param columns: integer value
     * @return a boolean value
     */
    public boolean validateColumns(int columns) {
    	if(columns < 10 || columns > 20) {
    		return false;
    	}
    	return true;
    }
    /***
     * @author Marta
     * Print the board on the screen
     */
	public void showBoard() {
		for(int j = 0; j < columns; j++) {
    		System.out.print("\t" + (j + 1));//print columns numbers
    	}
        System.out.println();
        for(int i = 0 ; i < rows; i++ ){
            System.out.print((i + 1) + " ");//print rows numbers
            for(int j = 0 ; j < columns; j++ ){
            	if(boardSeaArea[i][j] == -1) {
            		System.out.print("\t"+"");
            	}
            	else if(boardSeaArea[i][j] == 0){
                    System.out.print("\t"+"M");
                }
            	else if(boardSeaArea[i][j] == 1){
                    System.out.print("\t"+"\\_");
                }
            	else if(boardSeaArea[i][j] == 2){
                    System.out.print("\t"+"||_");
                }
            	else if(boardSeaArea[i][j] == 3){
                    System.out.print("\t"+"_/");
                }
            }
            System.out.println();
        }
	}
	/***
	 * @author Marta
	 * Initializes the board with all squares set the value -1
	 * that means its position has not been picked
	 */
	public void initBoard() {
		boardSeaArea = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				boardSeaArea[i][j] = -1;
			}
		}	
	}
	/*** 
	 * @author Marta
	 * Generate the ships quantity and place them randomly into the board.
	 */
	public void initShips() {
		
		Random random = new Random();
		
		shipsQtd = ((rows/2)+(columns/2))/3;
		
		ships = new int[shipsQtd][2];
		
		for(int i = 0 ; i < shipsQtd ; i++){
			//do..while: check if position is out of bounds
			do {
				columnPosition = random.nextInt(columns);
			}while(columnPosition > columns-3);
			
			rowPosition = random.nextInt(rows);
			
			ships[i][0] = rowPosition;
			ships[i][1] = columnPosition;
            
            for(int j = 0 ; j < i ; j++){
                if((ships[i][0] == ships[j][0]) && (ships[i][1] == ships[j][1]))
                	//do..while: check if the ship are in the same place
                    do{
                        ships[i][0] = random.nextInt(columns);
                        ships[i][1] = random.nextInt(columns);
                    }while((ships[i][0] == ships[j][0]) && (ships[i][1] == ships[j][1]));
            }
            
        }
	} 
	/***
	 * @author Marta
	 * the player guess
	 */
	public void shoot() {
	//do ..while: check if position has been picked already
	do {
		try {//in case the user input any value that is not an integer
			
			do {
			      System.out.print("Row: ");
			      this.shoot[0] = Integer.parseInt(scanner.nextLine());   
			 }while(this.shoot[0] <= 0 || this.shoot[0] > rows);
			        this.shoot[0]--;
			        
			  do {
			       System.out.print("Column: ");
			       this.shoot[1] = Integer.parseInt(scanner.nextLine()); 
			  }while(this.shoot[1]  <= 0 || this.shoot[1] > columns);
			        this.shoot[1]--;
		}catch (NumberFormatException e) {
			System.out.println("Not a number!");
			shoot();
		}
		        
	}while(isPositionLocked());
       
}
	/***
	 * @author Marta
	 * @return a boolean value: true if the position is unavailable
	 */
	public boolean isPositionLocked() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if(boardSeaArea[shoot[0]][shoot[1]] == 0 || boardSeaArea[shoot[0]][shoot[1]] == 1
						|| boardSeaArea[shoot[0]][shoot[1]] == 2 || boardSeaArea[shoot[0]][shoot[1]] == 3) {
					System.out.println("This position had been already chosen.");
					return true;
				}
			
			}
		
		}
		return false;
	}
	/***
	 * @author Marta
	 * @return a boolean value: true if the ship is in the position chosen
	 */
	public boolean hit(){
		for(int ship = 0 ; ship < ships.length ; ship++){
            if(shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                return true;
            }
        }
		return false;
    }
	/***
	 * @author Marta
	 * Updates the board whether it is a hit or not
	 */
	public void changeboard(){
		
		if(hit()) {//set the values 1,2 and 3 in the board. draw the ship on the screen.
			boardSeaArea[shoot[0]][shoot[1]] = 1;
			boardSeaArea[shoot[0]][shoot[1]+1] = 2;
			boardSeaArea[shoot[0]][shoot[1]+2] = 3;
		}
        else
            boardSeaArea[shoot[0]][shoot[1]] = 0;
	}
	/***
	 * @author Marta
	 * The game will run while the ships quantity is greater than 0.
	 */
	public void play() {
		
		int shotHit = 0;
		
		do{
			for(Player player : players) {
				System.out.println("Ships left: " + shipsQtd);
				System.out.println("Counter: " + counter);
				System.out.println((rows*columns)-((rows/2)+(columns/2))/2);
				System.out.println(player.getFirstName() + "s' turn: ");
				showBoard();
	            shoot();
	            player.setAttempts();
	            
	            if(hit()){
	            	System.out.printf(player.getFirstName() + " hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
	            	changeboard();
	                shotHit++;
	                player.shotHit++;
	                if(shipsQtd == 1) {
	                	player.setHit(200);
	                }
	                player.setHit(100);
	                player.setScore();
	                counter++;
	                shipsQtd--;
	            }                
	            else {
	            	changeboard();
	            	player.setMiss(2);
	            	player.setScore();
	            	counter++;
	            }
	            if(isBoardFull()) {
	            	break;
	            }
			}
        }while(shipsQtd > 0);
		
		System.out.println("Ships sunk: " + shotHit + "\n" + "Game finished!");
		
		ranking();
        showBoard();
	}
	/***
	 * @author Marta
	 * @return true if there is no more squares in the board
	 */
	private boolean isBoardFull() {
		int x = (rows*columns)-((rows/2)+(columns/2));
		if(counter == x)
			return true;
		return false;
	}
	/***
	 * @author Marta
	 * Display the winner or if it is a draw.
	 */
	public void ranking() {
		int maior = 0, atual = 0;
		ArrayList<Player> winner = new ArrayList<>();
		
		for(int i = 0; i < players.size(); i++) {
			Collections.sort(players);
			System.out.println(players.get(i).getFirstName()+" -> " + "\t"+players.get(i).getScore()
					+ " points " + players.get(i).getAttempts() + " attempts" + "\t" + players.get(i).shotHit + " ships hit");
			
			if(players.get(i).getScore() > maior) {
				maior = players.get(i).getScore();
				atual = maior;
			}
		}
		 for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getScore() >= atual) {
				winner.add(players.get(i));
			}
		 } 
				if(winner.size() == 1) {
					System.out.println("Winner: " + winner.get(0).getFirstName());
				}
				if(winner.size() == 2) {
					System.out.println("Draw: " + winner.get(0).getFirstName() +"\n"+
												  winner.get(1).getFirstName());
				}
				if(winner.size() == 3) {
					System.out.println("Draw: " + winner.get(0).getFirstName() +"\n"+
												  winner.get(1).getFirstName() +"\n"+
												  winner.get(2).getFirstName());
				}
				if(winner.size() == 4) {
					System.out.println("Draw: " + winner.get(0).getFirstName() +"\n"+
												  winner.get(1).getFirstName() +"\n"+
												  winner.get(2).getFirstName() +"\n"+
												  winner.get(3).getFirstName());
				}
	}
}