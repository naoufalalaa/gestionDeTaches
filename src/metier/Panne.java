package metier;

import java.time.LocalDate;

public class Panne {
    private int ID_PANNE;
    private String TITRE;
    private String DESCRIPTION;
    private LocalDate START_DATE;
    private LocalDate END_DATE;
    private Machine machine;

    public Panne(int ID_PANNE, String TITRE, String DESCRIPTION, LocalDate START_DATE, LocalDate END_DATE) {
        this.ID_PANNE = ID_PANNE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }
    public Panne(String TITRE, String DESCRIPTION, LocalDate START_DATE, LocalDate END_DATE) {
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public Panne(int ID_PANNE, String TITRE, String DESCRIPTION, LocalDate START_DATE, LocalDate END_DATE, Machine machine) {
        this.ID_PANNE = ID_PANNE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.machine = machine;
    }

    public Panne() {

    }

    public int getID_PANNE() {
        return ID_PANNE;
    }

    public void setID_PANNE(int ID_PANNE) {
        this.ID_PANNE = ID_PANNE;
    }

    public String getTITRE() {
        return TITRE;
    }

    public void setTITRE(String TITRE) {
        this.TITRE = TITRE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public LocalDate getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(LocalDate START_DATE) {
        this.START_DATE = START_DATE;
    }

    public LocalDate getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(LocalDate END_DATE) {
        this.END_DATE = END_DATE;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return TITRE;
    }
}
