package game.entities;

import game.gamestate.GameState;
import game.objects.Block;
import game.physics.Collision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import main.game.GamePanel;

public class Player {

	
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	
	private double x, y;
	private int width, height;
	
	private double moveSpeed = 2.5;
	
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	private double maxFallSpeed = .7;
	private double currentFallSpeed = Math.abs(maxFallSpeed / 5) * 2;
	
	
	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Block[][] b) {
		
		int iX = (int)x;
		int iY = (int)y;
		
		if(!jumping && topCollision) {
			falling = true;
		}
		
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j].getID() != 0) {
					
			// right
			if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j]) 
					|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), b[i][j])) {
						right = false;
					}

			// left
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), b[i][j])
					|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset - 1), b[i][j])) {
						left = false;
					}
			
			// top
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i][j]) ||
					Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i][j])) {
						jumping = false;
						falling = true;
					}

			// bottom
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i][j]) ||
					Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 1,  iY + height + (int)GameState.yOffset + 1), b[i][j])) {
						y = b[i][j].getY() - (int)GameState.yOffset - height;
						falling = false;
						topCollision = true;
						break;
			} else {
				if(!topCollision && !jumping) {
					falling = true;
					}
				}
			}
			}
		}	
		
		if(right) {
			GameState.xOffset += moveSpeed;
		}
		
		if(left) {
			GameState.xOffset -= moveSpeed;
		}
		
		if(jumping) {
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= .11;
			
			if(currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		
		
		if(falling && currentFallSpeed == maxFallSpeed) {
			System.out.println("Game Over!");
		}
		if(falling) {
			GameState.yOffset += currentFallSpeed;
	
			currentFallSpeed += .5;
	
			if(currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += .1;
			}
			if(!falling) {
				currentFallSpeed = .1;
			}
		}
	}
		
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int)y, width, height);
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_D) right = true;
		if(k == KeyEvent.VK_A) left = true;
		if(k == KeyEvent.VK_SPACE && !jumping && !falling) jumping = true;
		if(k == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_D) right = false;
		if(k == KeyEvent.VK_A) left = false;
	}
	
}
