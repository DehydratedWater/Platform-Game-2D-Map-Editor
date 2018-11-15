package mapEditor;

public class PlatformGameMapEditor{

	public static void main(String[] args) 
	{
		MainWindow w = new MainWindow(1280, 720, 10, 10, "PlatformGameMapEditor");
		w.initializeData();
		while(w.isVisible())
		{
			w.refreshFrame();
		}
		w.clearUp();

	}
}
