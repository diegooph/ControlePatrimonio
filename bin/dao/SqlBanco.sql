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
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `descricao` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `modelo` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`idCategoria`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nomeUsuario` VARCHAR(200) NULL DEFAULT NULL COMMENT '',
  `permisaoUsuario` INT(11) NULL DEFAULT NULL COMMENT '',
  `senhaUsuario` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `username` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idUsuario`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`local` (
  `idLocal` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nomeLocal` VARCHAR(45) NOT NULL COMMENT '',
  `Usuario_idUsuario` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idLocal`)  COMMENT '',
  INDEX `fk_Local_Usuario1_idx` (`Usuario_idUsuario` ASC)  COMMENT '',
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
  `idPatrimonio` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `nomePatrimonio` VARCHAR(200) NOT NULL COMMENT '',
  `codigo` VARCHAR(45) NOT NULL COMMENT '',
  `detalhamentoTecnico` VARCHAR(200) NULL DEFAULT NULL COMMENT '',
  `Categoria_idCategoria` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idPatrimonio`)  COMMENT '',
  INDEX `fk_Patrimonio_Categoria_idx` (`Categoria_idCategoria` ASC)  COMMENT '',
  CONSTRAINT `fk_Patrimonio_Categoria`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `controlepatrimonio`.`categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`requisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`requisicao` (
  `idRequisicao` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `titulo` VARCHAR(500) NOT NULL COMMENT '',
  `mensagem` VARCHAR(500) NULL DEFAULT NULL COMMENT '',
  `statusrequerimento` INT(11) NOT NULL COMMENT '',
  `tipoRequerimento` INT(11) NOT NULL COMMENT '',
  `dataRequisicao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `dataParecer` DATETIME NULL DEFAULT NULL COMMENT '',
  `dataFinalizacao` DATETIME NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idRequisicao`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`patrimonio_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`patrimonio_has_usuario` (
  `Patrimonio_has_Usuario` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Patrimonio_idPatrimonio` INT(11) NOT NULL COMMENT '',
  `Requisicao_idRequisicao` INT(11) NOT NULL COMMENT '',
  `Usuario_idUsuario` INT(11) NOT NULL COMMENT '',
  `Status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '',
  PRIMARY KEY (`Patrimonio_has_Usuario`, `Patrimonio_idPatrimonio`, `Requisicao_idRequisicao`)  COMMENT '',
  INDEX `fk_Patrimonio_has_Requisicao_Requisicao1_idx` (`Requisicao_idRequisicao` ASC)  COMMENT '',
  INDEX `fk_Patrimonio_has_Requisicao_Patrimonio1_idx` (`Patrimonio_idPatrimonio` ASC)  COMMENT '',
  INDEX `fk_Patrimonio_has_Requisicao_Usuario1_idx` (`Usuario_idUsuario` ASC)  COMMENT '',
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


-- -----------------------------------------------------
-- Table `controlepatrimonio`.`local_has_patrimonio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controlepatrimonio`.`local_has_patrimonio` (
  `local_idLocal` INT(11) NOT NULL COMMENT '',
  `patrimonio_idPatrimonio` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`local_idLocal`, `patrimonio_idPatrimonio`)  COMMENT '',
  INDEX `fk_local_has_patrimonio_patrimonio1_idx` (`patrimonio_idPatrimonio` ASC)  COMMENT '',
  INDEX `fk_local_has_patrimonio_local1_idx` (`local_idLocal` ASC)  COMMENT '',
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

