//COMP
//java -ea -cp . CommandLineDomineering
public class BlackBoxDomineering{
	private static Player player;
	private static long TIME;
	 private static class CommandLineDom implements MoveChannel<DomineeringMove> {
		    public DomineeringMove getMove() {
		    	System.err.println(System.currentTimeMillis()-TIME);
		    	String move = System.console().readLine();
				int x = Integer.parseInt(move.substring(0,1));
				int y = Integer.parseInt(move.substring(2,3));
				if(player == Player.MAXIMIZER)
					{
						System.err.println(x+" "+y+" "+(x+1)+" "+y);
						return new DomineeringMove(x,y,x,y+1);
					}
				else
				{
					System.err.println(x+" "+y+" "+x+" "+(y+1));
					return new DomineeringMove(x,y,x+1,y);
				}
		    }

		    public void giveMove(DomineeringMove move) {
		    	System.out.println(move);
		    	System.out.flush();
		    }

		    public void comment(String msg) {
		      System.err.println(msg);
		    }

		    public void end(int value) {
		    	System.exit(0);
		    }
		  }

		  public static void main(String [] args) {
			  TIME = System.currentTimeMillis();
			  assert(args.length==4);
			  int width=0;
			  int height=0;
			  try
			  {
				  width= Integer.parseInt(args[2]);
				  height = Integer.parseInt(args[3]);
			  }
			  catch(NumberFormatException e)
			  {
				  System.exit(1);
			  }
			  DomineeringBoard board = new DomineeringBoard(width, height);
			  if(args[0].equals("first"))
			  { 
				  player=Player.MAXIMIZER;
				  board.tree().firstPlayer(new CommandLineDom());  
			  }
			  else if(args[0].equals("second"))
			  {
				  player=Player.MINIMIZER;
				  board.tree().secondPlayer(new CommandLineDom());	
			  }
			  else 
				 System.err.println("ERROR WITH FIRST ARGUMENT");
		  }

}
