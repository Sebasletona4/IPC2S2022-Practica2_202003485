package práctica2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Panel extends JPanel {
    Color colorfondo=Color.black;
    int max, tam, cantidad;
    
    public Panel(int max, int cantidad){
        this.max=max;
        this.cantidad=cantidad;
        this.tam=max/cantidad;
    }
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorfondo);
        for (int i=0; i<cantidad; i++){
            for (int j=0; j<cantidad; j++){
                pintor.drawRect(i*tam, j*tam, tam-1, tam-1);
            }
        }
    }
    
    
    
}
