import java.io.Serializable;

public class HighScoreNode implements Comparable<HighScoreNode>, Serializable {
    private String name;
    private int score;
    public HighScoreNode(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return  score + "p - " + name;
    }


    @Override
    public int compareTo(HighScoreNode o) {
        //HighScoreNode temp = (HighScoreNode) o;
        return  o.getScore() - this.getScore();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
