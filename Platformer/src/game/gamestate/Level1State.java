package game.gamestate;

import game.entities.Player;
import game.mapping.Map;

import java.awt.Graphics;

public class Level1State extends GameState {
	
	private Player player;
	
	private Map map;
	
	//private Block[] b;
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		player = new Player(30, 30);
		map = new Map("/Maps/map1.map");
		
		xOffset = -200;
		yOffset = -400;
		//b = new Block[5];
		
		/*b[0] = new Block(500, 400);
		b[1] = new Block(450, 450);
		b[2] = new Block(400, 500);
		b[3] = new Block(350, 550);
		b[4] = new Block(550, 350);*/
	}

	public void tick() {
		player.tick(map.getBlocks());
		
	}

	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
	}

	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	public void keyReleased(int k) {
		player.keyReleased(k);
	}
}
