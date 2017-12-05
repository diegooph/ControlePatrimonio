-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema controlepatrimonio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema controlepatrimonio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controlepatrimonio` DEFAULT CHARACTER SET utf8 ;
USE `controlepatrimonio` ;

-- -----------------------------------------------------
-- Table `controlepatrimonio`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeUsuario` VARCHAR(200) NULL DEFAULT NULL,
  `permisaoUsuario` INT(11) NULL DEFAULT NULL,
  `senhaUsuario` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`local` (
  `idLocal` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeLocal` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idLocal`),
  INDEX `fk_Local_Usuario1_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Local_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `controlepatrimonio`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`requisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`requisicao` (
  `idRequisicao` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(500) NOT NULL,
  `mensagem` VARCHAR(500) NULL DEFAULT NULL,
  `statusrequerimento` INT(11) NOT NULL,
  `tipoRequerimento` INT(11) NOT NULL,
  `dataRequisicao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataParecer` DATETIME NULL DEFAULT NULL,
  `dataFinalizacao` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`idRequisicao`))
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`local_has_patrimonio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`local_has_patrimonio` (
  `local_idLocal` INT(11) NOT NULL,
  `requisicao_idRequisicao` INT(11) NOT NULL,
  PRIMARY KEY (`local_idLocal`, `requisicao_idRequisicao`),
  INDEX `fk_local_has_patrimonio_local1_idx` (`local_idLocal` ASC),
  INDEX `fk_local_has_patrimonio_requisicao1_idx` (`requisicao_idRequisicao` ASC),
  CONSTRAINT `fk_local_has_patrimonio_local1`
    FOREIGN KEY (`local_idLocal`)
    REFERENCES `controlepatrimonio`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_local_has_patrimonio_requisicao1`
    FOREIGN KEY (`requisicao_idRequisicao`)
    REFERENCES `controlepatrimonio`.`requisicao` (`idRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio` (
  `idPatrimonio` INT(11) NOT NULL AUTO_INCREMENT,
  `nomePatrimonio` VARCHAR(200) NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  `detalhamentoTecnico` VARCHAR(200) NULL DEFAULT NULL,
  `Categoria_idCategoria` INT(11) NOT NULL,
  PRIMARY KEY (`idPatrimonio`),
  INDEX `fk_Patrimonio_Categoria_idx` (`Categoria_idCategoria` ASC),
  CONSTRAINT `fk_Patrimonio_Categoria`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `controlepatrimonio`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio_has_usuario` (
  `Patrimonio_idPatrimonio` INT(11) NOT NULL,
  `Requisicao_idRequisicao` INT(11) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
  `Status` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Patrimonio_idPatrimonio`, `Requisicao_idRequisicao`),
  INDEX `fk_Patrimonio_has_Requisicao_Requisicao1_idx` (`Requisicao_idRequisicao` ASC),
  INDEX `fk_Patrimonio_has_Requisicao_Patrimonio1_idx` (`Patrimonio_idPatrimonio` ASC),
  INDEX `fk_Patrimonio_has_Requisicao_Usuario1_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Patrimonio_has_Requisicao_Patrimonio1`
    FOREIGN KEY (`Patrimonio_idPatrimonio`)
    REFERENCES `controlepatrimonio`.`patrimonio` (`idPatrimonio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patrimonio_has_Requisicao_Requisicao1`
    FOREIGN KEY (`Requisicao_idRequisicao`)
    REFERENCES `controlepatrimonio`.`requisicao` (`idRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patrimonio_has_Requisicao_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `controlepatrimonio`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `controlepatrimonio` ;

-- -----------------------------------------------------
-- Placeholder table for view `controlepatrimonio`.`listarrelatoriostatico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`listarrelatoriostatico` (`QuantidadePatrimonios` INT, `QuantosUsados` INT);

-- -----------------------------------------------------
-- Placeholder table for view `controlepatrimonio`.`selecionarpatrimoniolocais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`selecionarpatrimoniolocais` (`idPatrimonio` INT, `nomePatrimonio` INT, `codigo` INT, `detalhamentoTecnico` INT, `Categoria_idCategoria` INT, `idCategoria` INT, `descricao` INT, `modelo` INT, `local_idlocal` INT);

-- -----------------------------------------------------
-- procedure RelatorioCategorias
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RelatorioCategorias`(dataInicio date , dataFim date)
BEGIN
SELECT modelo , getQuantidadeSolicitada(idCategoria, dataInicio , dataFim) as QuantidadeSolicitada ,
				GetquantidadeIndeferida(idCategoria, dataInicio , dataFim) as quantidadeIndeferida,
				getQuantidadeDeferidas (idCategoria, dataInicio , dataFim) as QuantidadeDeferida,
                getQuantidadeParaLocais(idCategoria, dataInicio , dataFim) as quantidadeParaLocais
FROM controlepatrimonio.categoria ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure VerificarExclusaoUsuario
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VerificarExclusaoUsuario`(out verificarexclusao int , in idUsuarioEx int )
BEGIN
set verificarexclusao = 0;
if(SELECT count(Usuario_idUsuario)>0 FROM controlepatrimonio.patrimonio_has_usuario where Usuario_idUsuario= idUsuarioEx )or(SELECT count(Usuario_idUsuario)>0 FROM controlepatrimonio.local where Usuario_idUsuario= idUsuarioEx) then
set verificarexclusao = 1;
 end if;
 select verificarexclusao;
 
 
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getQuantidadeDeferidas
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `getQuantidadeDeferidas`(idcategoriainc int,dataInicio date , dataFim date) RETURNS int(11)
BEGIN
set @quantidadeParaLocais = (select count(idpatrimonio) from categoria 
join patrimonio on idcategoria = categoria_idcategoria 
join patrimonio_has_usuario on idpatrimonio = patrimonio_idpatrimonio 
join requisicao on idrequisicao = patrimonio_has_usuario.requisicao_idRequisicao 
where statusrequerimento = 0 and idcategoria = idcategoriainc and datafinalizacao between datainicio and datafim );
RETURN @quantidadeParaLocais;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getQuantidadeIndeferida
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `getQuantidadeIndeferida`(idcategoriainc int,dataInicio date , dataFim date) RETURNS int(11)
BEGIN
set @quantidadeParaLocais = (select count(idpatrimonio) from categoria 
join patrimonio on idcategoria = categoria_idcategoria 
join patrimonio_has_usuario on idpatrimonio = patrimonio_idpatrimonio 
join requisicao on idrequisicao = patrimonio_has_usuario.requisicao_idRequisicao 
where statusrequerimento = 1 and idcategoria = idcategoriainc and datafinalizacao between datainicio and datafim);
RETURN @quantidadeParaLocais;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getQuantidadeParaLocais
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `getQuantidadeParaLocais`(idcategoriaInc int,dataInicio date , dataFim date) RETURNS int(11)
BEGIN
set @quantidadeParaLocais = (select count(idpatrimonio) from categoria 
join patrimonio on idcategoria = categoria_idcategoria 
join patrimonio_has_usuario on idpatrimonio = patrimonio_idpatrimonio 
join requisicao on idrequisicao = patrimonio_has_usuario.requisicao_idRequisicao 
join local_has_patrimonio on local_has_patrimonio.requisicao_idRequisicao = idrequisicao 
where statusrequerimento = 0 and idcategoria = idcategoriainc and datafinalizacao between datainicio and datafim);
RETURN @quantidadeParaLocais
;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getQuantidadeSolicitada
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `getQuantidadeSolicitada`(idCategoriainc int,dataInicio date , dataFim date) RETURNS int(11)
BEGIN
set @quantidadeSolicitada = (select count(idpatrimonio) from categoria 
join patrimonio on idcategoria = categoria_idcategoria 
join patrimonio_has_usuario on idpatrimonio = patrimonio_idpatrimonio 
join requisicao on idrequisicao = requisicao_idrequisicao 
where idcategoria = idcategoriainc and datafinalizacao between datainicio and datafim); 
RETURN @quantidadeSolicitada;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure incert_requisicao_npn
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `incert_requisicao_npn`(in tituloinc varchar(500) , in mensageminc varchar(500) , in statusquerimentoinc int , in tipoRequisicaoinc int , in idLocalinc int ,in idpatrimonioInc int , in idusuarioinc int)
BEGIN
INSERT INTO `controlepatrimonio`.`requisicao` (`titulo`, `mensagem`, `statusrequerimento`, `tipoRequerimento` ) VALUES ( tituloinc , mensageminc  , statusquerimentoinc ,  tipoRequisicaoinc  );
set @idRequisicaoInc =  LAST_INSERT_ID();

INSERT INTO `controlepatrimonio`.`patrimonio_has_usuario`(`Patrimonio_idPatrimonio`,`Requisicao_idRequisicao`,`Usuario_idUsuario`)VALUES(idpatrimonioinc, @idRequisicaoInc , idusuarioinc);

if idlocalinc != 0 then
INSERT INTO `controlepatrimonio`.`local_has_patrimonio`(`local_idLocal`,`Requisicao_idRequisicao`)VALUES(idlocalinc ,@idRequisicaoInc);
end if ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function selecionarusuario
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `selecionarusuario`(idpatrimonio int) RETURNS int(11)
BEGIN
set @id =(SELECT Usuario_idUsuario from patrimonio_has_usuario join requisicao on requisicao.idrequisicao = patrimonio_has_usuario.Requisicao_idRequisicao where statusrequerimento = 0 and dataFinalizacao is null and patrimonio_has_usuario.Patrimonio_idPatrimonio = idpatrimonio order by dataparecer asc limit 1 );

RETURN @id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure upDataFinalizadoRequisisao
-- -----------------------------------------------------

DELIMITER $$
USE `controlepatrimonio`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `upDataFinalizadoRequisisao`(in idrequisicaoinc int)
BEGIN

set @idPatrimonio = (select patrimonio_idpatrimonio from  patrimonio_has_usuario  join requisicao on idrequisicao = requisicao_idrequisicao where idrequisicao = idrequisicaoinc );
set @idrequisicaoReferente = (select idrequisicao from  patrimonio_has_usuario  join requisicao on idrequisicao = requisicao_idrequisicao where tiporequerimento = 1 and datafinalizacao is null and patrimonio_has_usuario.patrimonio_idPatrimonio = @idPatrimonio order by dataparecer limit 1);
UPDATE `controlepatrimonio`.`requisicao` SET `dataFinalizacao` = now() WHERE `idRequisicao` =  @idrequisicaoReferente ;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `controlepatrimonio`.`listarrelatoriostatico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controlepatrimonio`.`listarrelatoriostatico`;
USE `controlepatrimonio`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `controlepatrimonio`.`listarrelatoriostatico` AS select (select count(`controlepatrimonio`.`patrimonio`.`idPatrimonio`) from `controlepatrimonio`.`patrimonio`) AS `QuantidadePatrimonios`,(select count(`controlepatrimonio`.`requisicao`.`idRequisicao`) from `controlepatrimonio`.`requisicao` where ((`controlepatrimonio`.`requisicao`.`statusrequerimento` = 0) and isnull(`controlepatrimonio`.`requisicao`.`dataFinalizacao`))) AS `QuantosUsados`;

-- -----------------------------------------------------
-- View `controlepatrimonio`.`selecionarpatrimoniolocais`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controlepatrimonio`.`selecionarpatrimoniolocais`;
USE `controlepatrimonio`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `controlepatrimonio`.`selecionarpatrimoniolocais` AS select `controlepatrimonio`.`patrimonio`.`idPatrimonio` AS `idPatrimonio`,`controlepatrimonio`.`patrimonio`.`nomePatrimonio` AS `nomePatrimonio`,`controlepatrimonio`.`patrimonio`.`codigo` AS `codigo`,`controlepatrimonio`.`patrimonio`.`detalhamentoTecnico` AS `detalhamentoTecnico`,`controlepatrimonio`.`patrimonio`.`Categoria_idCategoria` AS `Categoria_idCategoria`,`controlepatrimonio`.`categoria`.`idCategoria` AS `idCategoria`,`controlepatrimonio`.`categoria`.`descricao` AS `descricao`,`controlepatrimonio`.`categoria`.`modelo` AS `modelo`,`controlepatrimonio`.`local_has_patrimonio`.`local_idLocal` AS `local_idlocal` from ((((`controlepatrimonio`.`patrimonio` join `controlepatrimonio`.`categoria` on((`controlepatrimonio`.`categoria`.`idCategoria` = `controlepatrimonio`.`patrimonio`.`Categoria_idCategoria`))) join `controlepatrimonio`.`patrimonio_has_usuario` on((`controlepatrimonio`.`patrimonio_has_usuario`.`Patrimonio_idPatrimonio` = `controlepatrimonio`.`patrimonio`.`idPatrimonio`))) join `controlepatrimonio`.`requisicao` on((`controlepatrimonio`.`patrimonio_has_usuario`.`Requisicao_idRequisicao` = `controlepatrimonio`.`requisicao`.`idRequisicao`))) join `controlepatrimonio`.`local_has_patrimonio` on((`controlepatrimonio`.`local_has_patrimonio`.`requisicao_idRequisicao` = `controlepatrimonio`.`requisicao`.`idRequisicao`))) where ((`controlepatrimonio`.`requisicao`.`statusrequerimento` = 0) and isnull(`controlepatrimonio`.`requisicao`.`dataFinalizacao`));
INSERT INTO `controlepatrimonio`.`usuario` (`nomeUsuario`, `permisaoUsuario`, `senhaUsuario`, `username`) VALUES ('administrador', '0', 'admin', 'ADMIN');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
