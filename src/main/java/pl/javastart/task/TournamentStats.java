package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {
    public static final String FIRST_NAME = "1";
    public static final String LAST_NAME = "2";
    public static final String SCORE = "3";

    void run(Scanner scanner) throws IOException {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        List<Player> players = getPlayers(scanner);
        Comparator<Player> comparator = chooseSortMethod(scanner);
        Collections.sort(players, comparator);
        save(players);
        System.out.println("Dane posortowano i zapisano do pliku stats.csv");
    }

    private Comparator<Player> chooseSortMethod(Scanner scanner) {
        System.out.printf("Po jakim parametrze posortować? (%s - imię, %s - nazwisko, %s - wynik) \n", FIRST_NAME,
                LAST_NAME, SCORE);
        String choice = scanner.nextLine();
        Comparator<Player> comparator = null;
        boolean stopAsking = true;
        do {
            switch (choice) {
                case FIRST_NAME -> comparator = new PlayerFirstNameComparator();
                case LAST_NAME -> comparator = new PlayerLastNameComparator();
                case SCORE -> comparator = new PlayerScoreComparator();
                default -> {
                    System.out.println("Wprowadzono błędną opcję. Jeszcze raz.");
                    stopAsking = false;
                }
            }
        } while (!stopAsking);

        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int sortChoice = scanner.nextInt();
        if (sortChoice == 2) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    private List<Player> getPlayers(Scanner scanner) {
        List<Player> players = new ArrayList<>();
        boolean stopAsking = false;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            String nextPlayer = scanner.nextLine();

            if (nextPlayer.equalsIgnoreCase("stop")) {
                stopAsking = true;
            } else {
                String[] split = nextPlayer.split(" ");
                players.add(new Player(split[0], split[1], Integer.parseInt(split[2])));
            }
        } while (!stopAsking);
        return players;
    }

    private void save(List<Player> players) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("stats.csv"));
        for (Player player : players) {
            writer.write(player.getFirstName() + " " + player.getLastName() + ";" + player.getScore());
            writer.newLine();
        }
        writer.close();
    }
}
