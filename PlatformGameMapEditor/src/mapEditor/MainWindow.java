package mapEditor;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import interfaceSet.InterfaceButton;
import interfaceSet.InterfaceTab;
import managers.ControlsManager;
import managers.textureManager;
import map.Constants;
import map.Map;
import openSave.OpenMap;
import openSave.SaveMap;


public class MainWindow extends Window implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, Constants{

	private static final long serialVersionUID = 1L;
	private textureManager texManagerMainMap, texManagerSecondMap;
	private ControlsManager cm;
	private int sizeX, sizeY;
	private Map map;
	private Map map2;
	private InterfaceButton button, choosen, save, open, switchMode;
	private int choosenIndex = 0;
	private boolean showInterface = false, mode = false;
	private InterfaceTab interfaceSet, interfaceSet2;
	private int choosenIndex2;
	private int spawnY;
	private int spawnX;
	
	public MainWindow(int resx, int resy, int locx, int locy, String title) {
		super(resx, resy, locx, locy, title);
		Window.antialiasing = true;
		Window.clearLastFrame = true;
		Window.lossyScale = false;
		Window.showFPS = true;
		Window.FPSrate = 60;
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		this.sizeX = resx;
		this.sizeY = resy;
	}

	
	
	
	
	public void initializeData()
	{
		cm = new ControlsManager();
		texManagerMainMap = new textureManager();
		texManagerSecondMap = new textureManager();
		MapSizeSetter m = new MapSizeSetter();
		int[] xy = m.startGetter();
		map = new Map(xy[0], xy[1]);
		map2 = new Map(xy[0], xy[1]);
		inicializeMapTextures(texManagerMainMap);
		inicializeSecondMapTextures(texManagerSecondMap);
		button = new InterfaceButton(sizeX-280, 5, 140, 60, "Set block");
		choosen = new InterfaceButton(sizeX-100, 5, 64, 64, "");
		save = new InterfaceButton(5, sizeY-100, 80, 50, "Save");
		open = new InterfaceButton(100, sizeY-100, 80, 50, "Open");
		switchMode  = new InterfaceButton(210, sizeY-100, 80, 50, "Map");
		interfaceSet = new InterfaceTab(10, 5, texManagerMainMap.getListSize());
		interfaceSet2 = new InterfaceTab(10, 5, texManagerSecondMap.getListSize());
	}
	
	
	public void inicializeMapTextures(textureManager tm)
	{
		System.out.println("Dodawanie tekstur mapy");
		tm.addNewTextureAndGetTextureID("res/air.png", "air");
		tm.addNewTextureAndGetTextureID("res/airIsland.png", "airIsland");
		tm.addNewTextureAndGetTextureID("res/airIslandLeft.png", "airIslandLeft");
		tm.addNewTextureAndGetTextureID("res/airIslandRight.png", "airIslandRight");
		tm.addNewTextureAndGetTextureID("res/dirt.png", "dirt");
		tm.addNewTextureAndGetTextureID("res/dirtWithBottom.png", "dirtWithBottom");
		tm.addNewTextureAndGetTextureID("res/grassRightUpCornerWithBottom.png", "grassRightUpCornerWithBottom");
		tm.addNewTextureAndGetTextureID("res/grassLeftUpCornerWithBottom.png", "grassLeftUpCornerWithBottom");
		tm.addNewTextureAndGetTextureID("res/grassLeftUpCorner.png", "grassLeftUpCorner");
		tm.addNewTextureAndGetTextureID("res/grassLeft.png", "grassLeft");
		tm.addNewTextureAndGetTextureID("res/grassLeftWithBottom.png", "grassLeftWithBottom");
		tm.addNewTextureAndGetTextureID("res/grassLeftDownCorner.png", "grassLeftDownCorner");
		tm.addNewTextureAndGetTextureID("res/grassUp.png", "grassUp");
		tm.addNewTextureAndGetTextureID("res/grassRightDownCorner.png", "grassRightDownCorner");
		tm.addNewTextureAndGetTextureID("res/grassRight.png", "grassRight");
		tm.addNewTextureAndGetTextureID("res/grassRightWithBottom.png", "grassRightWithBottom");
		tm.addNewTextureAndGetTextureID("res/grassRightUpCorner.png", "grassRightUpCorner");
		tm.addNewTextureAndGetTextureID("res/layerCenter.png", "layerCenter");
		tm.addNewTextureAndGetTextureID("res/layerUp.png", "layerUp");
		tm.addNewTextureAndGetTextureID("res/layerDown.png", "layerDown");
		tm.addNewTextureAndGetTextureID("res/kolce.png", "kolce");
	}
	
