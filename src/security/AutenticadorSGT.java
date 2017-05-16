package security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class AutenticadorSGT {
	
	private static AutenticadorSGT oAuth;
	private List<String> cookies;
	private static boolean logado;

	private AutenticadorSGT(String login, String password) throws IOException {
		
		StringBuilder urlSGT = new StringBuilder()
		.append( SGTBase.getBase( SGTBase.P ) )
		.append("/security/login");

		URL securitySGT = new URL(  urlSGT.toString() );
		HttpURLConnection oAuthSGT = (HttpURLConnection) securitySGT.openConnection();
		
		oAuthSGT.setDoOutput(true);
		oAuthSGT.setDoInput(true);
		oAuthSGT.setRequestMethod("POST");
		oAuthSGT.setRequestProperty("Content-Type", "application/json");
		oAuthSGT.setRequestProperty("Accept", "application/json");

		JSONWriter payload = new JSONStringer();
		
		payload.object()
			.key("login")
			.value( login )
			.key("senha")
			.value( password )
		 	.endObject();
		
		OutputStreamWriter osw = new OutputStreamWriter( oAuthSGT.getOutputStream() );
		osw.write( payload.toString() );
		osw.flush();

		cookies = oAuthSGT.getHeaderFields().get("Set-Cookie");
		
		BufferedReader bufferResponseSGT = null;
		
		String sgtResponse = "";
		String context;
		bufferResponseSGT = new BufferedReader( new InputStreamReader( oAuthSGT.getInputStream(), "UTF-8" ));
		
		while ( (context = bufferResponseSGT.readLine() ) != null) {
			sgtResponse = context;
		}
		
		
		logado = new JSONObject( sgtResponse).getBoolean("login");
	}

	public static synchronized AutenticadorSGT getInstance(String login, String password) {
		if (oAuth == null) {
			try {
				oAuth = new AutenticadorSGT( login, password );
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage() );
			}
		} else {
			for (String c : oAuth.cookies) {
				if (c.contains("false")) {
					try {
						oAuth = new AutenticadorSGT(login, password );
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if ( isLogado() ) {
			try {
				oAuth = new AutenticadorSGT( login, password );
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage() );
			}
		} else {
			for (String c : oAuth.cookies) {
				if (c.contains("false")) {
					try {
						oAuth = new AutenticadorSGT(login, password );
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return oAuth;
	}
	
	public static boolean isLogado() {
		return logado;
	}

	public List<String> getCookie() {
		return cookies;
	}
	
	public static synchronized void rmfCookie() {
		oAuth = null;
	}
	
	public String bCookie() {
		StringBuilder sb = new StringBuilder();
		for (String c : cookies) {                    
			sb.append( c.split(";", 2)[0] );
		}
		return sb.toString();
	}
	

}
