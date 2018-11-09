# -*- coding: utf-8 -*-
from __future__ import print_function

import random
import time
import textwrap

import grpc

import planecontrol_pb2
import planecontrol_pb2_grpc

TCname = "ERROR"
finish = False
maxWidth = 10

def fill(word):
    if isinstance(word, (int, long)):
        word = str(word)
    word = "{}                         ".format(word)
    word = "%.10s " % word
    return word
def wrap(line):
    wrapped = textwrap.fill(line, 1000)
    return wrapped

class requestInfo(object):

    def __init__(self, address, port):
        try:
            # self.channel = grpc.insecure_channel("{}:{}".format(address, port))
            self.channel = grpc.insecure_channel("localhost:50052")
            self.stub = planecontrol_pb2_grpc.InfoServiceStub(self.channel)
        except grpc.RpcError as e:
            global finish
            self.channel.close()
            print("Error: " + e.details())
            status_code = e.code()
            print("Status: " + status_code.name)
            print("Value: {}".format(status_code.value))
            print("Details: {}".format(e.debug_error_string))
            finish = True

    def planeList(self):
        global finish
        try:
            Request = planecontrol_pb2.InfoRequest(control_tower="something")
            Response = self.stub.Info(Request)
            for res in Response:
                global TCname
                TCname = res.control_tower
                runway = ""
                print("[Pantalla de información - {} ]".format(TCname))
                # if res.departurePlane and res.arrivalPlane:
                #     print(wrap("%.40s"%"Departures\t\t\t\t    "+" | "+"%.40s"%"Arrivals"))
                #     print(wrap(fill("Avion")+fill("Destino")+fill("Pista")+fill("Hr")+" | "+fill("Avion")+fill("Destino")+fill("Pista")+fill("Hr")))
                #     for dp, ap in res.departurePlane, res.arrivalPlane:
                #         print(wrap(fill(dp.planeNumber)+fill(dp.destName)+fill(dp.runway)+fill(dp.time)+" | " +fill(ap.planeNumber)+fill(ap.destName)+fill(ap.runway)+fill(ap.time)))
                if res.departurePlane:
                    print("*"*maxWidth*5)
                    print(wrap(fill("Departures")+" "*maxWidth*3))
                    print("-"*maxWidth*5)
                    print(wrap(fill("Avion")+fill("Destino")+fill("Pista")+fill("Hr")))
                    for dp in res.departurePlane:
                        if (dp.runway == -1):
                            runway = "En espera"
                        else:
                            runway = dp.runway
                        print(wrap(fill(dp.planeNumber)+fill(dp.destName)+fill(runway)+fill(dp.time)))
                    print("*"*maxWidth*5)
                if res.arrivalPlane:
                    print(wrap(fill("Arrivals")+" "*maxWidth*3))
                    print("-"*maxWidth*5)
                    print(wrap(fill("Avion")+fill("Destino")+fill("Pista")+fill("Hr")))
                    for ap in res.arrivalPlane:
                        if (ap.runway == -1):
                            runway = "En espera"
                        else:
                            runway = ap.runway
                        print(wrap(fill(ap.planeNumber)+fill(ap.destName)+fill(runway)+fill(ap.time)))
                    print("*"*maxWidth*5)
                finish = True
        except grpc.RpcError as e:
            self.channel.close()
            print("Error: " + e.details())
            status_code = e.code()
            print("Status: " + status_code.name)
            print("Value: {}".format(status_code.value))
            print("Details: {}".format(e.debug_error_string))
            finish = True

def closeMsg():
    print("[Pantalla de información - {} ] Cerrando...".format(TCname))


def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    formatOK = False
    while not formatOK:
        try:
            addressPort = raw_input("Ingrese dirección de la torre de control (ip:puerto):")
            address, port = addressPort.split(":")
            formatOK = True
        except ValueError:
            print("Formato incorrecto, debe ser 'ip:puerto', intentelo nuevamente.")

    requestInfo(address, port).planeList()
    # Loop para envitar errores de canal cuando se termina el programa antes de
    # terminar de recibir la respuesta del servidor
    # Para terminar el programa escribir "exit" o ctrl+c (KeyboardInterrupt)
    try:
        while not finish:
            close = raw_input()
            if (close == "exit"):
                break
        closeMsg()
    except KeyboardInterrupt:
        closeMsg()

if __name__ == '__main__':
    run()
