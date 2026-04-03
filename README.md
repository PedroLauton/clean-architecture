# Desafio PicPay - Estudo de Clean Architecture

Este projeto foi desenvolvido com o propósito principal de **aplicar, entender e estudar a fundo a Clean Architecture (Arquitetura Limpa)** e os princípios **SOLID** no ecossistema Java com Spring Boot.

Todo o desenvolvimento e estruturação deste projeto foram guiados e inspirados pelos  tutoriais do canal **[Daniel Santos (@DanielSantosDevECC)](https://www.youtube.com/@DanielSantosDevECC)** no YouTube. Todo o crédito da idealização didática pertence a ele. 

O domínio da aplicação resolve o conhecido "Desafio Back-end PicPay", que consiste em uma API RESTful para transferência de dinheiro entre usuários (comuns e lojistas), com validações de regras de negócio, chamadas a serviços externos de autorização e envio de notificações.

---

## 🏗️ A Arquitetura e Estrutura do Projeto

Para garantir um alto nível de desacoplamento e manutenibilidade, o projeto foi estruturado em um formato **Multi-module** utilizando Gradle, dividindo o sistema nas seguintes camadas, respeitando a Regra de Dependência (de fora para dentro):

### 1. `core` (Domínio)
O coração da aplicação. Não possui nenhuma dependência de frameworks externos (nem mesmo o Spring).
* **Contém:** Entidades de domínio ricas (`User`, `Wallet`, `Transaction`, `TaxNumber`), Enums e as Exceções de negócio (`TransferException`, etc).
* **Objetivo:** Isolar as regras de negócio puras e essenciais do sistema.

### 2. `usecase` (Casos de Uso - Interfaces)
Define *o que* a aplicação faz, atuando como portas de entrada para o domínio.
* **Contém:** Interfaces que representam as ações do sistema (ex: `TransferUseCase`, `CreateUserUseCase`).

### 3. `application` (Regras da Aplicação)
Implementa os casos de uso e orquestra o fluxo de dados, comunicando-se com o domínio e definindo os "Gateways" que a infraestrutura deverá implementar.
* **Contém:** Implementações dos Casos de Uso (`TransferUseCaseImpl`) e interfaces de saída/Gateways (`TransferGateway`, `UserNotificationGateway`).
* **Objetivo:** Atuar como a camada de "Interface Adapters" pelo lado do domínio, garantindo o princípio de Inversão de Dependência (DIP do SOLID).

### 4. `infrastructure` (Infraestrutura)
A camada mais externa, onde "a mágica do framework acontece". É a única camada que conhece o Spring Boot, o banco de dados e as APIs externas.
* **Contém:** * **Controllers:** Exposição da API REST (`UserController`, `WalletController`).
  * **Repositories:** Integração com banco de dados via Spring Data JPA e mapeamento ORM (Entidades JPA).
  * **Services/Gateways Impl:** Implementação dos Gateways definidos na camada de aplicação.
  * **Clients:** Integração com APIs externas utilizando **Spring Cloud OpenFeign** (`ApiValidateClient`, `NotificationClient`).
  * **Config:** Configuração de Beans para injeção de dependência manual dos casos de uso puros.
  * **Exceptions:** Tratamento global de erros com `@ControllerAdvice`.

---

## 🚀 Tecnologias Utilizadas

* **Java** * **Spring Boot 3** (Web, Data JPA, Validation)
* **Spring Cloud OpenFeign** (Integração com serviços externos)
* **Gradle** (Build tool e gerenciamento de multi-módulos)
* **Flyway** (Migrations e versionamento de banco de dados)
* **H2 Database** (Banco de dados em memória para testes e desenvolvimento local)
* **MySQL** (Driver configurado para ambiente produtivo)
* **JJWT (Auth0)** (Dependência configurada para futura implementação de segurança)

---

## ⚙️ Como executar o projeto

### Pré-requisitos
* Java JDK 17 ou superior instalado.
* Gradle (opcional, o projeto possui o `gradlew`).

### Passos

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SeuUsuario/curso-arquitetura-limpa-desafio-picpay.git](https://github.com/SeuUsuario/curso-arquitetura-limpa-desafio-picpay.git)
   ```
2. Navegue até a raiz do projeto:
  ```bash
  cd curso-arquitetura-limpa-desafio-picpay
  ```

3. Execute a aplicação (a camada de infraestrutura subirá o Spring Boot):
  ```Bash
  ./gradlew :infrastructure:bootRun
  ```

4. O servidor iniciará na porta padrão 8081 (conforme configurado em application.yml). O banco de dados H2 pode ser acessado em http://localhost:8081/h2-console. As tabelas são criadas automaticamente via Flyway no momento do startup.

---

## 📚 Aprendizados e Padrões Aplicados
- **Clean Architecture & CQS**: Separação clara entre comandos e consultas.
- **SOLID**: Forte ênfase no Princípio de Responsabilidade Única (SRP) e Inversão de Dependência (DIP).
- **Fail-Fast Principle**: Validações de domínio (TaxNumber, TransactionPin) falhando logo no início do fluxo.
- **Padrão Adapter/Gateway**: Protegendo o domínio de detalhes de implementação (banco de dados e APIs externas não vazam para a lógica de negócio).
- **Domain-Driven Exceptions**: Mapeamento padronizado de códigos de erro de negócio (ex: TR0004, ON0001).
