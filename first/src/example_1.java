public class example_1 {
    public static void main(String[] args) {
        MobileShop shop1=new MobileShop("no1");
        shop1.show();
    }
}

class MobileShop{
    private String shopname;
    public MobileShop(String name){
        shopname=name;
    }
    class InnerPurchaseMoney{
        public String PurchaseMoney;
        public InnerPurchaseMoney(){
            PurchaseMoney=shopname+"'s PurchaseMoney";
        }
        public void show(){
            System.out.println(PurchaseMoney);
        }

    }
    public void show(){
        InnerPurchaseMoney inner1=new InnerPurchaseMoney();
        inner1.show();
    }
}