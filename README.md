# SOCKETS UDP
## Cliente y servidor
El presente proyecto contiene solamente 2 clases java cuya finalidad es lograr
una conexión exitosa mediante el protocolo UDP entre 2 procesos distintos dentro de un PC.

Una de las clases será un *servidor UDP* que se encuentra a la espera de la conexión de un cliente
y la otra será un *cliente UDP* que se conectará al servidor, recibirá un stream de texto y se desconectará.

### Ejecución en **LINUX**
Este proyecto contiene un script para linux que al ejecutarse desde la terminal
lanzará las 2 clases por separado en procesos en segundo plano y se
muestran los Logs de cada uno(identificando su procedencia).

*Para ejecutar el script escriba en la terminal*:
```
./ejecutar.sh
``` 
