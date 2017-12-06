
public class XO_tester {
   public static void main(String[] args){
      XO_game game = new XO_game();
      System.out.println(game);
      game.x_move((byte)1,(byte)1);
      System.out.println(game);
      game.o_move((byte)0,(byte)0);
      System.out.println(game);
      System.out.println(game.game_result()); //to check ended or not
      game.x_move((byte)1,(byte)2);
      System.out.println(game);
      game.o_move((byte)0,(byte)2);
      System.out.println(game);
      System.out.println(game.game_result());
      game.x_move((byte)1,(byte)0);
      System.out.println(game);
      game.o_move((byte)2,(byte)2);
      System.out.println(game);
      System.out.println(game.game_result());
   }
}
