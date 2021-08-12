# PicPay - Desafio Android

<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>

Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:
- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
- Resolver o desafio durante a entrevista, fazendo um pair programming interativo com os nossos devs, guiando o desenvolvimento.

Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:

- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
- Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
- Gostaríamos de cachear os dados retornados pelo servidor.
- Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
- Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais.
  - Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)

##

## Modificações

- Uso do padrão de arquitetura MVVM utilizando os architecture components (ViewModel e LiveData)
- Uso de injeção de dependência com Hilt
- Uso do Repository Pattern tendo o banco de dados como única fonte de verdade
- Uso do Room para o banco de dados
- Uso de Coroutines para as chamadas assíncronas
- Uso de Flow para o streaming de status do consumo de dados
- Uso de ViewBinding para ter acesso às views
- Uso do swipe to refresh para que o usuário possa forçar um update
- Uso do MockK para testes unitários
- Uso de um linter

## Possíveis melhorias futuras

- Modularização
- Paginação
- Navigation component
- Colocar regra de negócio para que não seja feita a request de usuários toda vez que a tela de contatos é aberta
- Github actions para CI e CD
- Mover as dependências para um arquivo gradle separado e organizá-las em grupos
- Suporte Dark mode
- Async DiffUtil