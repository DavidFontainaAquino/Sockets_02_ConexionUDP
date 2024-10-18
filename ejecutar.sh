#!/bin/bash

clear
dir=$(pwd)/src/com/dfont/App

# Se lanza el servidor
java "$dir"/Servidor.java &

# Se lanza el cliente
java "$dir"/Cliente.java &
