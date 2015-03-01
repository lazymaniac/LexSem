-- -----------------------------------------------------
-- Schema semantyk
-- -----------------------------------------------------
-- Baza danych chatterbota semantycznego.
DROP SCHEMA IF EXISTS semantyk CASCADE;
CREATE SCHEMA semantyk;
-- -----------------------------------------------------
-- Table semantyk.JEDNOSTKA_WIKI
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.JEDNOSTKA_WIKI ;

CREATE TABLE IF NOT EXISTS semantyk.JEDNOSTKA_WIKI (
  ID_JEDNOSTKA SERIAL PRIMARY KEY,
  NAZWA VARCHAR(255) NULL,
  TEMAT VARCHAR(255) NULL);
CREATE UNIQUE INDEX PK_JEDNOSTKA_idx ON semantyk.JEDNOSTKA_WIKI(ID_JEDNOSTKA);
CREATE INDEX WIKT_NAZWA_IDX ON semantyk.JEDNOSTKA_WIKI(NAZWA);

-- -----------------------------------------------------
-- Table semantyk.CZESCI_MOWY
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.CZESCI_MOWY ;

CREATE TABLE IF NOT EXISTS semantyk.CZESCI_MOWY (
  ID_CZESC_MOWY SERIAL PRIMARY KEY,
  CZESC_MOWY VARCHAR(255) NULL DEFAULT 'default',
  ID_JEDNOSTKA INT NOT NULL REFERENCES semantyk.JEDNOSTKA_WIKI (ID_JEDNOSTKA));

CREATE UNIQUE INDEX PK_CZESC_MOWY_IDX ON semantyk.CZESCI_MOWY(ID_CZESC_MOWY);
CREATE INDEX CZESC_MOWY_IDX ON semantyk.CZESCI_MOWY(CZESC_MOWY);


-- -----------------------------------------------------
-- Table semantyk.ZNACZENIA
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.ZNACZENIA ;

CREATE TABLE IF NOT EXISTS semantyk.ZNACZENIA (
  ID_ZNACZENIA SERIAL PRIMARY KEY,
  ZNACZENIE VARCHAR(2048) NOT NULL,
  ID_CZESC_MOWY INT NOT NULL REFERENCES semantyk.CZESCI_MOWY (ID_CZESC_MOWY));

CREATE UNIQUE INDEX PK_ZNACZENIA_IDX ON semantyk.ZNACZENIA(ID_ZNACZENIA);
CREATE INDEX ZNACZENIE_IDX ON semantyk.ZNACZENIA(ZNACZENIE);
-- -----------------------------------------------------
-- Table semantyk.RZECZOWNIK_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.RZECZOWNIK_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.RZECZOWNIK_ODM (
  ID_RZECZ_ODM SERIAL PRIMARY KEY,
  ODMIENIALNY BOOLEAN NOT NULL,
  BRAK_LP BOOLEAN NULL,
  BRAK_LM BOOLEAN NULL,
  RODZAJ VARCHAR(45) NULL,
  DRUGI_RODZAJ VARCHAR(45) NULL,
  TEMAT VARCHAR(255) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));

CREATE UNIQUE INDEX PK_RZECZ_ODM_IDX ON semantyk.RZECZOWNIK_ODM(ID_RZECZ_ODM);

-- -----------------------------------------------------
-- Table semantyk.PRZYMIOTNIK_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.PRZYMIOTNIK_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.PRZYMIOTNIK_ODM (
  ID_PRZYMIOTNIK_ODM SERIAL PRIMARY KEY,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));

CREATE UNIQUE INDEX PK_PRZYM_ODM_IDX ON semantyk.PRZYMIOTNIK_ODM(ID_PRZYMIOTNIK_ODM);
-- -----------------------------------------------------
-- Table semantyk.PRZYM_STOPIEN
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.PRZYM_STOPIEN ;

CREATE TABLE IF NOT EXISTS semantyk.PRZYM_STOPIEN (
  ID_PRZYM_STOP SERIAL PRIMARY KEY,
  STOPIEN VARCHAR(45) NOT NULL,
  ID_PRZYM_ODM INT NOT NULL REFERENCES semantyk.PRZYMIOTNIK_ODM (ID_PRZYMIOTNIK_ODM));

