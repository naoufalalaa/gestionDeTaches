package metier;

import java.util.List;

public class APP {
    public static void main(String[] args) {
        IMetier metier=new MetierImp();
         //metier.addIntervenant(new Intervenant("takiche","ff","ef","dfd","s   fs","sf"));
        //metier.deleteProfesseur(1);
        //metier.editeDepartement(new Departement());
       // System.out.println(metier.findPanneByReferenceMachine("9FS8F7S8F7S87F?").getTITRE());
       // System.out.println(metier.findTacheByIDPanne(metier.findPanneByTitre("jhj").getID_PANNE()).get(2).getID_TACHE());
        System.out.println(metier.findTacheParID(3).getDESCRIPTION());

    }
}
