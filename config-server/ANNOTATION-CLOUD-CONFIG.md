# Spring Cloud Config

Spring Cloud Config é uma ferramenta que faz parte do ecossistema Spring Cloud, desenvolvido pela Pivotal (agora parte da VMware). Ele oferece um serviço centralizado para gerenciar as configurações de aplicativos distribuídos, permitindo que você mantenha as configurações de vários ambientes (desenvolvimento, teste, produção, etc.) em um único local e gerencie essas configurações de forma dinâmica.

A ideia principal por trás do Spring Cloud Config é evitar a necessidade de incorporar configurações diretamente nos binários de aplicativos. Em vez disso, as configurações são mantidas em um repositório de configuração externo, que pode ser um repositório Git, SVN, Vault, ou qualquer outra fonte suportada pelo Spring Cloud Config.

Existem vários componentes principais no Spring Cloud Config:

1. **Servidor de Configuração (Config Server)**: É o componente central do Spring Cloud Config. Ele é responsável por servir as configurações para os aplicativos que as solicitam. O servidor de configuração é construído com Spring Boot e pode ser implantado como um aplicativo independente.

2. **Repositório de Configuração**: Onde as configurações são armazenadas. Por padrão, o Spring Cloud Config suporta repositórios Git, mas também é extensível para suportar outros tipos de repositórios, como SVN, Vault, etc.

3. **Cliente de Configuração (Config Client)**: É uma biblioteca que os aplicativos usam para se conectarem ao servidor de configuração e obterem suas configurações. Os aplicativos Spring Boot podem facilmente integrar o cliente de configuração, tornando simples a obtenção das configurações do servidor.

Com o Spring Cloud Config, você pode centralizar e externalizar as configurações do seu aplicativo, o que facilita a gestão e a atualização das configurações em vários ambientes. Além disso, ele oferece recursos avançados, como a criptografia de dados sensíveis nas configurações e a capacidade de recarregar as configurações sem a necessidade de reiniciar os aplicativos, o que é muito útil em ambientes de nuvem e de contêineres, onde a escalabilidade e a agilidade são essenciais.


# Arquivo pom.xml para Config Server

Este documento fornece informações sobre o arquivo `pom.xml` com a dependência para o Spring Cloud Config Server.

## Dependência de Gerenciamento

```xml
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

- **Descrição**: Gerencia as dependências do projeto, importando o BOM (Bill of Materials) do Spring Cloud para garantir compatibilidade entre as versões das dependências do Spring Cloud.
- **Grupo**: `org.springframework.cloud`
- **Artifact**: `spring-cloud-dependencies`
- **Versão**: A versão do Spring Cloud será determinada automaticamente pela propriedade `spring-cloud.version`.
- **Tipo**: `pom`
- **Escopo**: `import`

## Dependências

### Spring Cloud Config Server

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

- **Descrição**: Adiciona a dependência para o Spring Cloud Config Server ao projeto.
- **Grupo**: `org.springframework.cloud`
- **Artifact**: `spring-cloud-config-server`
- **Versão**: A versão do Spring Cloud Config Server será determinada automaticamente pela versão definida na propriedade `spring-cloud.version`.

## Propriedades

### Versão do Java

```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2022.0.2</spring-cloud.version>
</properties>
```

- **Descrição**: Define a versão do Java e a versão do Spring Cloud a serem usadas no projeto.
- **`java.version`**: Especifica a versão do Java a ser usada no projeto.
- **`spring-cloud.version`**: Especifica a versão do Spring Cloud a ser usada no projeto.

---

Esta documentação fornece uma visão geral do arquivo `pom.xml` com as dependências e configurações necessárias para o Spring Cloud Config Server, incluindo informações sobre a dependência de gerenciamento e as propriedades definidas.


# Arquivo de configuração `application.yaml` do microservice config-server

Este arquivo de configuração é usado para configurar um servidor Spring Cloud Config.

## Configurações do Servidor

### Porta do Servidor

```yaml
server:
  port: 8081
```

- **Descrição**: Define a porta em que o servidor Spring Cloud Config será executado.
- **Valor padrão**: 8081

## Configurações do Spring Cloud Config

### Configuração do Repositório Git

```yaml
spring:
  cloud:
    config:
      server:
        git:
          default-label: config-server
          uri: https://github.com/Daniel-Penelva/microservico-springboot3
          search-paths: config-data
