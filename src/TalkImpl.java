import AppliChat.TalkPOA;


public class TalkImpl extends TalkPOA{

	private String _name;
	
	public TalkImpl(){
		_name = "Lili";
	}
	
	public void sendMsg(String username, String msg) {
		System.out.println(username + " : " + msg);	
	}

	public void name(String newName) {
		_name = newName;
	}
	
	public String name() {
		return _name;
	}

}
