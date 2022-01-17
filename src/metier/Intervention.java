package metier;

public class Intervention {
        private Intervenant intervenant;
        private Tache tache;
        private String date_END;
        private String date_START;
        private String status;

        public Intervention(Intervenant intervenant, Tache tache, String date_END, String date_START, String status) {
                this.intervenant = intervenant;
                this.tache = tache;
                this.date_END = date_END;
                this.date_START = date_START;
                this.status = status;
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

}
