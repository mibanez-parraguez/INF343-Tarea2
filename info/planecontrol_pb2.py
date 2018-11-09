# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: planecontrol.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='planecontrol.proto',
  package='tareados',
  syntax='proto3',
  serialized_options=_b('\n\013avion.protoB\022PlaneControlProtosP\001'),
  serialized_pb=_b('\n\x12planecontrol.proto\x12\x08tareados\"?\n\x0bLandRequest\x12\x0c\n\x04\x64\x65st\x18\x01 \x01(\t\x12\"\n\x05plane\x18\x02 \x01(\x0b\x32\x13.tareados.PlaneMsge\"Q\n\x0cLandResponse\x12\x0e\n\x06runway\x18\x01 \x01(\x05\x12\r\n\x05queue\x18\x02 \x01(\x05\x12\x10\n\x08\x61ltitude\x18\x03 \x01(\x05\x12\x10\n\x08\x64\x65stName\x18\x04 \x01(\t\"R\n\x0eTakeoffRequest\x12\"\n\x05plane\x18\x01 \x01(\x0b\x32\x13.tareados.PlaneMsge\x12\x0c\n\x04\x64\x65st\x18\x02 \x01(\t\x12\x0e\n\x06instOK\x18\x03 \x01(\x08\"\xa9\x01\n\x0fTakeoffResponse\x12\x10\n\x08\x61ltitude\x18\x01 \x01(\x05\x12\x0e\n\x06runway\x18\x02 \x01(\x05\x12\x0c\n\x04\x64\x65st\x18\x03 \x01(\t\x12\x10\n\x08queuePos\x18\x04 \x01(\x05\x12\x11\n\tprevPlane\x18\x05 \x01(\t\x12\x0e\n\x06\x64\x65stOK\x18\x06 \x01(\x08\x12\x0f\n\x07restrOK\x18\x07 \x01(\x08\x12\x0e\n\x06instOK\x18\x08 \x01(\x08\x12\x10\n\x08\x64\x65stName\x18\t \x01(\t\"\xdb\x01\n\tPlaneMsge\x12\x0f\n\x07\x61irline\x18\x01 \x01(\t\x12\x13\n\x0bplaneNumber\x18\x02 \x01(\t\x12\x0f\n\x07maxLoad\x18\x03 \x01(\x05\x12\x10\n\x08\x63urrLoad\x18\x04 \x01(\x05\x12\x13\n\x0bmaxCapacity\x18\x05 \x01(\x05\x12\x14\n\x0c\x63urrCapacity\x18\x06 \x01(\x05\x12\x15\n\rsourceAddress\x18\x07 \x01(\t\x12\x13\n\x0b\x64\x65stAddress\x18\x08 \x01(\t\x12\x0e\n\x06runway\x18\t \x01(\x05\x12\x10\n\x08\x64\x65stName\x18\n \x01(\t\x12\x0c\n\x04time\x18\x0b \x01(\t\"$\n\x0bInfoRequest\x12\x15\n\rcontrol_tower\x18\x01 \x01(\t\"}\n\x0cInfoResponse\x12+\n\x0e\x64\x65parturePlane\x18\x01 \x03(\x0b\x32\x13.tareados.PlaneMsge\x12)\n\x0c\x61rrivalPlane\x18\x02 \x03(\x0b\x32\x13.tareados.PlaneMsge\x12\x15\n\rcontrol_tower\x18\x03 \x01(\t2\x98\x01\n\x13PlaneControlService\x12;\n\x04Land\x12\x15.tareados.LandRequest\x1a\x16.tareados.LandResponse\"\x00(\x01\x30\x01\x12\x44\n\x07Takeoff\x12\x18.tareados.TakeoffRequest\x1a\x19.tareados.TakeoffResponse\"\x00(\x01\x30\x01\x32H\n\x0bInfoService\x12\x39\n\x04Info\x12\x15.tareados.InfoRequest\x1a\x16.tareados.InfoResponse\"\x00\x30\x01\x42#\n\x0b\x61vion.protoB\x12PlaneControlProtosP\x01\x62\x06proto3')
)




