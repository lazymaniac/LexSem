SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema semantyk
-- -----------------------------------------------------
-- Baza danych chatterbota semantycznego.
DROP SCHEMA IF EXISTS `semantyk` ;
CREATE SCHEMA IF NOT EXISTS `semantyk` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci ;
USE `semantyk` ;

-- -----------------------------------------------------
-- Table `semantyk`.`JEDNOSTKA_WIKI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`JEDNOSTKA_WIKI` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`JEDNOSTKA_WIKI` (
  `ID_JEDNOSTKA` INT NOT NULL AUTO_INCREMENT,
  `NAZWA` VARCHAR(255) NULL,
  `TEMAT` VARCHAR(255) NULL,
  PRIMARY KEY (`ID_JEDNOSTKA`),
  UNIQUE INDEX `ID_word_data_UNIQUE` (`ID_JEDNOSTKA` ASC))
ENGINE = InnoDB
COMMENT = 'Główna tabela bazy danych. Reprezentuje pojedyncze słowo lub zwrot. Łaczy opis gramatyczny z semantycznym.';


-- -----------------------------------------------------
-- Table `semantyk`.`CZESCI_MOWY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`CZESCI_MOWY` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`CZESCI_MOWY` (
  `ID_CZESC_MOWY` INT NOT NULL AUTO_INCREMENT,
  `CZESC_MOWY` VARCHAR(255) NULL DEFAULT 'default',
  `ID_JEDNOSTKA` INT NOT NULL,
  PRIMARY KEY (`ID_CZESC_MOWY`),
  UNIQUE INDEX `ID_CZESC_MOWY_UNIQUE` (`ID_CZESC_MOWY` ASC),
  INDEX `FK_JEDNOSTKA_idx` (`ID_JEDNOSTKA` ASC),
  CONSTRAINT `FK_CZESC_ZNACZ`
    FOREIGN KEY (`ID_JEDNOSTKA`)
    REFERENCES `semantyk`.`JEDNOSTKA_WIKI` (`ID_JEDNOSTKA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela reprezentująca informacje o częściach mowy danej jednostki.'
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `semantyk`.`ZNACZENIA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`ZNACZENIA` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`ZNACZENIA` (
  `ID_ZNACZENIA` INT NOT NULL AUTO_INCREMENT,
  `ZNACZENIE` VARCHAR(2048) NOT NULL,
  `ID_CZESC_MOWY` INT NOT NULL,
  PRIMARY KEY (`ID_ZNACZENIA`),
  UNIQUE INDEX `ID_ZNACZENIA_UNIQUE` (`ID_ZNACZENIA` ASC),
  INDEX `FK_CZESC_MOWY_idx` (`ID_CZESC_MOWY` ASC),
  CONSTRAINT `FK_CZESC_MOWY`
    FOREIGN KEY (`ID_CZESC_MOWY`)
    REFERENCES `semantyk`.`CZESCI_MOWY` (`ID_CZESC_MOWY`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela przechowująca informację o znczeniach przypisanych do częsci mowy danej jednostki.';


-- -----------------------------------------------------
-- Table `semantyk`.`RZECZOWNIK_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`RZECZOWNIK_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`RZECZOWNIK_ODM` (
  `ID_RZECZ_ODM` INT NOT NULL AUTO_INCREMENT,
  `ODMIENIALNY` TINYINT(1) NOT NULL,
  `BRAK_LP` TINYINT(1) NULL,
  `BRAK_LM` TINYINT(1) NULL,
  `RODZAJ` VARCHAR(45) NULL,
  `DRUGI_RODZAJ` VARCHAR(45) NULL,
  `TEMAT` VARCHAR(255) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_RZECZ_ODM`),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  UNIQUE INDEX `ID_RZECZ_ODM_UNIQUE` (`ID_RZECZ_ODM` ASC),
  CONSTRAINT `FK_RZECZ_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana rzeczownika.';


-- -----------------------------------------------------
-- Table `semantyk`.`PRZYMIOTNIK_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`PRZYMIOTNIK_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYMIOTNIK_ODM` (
  `ID_PRZYMIOTNIK_ODM` INT NOT NULL AUTO_INCREMENT,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_PRZYMIOTNIK_ODM`),
  UNIQUE INDEX `ID_PRZYMIOTNIK_ODM_UNIQUE` (`ID_PRZYMIOTNIK_ODM` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_PRZYM_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana przymiotnika.';


-- -----------------------------------------------------
-- Table `semantyk`.`PRZYM_STOPIEN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`PRZYM_STOPIEN` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYM_STOPIEN` (
  `ID_PRZYM_STOP` INT NOT NULL AUTO_INCREMENT,
  `STOPIEN` VARCHAR(45) NOT NULL,
  `ID_PRZYM_ODM` INT NOT NULL,
  PRIMARY KEY (`ID_PRZYM_STOP`),
  INDEX `FK_PRZYM_ODM_idx` (`ID_PRZYM_ODM` ASC),
  UNIQUE INDEX `ID_PRZYM_STOP_UNIQUE` (`ID_PRZYM_STOP` ASC),
  CONSTRAINT `FK_PRZYM_ODM`
    FOREIGN KEY (`ID_PRZYM_ODM`)
    REFERENCES `semantyk`.`PRZYMIOTNIK_ODM` (`ID_PRZYMIOTNIK_ODM`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Zawiera odmiane konkretnego stopnia danego przymiotnika.';


-- -----------------------------------------------------
-- Table `semantyk`.`PRZYPADKI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`PRZYPADKI` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYPADKI` (
  `ID_PRZYPADEK` INT NOT NULL AUTO_INCREMENT,
  `MIANOWNIK` VARCHAR(255) NULL,
  `DOPELNIACZ` VARCHAR(255) NULL,
  `CELOWNIK` VARCHAR(255) NULL,
  `BIERNIK` VARCHAR(255) NULL,
  `NARZEDNIK` VARCHAR(255) NULL,
  `MIEJSCOWNIK` VARCHAR(255) NULL,
  `WOLACZ` VARCHAR(255) NULL,
  `TYP_ODMIANY` VARCHAR(45) NOT NULL,
  `ID_RZECZOWNIK_ODM` INT NULL,
  `ID_PRZYM_ODM` INT NULL,
  PRIMARY KEY (`ID_PRZYPADEK`),
  UNIQUE INDEX `ID_PRZYPADEK_UNIQUE` (`ID_PRZYPADEK` ASC),
  INDEX `FK_RZECZ_ODM_idx` (`ID_RZECZOWNIK_ODM` ASC),
  INDEX `FK_PRZYM_STOP_idx` (`ID_PRZYM_ODM` ASC),
  CONSTRAINT `FK_RZECZ_ODM`
    FOREIGN KEY (`ID_RZECZOWNIK_ODM`)
    REFERENCES `semantyk`.`RZECZOWNIK_ODM` (`ID_RZECZ_ODM`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRZYM_STOP`
    FOREIGN KEY (`ID_PRZYM_ODM`)
    REFERENCES `semantyk`.`PRZYM_STOPIEN` (`ID_PRZYM_STOP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana przez przypadki.';


-- -----------------------------------------------------
-- Table `semantyk`.`ZAIMEK_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`ZAIMEK_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`ZAIMEK_ODM` (
  `ID_ZAIMEK_ODM` INT NOT NULL AUTO_INCREMENT,
  `ID_ZNACZENIE` INT NOT NULL,
  `ID_ODM` INT NOT NULL,
  PRIMARY KEY (`ID_ZAIMEK_ODM`),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  INDEX `FK_ZAIM_ODM_idx` (`ID_ODM` ASC),
  UNIQUE INDEX `ID_ZAIMEK_ODM_UNIQUE` (`ID_ZAIMEK_ODM` ASC),
  CONSTRAINT `FK_ZAIM_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ZAIM_ODM`
    FOREIGN KEY (`ID_ODM`)
    REFERENCES `semantyk`.`PRZYPADKI` (`ID_PRZYPADEK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana';


-- -----------------------------------------------------
-- Table `semantyk`.`SYNSET`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`SYNSET` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`SYNSET` (
  `ID_SYNSET` INT NOT NULL,
  `PODZIELNY` INT NULL,
  `ABSTRACT` TINYINT(1) NULL,
  PRIMARY KEY (`ID_SYNSET`),
  UNIQUE INDEX `ID_SYNSET_UNIQUE` (`ID_SYNSET` ASC))
ENGINE = InnoDB
COMMENT = 'relacje syntaktyczne';


-- -----------------------------------------------------
-- Table `semantyk`.`TYP_RELACJI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`TYP_RELACJI` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`TYP_RELACJI` (
  `ID_TYP_RELACJI` INT NOT NULL,
  `TYP` VARCHAR(45) NULL,
  `RODZIC` INT NULL,
  `NAZWA` VARCHAR(255) NULL,
  `OPIS` VARCHAR(2048) NULL,
  `POSSTR` VARCHAR(255) NULL,
  `WYSWIETL` VARCHAR(255) NULL,
  `SKROT` VARCHAR(255) NULL,
  `AUTOODWRACANIE` TINYINT(1) NULL,
  PRIMARY KEY (`ID_TYP_RELACJI`),
  UNIQUE INDEX `ID_typ_relacji_UNIQUE` (`ID_TYP_RELACJI` ASC))
ENGINE = InnoDB
COMMENT = 'relacje syntaktyczne';


-- -----------------------------------------------------
-- Table `semantyk`.`RELACJA_SYNSETU`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`RELACJA_SYNSETU` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`RELACJA_SYNSETU` (
  `RODZIC` INT NOT NULL,
  `DZIECKO` INT NOT NULL,
  `RELACJA` INT NOT NULL,
  `SPRAWDZONY` TINYINT(1) NULL,
  PRIMARY KEY (`RODZIC`, `DZIECKO`, `RELACJA`),
  INDEX `FK_SYN_RODZIC` (`RODZIC` ASC),
  INDEX `FK_SYN_DZIECKO` (`DZIECKO` ASC),
  INDEX `FK_SYN_TYP_RELACJI` (`RELACJA` ASC),
  CONSTRAINT `FK_SYN_RODZIC`
    FOREIGN KEY (`RODZIC`)
    REFERENCES `semantyk`.`SYNSET` (`ID_SYNSET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SYN_DZIECKO`
    FOREIGN KEY (`DZIECKO`)
    REFERENCES `semantyk`.`SYNSET` (`ID_SYNSET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SYN_TYP_RELACJI`
    FOREIGN KEY (`RELACJA`)
    REFERENCES `semantyk`.`TYP_RELACJI` (`ID_TYP_RELACJI`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'relacje syntaktyczne';


-- -----------------------------------------------------
-- Table `semantyk`.`PRZYSLOWEK_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`PRZYSLOWEK_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYSLOWEK_ODM` (
  `ID_PRZYS_ODM` INT NOT NULL AUTO_INCREMENT,
  `STOP_WYZSZY` VARCHAR(255) NULL,
  `STOP_NAJWYZSZY` VARCHAR(255) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_PRZYS_ODM`),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  UNIQUE INDEX `ID_PRZYS_ODM_UNIQUE` (`ID_PRZYS_ODM` ASC),
  CONSTRAINT `FK_PRZYS_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana przysłówka';


-- -----------------------------------------------------
-- Table `semantyk`.`CZASOWNIK_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`CZASOWNIK_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`CZASOWNIK_ODM` (
  `ID_CZASOWNIK_ODM` INT NOT NULL AUTO_INCREMENT,
  `TEMAT` VARCHAR(255) NULL,
  `IS_DOKONANY` TINYINT(1) NULL,
  `KONIUGACJA` VARCHAR(45) NULL,
  `BEZOKOLICZNIK` VARCHAR(255) NULL,
  `FORM_BEZOSOB_CZAS_PRZESZ` VARCHAR(255) NULL,
  `IMIES_PRZYS_WSPOL` VARCHAR(255) NULL,
  `IMIES_PRZYS_UPRZED` VARCHAR(255) NULL,
  `RZECZ_ODCZAS` VARCHAR(255) NULL,
  `ZWROTNY` TINYINT(1) NULL,
  `ZAIMEK_ZWROTNY` VARCHAR(45) NULL,
  `DOKONANY` VARCHAR(255) NULL,
  `NIE_DOKONANY` VARCHAR(255) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_CZASOWNIK_ODM`),
  UNIQUE INDEX `ID_czasownik_odm_UNIQUE` (`ID_CZASOWNIK_ODM` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_CZAS_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Odmiana czasowników.';


-- -----------------------------------------------------
-- Table `semantyk`.`OSOBY_ODM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`OSOBY_ODM` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`OSOBY_ODM` (
  `ID_OSOBY_ODM` INT NOT NULL AUTO_INCREMENT,
  `OS_1_LP` VARCHAR(255) NULL,
  `OS_2_LP` VARCHAR(255) NULL,
  `OS_3_LP` VARCHAR(255) NULL,
  `OS_1_LM` VARCHAR(255) NULL,
  `OS_2_LM` VARCHAR(255) NULL,
  `OS_3_LM` VARCHAR(255) NULL,
  `TYP_ODMIANY` VARCHAR(45) NOT NULL,
  `ID_CZASOWNIK_ODM` INT NOT NULL,
  PRIMARY KEY (`ID_OSOBY_ODM`),
  INDEX `FK_CZAS_ODM_idx` (`ID_CZASOWNIK_ODM` ASC),
  UNIQUE INDEX `ID_OSOBY_ODM_UNIQUE` (`ID_OSOBY_ODM` ASC),
  CONSTRAINT `FK_CZAS_ODM`
    FOREIGN KEY (`ID_CZASOWNIK_ODM`)
    REFERENCES `semantyk`.`CZASOWNIK_ODM` (`ID_CZASOWNIK_ODM`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera odmiane przez osoby.w';


-- -----------------------------------------------------
-- Table `semantyk`.`JEDNOSTKA_WN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`JEDNOSTKA_WN` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`JEDNOSTKA_WN` (
  `ID_JEDN_WN` INT NOT NULL,
  `NAZWA` VARCHAR(255) NOT NULL,
  `POZYCJA` VARCHAR(255) NOT NULL,
  `DOMENA` VARCHAR(255) NOT NULL,
  `WARIANT` INT NOT NULL,
  `ID_JEDN_WIKI` INT NULL,
  PRIMARY KEY (`ID_JEDN_WN`),
  UNIQUE INDEX `ID_JEDN_WN_UNIQUE` (`ID_JEDN_WN` ASC),
  INDEX `FK_JEDN_WIKI_idx` (`ID_JEDN_WIKI` ASC),
  CONSTRAINT `FK_JEDN_WIKI`
    FOREIGN KEY (`ID_JEDN_WIKI`)
    REFERENCES `semantyk`.`JEDNOSTKA_WIKI` (`ID_JEDNOSTKA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `semantyk`.`RELACJA_LEKS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`RELACJA_LEKS` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`RELACJA_LEKS` (
  `RODZIC` INT NOT NULL,
  `DZIECKO` INT NOT NULL,
  `RELACJA` INT NOT NULL,
  PRIMARY KEY (`RODZIC`, `DZIECKO`, `RELACJA`),
  INDEX `FK_LEKS_RELACJA` (`RELACJA` ASC),
  INDEX `FK_LEKS_DZIECKO_idx` (`DZIECKO` ASC),
  CONSTRAINT `FK_LEKS_RELACJA`
    FOREIGN KEY (`RELACJA`)
    REFERENCES `semantyk`.`TYP_RELACJI` (`ID_TYP_RELACJI`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LEKS_RODZIC`
    FOREIGN KEY (`RODZIC`)
    REFERENCES `semantyk`.`JEDNOSTKA_WN` (`ID_JEDN_WN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_LEKS_DZIECKO`
    FOREIGN KEY (`DZIECKO`)
    REFERENCES `semantyk`.`JEDNOSTKA_WN` (`ID_JEDN_WN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'relacje syntaktyczne';


-- -----------------------------------------------------
-- Table `semantyk`.`ANTONIMY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`ANTONIMY` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`ANTONIMY` (
  `ID_ANTONIM` INT NOT NULL AUTO_INCREMENT,
  `ANTONIM` VARCHAR(2048) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_ANTONIM`),
  CONSTRAINT `FK_ANTO_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera informacje o antonimach danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`FRAZEOLOGIE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`FRAZEOLOGIE` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`FRAZEOLOGIE` (
  `ID_FRAZOLOGIA` INT NOT NULL AUTO_INCREMENT,
  `FRAZEOLOGIA` VARCHAR(4096) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_FRAZOLOGIA`),
  UNIQUE INDEX `ID_FRAZOLOGIA_UNIQUE` (`ID_FRAZOLOGIA` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_FRAZ_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera informacja o frazeologiach danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`KOLOKACJE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`KOLOKACJE` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`KOLOKACJE` (
  `ID_KOLOKACJA` INT NOT NULL AUTO_INCREMENT,
  `KOLOKACJA` VARCHAR(2048) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_KOLOKACJA`),
  UNIQUE INDEX `ID_KOLOKACJA_UNIQUE` (`ID_KOLOKACJA` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_KOL_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera kolokacje danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`POKREWNE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`POKREWNE` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`POKREWNE` (
  `ID_POKREWNY` INT NOT NULL AUTO_INCREMENT,
  `POKREWNY` VARCHAR(510) NULL,
  `CZESC_MOWY` VARCHAR(255) NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  `POKREWNEcol` VARCHAR(45) NULL,
  PRIMARY KEY (`ID_POKREWNY`),
  UNIQUE INDEX `ID_POKREWNY_UNIQUE` (`ID_POKREWNY` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_POK_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera informajcę o wyrazach pokrewnych danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`PRZYKLADY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`PRZYKLADY` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYKLADY` (
  `ID_PRZYKLAD` INT NOT NULL AUTO_INCREMENT,
  `PRZYKLAD` VARCHAR(2048) NOT NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_PRZYKLAD`),
  UNIQUE INDEX `ID_PRZYKLAD_UNIQUE` (`ID_PRZYKLAD` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_PRZYK_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera przykłądy wykorzystania danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`SYNONIMY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`SYNONIMY` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`SYNONIMY` (
  `ID_SYNONIM` INT NOT NULL AUTO_INCREMENT,
  `SYNONIM` VARCHAR(2048) NOT NULL,
  `ID_ZNACZENIE` INT NOT NULL,
  PRIMARY KEY (`ID_SYNONIM`),
  UNIQUE INDEX `ID_SYNONIM_UNIQUE` (`ID_SYNONIM` ASC),
  INDEX `FK_ZNACZENIE_idx` (`ID_ZNACZENIE` ASC),
  CONSTRAINT `FK_SYNO_ZNACZ`
    FOREIGN KEY (`ID_ZNACZENIE`)
    REFERENCES `semantyk`.`ZNACZENIA` (`ID_ZNACZENIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tabela zawiera informację o synonimach danego słowa.';


-- -----------------------------------------------------
-- Table `semantyk`.`RAW_WIKI_UNIT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`RAW_WIKI_UNIT` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`RAW_WIKI_UNIT` (
  `ID_RAW_UNIT` INT NOT NULL AUTO_INCREMENT,
  `NAZWA` VARCHAR(255) NOT NULL,
  `BODY` VARCHAR(4000) NOT NULL,
  PRIMARY KEY (`ID_RAW_UNIT`),
  UNIQUE INDEX `ID_RAW_UNIT_UNIQUE` (`ID_RAW_UNIT` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `semantyk`.`JEDN_WN_SYN_REL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `semantyk`.`JEDN_WN_SYN_REL` ;

CREATE TABLE IF NOT EXISTS `semantyk`.`JEDN_WN_SYN_REL` (
  `ID_JEDN_WN` INT NOT NULL,
  `ID_SYNSET` INT NOT NULL,
  PRIMARY KEY (`ID_JEDN_WN`, `ID_SYNSET`),
  INDEX `FK_SYN_REL_idx` (`ID_SYNSET` ASC),
  CONSTRAINT `FK_JEDN_WN_REL`
    FOREIGN KEY (`ID_JEDN_WN`)
    REFERENCES `semantyk`.`JEDNOSTKA_WN` (`ID_JEDN_WN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SYN_REL`
    FOREIGN KEY (`ID_SYNSET`)
    REFERENCES `semantyk`.`SYNSET` (`ID_SYNSET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `semantyk` ;

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`ZNACZENIE_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`ZNACZENIE_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `ZNACZENIE` INT, `CZESC_MOWY` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`SYNONIM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`SYNONIM_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `SYNONIM` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`ANTONIM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`ANTONIM_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `ANTONIM` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`PRZYKLAD_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYKLAD_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `PRZYKLAD` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`FRAZEOLOGIA_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`FRAZEOLOGIA_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `FRAZEOLOGIA` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`KOLOKACJA_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`KOLOKACJA_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `KOLOKACJA` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`POKREWNY_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`POKREWNY_VIEW` (`ID_JEDNOSTKA` INT, `nazwa` INT, `CZESC_MOWY` INT, `POKREWNY` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`RZECZ_ODM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`RZECZ_ODM_VIEW` (`ID_JEDNOSTKA` INT, `NAZWA` INT, `CZESC_MOWY` INT, `ID_RZECZ_ODM` INT, `BRAK_LM` INT, `BRAK_LP` INT, `RODZAJ` INT, `DRUGI_RODZAJ` INT, `ODMIENIALNY` INT, `TEMAT` INT, `ID_PRZYPADEK` INT, `TYP_ODMIANY` INT, `MIANOWNIK` INT, `BIERNIK` INT, `CELOWNIK` INT, `DOPELNIACZ` INT, `MIEJSCOWNIK` INT, `NARZEDNIK` INT, `WOLACZ` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`ZAIMEK_ODM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`ZAIMEK_ODM_VIEW` (`ID_JEDNOSTKA` INT, `NAZWA` INT, `CZESC_MOWY` INT, `ID_PRZYPADEK` INT, `TYP_ODMIANY` INT, `MIANOWNIK` INT, `BIERNIK` INT, `CELOWNIK` INT, `DOPELNIACZ` INT, `MIEJSCOWNIK` INT, `NARZEDNIK` INT, `WOLACZ` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`PRZYM_ODM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYM_ODM_VIEW` (`ID_JEDNOSTKA` INT, `NAZWA` INT, `CZESC_MOWY` INT, `STOPIEN` INT, `ID_PRZYPADEK` INT, `TYP_ODMIANY` INT, `MIANOWNIK` INT, `BIERNIK` INT, `CELOWNIK` INT, `DOPELNIACZ` INT, `MIEJSCOWNIK` INT, `NARZEDNIK` INT, `WOLACZ` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`CZAS_ODM_VIWE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`CZAS_ODM_VIWE` (`ID_JEDNOSTKA` INT, `NAZWA` INT, `CZESC_MOWY` INT, `STOPIEN` INT, `ID_PRZYPADEK` INT, `TYP_ODMIANY` INT, `MIANOWNIK` INT, `BIERNIK` INT, `CELOWNIK` INT, `DOPELNIACZ` INT, `MIEJSCOWNIK` INT, `NARZEDNIK` INT, `WOLACZ` INT);

-- -----------------------------------------------------
-- Placeholder table for view `semantyk`.`PRZYS_ODM_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `semantyk`.`PRZYS_ODM_VIEW` (`ID_JEDNOSTKA` INT, `NAZWA` INT, `CZESC_MOWY` INT, `STOP_WYZSZY` INT, `STOP_NAJWYZSZY` INT);

-- -----------------------------------------------------
-- View `semantyk`.`ZNACZENIE_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`ZNACZENIE_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`ZNACZENIE_VIEW`;
USE `semantyk`;
CREATE OR replace view ZNACZENIE_VIEW AS 
    SELECT 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        ZNACZENIA.ZNACZENIE,
        CZESCI_MOWY.CZESC_MOWY
    FROM
        JEDNOSTKA_WIKI wiki
            LEFT JOIN
        CZESCI_MOWY ON (CZESCI_MOWY.id_jednostka = wiki.ID_JEDNOSTKA)
            LEFT JOIN
        ZNACZENIA ON (ZNACZENIA.ID_CZESC_MOWY = CZESCI_MOWY.id_czesc_mowy)
    group BY ZNACZENIA.znaczenie;



-- -----------------------------------------------------
-- View `semantyk`.`SYNONIM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`SYNONIM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`SYNONIM_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW SYNONIM_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        syn.SYNONIM
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        SYNONIMY syn ON (syn.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY syn.SYNONIM;

-- -----------------------------------------------------
-- View `semantyk`.`ANTONIM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`ANTONIM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`ANTONIM_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW ANTONIM_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        ant.ANTONIM
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        ANTONIMY ant ON (ant.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY ant.ANTONIM;

-- -----------------------------------------------------
-- View `semantyk`.`PRZYKLAD_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`PRZYKLAD_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`PRZYKLAD_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW PRZYKLAD_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        przy.PRZYKLAD
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        PRZYKLADY przy ON (przy.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY przy.PRZYKLAD;

-- -----------------------------------------------------
-- View `semantyk`.`FRAZEOLOGIA_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`FRAZEOLOGIA_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`FRAZEOLOGIA_VIEW`;
USE `semantyk`;
CREATE  OR REPLACE VIEW FRAZEOLOGIA_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        fraz.FRAZEOLOGIA
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        FRAZEOLOGIE fraz ON (fraz.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY fraz.FRAZEOLOGIA
;

-- -----------------------------------------------------
-- View `semantyk`.`KOLOKACJA_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`KOLOKACJA_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`KOLOKACJA_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW KOLOKACJA_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        kol.KOLOKACJA
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        KOLOKACJE kol ON (kol.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY kol.KOLOKACJA;

-- -----------------------------------------------------
-- View `semantyk`.`POKREWNY_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`POKREWNY_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`POKREWNY_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW POKREWNY_VIEW AS
    select 
        wiki.ID_JEDNOSTKA,
        wiki.nazwa,
        cz_mo.CZESC_MOWY,
        pok.POKREWNY
    from
        JEDNOSTKA_WIKI wiki
            left join
        CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
            left join
        ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
            JOIN
        POKREWNE pok ON (pok.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
    GROUP BY pok.POKREWNY
;

-- -----------------------------------------------------
-- View `semantyk`.`RZECZ_ODM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`RZECZ_ODM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`RZECZ_ODM_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW RZECZ_ODM_VIEW AS 
select 
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    rz_odm.ID_RZECZ_ODM,
    rz_odm.BRAK_LM,
    rz_odm.BRAK_LP,
    rz_odm.RODZAJ,
    rz_odm.DRUGI_RODZAJ,
    rz_odm.ODMIENIALNY,
    rz_odm.TEMAT,
    przyp.ID_PRZYPADEK,
    przyp.TYP_ODMIANY,
    przyp.MIANOWNIK,
    przyp.BIERNIK,
    przyp.CELOWNIK,
    przyp.DOPELNIACZ,
    przyp.MIEJSCOWNIK,
    przyp.NARZEDNIK,
    przyp.WOLACZ
from
    JEDNOSTKA_WIKI wiki
        join
    CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
        join
    ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
        join
    RZECZOWNIK_ODM rz_odm ON znacz.ID_ZNACZENIA = rz_odm.ID_ZNACZENIE
        join
    PRZYPADKI przyp ON rz_odm.ID_RZECZ_ODM = przyp.ID_RZECZOWNIK_ODM;


-- -----------------------------------------------------
-- View `semantyk`.`ZAIMEK_ODM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`ZAIMEK_ODM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`ZAIMEK_ODM_VIEW`;
USE `semantyk`;
CREATE  OR REPLACE VIEW ZAIMEK_ODM_VIEW AS
select 
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    przyp.ID_PRZYPADEK,
    przyp.TYP_ODMIANY,
    przyp.MIANOWNIK,
    przyp.BIERNIK,
    przyp.CELOWNIK,
    przyp.DOPELNIACZ,
    przyp.MIEJSCOWNIK,
    przyp.NARZEDNIK,
    przyp.WOLACZ
from
    JEDNOSTKA_WIKI wiki
        join
    CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
        join
    ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
        join
    ZAIMEK_ODM za_odm ON znacz.ID_ZNACZENIA = za_odm.ID_ZNACZENIE
        join
    PRZYPADKI przyp ON za_odm.ID_ODM = przyp.ID_PRZYPADEK;


-- -----------------------------------------------------
-- View `semantyk`.`PRZYM_ODM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`PRZYM_ODM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`PRZYM_ODM_VIEW`;
USE `semantyk`;
CREATE OR REPLACE VIEW PRZYM_ODM_VIEW AS
select 
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    prz_stop.STOPIEN,
    przyp.ID_PRZYPADEK,
    przyp.TYP_ODMIANY,
    przyp.MIANOWNIK,
    przyp.BIERNIK,
    przyp.CELOWNIK,
    przyp.DOPELNIACZ,
    przyp.MIEJSCOWNIK,
    przyp.NARZEDNIK,
    przyp.WOLACZ
from
    JEDNOSTKA_WIKI wiki
        join
    CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
        join
	ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
        join
    PRZYMIOTNIK_ODM prz_odm ON znacz.ID_ZNACZENIA = prz_odm.ID_ZNACZENIE
        join
    PRZYM_STOPIEN prz_stop ON prz_odm.ID_PRZYMIOTNIK_ODM = prz_stop.ID_PRZYM_ODM
        join
    PRZYPADKI przyp ON prz_stop.ID_PRZYM_STOP = przyp.ID_PRZYM_ODM;


-- -----------------------------------------------------
-- View `semantyk`.`CZAS_ODM_VIWE`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`CZAS_ODM_VIWE` ;
DROP TABLE IF EXISTS `semantyk`.`CZAS_ODM_VIWE`;
USE `semantyk`;
CREATE OR REPLACE VIEW CZAS_ODM_VIWE AS
select 
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    prz_stop.STOPIEN,
    przyp.ID_PRZYPADEK,
    przyp.TYP_ODMIANY,
    przyp.MIANOWNIK,
    przyp.BIERNIK,
    przyp.CELOWNIK,
    przyp.DOPELNIACZ,
    przyp.MIEJSCOWNIK,
    przyp.NARZEDNIK,
    przyp.WOLACZ
from
    JEDNOSTKA_WIKI wiki
        join
    CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
        join
    ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
        join
    PRZYMIOTNIK_ODM prz_odm ON znacz.ID_ZNACZENIA = prz_odm.ID_ZNACZENIE
        join
    PRZYM_STOPIEN prz_stop ON prz_odm.ID_PRZYMIOTNIK_ODM = prz_stop.ID_PRZYM_ODM
        join
    PRZYPADKI przyp ON prz_stop.ID_PRZYM_STOP = przyp.ID_PRZYM_ODM;



-- -----------------------------------------------------
-- View `semantyk`.`PRZYS_ODM_VIEW`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `semantyk`.`PRZYS_ODM_VIEW` ;
DROP TABLE IF EXISTS `semantyk`.`PRZYS_ODM_VIEW`;
USE `semantyk`;
CREATE  OR REPLACE VIEW PRZYS_ODM_VIEW AS
select 
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    przys_odm.STOP_WYZSZY,
    przys_odm.STOP_NAJWYZSZY
from
    JEDNOSTKA_WIKI wiki
        join
    CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
        join
	ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
        join
    PRZYSLOWEK_ODM przys_odm ON znacz.ID_ZNACZENIA = przys_odm.ID_ZNACZENIE
;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
