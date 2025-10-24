import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class first extends JFrame {
    private DefaultListModel<String> emailListModel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField tfMail;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                first frame = new first();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public first() {
        // Set up the window
        setTitle("Contact Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Name panel
        JPanel namePanel = new JPanel();
        namePanel.setBorder(new TitledBorder(new EtchedBorder(), "Name"));
        namePanel.setLayout(new GridLayout(2, 2, 5, 5));

        contentPane.add(namePanel);
        
        JPanel data = new JPanel();
        namePanel.add(data);
        data.setLayout(new GridLayout(2, 4, 0, 0));
        
        JLabel label = new JLabel("First Name:");
        data.add(label);
        
        textField = new JTextField();
        data.add(textField);
        
        JLabel label_1 = new JLabel("Last Name:");
        data.add(label_1);
        
        textField_1 = new JTextField();
        data.add(textField_1);
        
        JLabel label_2 = new JLabel("Title:");
        data.add(label_2);
        
        textField_2 = new JTextField();
        data.add(textField_2);
        
        JLabel label_3 = new JLabel("Nickname:");
        data.add(label_3);
        
        textField_3 = new JTextField();
        data.add(textField_3);
        
        JPanel displayFormat = new JPanel();
        namePanel.add(displayFormat);
        displayFormat.setLayout(new BoxLayout(displayFormat, BoxLayout.X_AXIS));
        
        JLabel lblDisplayFormat = new JLabel("Display Format:");
        displayFormat.add(lblDisplayFormat);
        
        JComboBox cbDisplayFormat = new JComboBox();
        cbDisplayFormat.setFont(new Font("Tahoma", Font.PLAIN, 11));
        displayFormat.add(cbDisplayFormat);

        // Email list
        emailListModel = new DefaultListModel<>();
        
        JPanel mailPanel = new JPanel();
        mailPanel.setBorder(new TitledBorder(null, "E-mail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(mailPanel);
        mailPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel mailInput = new JPanel();
        mailPanel.add(mailInput);
        
        JLabel lblMailAddr = new JLabel("E-mail Address:");
        mailInput.add(lblMailAddr);
        
        tfMail = new JTextField();
        mailInput.add(tfMail);
        tfMail.setColumns(10);
        
        JPanel buttons = new JPanel();
        mailPanel.add(buttons);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        
        JButton btnAdd = new JButton("Add");
        buttons.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit");
        buttons.add(btnEdit);
        
        JButton btnRemove = new JButton("Remove");
        buttons.add(btnRemove);
        
        JButton btnAdd_1_1_1 = new JButton("Add");
        buttons.add(btnAdd_1_1_1);
        
        JPanel botonera = new JPanel();
        mailPanel.add(botonera);
        botonera.setLayout(new BoxLayout(botonera, BoxLayout.Y_AXIS));
        
        JPanel buttonPanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) buttonPanel.getLayout();
        flowLayout_1.setAlignment(FlowLayout.RIGHT);
        flowLayout_1.setVgap(1);
        contentPane.add(buttonPanel);
        
        JButton okButton = new JButton("OK");
        buttonPanel.add(okButton);
        
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
    }
}
