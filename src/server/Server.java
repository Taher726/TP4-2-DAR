package server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	private static final int PORT = 1234;
	private static byte[] data = new byte[1024];
	public static void main(String[] args) throws Exception {
		// Crée un socket pour écouter sur le port 1234
		DatagramSocket socket = new DatagramSocket(PORT);
		System.out.println("Lancement du serveur");

		while (true) {
			// Prépare un DatagramPacket pour recevoir des données
			DatagramPacket paquet = new DatagramPacket(data, data.length);
			socket.receive(paquet);

			// Convertit les données du paquet en une chaîne de caractères
			String msg = new String(paquet.getData(), 0, paquet.getLength());

			// Affiche l'adresse IP de l'expéditeur et le message reçu
			System.out.println(paquet.getAddress() + " : " + msg);

			// Crée une réponse en ajoutant le message reçu et l'heure actuelle
			String reponse = msg + " est connecté à " + getCurrentTime();

			// Crée un DatagramPacket pour envoyer la réponse à l'expéditeur
			DatagramPacket envoi = new DatagramPacket(reponse.getBytes(), reponse.length(), paquet.getAddress(), paquet.getPort());

			// Envoie la réponse
			socket.send(envoi);
		}
	}
	
	// Méthode pour obtenir l'heure actuelle au format "yyyy MM dd HH:mm:ss"
	private static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
