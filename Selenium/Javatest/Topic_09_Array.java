package Javatest;

public class Topic_09_Array {

    static int[] studentAge = {15,16,17,18,19,20};
    static String[] studentName = {"Nguyễn Văn An","Lê Văn Hòa"};

    public static void main(String[] arg){
       String[] studentAddress = new String[5];

        studentAddress[0] = "Đặng Ngọc Anh";
        studentAddress[1] = "Trương Học Mỹ";
        studentAddress[2] = "Nguyễn Anh Quân";
        studentAddress[3] = "Tống Minh Đạt";
        studentAddress[4] = "Lê Thu Nga";

        System.out.println(studentName[0]);
        System.out.println(studentName[1]);

        for (int i =0; i< studentAddress.length; i++)
        {
            System.out.println(studentAddress[i]);
        }



    }

}
