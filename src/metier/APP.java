package metier;

import java.util.List;

public class APP {
    public static void main(String[] args) {
        IMetier metier=new MetierImp() {
        };
        // metier.addIntervenant(new Intervenant("takiche","ff","ef","dfd","sfs","sf"));
        //metier.deleteProfesseur(1);
        //metier.editeDepartement(new Departement());
        List<Intervenant> P=metier.getAllIntervenant();
        for(Intervenant c:P){
          System.out.println(c.getNOM());
        }
    }
}
