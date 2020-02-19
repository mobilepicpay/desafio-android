# PicPay - Desafio Android

Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema e, por isso, uma das fases da nossa entrevista é implementar melhorias no código deste repositório.

<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>

Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:

- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
- Nossos relatórios de crash tem mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
- Gostariamos de cachear os dados retornados pelo servidor.
- Haverá mudanças na lógica de negócios e gostariamos que a arquitetura reaja bem a isso. Exemplo: TODO
- Haverá mudanças na lógica de apresentação. Gostariamos que a arquitetura reaja bem a isso. Exemplo: TODO
- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais. 
  - Gostariamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiés ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)
