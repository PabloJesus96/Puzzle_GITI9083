package pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod;

/**
 * Created by Pablo Barrientos on 17/03/2018.
 */

public class PuzzleClasico implements PuzzleInt {
    protected int [] tablero;
    private  int disponible;//Casilla libre

    public PuzzleClasico(){
        tablero= new int[16];
        for (int i=0;i<16;i++){
            tablero[i]=i;
        }
        disponible= 15;
    }

    @Override
    public void moverPieza(int pos1, int pos2) {
        int tempo;
        if(verificarMov(pos1)){
            tempo = tablero[pos1];//La posición la guarde en la variable tempo
            tablero[pos1]=tablero[disponible];
            tablero[disponible]= tempo;
            disponible=pos1;//Valor de la casilla nueva disponible
        }
    }

    @Override
    public boolean verificarMov(int pos) {
        if((pos/4==disponible/4&&Math.abs(pos-disponible)==1)||(pos%4==disponible%4&&Math.abs(pos-disponible)==4)){
            return true;
        }else {
            return false;
        }
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

    public int getDisponible() {
        return disponible;
    }
}