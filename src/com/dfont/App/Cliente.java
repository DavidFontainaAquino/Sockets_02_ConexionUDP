package com.dfont.App;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	public static void main(String[] args) {
		
		
		final int PUERTO_SERVIDOR = 5000;
		byte[] buffer = new byte[1024];
		Logger logger = Logger.getLogger(Cliente.class.getName());
		String mensaje = new String("Hola servidor, soy el cliente");
		buffer = mensaje.getBytes(StandardCharsets.UTF_8);
		
		try {
			InetAddress direccionServidor = InetAddress.getByName("localhost");
			
			// Se crea el socket para la conexion UDP
			DatagramSocket socket = new DatagramSocket();
			logger.log(Level.INFO, "Socket creado" );
			
			// Se crea el DatagramPacket que tiene la dirección y puerto del servidor
			DatagramPacket solicitud = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
			
			// Se envia el datagrama a travez del socket
			socket.send(solicitud);
			logger.log(Level.INFO, "Solicitud enviada");
			
			// Se crea un nuevo DatagramPacket para recibir la respuesta del servidor
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
			
			// Se pone el socket a la espera de una respuesta que sera almacenado en "respuesta"
			// En este lugar el aplicativo quedara a la espera de una comunicacion
			socket.receive(respuesta);
			logger.log(Level.INFO, "Respuesta recibida");
			
			// Luego de recibir el mensaje se convierte el contenido del buffer en un String
			mensaje= new String(buffer, StandardCharsets.UTF_8);
			logger.log(Level.INFO, "Recibido: " + mensaje);
			
			// Se cierra el socket
			socket.close();

		} catch (SocketException e) {
			logger.log(Level.SEVERE,"Ocurrio una excepcion en la creacion del socket", e);
		} catch (UnknownHostException e) {
			logger.log(Level.SEVERE, "Ocurrio una excepcion en la resolucion del host", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Ocurrio una excepcion en el envio o recepcion del datagramas", e);
		}
	}
}
