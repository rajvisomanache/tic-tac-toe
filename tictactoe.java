import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class tictactoe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel control_panel = new JPanel(); // New panel for reset button
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton resetButton = new JButton("Reset"); // Reset button
    boolean player1_turn;
    String player1Symbol = "😺";  // Player X
    String player2Symbol = "🐶";  // Player O

    tictactoe(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,850); // Increased height to fit reset button
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Segoe UI Emoji",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        String[] themes = { "Classic", "Dark Mode", "Neon" };
        JComboBox<String> themeSelector = new JComboBox<>(themes);
        themeSelector.setFocusable(false);
        themeSelector.addActionListener(e -> {
            String selected = (String) themeSelector.getSelectedItem();
            switch (selected) {
                case "Classic":
                    frame.getContentPane().setBackground(new Color(50, 50, 50));
                    textfield.setBackground(new Color(25, 25, 25));
                    textfield.setForeground(new Color(25, 255, 0));
                    break;
                case "Dark Mode":
                    frame.getContentPane().setBackground(Color.BLACK);
                    textfield.setBackground(Color.DARK_GRAY);
                    textfield.setForeground(Color.WHITE);
                    break;
                case "Neon":
                    frame.getContentPane().setBackground(new Color(0, 0, 50));
                    textfield.setBackground(new Color(0, 255, 255));
                    textfield.setForeground(new Color(255, 0, 255));
                    break;
            }
        });

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        title_panel.add(themeSelector, BorderLayout.EAST);
        title_panel.add(textfield, BorderLayout.CENTER);
        
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<9;i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Segoe UI Emoji",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        resetButton.setFont(new Font("Arial", Font.BOLD, 30));
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetGame());

        control_panel.setLayout(new FlowLayout());
        control_panel.setBackground(new Color(50, 50, 50));
        control_panel.add(resetButton);

        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.CENTER);
        frame.add(control_panel, BorderLayout.SOUTH); // Add reset button at the bottom

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++) {
            if(e.getSource()==buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText(player1Symbol);
                        player1_turn=false;
                        textfield.setText(player2Symbol + " turn ");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText(player2Symbol);
                        player1_turn=true;
                        textfield.setText(player1Symbol + " turn ");
                        check();
                    }
                }
            }			
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText(player1Symbol + " turn ");
        }
        else {
            player1_turn=false;
            textfield.setText(player2Symbol + " turn ");
        }
    }

    public void check() {
        // Your original win logic — unchanged
        if((buttons[0].getText().equals(player1Symbol)) && (buttons[1].getText().equals(player1Symbol)) && (buttons[2].getText().equals(player1Symbol))) xWins(0,1,2);
        if((buttons[3].getText().equals(player1Symbol)) && (buttons[4].getText().equals(player1Symbol)) && (buttons[5].getText().equals(player1Symbol))) xWins(3,4,5);
        if((buttons[6].getText().equals(player1Symbol)) && (buttons[7].getText().equals(player1Symbol)) && (buttons[8].getText().equals(player1Symbol))) xWins(6,7,8);
        if((buttons[0].getText().equals(player1Symbol)) && (buttons[3].getText().equals(player1Symbol)) && (buttons[6].getText().equals(player1Symbol))) xWins(0,3,6);
        if((buttons[1].getText().equals(player1Symbol)) && (buttons[4].getText().equals(player1Symbol)) && (buttons[7].getText().equals(player1Symbol))) xWins(1,4,7);
        if((buttons[2].getText().equals(player1Symbol)) && (buttons[5].getText().equals(player1Symbol)) && (buttons[8].getText().equals(player1Symbol))) xWins(2,5,8);
        if((buttons[0].getText().equals(player1Symbol)) && (buttons[4].getText().equals(player1Symbol)) && (buttons[8].getText().equals(player1Symbol))) xWins(0,4,8);
        if((buttons[2].getText().equals(player1Symbol)) && (buttons[4].getText().equals(player1Symbol)) && (buttons[6].getText().equals(player1Symbol))) xWins(2,4,6);

        if((buttons[0].getText().equals(player2Symbol)) && (buttons[1].getText().equals(player2Symbol)) && (buttons[2].getText().equals(player2Symbol))) oWins(0,1,2);
        if((buttons[3].getText().equals(player2Symbol)) && (buttons[4].getText().equals(player2Symbol)) && (buttons[5].getText().equals(player2Symbol))) oWins(3,4,5);
        if((buttons[6].getText().equals(player2Symbol)) && (buttons[7].getText().equals(player2Symbol)) && (buttons[8].getText().equals(player2Symbol))) oWins(6,7,8);
        if((buttons[0].getText().equals(player2Symbol)) && (buttons[3].getText().equals(player2Symbol)) && (buttons[6].getText().equals(player2Symbol))) oWins(0,3,6);
        if((buttons[1].getText().equals(player2Symbol)) && (buttons[4].getText().equals(player2Symbol)) && (buttons[7].getText().equals(player2Symbol))) oWins(1,4,7);
        if((buttons[2].getText().equals(player2Symbol)) && (buttons[5].getText().equals(player2Symbol)) && (buttons[8].getText().equals(player2Symbol))) oWins(2,5,8);
        if((buttons[0].getText().equals(player2Symbol)) && (buttons[4].getText().equals(player2Symbol)) && (buttons[8].getText().equals(player2Symbol))) oWins(0,4,8);
        if((buttons[2].getText().equals(player2Symbol)) && (buttons[4].getText().equals(player2Symbol)) && (buttons[6].getText().equals(player2Symbol))) oWins(2,4,6);
    }

    public void xWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++) buttons[i].setEnabled(false);
        textfield.setText(player1Symbol + " wins");
    }

    public void oWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++) buttons[i].setEnabled(false);
        textfield.setText(player2Symbol + " wins");
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }
        firstTurn();
    }

    public static void main(String[] args) {
        new tictactoe();
    }
}
