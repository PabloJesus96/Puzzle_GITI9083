package pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod;

/**
 * Created by Pablo Barrientos on 17/03/2018.
 */

public class PuzzleLibre implements PuzzleInt {
    protected int []tablero;

    public  PuzzleLibre(){//Constructor
        tablero= new int[16];
        for (int i=0;i<16;i++){
            tablero[i]=i;
        }
    }

    @Override
    public void moverPieza(int pos1, int pos2) {
        int tempo;
        tempo = tablero[pos1];
        tablero[pos1]=tablero[pos2];
        tablero[pos2]=tablero[tempo];
    }

    @Override
    public boolean verificarMov(int pos) {
        return true;
    }

    @Override
    public boolean yaGano() {
        for(int i=0;i<16;i++){
            if(tablero[i]!=i){
                return false;//que no termine el ciclo
            }
        }
        return true;
    }
}
