# Coopera :: gestor-votacao

~~~~Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.

Considerações sobre o projeto:

●	Domínio criado:
    ○	Cadastro de Atas
    ○	Cadastro de Sessões de Votações
    ○	Cadastro de Votos
    ■	Para a votação ocorrer, antes deverá ser definida uma pauta e uma sesseão de votação.
    ■	A sessão de votação é quem controla o tempo de abertura para votação e também agrupa os votos de uma pauta. Nessa solução é possível que uma pauta seja votada novamente em uma outra sessão.

●	Arquitetura:
    ○	Deinição de camadas para a API, Business e Infra, sendo essa ultimo a responsável por encapsular as chamadas a persistência e clients externos.
    ○	Versionamento da API, muda a cada alterações do contrato.
    ○	Repository teve o papel de manipular as entidades entregues por Data Access Objects, que foi o pattern escolhido para a persistência.

●	Melhorias que podem fazer parte do MVP2:
    ○	Criar um processo para finalizar as votações automaticamente
    ○	Informar do fim de uma votação~~~~




