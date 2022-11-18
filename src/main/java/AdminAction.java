public enum AdminAction {
    SHOW_STUDENT_ACTIONS(1),
    STUDENT_LIST(1),
    STUDENT_BALANCE(2),
    STUDENT_ID(3),
    STUDENT_COURSES(4),
    SHOW_ADMIN_ACTIONS(2),
    ADJUST_STUDENT_BALANCE(1),
    CHANGE_STUDENT_COURSES(2),
    ADJUST_STUDENT_YEAR(3);

    private final int Index;
    AdminAction(int Index) {
        this.Index = Index;
    }

    public AdminAction getMasterAction(int index) {
        return switch(index) {
            case 1 -> SHOW_STUDENT_ACTIONS;
            default -> SHOW_ADMIN_ACTIONS;
        };
    }
}
