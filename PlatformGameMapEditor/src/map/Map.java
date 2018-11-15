package map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import managers.textureManager;

public class Map implements Constants
{
	private int[][] mapTab;
	private int SizeX, SizeY;
	private float X = 0, Y = 0, scaleX = 1, scaleY = 1;
	
	public Map(int x, int y)
	{
		mapTab = new int[x][y];
		SizeX = x;
		SizeY = y;
	}
	public void drawMap(Graphics2D g, textureManager tm, int sizeX, int sizeY)
	{
		int sX = (int) -(X/(BLOCK_SIZE*scaleX))-1;
		int sY = (int) -(Y/(BLOCK_SIZE*scaleY))-1;
		if(sX<0)
			sX = 0;
		if(sY<0)
			sY = 0;
		if(sX>SizeX)
			sX = SizeX-1;
		if(sY>SizeY)
			sY = SizeY-1;
		int eX = (int)(sizeX/(BLOCK_SIZE*scaleX))+sX+3;
		int eY = (int)(sizeY/(BLOCK_SIZE*scaleY))+sY+3;
		if(eX>SizeX)
			eX=SizeX;
		if(eY>SizeY)
			eY=SizeY;
		//System.out.println(sX+" "+sY+" - "+eX+" "+eY);
		for(int i = sX; i < eX; i++)
		{
			for(int j = sY; j < eY; j++)
			{
				g.drawImage(tm.getTexture(getField(i, j)).texture, (int)(i*BLOCK_SIZE*scaleX+X), (int)(j*BLOCK_SIZE*scaleY+Y), (int)(BLOCK_SIZE*scaleX), (int)(BLOCK_SIZE*scaleY), null);
			}
		}
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.gray);
		for(int i = 0; i < SizeX; i++)
		{
			for(int j = 0; j < SizeY; j++)
			{
				g.drawRect((int)(i*BLOCK_SIZE*scaleX+X), (int)(j*BLOCK_SIZE*scaleY+Y), (int)(BLOCK_SIZE*scaleX), (int)(BLOCK_SIZE*scaleY));
			}
		}
	}
	
	public int[] getClicked(int x, int y)
	{
		float locX = x - X;
		float locY = y - Y;
		
		locX /= (BLOCK_SIZE*scaleX);
		locY /= (BLOCK_SIZE*scaleY);
		
		if(locX<0)
			return null;
		if(locY<0)
			return null;
		if(locX>SizeX)
			return null;
		if(locY>SizeY)
			return null;
		return new int[]{(int)locX, (int)locY};
	}
	
	public void setClicked(Graphics2D g, int x, int y)
	{
		float locX = x - X;
		float locY = y - Y;
		
		locX /= (BLOCK_SIZE*scaleX);
		locY /= (BLOCK_SIZE*scaleY);
		
		if(locX<0)
			return;
		if(locY<0)
			return;
		if(locX>SizeX)
			return;
		if(locY>SizeY)
			return;
		//System.out.println((int)locX+" "+(int)locY);
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int)((int)locX*BLOCK_SIZE*scaleX+X), (int)((int)locY*BLOCK_SIZE*scaleY+Y), (int)(BLOCK_SIZE*scaleX), (int)(BLOCK_SIZE*scaleY));
		g.setStroke(new BasicStroke(1));
	}
	
	public int getField(int x, int y)
	{
		return mapTab[x][y];
	}
	public void setField(int value, int x, int y)
	{
		mapTab[x][y] = value;
	}
	public void moveMap(float x, float y)
	{
		X+=x*scaleX;
		Y+=y*scaleY;
	}
	public float getScaleX() {
		return scaleX;
	}
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}
	public float getScaleY() {
		return scaleY;
	}
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	public float getX() {
		return X;
	}
	public void setX(float x) {
		X = x;
	}
	public float getY() {
		return Y;
	}
	public void setY(float y) {
		Y = y;
	}
	public void scaleMap(float sx, float sy)
	{
		scaleX+=sx;
		scaleY+=sy;
		if(scaleX<0.1f)
			scaleX = 0.1f;
		if(scaleY<0.1f)
			scaleY = 0.1f;
	}
	public int getSizeY() {
		return SizeY;
	}
	public void setSizeY(int sizeY) {
		SizeY = sizeY;
	}
	public int getSizeX() {
		return SizeX;
	}
	public void setSizeX(int sizeX) {
		SizeX = sizeX;
	}
	public int[][] getMapTab() {
		return mapTab;
	}
	public void setMapTab(int[][] mapTab) {
		this.mapTab = mapTab;
	}
}
