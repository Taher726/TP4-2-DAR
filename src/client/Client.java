package client;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
	private static final int PORT = 1234;
	private static byte[] buffer = new byte[1024];
	public static void main(String[] args) throws Exception {
		// Crée un objet Scanner pour lire l'entrée utilisateur
		Scanner scanner = new Scanner(System.in);
		System.out.println("Donnez votre nom et prénom : ");

		// Lit la saisie de l'utilisateur et la stocke dans la variable "message"
		String message = scanner.nextLine();

		// Convertit le message en tableau de bytes
		byte[] data = message.getBytes();

		// Récupère l'adresse IP du serveur (localhost) à laquelle envoyer le message
		InetAddress adresseDest = InetAddress.getByName("localhost");

		// Crée un DatagramPacket avec les données, l'adresse de destination et le port
		DatagramPacket paquet = new DatagramPacket(data, data.length, adresseDest, PORT);

	// Crée un socket pour envoyer le paquet
	DatagramSocket socket = new DatagramSocket();

	// Envoie le paquet au serveur
	socket.send(paquet);

	// Prépare un DatagramPacket pour recevoir la réponse du serveur
	DatagramPacket recu = new DatagramPacket(buffer, buffer.length);

	// Attend la réception d'un paquet du serveur
	socket.receive(recu);

	// Affiche la réponse reçue du serveur
	System.out.println(new String(recu.getData(), 0, recu.getLength()));

	// Affiche l'adresse IP de l'expéditeur (le serveur) et le port
	System.out.println("Adresse : " + recu.getAddress());
	System.out.println("Port : " + recu.getPort());
	}
}