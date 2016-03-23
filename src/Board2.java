// An abstract two-player game with outcomes in the integers.
// We define a particular game by implementing the abstract methods.
//
// Our approach requires immutable implementations of Board.  We
// require that the only public constructor builds the initial board.
// Other constructors may be used for private purposes.

import java.io.*;
import java.util.*;
import java.lang.*;

public abstract class Board2<Move> {
  abstract Player nextPlayer();
  abstract Set<Move> availableMoves(); 
  abstract int value(); 
  abstract Board2<Move> play(Move move);

  // Constructs the game tree of the board using the minimax algorithm
  // (without alpha-beta pruning):
  public GameTree2<Move> tree() {
    if (availableMoves().isEmpty())
      return new GameTree2<Move>
                    (this, 
                     new LinkedHashMap<Move,GameTree2<Move>>(), 
                     value());
    else
      return (nextPlayer() == Player.MAXIMIZER ? maxTree() : minTree()); 
  }

  // Two helper methods for that, which call the above method tree:
  public GameTree2<Move> maxTree() {
    assert(!availableMoves().isEmpty());

    int optimalOutcome = Integer.MIN_VALUE;
    LinkedHashMap<Move,GameTree2<Move>> children 
                 = new LinkedHashMap<Move,GameTree2<Move>>(); 

    for (Move m : availableMoves()) {
      GameTree2<Move> subtree = play(m).tree();
   //   optimalOutcome = Math.max(optimalOutcome,subtree.optimalOutcome());
      if(optimalOutcome<subtree.optimalOutcome())
      {  
    	  optimalOutcome=subtree.optimalOutcome();
    	  children.put(m,subtree);
      }
      if(optimalOutcome==1)
    	  break;
    }

    return new GameTree2<Move>(this,children,optimalOutcome); 
  }

  public GameTree2<Move> minTree() {
    assert(!availableMoves().isEmpty());

    int optimalOutcome = Integer.MAX_VALUE;
    LinkedHashMap<Move,GameTree2<Move>> children 
                 = new LinkedHashMap<Move,GameTree2<Move>>(); 

    for (Move m : availableMoves()) {
      GameTree2<Move> subtree = play(m).tree();
   //   optimalOutcome = Math.min(optimalOutcome,subtree.optimalOutcome());
      if(optimalOutcome>subtree.optimalOutcome())
      {  
    	  optimalOutcome=subtree.optimalOutcome();
    	  children.put(m,subtree);
      }
      if(optimalOutcome==-1)
    	  break;
    }

    return new GameTree2<Move>(this,children,optimalOutcome); 
  }
}
