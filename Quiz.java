import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {

    String[] questions = new String[]{
                               "What was Java originally called?",
                               "Which keyword is used to create a class in Java?",
                               "How do you insert COMMENTS in Java code?",
                               "Which data type is used to create a variable that should store text?",
                               "Which method can be used to find the length of a string?",
                               "Which operator is used to add together two values?",
                               "Which method can be used to return a string in upper case letters?",
                               "Which operator can be used to compare two values?",
                               "Array indexes start with:\n"
    };
    String[][] options= new String[][]{
            {"Apple",  "Latte", "Oak", "Coffe"},
            {"class",  "Myclass", "class()", "myclass[]"},
            {"/* this is a comment",  "// this is a comment", "-- this is a comment", "! this is a comment"},
            {"string",  "char", "String", "Text"},
            {"length()",  "len", "size[]", "genLength()"},
            {"a * b",  "a + b", "a ++ b", "a plus b"},
            {"toUpperCase()",  "UpperCase", "upper", "makeUpper"},
            {"==",  "<>", "><", "="},
            {"2",  "-1", "1", "0"}
    };

    char[] answers = new char[]{
            'C',
            'A',
            'B',
            'C',
            'A',
            'B',
            'A',
            'A',
            'D'
    };
    char guess;
    char answer;
    int index=0;
    Timer pause;
    int correctGuesses=0;
    int totalQuestions= questions.length;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerLabelA = new JLabel();
    JLabel answerLabelB = new JLabel();
    JLabel answerLabelC = new JLabel();
    JLabel answerLabelD = new JLabel();
    JLabel timeLabel= new JLabel();
    JLabel secondsLeft = new JLabel();
    JButton resetButton= new JButton();

    JTextField numberRight=  new JTextField();
    JTextField percentage= new JTextField();


    public Quiz()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 550);
        frame.setLocation(170, 100);
        frame.setResizable(false);
        frame.setLayout(null);
        init();
        nextQuestion();

        frame.setVisible(true);
    }

    void init()
    {    correctGuesses=0;
        index=0;
        frame.getContentPane().setBackground(Color.darkGray);
        textField.setBounds(0, 0, 1300, 50);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        textField.setFont( new Font("Ink Free", Font.BOLD, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(0, 50, 1300, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textArea.setFont( new Font("Ink Free", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0, 100, 200, 100);
        buttonA.setFont(new Font("Ink Free", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 200, 100);
        buttonB.setFont(new Font("Ink Free", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 200, 100);
        buttonC.setFont(new Font("Ink Free", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener( this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 200, 100);
        buttonD.setFont(new Font("Ink Free", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answerLabelA.setBounds(225, 100, 1050, 100);
        answerLabelA.setBackground(Color.BLACK);
        answerLabelA.setForeground(Color.green);
        answerLabelA.setFont( new Font("Ink Free", Font.BOLD, 35) );

        answerLabelB.setBounds(225, 200, 1050, 100);
        answerLabelB.setBackground(Color.BLACK);
        answerLabelB.setForeground(Color.green);
        answerLabelB.setFont( new Font("Ink Free", Font.BOLD, 35) );

        answerLabelC.setBounds(225, 300, 1050, 100);
        answerLabelC.setBackground(Color.BLACK);
        answerLabelC.setForeground(Color.green);
        answerLabelC.setFont( new Font("Ink Free", Font.BOLD, 35) );

        answerLabelD.setBounds(225, 400, 1050, 100);
        answerLabelD.setBackground(Color.BLACK);
        answerLabelD.setForeground(Color.green);
        answerLabelD.setFont( new Font("Ink Free", Font.BOLD, 35) );


/*

        timeLabel.setBounds(1200, 480, 100, 25);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.green);
        timeLabel.setFont( new Font("Ink Free", Font.PLAIN, 15));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("TIMER:");
*/

        numberRight.setBounds(350, 275, 600, 100);
        numberRight.setBackground(Color.black);
        numberRight.setForeground(Color.green);
        numberRight.setFont( new Font("Ink Free", Font.BOLD, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);

        frame.add(answerLabelA);
        frame.add(answerLabelB);
        frame.add(answerLabelC);
        frame.add(answerLabelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        correctGuesses=0;

    }
    public void nextQuestion(){
      if(index >= 2)
      {
          result();
      }
      else
      {
          textField.setText("Question " + (index + 1) +"/"+ (questions.length));
          textArea.setText(questions[index]);
          answerLabelA.setText( options[index][0] );
          answerLabelB.setText( options[index][1] );
          answerLabelC.setText( options[index][2] );
          answerLabelD.setText( options[index][3] );
      }
    }

    public void displayAnswer()
    {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        if(answers[index] != 'A')
            answerLabelA.setForeground(Color.red);
        if(answers[index] != 'B')
            answerLabelB.setForeground(Color.red);
        if(answers[index] != 'C')
            answerLabelC.setForeground(Color.red);
        if(answers[index] != 'D')
            answerLabelD.setForeground(Color.red);
         pause = new Timer(2000, new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() != resetButton && index < questions.length) {
                    answerLabelA.setForeground(Color.green);
                    answerLabelB.setForeground(Color.green);
                    answerLabelC.setForeground(Color.green);
                    answerLabelD.setForeground(Color.green);

                    answer = ' ';
                    buttonA.setEnabled(true);
                    buttonB.setEnabled(true);
                    buttonC.setEnabled(true);
                    buttonD.setEnabled(true);
                    System.out.println("timer "+index);
                    index++;
                    System.out.println("timer2 "+ index);
                    nextQuestion();
                }
            }
        });
        pause.setRepeats(false);
        pause.start();

    }

    public void result()
    {
 buttonA.setEnabled(false);
buttonB.setEnabled(false);
buttonC.setEnabled(false);
buttonD.setEnabled(false);
        resetButton.setVisible(true);
        numberRight.setVisible(true);
        frame.getContentPane().removeAll();
        frame.repaint();
frame.getContentPane().setBackground(Color.black);
textField.setText("Your score:");
textField.setBounds( 350, 200, 600, 100);
textField.setFont(new Font("Ink Free", Font.BOLD, 50) );
numberRight.setText("Correct: " + correctGuesses + " from: "+ totalQuestions);
frame.add(textField);
frame.add(numberRight);

        resetButton.setBounds(1170, 430, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        resetButton.addActionListener(this);
        resetButton.setText("Reset");
        resetButton.addActionListener(this);
        frame.add(resetButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        if(e.getSource() == buttonA && e.getSource() != resetButton )
        {
            answer = 'A';
            if(answer == answers[index])
            {
                correctGuesses++;
            }
            displayAnswer();
        }
        if(e.getSource() == buttonB && e.getSource() != resetButton )
        {
            answer = 'B';
            if(answer == answers[index])
            {
                correctGuesses++;
            }
            displayAnswer();
        }
        if(e.getSource() == buttonC && e.getSource() != resetButton )
        {
            answer = 'C';
            if(answer == answers[index])
            {
                correctGuesses++;
            }
            displayAnswer();
        }
        if(e.getSource() == buttonD && e.getSource() != resetButton )
        {
            answer = 'D';
            if(answer == answers[index])
            {
                correctGuesses++;
            }
            displayAnswer();
        }
        if(e.getSource() == resetButton)
        {
            System.out.println(index);
             init();
             //nextQuestion();
             resetButton.setVisible(false);
             numberRight.setVisible(false);
        }
    }
}
