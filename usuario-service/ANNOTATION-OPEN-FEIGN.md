# OpenFeign

O OpenFeign é uma biblioteca de cliente HTTP para simplificar a comunicação entre serviços RESTful em Java. Desenvolvido pela Netflix e posteriormente incorporado ao Spring Cloud, o OpenFeign foi projetado para facilitar a escrita de clientes RESTful em Java, tornando a comunicação entre serviços mais fácil e eficiente.

A principal ideia por trás do OpenFeign é fornecer uma maneira simples e declarativa de definir e consumir interfaces de serviços RESTful. Em vez de lidar manualmente com a criação de requisições HTTP, configuração de clientes e tratamento de respostas, os desenvolvedores podem simplesmente definir interfaces Java com anotações que descrevem os endpoints do serviço. O OpenFeign então gera automaticamente o cliente HTTP apropriado com base nessas interfaces, lidando com muitos detalhes de implementação para você.

Além disso, o OpenFeign oferece suporte a recursos como:

1. Anotações para configurar facilmente cabeçalhos HTTP, parâmetros de consulta, tipos de conteúdo, etc.
2. Interceptadores para personalizar o comportamento do cliente, como logging, manipulação de erros, etc.
3. Integração com bibliotecas como Ribbon para balanceamento de carga e Hystrix para tolerância a falhas.
4. Suporte a Spring Cloud para integração fácil em aplicativos baseados em Spring.

Usando o OpenFeign, os desenvolvedores podem escrever código mais limpo e legível, reduzindo a quantidade de código boilerplate necessário para consumir serviços RESTful. Isso torna a comunicação entre serviços mais simples e menos propensa a erros, ao mesmo tempo em que melhora a produtividade do desenvolvedor.

Em resumo, o OpenFeign é uma poderosa biblioteca para comunicação entre serviços em Java, facilitando a integração e o consumo de serviços RESTful de uma maneira simples e declarativa.

# Feign Client para comunicação de Microserviços

O Feign Client é uma ferramenta poderosa para facilitar a comunicação entre microserviços em uma arquitetura distribuída. Aqui estão alguns pontos-chave sobre o uso do Feign Client para comunicação de microserviços:

1. **Abstração de chamadas HTTP**: O Feign Client permite que você defina interfaces Java com anotações para descrever chamadas HTTP para serviços remotos. Isso abstrai muitos detalhes de implementação, como criação de conexões HTTP, serialização e desserialização de dados, e tratamento de erros, simplificando o código do cliente.

2. **Declaratividade**: Com o Feign, você pode definir clientes RESTful declarativos, o que significa que você simplesmente descreve as operações que deseja executar em um serviço remoto, em vez de escrever código detalhado para fazer solicitações HTTP.

3. **Integração com Spring Cloud**: O Feign é frequentemente usado em conjunto com o Spring Cloud para construir e implantar aplicativos em uma arquitetura de microserviços. O Spring Cloud fornece recursos para descoberta de serviços, balanceamento de carga, tolerância a falhas e muito mais, facilitando a integração e a comunicação entre microserviços.

4. **Facilidade de configuração**: O Feign Client pode ser configurado facilmente usando anotações Java, como `@FeignClient` para definir o cliente Feign, `@RequestMapping` para mapear os endpoints do serviço remoto e `@RequestBody` para serializar objetos no corpo das solicitações HTTP.

5. **Suporte a balanceamento de carga**: O Feign Client pode ser configurado para trabalhar com serviços de descoberta, como o Eureka, para obter automaticamente informações sobre os serviços disponíveis e distribuir as solicitações entre várias instâncias de um serviço, melhorando a escalabilidade e a disponibilidade do sistema.

6. **Facilidade de manutenção**: Ao usar o Feign Client, seu código se beneficia de uma abstração de comunicação com o serviço remoto. Isso torna mais fácil fazer alterações no serviço remoto sem afetar diretamente o código do cliente, pois as alterações na interface do Feign Client podem ser isoladas das mudanças no serviço remoto.

Em resumo, o Feign Client é uma ferramenta valiosa para simplificar a comunicação entre microserviços em uma arquitetura distribuída. Ele fornece uma maneira fácil e declarativa de definir e consumir APIs RESTful, reduzindo a complexidade e o esforço necessários para integrar e manter aplicativos em um ambiente de microserviços.

# Configuração de Dependência do OpenFeign

## Introdução
Este documento fornece instruções sobre como configurar a dependência do OpenFeign em um projeto Java usando o Apache Maven. O OpenFeign é uma biblioteca de cliente HTTP que simplifica a comunicação entre serviços RESTful em uma arquitetura de microservices.

## Dependência Maven
Para começar a usar o OpenFeign em seu projeto, adicione a seguinte dependência ao seu arquivo `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <!-- Outras dependências do seu projeto podem ser adicionadas aqui -->
</dependencies>
```

