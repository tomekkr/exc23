package pl.tk.exc23;

public enum Category {
    GROCERIES("spozywcze"),
    HOUSEHOLD("domowe"),
    OTHER("inne");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
