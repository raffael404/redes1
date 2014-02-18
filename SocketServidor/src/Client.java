import java.io.DataOutputStream;


public class Client {
	private String name;
	DataOutputStream toClient;

	public Client (String name, DataOutputStream toClient){
		this.name =name;
		this.toClient = toClient;
	}
	public String getName() {
		return name;
	}
	public DataOutputStream getToClient() {
		return toClient;
	}
	
}
