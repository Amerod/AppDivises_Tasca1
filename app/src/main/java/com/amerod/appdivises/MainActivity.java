package com.amerod.appdivises;

import android.content.DialogInterface;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Cambio cambiador;
    private float cantidadOrigen;
    private float cantidadFinal;
    private float comision;
    private float tipoCambio;

    private EditText txtCantidadOrigen;
    private EditText txtCantidadFinal;
    private EditText txtTipoCambio;
    private EditText txtComision;
    private Button btnInvertir;

    private TextView lblCambio;
    private TextView lblOrigen;
    private TextView lblFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cambiador = new Cambio("EURO","DOLAR");
        txtCantidadOrigen = (EditText) findViewById(R.id.txtCantidadOrigen);
        txtCantidadFinal = (EditText) findViewById(R.id.txtCantidadFinal);
        txtTipoCambio = (EditText) findViewById(R.id.txtTipoCambio);
        txtComision = (EditText) findViewById(R.id.txtComision);
        lblCambio = (TextView) findViewById(R.id.lblCambio);
        lblOrigen = (TextView) findViewById(R.id.lblOrigen);
        lblFinal = (TextView)findViewById(R.id.lblFinal);
        btnInvertir = (Button) findViewById(R.id.btnInvertir);
        btnInvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invertir();
            }
        });
        cantidadOrigen = 0.0f;
        cantidadFinal = 0.0f;
        comision = cambiador.getComision();
        tipoCambio = cambiador.getTipoCambio();
        lblOrigen.setText(cambiador.getMonedaOrigen());
        lblFinal.setText(cambiador.getMonedaDestino());
        txtCantidadOrigen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    if (txtCantidadOrigen.getText().toString().matches("")){
                        cantidadOrigen = 0.0f;
                    }else{
                        cantidadOrigen = Float.parseFloat(txtCantidadOrigen.getText().toString());
                    }
                    actualizar();
                    return true;
                }
                return false;
            }
        });
        txtTipoCambio.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    Log.i("BEBUG",txtTipoCambio.getText().toString());
                    if (txtTipoCambio.getText().toString().matches("")){
                        tipoCambio = 1.0f;
                    }else{
                        tipoCambio = Float.parseFloat(txtTipoCambio.getText().toString());
                    }
                    actualizar();
                    return true;
                }
                return false;
            }
        });
        txtComision.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    if (!txtComision.getText().toString().matches("")){
                        comision = Float.parseFloat(txtComision.getText().toString());
                    }else{
                        comision = 0.0f;
                    }
                    actualizar();
                    return true;
                }
                return false;
            }
        });
    }

    public void actualizar(){
        if (txtCantidadOrigen.getText().toString() == "") cantidadOrigen = 0.0f;
        if (txtCantidadFinal.getText().toString() == "") cantidadFinal = 0.0f;
        if (txtComision.getText().toString() == "") comision = cambiador.getComision();

        if (comision>0.99f){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Comisión no valida")
            .setMessage("Solo se permiten valores entre 0.0 y 0.99")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }else{
            cambiador.setTipoCambio(tipoCambio);
            cambiador.setComision(comision);
            cantidadFinal = cambiador.cambiar(cantidadOrigen);
            txtCantidadFinal.setText(String.valueOf(cantidadFinal));
            lblCambio.setText("1.0 "+cambiador.getMonedaOrigen()+" = "+cambiador.cambiarSinComision(1.0f)+" "+cambiador.getMonedaDestino());
        }
    }

    public void invertir(){
        cambiador.invertirTipoCambio();
        lblOrigen.setText(cambiador.getMonedaOrigen());
        lblFinal.setText(cambiador.getMonedaDestino());
        tipoCambio = cambiador.getTipoCambio();
        txtTipoCambio.setText(String.valueOf(tipoCambio));
        lblCambio.setText("1.0 "+cambiador.getMonedaOrigen()+" = "+cambiador.cambiarSinComision(1.0f)+" "+cambiador.getMonedaDestino());
        limpiar();
    }

    public void limpiar(){
        txtCantidadOrigen.setText("");
        txtCantidadFinal.setText("");
    }

}
