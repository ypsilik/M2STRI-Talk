package AppliChat;

/**
* AppliChat/ChatHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Talk.idl
* Wednesday, October 23, 2019 3:09:02 PM CEST
*/

public final class ChatHolder implements org.omg.CORBA.portable.Streamable
{
  public AppliChat.Chat value = null;

  public ChatHolder ()
  {
  }

  public ChatHolder (AppliChat.Chat initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AppliChat.ChatHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AppliChat.ChatHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AppliChat.ChatHelper.type ();
  }

}
