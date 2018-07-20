O aplicativo desenvolvido utiliza as seguintes tecnologias:

Spring Boot / Spring Data / Swagger Api documentation / Banco MySql

Uma simples DDL está presente internamente no projeto (arquivo schema.sql)
existe uma massa de dados para teste no (arquivo data.sql)

Ao rodar o aplicativo através da execução da classe:

com.cartao.Application

será criada uma tabela chamada db_cartao

no arquivo application.properties está configurado:

spring.datasource.url=jdbc:mysql://localhost:3307/db_cartao
spring.datasource.username=root
spring.datasource.password=usbw

sugestão de ambiente servidor para utilização do banco mysql:

Usb Web Server, disponível para download em:

http://www.usbwebserver.net/downloads/USBWebserver%20v8.6.zip

API

Para acessar a documentação da API, acesse o endereço:
http://localhost:8080/cartao/api/swagger-ui.html

Metodo Save
Utilize o metodo save para realizar um lançamento de transação
URI: http://localhost:8080/cartao/transacao/save
Method: POST
BODY: (um objeto Transacao)
{
 "cartao":14444440000,
 "valor":10.00,
 "data":"16/07/2018 12:15:14"
}


Metodo getByDate
URI: http://localhost:8080/cartao/transacao/getByDate/{dia}/{mes}/{ano}
Method: GET
Response JSON: (Lista de transacoes)
[
    {
        "id": 938,
        "cartao": 1123123123,
        "valor": 10,
        "data": "21/07/2018 11:11:11"
    },
    {
        "id": 939,
        "cartao": 112123,
        "valor": 22,
        "data": "21/07/2018 13:11:11"
    }
]

Caso a resposta seja muito grande, recomendo a utilização do método STREAM 
que garantirá a consulta para milhares de registros sem timeout

Metodo STREAM
URI: http://localhost:8080/cartao/transacao/getByDate/stream/{dia}/{mes}/{ano}
Metodo: GET
Response Text/Plain (Streaming)
0000000444000111 00001100001 18072018
0000000444000111 00001100001 18072018
0000000444000111 00001100001 18072018
[numero do cartao][valor transacao][data ddMMyyyy]
