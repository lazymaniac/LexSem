package pl.semantyk.utils;

public class Conjugation103Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation103Form(ConjugationGenerator koniugacjaGenerator) {
        this.koniugacjaGenerator = koniugacjaGenerator;
    }

    @Override
    public void fillConjugation() {
        koniugacjaGenerator.setBezokol("ąć");
        koniugacjaGenerator.setCzas_teraz_1os_lp("mę|nę");
        koniugacjaGenerator.setCzas_teraz_2os_lp("miesz|niesz");
        koniugacjaGenerator.setCzas_teraz_3os_lp("mie|nie");
        koniugacjaGenerator.setCzas_teraz_1os_lm("miemy|niemy");
        koniugacjaGenerator.setCzas_teraz_2os_lm("miecie|niecie");
        koniugacjaGenerator.setCzas_teraz_3os_lm("mą|ną");
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("ąłem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("ąłeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("ął");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("ęliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("ęliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("ęli");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("ęłam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("ęłaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("ęła");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("ełyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("ęłyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("ły");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("ęło");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("ęto");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("~");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("my");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("cie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("ąłbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("ąłbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("ąłby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("ęlibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("ęlibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("ęliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("ęłabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("ęłabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("ęłaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("ęłybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("ęłybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("ęłyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("ęłoby");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_lm_m("mący|nący");
        koniugacjaGenerator.setImies_przym_czynny_123os_lp_f("mąca|nący");
        koniugacjaGenerator.setImies_przym_czynny_123os_fn_lm("mące|nące");
        koniugacjaGenerator.setImies_przym_czynny_1os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_2os_n("-");
        koniugacjaGenerator.setImies_przym_czynny_3os_n("mące|nące");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_m("ęty");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_m("ęci");
        koniugacjaGenerator.setImies_przym_bierny_123os_lp_f("ęta");
        koniugacjaGenerator.setImies_przym_bierny_123os_lm_fn("ęte");
        koniugacjaGenerator.setImies_przym_bierny_1os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_2os_lp_n("-");
        koniugacjaGenerator.setImies_przym_bierny_3os_lp_n("ęte");
        koniugacjaGenerator.setImies_przys_wpolcz_123os_lp_lm("mąc|nąc");
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("ąwszy");
        koniugacjaGenerator.setRzecz_odczas("ęcie");
        koniugacjaGenerator.fill();
    }
}