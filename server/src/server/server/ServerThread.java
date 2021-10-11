package server;
import java.net.*;

import java.io.*;


public class ServerThread extends Thread
{
    ServerSocket server=null;
    Socket client =null;
    String stringaRicevuta=null;
    String stringaModificata=null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket)
    {
        this.client=socket;
    }
    public void run()
    {
        try
        {
            comunica();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
    public void comunica() throws Exception{
        inDalClient= new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient= new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            stringaRicevuta=inDalClient.readLine();
           if(stringaRicevuta==null || stringaRicevuta.equals("FINE"))
           {
               outVersoClient.writeBytes(stringaRicevuta+"(-->socket in chiusura)"+'\n');
               System.out.println("Echo sul server in chiusura   :"+ stringaRicevuta);
               outVersoClient.close();
               inDalClient.close();
               System.out.println("9 chiusura socket"+client);
               client.close();
               break;
           }
           if(stringaRicevuta.equals("STOP"))
           {
               outVersoClient.writeBytes(stringaRicevuta+"(-->server in chiusura)"+'\n');
               System.out.println("Echo sul server in chiusura   :"+ stringaRicevuta);
               outVersoClient.close();
               inDalClient.close();
               System.out.println("9 chiusura socket"+client);
               System.out.println("10 chiusura server"+client);
               client.close();
               server.close();
               break;
           }
           else
           {
               outVersoClient.writeBytes(stringaRicevuta+"ricevuta e ritrasmessa"+ '\n');
               System.out.println("6 Echo sul server :"+client);
            }         
        }
    }
}
