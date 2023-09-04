package stadium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Stadium {
    private String name;
    private HashMap<String, Sector> sectors;

    public Stadium(String name) {
        this.name = name;
        this.sectors = new HashMap<String, Sector>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        for (int i = 0; i < sectorNames.length; i++) {
            this.sectors.put(sectorNames[i], new Sector(sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        Sector sector = sectors.get(sectorName);
        if (sector.isTaken(seat)) {
            throw new SeatTakenException();
        }
        sector.takeSeat(seat, type);
    }

    public void showSectors() {
        this.sectors.values()
                .stream()
                .sorted(Comparator.comparing(Sector::free).reversed()
                        .thenComparing(Sector::getCode))
                .forEach(System.out::println);
    }
}
