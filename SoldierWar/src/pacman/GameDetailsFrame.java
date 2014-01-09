package pacman;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameDetailsFrame extends JFrame{
	
	public GameDetailsFrame(){
		super();
		build();
	}
	
	public void build(){
		setLayout(null);
		
		
		
		
		setResizable(false);
		setTitle("Game Details");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 0, 400, 400);
		setVisible(true);

	}

}
