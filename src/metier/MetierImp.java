package metier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetierImp implements IMetier{
    private final Connection conn= SignletonConnectionDB.getConnection();



    @Override
    public void addIntervenant(User p) {
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into user(NOM,PRENOM,TELEPHONE,EMAIL,LOGIN,PASSWORD) values (?,?,?,?,?,?)");
            pstm.setString(1,p.getNOM());
            pstm.setString(2,p.getPRENOM());
            pstm.setString(3,p.getTELEPHONE());
            pstm.setString(4,p.getEMAIL());
            pstm.setString(5,p.getLOGIN());
            pstm.setString(6,p.getPASSWORD());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIntervenant(Intervenant p) {
        try {
            PreparedStatement pstm=conn.prepareStatement("DELETE FROM user WHERE ID_USER=?");
            pstm.setInt(1,p.getID_USER());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateIntervenant(Intervenant p) {
        try {
            PreparedStatement pstm=conn.prepareStatement("UPDATE user SET NOM=? ,PRENOM=?, LOGIN=? ,PASSWORD=? ,TELEPHONE=? ,EMAIL=?  WHERE ID_USER=?");
            pstm.setString(1,p.getNOM());
            pstm.setString(2,p.getPRENOM());
            pstm.setString(3,p.getLOGIN());
            pstm.setString(4,p.getPASSWORD());
            pstm.setString(5,p.getTELEPHONE());
            pstm.setString(6,p.getEMAIL());
            pstm.setInt(7, p.getID_USER());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Intervenant> getAllIntervenant() {
        List<Intervenant> intervenants=new ArrayList<>();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from  user");
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Intervenant p=new Intervenant(rs.getInt("ID_USER"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getString("TELEPHONE"),rs.getString("EMAIL"),rs.getString("LOGIN"),rs.getString("PASSWORD"));
                intervenants.add(p);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return intervenants;
    }

    @Override
    public List<Panne> getAllPannes() {
        List<Panne> pannes = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM panne");
            while(rs.next()){
                pannes.add(new Panne(rs.getInt("ID_PANNE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pannes;    }

    @Override
    public Intervenant findIntervenantByID(int id) {
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from user WHERE ID_USER=?");
            pstm.setInt(1,id);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Intervenant intervenant=new Intervenant(rs.getInt("ID_USER"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getString("TELEPHONE"),rs.getString("EMAIL"),rs.getString("LOGIN"),rs.getString("PASSWORD"));
                return intervenant;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public List<Intervenant> findIntervenantByMC(String motCle) {
        List<Intervenant> intervenants=new ArrayList<>();

        if(motCle.isEmpty()){
            return intervenants;
        }
        else {
            try {
                PreparedStatement pstm=conn.prepareStatement("select * from user where NOM like ? OR PRENOM LIKE ?");
                pstm.setString(1,"%"+motCle+"%");
                pstm.setString(2,"%"+motCle+"%");
                ResultSet rs= pstm.executeQuery();
                while (rs.next()){

                    Intervenant p=new Intervenant(rs.getInt("ID_USER"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getString("TELEPHONE"),rs.getString("EMAIL"),rs.getString("LOGIN"),rs.getString("PASSWORD"));
                    intervenants.add(p);
                    }
            }catch (Exception e){
                e.printStackTrace();
            }

            return  intervenants;
        }
    }

    @Override
    public Panne findPanneByID(int id) {
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from  panne WHERE ID_PANNE=?");
            pstm.setInt(1,id);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Panne panne =new Panne(rs.getInt("ID_PANNE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate());
                return panne;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Panne findPanneByTitre(String titre) {
        Panne panne=new Panne();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from  panne WHERE TITRE=?");
            pstm.setString(1,titre);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                panne=new Panne(rs.getInt("ID_PANNE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return panne;
    }

    @Override
    public Panne findPanneByReferenceMachine(String ref) {

        Panne panne = new Panne();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from panne WHERE REFERENCE=?");
            pstm.setString(1,ref);
            ResultSet rs=pstm.executeQuery();

            if (rs.next()){
               panne=new Panne(rs.getInt("ID_PANNE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return panne;
    }


    @Override
    public void addTache(Tache t) {
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into tache(TITRE,DESCRIPTION,MATERIELS,START_DATE,END_DATE,STATUT,ID_PANNE,ID_USER) values (?,?,?,?,?,?,?,?)");
            pstm.setString(1,t.getTITRE());
            pstm.setString(2,t.getDESCRIPTION());
            pstm.setString(3,t.getMATERIELS());
            pstm.setDate(4,Date.valueOf(t.getSTART_DATE()));
            pstm.setDate(5,Date.valueOf(t.getEND_DATE()));
            pstm.setString(6,t.getSTATUT());
            pstm.setInt(8,t.getINTERVENANT().getID_USER());
            pstm.setInt(7,t.getPANNE().getID_PANNE());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateTache(Tache oldTache, Tache t) {
        try {
        PreparedStatement pstm=conn.prepareStatement("UPDATE tache SET TITRE=? ,DESCRIPTION=?, MATERIELS=? ,START_DATE=? ,END_DATE=? ,STATUT=?,ID_PANNE=?,ID_USER=? WHERE ID_TACHE=?");
            pstm.setString(1, t.getTITRE());
            pstm.setString(2, t.getDESCRIPTION());
            pstm.setString(3, t.getMATERIELS());
            pstm.setDate(4,Date.valueOf(t.getSTART_DATE()));
            pstm.setDate(5,Date.valueOf(t.getEND_DATE()));
            pstm.setString(6, t.getSTATUT());
            pstm.setInt(8, t.getINTERVENANT().getID_USER());
            pstm.setInt(7, t.getPANNE().getID_PANNE());
            pstm.setInt(9, oldTache.getID_TACHE());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTache(Tache t) {
        try {
            PreparedStatement pstm=conn.prepareStatement("DELETE FROM tache WHERE ID_TACHE=?");
            pstm.setInt(1,t.getID_TACHE());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatusTache(int idt,String s) {
        try {
            PreparedStatement pstm=conn.prepareStatement("UPDATE tache SET STATUT=? WHERE id_TACHE=?");
            pstm.setString(1,s);
            pstm.setInt(2,idt);
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Tache> getAllTaches() {
        List<Tache> taches=new ArrayList<>();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from  tache");
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                PreparedStatement p = conn.prepareStatement("SELECT * FROM panne WHERE ID_PANNE = ?");
                p.setInt(1,rs.getInt("ID_PANNE"));
                ResultSet rp = p.executeQuery();
                while (rp.next()){
                    Panne panne = new Panne(rp.getInt("ID_PANNE"),rp.getString(2),rp.getString(3),rp.getDate(4).toLocalDate(),rp.getDate(5).toLocalDate());
                    PreparedStatement inter = conn.prepareStatement("SELECT * FROM user WHERE ID_USER = ?");
                    inter.setInt(1,rs.getInt("ID_USER"));
                    ResultSet ri = inter.executeQuery();
                    while (ri.next()) {
                        Intervenant intervenant = new Intervenant(ri.getInt(1), ri.getString(2), ri.getString(3), ri.getString(4), ri.getString(5), ri.getString(6), ri.getString(7));
                        Tache t = new Tache(rs.getInt("ID_TACHE"), rs.getString("TITRE"), rs.getString("DESCRIPTION"), rs.getString("MATERIELS"), rs.getDate("START_DATE").toLocalDate(), rs.getDate("END_DATE").toLocalDate(), rs.getString("STATUT"), panne, intervenant);
                        taches.add(t);
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public List<Tache> findTacheParMC(String motCle) {
        List<Tache> taches=new ArrayList<>();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from  tache WHERE TITRE LIKE ?");
            pstm.setString(1,"%"+motCle+"%");
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Panne panne = findPanneByID(rs.getInt("ID_PANNE"));
                Intervenant intervenant = findIntervenantByID(rs.getInt("ID_USER"));
                Tache t=new Tache(rs.getInt("ID_TACHE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getString("MATERIELS"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate(),rs.getString("STATUT"),panne,intervenant);
                taches.add(t);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public Tache findTacheParID(int idt) {
        Tache tache=new Tache();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from tache WHERE ID_TACHE=?");
            pstm.setInt(1,idt);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Panne panne = findPanneByID(rs.getInt("ID_PANNE"));
                Intervenant intervenant = findIntervenantByID(rs.getInt("ID_USER"));
                tache=new Tache(rs.getInt("ID_TACHE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getString("MATERIELS"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate(),rs.getString("STATUT"),panne,intervenant);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return tache;
    }

    @Override
    public List<Tache> findTacheByIDPanne(int idp) {
        List<Tache> taches=new ArrayList<>();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from tache WHERE ID_PANNE=?");
            pstm.setInt(1,idp);
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                Panne panne = findPanneByID(rs.getInt("ID_PANNE"));
                Intervenant intervenant = findIntervenantByID(rs.getInt("ID_USER"));
                Tache t=new Tache(rs.getInt("ID_TACHE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getString("MATERIELS"),rs.getDate("START_DATE").toLocalDate(),rs.getDate("END_DATE").toLocalDate(),rs.getString("STATUT"),panne,intervenant);
                taches.add(t);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return taches;
    }


    @Override
    public void addMachine(Machine m,Panne p) {
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into machine(REFERENCE,NOM,MODEL) values (?,?,?)");
            pstm.setString(1,m.getREFERENCE());
            pstm.setString(2,m.getNOM());
            pstm.setInt(3,m.getMODEL());
            pstm.executeUpdate();

            PreparedStatement pstm2=conn.prepareStatement("insert into panne(TITRE,DESCRIPTION,START_DATE,END_DATE,REFERENCE) values (?,?,?,?,?)");
            pstm2.setString(1,p.getTITRE());
            pstm2.setString(2,p.getDESCRIPTION());
            pstm2.setDate(3, Date.valueOf(p.getSTART_DATE()));
            pstm2.setDate(4,Date.valueOf(p.getEND_DATE()));
            pstm2.setString(5,m.getREFERENCE());

            pstm2.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMachine(Machine m) {
        try {

            PreparedStatement pstm=conn.prepareStatement("DELETE FROM machine where REFERENCE = ? ");
            pstm.setString(1,m.getREFERENCE());

            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateMachine(Machine m, Machine old) {
        try {
            PreparedStatement pstm=conn.prepareStatement("UPDATE machine SET NOM=? , MODEL = ? WHERE REFERENCE = ?");
            pstm.setString(1,m.getNOM());
            pstm.setInt(2,m.getMODEL());
            pstm.setString(3,m.getREFERENCE());

            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Machine> getAllMachines() {
         List<Machine> machines = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM machine");
            while(rs.next()){
                machines.add(new Machine(rs.getString("REFERENCE"),rs.getString("NOM"),rs.getInt("MODEL")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return machines ;
    }



    @Override
    public List<Machine> findMachineParMC(String motCle) {
        List<Machine> machines = new ArrayList<>();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM machine WHERE REFERENCE like '%"+motCle+"%' OR NOM LIKE '%"+motCle+"%' OR MODEL Like '%"+motCle+"%'");
            while (rs.next()){
                machines.add(new Machine(rs.getString("REFERENCE"),rs.getString("NOM"),rs.getInt("MODEL")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return machines;
    }

    @Override
    public List<Tache> getAllTachesPanne(int id_panne) {
        System.out.println("getallpanetaches");
        System.out.println(id_panne);

        List<Tache> taches = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM tache WHERE ID_PANNE=?");
            ps.setInt(1,id_panne);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Tache tache = new Tache();
                tache.setID_TACHE(rs.getInt("ID_TACHE"));
                tache.setTITRE(rs.getString("TITRE"));
                tache.setDESCRIPTION(rs.getString("DESCRIPTION"));
                tache.setMATERIELS(rs.getString("MATERIELS"));
                tache.setSTART_DATE(rs.getDate("START_DATE").toLocalDate());
                tache.setEND_DATE(rs.getDate("END_DATE").toLocalDate());
                tache.setSTATUT(rs.getString("STATUT"));
                taches.add(tache);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return taches;
    }

}
