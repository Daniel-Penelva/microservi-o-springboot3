# Eureka

O servidor Eureka é uma parte fundamental da arquitetura do Netflix OSS (Open Source Software). Ele é um serviço de descoberta de microservices, que ajuda na localização e comunicação entre diferentes serviços em um ambiente distribuído. Resumo de como funciona:

1. **Registro de Serviços**: Os serviços que desejam ser descobertos e acessíveis registram-se no servidor Eureka. Isso normalmente é feito durante o processo de inicialização do serviço.

2. **Heartbeats**: Após o registro, os serviços enviam "heartbeats" regulares para o servidor Eureka para informá-lo de que estão ativos e funcionando corretamente. Isso permite que o servidor Eureka mantenha um registro atualizado de quais serviços estão disponíveis e sua localização.

3. **Renovação de Registro**: Os serviços precisam renovar seu registro periodicamente, enviando novos heartbeats. Se um serviço parar de enviar heartbeats, o servidor Eureka assume que o serviço está inativo e remove-o do registro.

4. **Descoberta de Serviços**: Os clientes que desejam se comunicar com outros serviços consultam o servidor Eureka para descobrir a localização dos serviços desejados. O servidor Eureka fornece informações sobre os endpoints dos serviços disponíveis.

5. **Balanceamento de Carga**: Além de fornecer informações de localização dos serviços, o servidor Eureka pode ser integrado com balanceadores de carga para distribuir solicitações entre instâncias de um serviço específico.

6. **Resiliência**: O servidor Eureka é projetado para ser altamente resiliente e tolerante a falhas. Ele pode operar em um ambiente distribuído com várias instâncias executando em diferentes locais.

No geral, o servidor Eureka simplifica a comunicação e a interação entre os microservices em uma arquitetura distribuída, facilitando a escalabilidade e a confiabilidade do sistema. Ele desempenha um papel crucial na construção de sistemas baseados em microsserviços, fornecendo uma maneira eficiente de localizar e acessar serviços em um ambiente dinâmico e em constante mudança.

# Arquivo Bootstrap.yaml (eureka-service)

```yaml
spring:
  application:
    name: eureka-service
  cloud:
    config:
      enabled: true
      uri: http://localhost:8081
```

### Arquivo Bootstrap.yaml

O arquivo `bootstrap.yaml` é utilizado em aplicações Spring Boot para configurar o contexto de inicialização antes mesmo da aplicação ser iniciada. Ele é usado principalmente para configurar propriedades de inicialização do ambiente, como a configuração do servidor de configuração na nuvem.

#### Propriedades:

1. **spring.application.name**:
    - **Descrição**: Define o nome da aplicação Spring Boot.
    - **Valor**: "eureka-service"
    - **Uso**: O nome da aplicação é usado para identificar a aplicação em ambientes distribuídos, como quando se registra no serviço de descoberta de serviço (por exemplo, Eureka, Consul).

2. **spring.cloud.config.enabled**:
    - **Descrição**: Habilita ou desabilita a configuração na nuvem para a aplicação Spring Boot.
    - **Valor**: `true`
    - **Uso**: Quando definido como `true`, a aplicação Spring Boot procurará um servidor de configuração na nuvem para buscar suas configurações. Isso é útil em arquiteturas distribuídas onde você deseja manter as configurações centralizadas e atualizáveis.

3. **spring.cloud.config.uri**:
    - **Descrição**: Define a URI do servidor de configuração na nuvem.
    - **Valor**: `http://localhost:8081`
    - **Uso**: Especifica o endpoint onde o servidor de configuração na nuvem está sendo executado. A aplicação Spring Boot usará esse endpoint para recuperar suas configurações.

#### Uso:

O arquivo `bootstrap.yaml` deve ser colocado no diretório `src/main/resources` do projeto Spring Boot. Durante a inicialização da aplicação, o Spring Boot irá ler esse arquivo e configurar o contexto de inicialização de acordo com as propriedades especificadas.

# Dependências necessárias para utilizar Eureka e Server Config

