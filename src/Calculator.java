import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    //Window sizing
    int boardWidth = 360;
    int boardHeight = 540;
    
    //Color scheme
    Color customBlack = new Color(31, 31, 31);
    Color customLightGray = new Color(129, 129, 129);
    Color customDarkGray = new Color(68, 68, 68);
    Color customRed = new Color(170, 57, 61);

    //Buttons
    String[] btnValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    String num1 = "0";
    String num2 = null;
    String operator = null;
    

    //Window frame
    JFrame frame = new JFrame("Calculator");
    JLabel disJLabel = new JLabel();
    JPanel disJPanel = new JPanel();

    JPanel btnPanel = new JPanel();


    Calculator() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        disJLabel.setBackground(customBlack);
        disJLabel.setForeground(Color.white);
        disJLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        disJLabel.setHorizontalAlignment(JLabel.RIGHT);
        disJLabel.setText("0");
        disJLabel.setOpaque(true);

        disJPanel.setLayout(new BorderLayout());
        disJPanel.add(disJLabel);
        frame.add(disJPanel, BorderLayout.NORTH);

        btnPanel.setLayout(new GridLayout(5, 4));
        btnPanel.setBackground(customBlack);
        frame.add(btnPanel);
        

        for (int i = 0; i < btnValues.length; i++) {
            JButton btn = new JButton();
            String btnValue = btnValues[i];
            btn.setFont(new Font("Arial", Font.PLAIN, 30));
            btn.setText(btnValue);
            btn.setFocusable(false);
            btn.setBorder(new LineBorder(customBlack));

            if (Arrays.asList(topSymbols).contains(btnValue)) {
                btn.setBackground(customLightGray);
                btn.setForeground(customBlack);
            }

            else if (Arrays.asList(rightSymbols).contains(btnValue)) {
                btn.setBackground(customRed);
                btn.setForeground(Color.white);
            }

            else {
                btn.setBackground(customDarkGray);
                btn.setForeground(Color.white);
            }

            btnPanel.add(btn);

            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    String btnValue = btn.getText();

                    if (Arrays.asList(rightSymbols).contains(btnValue)) {
                        if (btnValue == "=") {
                            if (num1 != null) {
                                num2 = disJLabel.getText();
                                double noA = Double.parseDouble(num1);
                                double noB = Double.parseDouble(num2);

                                if (operator == "+") {
                                    disJLabel.setText(hidedZero(noA + noB)); 
                                }

                                if (operator == "-") {
                                    disJLabel.setText(hidedZero(noA - noB)); 
                                }

                                if (operator == "×") {
                                    disJLabel.setText(hidedZero(noA * noB)); 
                                }

                                if (operator == "÷") {
                                    disJLabel.setText(hidedZero(noA / noB)); 
                                }
                                clearAll();
                            }
                        }

                        else if ("÷×-+".contains(btnValue)) {
                            if (operator == null) {
                                num1 = disJLabel.getText();
                                disJLabel.setText("0");
                                num2 = "0";
                            }
                            operator = btnValue;
                        }
                    }
                        
                    else if (Arrays.asList(topSymbols).contains(btnValue)) {
                        if (btnValue == "AC") {
                            clearAll();
                            disJLabel.setText("0");
                        }
                        else if (btnValue == "+/-") {
                            double numDisplay = Double.parseDouble(disJLabel.getText());
                            numDisplay *= -1;
                            disJLabel.setText(hidedZero(numDisplay));
                        }
                        else if (btnValue == "%") {
                            double numDisplay = Double.parseDouble(disJLabel.getText());
                            numDisplay /= 100;
                            disJLabel.setText(hidedZero(numDisplay));
                        }
    
                    }
    
                    else {
                        if (btnValue == ".") {
                            if (!disJLabel.getText().contains(btnValue)) {
                                disJLabel.setText(disJLabel.getText() + btnValue);
                            }
                                
                        }
                        else if ("0123456789".contains(btnValue)) {
                            if (disJLabel.getText() == "0") {
                                disJLabel.setText(btnValue);
                            }
                            else {
                                disJLabel.setText(disJLabel.getText() + btnValue);
                            }
                            
                        }
                    }   
                }
            });
        }
    }

    void clearAll() {
        num1 = "0";
        num2 = null;
        operator = null; 
    }

    String hidedZero(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);

        }
        else {
            return Double.toString(numDisplay);
        }
    }


}