CREATE UNIQUE INDEX PK_PRZYM_STOP_IDX ON semantyk.PRZYM_STOPIEN(ID_PRZYM_STOP);

-- -----------------------------------------------------
-- Table semantyk.PRZYPADKI
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.PRZYPADKI ;

CREATE TABLE IF NOT EXISTS semantyk.PRZYPADKI (
  ID_PRZYPADEK SERIAL PRIMARY KEY,
  MIANOWNIK VARCHAR(255) NULL,
  DOPELNIACZ VARCHAR(255) NULL,
  CELOWNIK VARCHAR(255) NULL,
  BIERNIK VARCHAR(255) NULL,
  NARZEDNIK VARCHAR(255) NULL,
  MIEJSCOWNIK VARCHAR(255) NULL,
  WOLACZ VARCHAR(255) NULL,
  TYP_ODMIANY VARCHAR(45) NOT NULL,
  ID_RZECZOWNIK_ODM INT NULL REFERENCES semantyk.RZECZOWNIK_ODM (ID_RZECZ_ODM),
  ID_PRZYM_ODM INT NULL  REFERENCES semantyk.PRZYM_STOPIEN (ID_PRZYM_STOP));

CREATE UNIQUE INDEX PK_PRZYPADKI_IDX ON semantyk.PRZYPADKI(ID_PRZYPADEK);
CREATE INDEX PRZYPADKI_IDX ON semantyk.PRZYPADKI(MIANOWNIK, DOPELNIACZ, CELOWNIK, BIERNIK, NARZEDNIK, MIEJSCOWNIK, WOLACZ);

-- -----------------------------------------------------
-- Table semantyk.ZAIMEK_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.ZAIMEK_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.ZAIMEK_ODM (
  ID_ZAIMEK_ODM SERIAL PRIMARY KEY,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA),
  ID_ODM INT NOT NULL REFERENCES semantyk.PRZYPADKI (ID_PRZYPADEK));

CREATE UNIQUE INDEX PK_ZAIM_ODM_IDX ON semantyk.ZAIMEK_ODM(ID_ZAIMEK_ODM);

-- -----------------------------------------------------
-- Table semantyk.SYNSET
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.SYNSET ;

CREATE TABLE IF NOT EXISTS semantyk.SYNSET (
  ID_SYNSET SERIAL PRIMARY KEY,
  PODZIELNY INT NULL,
  ABSTRACT BOOLEAN NULL);

CREATE UNIQUE INDEX PK_SYNSET_IDX ON semantyk.SYNSET(ID_SYNSET);

-- -----------------------------------------------------
-- Table semantyk.TYP_RELACJI
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.TYP_RELACJI ;

CREATE TABLE IF NOT EXISTS semantyk.TYP_RELACJI (
  ID_TYP_RELACJI SERIAL PRIMARY KEY,
  TYP VARCHAR(45) NULL,
  RODZIC INT NULL,
  NAZWA VARCHAR(255) NULL,
  OPIS VARCHAR(2048) NULL,
  POSSTR VARCHAR(255) NULL,
  WYSWIETL VARCHAR(255) NULL,
  SKROT VARCHAR(255) NULL,
  AUTOODWRACANIE BOOLEAN NULL);

CREATE UNIQUE INDEX PK_TYP_REL_IDX ON semantyk.TYP_RELACJI(ID_TYP_RELACJI);

-- -----------------------------------------------------
-- Table semantyk.RELACJA_SYNSETU
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.RELACJA_SYNSETU ;

CREATE TABLE IF NOT EXISTS semantyk.RELACJA_SYNSETU (
  RODZIC INT NOT NULL REFERENCES semantyk.SYNSET (ID_SYNSET),
  DZIECKO INT NOT NULL REFERENCES semantyk.SYNSET (ID_SYNSET),
  RELACJA INT NOT NULL REFERENCES semantyk.TYP_RELACJI (ID_TYP_RELACJI),
  SPRAWDZONY BOOLEAN NULL,
  PRIMARY KEY (RODZIC, DZIECKO, RELACJA));
