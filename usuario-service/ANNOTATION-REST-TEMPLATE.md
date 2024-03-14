# RestTemplate
O `RestTemplate` é uma classe fundamental na biblioteca Spring Framework para acessar e consumir serviços RESTful em aplicativos Java. Ele simplifica muito a comunicação com APIs HTTP, fornecendo métodos convenientes para enviar solicitações HTTP e processar respostas.

Pontos-chave sobre o `RestTemplate`:

1. **Integração HTTP simplificada**: O `RestTemplate` oferece uma maneira simples e direta de interagir com serviços web baseados em HTTP. Ele abstrai muitos detalhes de baixo nível relacionados ao HTTP, permitindo que os desenvolvedores se concentrem na lógica de negócios de alto nível.

2. **Operações HTTP**: O `RestTemplate` suporta todos os principais métodos HTTP, como GET, POST, PUT, DELETE, PATCH, etc. Isso permite que os desenvolvedores construam aplicativos que interagem de maneira completa e eficiente com APIs RESTful.

3. **Marshalling e Unmarshalling de Dados**: O `RestTemplate` pode converter automaticamente entre objetos Java e representações de dados JSON ou XML, facilitando o envio e a recepção de dados em aplicativos Java.

4. **Configuração personalizada**: O `RestTemplate` pode ser configurado com interceptadores, conversores de mensagem, gerenciadores de autenticação e muito mais. Isso oferece uma grande flexibilidade para ajustar o comportamento do cliente HTTP de acordo com os requisitos específicos do aplicativo.

5. **Tratamento de erros**: O `RestTemplate` fornece recursos para lidar com erros de comunicação, como timeouts, erros de rede e erros de servidor. Isso ajuda a garantir que os aplicativos possam lidar com falhas de maneira robusta e elegante.

6. **Suporte a tipos genéricos**: O `RestTemplate` suporta tipos genéricos, o que permite o uso de classes Java parametrizadas para representar entidades de domínio complexas ao trabalhar com APIs RESTful.

7. **Facilidade de uso**: O `RestTemplate` é fácil de usar e possui uma API intuitiva. Com apenas algumas linhas de código, os desenvolvedores podem criar clientes HTTP poderosos e flexíveis em seus aplicativos.

# Comunicação dos microserviços usando RestTemplate

Usar o `RestTemplate` para ajudar na comunicação com microserviços é uma prática comum em arquiteturas de microserviços. Ele permite que diferentes partes de um sistema distribuído se comuniquem de forma eficiente e confiável. No meu caso estou usando o `RestTemplate` para se comunicar com os microserviços de três APIs: usuário, carro e moto.

Algumas considerações específicas para cada um dos microserviços:

1. **API de Usuário**: Este microserviço lida com informações relacionadas aos usuários. Estou usando o `RestTemplate` para enviar solicitações HTTP para este microserviço, por exemplo, para recuperar dados de todos os carros e de todas as motos pelo id do usuário.

2. **API de Carro**: Este microserviço pode lidar com informações sobre carros, como detalhes do veículo, marca e modelo. Com o `RestTemplate` estou enviando solicitações para este microserviço para obter informações sobre carros disponíveis de um determinado usuário.

3. **API de Moto**: Da mesma forma que o microserviço de carro, o microserviço de moto gerencia informações relacionadas a motocicletas. Com o `RestTemplate` estou enviando solicitações para este microserviço para obter informações sobre motos disponíveis de um determinado usuário.

Ao usar o `RestTemplate` para se comunicar com esses microserviços, é importante considerar questões como tratamento de erros, autenticação, autorização, e também garantir uma comunicação segura e eficiente entre os diferentes componentes do sistema. Além disso, pode modularizar seu código, encapsulando a lógica de comunicação com cada microserviço em classes separadas para manter a coesão e facilitar a manutenção. Porém para o meu caso estou fazendo exemplos simples para obter aprendizado.

# Classe RestTemplateConfig

