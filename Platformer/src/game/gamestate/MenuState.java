package game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.game.GamePanel;

public class MenuState extends GameState {

	private String[] options =  {"Start", "Help", "Quit"};
	private int currentSelection = 0;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
	
	}

	public void tick() {
	
		
	}

	public void draw(Graphics g) {

		g.setColor(new Color(225, 50, 50));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for(int i = 0; i < options.length; i++){
			if(i == currentSelection) {
				g.setColor(Color.BLUE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			//g.drawLine(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT);
			g.setFont(new Font("Cambria", Font.BOLD, 65));
			g.drawString(options[i], GamePanel.WIDTH/2 - 70, 100 + i * 100);
		}
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN) {
			currentSelection++;
				if(currentSelection >= options.length) {
					currentSelection = 0;
				}
		} 
		else if(k == KeyEvent.VK_UP) {
			currentSelection--;
				if(currentSelection < 0) {
					currentSelection = options.length - 1;
				}
		}
		if(k == KeyEvent.VK_ENTER){
			if(currentSelection == 0) {
				gsm.states.push(new Level1State(gsm));
			}
			else if(currentSelection == 1) {
				System.out.println("A = Left, D = Right, SPCBAR = Jump");
			}
			else if(currentSelection == 2) {
				System.exit(0);
			}
		}
	}

	public void keyReleased(int k) {
		
	}
}
