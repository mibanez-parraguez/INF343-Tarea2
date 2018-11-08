from __future__ import print_function

import random

import grpc

import infocontrol_pb2
import infocontrol_pb2_grpc

def guide_list_features(stub):
    rectangle = route_guide_pb2.Rectangle(
        lo=route_guide_pb2.Point(latitude=400000000, longitude=-750000000),
        hi=route_guide_pb2.Point(latitude=420000000, longitude=-730000000))
    print("Looking for features between 40, -75 and 42, -73")

    features = stub.ListFeatures(rectangle)

    for feature in features:
        print("Feature called %s at %s" % (feature.name, feature.location))

def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    print("Ingrese dirección IP de la torre de control:")
    scanf(address)
    grpc.insecure_channel(address+':50052') #PUERTO 50052 = Thread del la torre de control encargada de enviar informacion
    stub = infocontrol_pb2_grpc.InfoServiceStub(channel)
    print("Pantalla de información - "+)


if __name__ == '__main__':
    run()