```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2022.0.2</spring-cloud.version>
</properties>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### Propriedades:

1. **java.version**:
    - **Descrição**: Define a versão do Java usada no projeto.
    - **Valor**: 17
    - **Uso**: Especifica a versão do Java que será utilizada durante a compilação e execução do projeto.

2. **spring-cloud.version**:
    - **Descrição**: Define a versão do Spring Cloud usada no projeto.
    - **Valor**: 2022.0.2
    - **Uso**: Define a versão do Spring Cloud que será usada para integrar serviços em nuvem ao projeto.

#### Dependências:

1. **spring-cloud-starter-config**:
    - **Descrição**: Adiciona as dependências necessárias para integrar a configuração distribuída do Spring Cloud.
    - **Uso**: Permite que a aplicação Spring Boot obtenha suas configurações de um servidor de configuração centralizado.

2. **spring-cloud-starter-bootstrap**:
    - **Descrição**: Adiciona as dependências necessárias para inicialização do contexto do Spring Cloud.
    - **Uso**: É utilizado para inicializar a aplicação Spring Boot antes mesmo da fase de inicialização regular do Spring.

3. **spring-cloud-starter-netflix-eureka-server**:
    - **Descrição**: Adiciona as dependências necessárias para criar um servidor Eureka com Spring Cloud Netflix.
    - **Uso**: Permite que a aplicação Spring Boot seja registrada e descoberta em um servidor Eureka.

#### Gerenciamento de Dependências:

1. **spring-cloud-dependencies**:
    - **Descrição**: Define as versões gerenciadas das dependências do Spring Cloud.
    - **Uso**: Gerencia as versões das dependências do Spring Cloud, garantindo compatibilidade entre elas.

# Anotação @EnableEurekaServer

### Documentação da Anotação @EnableEurekaServer

- **Descrição**:
    - `@EnableEurekaServer` é uma anotação de configuração fornecida pelo Spring Cloud Netflix Eureka, um componente do Spring Cloud usado para facilitar a construção de arquiteturas baseadas em microservices. Essa anotação é usada para marcar uma classe de configuração como responsável por iniciar um servidor Eureka.

- **Uso**:
    - A anotação `@EnableEurekaServer` é geralmente usada em conjunto com a anotação `@SpringBootApplication` ou `@Configuration`. Ao ser detectada durante o processo de inicialização da aplicação Spring Boot, essa anotação habilita o servidor Eureka, permitindo que outras aplicações se registrem nele para fins de descoberta de serviço.

- **Funcionalidades**:
    - **Registro de Serviço**: Habilita a capacidade de um aplicativo atuar como um servidor Eureka, permitindo que outros serviços se registrem nele para fins de descoberta.
    - **Descoberta de Serviço**: Fornece uma interface para que outras aplicações possam descobrir e se comunicar com os serviços registrados no servidor Eureka.
    - **Monitoramento de Saúde**: Monitora a saúde dos serviços registrados, removendo automaticamente os serviços que se tornam indisponíveis.

- **Exemplo de Uso**:

```java
package com.microservice.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}
}
```

- **Dependências**:
    - Para usar a anotação `@EnableEurekaServer`, é necessário adicionar a dependência `spring-cloud-starter-netflix-eureka-server` ao arquivo `pom.xml` do projeto.

# Arquivos de configuração dos microservices

### 1. Configuração do Eureka Service (`eureka-service.yml`):

```yaml
server:
  port: 8761
eureka:
  client:
    fetch-registry: false
    register-with-eureka: false
```

- **Descrição**:
    - Este arquivo configura o servidor Eureka que atua como o serviço de descoberta de serviço.
    - Define a porta em que o servidor Eureka será executado e configura o cliente Eureka para não buscar o registro nem se registrar com outros servidores Eureka.

### 2. Configuração do Serviço de Usuário (`usuario-service.yml`):

```yaml
server:
  port: 8001
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      default-zone: http://localhost:8761/eureka
    instance:
      hostname: localhost
```

- **Descrição**:
    - Este arquivo configura o serviço de usuário.
    - Define a porta em que o serviço de usuário será executado.
    - Configura o cliente Eureka para buscar o registro e se registrar com o servidor Eureka em `http://localhost:8761/eureka`.

### 3. Configuração do Serviço de Carro (`carro-service.yml`):

```yaml
server:
  port: 8002
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      default-zone: http://localhost:8761/eureka
    instance:
      hostname: localhost
```

- **Descrição**:
    - Este arquivo configura o serviço de carro.
    - Define a porta em que o serviço de carro será executado.
    - Configura o cliente Eureka para buscar o registro e se registrar com o servidor Eureka em `http://localhost:8761/eureka`.

### 4. Configuração do Serviço de Moto (`moto-service.yml`):

```yaml
server:
  port: 8003
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      default-zone: http://localhost:8761/eureka
    instance:
      hostname: localhost
```

- **Descrição**:
    - Este arquivo configura o serviço de moto.
    - Define a porta em que o serviço de moto será executado.
    - Configura o cliente Eureka para buscar o registro e se registrar com o servidor Eureka em `http://localhost:8761/eureka`.

# Anotação @EnableDiscoveryClient

A anotação `@EnableDiscoveryClient` é usada para habilitar a funcionalidade de descoberta de serviço em uma aplicação Spring Boot quando ela atua como um cliente de um serviço de descoberta, como o Eureka.

- **Descrição**:
    - `@EnableDiscoveryClient` é uma anotação de configuração fornecida pelo Spring Cloud. Ela é usada para marcar uma aplicação Spring Boot como um cliente de serviço de descoberta. Isso permite que a aplicação se registre em um serviço de descoberta (por exemplo, Eureka, Consul, Zookeeper) e descubra outros serviços registrados no mesmo serviço de descoberta.

- **Uso**:
    - A anotação `@EnableDiscoveryClient` deve ser adicionada à classe principal (`@SpringBootApplication`) ou a uma classe de configuração da aplicação Spring Boot. Ao ser detectada durante o processo de inicialização, ela habilita o suporte a descoberta de serviço na aplicação.

- **Funcionalidades**:
    - **Registro no Serviço de Descoberta**: Habilita a capacidade da aplicação de se registrar em um serviço de descoberta, permitindo que outras aplicações possam descobri-la.
    - **Descoberta de Serviço**: Permite que a aplicação descubra e se comunique com outros serviços registrados no mesmo serviço de descoberta.
    - **Balanceamento de Carga**: Alguns serviços de descoberta oferecem funcionalidades de balanceamento de carga, permitindo que as solicitações sejam distribuídas entre várias instâncias de um serviço.

- **Exemplo de Uso**:
```java
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class UsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }

}
```

- **Observações**:
    - A anotação `@EnableDiscoveryClient` é usada quando a aplicação Spring Boot atua como cliente de um serviço de descoberta, como o Eureka. Para o Eureka, a anotação `@EnableEurekaClient` também pode ser usada, pois ela é uma extensão específica do Eureka da anotação `@EnableDiscoveryClient`.

# Autor
## Feito por: `Daniel Penelva de Andrade`