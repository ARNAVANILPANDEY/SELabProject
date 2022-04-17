import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstFrame
{
    public static void main(String[] args)
    {
        utils obj= new utils("    Employee","      Name");
    }
}

class utils extends JFrame
{
    public utils(String str,String empName)
    {
        JPanel p=new JPanel(new GridBagLayout());
        JPanel p2=new JPanel(new GridBagLayout());
        JLabel l1=new JLabel("Welcome "+str);
        JButton b1;
        JButton b2;
        JButton b3;
        JButton b4;
        JButton b5;
        JButton b6;


        b1=new JButton("Query Book");
        b2=new JButton("Request Book");
        b3=new JButton("Cancel Book");
        b4=new JButton("Billing");
        b5=new JButton("Update Inventory");
        b6=new JButton("Generate Statistics");


        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(20,10,20,10);
        c.gridx=0;
        c.gridy=0;
        p2.add(l1,c);
        c.gridx=0;
        c.gridy=2;
        p.add(b1,c);
        c.gridx=2;
        c.gridy=2;
        p.add(b2,c);
        c.gridx=2;
        c.gridy=3;
        p.add(b3,c);
        c.gridx=0;
        c.gridy=3;
        p.add(b4,c);
        c.gridx=3;
        c.gridy=2;
        p.add(b5,c);
        c.gridx=3;
        c.gridy=3;
        p.add(b6,c);
        add(p2);
        add(p);

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    new QueryBook();
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
                    new ReqBook();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    new CancelRequest();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    new billFrame(str,empName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                new updateInventory();
            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
