import java.time.LocalDate;

public class Task {
    String name;
    int status;
    LocalDate created;
    LocalDate updated;

    Task (String name, int status, LocalDate created, LocalDate updated){
        this.name = name;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

}
