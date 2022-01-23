package metier;

public class Intervenant extends User {

    public Intervenant(int ID_USER, String NOM, String PRENOM, String TELEPHONE, String EMAIL, String LOGIN, String PASSWORD) {
        super(ID_USER, NOM, PRENOM, TELEPHONE, EMAIL, LOGIN, PASSWORD);
    }
    public Intervenant(String NOM, String PRENOM, String TELEPHONE, String EMAIL, String LOGIN, String PASSWORD) {
        super(NOM, PRENOM, TELEPHONE, EMAIL, LOGIN, PASSWORD);
    }

    @Override
    public String toString() {
        return super.getNOM();
    }

    public Intervenant() {
    }


}
