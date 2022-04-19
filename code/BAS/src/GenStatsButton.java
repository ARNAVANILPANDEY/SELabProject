import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class GenStatsButton
{
    public static void main(String[] args)
    {
        GenStats gs=new GenStats("ID","USER");
    }
}

class GenStats extends JFrame
{
    public GenStats(String str,String empName)
    {

        JPanel p=new JPanel(new GridBagLayout());
        JPanel p1=new JPanel(new GridBagLayout());
        JPanel p2=new JPanel(new GridBagLayout());



        GridBagConstraints c1=new GridBagConstraints();
        GridBagConstraints c2=new GridBagConstraints();

        c1.insets=new Insets(10,10,10,10);
        c2.insets=new Insets(10,20,10,10);


        JLabel l1=new JLabel("Statistics");
        JLabel l2=new JLabel("Book Name");
        JLabel l3=new JLabel("Quantity");
        JLabel l5=new JLabel("Author");
        c1.gridx=0;
        c1.gridy=0;
        p1.add(l1,c1);
        c2.gridx=0;
        c2.gridy=0;
        p2.add(l2,c2);
        c2.gridx++;
        p2.add(l3,c2);
        c2.gridx++;
        p2.add(l5);
        c2.gridx=0;
        c2.gridy=1;

        Vector<JLabel>vec=new Vector<JLabel>();

        ResultSet RS=null;
        try {
             RS=dao();
             while (RS.next())
             {
                 String s=RS.getString(1);
                 JLabel ll=new JLabel(s);
                 vec.addElement(ll);
                 p2.add(vec.lastElement(),c2);
                 JLabel ll2=new JLabel(RS.getString(2));
                 vec.addElement(ll2);
                 c2.gridx++;
                 p2.add(vec.lastElement(),c2);
                 JLabel ll3=new JLabel(RS.getString(3));
                 vec.addElement(ll3);
                 c2.gridx++;
                 p2.add(vec.lastElement(),c2);
                 c2.gridx--;
                 c2.gridx--;
                 c2.gridy++;
             }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        
        int N = vec.size() / 3;

        String dat[][]=new String[N][3];
        String[] strdat={"Book Name", "Sold Copies","Author"};
        int j=0;
        for (int i=0;i< vec.size();++i)
        {
            if (i%3==0 && i>0)
                ++j;

            dat[j][i%3]=vec.elementAt(i).getText();

        }

        JTable jt=new JTable(dat,strdat);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);


        p.add(p1,c1);
        c1.gridy++;
        p.add(sp,c1);

        // p.add(p2,c1);
        add(p);


        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        new utils(str,empName);
    }

    public ResultSet dao() throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="SELECT name,soldcopy,author FROM booktab";
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);

        return rs;

    }
}
