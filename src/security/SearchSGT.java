package security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

public class SearchSGT {
	
	public JSONArray callSGT(String url, String user, String password) throws IOException {
		URL urlSGT = new URL( url );
		
		HttpURLConnection connectionSGT = (HttpURLConnection) urlSGT.openConnection();
		connectionSGT.setRequestMethod("GET");
		connectionSGT.setRequestProperty("Accept", "application/json");
		connectionSGT.addRequestProperty("Cookie", AutenticadorSGT.getInstance( user, password ).bCookie() );
		
		BufferedReader bufferResponseSGT = null;
		
		String sgtResponse = "";
		String context;
		bufferResponseSGT = new BufferedReader( new InputStreamReader( connectionSGT.getInputStream(), "UTF-8" ));
		
		while ( (context = bufferResponseSGT.readLine() ) != null) {
			sgtResponse = context;
		}
		
		JSONArray array = new JSONArray(sgtResponse);
		
		return array;
	}

}
