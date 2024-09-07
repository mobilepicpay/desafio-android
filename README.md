# 🚀 Desafio PicPay

Bem-vindo ao meu **Desafio PicPay**!

Este repositório contém minha sugetão de solução para o desafio técnico Android proposto pelo PicPay.

> [!IMPORTANT]  
>Esse aplicativo não faz parte de um processo seletivo e foi desenvolvido de livre e espontânea vontade por mim, Wagner Arcieri. Foi inteiramente desenvolvido com Kotlin e Jetpack Compose e segue as melhores práticas de design e desenvolvimento do Android. Contruído com objetivos de estudos pessoais, assim como contribuir com a comunidade, pode ser usado como repositório aberto para consultas, em Português, de quaisquer desenvolvedores que se interessarem. *

> [!NOTE]  
> Esse README é um trabalho contínuo🚧.

## 📜 Descrição original do desafio

***PicPay - Desafio Android***


Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:
-   Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
-   Discutir as possibilidades de solução durante a entrevista, fazendo um pair programming (bate-papo) interativo com os nossos devs.
    Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:
-   Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
-   Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
-   Gostaríamos de cachear os dados retornados pelo servidor.
-   Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
-   Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
-   Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais.
  -   Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  -   Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)

Ps.: Fique à vontade para editar o projeto inteiro, organização de pastas e módulos, bem como as dependências utilizadas

## 📝 Proposta

Diante da liberdade de refatorar o projeto todo à vontade, para garantir a conclusão de projeto dentro de um período relativamente aceitávei, foquei em conter o escopo nos seguintes requisitos:

-   **Preservar o estado da tela**: Garantir que a tela não perca o estado durante mudanças de configuração, como rotação do dispositivo.
-   **Corrigir crashes**: Identificar e corrigir possíveis falhas no aplicativo.
-   **Implementar cache de dados do servidor**: Garantir que os dados recebidos do servidor sejam armazenados em cache, permitindo que o aplicativo funcione offline.
-   **Arquitetura flexível**: Refatorar a arquitetura para que possa facilmente se adaptar a mudanças na lógica de negócio e na apresentação, assim como suportar grandes equipes trabalhando na mesma base de código.


## 📱 Funcionalidades

-   **Suporte Offline-First**: Cache de dados utilizando Room, garantindo a funcionalidade mesmo sem internet.
-   **Processamento de Imagens**: Otimização de armazenamento com compressão de imagens.
-   **Sincronização em Segundo Plano**: Delegada ao WorkManager, mesmo com o aplicativo fechado.
-   **Busca**:  Rápida e eficiente no Banco de Dados interno (ROOM).
-  **EasterEgg**: Há mini-jogo escondido dentro da improvável situação de não haver uma lista de contatos.


## 🔧 Tecnologias Utilizadas

-   **Jetpack Compose**: Para construção de UIs modernas e declarativas.
-   **Koin**: Framework de injeção de dependência leve.
-   **Room**: Banco de dados local para capacidades offline-first.
-   **Retrofit**: Biblioteca de networking para fazer requisições HTTP.
-   **WorkManager**: Para processamento em segundo plano e sincronização de dados.
-   **Kotlin Coroutines**: Para programação assíncrona.
-   **StateFlow**: Para gerenciamento de estado reativo.
-   **DataStore**: Para manipulação de preferências e configurações do app.

## Demonstração

