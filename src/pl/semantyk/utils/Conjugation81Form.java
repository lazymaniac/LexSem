package pl.semantyk.utils;

public class Conjugation81Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation81Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillConjugation() {
        koniugacjaGenerator.setBezokol("ywać");
        koniugacjaGenerator.setCzas_teraz_1os_lp("uję");
        koniugacjaGenerator.setCzas_teraz_2os_lp("ujesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("uje");
        koniugacjaGenerator.setCzas_teraz_1os_lm("ujemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ujecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ują");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("ywałem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("ywałeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ywał");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("ywaliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("ywaliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("ywali");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("ywałam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("ywałaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("ywała");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("ywałyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("ywałyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("ywały");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("ywało");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ywano");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("uj");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("ujmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("ujcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("ywałbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("ywałbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("ywałby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("ywalibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("ywalibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("ywaliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("ywałabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("ywałabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("ywałaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("ywałybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("ywałybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("ywałyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("ywałobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("ywałobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("ywałoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ujący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ująca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ujące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ujące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("ywany");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("ywani");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("ywana");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("ywane");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("ywane");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ując");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("awszy");
        koniugacjaGenerator.setRzecz_odczas("ywanie");
        koniugacjaGenerator.fill();
    }
}