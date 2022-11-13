package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        try{
            // establish conn using hostname+portnumber
            Socket me = new Socket(hostName, portNumber);

            // write to server
            PrintWriter out = new PrintWriter(me.getOutputStream(),true);

            // read from server
            BufferedReader in = new BufferedReader(new InputStreamReader(me.getInputStream()));

            // using scanner here
            Scanner sc = new Scanner(System.in);
            String fromUser = null;
            String fromServer = null;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                //reading from user
                fromUser = sc.nextLine();
                if (fromUser != null) {
                    out.println(fromUser);

                    if (fromUser.equals("exit"))
                        break;
                }
            }
            // am transmis mesaj de exit, deci serverul trebuie sa mi trimita lista sortata
            while ((fromServer = in.readLine()) != null) {
                // afisam ce ne trimite serverul
                System.out.println(fromServer);
                // cand primim semnal de exit, a terminat de trimis, putem inchide clientul
                if (fromServer.equals("exit"))
                    break;
            }
            sc.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