_LANDREQUEST = _descriptor.Descriptor(
  name='LandRequest',
  full_name='tareados.LandRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='dest', full_name='tareados.LandRequest.dest', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='plane', full_name='tareados.LandRequest.plane', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=32,
  serialized_end=95,
)


_LANDRESPONSE = _descriptor.Descriptor(
  name='LandResponse',
  full_name='tareados.LandResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='runway', full_name='tareados.LandResponse.runway', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='queue', full_name='tareados.LandResponse.queue', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='altitude', full_name='tareados.LandResponse.altitude', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destName', full_name='tareados.LandResponse.destName', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=97,
  serialized_end=178,
)


_TAKEOFFREQUEST = _descriptor.Descriptor(
  name='TakeoffRequest',
  full_name='tareados.TakeoffRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='plane', full_name='tareados.TakeoffRequest.plane', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='dest', full_name='tareados.TakeoffRequest.dest', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='instOK', full_name='tareados.TakeoffRequest.instOK', index=2,
      number=3, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=180,
  serialized_end=262,
)


_TAKEOFFRESPONSE = _descriptor.Descriptor(
  name='TakeoffResponse',
  full_name='tareados.TakeoffResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='altitude', full_name='tareados.TakeoffResponse.altitude', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='runway', full_name='tareados.TakeoffResponse.runway', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='dest', full_name='tareados.TakeoffResponse.dest', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='queuePos', full_name='tareados.TakeoffResponse.queuePos', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='prevPlane', full_name='tareados.TakeoffResponse.prevPlane', index=4,
      number=5, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destOK', full_name='tareados.TakeoffResponse.destOK', index=5,
      number=6, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='restrOK', full_name='tareados.TakeoffResponse.restrOK', index=6,
      number=7, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='instOK', full_name='tareados.TakeoffResponse.instOK', index=7,
      number=8, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destName', full_name='tareados.TakeoffResponse.destName', index=8,
      number=9, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=265,
  serialized_end=434,
)


_PLANEMSGE = _descriptor.Descriptor(
  name='PlaneMsge',
  full_name='tareados.PlaneMsge',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='airline', full_name='tareados.PlaneMsge.airline', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='planeNumber', full_name='tareados.PlaneMsge.planeNumber', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='maxLoad', full_name='tareados.PlaneMsge.maxLoad', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='currLoad', full_name='tareados.PlaneMsge.currLoad', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='maxCapacity', full_name='tareados.PlaneMsge.maxCapacity', index=4,
      number=5, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='currCapacity', full_name='tareados.PlaneMsge.currCapacity', index=5,
      number=6, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='sourceAddress', full_name='tareados.PlaneMsge.sourceAddress', index=6,
      number=7, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destAddress', full_name='tareados.PlaneMsge.destAddress', index=7,
      number=8, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='runway', full_name='tareados.PlaneMsge.runway', index=8,
      number=9, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destName', full_name='tareados.PlaneMsge.destName', index=9,
      number=10, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='time', full_name='tareados.PlaneMsge.time', index=10,
      number=11, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=437,
  serialized_end=656,
)


_INFOREQUEST = _descriptor.Descriptor(
  name='InfoRequest',
  full_name='tareados.InfoRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='control_tower', full_name='tareados.InfoRequest.control_tower', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=658,
  serialized_end=694,
)


_INFORESPONSE = _descriptor.Descriptor(
  name='InfoResponse',
  full_name='tareados.InfoResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='departurePlane', full_name='tareados.InfoResponse.departurePlane', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='arrivalPlane', full_name='tareados.InfoResponse.arrivalPlane', index=1,
      number=2, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='control_tower', full_name='tareados.InfoResponse.control_tower', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=696,
  serialized_end=821,
)

