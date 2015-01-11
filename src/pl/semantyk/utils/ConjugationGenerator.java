package pl.semantyk.utils;

import pl.semantyk.domain.VerbVar;
import pl.semantyk.domain.PersonVar;
import pl.semantyk.enums.ConjugationType;
import pl.semantyk.enums.PersonVarType;

// TODO dopisac obsluge zaimka zwrotnego oraz przedimka będę
// TODO zrefaktoryzowac. Rozpisac kazda koniugacje do osobnej klasy.

/**
 * Generates all vars for verb on the basis of conjugation type.
 *
 * @author Sebastian.Fabisz
 * @version 1.0
 */
public class ConjugationGenerator {

    private VerbVar czasOdm;
    private String temat;
    private ConjugationType koniugacja;
    private String bezokol;
    private String czas_teraz_1os_lp;
    private String czas_teraz_2os_lp;
    private String czas_teraz_3os_lp;
    private String czas_teraz_1os_lm;
    private String czas_teraz_2os_lm;
    private String czas_teraz_3os_lm;
    private String czas_przesz_1os_lp_m;
    private String czas_przesz_2os_lp_m;
    private String czas_przesz_3os_lp_m;
    private String czas_przesz_1os_lm_m;
    private String czas_przesz_2os_lm_m;
    private String czas_przesz_3os_lm_m;
    private String czas_przesz_1os_lp_f;
    private String czas_przesz_2os_lp_f;
    private String czas_przesz_3os_lp_f;
    private String czas_przesz_1os_lm_fn;
    private String czas_przesz_2os_lm_fn;
    private String czas_przesz_3os_lm_fn;
    private String czas_przesz_1os_lp_n;
    private String czas_przesz_2os_lp_n;
    private String czas_przesz_3os_lp_n;
    private String form_bezosob_czasu_przesz_123os_lp_lm;
    private String tryb_rozkaz_1os_lp;
    private String tryb_rozkaz_2os_lp;
    private String tryb_rozkaz_3os_lp;
    private String tryb_rozkaz_1os_lm;
    private String tryb_rozkaz_2os_lm;
    private String tryb_rozkaz_3os_lm;
    private String tryb_przyp_1os_lp_m;
    private String tryb_przyp_2os_lp_m;
    private String tryb_przyp_3os_lp_m;
    private String tryb_przyp_1os_lm_m;
    private String tryb_przyp_2os_lm_m;
    private String tryb_przyp_3os_lm_m;
    private String tryb_przyp_1os_lp_f;
    private String tryb_przyp_2os_lp_f;
    private String tryb_przyp_3os_lp_f;
    private String tryb_przyp_1os_lm_fn;
    private String tryb_przyp_2os_lm_fn;
    private String tryb_przyp_3os_lm_fn;
    private String tryb_przyp_1os_lp_n;
    private String tryb_przyp_2os_lp_n;
    private String tryb_przyp_3os_lp_n;
    private String imies_przym_czynny_123os_lp_lm_m;
    private String imies_przym_czynny_123os_lp_f;
    private String imies_przym_czynny_123os_fn_lm;
    private String imies_przym_czynny_1os_n;
    private String imies_przym_czynny_2os_n;
    private String imies_przym_czynny_3os_n;
    private String imies_przym_bierny_123os_lp_m;
    private String imies_przym_bierny_123os_lm_m;
    private String imies_przym_bierny_123os_lp_f;
    private String imies_przym_bierny_123os_lm_fn;
    private String imies_przym_bierny_1os_lp_n;
    private String imies_przym_bierny_2os_lp_n;
    private String imies_przym_bierny_3os_lp_n;
    private String imies_przys_wpolcz_123os_lp_lm;
    private String imies_przys_uprzed_123os_lp_lm;
    private String rzecz_odczas;

    public ConjugationGenerator(VerbVar czasOdm, String koniugacja) {
        this.czasOdm = czasOdm;
        this.koniugacja = ConjugationType.enumOf(koniugacja);
        temat = czasOdm.getTopic();
    }

