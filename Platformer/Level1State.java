package game.gamestate;

import game.entities.Player;
import game.objects.Block;
import game.physics.Collision;

import java.awt.Graphics;

public class Level1State extends GameState {
	
	private Player player;
	
	private Block[] b;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		player = new Player(30, 30);
		
		b = new Block[6];
		
		b[0] = new Block(30, 30);
		//b[1] = new Block(15, 10);
		//b[2] = new Block(20, 10);
		//b[3] = new Block(25, 10);
		//b[4] = new Block(30, 10);
		//b[5] = new Block(35, 10);
		//b[5] = new Block(40, 10);
	}

	public void tick() {
		
		for(int i = 0; i < b.length; i++) {
			b[i].tick();
		}
		player.tick(b);
	}

	public void draw(Graphics g) {
		player.draw(g);
	
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}
