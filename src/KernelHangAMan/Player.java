package KernelHangAMan;

/**
 * Created by Rifux on 20/04/2017.
 */
public class Player {
    private String pseudonyme;
    private int highestScore;

    public Player(String pseudonyme, int highestScore) {
        this.pseudonyme = pseudonyme;
        this.highestScore = highestScore;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    @Override
    public String toString() {
        return pseudonyme+" : "+highestScore+"\n";
    }
}
