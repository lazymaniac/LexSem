package pl.semantyk.utils;

public class Koniugacja62Form implements KoniugacjaForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Koniugacja62Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillKoniugacja() {
        koniugacjaGenerator.setBezokol("yć");
        koniugacjaGenerator.setCzas_teraz_1os_lp("ę");
        koniugacjaGenerator.setCzas_teraz_2os_lp("ysz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("y");
        koniugacjaGenerator.setCzas_teraz_1os_lm("ymy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ycie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ą");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("yłem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("yłeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ył");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("yliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("yliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("yli");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("yłam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("yłaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("yła");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("yłyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("yłyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("yły");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("yło");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ono");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("my");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("cie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("yłbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("yłbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("yłby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("ylibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("ylibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("yliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("yłabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("yłabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("yłaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("yłybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("yłybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("yłyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("yłobym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("yłobyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("yłoby");
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
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("ywszy");
        koniugacjaGenerator.setRzecz_odczas("enie");
        koniugacjaGenerator.fill();
    }
}