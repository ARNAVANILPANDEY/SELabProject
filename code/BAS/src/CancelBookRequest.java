import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

public class CancelBookRequest
{
    public static void main(String[] args)
    {
        CancelRequest cr=new CancelRequest();
    }
}

class CancelRequest extends JFrame
{
    public CancelRequest()
    {

        JLabel l1=new JLabel("Book Name");
        JLabel  l2=new JLabel("");
        JLabel l3=new JLabel("");
        JTextField t1=new JTextField(10);
        JButton b1=new JButton("Find");
        JButton b2=new JButton("Cancel Request");
        JPanel p=new JPanel(new GridBagLayout());
        JPanel p1=new JPanel(new GridBagLayout());
        JPanel p2=new JPanel(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.insets=new Insets(10,5,10,5);
        c.gridx=0;
        c.gridy=0;
        p1.add(l1,c);
        c.gridx=1;
        c.gridy=0;
        p1.add(t1);
        c.gridx=2;
        c.gridy=0;
        p1.add(b1,c);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(20,5,10,5);
        p2.add(l2,c);
        c.gridx=0;
        c.gridy=1;
        c.insets=new Insets(5,5,10,5);
        p2.add(b2,c);
        c.gridx=0;
        c.gridy=2;
        p2.add(l3,c);
        final Vector<String>[] s1 = new Vector[]{new Vector<String>()};
        Vector<String> S=new Vector<String>();
        Vector<String>SS=new Vector<String>();
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                        s1[0] =dao(t1.getText());


//                    String newline =System.lineSeparator();
                    l2.setText(s1[0].elementAt(0)+", "+ s1[0].elementAt(1)+", "+ s1[0].elementAt(2)+
                            ", "+ s1[0].elementAt(3)+", "+ s1[0].elementAt(4));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    dao(Integer.parseInt(s1[0].elementAt(0)));
                    l3.setText("Request Removed Successfully");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        c.gridx=0;
        c.gridy=0;
        p.add(p1,c);

        c.gridx=0;
        c.gridy=2;
        p.add(p2,c);
        add(p);




        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Vector<String> dao(String s) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="SELECT * FROM reqtab where book =?";
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,s);
        ResultSet rs=st.executeQuery();
        rs.next();
        Vector<String>res=new Vector<String>();
        res.addElement(rs.getString(1));
        res.addElement(rs.getString(2));
        res.addElement(rs.getString(3));
        res.addElement(rs.getString(4));
        res.addElement(rs.getString(5));
        return res;
    }
    public void dao(Integer id) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="DELETE FROM reqtab where reqid =?";
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,id);
        Integer i=st.executeUpdate();

    }
}
