package metier;

public class Panne {
    private int ID_PANNE;
    private String TITRE;
    private String DESCRIPTION;
    private String START_DATE;
    private String END_DATE;

    public Panne(int ID_PANNE, String TITRE, String DESCRIPTION, String START_DATE, String END_DATE) {
        this.ID_PANNE = ID_PANNE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
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

    public String getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }
}
