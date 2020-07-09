create table pauta (
	id_pauta				        int		 primary key				not null,
	nm_pauta						varChar(50)							not null,
	descricao						varChar(255)						not null,
	dataInclusao					datetime							not null
);
