package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_act3 {

    private static int result(int x, char op) {
        if (op == '+') {
            return x + 5;
        }
        if (op == '-') {
            return x - 5;
        }
        if (op == '*') {
            return x * 5;
        }
        return x / 5;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente de la connexion d'un client");

        Socket socket = serverSocket.accept();
        System.out.println("Le client est connecté");

        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        char op = dis.readChar();


        int nb = dis.readInt();
        int x = result(nb, op);
        System.out.println("Calcul du serveur : " + nb + " " + op + " 5 = " + x);
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        dos.writeInt(x);
        dos.flush();

        dos.close();
        os.close();
        dis.close();
        is.close();
        socket.close();
        serverSocket.close();

        System.out.println("Serveur arrêté.");
    }
}