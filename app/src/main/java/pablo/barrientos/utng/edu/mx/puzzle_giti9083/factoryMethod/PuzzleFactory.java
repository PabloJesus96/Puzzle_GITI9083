package pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod;

/**
 * Created by Pablo Barrientos on 17/03/2018.
 */

public class PuzzleFactory {
    static PuzzleInt puzzle;

    public static PuzzleInt create(int tipo){
        switch(tipo) {
            case 1:
                puzzle = new PuzzleLibre();
                break;
            case 2:
                puzzle = new PuzzleClasico();
                break;
            case 3:
                puzzle = new PuzzleCircular();
                break;
            //más opciones
        }
        return puzzle;
    }
}
