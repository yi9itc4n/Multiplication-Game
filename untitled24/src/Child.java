import java.util.ArrayList;

public class Child {
    private String name;

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    private int highScore;
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    private int secondsElapsed = 0;
    private ArrayList<Question1> questions;
    private ArrayList<Exercise> exercises;

    public Child(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.exercises = new ArrayList<>();
        this.highScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        sb.append("High Score: ").append(highScore).append("\n\n");
        for (int i = 0; i < exercises.size(); i++) {
            sb.append("Exercise ").append(i+1).append(":\n\n").append(exercises.get(i)).append("\n");
        }
        sb.append("\n\n\n\n");
        return sb.toString();
    }

    public String toReport(){
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\t");
        sb.append(highScore).append("\t");
        while(i<exercises.size()){
            sb.append(exercises.get(i).toReport());
            i++;
            if(i!=exercises.size()){
                sb.append("\t\t");
            }
        }
        return sb.toString();
    }


    public ArrayList<Question1> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question1> questions) {
        this.questions = questions;
    }
}
