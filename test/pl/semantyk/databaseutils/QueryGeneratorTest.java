package pl.semantyk.databaseutils;

import org.junit.Assert;
import org.junit.Test;
import pl.semantyk.domain.*;
import pl.semantyk.exceptions.SystemException;

import static org.junit.Assert.*;
import static pl.semantyk.databaseutils.QueryGenerator.*;

public class QueryGeneratorTest {

    @Test
    public void testGetInsertQueryForClass() throws Exception {
        String query = getInsertQueryForClass(Synset.class);
        System.out.println(query);
        Assert.assertTrue(query.equals("INSERT INTO SYNSET VALUES (?, ?, ?);"));
    }

    @Test
    public void testGetFindByIdQueryForClass() throws Exception {
        String query = getFindByIdQueryForClass(CasesVar.class);
        System.out.println(query);
        Assert.assertTrue(query.equals("SELECT ID_PRZYPADEK, TYP_ODMIANY, MIANOWNIK, DOPELNIACZ, CELOWNIK, BIERNIK, NARZEDNIK, MIEJSCOWNIK, WOLACZ, ID_RZECZOWNIK_ODM, ID_PRZYM_ODM FROM PRZYPADKI WHERE ID_PRZYPADEK = ?"));
    }

    @Test
    public void testGetFindAllQueryForClass() throws Exception {
        String query = getFindAllQueryForClass(WikiUnit.class);
        System.out.println(query);
        Assert.assertTrue(query.equals("SELECT ID_JEDNOSTKA, NAZWA, TEMAT FROM JEDNOSTKA_WIKI"));
    }

    @Test
    public void testGetDeleteQueryForClass() throws Exception {
        String query = getDeleteQueryForClass(RelationType.class);
        System.out.println(query);
        Assert.assertTrue(query.equals("DELETE ID_TYP_RELACJI, TYP, RODZIC, NAZWA, OPIS, POSSTR, WYSWIETL, SKROT, AUTOODWRACANIE FROM TYP_RELACJI WHERE ID_TYP_RELACJI = ?"));
    }

    @Test
    public void testGetRemoveAllQueryForClass() throws Exception {
        String query = getDeleteAllQueryForClass(AdjectiveVar.class);
        System.out.println(query);
        Assert.assertTrue(query.equals("DELETE ID_PRZYMIOTNIK_ODM, ID_ZNACZENIE FROM PRZYMIOTNIK_ODM"));
    }
}