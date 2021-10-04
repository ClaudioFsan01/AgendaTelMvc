# Trabalho de Banco de Dados AgendaTel

## Banco de dados

O mysql deverá estar rodando com o banco `escola` preparado. O script para criação do banco está em `src/sql/schema.sql`

Há um arquivo para criação de um container docker para subir um banco de dados mysql. Se você já tiver um mysql instalado, basta ajustar a JDBC URL na classe `Banco`.

## Maven

O projeto está preparado para montar um pacote JAR auto contido (com todas as dependências). O comando a seguir montará o arquivo necessário para execução:

```sh
mvn install
```

Em seguida, a partir da raiz do projeto, basta executar:

```sh
java -jar target/agendatel-1.0-SNAPSHOT-jar-with-dependencies.jar
```

> Lembre-se que o mysql deve estar preparado e rodando.

## Compilar e executar na "mão"

```sh
javac --source-path=src -d build src/main/java/br/nom/belo/marcio/agendatel/**/*.java
java -cp build/:lib/mysql-connector-java-8.0.23.jar br.nom.belo.marcio.agendatel.controle.Main
```
