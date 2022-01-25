package metier;

import com.jfoenix.controls.JFXComboBox;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Tache implements Comparable<Tache>{
    private int ID_TACHE;
    private String TITRE;
    private String DESCRIPTION;
    private String MATERIELS;
    private LocalDate START_DATE;
    private LocalDate END_DATE;
    private String STATUT;
    private Panne PANNE;
    private Intervenant INTERVENANT;

    public Tache(int ID_TACHE, String TITRE, String DESCRIPTION, String MATERIELS, LocalDate START_DATE, LocalDate END_DATE, String STATUT, Panne PANNE, Intervenant INTERVENANT) {
        this.ID_TACHE = ID_TACHE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.PANNE = PANNE;
        this.INTERVENANT = INTERVENANT;
    }
    public Tache(int ID_TACHE, String TITRE, String DESCRIPTION, String MATERIELS, LocalDate START_DATE, LocalDate END_DATE, String STATUT, Intervenant INTERVENANT) {
        this.ID_TACHE = ID_TACHE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.INTERVENANT = INTERVENANT;
    }
    public Tache(String TITRE, String DESCRIPTION, String MATERIELS, LocalDate START_DATE, LocalDate END_DATE, String STATUT,Panne PANNE, Intervenant INTERVENANT) {
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.PANNE = PANNE;
        this.INTERVENANT = INTERVENANT;
    }
    public Tache() {

    }

    public int getID_TACHE() {
        return ID_TACHE;
    }

    public void setID_TACHE(int ID_TACHE) {
        this.ID_TACHE = ID_TACHE;
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

    public String getMATERIELS() {
        return MATERIELS;
    }

    public void setMATERIELS(String MATERIELS) {
        this.MATERIELS = MATERIELS;
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

    public String getSTATUT() {
        return STATUT;
    }

    public void setSTATUT(String STATUT) {
        this.STATUT = STATUT;
    }

    public Panne getPANNE() {
        return PANNE;
    }

    public void setPANNE(Panne PANNE) {
        this.PANNE = PANNE;
    }

    public Intervenant getINTERVENANT() {
        return INTERVENANT;
    }

    public void setINTERVENANT(Intervenant INTERVENANT) {
        this.INTERVENANT = INTERVENANT;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "ID_TACHE=" + ID_TACHE +
                ", TITRE='" + TITRE + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", MATERIELS='" + MATERIELS + '\'' +
                ", START_DATE=" + START_DATE +
                ", END_DATE=" + END_DATE +
                ", STATUT='" + STATUT + '\'' +
                ", PANNE=" + PANNE +
                ", INTERVENANT=" + INTERVENANT +
                '}';
    }
    @Override
    public int compareTo(Tache t) {
        return this.getSTART_DATE().compareTo(t.getSTART_DATE());
    }
    Comparator<Tache> CompareByStartDate = new Comparator<Tache>() {
        @Override
        public int compare(Tache o1, Tache o2) {
            return o1.getSTART_DATE().compareTo(o2.getEND_DATE());
        }
    };
}
