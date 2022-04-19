import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReqBookButton
{
    public static void main(String[] args)
    {
        ReqBook rq=new ReqBook("id","name");

    }
}

class ReqBook extends JFrame
{
    public ReqBook(String str,String empName)
    {

        JPanel p1=new JPanel(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(10,5,10,5);

        JLabel l1=new JLabel("Book Name");
        JLabel l2=new JLabel("Author Name");
        JLabel l3=new JLabel("Requesting Person");
        JLabel l0=new JLabel("Request Book");
        JLabel l4=new JLabel("");

        JTextField t1=new JTextField(10);
        JTextField t2=new JTextField(10);
        JTextField t3=new JTextField(10);

        JButton b1=new JButton("Submit Request");

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                try {
                    if (!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals(""))
                    {
                        dao(t1.getText(), t2.getText(), t3.getText());
                        l4.setText("Request added Successfully");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        c.gridx=3;
        c.gridy=0;
        p1.add(l0,c);
        c.gridx=0;
        c.gridy=1;
        p1.add(l1,c);
        c.gridx=1;
        c.gridy=1;
        p1.add(t1,c);
        c.gridx=2;
        c.gridy=1;
        p1.add(l2,c);
        c.gridx=3;
        c.gridy=1;
        p1.add(t2,c);
        c.gridx=4;
        c.gridy=1;
        p1.add(l3,c);
        c.gridx=5;
        c.gridy=1;
        p1.add(t3,c);
        c.gridx=3;
        c.gridy=2;
        p1.add(b1,c);
        c.gridx=3;
        c.gridy=3;
        p1.add(l4,c);

        add(p1);






        setLayout(new FlowLayout());
        setVisible(true);
        setSize(800, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        new utils(str,empName);
    }

    public void dao(String book_name, String book_author, String client) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        String inpQuery="SELECT max(reqid) from reqtab";
        String query = "INSERT INTO reqtab VALUES(?,?,?,?,?)";
        Connection con= DriverManager.getConnection(url,uname,pass);
        Statement st1= con.createStatement();
        ResultSet rs=st1.executeQuery(inpQuery);
        rs.next();
        Integer i=rs.getInt(1);
        PreparedStatement st=con.prepareStatement(query);
        st.setInt(1,i+1);
        st.setString(2,book_name);
        st.setString(3,book_author);
        st.setString(4,client);
        String d= String.valueOf(java.time.LocalDate.now());
        st.setString(5,d);
        st.executeUpdate();

    }
}