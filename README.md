# PicPay - Desafio Android

## Desafio

- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
- Resolver o desafio durante a entrevista, fazendo um pair programming interativo com os nossos 
devs, guiando o desenvolvimento.

## Problemas

- ESCALABILIDADE
- EXPERIENCIA DO USUARIO

## Alterações

- Utilizei o **coroutines** ao inves do **RxJava**
- Utilizei o **hilt** ao inves do **dagger2**

## Solução

1. -[x] Modular o app (Presentation, Domain, Data).
1.1. [Modulo de apresentacao**(Presentation)**](https://github.com/bruunoh/desafio-android/tree/dev/app), mantive todos os arquivos relacionados a nossa 
    view, como Activity, Fragment, Adapter, ViewModel, removendo toda regra de negocio 
    e configurações de serviços.
1.2. [Modulo de Negocio**(Domain)**](https://github.com/bruunoh/desafio-android/tree/dev/domain), criei um novo modulo para separar as regras da nossa aplicação da nossa view.
1.3. [Modulo de dados**(Data)**](https://github.com/bruunoh/desafio-android/tree/dev/data), criei um novo modulo para ficar responsavel por lidar com os dados que serão
    retornados do serviço
    
```
- Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
- Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
```
2. -[x] Alterar a arquitetura do projeto para o MVVM.
2.1) Optei por utilizar a arquitetura MVVM para usar o ViewModel junto com o Coroutines.
2.2) Foi utilizado
     
3. -[x] Utilizar a lib Room do Jetpack para salvar o cache da aplicação.
```
- Gostaríamos de cachear os dados retornados pelo servidor.
```

4. -[ ] Manter estado da tela com lifecycle da lib jetpack
```
- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos 
que o mesmo fosse mantido.

5. -[ ] Tratar retorno dos servicos
- Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não 
deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
```
    
6. -[ ] Testes
6.1. -[ ] Testes Automatizados
6.2. -[ ] Testes Unitarios
6.3. -[ ] Testes Instrumentados
```
- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes 
automatizados são essenciais.

- Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados 
  independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  
- Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos 
  fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.
  ```