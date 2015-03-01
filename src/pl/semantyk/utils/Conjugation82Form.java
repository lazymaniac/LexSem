package pl.semantyk.utils;

public class Conjugation82Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation82Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillConjugation() {
        koniugacjaGenerator.setBezokol("iwać");
        koniugacjaGenerator.setCzas_teraz_1os_lp("uję");
        koniugacjaGenerator.setCzas_teraz_2os_lp("ujesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("uje");
        koniugacjaGenerator.setCzas_teraz_1os_lm("ujemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ujecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ują");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("iwałem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("iwałeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("iwał");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("iwaliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("iwaliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("iwali");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("iwałam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("iwałaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("iwała");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("iwałyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("iwałyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("iwały");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("iwało");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("iwano");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("uj");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("ujmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("ujcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("iwałbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("iwałbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("iwałby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("iwalibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("iwalibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("iwaliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("iwałabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("iwałabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("iwałaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("iwałybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("iwałybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("iwałyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("iwałoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ujący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ująca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ujące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ujące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("iwany");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("iwani");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("iwana");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("iwane");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("iwane");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ując");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("awszy");
        koniugacjaGenerator.setRzecz_odczas("iwanie");
        koniugacjaGenerator.fill();
    }
}