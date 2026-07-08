package ar.com.grupoesfera.repartir.services;

import ar.com.grupoesfera.repartir.exceptions.GrupoInvalidoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.repositories.GruposRepository;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MontoPositivoSteps extends FastCucumberSteps {

    private static final Long ID_GRUPO = 1L;

    private GruposService grupos;
    private GruposRepository repository;
    private Grupo grupo;
    private GrupoInvalidoException error;

    @Dado("el grupo {string} sin gastos")
    public void elGrupoSinGastos(String nombre) {

        prepararGrupo(nombre, pesos(0));
    }

    @Dado("el grupo {string} con un total de $ {int}")
    public void elGrupoConUnTotalDe$(String nombre, int total) {

        prepararGrupo(nombre, pesos(total));
    }

    @Cuando("se registra un gasto de $ {int}")
    public void seRegistraUnGastoDe$(int monto) {

        registrarGasto(monto);
    }

    @Cuando("se intenta registrar un gasto de $ {int}")
    public void seIntentaRegistrarUnGastoDe$(int monto) {

        registrarGasto(monto);
    }

    @Entonces("el total del grupo {string} debería ser $ {int}")
    public void elTotalDelGrupoDeberiaSer$(String nombre, int total) {

        assertThat(error).isNull();
        assertThat(grupo.getNombre()).isEqualTo(nombre);
        assertThat(grupo.getTotal()).isEqualTo(pesos(total));
        verify(repository).save(grupo);
    }

    @Entonces("el total del grupo {string} debería seguir siendo $ {int}")
    public void elTotalDelGrupoDeberiaSeguirSiendo$(String nombre, int total) {

        assertThat(error).isInstanceOf(GrupoInvalidoException.class);
        assertThat(grupo.getNombre()).isEqualTo(nombre);
        assertThat(grupo.getTotal()).isEqualTo(pesos(total));
        verify(repository, never()).save(grupo);
    }

    private void prepararGrupo(String nombre, BigDecimal total) {

        grupos = new GruposService();
        repository = mock(GruposRepository.class);
        grupos.repository = repository;
        grupos.montos = new MontosService();

        grupo = new Grupo(ID_GRUPO);
        grupo.setNombre(nombre);
        grupo.setTotal(total);

        when(repository.findById(ID_GRUPO)).thenReturn(Optional.of(grupo));
    }

    private void registrarGasto(int monto) {

        try {
            grupos.agregarGasto(ID_GRUPO, gastoPor(monto));
        } catch (GrupoInvalidoException e) {
            error = e;
        }
    }

    private Gasto gastoPor(int monto) {

        Gasto gasto = new Gasto();
        gasto.setMonto(pesos(monto));
        return gasto;
    }

    private BigDecimal pesos(int monto) {

        return BigDecimal.valueOf(monto * 100L, 2);
    }
}
