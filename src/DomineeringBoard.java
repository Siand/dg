// Tic-tac-toe abstract board methods.
// Remember we require an immutable implementation.
// So we can change things only after cloning.

import java.util.*;


public class DomineeringBoard extends Board2<DomineeringMove>{
  // Rename the players for this particular game:
  private static final Player H = Player.MAXIMIZER;
  private static final Player V = Player.MINIMIZER;
  //create  a board
  private final boolean[][] board;
  //set initial height and width (changed in the constructor)
  private int height=4;
  private int width=4;

  // We use the following class fields to represent the played moves:
  private final HashSet<DomineeringMove> hMoves;
  private final HashSet<DomineeringMove> vMoves;

  // A public method to construct the initial board:
  /**
 * @param width the width of the board
 * @param height the hieght of the board
 */
public DomineeringBoard(int width,int height) {
    hMoves = DomineeringMove.noMoves();
    vMoves = DomineeringMove.noMoves();
    this.width=width;
    this.height=height;
    boolean[][] newBoard= new boolean[width][height];
	  for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
			{
				newBoard[j][i]=false;
			}
	 this.board=newBoard;
  }

  /**
   * Alternative constructor, used for adding new moves and updaing the board for the subtrees
   * @param hMoves the set of moves palyed by the horizontal player
   * @param vMoves the set of moves palyed by the vertical player
   * @param width the width of the board
   * @param height the hieght of the board
   */
  private DomineeringBoard(HashSet<DomineeringMove> hMoves, HashSet<DomineeringMove> vMoves,int width,int height) 
  {
    assert(disjoint(hMoves,vMoves));
    this.hMoves  = hMoves;
    this.vMoves  = vMoves;
    this.width=width;
    this.height=height;
	boolean[][] newBoard= new boolean[width][height];
	  for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
			{
				newBoard[j][i]=false;
			}
    for(DomineeringMove move : hMoves)
    {
		newBoard[move.getx1()][move.gety1()]=true;
    	newBoard[move.getx2()][move.gety2()]=true;
    }
    for(DomineeringMove move : vMoves)
    {

		newBoard[move.getx1()][move.gety1()]=true;
    	newBoard[move.getx2()][move.gety2()]=true;
    }
    this.board=newBoard;
  }

  /**
   * Who is the next player(the one that has to play now)
   */
@Override
Player nextPlayer() {
	return ((hMoves.size()+vMoves.size())%2==0? H :V);
	
}
/**
 * Returns the available moves
 */
@Override
Set<DomineeringMove> availableMoves() {
	Set<DomineeringMove> c = new HashSet<DomineeringMove>();
	
	if(nextPlayer()==H)
	{
		for(int i=0;i<height;i++)
			for(int j=0;j<width-1;j++)
			{
				if(!board[j][i] && !board[j+1][i])
					c.add(new DomineeringMove(j,i,j+1,i));
			}
	}
	else
	{
		for(int i=0;i<height-1;i++)
			for(int j=0;j<width;j++)
			{
				if(!board[j][i] && !board[j][i+1])
					c.add(new DomineeringMove(j,i,j,i+1));
			}
	}
	return c;
}
/**
 * Return the outcome of the game
 */
@Override
int value() {
	return (loser() && nextPlayer()==H)? -1:
			loser() && nextPlayer()==V ? 1:
				-1;
}
/**
 * Return whether a player loses the game(has no moves left)
 * @return whether the next player to play loses
 */
private boolean loser()
{
	if(nextPlayer()==H)
	{
		 for(int i=0;i<height;i++)
				for(int j=0;j<width-1;j++)
				{
					if(!board[j][i] && !board[j+1][i])																																																																																																				
						return false;
				}
		 return true;
	}
	else
	{
		 for(int i=0;i<height-1;i++)
				for(int j=0;j<width;j++)
				{
					if(!board[j][i] && !board[j][i+1])
						return false;
				}
		 return true;
	}
}
/**
 * A toString method for the board
 */
public String toString()
{
	String output = new String();
	
	for(int i=0;i<height;i++)
		{
			for(int j=0;j<width-1;j++)
			{
				output+="*";
				boolean flag=false;
				for(DomineeringMove move : hMoves)
				{
					if(move.getx1()==j && move.gety1()==i && move.getx2()==j+1 && move.gety2()==i)
						flag = true;
				}
				if(flag)
				{
					output+="---";
				}
				else 
					output+="   ";	
			}
			output+="*\n";
			for(int j=0;j<width;j++)
			{
				boolean flag=false;
				for(DomineeringMove move : vMoves)
				{
					if(i<height-1 && move.getx1()==j && move.gety1()==i && move.getx2()==j && move.gety2()==i+1)
						flag = true;
				}
				if( flag)
				{
					output+="|";
				}
				else 
					output+=" ";
				output+="   ";
			}
			output+="\n";
		}
	return output;
}
/**
 * Play a move
 * @param move the move
 * @return the new Board
 */
@Override
Board2<DomineeringMove> play(DomineeringMove move) {
	if(nextPlayer()==H)
	{
		return new DomineeringBoard(add(hMoves,move),vMoves,width,height);
	}
	else 
	{
		return new DomineeringBoard(hMoves,add(vMoves,move),width,height);
	}
}

/**
 * Do the sets a and b have common items
 * @param a the first set
 * @param b the second set
 * @return the common items
 */
static private HashSet<DomineeringMove> intersection(HashSet<DomineeringMove> a, HashSet<DomineeringMove> b) {
	@SuppressWarnings("unchecked")
	HashSet<DomineeringMove> c = (HashSet<DomineeringMove>) a.clone();
    c.retainAll(b);
    return c;
  }
/**
 * Return whether the sets a and b have nothing in common
 * @param a the first set	
 * @param b the second set
 * @return whether the sets a and b have nothing in common
 */
  static private boolean disjoint(HashSet<DomineeringMove> a, HashSet<DomineeringMove> b) {
    return(intersection(a,b).isEmpty());
  }
/**
 * Add a move to a set of moves
 * @param a the set
 * @param b the move
 * @return the new set
 */
  static private HashSet<DomineeringMove> add(HashSet<DomineeringMove> a, DomineeringMove b) {
	 @SuppressWarnings("unchecked")
	HashSet<DomineeringMove> c = (HashSet<DomineeringMove>) a.clone();
    c.add(b);
    return c;
  }  
}
