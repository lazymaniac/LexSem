package pl.semantyk.wikiparser;

import org.apache.commons.lang3.StringUtils;
import pl.semantyk.domain.AdjectiveVar;
import pl.semantyk.domain.AdjectiveDegreeVar;
import pl.semantyk.domain.CasesVar;
import pl.semantyk.enums.AdjectiveDegree;
import pl.semantyk.enums.CasesType;
import pl.semantyk.utils.*;

public class AdjectiveVarGenerator {

	private final static String Y_TIP = "y";
	
	private final static String I_TIP = "i";
	
	private final static String EMPTY_TIP = "";
	
    public AdjectiveVarGenerator() {
    }

    /**
     * Takes as argument title of adjective ending with -y or -i. In case when higher degree or highest aren't
     * empty also variables them.
     *
     * @param zeroDegree      topic of unit
     * @param higherDegree    higher degree.
     * @param highestDegree   highest degree.
     * @return AdjectiveVar
     */
    public AdjectiveVar genAdjVar(String zeroDegree, String higherDegree, String highestDegree) {
        AdjectiveVar result = new AdjectiveVar();

        if (StringUtils.isNotBlank(zeroDegree)) {
            if (StringUtils.endsWith(zeroDegree, Y_TIP)) {
                result.addAdjectiveDegree(genDegree(zeroDegree, Y_TIP, AdjectiveDegree.ZERO));
            } else {
                result.addAdjectiveDegree(genDegree(zeroDegree, I_TIP, AdjectiveDegree.ZERO));
            }
        }

        if (StringUtils.isNotBlank(higherDegree)) {
            if (StringUtils.endsWith(higherDegree, Y_TIP)) {
                result.addAdjectiveDegree(genDegree(higherDegree, Y_TIP, AdjectiveDegree.HIGHER));
            } else if (StringUtils.endsWith(higherDegree, I_TIP)) {
                result.addAdjectiveDegree(genDegree(higherDegree, I_TIP, AdjectiveDegree.HIGHER));
            } else {
                result.addAdjectiveDegree(genDegree(higherDegree, EMPTY_TIP, AdjectiveDegree.HIGHER));
            }
        }

        if (StringUtils.isNotBlank(highestDegree)) {
            if (StringUtils.endsWith(highestDegree, Y_TIP)) {
                result.addAdjectiveDegree(genDegree(highestDegree, Y_TIP, AdjectiveDegree.HIGHEST));
            } else if (StringUtils.endsWith(higherDegree, I_TIP)) {
                result.addAdjectiveDegree(genDegree(highestDegree, I_TIP, AdjectiveDegree.HIGHEST));
            } else {
                result.addAdjectiveDegree(genDegree(highestDegree, EMPTY_TIP, AdjectiveDegree.HIGHEST));
            }
        }

        for (AdjectiveDegreeVar stop : result.getDegrees()) {
            stop.setAdjectiveVar(result.getId());
        }

        return result;
    }

    private AdjectiveDegreeVar genDegree(String topic, String end, AdjectiveDegree degree) {
        AdjectiveDegreeVar result = new AdjectiveDegreeVar(degree);

        result.addCasesVar(genLpMsOs(topic, end));
        result.addCasesVar(genLpZenOs(topic, end));
        result.addCasesVar(genLpNMsOs(topic, end));
        result.addCasesVar(genLmMsOs(topic, end));
        result.addCasesVar(genLmNMsOs(topic, end));

        result.setAdjectiveDegree(degree);

        return result;
    }

    private CasesVar genLmNMsOs(String temat, String koncowka) {
        CasesVar result = new CasesVar(CasesType.ADJ_SING_N);

        String cialo;

        // TODO ZAPROGRAMOWAĆ WYJĄTKI.
        if (koncowka.equals(Y_TIP)) {
            cialo = temat.substring(0, temat.lastIndexOf(Y_TIP));
            result.setMianownik(cialo + koncowka);
            result.setWolacz(cialo + koncowka);
        } else {
            cialo = temat;
            result.setMianownik(cialo + PrzymOdmLmNmOsForm.mianownik);
            result.setWolacz(cialo + PrzymOdmLmNmOsForm.wolacz);
        }

        result.setDopelniacz(cialo + PrzymOdmLmNmOsForm.dopelniacz);
        result.setCelownik(cialo + PrzymOdmLmNmOsForm.celownik);
        result.setBiernik(cialo + PrzymOdmLmNmOsForm.biernik);
        result.setNarzednik(cialo + PrzymOdmLmNmOsForm.narzednik);
        result.setMiejscownik(cialo + PrzymOdmLmNmOsForm.miejscownik);

        return result;
    }

