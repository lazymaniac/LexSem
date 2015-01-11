package pl.semantyk.utils;


public class StringUtils {

    public static String removeNumeration(String input) {
        return input.replaceAll(input.startsWith(":") ? "(: \\([0-9\\.\\-,]+\\))" : "\\([0-9\\.\\-,]+\\)", "");
    }

    public static String removeWikiRefs(String input) {
        return input.replaceAll("(<ref[a-zA-Z0-9ąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\{\\}\\|\\s=\\?'`_~\\.,:%&\\(\\)\\$\\-\\[\\]\"<>/]+ref>)", "");
    }

    public static String removeApostrphes(String input) {
        return input.replaceAll("('')", "");
    }

    public static String removeAdditionalComment(String input) {
        return input.replaceAll("(''[a-zA-Z0-9ąćęłńóśźżĄĆĘŁŃÓŚŹŻ,\\.\\s]+'')", "");
    }

    public static String getStringAfterEqual(String input) {
        String temp = input.replaceAll("[\\s\\|]+", "");
        String[] splited = temp.trim().split("=");
        if (splited.length > 1) {
            return splited[1];
        }
        return "";
    }

    public static String getStringBeforeEqual(String input) {
        String temp = input.replaceAll("[\\s\\|]+", "");
        String[] splited = temp.split("=");
        return splited[0];
    }

    public static String removeParenthesis(String input) {
        return input.replaceAll("[\\{\\}\\(\\)\\[\\]]+", "");
    }

    public static String removePunctuation(String input) {
        return input.replaceAll("[,\\.;:\\'\\/\\\']+", "");
    }

    public static String removeWikiText(String input) {
        return input.replace("wikipedia|", "");
    }

    public static String removeReferences(String input) {
        String noRef = removeWikiRefs(input);
        return removeWikiText(noRef);
    }
    
    public static String removePersonTag(String input) {
    	return input.replaceAll("(\\{\\{[nfm]+\\}\\})", "");
    }

    //TEST
    public static void main(String[] args) {
        String temp = "asdasd<ref>{{PoradniaPWN|id=13645|hasło=zdrobnienia czy zgrubienia?}}</ref>";
        String clean = removeReferences(temp);
        System.out.println(clean);
    }
}
