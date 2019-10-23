import AppliChat.TalkPOA;


public class TalkImpl extends TalkPOA{

	private String _msg;
	private String _name;
	
	public TalkImpl(){
		_name = "Lili";
	}
	
	public void sendMsg(String msg) {
		System.out.println(_name + " : " + _msg);	
	}
	
	public void msg(String newMsg) {
		_msg = newMsg;
	}
	public String msg() {
		return _msg;
	}

	public void name(String newName) {
		_name = newName;
	}
	
	public String name() {
		return _name;
	}

}
