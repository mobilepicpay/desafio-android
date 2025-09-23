# PicPay - Desafio Android

<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>

Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:
- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
- Discutir as possibilidades de solução durante a entrevista, fazendo um pair programming (bate-papo) interativo com os nossos devs.

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

Ps.: Fique à vontade para editar o projeto inteiro, organização de pastas e módulos, bem como as dependências utilizadas


# Refatoração
O app foi completamente refatorado para seguir a arquitetura Clean Architecture + MVVM conforme as recomendações oficiais do time do Android (https://developer.android.com/topic/architecture), implementando as melhores práticas de desenvolvimento Android moderno.
Arquitetura Implementada

# MVVM (Model-View-ViewModel)
View: Activities/Fragments responsáveis apenas pela UI
ViewModel: Gerencia estado da UI, expõe LiveData para observers
Model: Dados e lógica de negócio (Domain + Data layers)

# Clean Architecture - 3 Camadas
## Domain Layer
Regras de negócio, interfaces dos repositórios e entidades do domínio
Model: User, Post - entidades puras do domínio
Repository Interfaces: UserRepository, PostRepository - contratos para acesso a dados
Use Cases: GetUsersUseCase, GetPostsUseCase - encapsulam lógica de negócio

## Data Layer
Implementações concretas de acesso a dados e mapeamento
Models: UserResponse, PostResponse - DTOs da API
Mappers: UserMapper, PostMapper - conversão DTO ↔ Domain
Repositories: UserRepositoryImpl, PostRepositoryImpl - implementações dos repositórios
Services: PicPayService, PostsService - clientes HTTP especializados

## Presentation Layer
UI e gerenciamento de estado visual
Activities/Fragments: MainActivity, UsersFragment, PostsFragment
ViewModels: UsersViewModel, PostsViewModel - estados (Loading/Success/Error)
Adapters/ViewHolders: UserListAdapter, PostListAdapter - exibição de listas
UI Components: RecyclerView com DiffUtil para performance

# Funcionalidades Implementadas
## Multi-API Architecture
PicPay API: https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/
JSONPlaceholder API: https://jsonplaceholder.typicode.com/
Separação clara com módulos Koin dedicados para isolamento

# Interface Moderna
Tabbed Navigation: ViewPager2 + TabLayout (Usuários + Posts)
Progressive UI: Loading states, error handling, empty states
Material Design: Tema consistente e responsivo

# Testes Unitários Completos (usando IA para criar)
PostsViewModelTest: Estados Loading/Success/Error
GetPostsUseCaseTest: Lógica de negócio
PostRepositoryImplTest: Acesso a dados + mapeamento
PostMapperTest: Conversões DTO ↔ Domain
Cobertura: 11 testes passando (100% sucesso)

# Injeção de Dependência
Koin: Módulos organizados por camada
Qualifiers: Separação de instâncias Retrofit (picpay, jsonplaceholder)
Scopes: ViewModels, factories, singletons apropriados

# Performance & Qualidade
Coroutines + LiveData (poderia usar Flow): Programação assíncrona
View Binding: Substituição de findViewById
DiffUtil: Atualizações eficientes de listas
Error Handling: Tratamento robusto de exceções
