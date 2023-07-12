import java.util.ArrayList;

public class Exercise {
    private ArrayList<Question1> questions;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    private int secondsElapsed = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Questions:\n");
        for (int i = 0; i < questions.size(); i++) {
            sb.append("Question ").append(i + 1).append(":\n").append(questions.get(i)).append("\n");
        }
        sb.append("Total seconds elapsed = ").append(secondsElapsed).append("\n");
        sb.append("Score = ").append(score).append("\n");
        sb.append("\n\n\n\n");
        return sb.toString();
    }
    public String toReport(){
        StringBuilder sb = new StringBuilder();
     for (int i = 0; i < questions.size(); i++)  {
         sb.append(questions.get(i).toReport());
     }
     sb.append(score).append("\n");
     return sb.toString();
    }

    public ArrayList<Question1> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question1> questions) {
        this.questions = questions;
    }

    public Exercise() {
        this.score = 0;
        this.secondsElapsed = 0;
        this.questions = new ArrayList<>();
    }
}
