# CobolSchool 🚀

O **CobolSchool** é uma plataforma de cursos online onde os usuários podem consumir conteúdos ou atuar como instrutores, postando suas próprias aulas. O projeto foi desenvolvido com uma arquitetura robusta no ecossistema Java, garantindo segurança, persistência eficiente de dados e alta produtividade no desenvolvimento.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias e ferramentas:

* **Java 21** (ou a versão que você estiver usando)
* **Spring Boot**: Framework base para a construção da API.
* **Spring Security**: Responsável pela autenticação e autorização dos usuários (ex: perfis de Aluno e Instrutor).
* **PostgreSQL**: Banco de dados relacional para armazenamento de usuários, cursos e aulas.
* **Lombok**: Biblioteca para redução de código boilerplate (getters, setters, construtores).
* **Maven / Gradle**: Gerenciador de dependências (ajuste conforme o seu uso).

---

## 🏗️ Principais Funcionalidades

* **Autenticação & Autorização**: Controle de acesso seguro via Spring Security.
* **Perfil Aluno**: Capacidade de visualizar e assistir às aulas disponíveis.
* **Perfil Instrutor**: Permissão para criar cursos e postar novas aulas.
* **Gestão de Conteúdo**: Cadastro e persistência de cursos e mídias/links das aulas no PostgreSQL.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 21](https://www.oracle.com/java/technologies/downloads/)
* [PostgreSQL](https://www.postgresql.org/download/)
* Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/danielBRTanimacao/CobolSchool.git
   cd CobolSchool
