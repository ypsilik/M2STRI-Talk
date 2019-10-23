
import java.util.ArrayList;

import AppliChat.ChatPOA;
import AppliChat.Talk;
import AppliChat.TalkHelper;


public class ChatImpl extends ChatPOA {

	private ArrayList<Talk> users = new ArrayList<Talk>();
	public void inscription(Talk ior) {
		users.add(ior);
	}

	public void diffusionMsg(String name, String msg) {
		for (Talk user : users) {
			user.sendMsg(name, msg);
		}
	}
	
	public void disconnect(Talk user){
		users.remove(user);
	}

}
