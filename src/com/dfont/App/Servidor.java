package com.dfont.App;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

	public static void main(String[] args) {
		final int PUERTO = 5000;
		byte[] buffer = new byte[1024];
		Logger logger = Logger.getLogger(Servidor.class.getName()); 
		DatagramSocket socket;
		try {
			// Se solicita al SO la apertura de un socket en el puerto especificado.
			socket = new DatagramSocket(PUERTO);
			logger.log(Level.INFO, "Servidor Iniciado");
			
			// Se crea un DatagramPacket donde se almacenara la peticion del cliente.
			DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
			
			// El aplicativo queda a la espera de una peticion de algun cliente
			socket.receive(peticion);
			logger.log(Level.INFO, "Esperando peticion");
			
			// Luego de recibir el mensaje se convierte el contenido del buffer en un String
			String mensaje = new String(buffer, StandardCharsets.UTF_8);
			logger.log(Level.INFO, "Recibido: " + mensaje);
			
			// Se obtiene la direccion IP y puerto del cliente
			int puertoCliente = peticion.getPort();
			InetAddress direccionCliente = peticion.getAddress();
			
			// Se crea el mensaje de respuesta y se almacena en el buffer
			mensaje = "Hola Cliente, soy el servidor";
			buffer = mensaje.getBytes();
			
			// Se crea el DatagramPacket que se enviara al cliente
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionCliente, puertoCliente);
			
			// Se envia la respuesta al cliente a travez de el socket creado
			socket.send(respuesta);
			logger.log(Level.INFO, "Respuesta enviada.");
			
			// Se cierra el socket
			socket.close();
			
		} catch (SocketException e) {
			logger.log(Level.SEVERE,"Ocurrio una excepcion en la creacion del socket", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Ocurrio una excepcion en el envio o recepcion del datagramas", e);
		}
	}
}
