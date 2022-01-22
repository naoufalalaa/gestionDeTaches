package metier;

public class User {
    private int ID_USER;
    private String NOM;
    private String PRENOM;
    private String TELEPHONE;
    private String EMAIL;
    private String LOGIN;
    private String PASSWORD;
    private String ROLE;

    public User(int ID_USER, String NOM, String PRENOM, String TELEPHONE, String EMAIL, String LOGIN, String PASSWORD) {
        this.ID_USER = ID_USER;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }
    public User(String NOM, String PRENOM, String TELEPHONE, String EMAIL, String LOGIN, String PASSWORD) {
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }

    public User(int ID_USER, String NOM, String PRENOM, String TELEPHONE, String EMAIL) {
        this.ID_USER = ID_USER;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
    }

    public User() {

    }

    //this controller is used for authentication
    public User(Integer id_user, String nom, String role) {
        this.ID_USER = id_user;
        NOM = nom;
        ROLE = role;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getROLE() {
        return ROLE;
    }
}