```java

package com.microservice.usuario.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Método responsável por criar e configurar uma instância do RestTemplate.
     * @return Uma instância de RestTemplate configurada.
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

### Descrição:
A classe `RestTemplateConfig` é uma classe de configuração Spring responsável por configurar e fornecer uma instância do `RestTemplate`, que será usada para realizar chamadas HTTP a outros microserviços dentro do sistema.

### Anotações:
- `@Configuration`: Esta anotação marca a classe como uma classe de configuração Spring, indicando que ela contém definições de beans que serão gerenciados pelo contêiner Spring.
- `@Bean`: Esta anotação é usada para marcar o método `restTemplate()` como um método de fábrica de bean. Ele informa ao Spring que o objeto retornado por este método deve ser registrado como um bean gerenciado pelo contêiner Spring.

### Métodos:
- `restTemplate()`: Este método cria e retorna uma instância do `RestTemplate`. Ele será chamado pelo contêiner Spring ao inicializar o aplicativo e uma instância do `RestTemplate` será disponibilizada para ser injetada em outras partes do código que precisam se comunicar com serviços externos.

### Uso:
Esta classe é usada para configurar um `RestTemplate` que pode ser injetado em outras partes do sistema que precisam realizar chamadas HTTP para outros microserviços, como as APIs de carro e moto mencionadas anteriormente.

Essa documentação fornece uma visão geral clara da finalidade e uso da classe `RestTemplateConfig`, facilitando o entendimento e a manutenção do código.

# Classe UsuarioService

```java
@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RestTemplate restTemplate;

    /* continuação do código dos outros métodos ... */

    /**
     * Método para obter a lista de carros associados a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter os carros.
     * @return Uma lista de objetos do tipo Carro associados ao usuário.
     */
    public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = restTemplate.getForObject("http://localhost:8081/api/carro/searchUserId/" + usuarioId, List.class);
        return carros;
    }

    /**
     * Método para obter a lista de motos associadas a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter as motos.
     * @return Uma lista de objetos do tipo Moto associadas ao usuário.
     */
    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8002/api/moto/searchUserId/" + usuarioId, List.class);
        return motos;
    }
}
```

### Descrição:
A classe `UsuarioService` é um serviço Spring responsável por operações relacionadas a usuários, incluindo a comunicação com os microserviços de carro e moto para recuperar os carros e motos associados a um usuário específico.

### Anotações:
- `@Service`: Esta anotação marca a classe como um serviço Spring, indicando que ela contém a lógica de negócios relacionada aos usuários.
- `@AllArgsConstructor`: Esta anotação é uma conveniência do Lombok que gera um construtor que inicializa todos os campos marcados com `@NonNull` ou `final`, neste caso, ele é usado para injetar automaticamente o `RestTemplate` no serviço.

### Campos:
- `private RestTemplate restTemplate`: Um objeto `RestTemplate` é injetado no serviço para permitir a comunicação com os microserviços de carro e moto.

### Métodos:
- `getCarros(int usuarioId)`: Este método faz uma chamada para o microserviço de carro para obter a lista de carros associados a um usuário específico.
- `getMotos(int usuarioId)`: Este método faz uma chamada para o microserviço de moto para obter a lista de motos associadas a um usuário específico.

### Parâmetros:
- `usuarioId`: O ID do usuário para o qual se deseja obter os carros ou motos associados.

### Retorno:
- Uma lista de objetos do tipo `Carro` ou `Moto`, respectivamente, contendo os carros ou motos associados ao usuário.

### Analisando parte do código:

1. `restTemplate.getForObject("http://localhost:8081/api/carro/searchUserId/" + usuarioId, List.class);`

    - `restTemplate`: É uma instância da classe `RestTemplate`, que é usada para fazer solicitações HTTP para outros serviços.
    - `.getForObject(...)`: Este é um método do `RestTemplate` que realiza uma solicitação HTTP GET e espera uma resposta do tipo especificado.
    - `"http://localhost:8081/api/carro/searchUserId/" + usuarioId`: É a URL para o serviço de carro, com o ID do usuário concatenado no final. Isso indica que está fazendo uma solicitação para o endpoint que busca carros associados a um usuário específico. Esse endpoint está definido na classe `CarroController` do método `listarCarroPorUsuarioId(@PathVariable int usuarioId)`.
    - `List.class`: Este parâmetro indica que está esperando uma resposta que será mapeada para uma lista de objetos do tipo especificado. No caso, `List.class` indica que está esperando uma lista de objetos.


2. `restTemplate.getForObject("http://localhost:8002/api/moto/searchUserId/" + usuarioId, List.class);`

    - `restTemplate`: Novamente, é uma instância do `RestTemplate`.
    - `.getForObject(...)`: Da mesma forma que no exemplo anterior, é usado para fazer uma solicitação HTTP GET e esperar uma resposta.
    - `"http://localhost:8002/api/moto/searchUserId/" + usuarioId`: Esta é a URL para o serviço de moto, com o ID do usuário concatenado no final. Indica que está fazendo uma solicitação para o endpoint que busca motos associadas a um usuário específico. Esse endpoint está definido na classe `MotoController` do método `listarMotoPorUsuarioId(@PathVariable int usuarioId)`.
    - `List.class`: Novamente, indica que está esperando uma resposta que será mapeada para uma lista de objetos, neste caso, objetos do tipo `Moto`.

Em resumo, essas partes do código estão utilizando o `RestTemplate` para fazer solicitações HTTP GET para os serviços de carro e moto, passando o ID do usuário como parâmetro na URL. Os serviços de carro e moto, por sua vez, respondem com uma lista de carros ou motos associados ao usuário especificado. Essas listas são então mapeadas para objetos Java correspondentes (`Carro` e `Moto`, respectivamente) e retornadas como resultado das chamadas dos métodos `getCarros` e `getMotos` do `UsuarioService`.

Essa documentação fornece uma descrição clara dos métodos, seus parâmetros e retornos, facilitando o entendimento e o uso do `UsuarioService`.

# Classe UsuarioController

```java
@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    /* continuação do código dos outros métodos ... */

    /**
     * Método para obter a lista de carros associados a um usuário.
     * @param usuarioId O ID do usuário para o qual se deseja obter os carros.
     * @return Um ResponseEntity contendo uma lista de objetos do tipo Carro associados ao usuário.
     */
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
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }
}
```

### Descrição:
A classe `UsuarioController` é um controlador Spring responsável por definir endpoints relacionados a usuários. Ele recebe requisições HTTP e invoca os métodos apropriados do serviço `UsuarioService` para processar essas requisições e retornar as respostas correspondentes.

### Anotações:
- `@RestController`: Esta anotação combina as anotações `@Controller` e `@ResponseBody`, indicando que esta classe é um controlador Spring que retorna dados no corpo da resposta HTTP.
- `@RequestMapping("/api/usuario")`: Esta anotação define o caminho base para todos os endpoints neste controlador. Todos os endpoints definidos nesta classe terão `/api/usuario` como parte do URL.
- `@AllArgsConstructor`: Esta anotação é uma conveniência do Lombok que gera um construtor que inicializa todos os campos marcados com `@NonNull` ou `final`, neste caso, ele é usado para injetar automaticamente o `UsuarioService` no controlador.

### Campos:
- `private UsuarioService usuarioService`: Uma instância de `UsuarioService` é injetada no controlador para permitir a comunicação com os microserviços de carro e moto.

### Métodos:
- `getCarros(int usuarioId)`: Este método trata requisições GET para obter a lista de carros associados a um usuário específico.
- `getMotos(int usuarioId)`: Este método trata requisições GET para obter a lista de motos associadas a um usuário específico.

### Parâmetros:
- `usuarioId`: O ID do usuário para o qual se deseja obter os carros ou motos associados.

### Retorno:
- Um `ResponseEntity` contendo uma lista de objetos do tipo `Carro` ou `Moto`, respectivamente, ou uma resposta de status 404 (Not Found) se o usuário não for encontrado.

Essa documentação fornece uma descrição clara dos métodos, seus parâmetros e retornos, facilitando o entendimento e o uso do `UsuarioController`.

---

# Autor
## Feito por `Daniel Penelva de Andrade`