_LANDREQUEST.fields_by_name['plane'].message_type = _PLANEMSGE
_TAKEOFFREQUEST.fields_by_name['plane'].message_type = _PLANEMSGE
_INFORESPONSE.fields_by_name['departurePlane'].message_type = _PLANEMSGE
_INFORESPONSE.fields_by_name['arrivalPlane'].message_type = _PLANEMSGE
DESCRIPTOR.message_types_by_name['LandRequest'] = _LANDREQUEST
DESCRIPTOR.message_types_by_name['LandResponse'] = _LANDRESPONSE
DESCRIPTOR.message_types_by_name['TakeoffRequest'] = _TAKEOFFREQUEST
DESCRIPTOR.message_types_by_name['TakeoffResponse'] = _TAKEOFFRESPONSE
DESCRIPTOR.message_types_by_name['PlaneMsge'] = _PLANEMSGE
DESCRIPTOR.message_types_by_name['InfoRequest'] = _INFOREQUEST
DESCRIPTOR.message_types_by_name['InfoResponse'] = _INFORESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

LandRequest = _reflection.GeneratedProtocolMessageType('LandRequest', (_message.Message,), dict(
  DESCRIPTOR = _LANDREQUEST,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.LandRequest)
  ))
_sym_db.RegisterMessage(LandRequest)

LandResponse = _reflection.GeneratedProtocolMessageType('LandResponse', (_message.Message,), dict(
  DESCRIPTOR = _LANDRESPONSE,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.LandResponse)
  ))
_sym_db.RegisterMessage(LandResponse)

TakeoffRequest = _reflection.GeneratedProtocolMessageType('TakeoffRequest', (_message.Message,), dict(
  DESCRIPTOR = _TAKEOFFREQUEST,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.TakeoffRequest)
  ))
_sym_db.RegisterMessage(TakeoffRequest)

TakeoffResponse = _reflection.GeneratedProtocolMessageType('TakeoffResponse', (_message.Message,), dict(
  DESCRIPTOR = _TAKEOFFRESPONSE,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.TakeoffResponse)
  ))
_sym_db.RegisterMessage(TakeoffResponse)

PlaneMsge = _reflection.GeneratedProtocolMessageType('PlaneMsge', (_message.Message,), dict(
  DESCRIPTOR = _PLANEMSGE,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.PlaneMsge)
  ))
_sym_db.RegisterMessage(PlaneMsge)

InfoRequest = _reflection.GeneratedProtocolMessageType('InfoRequest', (_message.Message,), dict(
  DESCRIPTOR = _INFOREQUEST,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.InfoRequest)
  ))
_sym_db.RegisterMessage(InfoRequest)

InfoResponse = _reflection.GeneratedProtocolMessageType('InfoResponse', (_message.Message,), dict(
  DESCRIPTOR = _INFORESPONSE,
  __module__ = 'planecontrol_pb2'
  # @@protoc_insertion_point(class_scope:tareados.InfoResponse)
  ))
_sym_db.RegisterMessage(InfoResponse)


DESCRIPTOR._options = None

_PLANECONTROLSERVICE = _descriptor.ServiceDescriptor(
  name='PlaneControlService',
  full_name='tareados.PlaneControlService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  serialized_start=824,
  serialized_end=976,
  methods=[
  _descriptor.MethodDescriptor(
    name='Land',
    full_name='tareados.PlaneControlService.Land',
    index=0,
    containing_service=None,
    input_type=_LANDREQUEST,
    output_type=_LANDRESPONSE,
    serialized_options=None,
  ),
  _descriptor.MethodDescriptor(
    name='Takeoff',
    full_name='tareados.PlaneControlService.Takeoff',
    index=1,
    containing_service=None,
    input_type=_TAKEOFFREQUEST,
    output_type=_TAKEOFFRESPONSE,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_PLANECONTROLSERVICE)

DESCRIPTOR.services_by_name['PlaneControlService'] = _PLANECONTROLSERVICE


_INFOSERVICE = _descriptor.ServiceDescriptor(
  name='InfoService',
  full_name='tareados.InfoService',
  file=DESCRIPTOR,
  index=1,
  serialized_options=None,
  serialized_start=978,
  serialized_end=1050,
  methods=[
  _descriptor.MethodDescriptor(
    name='Info',
    full_name='tareados.InfoService.Info',
    index=0,
    containing_service=None,
    input_type=_INFOREQUEST,
    output_type=_INFORESPONSE,
    serialized_options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_INFOSERVICE)

DESCRIPTOR.services_by_name['InfoService'] = _INFOSERVICE

# @@protoc_insertion_point(module_scope)
