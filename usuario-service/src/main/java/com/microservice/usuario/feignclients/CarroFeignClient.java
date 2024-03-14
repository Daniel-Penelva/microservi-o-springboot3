package com.microservice.usuario.feignclients;

import com.microservice.usuario.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "carro-service", url = "http://localhost:8002")
public interface CarroFeignClient {

    @PostMapping("/api/carro/save")
    public Carro save(@RequestBody Carro carro);
}
