package AppliChat;


/**
* AppliChat/ChatHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Talk.idl
* Wednesday, October 23, 2019 3:09:02 PM CEST
*/

abstract public class ChatHelper
{
  private static String  _id = "IDL:AppliChat/Chat:1.0";

  public static void insert (org.omg.CORBA.Any a, AppliChat.Chat that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AppliChat.Chat extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (AppliChat.ChatHelper.id (), "Chat");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AppliChat.Chat read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ChatStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AppliChat.Chat value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static AppliChat.Chat narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof AppliChat.Chat)
      return (AppliChat.Chat)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      AppliChat._ChatStub stub = new AppliChat._ChatStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static AppliChat.Chat unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof AppliChat.Chat)
      return (AppliChat.Chat)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      AppliChat._ChatStub stub = new AppliChat._ChatStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
