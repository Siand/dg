//COMP
//java -ea -cp . CommandLineDomineering
public class CommandLineDomineering {
	 private static class CommandLineDom implements MoveChannel<DomineeringMove> {
		    public DomineeringMove getMove() {
		    	//changed because of formatting
		    		int x1 =Integer.parseInt(System.console().readLine("Enter x1(the row of the first coordinate): "));
		    		int y1 =Integer.parseInt(System.console().readLine("Enter y1(the column of the first coordinate): "));
		    		int x2 =Integer.parseInt(System.console().readLine("Enter x2(the row of the second coordinate): "));
		    		int y2 =Integer.parseInt(System.console().readLine("Enter y2(the column of the second coordinate): "));
		    
		    	return new DomineeringMove(x1,y1,x2,y2);
		    	
		    }

		    public void giveMove(DomineeringMove move) {
		      System.out.println("I play " + move);
		    }

		    public void comment(String msg) {
		      System.out.println(msg);
		    }

		    public void end(int value) {
		      System.out.println("Game over. The result is " + value);
		    }
		  }

		  public static void main(String [] args) {
		    DomineeringBoard board = new DomineeringBoard(4,5);
		    
		    //START SECOND
		    // board.tree().firstPlayer(new CommandLineDom());
		    
		    
		    
		    //START FIRST
		    board.tree().secondPlayer(new CommandLineDom());
		  }
}
