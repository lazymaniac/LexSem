package pl.semantyk.utils;

public class Koniugacja4Form implements KoniugacjaForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Koniugacja4Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillKoniugacja() {
        koniugacjaGenerator.setBezokol("ować");
        koniugacjaGenerator.setCzas_teraz_1os_lp("uję");
        koniugacjaGenerator.setCzas_teraz_2os_lp("ujesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("uje");
        koniugacjaGenerator.setCzas_teraz_1os_lm("ujemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ujecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ują");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("owałem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("owałeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ował");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("owaliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("owaliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("owali");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("owałam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("owałaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("owała");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("owałyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("owałyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("owały");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("owałom");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("owałoś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("owało");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("owano");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("uj");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("ujmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("ujcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("owałbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("owałbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("owałby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("owalibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("owalibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("owaliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("owałabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("owałabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("owałaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("owałybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("owałybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("owałyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("owałobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("owałobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("owałoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ujący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ująca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ujące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ujące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("owany");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("owani");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("owana");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("owane");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("owane");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ując");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("owawszy");
        koniugacjaGenerator.setRzecz_odczas("owanie");
        koniugacjaGenerator.fill();
    }
}