## Configuração de Gerenciamento de Dependências
Além disso, é recomendável usar o gerenciamento de dependências do Spring Cloud para garantir que todas as dependências do Spring Cloud estejam na versão correta e sejam compatíveis entre si. Para fazer isso, adicione a seguinte configuração ao seu arquivo `pom.xml`:

```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2022.0.5</spring-cloud.version>
</properties>
```

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2022.0.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**OBS.** Certifique-se de substituir `2022.0.2` pela versão do Spring Cloud que você está usando em seu projeto.

## Conclusão
Com o OpenFeign, você pode simplificar a comunicação entre serviços em uma arquitetura de microservices, tornando o desenvolvimento mais eficiente e produtivo.

# Anotação @EnableFeignClients

A anotação `@EnableFeignClients` é uma das principais anotações fornecidas pelo Spring Cloud para habilitar o uso do OpenFeign em um aplicativo Spring Boot. Quando adiciona essa anotação a uma classe de configuração em seu aplicativo, o Spring Boot procura por interfaces marcadas com anotações `@FeignClient` e as registra como beans gerenciados pelo contêiner Spring.

Explicação mais detalhada sobre como funciona essa anotação:

1. **Escanear pacotes**: Quando você usa `@EnableFeignClients`, o Spring Boot escaneia automaticamente os pacotes do seu projeto em busca de interfaces anotadas com `@FeignClient`.

2. **Criação de beans**: Para cada interface que é encontrada, o Spring Boot cria uma instância do cliente Feign correspondente e a registra no contexto do aplicativo como um bean gerenciado.

3. **Configuração automática**: Além de criar os beans Feign para você, `@EnableFeignClients` também configura automaticamente várias características importantes do Feign, como balanceamento de carga, tolerância a falhas e outras configurações de cliente HTTP.

4. **Integração com Spring Cloud**: Essa anotação também é crucial quando você está construindo um aplicativo Spring Boot que faz parte de um ambiente de microservices gerenciado pelo Spring Cloud. `@EnableFeignClients` trabalha em conjunto com outras partes do Spring Cloud, como Ribbon para balanceamento de carga e Hystrix para tolerância a falhas, simplificando ainda mais o desenvolvimento de aplicativos distribuídos.

Exemplo de como `@EnableFeignClients` é usada na classe `UsuarioServiceApplication` de configuração Spring Boot:

```java
package com.microservice.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }

}
```

Em resumo, `@EnableFeignClients` é uma anotação importante para habilitar o uso do OpenFeign em um aplicativo Spring Boot, permitindo que crie facilmente clientes RESTful declarativos e simplificando a comunicação entre serviços em um ambiente distribuído.




Aqui está a documentação para a interface `CarroFeignClient`:

---

# Interface CarroFeignClient

## Introdução
Esta documentação descreve a interface `CarroFeignClient`, que é um cliente Feign usado para comunicar-se com o serviço "carro-service". Essa interface define métodos para salvar carros e obter carros associados a um usuário específico.

## Interface CarroFeignClient

```java
package com.microservice.usuario.feignclients;
import com.microservice.usuario.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
@FeignClient(value = "carro-service", url = "http://localhost:8081")
public interface CarroFeignClient {
    @PostMapping("/api/carro/save")
    public Carro save(@RequestBody Carro carro);
    @GetMapping("/api/carro/searchUserId/{usuarioId}")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
```

## Detalhes dos Métodos

### Método `save`
- **Descrição**: Este método é usado para salvar um novo carro no serviço "carro-service".
- **Método HTTP**: POST
- **Endpoint**: `/api/carro/save`
- **Parâmetro**: `carro` - O objeto Carro a ser salvo.
- **Retorno**: O objeto Carro salvo.

### Método `getCarros`
- **Descrição**: Este método é usado para obter uma lista de carros associados a um determinado usuário.
- **Método HTTP**: GET
- **Endpoint**: `/api/carro/searchUserId/{usuarioId}`
- **Parâmetro**: `usuarioId` - O ID do usuário para o qual deseja-se buscar carros.
- **Retorno**: Uma lista de objetos Carro associados ao usuário especificado.

## Configuração
A anotação `@FeignClient` é usada para configurar o cliente Feign. Ela especifica o nome do serviço a ser chamado ("carro-service") e a URL base do serviço. Neste caso, a URL base é "http://localhost:8081".

## Uso
Para usar a interface `CarroFeignClient`, injete-a em sua classe de serviço ou controlador Spring e chame os métodos definidos conforme necessário.

```java
@Autowired
private CarroFeignClient carroFeignClient;
```

