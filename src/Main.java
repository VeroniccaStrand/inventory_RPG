import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Skapar Scanner för användarinmatning
        Player player = new Player();              // Skapar spelaren
        Game game = new Game(player, scanner);     // Initierar spelet med spelaren och scannern

        game.start();  // Starta spelet
    }
}