CREATE UNIQUE INDEX PK_REL_SYN_IDX ON semantyk.RELACJA_SYNSETU(RODZIC, DZIECKO, RELACJA);

-- -----------------------------------------------------
-- Table semantyk.PRZYSLOWEK_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.PRZYSLOWEK_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.PRZYSLOWEK_ODM (
  ID_PRZYS_ODM SERIAL PRIMARY KEY,
  STOP_WYZSZY VARCHAR(255) NULL,
  STOP_NAJWYZSZY VARCHAR(255) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));
CREATE UNIQUE INDEX PK_PRZYSL_ODM_IDX ON semantyk.PRZYSLOWEK_ODM(ID_PRZYS_ODM);
CREATE INDEX PRZYS_ODM_IDX ON semantyk.PRZYSLOWEK_ODM(STOP_WYZSZY, STOP_NAJWYZSZY);

-- -----------------------------------------------------
-- Table semantyk.CZASOWNIK_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.CZASOWNIK_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.CZASOWNIK_ODM (
  ID_CZASOWNIK_ODM SERIAL PRIMARY KEY,
  TEMAT VARCHAR(255) NULL,
  IS_DOKONANY BOOLEAN NULL,
  KONIUGACJA VARCHAR(45) NULL,
  BEZOKOLICZNIK VARCHAR(255) NULL,
  FORM_BEZOSOB_CZAS_PRZESZ VARCHAR(255) NULL,
  IMIES_PRZYS_WSPOL VARCHAR(255) NULL,
  IMIES_PRZYS_UPRZED VARCHAR(255) NULL,
  RZECZ_ODCZAS VARCHAR(255) NULL,
  ZWROTNY BOOLEAN NULL,
  ZAIMEK_ZWROTNY VARCHAR(45) NULL,
  DOKONANY VARCHAR(255) NULL,
  NIE_DOKONANY VARCHAR(255) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));
CREATE UNIQUE INDEX PK_CZAS_ODM_IDX ON semantyk.CZASOWNIK_ODM(ID_CZASOWNIK_ODM);

-- -----------------------------------------------------
-- Table semantyk.OSOBY_ODM
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.OSOBY_ODM ;

CREATE TABLE IF NOT EXISTS semantyk.OSOBY_ODM (
  ID_OSOBY_ODM SERIAL PRIMARY KEY,
  OS_1_LP VARCHAR(255) NULL,
  OS_2_LP VARCHAR(255) NULL,
  OS_3_LP VARCHAR(255) NULL,
  OS_1_LM VARCHAR(255) NULL,
  OS_2_LM VARCHAR(255) NULL,
  OS_3_LM VARCHAR(255) NULL,
  TYP_ODMIANY VARCHAR(45) NOT NULL,
  ID_CZASOWNIK_ODM INT NOT NULL REFERENCES semantyk.CZASOWNIK_ODM (ID_CZASOWNIK_ODM));
CREATE UNIQUE INDEX PK_OSOBY_ODM_IDX ON semantyk.OSOBY_ODM(ID_OSOBY_ODM);

-- -----------------------------------------------------
-- Table semantyk.JEDNOSTKA_WN
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.JEDNOSTKA_WN ;

CREATE TABLE IF NOT EXISTS semantyk.JEDNOSTKA_WN (
  ID_JEDN_WN SERIAL PRIMARY KEY,
  NAZWA VARCHAR(255) NOT NULL,
  POZYCJA VARCHAR(255) NOT NULL,
  DOMENA VARCHAR(255) NOT NULL,
  WARIANT INT NOT NULL,
  ID_JEDN_WIKI INT NULL REFERENCES semantyk.JEDNOSTKA_WIKI (ID_JEDNOSTKA));
CREATE UNIQUE INDEX PK_JEDN_WN_IDX ON semantyk.JEDNOSTKA_WN(ID_JEDN_WN);

