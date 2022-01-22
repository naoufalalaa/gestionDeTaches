package metier;

import java.util.List;

public interface IMetier {

    void addIntervenant(Intervenant p);
    void deleteIntervenant(Intervenant p);
    void updateIntervenant(Intervenant p);
    List<Intervenant> getAllIntervenant();
    List<Panne> getAllPannes();
    Intervenant findIntervenantByID(int id);
    List<Intervenant> findIntervenantByMC(String mc);

    Panne findPanneByID(int id);
    Panne findPanneByTitre(String titre);
    Panne findPanneByReferenceMachine(String ref);

    void addTache(Tache t);
    void updateTache(Tache t);
    void deleteTache(Tache t);
    void updateStatusTache(int idt,String s);
    List<Tache> getAllTaches();
    List<Tache> findTacheParMC(String motCle);
    Tache findTacheParID(int idt);
    List<Tache> findTacheByIDPanne(int idp);


    void addMachine(Machine m,Panne p);
    void deleteMachine(Machine m);
    void updateMachine(Machine m, Machine old);

    List<Machine> getAllMachines();
    List<Machine> findMachineParMC(String motCle);




}
