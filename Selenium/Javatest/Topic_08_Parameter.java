package Javatest;

public class Topic_08_Parameter {

    static String fullNameGlobal = "Automaiton Testing";

    public static void main(String[] arg){
        // ĐỐi số ( lôi ra dùng)
        setFullName("Manual Testing");
        System.out.println(getFullName());
    }

    public static  void setFullName(String fullName) { // Tham số - khai báo

        fullNameGlobal =fullName;
    }
    public static String getFullName(){

        return fullNameGlobal;
    }
}
