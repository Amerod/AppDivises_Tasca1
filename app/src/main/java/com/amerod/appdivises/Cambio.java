package com.amerod.appdivises;

/**
 * Created by Andres on 22/02/2018.
 */

public class Cambio {
    private float tipoCambio;
    private float comision;

    public Cambio(){
        tipoCambio = 1.0f;
        comision = 0.1f;
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
    }

    public float cambiar(float origen){
        return (origen*tipoCambio) - origen*comision;
    }
}
