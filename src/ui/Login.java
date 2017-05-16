package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import security.AutenticadorSGT;

public class Login extends JFrame {

	private static final long serialVersionUID = 284028857090210634L;
	
	private JTextField jtJogin;
	private JPasswordField jpassword;
	
	private JButton jbLogar, jbLimpar;
	
	private JPanel jpCenter, jpButtons;
	
	private JLabel jlMessage;
	
	private Login ref = null;
	@SuppressWarnings("unused")
	private ReportFrame report = null;
	
	public Login() {
		this.setTitle("SGT");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		jlMessage = new JLabel("", JLabel.CENTER);
		
		jpButtons = new JPanel( new FlowLayout() );
		
		jbLogar = new JButton("login");
		jbLogar.setMnemonic( KeyEvent.VK_L);
		jbLimpar = new JButton("limpar");
		jbLimpar.setMnemonic( KeyEvent.VK_R );
		
		jpButtons.add( jbLogar );
		jpButtons.add( jbLimpar );
		
		//layout dos campos
		jpCenter = new JPanel( new GridLayout(2,2) );
		
		jpCenter.add( new JLabel("usuario : ", JLabel.RIGHT));
		jtJogin = new JTextField();
		jpCenter.add( jtJogin );
		
		jpCenter.add( new JLabel("senha : ", JLabel.RIGHT));
		jpassword = new JPasswordField();
		jpCenter.add( jpassword );
		
		this.setLayout( new BorderLayout() );
		this.add( jlMessage , BorderLayout.NORTH );
		this.add( jpCenter , BorderLayout.CENTER );
		this.add( jpButtons , BorderLayout.SOUTH );
		
		this.setVisible( true );
		
		this.setLocationRelativeTo( null );
		this.pack();
		Dimension d = this.getPreferredSize();
		d.setSize( ( d.getWidth() + 80 ), d.getHeight() );
		this.setMinimumSize( d );
		this.setResizable( false );
		
		ref = this;
		
//		jtJogin.setText("emilio.vieira@cni.org.br");
//		jpassword.setText("Em231079");
		
		jbLogar.addActionListener( new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = jtJogin.getText();
				String password = new String( jpassword.getPassword() );
				
				AutenticadorSGT.rmfCookie();
				
				AutenticadorSGT autenticar = AutenticadorSGT.getInstance( user , password );
				
				if ( autenticar.isLogado() ) {
					report = new ReportFrame( autenticar.isLogado(), user, password );
					ref.setVisible( false );
					jlMessage.setText("");
				} else {
					ref.setVisible( true );
					report = new ReportFrame( autenticar.isLogado(), user, password );
					jlMessage.setText("Login o senha não conferem");
					jlMessage.setForeground(Color.RED);
				}
			}
		});
		
		jbLimpar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtJogin.setText("");
				jtJogin.requestFocus();
				jpassword.setText("");
				jlMessage.setText("");
				AutenticadorSGT.rmfCookie();
			}
		});
		
		jtJogin.requestFocus();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                /*Login login = */
            	new Login();
            }
        });
	}
}
