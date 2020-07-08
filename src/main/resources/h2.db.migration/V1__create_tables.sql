create table pauta (
	idPauta				            int		 primary key				not null,
	descricao						varChar(255)						not null,
	dataInclusao					datetime							not null
);

create table assembleia (
	idAssembleia		            int		 primary key				not null,
	idPauta				            int		 							not null,
	descricao						varChar(255)						not null,
	dataAbertura					datetime							not null,
	dataEncerramento				datetime							not null,
	dataInclusao					datetime							not null
);
create nonclustered index [ix_assembleia_pauta]
		on [dbo].assembleia([idPauta] asc);

create table voto (
	idVoto			int		 primary key				not null,
	cpfAssociado	char(11)							not null,
	voto			char(1)								not null
	constraint ckc_voto check (voto like '[N,S]')
);
