public class example_3 {
    public static void main(String[] args) {
        putong_ren p1 =new putong_ren();
        p1.putong_canting();
        vip_ren v1 =new vip_ren();
        v1.vip_canting();
        shichi_ren s1 =new shichi_ren();
        s1.putong_canting();
        s1.vip_canting();
    }
}

interface putong{
    void putong_canting();
}

interface vip{
    void vip_canting();
}

interface try_people extends putong,vip{
    void putong_canting();
    void vip_canting();
}

class putong_ren implements putong{
    putong_ren(){}
    public void putong_canting(){
        System.out.println("我是普通用户，在普通餐厅");
    }
}
class vip_ren implements vip{
    vip_ren(){}
    public void vip_canting(){
        System.out.println("我是vip用户，在vip餐厅");
    }
}
class shichi_ren implements try_people{
    shichi_ren(){}
    public void putong_canting(){
        System.out.println("我是试吃用户，在普通餐厅");
    }
    public void vip_canting(){
        System.out.println("我是试吃用户，在vip餐厅");
    }
}


