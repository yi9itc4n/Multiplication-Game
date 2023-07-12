import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameScreen1 extends JFrame {
    private ArrayList<Question1> question1ArrayList;
    private static int minNumber;
    private static int maxNumber;
    private static int numQuestions;
    private int currentQuestion = 0;
    private static String username;
    private static ArrayList<Child> students;
    private Timer timer;
    private int secondsElapsed = 0;
    private JLabel timerLabel;
    private Exercise exercise;

    public static int getMinNumber() {
        return minNumber;
    }

    public static void setMinNumber(int minNumber) {
        GameScreen1.minNumber = minNumber;
    }

    public static int getMaxNumber() {
        return maxNumber;
    }

    public static void setMaxNumber(int maxNumber) {
        GameScreen1.maxNumber = maxNumber;
    }

    public static int getNumQuestions() {
        return numQuestions;
    }

    public static void setNumQuestions(int numQuestions) {
        GameScreen1.numQuestions = numQuestions;
    }

    public GameScreen1(int min, int max, int numQuestions, String username, ArrayList<Child> students) {
        this.minNumber = min;
        this.maxNumber = max;
        this.numQuestions = numQuestions;
        this.username = username;
        this.students = students;
        this.exercise = new Exercise();
        question1ArrayList = new ArrayList<>();

        // Set up the game screen GUI
        setTitle("Multiplication Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 200);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        timerLabel = new JLabel("Time: 0 seconds");
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        container.add(timerLabel, BorderLayout.NORTH);

        JPanel questionPanel = new JPanel(new FlowLayout());
        container.add(questionPanel, BorderLayout.CENTER);

        displayNextQuestion(questionPanel);

        // Create and start the timer
        timer = new Timer(1000, new TimerActionListener());
        timer.start();

        setVisible(true);
    }
    private Child findChild(String username) {
        for (Child child : students) {
            if (child.getName().equals(username)) {
                return child;
            }
        }
        return null;
    }

    public int elapsedSeconds(ArrayList<Question1> question1ArrayList) {
        int secs = 0;
        for (Question1 q : question1ArrayList) {
            secs += q.getSecondsElapsed();
        }
        return secs;
    }
    private void displayNextQuestion(Container container) {
        Question1 Q = new Question1();
        if (currentQuestion < numQuestions) {
            Child child = findChild(username);
            String qString = Q.generateRandomNumbers(minNumber, maxNumber);
            JLabel questionLabel = new JLabel(qString);
            container.add(questionLabel);
            JTextField answerField = new JTextField(20);
            container.add(answerField);


            JButton submitButton = new JButton("Submit");
            container.add(submitButton);
            submitButton.getRootPane().setDefaultButton(submitButton);
            submitButton.addActionListener(e -> {
                if (answerField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter an answer!");
                    return;
                }
                if (Integer.parseInt(answerField.getText()) == Q.getRand1()*Q.getRand2()) {
                    Question1.setCorrect(Question1.getCorrect()+1);
                    JOptionPane.showMessageDialog(null, "Correct!");
                    Q.setAnswer(1);
                } else {
                    Q.setAnswer(0);
                    JOptionPane.showMessageDialog(null, "Incorrect!");
                }
                container.remove(questionLabel);
                container.remove(answerField);
                container.remove(submitButton);
                currentQuestion++;
                question1ArrayList.add(Q);

                Q.setSecondsElapsed(secondsElapsed-elapsedSeconds(question1ArrayList));
                displayNextQuestion(container);
                container.revalidate();
                container.repaint();



            });
        } else {
            timer.stop();
            int secondsTaken = secondsElapsed;

            JOptionPane.showMessageDialog(null, "Game Over! Score: " + Q.getCorrect() +"/"+getNumQuestions()+ " \nTime taken: " + secondsTaken + " seconds");


            LoginScreen loginScreen = new LoginScreen(students, GameScreen1.getMinNumber(), GameScreen1.getMaxNumber(), GameScreen1.getNumQuestions());
            loginScreen.setVisible(true);
            dispose();
            int score=0;
            for (Question1 q : question1ArrayList) {
                score += q.getCorrect();
            }
            score = score/numQuestions;
            score = score*100;
            score = score/secondsElapsed;
            exercise.setScore(score);
            exercise.setQuestions(question1ArrayList);
            exercise.setSecondsElapsed(secondsElapsed);
            findChild(username).addExercise(exercise);
            if(findChild(username).getHighScore()<score){
                findChild(username).setHighScore(score);
            }
            Question1.setCorrect(0);

        }
    }

    private class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed + " seconds");
        }
    }

}
