package ar.com.grupoesfera.repartir.services;

import ar.com.grupoesfera.repartir.exceptions.GrupoInvalidoException;
import ar.com.grupoesfera.repartir.exceptions.GrupoNoEncontradoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.repositories.GruposRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruposService {

    @Autowired
    GruposRepository repository;

    @Autowired
    MontosService montos;

    public List<Grupo> listarGrupos() {

        return  repository.findAll();
    }

    public Grupo crear(Grupo nuevoGrupo) {

        validar(nuevoGrupo);
        guardar(nuevoGrupo);

        return nuevoGrupo;
    }

    private void guardar(Grupo nuevoGrupo) {
        montos.inicializarTotal(nuevoGrupo);
        repository.save(nuevoGrupo);
    }

    private void validar(Grupo nuevoGrupo) {
        if (!nuevoGrupo.estaFormado()) {
            throw new GrupoInvalidoException();
        }

        if (Strings.isBlank(nuevoGrupo.getNombre())) {
            throw new GrupoInvalidoException();

        }
    }

    public Grupo recuperar(Long id) {

        Optional<Grupo> grupoBuscado = repository.findById(id);

        if (!grupoBuscado.isPresent()) {
            throw new GrupoNoEncontradoException();
        }

        return grupoBuscado.get();
    }

    public Grupo agregarGasto(Long id, Gasto gasto) {

        Grupo grupo = recuperar(id);
        validar(gasto);
        montos.acumularAlTotal(grupo, gasto);
        repository.save(grupo);
        return grupo;
    }

    private void validar(Gasto gasto) {
        if (gasto == null || !gasto.esValido()) {
            throw new GrupoInvalidoException();
        }
    }

}
