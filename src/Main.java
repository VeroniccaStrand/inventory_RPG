import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        Game game = new Game(player, scanner);

        game.start();
    }
}
