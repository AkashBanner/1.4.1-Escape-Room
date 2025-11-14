import java.util.Scanner;
public class EscapeRoom
{
  public static void main(String[] args) 
  {      
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    int m = 60;   
    int score = 0;

    String[] validCommands = { 
      "right","left","up","down","r","l","u","d",
      "jump","jr","jumpleft","jl","jumpup","ju","jumpdown","jd",
      "pickup","p",
      "trap",
      "quit","q",
      "replay",
      "help","?"
    };
  
    boolean play = true;

    while (play)
    {
      System.out.print("Enter command: ");
      String cmd = UserInput.getValidInput(validCommands);

      if (cmd.equals("right") || cmd.equals("r")) {
        score += game.movePlayer(m,0);
      }
      else if (cmd.equals("left") || cmd.equals("l")) {
        score += game.movePlayer(-m,0);
      }
      else if (cmd.equals("up") || cmd.equals("u")) {
        score += game.movePlayer(0,-m);
      }
      else if (cmd.equals("down") || cmd.equals("d")) {
        score += game.movePlayer(0,m);
      }
      else if (cmd.equals("jump") || cmd.equals("jr")) {
        int check1 = game.movePlayer(m,0);
        if (check1 == 0) {
          int check2 = game.movePlayer(m,0);
          score += check2;
        }
        else {
          System.out.println("Cannot jump over a wall.");
          score += check1;
        }
      }
      else if (cmd.equals("jumpleft") || cmd.equals("jl")) {
        int check1 = game.movePlayer(-m,0);
        if (check1 == 0) {
          int check2 = game.movePlayer(-m,0);
          score += check2;
        }
        else {
          System.out.println("Cannot jump over a wall.");
          score += check1;
        }
      }
      else if (cmd.equals("jumpup") || cmd.equals("ju")) {
        int check1 = game.movePlayer(0,-m);
        if (check1 == 0) {
          int check2 = game.movePlayer(0,-m);
          score += check2;
        }
        else {
          System.out.println("Cannot jump over a wall.");
          score += check1;
        }
      }
      else if (cmd.equals("jumpdown") || cmd.equals("jd")) {
        int check1 = game.movePlayer(0,m);
        if (check1 == 0) {
          int check2 = game.movePlayer(0,m);
          score += check2;
        }
        else {
          System.out.println("Cannot jump over a wall.");
          score += check1;
        }
      }
      else if (cmd.equals("pickup") || cmd.equals("p")) {
        score += game.pickupPrize();
      }
      else if (cmd.equals("trap")) {
        score += game.springTrap(0,0);
      }
      else if (cmd.equals("help") || cmd.equals("?")) {
        System.out.println("Commands:");
        System.out.println("Movement: right/left/up/down or r/l/u/d");
        System.out.println("Jumping: jump/jr, jumpleft/jl, jumpup/ju, jumpdown/jd");
        System.out.println("pickup or p");
        System.out.println("trap");
        System.out.println("replay");
        System.out.println("quit or q");
        System.out.println();
      }

      else if (cmd.equals("replay")) {
        score += game.replay();
        System.out.println("Board reset. Steps cleared.");
      }

      else if (cmd.equals("quit") || cmd.equals("q")) {
        System.out.println("You quit the game.");
        play = false;
      }
      if (gameAtEnd(game)) {
        System.out.println("You reached the far right side!");
        play = false;
      }
    }
    score += game.endGame();
    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
  public static boolean gameAtEnd(GameGUI game) {
    return game.playerLoc.getX() > 510 - 120;   // error here, says "The field GameGUI.playerLoc is not visibleJava(33554503)"
  }
}
