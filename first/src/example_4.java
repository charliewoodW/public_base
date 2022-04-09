public class example_4 {
    public static void main(String[] args) {
        putong_door d1 =new putong_door();
        d1.open();
        d1.close();
        baojingqi b1 = new baojingqi();
        b1.to_alarm();
        gaoji_door g1 = new gaoji_door();
        g1.open();
        g1.close();
        g1.to_alarm();
    }
}

abstract class door{
    public door(){}
    public abstract void open();
    public abstract void close();
}

interface alarm{
    void to_alarm();
}

class putong_door extends door{
    public putong_door(){}
    public void open(){
        System.out.println("i can open");
    }
    public void close(){
        System.out.println("i can close");
    }

}

class baojingqi implements alarm{
    public baojingqi(){}
    public void to_alarm(){
        System.out.println("i can alarm");
    }
}

class gaoji_door extends door implements alarm{
    public gaoji_door(){}
    public void open(){
        System.out.println("i can open");
    }
    public void close(){
        System.out.println("i can close");
    }
    public void to_alarm(){
        System.out.println("i can alarm");
    }
}


