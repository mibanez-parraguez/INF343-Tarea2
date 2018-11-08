# -*- coding: utf-8 -*-
from __future__ import print_function

import random

import grpc

import planecontrol_pb2
import planecontrol_pb2_grpc

def planeList(stub):
    Request = planecontrol_pb2.InfoRequest(control_tower="something")

    print(Request)
    print("Searching planes...")
    Response = stub.Info(Request)
    # print(Response.arrivalPlane[0].planeNumber())
    if Response:
        print("RESPONDIDOU")
    # for departurePlane, arrivalPlane, control_tower in Response:
    #     print("Plane called - ")
def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    address = raw_input("Ingrese dirección IP de la torre de control:")
    #PUERTO 50052 = Thread del la torre de control encargada de enviar informacion
    with grpc.insecure_channel('0.0.0.0:50051') as channel:
        stub = planecontrol_pb2_grpc.PlaneControlServiceStub(channel)
        planeList(stub)
        print("Finish")
    i = 0
    while True:
        i+=1

if __name__ == '__main__':
    run()