```

- **Descrição**: Configura as propriedades necessárias para o Spring Cloud Config acessar o repositório Git que contém as configurações.
- **`default-label`**: Define o branch padrão a ser usado quando recuperar as configurações do repositório Git.
- **`uri`**: Especifica o URI do repositório Git onde as configurações estão armazenadas.
- **`search-paths`**: Especifica os caminhos dentro do repositório Git onde as configurações estão localizadas.

## Configurações da Aplicação

### Nome da Aplicação

```yaml
  application:
    name: config-server
```

- **Descrição**: Define o nome da aplicação Spring Boot.
- **Valor padrão**: config-server


# Arquivos YAML para Configurações de Serviços

Este documento fornece informações sobre os arquivos YAML que especificam os caminhos dentro do repositório Git onde as configurações estão localizadas para cada serviço.

## Arquivo carro-server.yaml

```yaml
server:
  port: 8002
```

- **Descrição**: Este arquivo de configuração especifica as configurações para o serviço de servidor de carros.
- **Caminho no Repositório Git**: `config-data/carro-server.yaml`
- **Configurações**:
    - `server.port`: Define a porta em que o serviço de servidor de carros será executado.

## Arquivo moto-service.yaml

```yaml
server:
  port: 8003
```

- **Descrição**: Este arquivo de configuração especifica as configurações para o serviço de motos.
- **Caminho no Repositório Git**: `config-data/moto-service.yaml`
- **Configurações**:
    - `server.port`: Define a porta em que o serviço de motos será executado.

## Arquivo usuario-service.yaml

```yaml
server:
  port: 8001
```

- **Descrição**: Este arquivo de configuração especifica as configurações para o serviço de usuários.
- **Caminho no Repositório Git**: `config-data/usuario-service.yaml`
- **Configurações**:
    - `server.port`: Define a porta em que o serviço de usuários será executado.


# Anotação @EnableConfigServer

A anotação `@EnableConfigServer` é uma anotação especial fornecida pelo Spring Boot e pelo Spring Cloud, usada para configurar um aplicativo Spring Boot como um servidor de configuração. Essa anotação é usada em uma classe de configuração para habilitar e configurar o servidor de configuração do Spring Cloud Config.

```java
package com.microservice.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

## Funcionalidades

### Habilitando o Servidor de Configuração

Ao adicionar a anotação `@EnableConfigServer` a uma classe de configuração Spring Boot, você habilita o servidor de configuração no aplicativo. Isso configura o aplicativo para funcionar como um servidor de configuração capaz de atender às solicitações de configuração de outros aplicativos em um ambiente distribuído.

### Exposição de Endpoints RESTful

O Spring Boot, ao habilitar o servidor de configuração com `@EnableConfigServer`, configura automaticamente os endpoints RESTful necessários para que os clientes possam acessar as configurações do servidor. Isso inclui endpoints para recuperar configurações específicas, atualizações dinâmicas de configurações e outros recursos relacionados à gestão de configurações.

### Integração com Spring Cloud Config

`@EnableConfigServer` é uma parte essencial da integração do Spring Boot com o Spring Cloud Config. Trabalhando em conjunto com outras configurações do Spring Cloud Config, essa anotação fornece um serviço robusto e escalável para a gestão de configurações em ambientes distribuídos.

### Simplificação da Configuração

Adicionando a anotação `@EnableConfigServer` a uma classe de configuração Spring Boot, você pode configurar rapidamente um servidor de configuração sem a necessidade de escrever muitos códigos ou configurar manualmente os endpoints e funcionalidades necessárias.

## Considerações

A anotação `@EnableConfigServer` é fundamental para criar servidores de configuração no Spring Boot, permitindo que você gerencie centralmente e distribua configurações para seus aplicativos em um ambiente distribuído de maneira eficiente e escalável.


# Arquivos bootstrap.yaml para os Serviços

Esta documentação fornece informações sobre os arquivos `bootstrap.yaml` para os serviços de usuário, carro e moto.

## Arquivo bootstrap.yaml do Serviço de Usuário

```yaml
server:
  port: 8001
spring:
  application:
    name: usuario-service
  cloud:
    config:
      enabled: true
      uri: http://localhost:8081

datasource:
  url: jdbc:h2:mem:testUsuariodb
  driver-class-name: org.h2.Driver
  username: sa
  password:
jpa:
  database-platform: org.hibernate.dialect.H2Dialect
  show-sql: true
```

- **Descrição**: Este arquivo de configuração é para o serviço de usuário.
- **Porta do Servidor**: `8001`
- **Nome da Aplicação Spring**: `usuario-service`
- **Spring Cloud Config**: Habilitado para recuperar configurações do servidor de configuração em `http://localhost:8081`.
- **Configurações do Banco de Dados**:
    - **URL**: `jdbc:h2:mem:testUsuariodb`
    - **Driver**: `org.h2.Driver`
    - **Usuário**: `sa`
    - **Senha**: Vazio
