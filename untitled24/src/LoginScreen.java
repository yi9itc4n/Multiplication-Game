import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JButton loginButton;

    private ArrayList<Child> students;
    private AdminScreen adminScreen;
    private int minNumber;
    private int maxNumber;
    private int numQuestions;

    public LoginScreen() {
        students = new ArrayList<>();

        // Set up the login screen GUI
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 150);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new FlowLayout());

        usernameField = new JTextField(20);
        container.add(usernameField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        container.add(loginButton);
        loginButton.getRootPane().setDefaultButton(loginButton);
        }
    public LoginScreen(ArrayList<Child> students,int min,int max,int num_of_q) {
        this.students = students;
this.minNumber=min;
this.maxNumber=max;
this.numQuestions=num_of_q;

        // Set up the login screen GUI
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 150);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        usernameField = new JTextField(20);
        container.add(usernameField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        container.add(loginButton);
        loginButton.getRootPane().setDefaultButton(loginButton);
    }

    public LoginScreen(ArrayList<Child> students) {
        this.students = students;

        // Set up the login screen GUI
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(300, 150);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        usernameField = new JTextField(20);
        container.add(usernameField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        container.add(loginButton);
        loginButton.getRootPane().setDefaultButton(loginButton);
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loginButton) {
            String username = usernameField.getText();

            if (username.equals("admin")) {
                adminScreen = new AdminScreen(students);
                String filename = "file.ser";

                // Serialization
                try
                {
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    out.writeObject(adminScreen);

                    out.close();
                    file.close();

                    System.out.println("Object has been serialized");

                }

                catch(IOException ex)
                {
                    System.out.println("IOException is caught");
                }


                adminScreen.setVisible(true);
                setVisible(false);
            } else {
                boolean isStudent = checkStudentUsername(username);

                if (isStudent) {
                    JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
                    setVisible(false);
                    GameScreen1 gameScreen = new GameScreen1(minNumber,maxNumber,numQuestions,username, students);
                    gameScreen.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username!");
                }
            }
        }
    }



    public boolean checkStudentUsername(String username) {
        for (Child student : students) {
            if (student.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