	public void inicializeSecondMapTextures(textureManager tm)
	{
		System.out.println("Dodawanie tekstur przedmiotów");
		tm.addNewTextureAndGetTextureID("res/air.png", "air");
		tm.addNewTextureAndGetTextureID("res/coin.png", "coin");
	}
	public void refreshFrame()
	{
		super.refresh(texManagerMainMap);
		this.sizeX = getWidth();
		this.sizeY = getHeight();
	}
	
	
	
	@Override
	public void useControls(Graphics2D g, int delta) 
	{
		super.useControls(g, delta);
		//System.out.println(cm.mLEFT+" "+cm.mRIGHT);
		if(cm.W)
		{
			map.moveMap(0, MOVE_MAP_SPEED);
			map2.moveMap(0, MOVE_MAP_SPEED);
		}
		if(cm.S)
		{
			map.moveMap(0, -MOVE_MAP_SPEED);
			map2.moveMap(0, -MOVE_MAP_SPEED);
		}
		if(cm.A)
		{
			map.moveMap(MOVE_MAP_SPEED, 0);
			map2.moveMap(MOVE_MAP_SPEED, 0);
		}
		if(cm.D)
		{
			map.moveMap(-MOVE_MAP_SPEED, 0);
			map2.moveMap(-MOVE_MAP_SPEED, 0);
		}
		
		if(cm.R)
		{
			map.setScaleX(1);
			map.setScaleY(1);
			map.setX(0);
			map.setY(0);
			map2.setScaleX(1);
			map2.setScaleY(1);
			map2.setX(0);
			map2.setY(0);
		}
			
		
		if(cm.mouseWeel>0)
		{
			map.scaleMap(SCALE_MAP_SPEED, SCALE_MAP_SPEED);
			map2.scaleMap(SCALE_MAP_SPEED, SCALE_MAP_SPEED);
		}
		else if(cm.mouseWeel<0)
		{
			map.scaleMap(-SCALE_MAP_SPEED, -SCALE_MAP_SPEED);
			map2.scaleMap(-SCALE_MAP_SPEED, -SCALE_MAP_SPEED);
		}
		
		boolean clicked = false;
		if(cm.mLEFT)
		{
			if(save.isClicked(cm.mX, cm.mY))
			{
				SaveMap.saveMap(map, map2, map.getSizeX(), map.getSizeY(), spawnX, spawnY);
				clicked = true;
			}
			if(open.isClicked(cm.mX, cm.mY))
			{
				int[] spawn = OpenMap.openMap(map, map2);
				if(spawn != null)
				{
					spawnX = spawn[0];
					spawnY = spawn[1];
				}
				clicked = true;
			}
			
		}
		
		if(cm.cCENTRE)
		{
			int[] spawn = map.getClicked(cm.mX, cm.mY);
			if(spawn!=null)
			{
				System.out.println("Ustnowiono spawn w "+spawn[0]+" "+spawn[1]);
				spawnX = spawn[0];
				spawnY = spawn[1];
			}
		}
		if(cm.cLEFT&&switchMode.isClicked(cm.mX, cm.mY))
		{
				clicked = true;
				//System.out.println("Zmiana trybu "+mode);
				mode = !mode;
				if(!mode)
					switchMode.setText("Map");
				else
					switchMode.setText("Item");
		}

		if(!clicked&&!switchMode.isClicked(cm.mX, cm.mY))
		{
		if(cm.mLEFT&&!showInterface)
		{
			showInterface = button.isClicked(cm.mX, cm.mY);
		}
		
		if(cm.mLEFT&&!showInterface)
		{
			if(!mode)
			{
				int[] choose = map.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				map.setField(choosenIndex, choose[0], choose[1]);
			}
			else
			{
				int[] choose = map2.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				map2.setField(choosenIndex2, choose[0], choose[1]);
			}
		}
		
		if(cm.mRIGHT&&!showInterface)
		{
			//System.out.println("prawy");
			if(!mode)
			{
				int[] choose = map.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				map.setField(0, choose[0], choose[1]);
			}
			else
			{
				int[] choose = map2.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				map2.setField(0, choose[0], choose[1]);
			}
		}
		
		if(cm.cLEFT&&showInterface)
		{
			if(!mode)
			{
				int[] choose = interfaceSet.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				{
					choosenIndex = interfaceSet.getField(choose[0], choose[1]);
					showInterface = false;
				}
			}
			else
			{
				int[] choose = interfaceSet2.getClicked(cm.mX, cm.mY);
				if(choose!=null)
				{
				choosenIndex2 = interfaceSet2.getField(choose[0], choose[1]);
				showInterface = false;
				}
			}
		}
		}
		cm.reset();
	}
	





