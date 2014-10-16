package pl.semantyk.utils;

public class Koniugacja11Form implements KoniugacjaForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Koniugacja11Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillKoniugacja() {
        koniugacjaGenerator.setBezokol("ć");
        koniugacjaGenerator.setCzas_teraz_1os_lp("ę");
        koniugacjaGenerator.setCzas_teraz_2os_lp("esz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("e");
        koniugacjaGenerator.setCzas_teraz_1os_lm("emy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("ecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ą");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("łem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("łeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ł");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("liśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("liście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("li");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("łam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("łaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("ła");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("łyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("łyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("ły");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("ło");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ono");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("~");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("my");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("cie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("łbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("łbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("łby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("libyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("libyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("liby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("łabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("łabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("łaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("łybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("łybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("łyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("łoby");
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
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("łszy");
        koniugacjaGenerator.setRzecz_odczas("enie");
        koniugacjaGenerator.fill();
    }
}