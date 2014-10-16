package pl.semantyk.utils;

public class Koniugacja61Form implements KoniugacjaForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Koniugacja61Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillKoniugacja() {
        koniugacjaGenerator.setBezokol("ić");
        koniugacjaGenerator.setCzas_teraz_1os_lp("ę");
        koniugacjaGenerator.setCzas_teraz_2os_lp("isz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("i");
        koniugacjaGenerator.setCzas_teraz_1os_lm("imy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("icie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ą");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("iłem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("iłeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ił");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("iliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("iliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("ili");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("iłam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("iłaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("iła");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("iłyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("iłyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("iły");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("iło");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ono");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("my");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("cie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("iłbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("iłbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("iłby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("ilibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("ilibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("iliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("iłabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("iłabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("iłaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("iłybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("iłybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("iłyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("iłobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("iłobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("iłoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("ący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("ąca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("ące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("ące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("ony");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("eni");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("ona");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("one");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("one");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("ąc");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("iwszy");
        koniugacjaGenerator.setRzecz_odczas("enie");
        koniugacjaGenerator.fill();
    }
}