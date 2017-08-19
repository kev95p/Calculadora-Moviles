package com.example.kevin.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.Principal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvEntrada;
    TextView tvSalida;
    ArrayList<Button> lstNumeros;
    ArrayList<Button> lstOperaciones;
    Button btnDel,btnIgual;
    boolean operacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEntrada = (TextView) findViewById(R.id.tvEntrada);
        tvSalida = (TextView) findViewById(R.id.tvResultado);
        btnDel = (Button) findViewById(R.id.btnDEL);
        btnIgual = (Button) findViewById(R.id.btnIgual);


        lstNumeros = new ArrayList<>();
        lstNumeros.add( (Button) findViewById(R.id.btn9));
        lstNumeros.add( (Button) findViewById(R.id.btn8));
        lstNumeros.add( (Button) findViewById(R.id.btn7));
        lstNumeros.add( (Button) findViewById(R.id.btn6));
        lstNumeros.add( (Button) findViewById(R.id.btn5));
        lstNumeros.add( (Button) findViewById(R.id.btn4));
        lstNumeros.add( (Button) findViewById(R.id.btn3));
        lstNumeros.add( (Button) findViewById(R.id.btn2));
        lstNumeros.add( (Button) findViewById(R.id.btn1));
        lstNumeros.add( (Button) findViewById(R.id.btn0));

        lstOperaciones = new ArrayList<>();
        lstOperaciones.add((Button) findViewById(R.id.btnSUM));
        lstOperaciones.add((Button) findViewById(R.id.btnRES));
        lstOperaciones.add((Button) findViewById(R.id.btnMUL));
        lstOperaciones.add((Button) findViewById(R.id.btnDIV));

        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                tvEntrada.setText("");
                tvSalida.setText("");
                operacion = true;
                return true;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarUltimo();
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSalida.setText(Parser.evaluar(tvEntrada.getText().toString()));
            }
        });
        initNumeros();
        initOperaciones();
    }

    private void initNumeros(){
        for (final Button btn:lstNumeros){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cada vez que pulse un numero lo concatena al texto
                    tvEntrada.setText(tvEntrada.getText().toString() + btn.getText().toString());
                    operacion=false;
                }
            });
        }
    }

    private void initOperaciones(){

        for (final Button btn:lstOperaciones){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!operacion){// si no hay operacion matematica pulsada con anterioridad
                        //agrega la operacion al texto
                        tvEntrada.setText(tvEntrada.getText().toString() + btn.getText().toString());
                        operacion=true;// y entonces la operacion matematica ya esta pulsada
                    }
                }
            });
        }
    }

    private  void EliminarUltimo(){
        String str = tvEntrada.getText().toString();//obtento el texto del TextView
        if (str != null && str.length() > 0 ) {// verifico que no sea nulo y que tenga mas de 1 caracter
            str = str.substring(0, str.length() - 1); // saco una subcadena del texto total - 1 (esto elimina el ultimo)
            if(str.length()>0)//si la longitud ya cortada es mayor a cero
                operacion = esOperacion((str.substring(str.length()-1,str.length())));//evaluo si es operacion
            else//si es menor a cero, es decir esta vacio
                operacion = true;//guardo como pulsado para evitar poner op matematicas al inicio
        }
        tvEntrada.setText(str);
    }

    private boolean esOperacion(String txt){//evalua si es operacion matematica
        for (final Button btn:lstOperaciones){//revizo en la lista de botones
            if(btn.getText().equals(txt)){//comparo si el texto que envio es igual al texto de los botones '+' == '+' -> true
                return true;
            }
        }
        return false;//si no hay ningun texto que coincida entonces no es op matematica
    }

}
