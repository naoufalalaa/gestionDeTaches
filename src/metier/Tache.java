package metier;

import java.util.List;

public class Tache {
    private int ID_TACHE;
    private String TITRE;
    private String DESCRIPTION;
    private String MATERIELS;
    private String START_DATE;
    private String END_DATE;
    private String STATUT;
    private Panne PANNE;
    private List<Intervention> interventions;

    public Tache(int ID_TACHE, String TITRE, String DESCRIPTION, String MATERIELS, String START_DATE, String END_DATE, String STATUT, Panne PANNE, List<Intervention> interventions) {
        this.ID_TACHE = ID_TACHE;
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.PANNE = PANNE;
        this.interventions = interventions;
    }


    public Tache(String TITRE, String DESCRIPTION, String MATERIELS, String START_DATE, String END_DATE, String STATUT, Panne PANNE, List<Intervention> interventions) {
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.PANNE = PANNE;
        this.interventions = interventions;
    }
    public Tache(String TITRE, String DESCRIPTION, String MATERIELS, String START_DATE, String END_DATE, String STATUT, Panne PANNE) {
        this.TITRE = TITRE;
        this.DESCRIPTION = DESCRIPTION;
        this.MATERIELS = MATERIELS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
        this.STATUT = STATUT;
        this.PANNE = PANNE;
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

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
}
