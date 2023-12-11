### Objetivo: Desenvolver o sistema 4ever, utilizando separação das camadas de apresentação, regras de negócio, modelo de negócio e repositório.

<hr>

##### Regras de negócio para os requisitos do sistema:
- [ ] 1. Um evento é identificado por um id sequencial (1,2,3...) que deve ser gerado internamente pelo sistema
- [ ] 2. Um evento pode ter preço zero, mas nunca negativo
- [ ] 3. A data e a descrição do evento são obrigatórias
- [ ] 4. A capacidade do evento deve ser de no mínimo 2 ingressos
- [ ] 5. A quantidade de ingressos de um evento não pode ultrapassar a sua capacidade
- [ ] 6. Pode existir mais de um evento numa mesma data
- [ ] 7. Um evento só pode ser apagado caso não tenha ingresso
- [ ] 8. Um participante é identificado por um cpf. 
- [x] 9. Um participante tem idade calculada pela diferença entre a sua data de nascimento e a data atual
- [ ] 10. Um participante só pode ser apagado caso o seu último ingresso esteja ultrapassado e, neste caso, todos os seus ingressos devem ser apagados.
- [ ] 11. Um ingresso é identificado por um código gerado pelo sistema no formato id + “-“ + cpf
- [ ] 12. Um ingresso possui um telefone de contato obrigatório, que pode ser de qualquer pessoa
- [ ] 13. Um ingresso pode ser apagado
- [ ] 14. O preço do ingresso é o preço do evento menos o desconto que é calculado de acordo com a idade do participante conforme tabela abaixo:

|Idade        |   Desconto               |
|-------------|--------------------------|
| <18         |   10% do preço do evento |
| >=18 e <60  |   Sem desconto           |
| >=60        |   20% do preço do evento |

- [ ] 15. Um convidado de uma empresa acumula o desconto da idade com um desconto de 50% no preço do evento

