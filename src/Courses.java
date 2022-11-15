public enum Courses {
    HISTORY101("History-101", 600, 1),
    MATHEMATICS101("Mathematics-101", 800, 2),
    ENGLISH101("English-101", 400, 3),
    CHEMISTRY101("Chemistry-101", 750, 4),
    COMPUTER_SCIENCE101("Computer Science-101", 1500, 5);

    private final String Name;
    private final int Cost;
    private final int Index;

    private Courses(String Name, int Cost, int Index) {
        this.Name = Name;
        this.Cost = Cost;
        this.Index = Index;
    }

    public static Courses getCoursesEnum(int index) {
        return switch(index) {
            case 1 -> HISTORY101;
            case 2 -> MATHEMATICS101;
            case 3 -> ENGLISH101;
            case 4 -> CHEMISTRY101;
            default -> COMPUTER_SCIENCE101;
        };
    }

    public String getName() {
        return Name;
    }

    public int getCost() {
        return Cost;
    }

    public int getIndex() {
        return Index;
    }
}