- **Configurações do JPA**:
    - **Plataforma do Banco de Dados**: `org.hibernate.dialect.H2Dialect`
    - **Mostrar SQL**: `true`

## Arquivo bootstrap.yaml do Serviço de Carro

```yaml
server:
  port: 8002
spring:
  application:
    name: carro-service
  cloud:
    config:
      enabled: true
      uri: http://localhost:8081

datasource:
  url: jdbc:h2:mem:testCarrodb
  driver-class-name: org.h2.Driver
  username: sa
  password:
jpa:
  database-platform: org.hibernate.dialect.H2Dialect
  show-sql: true
```

- **Descrição**: Este arquivo de configuração é para o serviço de carro.
- **Porta do Servidor**: `8002`
- **Nome da Aplicação Spring**: `carro-service`
- **Spring Cloud Config**: Habilitado para recuperar configurações do servidor de configuração em `http://localhost:8081`.
- **Configurações do Banco de Dados**:
    - **URL**: `jdbc:h2:mem:testCarrodb`
    - **Driver**: `org.h2.Driver`
    - **Usuário**: `sa`
    - **Senha**: Vazio
- **Configurações do JPA**:
    - **Plataforma do Banco de Dados**: `org.hibernate.dialect.H2Dialect`
    - **Mostrar SQL**: `true`

## Arquivo bootstrap.yaml do Serviço de Moto

```yaml
server:
  port: 8003
spring:
  application:
    name: moto-service
  cloud:
    config:
      enabled: true
      uri: http://localhost:8081

datasource:
  url: jdbc:h2:mem:testMotodb
  driver-class-name: org.h2.Driver
  username: sa
  password:
jpa:
  database-platform: org.hibernate.dialect.H2Dialect
  show-sql: true
```

- **Descrição**: Este arquivo de configuração é para o serviço de moto.
- **Porta do Servidor**: `8003`
- **Nome da Aplicação Spring**: `moto-service`
- **Spring Cloud Config**: Habilitado para recuperar configurações do servidor de configuração em `http://localhost:8081`.
- **Configurações do Banco de Dados**:
    - **URL**: `jdbc:h2:mem:testMotodb`
    - **Driver**: `org.h2.Driver`
    - **Usuário**: `sa`
    - **Senha**: Vazio
- **Configurações do JPA**:
    - **Plataforma do Banco de Dados**: `org.hibernate.dialect.H2Dialect`
    - **Mostrar SQL**: `true`

# Arquivo pom.xml para Config Client

Este documento fornece informações sobre o arquivo `pom.xml` com a dependência para o Spring Cloud Config Client para os microservices `usuario-service`, `carro-service` e `moto-service`.

## Propriedades

### Versão do Java e do Spring Cloud

```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2022.0.2</spring-cloud.version>
</properties>
```

- **Descrição**: Define a versão do Java e a versão do Spring Cloud a serem usadas no projeto.
- **`java.version`**: Especifica a versão do Java a ser usada no projeto.
- **`spring-cloud.version`**: Especifica a versão do Spring Cloud a ser usada no projeto.

## Dependências

### Spring Cloud Config Starter

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

- **Descrição**: Adiciona a dependência para o Spring Cloud Config Starter ao projeto, que fornece suporte para configurar o aplicativo como um cliente do Spring Cloud Config.
- **Grupo**: `org.springframework.cloud`
- **Artifact**: `spring-cloud-starter-config`

### Spring Cloud Starter Bootstrap

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

- **Descrição**: Adiciona a dependência para o Spring Cloud Starter Bootstrap ao projeto, que fornece suporte para inicialização do aplicativo e carregamento de configurações de propriedades de inicialização.
- **Grupo**: `org.springframework.cloud`
- **Artifact**: `spring-cloud-starter-bootstrap`

## Dependência de Gerenciamento

### Spring Cloud Dependencies

```xml
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

- **Descrição**: Gerencia as dependências do projeto, importando o BOM (Bill of Materials) do Spring Cloud para garantir compatibilidade entre as versões das dependências do Spring Cloud.
- **Grupo**: `org.springframework.cloud`
- **Artifact**: `spring-cloud-dependencies`
- **Versão**: A versão do Spring Cloud será determinada automaticamente pela propriedade `spring-cloud.version`.
- **Tipo**: `pom`
- **Escopo**: `import`

---

# Autor
## Feito por: `Daniel Penelva de Andrade`