//Zadacha 8/51
package Collections.ExamExercises;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NonExistingItemException extends Exception {
    public NonExistingItemException(String message) {
        super(message);
    }
}

class Archive {
    private int id;
    private LocalDate dateArchived = null;

    public Archive(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateArchived() {
        return dateArchived;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    @Override
    public String toString() {
        return String.format("Item %d archived at %s", id, dateArchived);
    }
}

class LockedArchive extends Archive {

    private LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    public LocalDate getDateToOpen() {
        return dateToOpen;
    }
}

class SpecialArchive extends Archive {

    private int maxOpen;
    private int counter = 0;

    public SpecialArchive(int id, int  maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public void plusCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

class ArchiveStore {

    private List<Archive> archiveList;
    private StringBuilder sb = new StringBuilder();

    public ArchiveStore() {
        archiveList = new ArrayList<>();
    }

    public void archiveItem(Archive item, LocalDate date)
    {
        item.setDateArchived(date);
        archiveList.add(item);
        sb.append(item).append("\n");
    }

    public void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive found = null;
        for (Archive arch : archiveList)
        {
            if(id == arch.getId())
            {
                found = arch;
            }
        }
        if(found == null)
        {
            throw new NonExistingItemException(String.format("Item with id %d doesn't exist", id));
        }
        else
            if(found instanceof LockedArchive)
            {
                if(date.isBefore(((LockedArchive) found).getDateToOpen()))
                {
                    sb.append(String.format("Item %d cannot be opened before %s", id, ((LockedArchive) found).getDateToOpen())).append("\n");
                }
                else
                    sb.append(String.format("Item %d opened at %s", id, date)).append("\n");
            }
            else if(found instanceof SpecialArchive)
            {
                if(((SpecialArchive) found).getMaxOpen() <= ((SpecialArchive) found).getCounter())
                {
                    sb.append(String.format("Item %d cannot be opened more than %s times", id, ((SpecialArchive) found).getMaxOpen())).append("\n");
                }
                else
                    sb.append(String.format("Item %d opened at %s", id, date)).append("\n");
                ((SpecialArchive) found).plusCounter();
            }
    }

    public String getLog()
    {
        return sb.toString();
    }
}

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 11, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            LocalDate dateToOpen = date.plusDays(days);
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}
