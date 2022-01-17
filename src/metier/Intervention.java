package metier;

public class Intervention {
        private Intervenant intervenant;
        private Tache tache;
        private String START_DATE;
        private String END_DATE;

        public Intervention(Intervenant intervenant, Tache tache, String START_DATE, String END_DATE) {
                this.intervenant = intervenant;
                this.tache = tache;
                this.START_DATE = START_DATE;
                this.END_DATE = END_DATE;
        }
        public Intervention(String START_DATE, String END_DATE) {

                this.START_DATE = START_DATE;
                this.END_DATE = END_DATE;
        }

        public Intervenant getIntervenant() {
                return intervenant;
        }

        public void setIntervenant(Intervenant intervenant) {
                this.intervenant = intervenant;
        }

        public Tache getTache() {
                return tache;
        }

        public void setTache(Tache tache) {
                this.tache = tache;
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
}
