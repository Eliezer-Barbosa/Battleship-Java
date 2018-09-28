
public class Game {
	
	public static void main(String[] args) {
		
		 Board board = new Board();
		 
		 board.initPlayersQuantity();
		 board.initPlayers();
		 board.initRows();
		 board.initColumns();
		 board.initBoard();
		 board.initShips();
		 board.play();
	}

}