## Conclusão
A interface `CarroFeignClient` simplifica a comunicação com o serviço "carro-service" em uma arquitetura de microservices. Com essa interface, é possível salvar carros e obter carros associados a usuários de forma simples e eficiente, utilizando os métodos definidos.

# Interface MotoFeignClient

## Introdução
Esta documentação descreve a interface `MotoFeignClient`, que é um cliente Feign usado para comunicar-se com o serviço "moto-service". Essa interface define métodos para salvar motos e obter motos associadas a um usuário específico.

## Interface MotoFeignClient

```java
package com.microservice.usuario.feignclients;
import com.microservice.usuario.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
@FeignClient(name = "moto-service", url = "http://localhost:8002")
public interface MotoFeignClient {
    @PostMapping("/api/moto/save")
    public Moto save(@RequestBody Moto moto);
    @GetMapping("/api/moto/searchUserId/{usuarioId}")
    public List<Moto> getMotos(@PathVariable int usuarioId);
}
```

## Detalhes dos Métodos

### Método `save`
- **Descrição**: Este método é usado para salvar uma nova moto no serviço "moto-service".
- **Método HTTP**: POST
- **Endpoint**: `/api/moto/save`
- **Parâmetro**: `moto` - O objeto Moto a ser salvo.
- **Retorno**: O objeto Moto salvo.

### Método `getMotos`
- **Descrição**: Este método é usado para obter uma lista de motos associadas a um determinado usuário.
- **Método HTTP**: GET
- **Endpoint**: `/api/moto/searchUserId/{usuarioId}`
- **Parâmetro**: `usuarioId` - O ID do usuário para o qual deseja-se buscar motos.
- **Retorno**: Uma lista de objetos Moto associados ao usuário especificado.

## Configuração
A anotação `@FeignClient` é usada para configurar o cliente Feign. Ela especifica o nome do serviço a ser chamado ("moto-service") e a URL base do serviço. Neste caso, a URL base é "http://localhost:8002".

## Uso
Para usar a interface `MotoFeignClient`, injete-a em sua classe de serviço ou controlador Spring e chame os métodos definidos conforme necessário.

```java
@Autowired
private MotoFeignClient motoFeignClient;
```

## Conclusão
A interface `MotoFeignClient` simplifica a comunicação com o serviço "moto-service" em uma arquitetura de microservices. Com essa interface, é possível salvar motos e obter motos associadas a usuários de forma simples e eficiente, utilizando os métodos definidos.


# Classe UsuarioService

## Introdução
Esta documentação descreve a classe `UsuarioService`, que é responsável por fornecer funcionalidades relacionadas a usuários, como salvar carros e motos associados a um usuário e obter informações sobre um usuário, seus carros e motos.

```java
@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RestTemplate restTemplate;
    private CarroFeignClient carroFeignClient;
    private MotoFeignClient motoFeignClient;
    // Outros métodos da classe...

    /* Métodos do serviço que fará comunicação com os microserviços Carro e Moto - utilizando OpenFeign */
    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro novoCarro = carroFeignClient.save(carro);
        return novoCarro;
    }
    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto novaMoto = motoFeignClient.save(moto);
        return novaMoto;
    }
    public Map<String, Object> getUsuarioAndCarroEMoto(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Id: " + usuarioId + " não encontrado"));
        if (usuario == null) {
            resultado.put("Mensagem", "Usuário não existe!");
            return resultado;
        }
        resultado.put("Usuario", usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if (carros.isEmpty()) {
            resultado.put("Carros", "Usuário não tem carro!");
        } else {
            resultado.put("Carros", carros);
        }
        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (motos.isEmpty()) {
            resultado.put("Motos", "Usuário não tem moto!");
        } else {
            resultado.put("Motos", motos);
        }
        return resultado;
    }
}
```

## Detalhes da Classe

### Injeção de Dependências
- A anotação `@Service` marca esta classe como um componente de serviço do Spring.
- A anotação `@AllArgsConstructor` é usada para gerar um construtor que aceita todas as dependências da classe como argumentos. Isso simplifica a injeção de dependências.

### Atributos
- `usuarioRepository`: Uma instância de `UsuarioRepository` utilizada para acessar e manipular dados de usuários no banco de dados.
- `restTemplate`: Um cliente HTTP para fazer chamadas a serviços externos.
- `carroFeignClient`: Uma instância de `CarroFeignClient`, um cliente Feign para se comunicar com o serviço de carros.
- `motoFeignClient`: Uma instância de `MotoFeignClient`, um cliente Feign para se comunicar com o serviço de motos.

## Métodos

### Método `saveCarro`
- **Descrição**: Salva um carro associado a um usuário.
- **Parâmetros**: `usuarioId` - O ID do usuário ao qual o carro será associado. `carro` - O objeto Carro a ser salvo.
- **Retorno**: O objeto Carro salvo.

