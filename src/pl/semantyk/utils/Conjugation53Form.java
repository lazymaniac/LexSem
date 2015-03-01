package pl.semantyk.utils;

public class Conjugation53Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation53Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillConjugation() {
        koniugacjaGenerator.setBezokol("nąć");
        koniugacjaGenerator.setCzas_teraz_1os_lp("nę");
        koniugacjaGenerator.setCzas_teraz_2os_lp("niesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("nie");
        koniugacjaGenerator.setCzas_teraz_1os_lm("niemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("niecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("ną");
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
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("nięto");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("nij");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("nijmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("nijcie");
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
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("nący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("nąca");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("nące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("nące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("nięty");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("nięci");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("nięta");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("nięte");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("nięte");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("nąc");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("łwszy");
        koniugacjaGenerator.setRzecz_odczas("nięcie");
        koniugacjaGenerator.fill();
    }
}