    public ConjugationGenerator(VerbVar czasOdm) {
        this.czasOdm = czasOdm;
        this.koniugacja = ConjugationType.enumOf(czasOdm.getConjugation());
        temat = czasOdm.getTopic();
    }

    public void fillForm() {
        KoniugacjaForm koniugacjaForm;
        switch (koniugacja) {
            case I: {
                koniugacjaForm = new Koniugacja1Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case II: {
                koniugacjaForm = new Koniugacja2Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case III: {
                koniugacjaForm = new Koniugacja3Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case IV: {
                koniugacjaForm = new Koniugacja4Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Va: {
                koniugacjaForm = new Koniugacja51Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Vb: {
                koniugacjaForm = new Koniugacja52Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Vc: {
                koniugacjaForm = new Koniugacja53Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIa: {
                koniugacjaForm = new Koniugacja61Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIb: {
                koniugacjaForm = new Koniugacja62Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIIa: {
                koniugacjaForm = new Koniugacja71Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIIb: {
                koniugacjaForm = new Koniugacja72Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIIIa: {
                koniugacjaForm = new Koniugacja81Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case VIIIb: {
                koniugacjaForm = new Koniugacja82Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case IX: {
                koniugacjaForm = new Koniugacja9Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Xa: {
                koniugacjaForm = new Koniugacja101Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Xb: {
                koniugacjaForm = new Koniugacja102Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case Xc: {
                koniugacjaForm = new Koniugacja103Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            case XI: {
                koniugacjaForm = new Koniugacja11Form(this);
                koniugacjaForm.fillKoniugacja();
                break;
            }
            default:
                break;
        }
    }

    public void fill() {
        czasOdm.setInfinitive(temat + bezokol);
        PersonVar czasTeraz = new PersonVar();
        czasTeraz.setPer1Sin(temat + czas_teraz_1os_lp);
        czasTeraz.setPer2Sin(temat + czas_teraz_2os_lp);
        czasTeraz.setPer3Sin(temat + czas_teraz_3os_lp);
        czasTeraz.setPer1Plu(temat + czas_teraz_1os_lm);
        czasTeraz.setPer2Plu(temat + czas_teraz_2os_lm);
        czasTeraz.setPer3Plu(temat + czas_teraz_3os_lm);
        czasTeraz.setVarType(PersonVarType.CZAS_TERAZ);
        czasTeraz.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(czasTeraz);

        PersonVar czasPrzeszlyM = new PersonVar();
        czasPrzeszlyM.setPer1Sin(temat + czas_przesz_1os_lp_m);
        czasPrzeszlyM.setPer2Sin(temat + czas_przesz_2os_lp_m);
        czasPrzeszlyM.setPer3Sin(temat + czas_przesz_3os_lp_m);
        czasPrzeszlyM.setPer1Plu(temat + czas_przesz_1os_lm_m);
        czasPrzeszlyM.setPer2Plu(temat + czas_przesz_2os_lm_m);
        czasPrzeszlyM.setPer3Plu(temat + czas_przesz_3os_lm_m);
        czasPrzeszlyM.setVarType(PersonVarType.CZAS_PRZESZ_M);
        czasPrzeszlyM.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(czasPrzeszlyM);

        PersonVar czasPrzeszlyF = new PersonVar();
        czasPrzeszlyF.setPer1Sin(temat + czas_przesz_1os_lp_f);
        czasPrzeszlyF.setPer2Sin(temat + czas_przesz_2os_lp_f);
        czasPrzeszlyF.setPer3Sin(temat + czas_przesz_3os_lp_f);
        czasPrzeszlyF.setPer1Plu(temat + czas_przesz_1os_lm_fn);
        czasPrzeszlyF.setPer2Plu(temat + czas_przesz_2os_lm_fn);
        czasPrzeszlyF.setPer3Plu(temat + czas_przesz_3os_lm_fn);
        czasPrzeszlyF.setVarType(PersonVarType.CZAS_PRZESZ_F);
        czasPrzeszlyF.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(czasPrzeszlyF);

        PersonVar czasPrzeszlyN = new PersonVar();
        czasPrzeszlyN.setPer1Sin(temat + czas_przesz_1os_lp_n);
        czasPrzeszlyN.setPer2Sin(temat + czas_przesz_2os_lp_n);
        czasPrzeszlyN.setPer3Sin(temat + czas_przesz_3os_lp_n);
        czasPrzeszlyN.setPer1Plu(temat + czas_przesz_1os_lm_fn);
        czasPrzeszlyN.setPer2Plu(temat + czas_przesz_2os_lm_fn);
        czasPrzeszlyN.setPer3Plu(temat + czas_przesz_3os_lm_fn);
        czasPrzeszlyN.setVarType(PersonVarType.CZAS_PRZESZ_N);
        czasPrzeszlyN.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(czasPrzeszlyN);

        czasOdm.setImpersonalFormPast(temat + form_bezosob_czasu_przesz_123os_lp_lm);

        PersonVar trybRozkaz = new PersonVar();
        trybRozkaz.setPer1Sin(tryb_rozkaz_1os_lp);
        trybRozkaz.setPer2Sin(temat + tryb_rozkaz_2os_lp);
        trybRozkaz.setPer3Sin(tryb_rozkaz_3os_lp);
        trybRozkaz.setPer1Plu(temat + tryb_rozkaz_1os_lm);
        trybRozkaz.setPer2Plu(temat + tryb_rozkaz_2os_lm);
        trybRozkaz.setPer3Plu(tryb_rozkaz_3os_lm);
        trybRozkaz.setVarType(PersonVarType.TRYB_ROZKAZ);
        trybRozkaz.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(trybRozkaz);

        PersonVar trybPrzypM = new PersonVar();
        trybPrzypM.setPer1Sin(temat + tryb_przyp_1os_lp_m);
        trybPrzypM.setPer2Sin(temat + tryb_przyp_2os_lp_m);
        trybPrzypM.setPer3Sin(temat + tryb_przyp_3os_lp_m);
        trybPrzypM.setPer1Plu(temat + tryb_przyp_1os_lm_m);
        trybPrzypM.setPer2Plu(temat + tryb_przyp_2os_lm_m);
        trybPrzypM.setPer3Plu(temat + tryb_przyp_3os_lm_m);
        trybPrzypM.setVarType(PersonVarType.TRYB_PRZYP_M);
        trybPrzypM.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(trybPrzypM);

        PersonVar trybPrzypF = new PersonVar();
        trybPrzypF.setPer1Sin(temat + tryb_przyp_1os_lp_f);
        trybPrzypF.setPer2Sin(temat + tryb_przyp_2os_lp_f);
        trybPrzypF.setPer3Sin(temat + tryb_przyp_3os_lp_f);
        trybPrzypF.setPer1Plu(temat + tryb_przyp_1os_lm_fn);
        trybPrzypF.setPer2Plu(temat + tryb_przyp_2os_lm_fn);
        trybPrzypF.setPer3Plu(temat + tryb_przyp_3os_lm_fn);
        trybPrzypF.setVarType(PersonVarType.TRYB_PRZYP_F);
        trybPrzypF.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(trybPrzypF);

        PersonVar trybPrzypN = new PersonVar();
        trybPrzypN.setPer1Sin(temat + tryb_przyp_1os_lp_n);
        trybPrzypN.setPer2Sin(temat + tryb_przyp_2os_lp_n);
        trybPrzypN.setPer3Sin(temat + tryb_przyp_3os_lp_n);
        trybPrzypN.setPer1Plu(temat + tryb_przyp_1os_lm_fn);
        trybPrzypN.setPer2Plu(temat + tryb_przyp_2os_lm_fn);
        trybPrzypN.setPer3Plu(temat + tryb_przyp_3os_lm_fn);
        trybPrzypN.setVarType(PersonVarType.TRYB_PRZYP_N);
        trybPrzypN.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(trybPrzypN);

        PersonVar imiesPrzymCzynnyM = new PersonVar();
        imiesPrzymCzynnyM.setPer1Sin(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setPer2Sin(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setPer3Sin(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setPer1Plu(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setPer2Plu(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setPer3Plu(temat + imies_przym_czynny_123os_lp_lm_m);
        imiesPrzymCzynnyM.setVarType(PersonVarType.IMIES_PRZYM_CZYNNY_M);
        imiesPrzymCzynnyM.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymCzynnyM);

        PersonVar imiesPrzymCzynnyF = new PersonVar();
        imiesPrzymCzynnyF.setPer1Sin(temat + imies_przym_czynny_123os_lp_f);
        imiesPrzymCzynnyF.setPer2Sin(temat + imies_przym_czynny_123os_lp_f);
        imiesPrzymCzynnyF.setPer3Sin(temat + imies_przym_czynny_123os_lp_f);
        imiesPrzymCzynnyF.setPer1Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyF.setPer2Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyF.setPer3Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyF.setVarType(PersonVarType.IMIES_PRZYM_CZYNNY_F);
        imiesPrzymCzynnyF.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymCzynnyF);

        PersonVar imiesPrzymCzynnyN = new PersonVar();
        imiesPrzymCzynnyN.setPer1Sin(imies_przym_czynny_1os_n);
        imiesPrzymCzynnyN.setPer2Sin(imies_przym_czynny_2os_n);
        imiesPrzymCzynnyN.setPer3Sin(temat + imies_przym_czynny_3os_n);
        imiesPrzymCzynnyN.setPer1Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyN.setPer2Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyN.setPer3Plu(temat + imies_przym_czynny_123os_fn_lm);
        imiesPrzymCzynnyN.setVarType(PersonVarType.IMIES_PRZYM_CZYNNY_N);
        imiesPrzymCzynnyN.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymCzynnyN);

        PersonVar imiesPrzymBiernyM = new PersonVar();
        imiesPrzymBiernyM.setPer1Sin(temat + imies_przym_bierny_123os_lp_m);
        imiesPrzymBiernyM.setPer2Sin(temat + imies_przym_bierny_123os_lp_m);
        imiesPrzymBiernyM.setPer3Sin(temat + imies_przym_bierny_123os_lp_m);
        imiesPrzymBiernyM.setPer1Plu(temat + imies_przym_bierny_123os_lm_m);
        imiesPrzymBiernyM.setPer2Plu(temat + imies_przym_bierny_123os_lm_m);
        imiesPrzymBiernyM.setPer3Plu(temat + imies_przym_bierny_123os_lm_m);
        imiesPrzymBiernyM.setVarType(PersonVarType.IMIES_PRZYM_BIERNY_M);
        imiesPrzymBiernyM.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymBiernyM);

        PersonVar imiesPrzymBiernyF = new PersonVar();
        imiesPrzymBiernyF.setPer1Sin(temat + imies_przym_bierny_123os_lp_f);
        imiesPrzymBiernyF.setPer2Sin(temat + imies_przym_bierny_123os_lp_f);
        imiesPrzymBiernyF.setPer3Sin(temat + imies_przym_bierny_123os_lp_f);
        imiesPrzymBiernyF.setPer1Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyF.setPer2Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyF.setPer3Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyF.setVarType(PersonVarType.IMIES_PRZYM_BIERNY_F);
        imiesPrzymBiernyF.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymBiernyF);

        PersonVar imiesPrzymBiernyN = new PersonVar();
        imiesPrzymBiernyN.setPer1Sin(imies_przym_bierny_1os_lp_n);
        imiesPrzymBiernyN.setPer2Sin(imies_przym_bierny_2os_lp_n);
        imiesPrzymBiernyN.setPer3Sin(temat + imies_przym_bierny_3os_lp_n);
        imiesPrzymBiernyN.setPer1Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyN.setPer2Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyN.setPer3Plu(temat + imies_przym_bierny_123os_lm_fn);
        imiesPrzymBiernyN.setVarType(PersonVarType.IMIES_PRZYM_BIERNY_N);
        imiesPrzymBiernyN.setVerbVar(czasOdm.getId());
        czasOdm.addPersonVar(imiesPrzymBiernyN);

        czasOdm.setAdverbialParticipleContemporary(temat + imies_przys_wpolcz_123os_lp_lm);
        czasOdm.setAdverbialParticiplePrior(temat + imies_przys_uprzed_123os_lp_lm);
        czasOdm.setGerund(temat + rzecz_odczas);
    }

    public void setBezokol(String bezokol) {
        this.bezokol = bezokol;
    }

    public void setCzas_teraz_1os_lp(String czas_teraz_1os_lp) {
        this.czas_teraz_1os_lp = czas_teraz_1os_lp;
    }

    public void setCzas_teraz_2os_lp(String czas_teraz_2os_lp) {
        this.czas_teraz_2os_lp = czas_teraz_2os_lp;
    }

    public void setCzas_teraz_3os_lp(String czas_teraz_3os_lp) {
        this.czas_teraz_3os_lp = czas_teraz_3os_lp;
    }

    public void setCzas_teraz_1os_lm(String czas_teraz_1os_lm) {
        this.czas_teraz_1os_lm = czas_teraz_1os_lm;
    }

    public void setCzas_teraz_2os_lm(String czas_teraz_2os_lm) {
        this.czas_teraz_2os_lm = czas_teraz_2os_lm;
    }

    public void setCzas_teraz_3os_lm(String czas_teraz_3os_lm) {
        this.czas_teraz_3os_lm = czas_teraz_3os_lm;
    }

    public void setCzas_przesz_1os_lp_m(String czas_przesz_1os_lp_m) {
        this.czas_przesz_1os_lp_m = czas_przesz_1os_lp_m;
    }

    public void setCzas_przesz_2os_lp_m(String czas_przesz_2os_lp_m) {
        this.czas_przesz_2os_lp_m = czas_przesz_2os_lp_m;
    }

    public void setCzas_przesz_3os_lp_m(String czas_przesz_3os_lp_m) {
        this.czas_przesz_3os_lp_m = czas_przesz_3os_lp_m;
    }

    public void setCzas_przesz_1os_lm_m(String czas_przesz_1os_lm_m) {
        this.czas_przesz_1os_lm_m = czas_przesz_1os_lm_m;
    }

    public void setCzas_przesz_2os_lm_m(String czas_przesz_2os_lm_m) {
        this.czas_przesz_2os_lm_m = czas_przesz_2os_lm_m;
    }

    public void setCzas_przesz_3os_lm_m(String czas_przesz_3os_lm_m) {
        this.czas_przesz_3os_lm_m = czas_przesz_3os_lm_m;
    }

    public void setCzas_przesz_1os_lp_f(String czas_przesz_1os_lp_f) {
        this.czas_przesz_1os_lp_f = czas_przesz_1os_lp_f;
    }

    public void setCzas_przesz_2os_lp_f(String czas_przesz_2os_lp_f) {
        this.czas_przesz_2os_lp_f = czas_przesz_2os_lp_f;
    }

    public void setCzas_przesz_3os_lp_f(String czas_przesz_3os_lp_f) {
        this.czas_przesz_3os_lp_f = czas_przesz_3os_lp_f;
    }

    public void setCzas_przesz_1os_lm_fn(String czas_przesz_1os_lm_fn) {
        this.czas_przesz_1os_lm_fn = czas_przesz_1os_lm_fn;
    }

    public void setCzas_przesz_2os_lm_fn(String czas_przesz_2os_lm_fn) {
        this.czas_przesz_2os_lm_fn = czas_przesz_2os_lm_fn;
    }

    public void setCzas_przesz_3os_lm_fn(String czas_przesz_3os_lm_fn) {
        this.czas_przesz_3os_lm_fn = czas_przesz_3os_lm_fn;
    }

    public void setCzas_przesz_1os_lp_n(String czas_przesz_1os_lp_n) {
        this.czas_przesz_1os_lp_n = czas_przesz_1os_lp_n;
    }

    public void setCzas_przesz_2os_lp_n(String czas_przesz_2os_lp_n) {
        this.czas_przesz_2os_lp_n = czas_przesz_2os_lp_n;
    }

    public void setCzas_przesz_3os_lp_n(String czas_przesz_3os_lp_n) {
        this.czas_przesz_3os_lp_n = czas_przesz_3os_lp_n;
    }

    public void setForm_bezosob_czasu_przesz_123os_lp_lm(String form_bezosob_czasu_przesz_123os_lp_lm) {
        this.form_bezosob_czasu_przesz_123os_lp_lm = form_bezosob_czasu_przesz_123os_lp_lm;
    }

    public void setTryb_rozkaz_1os_lp(String tryb_rozkaz_1os_lp) {
        this.tryb_rozkaz_1os_lp = tryb_rozkaz_1os_lp;
    }

    public void setTryb_rozkaz_2os_lp(String tryb_rozkaz_2os_lp) {
        this.tryb_rozkaz_2os_lp = tryb_rozkaz_2os_lp;
    }

    public void setTryb_rozkaz_3os_lp(String tryb_rozkaz_3os_lp) {
        this.tryb_rozkaz_3os_lp = tryb_rozkaz_3os_lp;
    }

    public void setTryb_rozkaz_1os_lm(String tryb_rozkaz_1os_lm) {
        this.tryb_rozkaz_1os_lm = tryb_rozkaz_1os_lm;
    }

    public void setTryb_rozkaz_2os_lm(String tryb_rozkaz_2os_lm) {
        this.tryb_rozkaz_2os_lm = tryb_rozkaz_2os_lm;
    }

    public void setTryb_rozkaz_3os_lm(String tryb_rozkaz_3os_lm) {
        this.tryb_rozkaz_3os_lm = tryb_rozkaz_3os_lm;
    }

    public void setTryb_przyp_1os_lp_m(String tryb_przyp_1os_lp_m) {
        this.tryb_przyp_1os_lp_m = tryb_przyp_1os_lp_m;
    }

    public void setTryb_przyp_2os_lp_m(String tryb_przyp_2os_lp_m) {
        this.tryb_przyp_2os_lp_m = tryb_przyp_2os_lp_m;
    }

    public void setTryb_przyp_3os_lp_m(String tryb_przyp_3os_lp_m) {
        this.tryb_przyp_3os_lp_m = tryb_przyp_3os_lp_m;
    }

    public void setTryb_przyp_1os_lm_m(String tryb_przyp_1os_lm_m) {
        this.tryb_przyp_1os_lm_m = tryb_przyp_1os_lm_m;
    }

    public void setTryb_przyp_2os_lm_m(String tryb_przyp_2os_lm_m) {
        this.tryb_przyp_2os_lm_m = tryb_przyp_2os_lm_m;
    }

    public void setTryb_przyp_3os_lm_m(String tryb_przyp_3os_lm_m) {
        this.tryb_przyp_3os_lm_m = tryb_przyp_3os_lm_m;
    }

    public void setTryb_przyp_1os_lp_f(String tryb_przyp_1os_lp_f) {
        this.tryb_przyp_1os_lp_f = tryb_przyp_1os_lp_f;
    }

    public void setTryb_przyp_2os_lp_f(String tryb_przyp_2os_lp_f) {
        this.tryb_przyp_2os_lp_f = tryb_przyp_2os_lp_f;
    }

    public void setTryb_przyp_3os_lp_f(String tryb_przyp_3os_lp_f) {
        this.tryb_przyp_3os_lp_f = tryb_przyp_3os_lp_f;
    }

    public void setTryb_przyp_1os_lm_fn(String tryb_przyp_1os_lm_fn) {
        this.tryb_przyp_1os_lm_fn = tryb_przyp_1os_lm_fn;
    }

    public void setTryb_przyp_2os_lm_fn(String tryb_przyp_2os_lm_fn) {
        this.tryb_przyp_2os_lm_fn = tryb_przyp_2os_lm_fn;
    }

    public void setTryb_przyp_3os_lm_fn(String tryb_przyp_3os_lm_fn) {
        this.tryb_przyp_3os_lm_fn = tryb_przyp_3os_lm_fn;
    }

    public void setTryb_przyp_1os_lp_n(String tryb_przyp_1os_lp_n) {
        this.tryb_przyp_1os_lp_n = tryb_przyp_1os_lp_n;
    }

    public void setTryb_przyp_2os_lp_n(String tryb_przyp_2os_lp_n) {
        this.tryb_przyp_2os_lp_n = tryb_przyp_2os_lp_n;
    }

    public void setTryb_przyp_3os_lp_n(String tryb_przyp_3os_lp_n) {
        this.tryb_przyp_3os_lp_n = tryb_przyp_3os_lp_n;
    }

    public void setImies_przym_czynny_123os_lp_lm_m(String imies_przym_czynny_123os_lp_lm_m) {
        this.imies_przym_czynny_123os_lp_lm_m = imies_przym_czynny_123os_lp_lm_m;
    }

    public void setImies_przym_czynny_123os_lp_f(String imies_przym_czynny_123os_lp_f) {
        this.imies_przym_czynny_123os_lp_f = imies_przym_czynny_123os_lp_f;
    }

    public void setImies_przym_czynny_123os_fn_lm(String imies_przym_czynny_123os_fn_lm) {
        this.imies_przym_czynny_123os_fn_lm = imies_przym_czynny_123os_fn_lm;
    }

    public void setImies_przym_czynny_1os_n(String imies_przym_czynny_1os_n) {
        this.imies_przym_czynny_1os_n = imies_przym_czynny_1os_n;
    }

    public void setImies_przym_czynny_2os_n(String imies_przym_czynny_2os_n) {
        this.imies_przym_czynny_2os_n = imies_przym_czynny_2os_n;
    }

    public void setImies_przym_czynny_3os_n(String imies_przym_czynny_3os_n) {
        this.imies_przym_czynny_3os_n = imies_przym_czynny_3os_n;
    }

    public void setImies_przym_bierny_123os_lp_m(String imies_przym_bierny_123os_lp_m) {
        this.imies_przym_bierny_123os_lp_m = imies_przym_bierny_123os_lp_m;
    }

    public void setImies_przym_bierny_123os_lm_m(String imies_przym_bierny_123os_lm_m) {
        this.imies_przym_bierny_123os_lm_m = imies_przym_bierny_123os_lm_m;
    }

    public void setImies_przym_bierny_123os_lp_f(String imies_przym_bierny_123os_lp_f) {
        this.imies_przym_bierny_123os_lp_f = imies_przym_bierny_123os_lp_f;
    }

    public void setImies_przym_bierny_123os_lm_fn(String imies_przym_bierny_123os_lm_fn) {
        this.imies_przym_bierny_123os_lm_fn = imies_przym_bierny_123os_lm_fn;
    }

    public void setImies_przym_bierny_1os_lp_n(String imies_przym_bierny_1os_lp_n) {
        this.imies_przym_bierny_1os_lp_n = imies_przym_bierny_1os_lp_n;
    }

    public void setImies_przym_bierny_2os_lp_n(String imies_przym_bierny_2os_lp_n) {
        this.imies_przym_bierny_2os_lp_n = imies_przym_bierny_2os_lp_n;
    }

    public void setImies_przym_bierny_3os_lp_n(String imies_przym_bierny_3os_lp_n) {
        this.imies_przym_bierny_3os_lp_n = imies_przym_bierny_3os_lp_n;
    }

    public void setImies_przys_wpolcz_123os_lp_lm(String imies_przys_wpolcz_123os_lp_lm) {
        this.imies_przys_wpolcz_123os_lp_lm = imies_przys_wpolcz_123os_lp_lm;
    }

    public void setImies_przys_uprzed_123os_lp_lm(String imies_przys_uprzed_123os_lp_lm) {
        this.imies_przys_uprzed_123os_lp_lm = imies_przys_uprzed_123os_lp_lm;
    }

    public void setRzecz_odczas(String rzecz_odczas) {
        this.rzecz_odczas = rzecz_odczas;
    }
}
