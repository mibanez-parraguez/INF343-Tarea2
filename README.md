# INF343-Tarea2
Tarea 2 Sistemas Distribuidos

__Autores__
* Felipe Santander (201104528-9)
* Miguel Ibáñez (2990010-8)

---

__Torre__
* Para crear las dependencias (desde carpeta torre/):

`$ protoc -I ../protos --grpc_out=. --plugin=protoc-gen-grpc='which grpc_cpp_plugin' ../protos/planecontrol.proto`
 
 Si el comando anterior produce el error "--grpc_out: protoc-gen-grpc: Plugin failed with status code 1." entonces utilizar:

`$ protoc -I../protos --cpp_out=. --grpc_out=. --plugin=protoc-gen-grpc=/usr/local/bin/grpc_cpp_plugin ../protos/planecontrol.proto`

`$ protoc -I ../protos --cpp_out=. ../protos/planecontrol.proto`


* Luego, para compilar se debe ejecutar (desde carpeta torre/):
`$ make`

* Para ejecutar torre servidor (desde carpeta torre/): 
`$ ./control_server`

__Avion__

* Para compilar (desde raíz): 
`$ ./gradlew installDist`

* Para ejecutar avion cliente (raíz): 
`$ ./build/install/Tarea2/bin/Avion`

__Pantalla__
* Se debe tener pip version 9.0.1 o mayor:
`$ python -m pip install --upgrade pip`
* Si lo anterior tiene error, utilizar:

`$ $ python -m pip install virtualenv`

`$ virtualenv venv`

`$ source venv/bin/activate`

`$ python -m pip install --upgrade pip`
* Instalar grpc:
`$ python -m pip install grpcio`
* Finalmente:

`$ python -m pip install grpcio-tools googleapis-common-protos`

* Para crear las dependencias (desde carpeta info/):
`$ python -m grpc_tools.protoc -I../protos --python_out=. --grpc_python_out=. ../protos/planecontrol.proto`
* Para ejecutar pantalla cliente (desde carpeta info/):
`$ python info.py`

__Adicional__

La torre de control (servidor) necesita una dirección ip y 2 puertos, el primero para el servidor principal de control del aeropuerto y el segundo para un servidor en un thread para la pantalla de información. Ej. 'localhost:50051:50052'.

Luego todas las direcciones pedidas necesitan de una dirección ip y el puerto. Por ej. un avión para entrar en la torre de control inicial del ejemplo anterior debiera ingresar 'localhost:50051' (única vez que se pide la dirección en la instancia del avión). Mientras que una pantalla de información debera ingresar 'localhost:50052', donde el puerto corresponde al segundo ingresado en la torre de control.

