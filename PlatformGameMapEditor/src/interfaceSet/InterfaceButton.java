package interfaceSet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import managers.textureManager;
import map.Constants;

public class InterfaceButton implements Constants
{
	private int x, y, h, w;
	private String text;
	
	public InterfaceButton(int x, int y, int w, int h, String name)
	{
		setX(x);
		setY(y);
		setW(w);
		setH(h);
		setText(name);
	}
	
	public void drawButton(Graphics2D g)
	{
		g.setColor(Color.lightGray);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 20));
		g.drawString(text, x+20, y+35);
		g.setFont(new Font("Arial", 0, 12));
		g.setStroke(new BasicStroke(3));
		g.drawRect(x, y, w, h);
	}
	public void drawButtonWithPhoto(Graphics2D g,	textureManager tm, int index)
	{
		g.setColor(Color.gray);
		g.fillRect(x, y, w, h);
		g.drawImage(tm.getTexture(index).texture, x, y, (int)BLOCK_SIZE, (int)BLOCK_SIZE, null);
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(3));
		g.drawRect(x, y, w, h);
	}
	public boolean isClicked(int X, int Y)
	{
		if(X>x&&X<(x+w)&&Y>y&&Y<(y+h))
			return true;
		else
			return false;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
}
