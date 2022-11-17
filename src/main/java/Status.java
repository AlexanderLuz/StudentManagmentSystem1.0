public enum Status {
    TUITION_PAYED("Tuition Payed"),
    TUITION_NOT_PAYED("Tuition not Payed");

    private final String Name;

    private Status(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }
}
