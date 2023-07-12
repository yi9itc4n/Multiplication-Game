import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdminScreen extends JFrame {
    private ArrayList<Child> students;
    private JTextField minNumberField;
    private JTextField maxNumberField;
    private JTextField numQuestionsField;
    public AdminScreen(ArrayList<Child> students) {

        this.students = students;

        // Set up the admin screen GUI
        setTitle("Admin Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(340, 482);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new FlowLayout());

        // Add components for the admin screen
        JLabel label = new JLabel("Student Usernames:");
        container.add(label);

        JTextArea usernamesTextArea = new JTextArea(10, 26);
        usernamesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(usernamesTextArea);
        container.add(scrollPane);

        for (Child username : students) {
            usernamesTextArea.append(username.toString());
        }

        JLabel addLabel = new JLabel("Add Child:");
        container.add(addLabel);

        JTextField addChildField = new JTextField(20);
        container.add(addChildField);

        JButton addChildButton = new JButton("Add");
        addChildButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int exists=0;
                String childName = addChildField.getText();
                for (Child child : students) {
                    if (child.getName().equals(childName)) {
                        exists=1;
                    }
                }
                if (!childName.isEmpty() && exists==0) {
                    Child child = new Child(childName);
                    students.add(child);
                    usernamesTextArea.append(childName + "\n");
                    addChildField.setText("");
                    JOptionPane.showMessageDialog(null, "Child added successfully!");
                }
                else if (childName.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter a child name!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Child already exists!");
                }
            }
        });
        container.add(addChildButton);


        JLabel minLabel = new JLabel("Minimum Number:");
        container.add(minLabel);

        minNumberField = new JTextField(10);
        container.add(minNumberField);

        JLabel maxLabel = new JLabel("Maximum Number:");
        container.add(maxLabel);

        maxNumberField = new JTextField(10);
        container.add(maxNumberField);

        JLabel numQuestionsLabel = new JLabel("Number of Questions:");
        container.add(numQuestionsLabel);

        numQuestionsField = new JTextField(10);
        container.add(numQuestionsField);

        JButton saveButton = new JButton("Save Settings");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                GameScreen1.setMinNumber(Integer.parseInt(minNumberField.getText()));
                GameScreen1.setMaxNumber(Integer.parseInt(maxNumberField.getText()));
                GameScreen1.setNumQuestions(Integer.parseInt(numQuestionsField.getText()));

                JOptionPane.showMessageDialog(null, "Settings saved successfully!");
            }
        });
        container.add(saveButton);


        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                LoginScreen loginScreen = new LoginScreen(students, GameScreen1.getMinNumber(), GameScreen1.getMaxNumber(), GameScreen1.getNumQuestions());
                loginScreen.setVisible(true);
            }
        });
        container.add(backToLoginButton);

        JLabel highScoresLabel = new JLabel("High Scores:");
        container.add(highScoresLabel);

        Collections.sort(students, new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Integer.compare(o2.getHighScore(), o1.getHighScore());
            }
        });

        JTextArea highScoresTextArea = new JTextArea(5, 20);
        highScoresTextArea.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(highScoresTextArea);
        container.add(scrollPane2);

for (Child child : students) {
            highScoresTextArea.append(child.getName() + ": " + child.getHighScore() + "\n");
        }
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(students);
    }


    }

