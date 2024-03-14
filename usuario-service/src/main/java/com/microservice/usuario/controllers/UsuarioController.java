package com.microservice.usuario.controllers;

import com.microservice.usuario.entities.Usuario;
import com.microservice.usuario.models.Carro;
import com.microservice.usuario.models.Moto;
import com.microservice.usuario.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    // http://localhost:8001/api/usuario/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getUsuarioAll() {

        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // http://localhost:8001/api/usuario/search/{id}
    @GetMapping("/search/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // http://localhost:8001/api/usuario/save
    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }


    /* Métodos do controller que fará comunicação com os microserviços Carro e Moto - utilizando RestTemplate */

    /**
     * Método para obter a lista de carros associados a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter os carros.
     * @return Um ResponseEntity contendo uma lista de objetos do tipo Carro associados ao usuário.
     */

    // http://localhost:8001/api/usuario/carros/{usuarioId}
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    /**
     * Método para obter a lista de motos associadas a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter as motos.
     * @return Um ResponseEntity contendo uma lista de objetos do tipo Moto associadas ao usuário.
     */

    // http://localhost:8001/api/usuario/motos/{usuarioId}
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }


    /* Métodos do serviço que fará comunicação com os microserviços Carro e Moto - utilizando OpenFeign */

    // http://localhost:8001/api/usuario/save/carro/{usuarioId}
    @PostMapping("/save/carro/{usuarioId}")
    public ResponseEntity<Carro> salvarCarro(@PathVariable int usuarioId, @RequestBody Carro carro){
        Carro novoCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(novoCarro);
    }
}
