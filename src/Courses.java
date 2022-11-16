public enum Courses {
    HISTORY101("History-101", 600, 1, Teacher.UMBRIDGE, Year.FRESHMAN),
    MATHEMATICS101("Mathematics-101", 800, 2, Teacher.DUMBLEDORE, Year.FRESHMAN),
    ENGLISH101("English-101", 400, 3, Teacher.HAGRID, Year.FRESHMAN),
    CHEMISTRY101("Chemistry-101", 750, 4, Teacher.SNAPE, Year.FRESHMAN),
    COMPUTER_SCIENCE101("Computer Science-101", 1500, 5, Teacher.DUMBLEDORE, Year.FRESHMAN);

    private final String Name;
    private final int Cost;
    private final int Index;
    private final Teacher Prof;
    private final Year MinimumYear;

    private Courses(String Name, int Cost, int Index, Teacher Prof, Year MinimumYear) {
        this.Name = Name;
        this.Cost = Cost;
        this.Index = Index;
        this.Prof = Prof;
        this.MinimumYear = MinimumYear;
    }

    public static Courses getEnum(int index) {
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

    public Teacher getProf() {
        return Prof;
    }

    public Year getMinimumYear() {
        return MinimumYear;
    }

    public void printCourseInfo() {
        System.out.println(getIndex()+". "+getName()+" | "+getCost()+" | "+getProf().getFullName()+" | "+getMinimumYear().getName());
    }
}
