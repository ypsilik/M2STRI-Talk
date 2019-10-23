import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import AppliChat.Talk;
import AppliChat.TalkHelper;


public class ClientTalk {
	public static AppliChat.Chat chatSrv;
	
	public static void main(String args[]) {
		
		try {
/********************* SERVEUR ****************************/
	        // Intialisation de l'ORB
	        //************************
	        final org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

	        // Gestion du POA
	        //****************
	        // Recuperation du POA
	        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

	        // Creation du servant
	        //*********************
	        TalkImpl myTalkImpl = new TalkImpl();

	        // Activer le servant au sein du POA et recuperer son ID
	        byte[] monTalkId = rootPOA.activate_object(myTalkImpl);

	        // Activer le POA manager
	        rootPOA.the_POAManager().activate();

	        // Enregistrement dans le service de nommage
	        //*******************************************
	        // Recuperation du naming service
	        NamingContext nameRoot=org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

	        // Construction du nom a enregistrer
/*	        org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
	        System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String nomObj = in.readLine();
	        nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");

	        // Enregistrement de l'objet CORBA dans le service de noms
	        nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(myTalkImpl));
	        System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");
*/
	        String IORServant = orb.object_to_string(rootPOA.servant_to_reference(myTalkImpl));
	        System.out.println("L'objet possede la reference suivante :");
	        System.out.println(IORServant);

	        // Lancement de l'ORB et mise en attente de requete
	       Thread t = new Thread(){
	    	   public void main(){
	    		   orb.run();
	    	   }
	       }; 
	       t.start();
/************************* Client *********************************/
		// Intialisation de l'orb
		org.omg.CORBA.ORB orb1 = org.omg.CORBA.ORB.init(args,null);

        // Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("Quel objet Corba voulez-vous contacter ?");
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        String idObj = in1.readLine();

        // Recuperation du naming service
        org.omg.CosNaming.NamingContext nameRoot1 =
        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

        // Construction du nom a rechercher
        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");

        // Recherche aupres du naming service
        org.omg.CORBA.Object serverChat = nameRoot1.resolve(nameToFind);
        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
        System.out.println(orb.object_to_string(serverChat));

        // Casting de l'objet CORBA au type convertisseur euro
        chatSrv = AppliChat.ChatHelper.narrow(serverChat);
        Talk myTalk = TalkHelper.narrow(rootPOA.servant_to_reference(myTalkImpl));
        myTalk.name("tdo");
        chatSrv.inscription(myTalk);
     // chatSrv.diffusionMsg("BONJOUR");
        try {
//			System.out.println("Entrez votre nom :");
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			String name = in.readLine();
			while(true) {
				System.out.println("> ");
				String message = in.readLine();
				if (message.equalsIgnoreCase("exit")){
					chatSrv.disconnect(myTalk);
					break;
				}
				ClientTalk.chatSrv.diffusionMsg(myTalk.name(), message);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	catch (Exception e) {
		e.printStackTrace();
	}

} // main
}
