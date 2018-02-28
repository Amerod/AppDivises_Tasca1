package com.amerod.appdivises;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Cambio cambiador;

    private String monedaInicial = "Euro";
    private String monedaFinal = "Dolar";
    private float cantidadInicial = 20.0f;
    private float tipoCambio = 1.5f;
    private float comision = 0.1f;

    private float cantidadFinal = 27.0f;


    @Before
    public void createCambiador(){
        cambiador = new Cambio(monedaInicial, monedaFinal);
        cambiador.setTipoCambio(tipoCambio);
        cambiador.setComision(comision);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.amerod.appdivises", appContext.getPackageName());
    }

    @Test
    public void cambioClassTest(){
        float cambio = cambiador.cambiar(cantidadInicial);

        assertEquals(cambio,cantidadFinal,0.1f);
        assertEquals(tipoCambio,cambiador.getTipoCambio(),0.1f);
        assertEquals(comision,cambiador.getComision(),0.1f);
        assertEquals(monedaInicial,cambiador.getMonedaOrigen());
        assertEquals(monedaFinal,cambiador.getMonedaDestino());
    }

}
