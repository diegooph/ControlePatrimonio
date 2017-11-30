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
AUTO_INCREMENT = 3
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
AUTO_INCREMENT = 10
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
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`local_has_patrimonio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`local_has_patrimonio` (
  `local_idLocal` INT(11) NOT NULL,
  `patrimonio_idPatrimonio` INT(11) NOT NULL,
  PRIMARY KEY (`local_idLocal`, `patrimonio_idPatrimonio`),
  INDEX `fk_local_has_patrimonio_patrimonio1_idx` (`patrimonio_idPatrimonio` ASC),
  INDEX `fk_local_has_patrimonio_local1_idx` (`local_idLocal` ASC),
  CONSTRAINT `fk_local_has_patrimonio_local1`
    FOREIGN KEY (`local_idLocal`)
    REFERENCES `controlepatrimonio`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_local_has_patrimonio_patrimonio1`
    FOREIGN KEY (`patrimonio_idPatrimonio`)
    REFERENCES `controlepatrimonio`.`patrimonio` (`idPatrimonio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
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
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio_has_usuario` (
  `Patrimonio_has_Usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Patrimonio_idPatrimonio` INT(11) NOT NULL,
  `Requisicao_idRequisicao` INT(11) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
  `Status` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Patrimonio_has_Usuario`, `Patrimonio_idPatrimonio`, `Requisicao_idRequisicao`),
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
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

USE `controlepatrimonio` ;

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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
