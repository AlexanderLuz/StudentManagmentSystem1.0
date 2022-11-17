public enum Teacher {
    HAGRID("Instructor", "Hagrid"),
    DUMBLEDORE("Professor Dr. Dr. Dr.", "Dumbledore"),
    UMBRIDGE("Associate Professor Dr.", "Umbridge"),
    SNAPE("Research Professor Dr. Dr.", "Snape");

    private final String Title;
    private final String Name;

    private Teacher(String Title, String Name) {
        this.Title = Title;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public String getTitle() {
        return Title;
    }

    public String getFullName() {
        return Title+" "+Name;
    }
}
