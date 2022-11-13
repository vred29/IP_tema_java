package server;


import human.Human;
import human.profesor.Profesor;
import human.student.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyThread extends Thread{

    private ArrayList<Human> lista_oameni;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    MyThread(Socket socket){
        this.clientSocket=socket;
        lista_oameni = new ArrayList<>();
    }

    private void addHuman(Human h) {
        if(!this.lista_oameni.contains(h)){
            this.lista_oameni.add(h);
            System.out.println("Human added to list.");
            out.println("Added to list.");
        }else {
            System.out.println("Human already exists.");
            out.println("Already exists.");
        }
    }
    private void evaluateMessage(String linie){

        String s[] = linie.split(" ");
        Human h;
        if(s[0].equals("Student")){
            System.out.println("Human - type student received.");

            // members required
            String nume;
            String prenume;
            String acronim_fac;
            Integer varsta;
            Integer an_studiu;
            if(s.length == 6){
                nume = s[1];
                prenume = s[2];
                acronim_fac = s[3];
                varsta = Integer.parseInt(s[4]);
                an_studiu = Integer.parseInt(s[5]);

                h = new Student(nume,prenume,acronim_fac,varsta,an_studiu);
                if(h!=null){
                    addHuman(h);
                }
            }else {
                System.out.println("Failed to add Human - type student");
                out.println("Failed to add. Usage: [Student <Nume> <Prenume> <Acronim facultate> <Varsta> <An de studiu>]");
            }
        } else if (s[0].equals("Profesor")) {
            System.out.println("Human - type profesor received.");

            // members required
            String nume;
            String prenume;
            String acronim_fac;
            Integer varsta;
            String materie;
            if(s.length == 6) {
                nume = s[1];
                prenume = s[2];
                acronim_fac = s[3];
                varsta = Integer.parseInt(s[4]);
                materie = s[5];

                h = new Profesor(nume, prenume, acronim_fac, varsta, materie);
                if(h!=null){
                    addHuman(h);
                }
            }else {
                System.out.println("Failed to add Human - type profesor");
                out.println("Failed to add. Usage: [Profesor <Nume> <Prenume> <Acronim facultate> <Varsta> <Materie predata>]");
            }
        } else if (s[0].equals("exit")) {
            System.out.println("Connection closing.");
            System.out.println("Sending list.");

            Collections.sort(lista_oameni);

            out.println("SORTED LIST");
            for(Human i:lista_oameni){
                out.println(i.toString());
            }
        } else {
            System.out.println("Cannot evaluate message.");
            out.println("Cannot evaluate message. Usage: [Profesor <Nume> <Prenume> <Acronim facultate> <Varsta> <Materie predata>]/[Student <Nume> <Prenume> <Acronim facultate> <Varsta> <An de studiu>]");
        }
    }

    @Override
    public void run() {

        try{
            // send to client
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            // read from client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String fromClient = null;

            out.println("Connected successfully.");
            while ((fromClient = in.readLine()) != null){
                System.out.println("Received from client: "+ fromClient);
                evaluateMessage(fromClient);
                if (fromClient.equals("exit")){
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                    clientSocket.close();
                    System.out.println("Connection closed.");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
