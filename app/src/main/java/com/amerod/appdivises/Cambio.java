package com.amerod.appdivises;

/**
 * Created by Andres on 22/02/2018.
 */

public class Cambio {
    private float tipoCambio;
    private float comision;
    private String monedaOrigen;
    private String monedaDestino;

    public Cambio(String monedaOrigen, String monedaDestino){
        tipoCambio = 1.0f;
        comision = 0.0f;
        setMonedaOrigen(monedaOrigen);
        setMonedaDestino(monedaDestino);
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public void setTipoCambio(float tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public float getComision() {
        return comision;
    }

    public float getTipoCambio() {
        return tipoCambio;
    }

    public void invertirTipoCambio(){
        setTipoCambio(1.0f/getTipoCambio());
        String auxiliar = monedaOrigen;
        setMonedaOrigen(monedaDestino);
        setMonedaDestino(auxiliar);
    }

    public float cambiar(float origen){
        return (origen*tipoCambio) - origen*tipoCambio*comision;
    }

    public float cambiarSinComision(float origen) { return origen*tipoCambio; }

    public String getMonedaOrigen() { return monedaOrigen; }

    public void setMonedaOrigen(String monedaOrigen) { this.monedaOrigen = monedaOrigen; }

    public String getMonedaDestino() { return monedaDestino; }

    public void setMonedaDestino(String monedaDestino) { this.monedaDestino = monedaDestino; }
}
