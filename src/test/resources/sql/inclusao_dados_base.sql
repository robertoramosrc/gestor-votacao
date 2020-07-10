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

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
10, 2, 'A', 'UM_MES', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into tb_sessao(
id_sessao, id_pauta, des_situacao, des_duracao, data_abertura, data_encerramento)
values(
11, 3, 'E', 'UM_DIA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