### Método `saveMoto`
- **Descrição**: Salva uma moto associada a um usuário.
- **Parâmetros**: `usuarioId` - O ID do usuário ao qual a moto será associada. `moto` - O objeto Moto a ser salvo.
- **Retorno**: O objeto Moto salvo.

### Método `getUsuarioAndCarroEMoto`
- **Descrição**: Obtém informações sobre um usuário, seus carros e suas motos associadas.
- **Parâmetros**: `usuarioId` - O ID do usuário do qual deseja-se obter as informações.
- **Retorno**: Um mapa contendo informações sobre o usuário, seus carros e suas motos associadas.

## Conclusão
A classe `UsuarioService` encapsula a lógica de negócios relacionada aos usuários, carros e motos em um sistema de microservices. Ela utiliza a injeção de dependências para se comunicar com o banco de dados, outros serviços e clientes Feign para realizar operações relacionadas aos usuários, como salvar carros e motos associados a um usuário e obter informações sobre um usuário e seus veículos.


# Classe UsuarioController

## Introdução
Esta documentação descreve a classe `UsuarioController`, que é responsável por fornecer endpoints RESTful para manipulação de usuários, incluindo ações como salvar carros e motos associados a um usuário e obter informações sobre um usuário e seus veículos.

```java
@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;
    // Outros métodos da classe...

    /* Métodos do serviço que fará comunicação com os microserviços Carro e Moto - utilizando OpenFeign */
    // http://localhost:8001/api/usuario/save/carro/{usuarioId}
    @PostMapping("/save/carro/{usuarioId}")
    public ResponseEntity<Carro> salvarCarro(@PathVariable int usuarioId, @RequestBody Carro carro){
        Carro novoCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(novoCarro);
    }
    // http://localhost:8001/api/usuario/save/moto/{usuarioId}
    @PostMapping("/save/moto/{usuarioId}")
    public ResponseEntity<Moto> salvarMoto(@PathVariable int usuarioId, @RequestBody Moto moto){
        Moto novaMoto = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(novaMoto);
    }
    // http://localhost:8001/api/usuario/searchAllCarAndMotocycle/{usuarioId}
    @GetMapping("/searchAllCarAndMotocycle/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosCarrosEMotos(@PathVariable int usuarioId){
        Map<String, Object> resultado = usuarioService.getUsuarioAndCarroEMoto(usuarioId);
        return ResponseEntity.ok(resultado);
    }
}
```

## Detalhes da Classe

### Anotações
- A anotação `@RestController` marca esta classe como um controlador Spring que lida com solicitações HTTP e retorna respostas JSON.
- A anotação `@RequestMapping` define o prefixo da URL para todos os endpoints neste controlador como "/api/usuario".
- A anotação `@AllArgsConstructor` é usada para gerar um construtor que aceita todas as dependências da classe como argumentos. Isso simplifica a injeção de dependências.

### Atributos
- `usuarioService`: Uma instância de `UsuarioService` utilizada para acessar e manipular dados relacionados a usuários.

## Métodos

### Método `salvarCarro`
- **Descrição**: Salva um carro associado a um usuário.
- **Endpoint**: POST `/save/carro/{usuarioId}`
- **Parâmetros**: `usuarioId` - O ID do usuário ao qual o carro será associado. `carro` - O objeto Carro a ser salvo no corpo da requisição.
- **Retorno**: ResponseEntity contendo o objeto Carro salvo.

### Método `salvarMoto`
- **Descrição**: Salva uma moto associada a um usuário.
- **Endpoint**: POST `/save/moto/{usuarioId}`
- **Parâmetros**: `usuarioId` - O ID do usuário ao qual a moto será associada. `moto` - O objeto Moto a ser salvo no corpo da requisição.
- **Retorno**: ResponseEntity contendo o objeto Moto salvo.

### Método `listarTodosCarrosEMotos`
- **Descrição**: Obtém informações sobre um usuário, seus carros e suas motos associadas.
- **Endpoint**: GET `/searchAllCarAndMotocycle/{usuarioId}`
- **Parâmetros**: `usuarioId` - O ID do usuário do qual deseja-se obter as informações.
- **Retorno**: ResponseEntity contendo um mapa com informações sobre o usuário, seus carros e suas motos associadas.

## Conclusão
A classe `UsuarioController` fornece endpoints RESTful para manipulação de usuários em um sistema de microservices. Ela delega as solicitações para o serviço correspondente na classe `UsuarioService` e retorna respostas adequadas em formato JSON. Com esses endpoints, é possível realizar ações como salvar carros e motos associados a um usuário e obter informações sobre um usuário e seus veículos.

---
# Autor
## Feito por: `Daniel Penelva de Andrade`