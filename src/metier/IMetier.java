package metier;

import java.util.List;

public interface IMetier {

    void addIntervenant(Intervenant p);
    void deleteIntervenant(Intervenant p);
    void updateIntervenant(Intervenant p);
    List<Intervenant> getAllIntervenant();
    Intervenant findIntervenantByID(int id);

    void addTache(Tache t);
    void deleteTache(Tache t);
    void updateTache(Tache t);
    List<Tache> getAllTaches();
    List<Tache> findTacheParMC(String motCle);


    void addMachine(Machine m);
    void deleteMachine(Machine m);
    void updateMachine(Machine m, Machine old);

    List<Machine> getAllMachines();
    List<Machine> findMachineParMC(String motCle);




}