    private CasesVar genLmMsOs(String temat, String koncowka) {
        CasesVar result = new CasesVar(CasesType.ADJ_PLUR_M);

        String cialo;

        // TODO ZAPROGRAMOWAĆ WYJĄTKI.
        if (koncowka.equals(Y_TIP)) {
            cialo = temat.substring(0, temat.lastIndexOf(Y_TIP));
            result.setMianownik(cialo + koncowka);
            result.setWolacz(cialo + koncowka);
            result.setMiejscownik(cialo + Y_TIP + PrzymOdmLmMsOsForm.miejscownik);
            result.setNarzednik(cialo + Y_TIP + PrzymOdmLmMsOsForm.narzednik);
            result.setBiernik(cialo + Y_TIP + PrzymOdmLmMsOsForm.biernik);
            result.setDopelniacz(cialo + Y_TIP + PrzymOdmLmMsOsForm.dopelniacz);
            result.setCelownik(cialo + Y_TIP + PrzymOdmLmMsOsForm.celownik);
        } else {
            cialo = temat;
            result.setMianownik(cialo);
            result.setWolacz(cialo);
            result.setMiejscownik(cialo + PrzymOdmLmMsOsForm.miejscownik);
            result.setNarzednik(cialo + PrzymOdmLmMsOsForm.narzednik);
            result.setBiernik(cialo + PrzymOdmLmMsOsForm.biernik);
            result.setDopelniacz(cialo + PrzymOdmLmMsOsForm.dopelniacz);
            result.setCelownik(cialo + PrzymOdmLmMsOsForm.celownik);

        }


        return result;
    }

    private CasesVar genLpNMsOs(String temat, String koncowka) {
        CasesVar result = new CasesVar(CasesType.ADJ_SING_N);

        String cialo;

        if (koncowka.equals(Y_TIP)) {
            cialo = temat.substring(0, temat.lastIndexOf(Y_TIP));
            result.setMiejscownik(cialo + Y_TIP + PrzymOdmLpNieMsForm.miejscownik);
            result.setNarzednik(cialo + Y_TIP + PrzymOdmLpNieMsForm.narzednik);
            result.setCelownik(cialo + PrzymOdmLpNieMsForm.celownik);
            result.setDopelniacz(cialo + PrzymOdmLpNieMsForm.dopelniacz);
        } else {
            cialo = temat;
            result.setMiejscownik(cialo + PrzymOdmLpNieMsForm.miejscownik);
            result.setNarzednik(cialo + PrzymOdmLpNieMsForm.narzednik);
            result.setCelownik(cialo + PrzymOdmLpNieMsForm.celownik);
            result.setDopelniacz(cialo + PrzymOdmLpNieMsForm.dopelniacz);
        }
        result.setMianownik(cialo + PrzymOdmLpNieMsForm.mianownik);
        result.setBiernik(cialo + PrzymOdmLpNieMsForm.biernik);
        result.setWolacz(cialo + PrzymOdmLpNieMsForm.wolacz);

        return result;
    }

    private CasesVar genLpZenOs(String temat, String koncowka) {
        CasesVar result = new CasesVar(CasesType.ADJ_PLUR_F);

        String cialo;

        if (koncowka.equals(Y_TIP)) {
            cialo = temat.substring(0, temat.lastIndexOf(Y_TIP));
            result.setMianownik(cialo + PrzymOdmLpZenForm.mianownik);
            result.setBiernik(cialo + PrzymOdmLpZenForm.biernik);
            result.setNarzednik(cialo + PrzymOdmLpZenForm.narzednik);
            result.setWolacz(cialo + PrzymOdmLpZenForm.wolacz);
        } else {
            cialo = temat;
            result.setMianownik(cialo.substring(0, cialo.length()-1) + PrzymOdmLpZenForm.mianownik);
            result.setBiernik(cialo.substring(0, cialo.length()-1) + PrzymOdmLpZenForm.biernik);
            result.setNarzednik(cialo.substring(0, cialo.length()-1) + PrzymOdmLpZenForm.narzednik);
            result.setWolacz(cialo.substring(0, cialo.length()-1) + PrzymOdmLpZenForm.wolacz);
        }
        
        result.setDopelniacz(cialo + PrzymOdmLpZenForm.dopelniacz);
        result.setCelownik(cialo + PrzymOdmLpZenForm.celownik);
        result.setMiejscownik(cialo + PrzymOdmLpZenForm.miejscownik);

        return result;
    }

    private CasesVar genLpMsOs(String temat, String koncowka) {
        CasesVar result = new CasesVar(CasesType.ADJ_SING_M);

        String cialo;

        if (koncowka.equals(Y_TIP)) {
            cialo = temat.substring(0, temat.lastIndexOf(Y_TIP));
            result.setMianownik(cialo + Y_TIP);
            result.setWolacz(cialo + Y_TIP);
            result.setNarzednik(cialo + PrzymOdmLpMsOsForm.narzednik_y);
            result.setMiejscownik(cialo + PrzymOdmLpMsOsForm.miejscownik_y);
        } else {
            cialo = temat;
            result.setMianownik(cialo);
            result.setWolacz(cialo);
            result.setNarzednik(cialo + PrzymOdmLpMsOsForm.narzednik_i);
            result.setMiejscownik(cialo + PrzymOdmLpMsOsForm.miejscownik_i);
        }

        result.setDopelniacz(cialo + PrzymOdmLpMsOsForm.dopelniacz);
        result.setCelownik(cialo + PrzymOdmLpMsOsForm.celownik);
        result.setBiernik(cialo + PrzymOdmLpMsOsForm.biernik);

        return result;
    }

}
