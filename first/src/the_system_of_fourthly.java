import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class the_system_of_fourthly {
    public static void main(String[] args) {
        windogoods win = new windogoods();
        win.setTitle("商品的录入与显示");
    }
}

class Goods implements java.io.Serializable {
    String name, mount, price;

    public void setName(String name) {
        this.name = name;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getMount() {
        return mount;
    }

    public String getPrice() {
        return price;
    }
}

class inputarea extends JPanel implements ActionListener {
    File f = null;
    Box baseBox, boxV1, boxV2;
    JTextField name, mount, price;
    JButton button;
    LinkedList<Goods> goodsList;

    inputarea(File f) {
        this.f = f;
        goodsList = new LinkedList<Goods>();
        name = new JTextField(12);
        mount = new JTextField(12);
        price = new JTextField(12);
        button = new JButton("录入");
        button.addActionListener(this);
        boxV1 = Box.createVerticalBox();
        boxV1.add(new JLabel("输入名称"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("输入库存"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("输入单价"));
        boxV1.add(Box.createVerticalStrut(8));
        boxV1.add(new JLabel("单击录入"));

        boxV2 = Box.createVerticalBox();
        boxV2.add(name);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(mount);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(price);
        boxV2.add(Box.createVerticalStrut(8));
        boxV2.add(button);
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        add(baseBox);
    }

    public void actionPerformed(ActionEvent e) {
        if (f.exists()) {
            try {
                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream oi = new ObjectInputStream(fi);
                goodsList = (LinkedList<Goods>) oi.readObject();
                fi.close();
                oi.close();
                Goods goods = new Goods();
                goods.setName(name.getText());
                goods.setMount(mount.getText());
                goods.setPrice(price.getText());
                goodsList.add(goods);
                FileOutputStream fo = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(goodsList);
                out.close();
            } catch (Exception ee) {
            }
        } else {
            try {
                f.createNewFile();
                Goods goods = new Goods();
                goods.setName(name.getText());
                goods.setMount(mount.getText());
                goods.setPrice(price.getText());
                goodsList.add(goods);
                FileOutputStream fo = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(goodsList);
                out.close();
            } catch (Exception ee) {
            }
        }
    }
}

class windogoods extends JFrame implements ActionListener {
    File file = null;
    JMenuBar bar;
    JMenu filemenu;
    JMenuItem 录入, 显示;
    JTextArea show;
    inputarea inputMessage;
    JPanel pCenter;
    JTable table;
    Object 表格单元[][], 列名[] = {"名称", "库存", "菜单"};
    CardLayout card;

    windogoods() {
        file = new File("库存.txt");
        录入 = new JMenuItem("录入");
        显示 = new JMenuItem("显示");
        bar = new JMenuBar();
        filemenu = new JMenu("菜单选项");
        filemenu.add(录入);
        filemenu.add(显示);
        bar.add(filemenu);
        setJMenuBar(bar);
        录入.addActionListener(this);
        显示.addActionListener(this);
        inputMessage = new inputarea(file);
        card = new CardLayout();
        pCenter = new JPanel();
        pCenter.setLayout(card);
        pCenter.add("录入", inputMessage);
        add(pCenter, BorderLayout.CENTER);
        setVisible(true);
        setBounds(100, 50, 420, 380);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == 录入) {
            card.show(pCenter, "录入");
        } else if (e.getSource() == 显示) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);
                LinkedList<Goods> goodsList = (LinkedList<Goods>) oi.readObject();
                fi.close();
                oi.close();
                int lenght = goodsList.size();
                表格单元 = new Object[lenght][3];
                table = new JTable(表格单元, 列名);
                pCenter.removeAll();
                pCenter.add("录入", inputMessage);
                pCenter.add("显示", new JScrollPane(table));
                pCenter.validate();
                Iterator<Goods> iter = goodsList.iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Goods 商品 = iter.next();
                    表格单元[i][0] = 商品.getName();
                    表格单元[i][1] = 商品.getMount();
                    表格单元[i][2] = 商品.getPrice();
                    i++;
                }
                table.repaint();
            } catch (Exception ee) {
            }
            card.show(pCenter, "显示");
        }
    }
}































