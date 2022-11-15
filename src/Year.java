public enum Year {
    FRESHMAN("Freshman", 1),
    SOPHOMORE("Sophomore", 2),
    JUNIOR("Junior", 3),
    SENIOR("Senior", 4);

    private final String Name;
    private final int Year;

    private Year(String Name, int Year) {
        this.Name = Name;
        this.Year = Year;
    }

    public int getYear() {
        return Year;
    }

    public String getName() {
        return Name;
    }

    public static Year getEnum(int i) {
        return switch(i) {
            case 1 -> FRESHMAN;
            case 2 -> SOPHOMORE;
            case 3 -> JUNIOR;
            default -> SENIOR;
        };
    }
}
