-- -----------------------------------------------------
-- Schema pokedex
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pokedex
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pokedex` DEFAULT CHARACTER SET utf8 ;
USE `pokedex` ;

-- -----------------------------------------------------
-- Table `pokedex`.`Região`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Região` (
  `idRegião` INT NOT NULL,
  PRIMARY KEY (`idRegião`));


-- -----------------------------------------------------
-- Table `pokedex`.`Pokedex`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Pokedex` (
  `idPokedex` INT NOT NULL,
  `Região_idRegião` INT NOT NULL,
  PRIMARY KEY (`idPokedex`),
  INDEX `fk_Pokedex_Região1_idx` (`Região_idRegião` ASC) VISIBLE,
  CONSTRAINT `fk_Pokedex_Região1`
    FOREIGN KEY (`Região_idRegião`)
    REFERENCES `pokedex`.`Região` (`idRegião`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`Treinador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Treinador` (
  `idTreinador` INT NOT NULL,
  `nivel` INT NULL,
  `insgnias` INT NULL,
  `nome` VARCHAR(45) NULL,
  `Pokedex_idPokedex` INT NOT NULL,
  PRIMARY KEY (`idTreinador`),
  INDEX `fk_Treinador_Pokedex_idx` (`Pokedex_idPokedex` ASC) VISIBLE,
  CONSTRAINT `fk_Treinador_Pokedex`
    FOREIGN KEY (`Pokedex_idPokedex`)
    REFERENCES `pokedex`.`Pokedex` (`idPokedex`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`pokemon` (
  `idPokemon` INT NOT NULL,
  `nome` VARCHAR(10) NULL,
  `vida` INT NULL,
  `physicalAtk` INT NULL,
  `specialAtk` INT NULL,
  `physicalDef` INT NULL,
  `specialDef` INT NULL,
  `speed` INT NULL,
  `Treinador_idTreinador` INT NOT NULL,
  `Pokedex_idPokedex` INT NOT NULL,
  `Região_idRegião` INT NOT NULL,
  PRIMARY KEY (`idPokemon`),
  INDEX `fk_pokemon_Treinador1_idx` (`Treinador_idTreinador` ASC) VISIBLE,
  INDEX `fk_pokemon_Pokedex1_idx` (`Pokedex_idPokedex` ASC) VISIBLE,
  INDEX `fk_pokemon_Região1_idx` (`Região_idRegião` ASC) VISIBLE,
  CONSTRAINT `fk_pokemon_Treinador1`
    FOREIGN KEY (`Treinador_idTreinador`)
    REFERENCES `pokedex`.`Treinador` (`idTreinador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pokemon_Pokedex1`
    FOREIGN KEY (`Pokedex_idPokedex`)
    REFERENCES `pokedex`.`Pokedex` (`idPokedex`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pokemon_Região1`
    FOREIGN KEY (`Região_idRegião`)
    REFERENCES `pokedex`.`Região` (`idRegião`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Cidade` (
  `idCidade` INT NOT NULL,
  `locCentroPkm` VARCHAR(45) NULL,
  `loc_loja` VARCHAR(45) NULL,
  `locGinasio` VARCHAR(45) NULL,
  `Região_idRegião` INT NOT NULL,
  PRIMARY KEY (`idCidade`),
  INDEX `fk_Cidade_Região1_idx` (`Região_idRegião` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Região1`
    FOREIGN KEY (`Região_idRegião`)
    REFERENCES `pokedex`.`Região` (`idRegião`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`Movimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Movimento` (
  `idMovimento` INT NOT NULL,
  `dano` INT NULL,
  `prioridade` INT NULL,
  `tipo` VARCHAR(10) NULL,
  `efeitosExtra` VARCHAR(45) NULL,
  PRIMARY KEY (`idMovimento`));


-- -----------------------------------------------------
-- Table `pokedex`.`pokemon_has_Movimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`pokemon_has_Movimento` (
  `pokemon_idPokemon` INT NOT NULL,
  `Movimento_idMovimento` INT NOT NULL,
  PRIMARY KEY (`pokemon_idPokemon`, `Movimento_idMovimento`),
  INDEX `fk_pokemon_has_Movimento_Movimento1_idx` (`Movimento_idMovimento` ASC) VISIBLE,
  INDEX `fk_pokemon_has_Movimento_pokemon1_idx` (`pokemon_idPokemon` ASC) VISIBLE,
  CONSTRAINT `fk_pokemon_has_Movimento_pokemon1`
    FOREIGN KEY (`pokemon_idPokemon`)
    REFERENCES `pokedex`.`pokemon` (`idPokemon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pokemon_has_Movimento_Movimento1`
    FOREIGN KEY (`Movimento_idMovimento`)
    REFERENCES `pokedex`.`Movimento` (`idMovimento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`Rota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Rota` (
  `idRota` INT NOT NULL,
  `nomeRota` VARCHAR(45) NULL,
  `bioma` VARCHAR(45) NULL,
  `clima` VARCHAR(45) NULL,
  `Cidade_idCidadeInicio` INT NOT NULL,
  `Cidade_idCidadeDestino` INT NOT NULL,
  PRIMARY KEY (`idRota`),
  INDEX `fk_Rota_Cidade1_idx` (`Cidade_idCidadeInicio` ASC) VISIBLE,
  INDEX `fk_Rota_Cidade2_idx` (`Cidade_idCidadeDestino` ASC) VISIBLE,
  CONSTRAINT `fk_Rota_Cidade1`
    FOREIGN KEY (`Cidade_idCidadeInicio`)
    REFERENCES `pokedex`.`Cidade` (`idCidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rota_Cidade2`
    FOREIGN KEY (`Cidade_idCidadeDestino`)
    REFERENCES `pokedex`.`Cidade` (`idCidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pokedex`.`Rota_has_pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedex`.`Rota_has_pokemon` (
  `Rota_idRota` INT NOT NULL,
  `pokemon_idPokemon` INT NOT NULL,
  PRIMARY KEY (`Rota_idRota`, `pokemon_idPokemon`),
  INDEX `fk_Rota_has_pokemon_pokemon1_idx` (`pokemon_idPokemon` ASC) VISIBLE,
  INDEX `fk_Rota_has_pokemon_Rota1_idx` (`Rota_idRota` ASC) VISIBLE,
  CONSTRAINT `fk_Rota_has_pokemon_Rota1`
    FOREIGN KEY (`Rota_idRota`)
    REFERENCES `pokedex`.`Rota` (`idRota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rota_has_pokemon_pokemon1`
    FOREIGN KEY (`pokemon_idPokemon`)
    REFERENCES `pokedex`.`pokemon` (`idPokemon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
