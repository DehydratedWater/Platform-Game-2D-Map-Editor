package interfaceSet;

import java.awt.Color;
import java.awt.Graphics2D;

import managers.textureManager;
import map.Map;

public class InterfaceTab extends Map{

	public InterfaceTab(int x, int y, int maxIndex) {
		super(x, y);
		int index = 0;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 9; j++)
			{
			if(index>maxIndex-1)
				super.setField(0, j, i);
			else
				super.setField(index, j, i);
			index++;
			}
		}
	}
	
	
	public void drawInterface(Graphics2D g, textureManager tm, int sizeX, int sizeY)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, sizeX, sizeY);
		setX(100);
		setY(100);
		super.drawMap(g, tm, sizeX-100, sizeY-100);
		
	}
}
