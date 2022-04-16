import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class QueryBookButton
{
    public static void main(String[] args) throws Exception
    {
        QueryBook qb=new QueryBook();
    }
}

class QueryBook extends JFrame
{
    public QueryBook() throws Exception
    {
        JPanel p1=new JPanel(new GridBagLayout());

        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(10,5,10,5);

        JLabel l1=new JLabel("Book Name");
        JTextField t1=new JTextField(10);
        JTextField t2=new JTextField(10);
        JLabel l2=new JLabel("Author");
        JButton b1=new JButton("OK");

        c.gridx=0;
        c.gridy=0;
        p1.add(l1,c);
        c.gridx=1;
        c.gridy=0;
        p1.add(t1,c);
        c.gridx=2;
        c.gridy=0;
        p1.add(l2,c);
        c.gridx=3;
        c.gridy=0;
        p1.add(t2,c);
        c.gridx=4;
        c.gridy=0;
        p1.add(b1,c);

        add(p1);

        JPanel p2=new JPanel(new GridBagLayout());
        JLabel h1=new JLabel("Position");
        JLabel h2=new JLabel("Quantity");
        JLabel h3=new JLabel("");
        JLabel h4=new JLabel("");
        GridBagConstraints d=new GridBagConstraints();
        d.insets=new Insets(20,50,10,50);
        d.gridx=0;
        d.gridy=0;
        p2.add(h1,d);
        d.gridx=1;
        d.gridy=0;
        p2.add(h2,d);
        d.gridx = 0;
        d.gridy = 1;
        p2.add(h3, d);
        d.gridx = 1;
        d.gridy = 1;
        p2.add(h4, d);
        add(p2);




        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {

                dao v;
                try {
                    v=new dao(t1.getText(),t2.getText());
                    //System.out.println(v.place+" "+ v.quan  );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (v.place !=null && v.quan!=null)
                {
                    h3.setText(v.place);
                    h4.setText(v.quan);

//                    p2.validate();
//                    p2.repaint();
                }
                else
                {
                    h3.setText("NULL");
                    h4.setText("NULL");
                }
            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class dao
{
    String place;
    String quan;
    public dao(String s1, String s2) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        String query="SELECT * FROM booktab WHERE name = ? and author = ?";
        Connection con= DriverManager.getConnection(url,uname,pass);
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,s1);
        st.setString(2,s2);
        ResultSet rs= st.executeQuery();
        while (rs.next())
        {
            this.place=rs.getString("place");
            this.quan=rs.getString("quant");
            //System.out.println(this.place+" "+this.quan);
        }
        st.close();
        con.close();

    }
}
