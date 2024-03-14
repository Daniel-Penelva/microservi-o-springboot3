package com.microservice.usuario.services;

import com.microservice.usuario.entities.Usuario;
import com.microservice.usuario.feignclients.CarroFeignClient;
import com.microservice.usuario.feignclients.MotoFeignClient;
import com.microservice.usuario.models.Carro;
import com.microservice.usuario.models.Moto;
import com.microservice.usuario.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RestTemplate restTemplate;
    private CarroFeignClient carroFeignClient;
    private MotoFeignClient motoFeignClient;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Id: " + id + " não encontrado!"));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    /* Métodos do serviço que fará comunicação com os microserviços Carro e Moto - utilizando RestTemplate */

    /**
     * Método para obter a lista de carros associados a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter os carros.
     * @return Uma lista de objetos do tipo Carro associados ao usuário.
     */
    public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/api/carro/searchUserId/" + usuarioId, List.class);
        return carros;
    }

    /**
     * Método para obter a lista de motos associadas a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter as motos.
     * @return Uma lista de objetos do tipo Moto associadas ao usuário.
     */
    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/moto/searchUserId/" + usuarioId, List.class);
        return motos;
    }

    /* Métodos do serviço que fará comunicação com os microserviços Carro e Moto - utilizando OpenFeign */

    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro novoCarro = carroFeignClient.save(carro);
        return novoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto novaMoto = motoFeignClient.save(moto);
        return novaMoto;
    }
}