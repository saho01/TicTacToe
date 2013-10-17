// Seth Aho
// CSCI 1082 TicTacToe game
// AI is not that smart yet, will not block an apparent win
// nor will it go for the apparent win

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;

public class TicTacToe extends JFrame{
	public static int turn = 1;
	public static final int WIDTH = 450;
	public static final int HEIGHT = 350;
	boolean gameOn = true;
	private JLabel display;
	private JPanel gameBoard;
	private JButton [] gameButton = new JButton[9];
	private JButton resetButton;
	private JButton singlePlayerButton;
	private JButton twoPlayerButton;
	private JPanel gameChoice;
	private boolean gameOver = false;
	private boolean singlePlayerGame = true;
	
	public TicTacToe(){
		super("TicTacToe");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		
		//add JLabel
		display = new JLabel(" ");
		display.setHorizontalAlignment(JLabel.CENTER);
		add(display, BorderLayout.NORTH);
		//add gameBoard
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(3,3));
		
		//add gameButton
		for(int i = 0; i < 9; i++){
			gameButton[i] = new JButton();
			gameButton[i].addActionListener(new GameButtonListener());
			gameBoard.add(gameButton[i]);
		}
		
		add(gameBoard, BorderLayout.CENTER);
		
		gameChoice = new JPanel();
		gameChoice.setLayout(new FlowLayout());
		
		resetButton = new JButton("Reset");
		resetButton.setBackground(Color.RED);
		resetButton.addActionListener(new ResetButtonListener());
		gameChoice.add(resetButton);
		
		singlePlayerButton = new JButton("Single Player Game");
		singlePlayerButton.setBackground(Color.CYAN);
		singlePlayerButton.addActionListener(new SinglePlayerListener());
		gameChoice.add(singlePlayerButton);
		
		twoPlayerButton = new JButton("2 Player Game");
		twoPlayerButton.setBackground(Color.CYAN);
		twoPlayerButton.addActionListener(new SinglePlayerListener());
		gameChoice.add(twoPlayerButton);
		
		
		add(gameChoice, BorderLayout.SOUTH);	
	}
	
	private class GameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(gameOver){
				return;
			}
				
			JButton button = (JButton)(e.getSource());
			String buttonText = button.getText();
			
			if(!singlePlayerGame){
				
				if(buttonText.equals("O") || buttonText.equals("X")){
					return;
				}
			
				if((turn % 2) != 0){
					button.setText("X");
					//button.setEnabled(false);

					if(winChecker()){
						display.setText("X's Win");
						gameOver = true;
					}
						
				}
				else{
					button.setText("O");
					//button.setEnabled(false);

					if(winChecker()){
						display.setText("O's Win");
						gameOver = true;
					}
				}
				
				if(turn == 9 && (!gameOver)){
					display.setText("Tie Game");
					gameOver = true;
				}
			}
			
			else{
				boolean playerOne = true;
				boolean playerTwo = false;
				while(playerOne){
					if(buttonText.equals("O") || buttonText.equals("X")){
						return;
					}
					
					button.setText("X");
		
					if(winChecker()){
						display.setText("X's Win");
						gameOver = true;
						return;
					}	
					playerOne = false;
					playerTwo = true;
				}
				
				if(turn == 5 && (!gameOver)){
					display.setText("Tie Game");
					gameOver = true;
					return;
				}
				while(playerTwo){
					Random rand = new Random();
					
					int buttonRandom = rand.nextInt(9);
					
					while(gameButton[buttonRandom].getText().equals("X") || gameButton[buttonRandom].getText().equals("O")){
						buttonRandom = rand.nextInt(9);
					}
					
					gameButton[buttonRandom].setText("O");
					
					if(winChecker()){
						display.setText("O's Win");
						gameOver = true;
						return;
					}	
					playerTwo = false;
					playerOne = true;
				}
			}
		turn++;
		}
	}
	
	private class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			for(int i = 0; i < 9; i++){
				gameButton[i].setText("");
				//gameButton[i].setEnabled(true);
			}
			display.setText(" ");
			turn = 1;
			gameOver = false;	
		}
	}
	
	private class SinglePlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String game = e.getActionCommand();
			if(game.equals("Single Player Game")){
				singlePlayerGame = true;
				display.setText("Single Player Game");
			}
			else{
				singlePlayerGame = false;
				display.setText("Two Player Game");
			}
		}
	}	
		
	private boolean winChecker(){
		if(gameButton[0].getText().equals(gameButton[1].getText()) &&
				gameButton[1].getText().equals(gameButton[2].getText()) && gameButton[2].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[3].getText().equals(gameButton[4].getText()) &&
					gameButton[4].getText().equals(gameButton[5].getText()) && gameButton[5].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[6].getText().equals(gameButton[7].getText()) &&
					gameButton[7].getText().equals(gameButton[8].getText())&& gameButton[8].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[0].getText().equals(gameButton[3].getText()) &&
					gameButton[3].getText().equals(gameButton[6].getText())&& gameButton[6].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[1].getText().equals(gameButton[4].getText()) &&
					gameButton[4].getText().equals(gameButton[7].getText())&& gameButton[7].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[2].getText().equals(gameButton[5].getText()) &&
					gameButton[5].getText().equals(gameButton[8].getText())&& gameButton[8].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[0].getText().equals(gameButton[4].getText()) &&
					gameButton[4].getText().equals(gameButton[8].getText())&& gameButton[8].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else if(gameButton[2].getText().equals(gameButton[4].getText()) &&
					gameButton[4].getText().equals(gameButton[6].getText())&& gameButton[6].getText() != ""){
			//display.setText("O's Win");
			return true;
		}
		else
			return false;

	}

	
	public static void main(String[] args)
    {
        TicTacToe gui = new TicTacToe();
        gui.setVisible(true);
    }

}