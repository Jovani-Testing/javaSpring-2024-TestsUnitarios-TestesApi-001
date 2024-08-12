# javaSpring-2024/001-TestsUnitarios-TestesApi

- Este projeto é uma API de usuários desenvolvida em Java usando Spring Boot. O foco principal é a implementação de testes unitários e de API para garantir a qualidade do código.

- Validações realizadas no controller - No próximo projeto  javaSpring-2024/002-TestsUnitarios-TestesApi faremos as validações de regras de negócio no service.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **JPA/Hibernate**
- **H2 Database (para testes)**
- **JUnit 5** - Para testes unitários
- **Rest Assured** - Para testes de API

  
## Configuração do Projeto

### Pré-requisitos

- **JDK 17+**
- **Maven 3.8+**

### Como Rodar o Projeto

1. **Clone o repositório:**
2. **mvn clean install**
3. **mvn spring-boot:run**

### Exemplo de requisição

Post - http://localhost:8080/users

{
    "name": "Fernando souza",
    "email": "fernando@gmail.com",
    "department": {
        "id": 1
    }
   
}
