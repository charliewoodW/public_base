import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class example_5 {
    public static void main(String[] args) {
        try_code();
    }
    public static void try_code(){
        try {
            int a=39;
            int b =0;
            int c =a/b;
        }
        catch (ArithmeticException e){
            System.out.println("除数不可为0");
        }
        finally {
            System.out.println("除法完成");
        }

    }
}



