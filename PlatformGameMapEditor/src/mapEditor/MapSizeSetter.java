package mapEditor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MapSizeSetter 
{
	public int[] startGetter()
	{
		 JTextField x = new JTextField(3);
         JTextField y = new JTextField(3);
         x.setText("100");
         y.setText("100");
         JPanel message = new JPanel();
         message.add(x);
         message.add(new JLabel(" x "));
         message.add(y);

         int result = JOptionPane.showConfirmDialog(null, message, "Enter map size x y", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.OK_OPTION) {
             String sX = x.getText();
             String sY = y.getText();
             

             try {
                 int X = Integer.parseInt(sX);
                 int Y = Integer.parseInt(sY);
                 JOptionPane.showMessageDialog(null, "You enetered " + X + " x " + Y);
                 return new int[]{X,Y};
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "The values you entered are invalid");
             }
         }
         return null;
	}
}
