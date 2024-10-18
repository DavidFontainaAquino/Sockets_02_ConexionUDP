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
			socket = new DatagramSocket(PUERTO);
			logger.log(Level.INFO, "Servidor Iniciado");

			DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
			socket.receive(peticion);
			logger.log(Level.INFO, "Esperando peticion");

			String mensaje = new String(buffer, StandardCharsets.UTF_8);
			logger.log(Level.INFO, "Recibido: " + mensaje);
			
			int puertoCliente = peticion.getPort();
			InetAddress direccionCliente = peticion.getAddress();
			mensaje = "Hola Cliente, soy el servidor";
			buffer = mensaje.getBytes();
			DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionCliente, puertoCliente);
			socket.send(respuesta);
			logger.log(Level.INFO, "Respuesta enviada.");
			socket.close();
			
		} catch (SocketException e) {
			logger.log(Level.SEVERE,"Ocurrio una excepcion en la creacion del socket", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Ocurrio una excepcion en el envio o recepcion del datagramas", e);
		}
	}

}
