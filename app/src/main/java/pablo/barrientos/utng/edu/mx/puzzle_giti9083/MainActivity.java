package pablo.barrientos.utng.edu.mx.puzzle_giti9083;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

import pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod.PuzzleCircular;
import pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod.PuzzleClasico;
import pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod.PuzzleFactory;
import pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod.PuzzleInt;
import pablo.barrientos.utng.edu.mx.puzzle_giti9083.factoryMethod.PuzzleLibre;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnTouchListener{
    private Button btnMezclar;
    private Button btnSalir;
    private Spinner cmbTipos;//tipo de rompecabezas
    private Spinner cmbOpciones;//Imagen seleccionada
    private int tipo=0;
    private int img;
    private PuzzleInt juego;
    private ImageButton[] imagenes;
    private int[][] pics;
    private int disponible=15;  //casilla disponible (tipo 2)
    private int pos1;   //primera pieza seleccionada (tipo 1)
    private int pos2;   //segunda pieza seleccionada
    private boolean banPar=true;    //Se pulsaron 2 piezas
    private int x1; //coordenadas donde inicia el touch (tipo3)
    private int y1;
    private int x2; //coordenadas donde finaliza el touch
    private int y2;
    private int dxy;   //dirección del desplazamiento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarControles();

        cmbTipos.setOnItemSelectedListener(this);
        cmbOpciones.setOnItemSelectedListener(this);
        btnSalir = (Button)findViewById(R.id.btn_salir);

        for(int i=0;i<16;i++){
            imagenes[i].setOnTouchListener(this);
        }

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        btnMezclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mezclar();
            }
        });

    }

    public void inicializarControles(){
        pics= new int[5][16];
        imagenes= new ImageButton[16];
        imagenes[0]= (ImageButton)findViewById(R.id.button1);
        imagenes[1]= (ImageButton)findViewById(R.id.button2);
        imagenes[2]= (ImageButton)findViewById(R.id.button3);
        imagenes[3]= (ImageButton)findViewById(R.id.button4);
        imagenes[4]= (ImageButton)findViewById(R.id.button5);
        imagenes[5]= (ImageButton)findViewById(R.id.button6);
        imagenes[6]= (ImageButton)findViewById(R.id.button7);
        imagenes[7]= (ImageButton)findViewById(R.id.button8);
        imagenes[8]= (ImageButton)findViewById(R.id.button9);
        imagenes[9]= (ImageButton)findViewById(R.id.button10);
        imagenes[10]= (ImageButton)findViewById(R.id.button11);
        imagenes[11]= (ImageButton)findViewById(R.id.button12);
        imagenes[12]= (ImageButton)findViewById(R.id.button13);
        imagenes[13]= (ImageButton)findViewById(R.id.button14);
        imagenes[14]= (ImageButton)findViewById(R.id.button15);
        imagenes[15]= (ImageButton)findViewById(R.id.button16);

        btnMezclar=(Button)findViewById(R.id.btn_mezclar);
        btnSalir=(Button)findViewById(R.id.btn_salir);
        cmbTipos=(Spinner)findViewById(R.id.spn_tipos);
        cmbOpciones=(Spinner)findViewById(R.id.spn_opciones);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.spn_tipos){
            tipo= (int)cmbTipos.getSelectedItemId();
            juego = PuzzleFactory.create(tipo);
           /*switch(tipo){
                case 1:
                    juego= new PuzzleLibre();
                    break;
                case 2:
                    juego= new PuzzleClasico();
                    break;
                case 3:
                    juego= new PuzzleCircular();
                    break;
                //más opciones
            }*/
        }else if(adapterView.getId()==R.id.spn_opciones){
            img= (int)cmbOpciones.getSelectedItemId();
            cargarImagenes(img);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void cargarImagenes(int img){
        switch(img){
            case 1://Números
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.n1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.n2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.n3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.n4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.n5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.n6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.n7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.n8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.n9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.n10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.n11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.n12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.n13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.n14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.n15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.n16));
                break;
            case 2://Android
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.a1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.a2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.a3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.a4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.a5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.a6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.a7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.a8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.a9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.a10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.a11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.a12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.a13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.a14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.a15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.a16));
                break;
            case 3://Mario
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.m1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.m2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.m3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.m4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.m5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.m6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.m7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.m8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.m9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.m10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.m11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.m12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.m13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.m14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.m15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.m16));
                break;
            case 4://Payaso
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.p1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.p2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.p3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.p4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.p5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.p6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.p7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.p8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.p9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.p10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.p11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.p12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.p13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.p14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.p15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.p16));
                break;
            case 5://tasa
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.pr16));
                break;
            case 6://Goku
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.g1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.g2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.g3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.g4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.g5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.g6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.g7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.g8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.g9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.g10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.g11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.g12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.g13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.g14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.g15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.g16));
                break;
            case 7://Danny
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.d1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.d2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.d3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.d4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.d5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.d6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.d7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.d8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.d9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.d10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.d11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.d12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.d13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.d14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.d15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.d16));
                break;
            case 8://programming
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.l1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.l2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.l3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.l4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.l5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.l6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.l7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.l8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.l9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.l10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.l11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.l12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.l13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.l14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.l15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.l16));
                break;
        }
        if(tipo==2){//tipo clásico
            imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.vacia));
        }
    }

    private void mezclar(){
        int x;
        int y;
        Random r= new Random();

        for(int i=1;i<100;i++){
            x= r.nextInt(16);
            y= r.nextInt(16);
            if(juego.verificarMov(x)){
                switch(tipo) {
                    case 1:
                        juego.moverPieza(x, y);
                        moverImagenes(x, y);
                        break;
                    case 2:
                        juego.moverPieza(x, disponible);
                        moverImagenes(x, disponible);
                        break;
                    case 3:
                        juego.moverPieza(x, y % 4);
                        moverImagenes(x, y % 4);
                        break;
                }
            }
        }
    }

    public void moverImagenes(int x, int y){
        ImageButton tempo= new ImageButton(this);//<--
        switch(tipo){
            case 2:
                disponible=x;
            case 1:
                tempo.setBackgroundDrawable(imagenes[x].getBackground());
                imagenes[x].setBackgroundDrawable(imagenes[y].getBackground());
                imagenes[y].setBackgroundDrawable(tempo.getBackground());
                break;
            case 3:
                int i=x/4;  //Fila
                int j=x%4;  //Columna
                switch(y){
                    case 1://arriba
                        tempo.setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 3://abajo
                        tempo.setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 2://derecha
                        tempo.setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 4://derecha
                        tempo.setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(tempo.getBackground());
                        break;
                }

                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int dx=0;//desplazamiento horizontal
        int dy=0;//desplazamiento vertical
        switch (view.getId()){
            case R.id.button1: pos1=0; break;
            case R.id.button2: pos1=1; break;
            case R.id.button3: pos1=2; break;
            case R.id.button4: pos1=3; break;
            case R.id.button5: pos1=4; break;
            case R.id.button6: pos1=5; break;
            case R.id.button7: pos1=6; break;
            case R.id.button8: pos1=7; break;
            case R.id.button9: pos1=8; break;
            case R.id.button10: pos1=9; break;
            case R.id.button11: pos1=10; break;
            case R.id.button12: pos1=11; break;
            case R.id.button13: pos1=12; break;
            case R.id.button14: pos1=13; break;
            case R.id.button15: pos1=14; break;
            case R.id.button16: pos1=15; break;
        }
        switch(tipo){
            case 1://Libre
                banPar= !banPar;
                if(!banPar){//si es falsa
                    pos2= pos1;
                }else{//se seleccionaron 2 piezas
                    juego.moverPieza(pos1,pos2);
                    moverImagenes(pos1,pos2);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),
                                "Ganaste!!",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 2://Clásico
                if(juego.verificarMov(pos1)){
                    juego.moverPieza(pos1,disponible);
                    moverImagenes(pos1,disponible);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),
                                "Ganaste!!",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 3:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    x1=(int)motionEvent.getX();//Obtiendo coordenadas
                    y1=(int)motionEvent.getY();//del inicio del touch
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    x2=(int)motionEvent.getX();//Obtiendo coordenadas
                    y2=(int)motionEvent.getY();//del fin del touch
                    dx= Math.abs(x2-x1);
                    dy= Math.abs(y2-y1);
                    if(dx>dy){//Mov. horizontal
                        if(x2>x1){//Hacia la derecha
                            dxy=2;
                        }else{//Hacia la izquierda
                            dxy=4;
                        }
                    }else{//Mov. vertical
                        if(y2>y1){//Hacia abajo
                            dxy=3;
                        }else{//Hacia arriba
                            dxy=1;
                        }
                    }
                    juego.moverPieza(pos1,dxy);
                    moverImagenes(pos1,dxy);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),
                                "Ganaste!!",Toast.LENGTH_LONG).show();
                    }
                }
        }
        return false;
    }
}
