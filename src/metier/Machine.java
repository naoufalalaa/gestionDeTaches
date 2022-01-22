package metier;

public class Machine {
    private String  REFERENCE;
    private String NOM;
    private int MODEL;

    public Machine(String REFERENCE, String NOM, int MODEL) {
        this.REFERENCE = REFERENCE;
        this.NOM = NOM;
        this.MODEL = MODEL;
    }
    public Machine() {

    }

    public Machine() {

    }

    public String getREFERENCE() {
        return REFERENCE;
    }

    public void setREFERENCE(String REFERENCE) {
        this.REFERENCE = REFERENCE;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public int getMODEL() {
        return MODEL;
    }

    public void setMODEL(int MODEL) {
        this.MODEL = MODEL;
    }
}
