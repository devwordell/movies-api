# Movies API
API RESTful que lê um arquivo csv com a lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards e obtém o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.

# Pré-requisito
Java 11.

# Executar o projeto
Dentro da pasta executar o script mvnw ou mvnw.cmd com parâmetros spring-boot:run.
```bash
# Exemplo:
./mvnw spring-boot:run
```
Recurso acessível pela URL:
```bash
GET http://localhost:8080/
```
Para facilitar testes, Swagger disponível:
```bash
http://localhost:8080/swagger-ui/index.html
```

Para passar o arquivo CSV por parâmetro utilizar -Dspring-boot.run.jvmArguments="-Dapplication.movielist=NOME_ARQUIVO.csv"
```bash
# Exemplo:
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dapplication.movielist=new_movielist.csv"
```

# Para testar
Dentro da pasta executar o script mvnw ou mvnw.cmd com parâmetro test.
```bash
# Exemplo:
./mvnw test
```