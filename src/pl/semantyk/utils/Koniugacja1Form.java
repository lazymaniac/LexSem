package pl.semantyk.utils;

public class Koniugacja1Form implements KoniugacjaForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Koniugacja1Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillKoniugacja() {
        koniugacjaGenerator.setBezokol("ać");
        koniugacjaGenerator.setCzas_teraz_1os_lp("am");
        koniugacjaGenerator.setCzas_teraz_2os_lp("asz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("a");
        koniugacjaGenerator.setCzas_teraz_1os_lm("amy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("acie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ają");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("ałem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("ałeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ał");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("aliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("aliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("ali");
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
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("aj");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("ajmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("ajcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("ałbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("ałbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("ałby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("alibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("alibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("aliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("ałabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("ałabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("ałaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("ałybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("ałybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("ałyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("ałobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("ałobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("ałoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ający");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ająca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ające");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ające");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("any");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("ani");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("ana");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("ane");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("ane");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ając");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("awszy");
        koniugacjaGenerator.setRzecz_odczas("anie");
        koniugacjaGenerator.fill();
    }
}