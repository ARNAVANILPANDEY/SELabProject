import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateInventoryButton
{
    public static void main(String[] args)
    {
        updateInventory updInv=new updateInventory();
    }
}

class updateInventory extends JFrame
{
    public updateInventory()
    {
        JRadioButton r1 = new JRadioButton("Add Book");
        JRadioButton r2 = new JRadioButton("Remove Book");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JPanel p=new JPanel(new GridBagLayout());
        JPanel p0=new JPanel(new GridBagLayout());
        JPanel p1=new JPanel(new GridBagLayout());
        JPanel p2=new JPanel(new GridBagLayout());

        JButton b1=new JButton("Add Book");
        JButton b2=new JButton("Remove Book");
        JButton ok=new JButton("OK");

        JLabel l1=new JLabel("ISBN: ");
        JTextField t1=new JTextField(10);
        JLabel l2=new JLabel("Book Name: ");
        JTextField t2=new JTextField(10);
        JLabel l3=new JLabel("Author Name: ");
        JTextField t3=new JTextField(10);
        JLabel l4=new JLabel("Publication: ");
        JTextField t4=new JTextField(10);
        JLabel l5=new JLabel("Price ");
        JTextField t5=new JTextField(10);
        JLabel l6=new JLabel("Quantity: ");
        JTextField t6=new JTextField(10);
        JLabel l7=new JLabel("Place in Shop");
        JTextField t7=new JTextField(10);
        JLabel l8=new JLabel("");

        JLabel L1=new JLabel("ISBN ");
        JTextField T1=new JTextField(10);
        JLabel L2=new JLabel("");


        GridBagConstraints c=new GridBagConstraints();
        GridBagConstraints c1=new GridBagConstraints();
        GridBagConstraints c2=new GridBagConstraints();

        c.insets=new Insets(1,1,1,1);
        c1.insets=new Insets(10,10,10,10);
        c2.insets=new Insets(10,10,10,10);


        c1.gridx=0;
        c1.gridy=0;
        c2.gridx=0;
        c2.gridy=0;

        p0.add(r1,c);
        c1.gridx=1;
        p0.add(r2,c);
        c1.gridx=0;
        p.add(p0,c1);
        p1.add(l1,c1);
        c1.gridx=1;
        c1.gridy=0;
        p1.add(t1,c1);
        c1.gridx=2;
        c1.gridy=0;
        p1.add(l2,c1);
        c1.gridx=3;
        c1.gridy=0;
        p1.add(t2,c1);
        c1.gridx=0;
        c1.gridy=1;
        p1.add(l3,c1);
        c1.gridx=1;
        c1.gridy=1;
        p1.add(t3,c1);
        c1.gridx=2;
        c1.gridy=1;
        p1.add(l4,c1);
        c1.gridx=3;
        c1.gridy=1;
        p1.add(t4,c1);
        c1.gridx=0;
        c1.gridy=2;
        p1.add(l5,c1);
        c1.gridx=1;
        c1.gridy=2;
        p1.add(t5,c1);
        c1.gridx=2;
        c1.gridy=2;
        p1.add(l6,c1);
        c1.gridx=3;
        c1.gridy=2;
        p1.add(t6,c1);
        c1.gridx=1;
        c1.gridy=3;
        p1.add(l7,c1);
        c1.gridx=2;
        c1.gridy=3;
        p1.add(t7,c1);
        c1.gridx=0;
        c1.gridy=4;
        p1.add(l8,c1);
        c1.gridx=4;
        c1.gridy=3;
        p1.add(b1,c1);
        c1.gridx=0;
        c1.gridy=1;
        p.add(p1,c1);


        p2.add(L1,c2);
        c2.gridx=1;
        c2.gridy=0;
        p2.add(T1,c2);
        c2.gridx=0;
        c2.gridy=1;
        p2.add(b2);
        p2.add(L2);
        c2.gridx=0;
        c2.gridy=2;
        p.add(p2,c2);
        p2.setVisible(false);
        p1.setVisible(false);
        p0.add(ok);

        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (r1.isSelected())
                {
                    //System.out.println(r1.isSelected());
                    p2.setVisible(false);
                    p1.setVisible(true);
                    p.repaint();
                    p.validate();
                }
                if (r2.isSelected())
                {
                   // System.out.println(r2.isSelected());
                    p1.setVisible(false);
                    p2.setVisible(true);
                    p.repaint();
                    p.validate();
                }
            }
        });

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    daoAdd(t1.getText(),t2.getText(),t3.getText(),t4.getText(),
                            Float.parseFloat(t5.getText()),Integer.parseInt(t6.getText()),
                            0F,0,t7.getText());
                    l8.setText("!!! Book Added Successfully !!!");

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
                    daoRem(T1.getText());
                    L2.setText("!!! Book Removed !!!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        add(p);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





    }

    public void daoAdd(String isbn,String name, String author, String publication, Float price,
                       Integer quant, Float invlel, Integer soldCopy, String place) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="INSERT INTO booktab VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement st= con.prepareStatement(query);
        st.setString(1,isbn);
        st.setString(2,name);
        st.setString(3,author);
        st.setString(4,publication);
        st.setFloat(5,price);
        st.setInt(6,quant);
        st.setFloat(7,invlel);
        st.setInt(8,soldCopy);
        st.setString(9,place);
        Integer res=st.executeUpdate();
    }

    public void daoRem(String isbn) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        Connection con= DriverManager.getConnection(url,uname,pass);
        String query="DELETE FROM booktab WHERE isbn=?";
        PreparedStatement st= con.prepareStatement(query);
        st.setString(1,isbn);
        Integer res=st.executeUpdate();
    }
}

