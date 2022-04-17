import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BillButton
{
    public static void main(String[] args)
    {
//        Vector<Item>v=new Vector<Item>();
//        Item i1=new Item("Operating Systems","918736473",2, 300.00F,600.00F);
//        Item i2=new Item("DSA","918736456",3, 400.00F,1200.00F);
//        v.addElement(i1);
//        bill b=new bill("1011",String.valueOf(java.time.LocalDate.now()),"Arnav",v,10,600.00F);
//        b.addBook(i2);
//        System.out.println(b.items.elementAt(0).bookName);
//        System.out.println(b.items.elementAt(1).bookName);
//        b.remBook(i2);
//        System.out.println(b.items.elementAt(0).bookName);
//        //System.out.println(b.items.elementAt(1).bookName);
        billFrame bf=new billFrame("emp1011","Avinash");


    }
}

class billFrame extends JFrame
{
    public billFrame(String empID, String empName)
    {
        Vector<Item>v=new Vector<Item>();
        bill Bill=new bill(empID,String.valueOf(java.time.LocalDate.now()),empName,v,25,0F);

        JPanel p=new JPanel(new GridBagLayout());
        JPanel p1=new JPanel(new GridBagLayout());
        JPanel p2=new JPanel(new GridBagLayout());
        JPanel p3=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(10,30,10,30);
        GridBagConstraints c3=new GridBagConstraints();
        c3.insets=new Insets(10,30,10,30);
        JLabel l1=new JLabel("Issuer ID: "+empID);
        JLabel l2=new JLabel("Issuer Name: "+empName);
        JLabel l3=new JLabel("Date: "+String.valueOf(java.time.LocalDate.now()));
        c.gridx=0;
        c.gridy=0;
        p1.add(l1,c);
        c.gridx=1;
        c.gridy=0;
        p1.add(l2,c);
        c.gridx=2;
        c.gridy=0;
        p1.add(l3,c);
        p.add(p1);

        JButton b1=new JButton("Add Item");
        JButton b2=new JButton("OK");
        JTextField t1=new JTextField(10);
        c.gridy=0;
        c.gridx=0;
        //p2.add(t1);
        c.gridy=0;
        c.gridx=1;
        //p2.add(b2);
        c.gridy=0;
        c.gridx=2;
       // p2.add(b1);

        //p.repaint();
        GridBagConstraints c2=new GridBagConstraints();
        c2.insets=new Insets(10,30,10,30);
        c2.gridx=1;
        c2.gridy=c.gridy;
        JTextField t=new JTextField(10);
        JLabel L1=new JLabel("ISBN");
        JLabel L2=new JLabel("Quantity");
       // JTextField t=new JTextField(10);
        JTextField T=new JTextField(10);
        c.gridx=0;
        c.gridy=0;
        p2.add(L1,c);
        c.gridx=1;
        c.gridy=0;
        p2.add(t,c);
        c.gridx=2;
        c.gridy=0;
        p2.add(L2,c);
        c.gridx=3;
        c.gridy=0;
        p2.add(T,c);
        c.gridx=0;
        c.gridy=1;
        p.add(p2,c);
        c3.gridx=0;
        c3.gridy=0;
        p3.add(b1,c3);
        c.gridx=0;
        c.gridy=2;
        p.add(p3,c);
        add(p);


        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                //System.out.println("hello2");
                c3.gridy++;
                Item s;
                try {

                    s=dao(t.getText(),T.getText());
                    Bill.addBook(s);
                    //t.setVisible(false);
                    //T.setVisible(false);
                    //L1.setVisible(false);
                    //L2.setVisible(false);
                    String str="ISBN: "+s.isbn+"  Name: "+s.bookName+"  Quantity: "+s.quantity+"  Rate: "+s.rate+"  Amount:"+s.amount;
                    JLabel ll=new JLabel(str);
                    b1.setVisible(false);
                    p3.add(ll,c3);
                    c3.gridy++;
                    p3.add(b1,c3);
                    b1.setVisible(true);
                   // p3.repaint();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });






        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public Item dao(String isbn,String quan) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="SELECT * FROM booktab where isbn =?";
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,isbn);
        ResultSet rs=st.executeQuery();
        rs.next();
        Integer Quantity=Integer.parseInt(quan);
        Float Rate=rs.getFloat("price");
        Float Amount=Quantity*Rate;
        Item res = new Item(rs.getString("name"),isbn,Integer.parseInt(quan),rs.getFloat("price"),Amount);



        query="UPDATE booktab SET soldcopy=soldcopy+? where isbn=?";
        PreparedStatement st2=con.prepareStatement(query);
        st2.setInt(1,res.quantity);
        st2.setString(2,isbn);
        Integer e=st2.executeUpdate();

        return res;


    }
}
