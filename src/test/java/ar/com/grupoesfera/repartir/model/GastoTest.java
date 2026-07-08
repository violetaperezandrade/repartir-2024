package ar.com.grupoesfera.repartir.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GastoTest {

    @Test
    void esValidoCuandoElMontoEsPositivo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(400000, 2));

        assertThat(gasto.esValido()).isTrue();
    }

    @Test
    void noEsValidoCuandoElMontoEsCero() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(0, 2));

        assertThat(gasto.esValido()).isFalse();
    }

    @Test
    void noEsValidoCuandoElMontoEsNegativo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(BigDecimal.valueOf(-50000, 2));

        assertThat(gasto.esValido()).isFalse();
    }

    @Test
    void noEsValidoCuandoElMontoEsNulo() {

        Gasto gasto = new Gasto();
        gasto.setMonto(null);

        assertThat(gasto.esValido()).isFalse();
    }
}
