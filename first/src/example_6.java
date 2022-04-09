import javax.swing.text.html.HTMLDocument;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class example_6 {
    public static void main(String[] args) {
        String path_production="D:/ideaProjects/first/src/production.txt";
        String path_shoppinglist="D:/ideaProjects/first/src/shoppinglist.txt";
        shopping_list shopping_list_o=
                new shopping_list(shoppinglist_in(file_in(path_shoppinglist)));
        market market =new market(production_in(file_in(path_production)));
        market.show();
        shopping_list_o.show();
        shopping_car car =new shopping_car();
        car.shopping(market,shopping_list_o);
        car.pay();
        car.show();

    }
    public static String file_in(String file1){
        File file =new File(file1);
        String read_text="";
        BufferedReader fileReader;
        try {
            fileReader=new BufferedReader(new FileReader(file));
            String s =null;
            while ((s = fileReader.readLine())!=null){
                read_text=read_text+s+"\n";
            }
            fileReader.close();
        }
        catch (IOException e){
            System.out.println("文件读取失败");
        }
        return read_text;
    }
    public static List<object> production_in(String string){
        String[] stringline=string.split("\n");
        List<object> objectList =new ArrayList<object>();
        List<String> stringList =new ArrayList<String>();
        for (String str:stringline){
            String[] s =str.split(" ");
            stringList.clear();
            for (String s1:s){
                stringList.add(s1);
            }
            object object=new object(stringList);
            objectList.add(object);
        }
        return objectList;
    }
    public static List<shopping_target> shoppinglist_in(String string){
        String[] stringline=string.split("\n");
        List<shopping_target> shoppingList =new ArrayList<shopping_target>();
        List<String> stringList =new ArrayList<String>();
        for (String str:stringline){
            String[] s =str.split(" ");
            stringList.clear();
            for (String s1:s){
                stringList.add(s1);
            }
            shopping_target target=new shopping_target(stringList);
            shoppingList.add(target);
        }
        return shoppingList;
    }
}



class object{
    private int id;
    private String name;
    private String producter;
    private double price;
    private int kucun;
    private int shouchu;
    public object(List<String> product) {
        Iterator<String> iter = product.iterator();
        this.id = Integer.parseInt(iter.next());
        this.name = iter.next();
        this.producter = iter.next();
        this.price = Double.parseDouble(iter.next());
        this.kucun = Integer.parseInt(iter.next());
        this.shouchu =0;
    }

    public void show(){
        System.out.print("id: "+id+" 名字："+name+" 生产商："+producter+" 价格："
                +price+" 库存："+kucun+"\n");
    }
    public void show_pay(){
        System.out.print("id: "+id+" 名字："+name+" 生产商："+producter+" 价格："
                +price+" 库存："+kucun+" 实际购买数："+shouchu+"\n");
    }
    public void change_kucun(int a){
        if(this.kucun-a >=0){
            this.kucun=this.kucun-a;
        }
        else {
            System.out.println("商品："+name+" 库存不足！");
        }
    }
    public int getId(){return id;}
    public double getPrice(){return price;}
    public int getshouchu(){return shouchu;}
    public void setshouchu(int a){
        if (shouchu+a>=0){
            shouchu=shouchu+a;
            change_kucun(a);
        }
        else {
            change_kucun(-(shouchu));
            shouchu=0;
        }

    }

}
class shopping_target{
    private int id;
    private int number;
    private int token;
    public shopping_target(List<String> shopping_list) {
        Iterator<String> iter = shopping_list.iterator();
        this.id = Integer.parseInt(iter.next());
        this.number = Integer.parseInt(iter.next());
        this.token = Integer.parseInt(iter.next());
    }

    public void show(){
        System.out.print("id: "+id+" 购买数量：" +number+" 购买标志："+token+"\n");
    }
    public int getId(){
        return id;
    }
    public int getNumber(){
        return number;
    }
    public int getToken(){
        return token;
    }
}
class shopping_list{
    private List<shopping_target> shopping_list=new ArrayList<shopping_target>();
    public shopping_list(List<shopping_target> shopping_list){
        this.shopping_list=shopping_list;
    }
    public void show(){
        System.out.println("\n*******************************购物清单如下：");
        for (shopping_target shopping_target:shopping_list){
            shopping_target.show();
        }
    }
    public List<shopping_target> get_list(){
        return this.shopping_list;
    }
}

class market{
    private List<object> objects_list=new ArrayList<object>();
    public market(List<object> objects_list){
        this.objects_list=objects_list;
    }
    public void show(){
        System.out.println("\n*******************************商城商品如下：");
        for (object object : objects_list) {
            object.show();
        }
    }
    public List<object> get_list(){
        return this.objects_list;
    }
}



class shopping_car{
    private List<object> fact_in_car;
    public shopping_car(){
        this.fact_in_car=new ArrayList<object>();
    }
    public void shopping(market market,shopping_list shopping_list){
        List<object> objectList =market.get_list();
        for (shopping_target shopping_target:shopping_list.get_list()){
            switch(shopping_target.getToken()){
                case 1:{
                    for (int i=0;i<objectList.size();i++){
                        object obj;
                        obj=objectList.get(i);
                        int flag=0;
                        if (obj.getId() == shopping_target.getId()){
//                            obj.change_kucun(shopping_target.getNumber());
                            objectList.set(i,obj);
                            obj.setshouchu(shopping_target.getNumber());
                            for (int j=0;j<fact_in_car.size();j++){
                                if (obj.getId() == fact_in_car.get(j).getId()) {
                                    fact_in_car.set(j,obj);
                                    flag+=1;
                                    break;
                                }
                            }
                            if (flag==0){
                                fact_in_car.add(obj);
                            }

                            break;
                        }
                    }
                }break;

                case 2:{
                    for (int i=0;i<objectList.size();i++){
                        object obj;
                        obj=objectList.get(i);
                        if (obj.getId() == shopping_target.getId()){
//                            obj.change_kucun(-shopping_target.getNumber());
                            objectList.set(i,obj);
                            obj.setshouchu(-shopping_target.getNumber());
                            for (int j=0;j<fact_in_car.size();j++){
                                if (obj.getId() == fact_in_car.get(j).getId()) {
                                    fact_in_car.set(j,obj);
                                    break;
                                }
                            }
                        }
                    }
                }break;

                case 3:{
                    for (int i=0;i<fact_in_car.size();i++){
                        if (fact_in_car.get(i).getId() == shopping_target.getId()){
                            fact_in_car.remove(i);
                            break;
                        }
                    }
                }break;

                default:break;
            }
        }
    }
    public double pay(){
        double pay_all=0.0;
        for (object object:fact_in_car){
            pay_all += object.getshouchu()*object.getPrice();
        }
        return pay_all;
    }
    public void show(){
        System.out.println("\n*******************************最终付款账单如下：");
        for (object object:fact_in_car){
            if (object.getshouchu()>0){
                object.show_pay();
            }

        }
        System.out.println("总金额 "+pay());
    }


}
