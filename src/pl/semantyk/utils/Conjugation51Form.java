package pl.semantyk.utils;

public class Conjugation51Form implements ConjugationForm {
    private final ConjugationGenerator koniugacjaGenerator;

    public Conjugation51Form(ConjugationGenerator koniugacjaGenerator) {
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
        koniugacjaGenerator.setCzas_przesz_1os_lp_m("nąłem");
        koniugacjaGenerator.setCzas_przesz_2os_lp_m("nąłeś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_m("nął");
        koniugacjaGenerator.setCzas_przesz_1os_lm_m("nęliśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_m("nęliście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_m("nęli");
        koniugacjaGenerator.setCzas_przesz_1os_lp_f("nęłam");
        koniugacjaGenerator.setCzas_przesz_2os_lp_f("nęłaś");
        koniugacjaGenerator.setCzas_przesz_3os_lp_f("nęła");
        koniugacjaGenerator.setCzas_przesz_1os_lm_fn("nęłyśmy");
        koniugacjaGenerator.setCzas_przesz_2os_lm_fn("nęłyście");
        koniugacjaGenerator.setCzas_przesz_3os_lm_fn("nęły");
        koniugacjaGenerator.setCzas_przesz_1os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_2os_lp_n("-");
        koniugacjaGenerator.setCzas_przesz_3os_lp_n("nęło");
        koniugacjaGenerator.setForm_bezosob_czasu_przesz_123os_lp_lm("nięto");
        koniugacjaGenerator.setTryb_rozkaz_1os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_2os_lp("nij");
        koniugacjaGenerator.setTryb_rozkaz_3os_lp("-");
        koniugacjaGenerator.setTryb_rozkaz_1os_lm("nijmy");
        koniugacjaGenerator.setTryb_rozkaz_2os_lm("nijcie");
        koniugacjaGenerator.setTryb_rozkaz_3os_lm("-");
        koniugacjaGenerator.setTryb_przyp_1os_lp_m("nąłbym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_m("nąłbyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_m("nąłby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_m("nęlibyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_m("nęlibyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_m("nęaliby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_f("nęłabym");
        koniugacjaGenerator.setTryb_przyp_2os_lp_f("nęłabyś");
        koniugacjaGenerator.setTryb_przyp_3os_lp_f("nęłaby");
        koniugacjaGenerator.setTryb_przyp_1os_lm_fn("nęłybyśmy");
        koniugacjaGenerator.setTryb_przyp_2os_lm_fn("nęłybyście");
        koniugacjaGenerator.setTryb_przyp_3os_lm_fn("nęłyby");
        koniugacjaGenerator.setTryb_przyp_1os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_2os_lp_n("-");
        koniugacjaGenerator.setTryb_przyp_3os_lp_n("nęłoby");
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
        koniugacjaGenerator.setImies_przys_uprzed_123os_lp_lm("nąwszy");
        koniugacjaGenerator.setRzecz_odczas("nięcie");
        koniugacjaGenerator.fill();
    }
}