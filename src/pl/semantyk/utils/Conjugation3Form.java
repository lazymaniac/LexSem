package pl.semantyk.utils;

public class Conjugation3Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation3Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillConjugation() {
        koniugacjaGenerator.setBezokol("eć");
        koniugacjaGenerator.setCzas_teraz_1os_lp("eję");
        koniugacjaGenerator.setCzas_teraz_2os_lp("ejesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("eje");
        koniugacjaGenerator.setCzas_teraz_1os_lm("ejemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ejecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("eją");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("ałem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("ałeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ał");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("eliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("eliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("eli");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("ałam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("ałaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("ała");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("ałyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("ałyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("ały");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("ałom");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("ałoś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("ało");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ano");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("ej");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("ejmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("ejcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("ałbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("ałbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("ałby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("elibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("elibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("eliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("ałabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("ałabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("ałaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("ałybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("ałybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("ałyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("ałobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("ałobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("ałoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ejący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ejąca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ejące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ejące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("ały");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("ały");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("ały");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("ały");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("ały");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("ały");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("ały");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ejąc");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("awszy");
        koniugacjaGenerator.setRzecz_odczas("enie");
        koniugacjaGenerator.fill();
    }
}