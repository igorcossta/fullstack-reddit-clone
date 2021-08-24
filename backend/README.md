# Subreddit (Java)

### TODO

- [X] Melhorar criação de subreddit
    - [X] Impedir que seja criado com o mesmo nome
    - [X] Impedir que seja criado com espaços, caracteres especiais ou números (apenas o nome em camel case)
- [X] Melhorar o retorno de falhas (ser mais específico nos erros)
- [X] Melhorar a criação do banco de dados anotando as classes com as annotations do javax.persistence nas classes Model
- [X] Melhorar as validações dos objetos de transferência (DTO)
- [ ] Verificar se a requisição foi feita por um navegador, caso seja exibir uma pagina html personalizada com o erro
- [ ] Retornar apenas 10 entidades em chamadas GET e com o total de páginas