-- -----------------------------------------------------
-- Table semantyk.RELACJA_LEKS
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.RELACJA_LEKS ;

CREATE TABLE IF NOT EXISTS semantyk.RELACJA_LEKS (
  RODZIC INT NOT NULL,
  DZIECKO INT NOT NULL,
  RELACJA INT NOT NULL,
  PRIMARY KEY (RODZIC, DZIECKO, RELACJA),
  FOREIGN KEY (RODZIC) REFERENCES semantyk.JEDNOSTKA_WN(ID_JEDN_WN),
  FOREIGN KEY (DZIECKO) REFERENCES semantyk.JEDNOSTKA_WN(ID_JEDN_WN),
  FOREIGN KEY (RELACJA) REFERENCES semantyk.TYP_RELACJI(ID_TYP_RELACJI));

CREATE UNIQUE INDEX FK_LEKS_RELACJA_idx ON semantyk.RELACJA_LEKS(RODZIC, DZIECKO, RELACJA);


-- -----------------------------------------------------
-- Table semantyk.ANTONIMY
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.ANTONIMY ;

CREATE TABLE IF NOT EXISTS semantyk.ANTONIMY (
  ID_ANTONIM SERIAL PRIMARY KEY,
  ANTONIM VARCHAR(2048) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));

CREATE UNIQUE INDEX PK_ANTONIM_IDX ON semantyk.ANTONIMY(ID_ANTONIM);

-- -----------------------------------------------------
-- Table semantyk.FRAZEOLOGIE
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.FRAZEOLOGIE ;

CREATE TABLE IF NOT EXISTS semantyk.FRAZEOLOGIE (
  ID_FRAZOLOGIA SERIAL PRIMARY KEY,
  FRAZEOLOGIA VARCHAR(4096) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));

CREATE UNIQUE INDEX PK_FRAZEOLOGIE_IDX ON semantyk.FRAZEOLOGIE(ID_FRAZOLOGIA);

-- -----------------------------------------------------
-- Table semantyk.KOLOKACJE
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.KOLOKACJE ;

CREATE TABLE IF NOT EXISTS semantyk.KOLOKACJE (
  ID_KOLOKACJA SERIAL PRIMARY KEY,
  KOLOKACJA VARCHAR(2048) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA(ID_ZNACZENIA));

CREATE UNIQUE INDEX PK_KOLOKACJE_IDX ON semantyk.KOLOKACJE(ID_KOLOKACJA);
-- -----------------------------------------------------
-- Table semantyk.POKREWNE
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.POKREWNE ;

CREATE TABLE IF NOT EXISTS semantyk.POKREWNE (
  ID_POKREWNY SERIAL PRIMARY KEY,
  POKREWNY VARCHAR(510) NULL,
  CZESC_MOWY VARCHAR(255) NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA),
  POKREWNE VARCHAR(45) NULL);
CREATE UNIQUE INDEX PK_POKREWNE_IDX ON semantyk.POKREWNE(ID_POKREWNY);

-- -----------------------------------------------------
-- Table semantyk.PRZYKLADY
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.PRZYKLADY ;

CREATE TABLE IF NOT EXISTS semantyk.PRZYKLADY (
  ID_PRZYKLAD SERIAL PRIMARY KEY,
  PRZYKLAD VARCHAR(2048) NOT NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));
CREATE UNIQUE INDEX PK_PRZYKLADY_IDX ON semantyk.PRZYKLADY(ID_PRZYKLAD);

-- -----------------------------------------------------
-- Table semantyk.SYNONIMY
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.SYNONIMY ;

CREATE TABLE IF NOT EXISTS semantyk.SYNONIMY (
  ID_SYNONIM SERIAL PRIMARY KEY,
  SYNONIM VARCHAR(2048) NOT NULL,
  ID_ZNACZENIE INT NOT NULL REFERENCES semantyk.ZNACZENIA (ID_ZNACZENIA));
CREATE UNIQUE INDEX PK_SYNONIMY_IDX ON semantyk.SYNONIMY(ID_SYNONIM);

