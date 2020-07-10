insert into tb_pauta(
	id_pauta, nm_pauta,	des_pauta,	data_inclusao)
Values(
	1, 'Pauta 1', 'Primeira Pauta', CURRENT_TIMESTAMP());

insert into tb_pauta(
	id_pauta, nm_pauta,	des_pauta,	data_inclusao)
Values(
	2, 'Pauta 2', 'Segunda Pauta', CURRENT_TIMESTAMP());

insert into tb_pauta(
	id_pauta, nm_pauta,	des_pauta,	data_inclusao)
Values(
	3, 'Pauta 3', 'Terceira Pauta', CURRENT_TIMESTAMP());

insert into tb_pauta(
	id_pauta, nm_pauta,	des_pauta,	data_inclusao)
Values(
	4, 'Pauta 4', 'Quarta Pauta', CURRENT_TIMESTAMP());

insert into tb_pauta(
	id_pauta, nm_pauta,	des_pauta,	data_inclusao)
Values(
	8, 'Pauta 8', 'Pauta com votos realizados', CURRENT_TIMESTAMP());

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
10, 2, 'A', 'UM_MES', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
11, 3, 'E', 'UM_DIA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
12, 4, 'A', 'UMA_HORA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
13, 8, 'A', 'MEIA_HORA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into tb_voto(id_voto, cpf, valor, id_sessao) values(1, '26669006063', 'S', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(2, '27227669068', 'S', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(3, '23703563087', 'S', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(4, '49458702097', 'N', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(5, '61727675002', 'N', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(6, '24016215048', 'S', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(7, '16796851048', 'X', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(8, '82576963072', 'S', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(9, '29094846020', 'N', 13);
insert into tb_voto(id_voto, cpf, valor, id_sessao) values(10, '93440946088', 'S', 13);
