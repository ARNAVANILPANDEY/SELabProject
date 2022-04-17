import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;


public class mainFrame
{
    public static void main(String[] args) throws Exception
    {


        //System.out.println("LIST: "+empList);


        login obj=new login();

    }
}

class login extends JFrame
{

    public login() throws Exception
    {
        Vector<employee> empList=new Vector<employee>();
        String url="jdbc:mysql://localhost:3306/BAS";
        String uname="root";
        String pass="Anusakar@1234";
        String query="SELECT * FROM emptab";
        Connection con= DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();
        ResultSet rs= st.executeQuery(query);
        while (rs.next())
        {
            employee temp=new employee();
            temp.empID= rs.getString("empID");
            temp.pass=rs.getString("empPass");
            temp.empName=rs.getString("empName");
            empList.addElement(temp);

        }



        JTextField t1 = new JTextField(5);
        JLabel l1=new JLabel("USERID");
        JLabel logErr=new JLabel();
        JLabel l2=new JLabel("Password");
        JPasswordField t2 = new JPasswordField(5);
        JButton b1 = new JButton("Login");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(logErr);

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {

                String inputPass=new String(t2.getPassword());

                employee temp=new employee();
                temp.empID=t1.getText();
                temp.pass=inputPass;
                int ind=0;
                for (int i=0;i<empList.size();i++)
                {
                    if (empList.elementAt(i).empID.equals(temp.empID) && empList.elementAt(i).pass.equals(temp.pass) )
                        ind=1;
                }
                if (ind==1)
                {
                    new utils(temp.empID,temp.empName);
                }
                else
                {
                    logErr.setText("Invalid Credentials!!");

                }


            }
        });

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