-- -----------------------------------------------------
-- Table semantyk.RAW_WIKI_UNIT
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.RAW_WIKI_UNIT ;

CREATE TABLE IF NOT EXISTS semantyk.RAW_WIKI_UNIT (
  ID_RAW_UNIT SERIAL PRIMARY KEY,
  NAZWA VARCHAR(255) NOT NULL,
  BODY VARCHAR(4000) NOT NULL);

CREATE UNIQUE INDEX PK_RAW_WIKI_UNIT_IDX ON semantyk.RAW_WIKI_UNIT(ID_RAW_UNIT);


-- -----------------------------------------------------
-- Table semantyk.JEDN_WN_SYN_REL
-- -----------------------------------------------------
DROP TABLE IF EXISTS semantyk.JEDN_WN_SYN_REL ;

CREATE TABLE IF NOT EXISTS semantyk.JEDN_WN_SYN_REL (
  ID_JEDN_WN INT NOT NULL REFERENCES semantyk.JEDNOSTKA_WN (ID_JEDN_WN),
  ID_SYNSET INT NOT NULL REFERENCES semantyk.SYNSET (ID_SYNSET),
  PRIMARY KEY (ID_JEDN_WN, ID_SYNSET));
CREATE UNIQUE INDEX PK_JEDN_WN_SYN_REL_IDX ON semantyk.JEDN_WN_SYN_REL(ID_JEDN_WN, ID_SYNSET);


-- -----------------------------------------------------
-- View semantyk.ZNACZENIE_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.ZNACZENIE_VIEW ;
CREATE OR replace view ZNACZENIE_VIEW AS
  SELECT
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    znacz.ZNACZENIE,
    cz_mo.CZESC_MOWY
  FROM
    semantyk.JEDNOSTKA_WIKI wiki
    LEFT JOIN
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    LEFT JOIN
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
  group BY znacz.znaczenie, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY;



-- -----------------------------------------------------
-- View semantyk.SYNONIM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.SYNONIM_VIEW ;

