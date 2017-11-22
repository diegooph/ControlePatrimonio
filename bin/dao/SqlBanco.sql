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
AUTO_INCREMENT = 58
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
AUTO_INCREMENT = 8
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
  `ocupado` TINYINT(1) NULL DEFAULT NULL,
  `usuario_idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idPatrimonio`),
  INDEX `fk_Patrimonio_Categoria_idx` (`Categoria_idCategoria` ASC),
  INDEX `fk_patrimonio_usuario1_idx` (`usuario_idUsuario` ASC),
  CONSTRAINT `fk_Patrimonio_Categoria`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `controlepatrimonio`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patrimonio_usuario1`
    FOREIGN KEY (`usuario_idUsuario`)
    REFERENCES `controlepatrimonio`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
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
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio_has_local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio_has_local` (
  `idPatrimonio_has_Local` INT(11) NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT(11) NOT NULL,
  `Local_idLocal` INT(11) NOT NULL,
  `Patrimonio_idPatrimonio` INT(11) NOT NULL,
  `Requisicao_idRequisicao` INT(11) NOT NULL,
  PRIMARY KEY (`idPatrimonio_has_Local`),
  INDEX `fk_Patrimonio_has_Requisicao1_Usuario1_idx` (`Usuario_idUsuario` ASC),
  INDEX `fk_Patrimonio_has_Requisicao1_Local1_idx` (`Local_idLocal` ASC),
  INDEX `fk_Patrimonio_has_Local_Patrimonio1_idx` (`Patrimonio_idPatrimonio` ASC),
  INDEX `fk_Patrimonio_has_Local_Requisicao1_idx` (`Requisicao_idRequisicao` ASC),
  CONSTRAINT `fk_Patrimonio_has_Local_Patrimonio1`
    FOREIGN KEY (`Patrimonio_idPatrimonio`)
    REFERENCES `controlepatrimonio`.`patrimonio` (`idPatrimonio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patrimonio_has_Local_Requisicao1`
    FOREIGN KEY (`Requisicao_idRequisicao`)
    REFERENCES `controlepatrimonio`.`requisicao` (`idRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patrimonio_has_Requisicao1_Local1`
    FOREIGN KEY (`Local_idLocal`)
    REFERENCES `controlepatrimonio`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Patrimonio_has_Requisicao1_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `controlepatrimonio`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio_has_usuario` (
  `Patrimonio_has_Usuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Patrimonio_idPatrimonio` INT(11) NOT NULL,
  `Requisicao_idRequisicao` INT(11) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
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
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
