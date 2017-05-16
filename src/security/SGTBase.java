package security;

public abstract class SGTBase {
	
	public final static String D = "d";
	public final static String H = "h";
	public final static String P = "p";
	public final static String T = "t";
	
	public final static String HTTP = "http";
	public final static String HTTPS = "https";
	public final static String PROTO = HTTP;
	
	private final static String PORT_FINAL_3 = "9763";
	private final static String PORT_FINAL_4 = "9764";
	
	public final static String PORT_P = PORT_FINAL_3;
	public final static String PORT_H = PORT_FINAL_4;
	public final static String PORT_D = PORT_FINAL_3;
	public final static String PORT_T = PORT_FINAL_3;
	
	public final static String SERVER_P = "sgt.cni.org.br";
	public final static String SERVER_H = "sgth.sc.senai.br";// OLD FIESC
//	public final static String SERVER_D = "sgtd.sc.senai.br"; OLD FIESC
	public final static String SERVER_D = "sgtd.exablack.com";
	public final static String SERVER_T = "sgtt.exablack.com";
	
	public static String getBase(String b) {
		String base = "";
		if ( "h".equalsIgnoreCase( b ) ) {
			///base = PROTO + "://" + SERVER_H + ":" + PORT_H + "/SGTWebApp/rest"; //BASE NAO EXISTE MAIS
			base = getBase( SGTBase.T );
		} else if ( "p".equalsIgnoreCase( b )) {
			base = PROTO + "://" + SERVER_P + ":" + PORT_P + "/SGTWebApp/rest";
		} else if ( "d".equalsIgnoreCase( b ) ) {
			base = PROTO + "://" + SERVER_D + ":" + PORT_D + "/SGTWebApp/rest";
		} else if ( "t".equalsIgnoreCase( b ) ) {
			base = PROTO + "://" + SERVER_T + ":" + PORT_T + "/SGTWebApp/rest";
			
		} else {
			throw new RuntimeException("ERRO AO SELECIONAR BASE NO SGT");
		}
		return base;
	}
	
}