[![](https://img.youtube.com/vi/ZxyjjXZtLII/0.jpg)](https://www.youtube.com/watch?v=ZxyjjXZtLII)

(video hospedado no YouTube, clique para assistir)

## 🛣️ Etapas do projeto

Um resumo cronológico das principais etapas que se desenrolaram no desenvolvimento desse projeto:

🚀 Projeto iniciado  
📈 Upgrade de Gradle e Kotlin e demais dependências.  
💉 Koin configurado para injeção de dependências.  
🏗️ Migração para Arquitetura Multi-Modular.  
🎨 Migração de XML para Jetpack Compose.  
🏛️ Implementado padrões de repositório e 'use cases' com Kotlin Coroutines.  
🌐 Otimização de chamadas do Retrofit e tratamento de erros.  
📶 Implementado suporte offline-first usando Room para cache local.  
🖥️ Melhorado o gerenciamento de estado com AppState customizado e Kotlin Flows.  
🔍 Adicionada funcionalidade de busca.  
🔄 Delegação da sincronização para o WorkManager, para sincronização em segundo plano.  
🖼️ Implementação de compressão das imagens salvas para otimizar o armazenamento.  
🔄 Adicionada sincronização condicional usando “diffing” com “checksums” de resposta da API.  
✅ Adicionados testes unitários e instrumentados.  
💉 Adicionada injeção de dependência com Koin aos testes unitários e instrumentados.  
🎨 Adicionado leves animações à UI.  
🛠️ Testes de qualidade e correção de bugs diversos.  
🎮 Adicionado “EasterEgg”, um mini-game escondido numa condicional improvável.  
🛠️ Código refatorado para melhor organização e separação de responsabilidades.

###  Curiosidades

Durante o desenvolvimento desse projeto, me deparei com um erro em testes instrumentados, utilizando uma "test rule" customizada fornecida pela documentação do Koin, aonde quando se realizava testes em conjutos, os testes subsequestes ao primeiro sempre quebravam.

Não consegui encontrar na internet nenhuma solução já disponível para a questão, somente mais referências de pessoas com o mesmo problema, assim como uma [Issue(#1557)](https://github.com/InsertKoinIO/koin/issues/1557) relativa no repositório do Koin.

Incomodado com a questão, busquei debugar o problema até encontrar uma possível solução.

Eu conto detalhadamente a solução e como cheguei nela nesse [artigo](https://medium.com/@wagnerarcieri/custom-koin-test-rule-instrumented-android-tests-41664eaf50a3).

## Modularização

Esse app foi desenvolvido com uma arquitetura multi-modular.

### Visão geral

Modularização é a prática de dividir o conceito de uma base de código monolítica de um módulo em módulos autocontidos e fracamente acoplados.

### Benefícios da modularização
Podemos citar vários benefícios em se modularizar um aplicativo, entre eles:

* **Escalabilidade** - Em uma base de código fortemente acoplada, uma única alteração pode desencadear uma cascata de alterações. Um projeto devidamente modularizado adotará o princípio da separação de preocupações. Isso, por sua vez, capacita os colaboradores com mais autonomia, ao mesmo tempo em que impõe padrões arquitetônicos.

* **Trabalho em Equipe** - A modularização ajuda a diminuir os conflitos de controle de versão e permite um trabalho mais eficiente em paralelo para desenvolvedores em equipes maiores.

* **Propriedade** - Um módulo pode ter um proprietário ou equipe exclusivamente dedicada e responsável por manter o código e os testes, corrigir bugs e revisar as alterações.

* **Encapsulamento** - O código isolado é mais fácil de ler, entender, testar e manter.

* **Tempo de build reduzido** - Build habilitados com paralelismo do Gradle pode reduzir significativamente os tempos de build, principalmente quando somente um modulo tem alterações e só ele será contruido novamente.

* **Dynamic Delivery** - A modularização é um requisito para o Google [Play Feature Delivery](https://developer.android.com/guide/playcore/feature-delivery?hl=pt-br), que permite que certos recursos de aplicativos sejam entregues condicionalmente ou baixados sob demanda.

* **Reutilização** - A modularização adequada permite oportunidades de compartilhamento de código e construção de vários aplicativos, em diferentes plataformas, a partir da mesma base.

### Possíveis prejuízos da modularização

No entanto, a modularização é um padrão que pode ser mal utilizado, e há algumas pegadinhas que você precisa estar ciente ao modularizar um aplicativo:

**Muitos módulos** - Cada módulo é sobrecarregado com maior complexidade na configuração de build. Isso pode fazer com que os tempos de sincronização do Gradle aumentem e incorra em um custo de manutenção contínuo. Adicionar mais módulos aumenta a complexidade da configuração do Gradle do projeto, quando comparado a um único módulo monolítico.

**Módulos insuficientes** - Por outro lado, se seus módulos forem poucos, grandes e fortemente acoplados, você acabará com outro monólito. Isso significa que você perde alguns benefícios da modularização. Se seu módulo estiver inchado e não tiver um propósito único e bem definido, você deve considerar dividi-lo.

**Muito complexo** - Não há solução mágica aqui. Nem sempre faz sentido modularizar um projeto. Um fator dominante é o tamanho e a complexidade relativa da base de código. Se não espera que seu projeto cresça além de um certo limite, os ganhos de escalabilidade e tempo de construção não serão aplicados.

### Estratégia de modularização
É importante saber que não existe uma estratégia de modularização única que se aplique à todos os projetos. Porém há diretrizes gerais que podem ser seguidas para garantir que se maximize os benefícios e minimize as desvantagens.

Os módulos podem ser construídos e testados de forma independente. Devido à flexibilidade do Gradle, há poucas restrições quanto à forma como se pode organizar um projeto. Em geral, deve se focar em baixo acoplamento e alta coesão.

* **Baixo acoplamento** - Os módulos devem ser o mais independentes possível uns dos outros, para que as alterações em um módulo tenham impacto zero ou mínimo em outros módulos. Eles não devem possuir conhecimento do funcionamento interno de outros módulos.

* **Alta coesão** - Um módulo deve compreender uma coleção de código que atua como um sistema. Ele deve ter responsabilidades claramente definidas e permanecer dentro dos limites de determinado conhecimento de domínio. Por exemplo, o módulo core:network é responsável por fazer solicitações de rede, manipular respostas de uma fonte de dados remota e fornecer dados a outros módulos.

### Módulos

A seguir uma imagem representando os módulos do aplicativo e suas relações de dependência.

![](https://github.com/wagarcdev/wagarcdev/blob/main/blob/picpay/modulos.png)

## Arquitetura
Esse aplicativo segue o guia fornecido pela documentação oficial do Google para Android com práticas e arquiteturas e para a criação de apps robustos com alta qualidade de produção, você pode encontrar mais informações sobre [aqui](https://developer.android.com/topic/architecture?hl=pt-br)

> [!NOTE]  
> A arquitetura oficial do Android é diferente de outras arquiteturas, como a "Clean Architecture". Conceitos de outras arquiteturas podem não se aplicar aqui, ou ser aplicados de maneiras diferentes.

**Quais são os benefícios da orientação oficial do Android em relação à "Clean Architecture"?**

* Menos rígida. Você pode começar apenas com camadas de UI e dados, introduzindo a camada de domínio somente quando for necessário que o aplicativo seja escalado.
* Mais fácil alterar onde os dados são armazenados (por exemplo, movendo de 'preferences' para um banco de dados como 'Room' ou para a nuvem), pois a camada de dados não depende de nenhuma outra camada.

**Quais são os benefícios da "Clean Architecture" em relação à orientação oficial do Android?**

* Mais fácil alterar a lógica de negócios. Encapsulamento claro dessa lógica desde o início. Nenhuma lógica de negócios na UI ou camadas de dados.
* Em aplicativos Android que seguem estritamente a "Clean Architecture", a lógica de negócios geralmente está contida em módulos baseados em bibliotecas JVM "puras" (que não têm dependência do Android). Isso pode ser útil em aplicativos multiplataforma.


As diferenças são destacadas em vermelho abaixo:

![](https://github.com/wagarcdev/wagarcdev/blob/main/blob/picpay/pattern_diff.png)

### Objetivos e requisitos
Os objetivos para a arquitetura do aplicativo são:

* Seguir a orientação oficial de arquitetura o mais próximo possível.

* Fácil para os desenvolvedores entenderem, nada muito experimental.
* Dar suporte à vários desenvolvedores trabalhando na mesma base de código.
* Facilitar testes locais e instrumentados.
* Minimizar os tempos de build.

### Visão geral da Arquitetura
A arquitetura segue um modelo de programação reativa com [fluxo de dados unidirecional](https://developer.android.com/topic/architecture/ui-layer?hl=pt-br#udf). Com a camada de dados na parte inferior, os conceitos-chave são:

* Camadas superiores reagem a mudanças em camadas inferiores.
* Eventos fluem para baixo.
* Dados fluem para cima.

O fluxo de dados é obtido usando [Kotlin Flows](https://developer.android.com/kotlin/flow?hl=pt-br).

#### Exibindo os contatos em ContactsScreen
Quando o aplicativo é executado pela primeira vez, ele tentará carregar uma lista de usuários/contatos da API remota. Uma vez carregada, é exibida ao usuário com base nas ordenações e buscas que ele escolher.

O diagrama a seguir mostra os eventos que ocorrem e como os dados fluem dos objetos relevantes para atingir isso.

![](https://github.com/wagarcdev/wagarcdev/blob/main/blob/picpay/data_flow.png)

### Camada de Dados

A `camada de dados` é implementada como uma fonte "offline-first" de dados de aplicativo e lógica de negócios. É a fonte da verdade ("single source of truth") para todos os dados no aplicativo.

O Repositório é a "API" pública para outras camadas, ele fornece a única maneira de acessar os dados do aplicativo.

Os dados são expostos como fluxos de dados. Isso significa que cada cliente do repositório deve estar preparado para reagir a alterações de dados. Os dados não são expostos como um snapshot (por exemplo, getModel) porque não há garantia de que eles ainda serão válidos no momento em que forem usados.

As leituras são realizadas a partir do armazenamento local como fonte da verdade.

Uma lista de usuários pode ser obtida coletando o fluxo `UsersRepository::getLocalUsers` que emite um `Flow<List<UserModel>>`.

A busca é realizada através do fluxo  `UsersRepository::searchUser` que recebe os parâmetros `searchQuery`, `sortColumn`, `sortOrder`, para a busca por nome/username, ordenar por nome ou username e ordenar de modo ascendente ou descendente, respectivamente e retorna um fluxo com a listra de usuários filtrada pelos resultados, também como um  `Flow<List<UserModel>>`.

Sempre que algum parametro de busca muda (por exemplo, quando o usuário escreve),  a `List<UserModel>` atualizada é emitida no fluxo.

### Sincronização de dados

O repositório é responsáveis ​​por reconciliar dados no armazenamento local com a fonte remota. Uma vez que os dados são obtidos, é realizado uma checagem de "diffing" do tipo "checksum" para constatar se os dados locais precisam ou não serem atualizados, caso precise, é feito o download de cada imagem de usário e realizado um redimensionamento e compressão da mesma para otimizar o espaço de armazenamento e em seguida os dados são imediatamente gravados no armazenamento local. Os dados atualizados são emitidos do armazenamento local (Room) para o fluxo de dados relevante e recebidos por quaisquer clientes ouvintes.

Essa abordagem garante que as preocupações de leitura e gravação do aplicativo sejam separadas e não interfiram umas nas outras.

No caso de erros durante a sincronização de dados, uma estratégia de "backoff" exponencial é empregada. Isso é delegado ao `WorkManager` por meio do `SyncWorker`, uma implementação da interface `Synchronizer`.

Veja o `UsersRepositoryImpl.syncWith` para um exemplo de sincronização de dados.

### Camada de Domíno
A camada de domínio contém `use cases`. Essas são classes que têm um único método invocável (operador fun invoke) contendo lógica de negócios.

Nesse projeto servem apenas de ligação entre a `camada de dados`e a `camada de UI`.

### Camada de UI
A camada de UI compreende:

* Elementos de UI criados usando o Jetpack Compose
* Android ViewModels

As ViewModels recebem fluxos de dados de use cases e do repositório e os transformam em estado de UI. Os elementos de UI refletem esse estado e fornecem maneiras para o usuário interagir com o aplicativo. Essas interações são passadas como eventos para o ViewModel, onde são processadas.

ViewModels recebem fluxos de dados como "cold flows" de um ou mais use cases. Eles são combinados, ou simplesmente mapeados, para produzir um único fluxo("Flow") de estado de UI. Esse único fluxo é então convertido em um "hot flow" usando `stateIn`. A conversão para um "state flow" permite que elementos de UI leiam o último estado conhecido do fluxo.

##  Ambiente de desenvolvimento
### Pré-requisitos

> [!WARNING]
> *(certifique-se de estar usando a versão estável mais recente do Android Studio disponível [aqui](https://developer.android.com/studio))*

-   Kotlin 1.9+
-   Gradle 8.5.2+

### Instalação

Clone o repositório:

    git clone https://github.com/wagarcdev/desafio-android.git


Abra o projeto no Android Studio e sincronize os arquivos do Gradle.

### Executando o Projeto

1.  Conecte um dispositivo Android ou inicie um emulador.
2.  Compile e execute o app usando o Android Studio.

## ✅ Testes

Para facilitar o teste de componentes, o aplicativo usa injeção de dependência com o [Koin](https://insert-koin.io/).

A maioria dos componentes da camada de dados são definidos como interfaces. Então, implementações concretas (com várias dependências) são obrigadas à fornecer essas interfaces para outros componentes no aplicativo. Não foi utilizada nenhuma biblioteca de simulação (mock). Em vez disso, as implementações podem ser substituídas por "dublês" de teste.

Esses dublês de teste implementam a mesma interface que as implementações de produção e geralmente fornecem uma implementação simplificada (mas ainda realista). Isso resulta em testes menos frágeis que podem exercitar mais código de produção, em vez de apenas verificar chamadas específicas em relação a simulações.

Exemplo:

Há implementações de teste do repositório, que implementam a interface normal e completa do repositório e também fornecem alternativas somente para teste. Os testes da ViewModel usam esses repositórios de teste e, portanto, podem usar essas alternativas somente para teste para manipular o estado do repositório de teste e verificar o comportamento resultante, em vez de verificar se métodos específicos do repositório foram chamados.

O projeto inclui uma suíte de testes unitários e instrumentados:

-   **Testes Unitários**: Localizados no diretório `src/test`.
-   **Testes Instrumentados**: Localizados no diretório `src/androidTest`.

Execute os testes unitários usando:

    ./gradlew test

Execute os testes unitários usando:

    ./gradlew connectedAndroidTest` 



## 👨‍💻 Autor

Se quiser bater um papo ou saber mais sobre a minha pessoa, o convite está feito!
Segue abaixo minhas redes!

**Wagner Arcieri**
-  [GitHub](https://github.com/wagarcdev)
-  [LinkedIn](https://www.linkedin.com/in/wagner-arcieri/)
-  [Medium](https://medium.com/@wagnerarcieri/)

## 🤝 Contribuições

Contribuições são bem-vindas!

Por favor, abra uma issue ou envie um pull request.
