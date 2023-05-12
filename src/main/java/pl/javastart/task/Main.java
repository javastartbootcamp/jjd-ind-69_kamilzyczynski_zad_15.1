package pl.javastart.task;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        TournamentStats tournamentStats = new TournamentStats();
        Scanner scanner = new Scanner(System.in);
        tournamentStats.run(scanner);
    }
}
