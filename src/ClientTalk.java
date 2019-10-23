import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;


public class ClientTalk {
	public static AppliChat.Talk myTalk;

	public static void chatUser() {
        try {
			System.out.println("Entrez votre nom :");
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String name = in.readLine();
			ClientTalk.myTalk.name(name);
			while(true) {
				System.out.println("> ");
				String message = in.readLine();
				if (message.equalsIgnoreCase("exit")){
					break;
				}
				ClientTalk.myTalk.msg(message);
				ClientTalk.myTalk.sendMsg(message);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {

		try {

		// Intialisation de l'orb
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

        // Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("Quel objet Corba voulez-vous contacter ?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String idObj = in.readLine();

        // Recuperation du naming service
        org.omg.CosNaming.NamingContext nameRoot =
        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

        // Recherche aupres du naming service
        org.omg.CORBA.Object userTalk = nameRoot.resolve(nameToFind);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(userTalk));

        // Casting de l'objet CORBA au type convertisseur euro
        myTalk = AppliChat.TalkHelper.narrow(userTalk);
        chatUser();
        
	}
	catch (Exception e) {
		e.printStackTrace();
	}

} // main
}