	@Override
	public void drawFrame(Graphics2D g, int delta, textureManager texManager1) 
	{
		super.drawFrame(g,delta,texManager1);
		if(!showInterface)
		{
		map.drawMap(g, texManager1, sizeX, sizeY);
		map2.drawMap(g, texManagerSecondMap, sizeX, sizeY);
		map.setClicked(g, cm.mX, cm.mY);
		button.drawButton(g);
		if(!mode)
		{
			choosen.drawButtonWithPhoto(g, texManager1, choosenIndex);
		}
		else
		{
			choosen.drawButtonWithPhoto(g, texManagerSecondMap, choosenIndex2);
		}
		}
		else if(showInterface)
		{
			if(!mode)
			{
				interfaceSet.drawInterface(g, texManager1, sizeX, sizeY);
				interfaceSet.setClicked(g, cm.mX, cm.mY);
			}
			else
			{
				interfaceSet2.drawInterface(g, texManagerSecondMap, sizeX, sizeY);
				interfaceSet2.setClicked(g, cm.mX, cm.mY);
			}
		}
		save.drawButton(g);
		open.drawButton(g);
		switchMode.drawButton(g);
	}
	
	
	@Override
	public void checkCollisions(Graphics2D g, int delta) 
	{
		super.checkCollisions(g, delta);

	}
	public void clearUp() 
	{
		
		
	}


	public void keyPressed(KeyEvent e) {
		cm.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		cm.keyReleased(e);
	}
	public void keyTyped(KeyEvent e) {
		cm.keyTyped(e);
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		cm.mouseWheelMoved(arg0);
	}
	public void mouseDragged(MouseEvent arg0) {
		cm.mouseDragged(arg0);
	}
	public void mouseMoved(MouseEvent arg0) {
		cm.mouseMoved(arg0);
	}
	public void mouseClicked(MouseEvent arg0) {
		cm.mouseClicked(arg0);
	}
	public void mouseEntered(MouseEvent arg0) {
		cm.mouseEntered(arg0);
	}
	public void mouseExited(MouseEvent arg0) {
		cm.mouseExited(arg0);
	}
	public void mousePressed(MouseEvent arg0) {
		cm.mousePressed(arg0);
	}
	public void mouseReleased(MouseEvent arg0) {
		cm.mouseReleased(arg0);
	}
}
