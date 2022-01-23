package GANTT;

public class Tache {

    private int duration;
    private String name;
    private String status;
    private Tache previous;

    public Tache(int duration, String name, String status) {
        this.duration = duration;
        this.name = name;
        this.status = status;
        this.previous = new Tache(0,"none","done",null);
    }


    public Tache(int duration, String name, String status, Tache previous) {
        this.duration = duration;
        this.name = name;
        this.status = status;
        this.previous = previous;
    }

    public Tache getPrevious() {
        return previous;
    }

    public void setPrevious(Tache previous) {
        this.previous = previous;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
