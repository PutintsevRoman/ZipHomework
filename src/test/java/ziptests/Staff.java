package ziptests;

public class Staff {
    private String name;
    private int age;
    private boolean coolboy;
    private String[] skills;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCoolboy(boolean coolboy) {
        this.coolboy = coolboy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public static class Id {
        private Integer number;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

    public boolean isCoolboy() {
        return coolboy;
    }
    public static boolean checkStaff(Staff staff1,Staff staff2){
        String ex = staff1.getName();
        String [] skills1 = staff1.getSkills();
        String [] skills2 = staff2.getSkills();

        if (staff1.getAge()!=staff2.getAge()) return false;
        if (ex.equals(staff2.getName())) return false;

        if (checkStrings(skills1,skills2)) return false;
        return true;
    }
    public static boolean checkStrings(String [] skills1,String [] skills2){
        if(skills1.equals(skills2));
        return true;
    }
}
