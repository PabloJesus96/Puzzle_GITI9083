package pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod;

/**
 * Created by Pablo Barrientos on 17/03/2018.
 */

public class PuzzleCircular implements PuzzleInt{
    protected int[] tablero;

    public PuzzleCircular(){
        tablero= new int[16];
        for(int i=0;i<16;i++){
            tablero[i]= i;
        }
    }


    @Override
    public void moverPieza(int pos1, int dxy) {
        int x= pos1/4;  //número de fila
        int y= pos1%4;  //número de columna
        int tempo;
        switch (dxy){
            case 1://Arriba
                tempo= tablero[y];
                tablero[y]= tablero[y+4];
                tablero[y+4]= tablero[y+8];
                tablero[y+8]= tablero[y+12];
                tablero[y+12]= tempo;
                break;
            case 3://Abajo
                tempo= tablero[y+12];
                tablero[y+12]= tablero[y+8];
                tablero[y+8]= tablero[y+4];
                tablero[y+4]= tablero[y];
                tablero[y]= tempo;
                break;
            case 2://Derecha
                tempo= tablero[x*4+3];
                tablero[x*4+3]= tablero[x*4+2];
                tablero[x*4+2]= tablero[x*4+1];
                tablero[x*4+1]= tablero[x*4];
                tablero[x*4]= tempo;
                break;
            case 4://Izquierda
                tempo= tablero[x*4];
                tablero[x*4]= tablero[x*4+1];
                tablero[x*4+1]= tablero[x*4+2];
                tablero[x*4+2]= tablero[x*4+3];
                tablero[x*4+3]= tempo;
                break;
        }
    }

    @Override
    public boolean verificarMov(int pos) {
        return true;
    }

    @Override
    public boolean yaGano() {
        for(int i=0;i<16;i++){
            if(tablero[i]!=i){
                return false;
            }
        }
        return true;
    }
}