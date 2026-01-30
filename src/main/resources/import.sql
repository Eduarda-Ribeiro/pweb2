
insert into produto (imagem, descricao, valor) values ('/imagens/rice.png','Arroz', 43.00);
insert into produto (imagem, descricao, valor) values ('/imagens/milk.png','Leite', 10.00);
insert into produto (imagem, descricao, valor) values ('/imagens/soy.png','Feijao', 23.00);
insert into produto (imagem, descricao, valor) values ('/imagens/corn-flakes.png','Flocão de Milho', 1.50);
insert into produto (imagem, descricao, valor) values ('/imagens/sugar.png','Açúcar', 12.00);
insert into produto (imagem, descricao, valor) values ('/imagens/salt.png','Sal', 7.50);
insert into produto (imagem, descricao, valor) values ('/imagens/coffee-cup.png','Café', 17.00);
insert into produto (imagem, descricao, valor) values ('/imagens/olive-oil.png','Óleo', 8.00);
insert into produto (imagem, descricao, valor) values ('/imagens/popcorn.png','Milho de Pipoca', 2.50);
insert into produto (imagem, descricao, valor) values ('/imagens/pasta.png','Macarrão Espaguete', 5.00);

insert into pessoa_juridica (id, razao_social, cnpj, email, telefone) values (3,'Da Casa Produções', '60.485.235/0001-76', 'maria@gmail', '63984836501');
insert into pessoa_fisica (id, nome, cpf, email, telefone) values (2,'Eduarda', '123456789-10', 'eduarda@gmail', '63984895151');
insert into pessoa_fisica (id, nome, cpf, email, telefone) values (4,'Gustavo', '987654321-90', 'gustavo@gmail', '63992598484');

insert into venda (data, cliente_id) values ('2025-10-10T14:00:00', 1);
insert into venda (data, cliente_id) values ('2025-11-20T10:30:00', 2);
insert into venda (data, cliente_id) values ('2025-10-20T10:30:00', 3);
insert into venda (data, cliente_id) values ('2025-10-20T10:30:00', 3);

insert into item_venda ( quantidade, venda_id, produto_id) values (1, 1, 1);
insert into item_venda (quantidade, venda_id, produto_id) values (2, 1, 3);
insert into item_venda ( quantidade, venda_id, produto_id) values (2, 2, 2);
insert into item_venda ( quantidade, venda_id, produto_id) values (1, 3, 1);
insert into item_venda ( quantidade, venda_id, produto_id) values (2, 3, 2);
insert into item_venda ( quantidade, venda_id, produto_id) values (3, 3, 3);
insert into item_venda ( quantidade, venda_id, produto_id) values (1, 4, 1);