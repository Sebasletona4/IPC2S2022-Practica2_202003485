package práctica2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caminante implements Runnable {
    int velocidad = 1000;
    Snake panel;
    boolean estado=true;
    public Caminante(Snake panel){
        this.panel=panel;
    }

    @Override
    public void run() {
        while(estado){
        panel.move();
        panel.repaint();
        try{
            Thread.sleep(velocidad);
        } catch (InterruptedException ex){
            Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void detener(){
            this.estado=false;
        }    

}
