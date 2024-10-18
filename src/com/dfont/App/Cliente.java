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
		System.out.println(buffer);
		
		try {
			InetAddress direccionServidor = InetAddress.getByName("localhost");
			DatagramSocket socket = new DatagramSocket();
			logger.log(Level.INFO, "Socket creado" );
			DatagramPacket solicitud = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
			socket.send(solicitud);
			logger.log(Level.INFO, "Solicitud enviada");
			
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
			socket.receive(respuesta);
			logger.log(Level.INFO, "Respuesta recibida");
			mensaje= new String(buffer, StandardCharsets.UTF_8);
			logger.log(Level.INFO, "Recibido: " + mensaje);
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
