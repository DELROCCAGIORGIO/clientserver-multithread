package it.delrocca;

import java.io.*;
import java.net.*;

public class Clientstr {
    String NomeServer="localhost";      //indirizzo server locale
    int SocketServer =6789;             //porta per servizio
    Socket miosocket;        
    BufferedReader tastiera;            //buffer per l'input da tastiera
    String StringaUtente;               //stringa inserita da utente
    String StringaRicevutaDalServer;    //stringa inserita dal server 
    DataOutputStream outVersoServer;    //stream di output
    BufferedReader inDalServer;         //stream di input

    public Socket connetti (){
     System.out.println("2 CLIENT partito in esecuzione . . .");
     try{
         tastiera= new BufferedReader(new InputStreamReader(System.in));

         miosocket= new Socket(NomeServer,SocketServer);

        outVersoServer= new DataOutputStream(miosocket.getOutputStream());

        inDalServer= new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
     }
       catch (UnknownHostException e){
         System.err.println("Host sconosciuto");
       }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
           System.out.println("Errore durante la connessione");
           System.exit(1);
       }
       return miosocket;
    }

    public void comunica() 
    {
        for(;;){
        try
        {
            System.out.println("4 benvenuto client,inserisci la stringa da trasmettere al server ,Attendo");
            stringaUtente=tastiera.readLine();
            System.out.println("5... invio la stringa al server e attendo");
            outVersoServer.writeBytes(stringaUtente+'\n');
            StringaRicevutaDalServer=inDalServer.readLine();
            System.out.println("7...risposta dal server"+'\n'+StringaRicevutaDalServer);
            if(stringaUtente.equals("FINE")){
                System.out.println("8 CLIENT:termina elaborazione e chiude connessione");
                miosocket.close();
                break;
            }
        }
       catch (Exception e)
           {
               System.out.println(e.getMessage());
               System.out.println("Errore durante la comunicazione col server");
               System.exit(1);
           }
       }
   
    }
}
