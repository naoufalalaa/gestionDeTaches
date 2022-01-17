package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DAO.SignletonConnectionDB;

public class MetierImp implements IMetier{
    private final SignletonConnectionDB db=new SignletonConnectionDB();

    @Override
    public void addIntervenant(Intervenant p) {
        Connection conn=db.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into user(nom,prenom,telephone,email,login,password) values (?,?,?,?,?,?)");
            pstm.setString(1,p.getNOM());
            pstm.setString(2,p.getPRENOM());
            pstm.setString(4,p.getTELEPHONE());
            pstm.setString(5,p.getEMAIL());
            pstm.setString(3,p.getLOGIN());
            pstm.setString(6,p.getPASSWORD());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIntervenant(Intervenant p) {
        Connection conn=db.getConnection();
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
        Connection conn=db.getConnection();
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
        Connection conn=db.getConnection();
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
    public Intervenant findIntervenantByID(int id) {
        Connection conn=db.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("DELETE FROM user WHERE ID_USER=?");
          //  pstm.setInt(1,p.getID_USER());
            pstm.executeUpdate();
            return  null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }



    @Override
    public void addTache(Tache t) {
        Connection conn=db.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into tache(TITRE,DESCRIPTION,MATERIELS,START_DATE,END_DATE,SATUT) values (?,?,?,?,?,?)");
            pstm.setString(1,t.getTITRE());
            pstm.setString(2,t.getDESCRIPTION());
            pstm.setString(3,t.getMATERIELS());
            pstm.setString(4,t.getSTART_DATE());
            pstm.setString(5,t.getEND_DATE());
            pstm.setString(6,t.getSTATUT());
            pstm.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTache(Tache t) {
        Connection conn=db.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("DELETE FROM tache WHERE ID_TACHE=?");
            pstm.setInt(1,t.getID_TACHE());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateTache(Tache t) {

    }

    @Override
    public List<Tache> getAllTaches() {
        List<Tache> taches=new ArrayList<>();
        Connection conn=db.getConnection();
        try{
            PreparedStatement pstm=conn.prepareStatement("select * from tache");
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                PreparedStatement pstm2=conn.prepareStatement("select * from  intervention WHERE ID_TACHE=?");
                pstm2.setInt(1,rs.getInt("ID_TACHE"));
                List<Intervention> interventions=new ArrayList<>();
                ResultSet rs2=pstm2.executeQuery();
                while(rs2.next()){
                    interventions.add(new Intervention(rs.));


                }
              //  Tache t=new Tache(rs.getInt("ID_TACHE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getString("MATERIELS"),rs.getString("START_DATE"),rs.getString("END_DATE"),rs.getString("STATUT"));
               // taches.add(t);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public List<Tache> findTacheParMC(String motCle) {
        Connection conn = db.getConnection();
        List<Tache> taches = new ArrayList<>();
        try{
            Statement pstm = conn.createStatement();
            ResultSet rs = pstm.executeQuery("select * from intervention WHERE TITRE LIKE '%"+motCle+"%' OR DESCRIPTION '%"+motCle+"%'");
            while (rs.next()){
                taches.add(new Tache(rs.getString("ID_TACHE"),rs.getString("TITRE"),rs.getString("DESCRIPTION"),rs.getString("MATERIELS"),));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return taches;
    }

    @Override
    public Tache getTacheByID(int ID) {
        Connection conn = db.getConnection();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tache WHERE ID_TACHE = "+ID);
            while (rs.next()){
                Statement stm1 = conn.createStatement();
                ResultSet rs1 = stm1.executeQuery("SELECT * FROM panne WHERE ID_PANNE = "+rs.getInt("ID_PANNE"));
                Panne panne = new Panne(rs.getInt("ID_PANNE"), rs1.getString("TITRE") , rs1.getString("DESCRIPTION"), rs1.getString("START_DATE") , rs1.getString("END_DATE") , rs1.getString("REFERENCE"));
                Statement stm2 = conn.createStatement();
                ResultSet rs2 = stm2.executeQuery("SELECT * FROM intervention");
                return new Tache(ID, rs.getString("TITRE") , rs.getString("DESCRIPTION") , rs.getString("MATERIELS") , rs.getString("START_DATE") , rs.getString("END_DATE") , rs.getString("STATUS") , panne ,  );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Intervention> getTacheInterventions(int ID_TACHE) {
        List<Intervention> interventions = new ArrayList<>();
        Connection conn = db.getConnection();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM intervention WHERE ID_TACHE = "+ID_TACHE);
            while (rs.next()){
                interventions.add(new Intervention());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void addMachine(Machine m) {
        Connection conn=db.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("insert into machine(REFERENCE,NOM,MODEL) values (?,?,?)");
            pstm.setString(1,m.getREFERENCE());
            pstm.setString(2,m.getNOM());
            pstm.setInt(3,m.getMODEL());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMachine(Machine m) {
        Connection conn=db.getConnection();
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
        Connection conn=db.getConnection();
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
         Connection conn = db.getConnection();
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
        Connection conn = db.getConnection();
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
    public Machine getMachineByID(int ID) {
        return null;
    }
}
