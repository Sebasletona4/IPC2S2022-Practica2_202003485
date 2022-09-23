package práctica2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake extends JPanel {
    Color colorsnake=Color.green;
    Color colormanzana=Color.red;
    int max, tam, cantidad, res;
    List<int[]> snake = new ArrayList<>();
    int[] manzana = new int[3];
    String direccion="Right";
    String direccionproxima="";

    Thread hilo;
    Caminante camino;
    
    
    public Snake(int max, int cantidad){
        this.max=max;
        this.cantidad=cantidad;
        this.tam=max/cantidad;
        this.res=max%cantidad;
        int[] a={cantidad/2-1,cantidad/2-1};
        snake.add(a);
        generarManzana();
        
        
        camino = new Caminante(this);
        hilo= new Thread(camino);
        hilo.start();
        
        
    }
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorsnake);
        
        for (int i=0; i<snake.size(); i++){
            pintor.fillRect(res/2+snake.get(i)[0]*tam,res/2+snake.get(i)[1]*tam, tam-1, tam-1);
        }

        for (int[] par:snake){
            pintor.fillRect(res/2+par[0]*tam,res/2+par[1]*tam, tam-1, tam-1);
        }
        pintor.setColor(colormanzana);
        pintor.fillRect(res/2+manzana[0]*tam,res/2+manzana[1]*tam, tam-1, tam-1);

    }
    
    public void move(){
        igualardir();
        int[] ultimo = snake.get(snake.size()-1);
        int agregarx=0;
        int agregary=0;
        switch(direccion){
            case "Right":agregarx=1;break;
            case "Left":agregarx=-1;break;
            case "Up":agregary=-1;break;
            case "Down":agregary=1;break;
        }
        int [] nuevo={ultimo[0]+agregarx, ultimo[1]+agregary};
        
        boolean existe=false;
        for (int i=0; i<snake.size(); i++){
            if(nuevo[0]==snake.get(i)[0]&&nuevo[1]==snake.get(i)[1]){
                existe=true;
                break;
            }
        if(existe){
            JOptionPane.showMessageDialog(this, "Jaja eso te pasa por crema");
        }else{
            if(nuevo[0]==manzana[0]&&nuevo[1]==manzana[1]){
                snake.add(nuevo);
                generarManzana();
            }else{
                snake.add(nuevo);
                snake.remove(0);
            }
        }
        }
        
    }
    
    public void generarManzana(){
        boolean existe=false;
        int a = (int) (Math.random()*10);
        int b = (int) (Math.random()*10);
        for (int[] par:snake){
            if(par[0]==a&&par[1]==b){
                existe=true;
                generarManzana();
                break;
            }
        }
        if(!existe){
            this.manzana[0]=a;
            this.manzana[1]=b;
        }
    }
    
    public void cambiodireccion(String dir){
        this.direccionproxima=dir;
    }
    
    public void igualardir(){
        this.direccion=this.direccionproxima;
    }
}