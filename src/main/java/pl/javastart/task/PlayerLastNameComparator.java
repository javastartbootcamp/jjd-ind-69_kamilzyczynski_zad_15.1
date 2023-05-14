package pl.javastart.task;

import java.util.Comparator;

public class PlayerLastNameComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 != null && o2 == null) {
            return 1;
        } else if (o1 == null && o2 != null) {
            return -1;
        }
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
