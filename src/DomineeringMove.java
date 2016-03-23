import java.util.HashSet;

public class DomineeringMove {

	private int x1,y1,x2,y2;
	/**
	 * Create a new DomineeringMove
	 * @param x1 first coordinate
	 * @param y1 second coordinate
	 * @param x2 third coordinate
	 * @param y2 forth coordinate
	 */
	public DomineeringMove(int x1,int y1,int x2,int y2)
	{
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
		
	}
	/**
	 * Get the first coordinate
	 * @return the first coordinate
	 */
	public int getx1()
	{
		return x1;
	}
	/**
	 * Get the second coordinate
	 * @return the second coordinate
	 */
	public int gety1()
	{
		return y1;
	}
	/**
	 * Get the third coordinate
	 * @return the third coordinate
	 */
	public int getx2()
	{
		return x2;
	}
	/**
	 * Get the forth coordinate
	 * @return the forth coordinate
	 */
	public int gety2()
	{
		return y2;
	}
	/**
	 * Return an empty set of DomineeringMove
	 * @return the set
	 */
	static public HashSet<DomineeringMove> noMoves() 
	{
		return(new HashSet<DomineeringMove>());
	}
	/**
	 * A method to override the hash code genertion to allow the finding of DomineeringMoves in the HashSet
	 */
	public int hashCode()
	{
		return x1*1000+y1*100+x2*10+y2;
	}
	/**
	 * A methd that allows for equality check for Moves
	 * @return whether the moves are the same
	 */
	public boolean equals(Object move)
	{
		if(move instanceof DomineeringMove)
		{
			DomineeringMove newMove = (DomineeringMove)move;
				return (x1==newMove.getx1()) && (y1==newMove.gety1()) && (x2==newMove.getx2()) && (y2==newMove.gety2());
		}
		else 
			return false;
	}
	/**
	 * A toString method for the moves
	 * @return the string
	 */
	public String toString()
	{
		return x1+","+y1;
	}
	
}
