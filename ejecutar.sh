#!/bin/bash

if ! command -v java --version 2>&1 > /dev/null; 
	then
		echo -e "\033[31mNo se encuentra java en su PC"
	else 
		clear
		dir=$(pwd)/src/com/dfont/App

		# Se lanza el servidor
		java "$dir"/Servidor.java &

		# Se lanza el cliente
		java "$dir"/Cliente.java &
fi
