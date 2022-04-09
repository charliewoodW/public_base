import java.util.Scanner;

public class example_2 {
    public static void main(String[] args) {
        Scanner s1 =new Scanner(System.in);
        System.out.println("请输入身份证号：");
        String id =s1.next();
        filter f1=new filter(id);
        f1.showid();
        f1.check();
    }
}

class filter{
    private String id;
    private String regex ="^([1-6][1-9]|50)\\d{4}\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}$";
    public filter(String id_){
        id=id_;
    }
    public void showid(){
        System.out.println(id);
    }
    public void check(){
        if (id.length()==15){
            check1();
        }
        else if (id.length()==18){
            check2();
        }
        else {
            System.out.println("位数不符");
        }
    }
    private void check1(){
        if ( id.matches(regex)){
            System.out.println("号码有效");
        }
        else {
            System.out.println("格式不符");
        }
    }
    private void  check2(){
        if(id.length() == 18){
            char[] id1 = id.toCharArray();
            //∑(ai×Wi)(mod 11)
            //加权因子
            int[] factor ={7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            //校验位
            char[] parity = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
            int sum = 0;
            for (int i = 0; i < 17; i++)
            {
                sum += Integer.parseInt(String.valueOf(id1[i])) * factor[i];
                System.out.println(sum);
            }
            if(parity[sum % 11] == String.valueOf(id1[17]).toUpperCase().toCharArray()[0] || parity[sum % 11] == id1[17]){
                System.out.println("号码有效");
            }
            else {
                System.out.println("校验不符");
            }
        }
    }
}
//33042119800811451X
//670311770706001