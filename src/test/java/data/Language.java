package data;

public enum Language {

    EN("en-gb"),
    ES("es"),
    NL("nl");

    private String lang;

    Language(String lang) {
        this.lang = lang;
    }

    public String value() {
        return lang;
    }
}
