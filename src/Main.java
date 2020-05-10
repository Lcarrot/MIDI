import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        Player player = new Player();
        for (int i = 0; i < str.length(); i++) {
            player.playNote(str.charAt(i));
        }
        player.writeNotes("aaa" , str);
        player.playFromFile("aaa");
        player.close();
    }
}