CREATE OR REPLACE VIEW SYNONIM_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    syn.SYNONIM
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.SYNONIMY syn ON (syn.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY syn.SYNONIM, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY;

-- -----------------------------------------------------
-- View semantyk.ANTONIM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.ANTONIM_VIEW ;

CREATE OR REPLACE VIEW ANTONIM_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    ant.ANTONIM
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.ANTONIMY ant ON (ant.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY ant.ANTONIM, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY;

-- -----------------------------------------------------
-- View semantyk.PRZYKLAD_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.PRZYKLAD_VIEW ;

CREATE OR REPLACE VIEW PRZYKLAD_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    przy.PRZYKLAD
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.PRZYKLADY przy ON (przy.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY przy.PRZYKLAD, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY;

-- -----------------------------------------------------
-- View semantyk.FRAZEOLOGIA_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.FRAZEOLOGIA_VIEW ;

CREATE  OR REPLACE VIEW FRAZEOLOGIA_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    fraz.FRAZEOLOGIA
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.FRAZEOLOGIE fraz ON (fraz.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY fraz.FRAZEOLOGIA, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY
;

-- -----------------------------------------------------
-- View semantyk.KOLOKACJA_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.KOLOKACJA_VIEW ;

CREATE OR REPLACE VIEW KOLOKACJA_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    kol.KOLOKACJA
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.KOLOKACJE kol ON (kol.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY kol.KOLOKACJA, wiki.ID_JEDNOSTKA, wiki.nazwa, cz_mo.CZESC_MOWY;

-- -----------------------------------------------------
-- View semantyk.POKREWNY_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.POKREWNY_VIEW ;

CREATE OR REPLACE VIEW POKREWNY_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.nazwa,
    cz_mo.CZESC_MOWY,
    pok.POKREWNY
  from
    semantyk.JEDNOSTKA_WIKI wiki
    left join
    semantyk.CZESCI_MOWY cz_mo ON (cz_mo.id_jednostka = wiki.ID_JEDNOSTKA)
    left join
    semantyk.ZNACZENIA znacz ON (znacz.ID_CZESC_MOWY = cz_mo.id_czesc_mowy)
    JOIN
    semantyk.POKREWNE pok ON (pok.ID_ZNACZENIE = znacz.ID_ZNACZENIA)
  GROUP BY pok.POKREWNY, wiki.ID_JEDNOSTKA, cz_mo.CZESC_MOWY, wiki.nazwa
;

-- -----------------------------------------------------
-- View semantyk.RZECZ_ODM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.RZECZ_ODM_VIEW ;

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
    semantyk.JEDNOSTKA_WIKI wiki
    join
    semantyk.CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
    join
    semantyk.ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
    join
    semantyk.RZECZOWNIK_ODM rz_odm ON znacz.ID_ZNACZENIA = rz_odm.ID_ZNACZENIE
    join
    semantyk.PRZYPADKI przyp ON rz_odm.ID_RZECZ_ODM = przyp.ID_RZECZOWNIK_ODM;


-- -----------------------------------------------------
-- View semantyk.ZAIMEK_ODM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.ZAIMEK_ODM_VIEW ;

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
    semantyk.JEDNOSTKA_WIKI wiki
    join
    semantyk.CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
    join
    semantyk.ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
    join
    semantyk.ZAIMEK_ODM za_odm ON znacz.ID_ZNACZENIA = za_odm.ID_ZNACZENIE
    join
    semantyk.PRZYPADKI przyp ON za_odm.ID_ODM = przyp.ID_PRZYPADEK;


-- -----------------------------------------------------
-- View semantyk.PRZYM_ODM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.PRZYM_ODM_VIEW ;

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
    semantyk.JEDNOSTKA_WIKI wiki
    join
    semantyk.CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
    join
    semantyk.ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
    join
    semantyk.PRZYMIOTNIK_ODM prz_odm ON znacz.ID_ZNACZENIA = prz_odm.ID_ZNACZENIE
    join
    semantyk.PRZYM_STOPIEN prz_stop ON prz_odm.ID_PRZYMIOTNIK_ODM = prz_stop.ID_PRZYM_ODM
    join
    semantyk.PRZYPADKI przyp ON prz_stop.ID_PRZYM_STOP = przyp.ID_PRZYM_ODM;


-- -----------------------------------------------------
-- View semantyk.CZAS_ODM_VIWE
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.CZAS_ODM_VIWE ;

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
    semantyk.JEDNOSTKA_WIKI wiki
    join
    semantyk.CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
    join
    semantyk.ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
    join
    semantyk.PRZYMIOTNIK_ODM prz_odm ON znacz.ID_ZNACZENIA = prz_odm.ID_ZNACZENIE
    join
    semantyk.PRZYM_STOPIEN prz_stop ON prz_odm.ID_PRZYMIOTNIK_ODM = prz_stop.ID_PRZYM_ODM
    join
    semantyk.PRZYPADKI przyp ON prz_stop.ID_PRZYM_STOP = przyp.ID_PRZYM_ODM;



-- -----------------------------------------------------
-- View semantyk.PRZYS_ODM_VIEW
-- -----------------------------------------------------
DROP VIEW IF EXISTS semantyk.PRZYS_ODM_VIEW ;

CREATE  OR REPLACE VIEW PRZYS_ODM_VIEW AS
  select
    wiki.ID_JEDNOSTKA,
    wiki.NAZWA,
    cz_mo.CZESC_MOWY,
    przys_odm.STOP_WYZSZY,
    przys_odm.STOP_NAJWYZSZY
  from
    semantyk.JEDNOSTKA_WIKI wiki
    join
    semantyk.CZESCI_MOWY cz_mo ON wiki.ID_JEDNOSTKA = cz_mo.ID_JEDNOSTKA
    join
    semantyk.ZNACZENIA znacz ON cz_mo.ID_CZESC_MOWY = znacz.ID_CZESC_MOWY
    join
    semantyk.PRZYSLOWEK_ODM przys_odm ON znacz.ID_ZNACZENIA = przys_odm.ID_ZNACZENIE
;
