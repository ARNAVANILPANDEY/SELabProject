import javax.swing.*;
import java.awt.*;

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
        JLabel  l2=new JLabel("MKC");
        JTextField t1=new JTextField(10);
        JButton b1=new JButton("Find");
        JButton b2=new JButton("Cancel Request");
        JPanel p1=new JPanel(new GridBagLayout());
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
        c.gridx=1;
        c.gridy=2;
        c.insets=new Insets(20,5,10,5);
        p1.add(l2,c);
        c.gridx=1;
        c.gridy=3;
        c.insets=new Insets(5,5,10,5);

        p1.add(b2,c);
        add(p1);



        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
