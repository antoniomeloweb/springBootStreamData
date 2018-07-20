--executo um drop table if exists e depois crio a tabela
--poderia ser diferente, utilizando apenas um create table if not exists
--estou deixando desta forma para que o script inicial possa criar o index 
--sem ocorrer erro durante os testes
DROP TABLE IF EXISTS `transacao`;
--CREATE TABLE IF NOT EXISTS `transacao` (
CREATE TABLE `transacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cartao` bigint(20) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE INDEX indexData ON transacao (data);
