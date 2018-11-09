// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: planecontrol.proto

package avion.proto;

public final class PlaneControlProtos {
  private PlaneControlProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tareados_LandRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tareados_LandRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tareados_LandResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tareados_LandResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tareados_TakeoffRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tareados_TakeoffRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tareados_TakeoffResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tareados_TakeoffResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tareados_PlaneMsge_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tareados_PlaneMsge_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022planecontrol.proto\022\010tareados\"?\n\013LandRe" +
      "quest\022\014\n\004dest\030\001 \001(\t\022\"\n\005plane\030\002 \001(\0132\023.tar" +
      "eados.PlaneMsge\"Q\n\014LandResponse\022\016\n\006runwa" +
      "y\030\001 \001(\005\022\r\n\005queue\030\002 \001(\005\022\020\n\010altitude\030\003 \001(\005" +
      "\022\020\n\010destName\030\004 \001(\t\"a\n\016TakeoffRequest\022\"\n\005" +
      "plane\030\001 \001(\0132\023.tareados.PlaneMsge\022\014\n\004dest" +
      "\030\002 \001(\t\022\016\n\006instOK\030\003 \001(\010\022\r\n\005flyOK\030\004 \001(\010\"\251\001" +
      "\n\017TakeoffResponse\022\020\n\010altitude\030\001 \001(\005\022\016\n\006r" +
      "unway\030\002 \001(\005\022\014\n\004dest\030\003 \001(\t\022\020\n\010queuePos\030\004 " +
      "\001(\005\022\021\n\tprevPlane\030\005 \001(\t\022\016\n\006destOK\030\006 \001(\010\022\017" +
      "\n\007restrOK\030\007 \001(\010\022\016\n\006instOK\030\010 \001(\010\022\020\n\010destN" +
      "ame\030\t \001(\t\"\333\001\n\tPlaneMsge\022\017\n\007airline\030\001 \001(\t" +
      "\022\023\n\013planeNumber\030\002 \001(\t\022\017\n\007maxLoad\030\003 \001(\005\022\020" +
      "\n\010currLoad\030\004 \001(\005\022\023\n\013maxCapacity\030\005 \001(\005\022\024\n" +
      "\014currCapacity\030\006 \001(\005\022\025\n\rsourceAddress\030\007 \001" +
      "(\t\022\023\n\013destAddress\030\010 \001(\t\022\016\n\006runway\030\t \001(\005\022" +
      "\020\n\010destName\030\n \001(\t\022\014\n\004time\030\013 \001(\t2\230\001\n\023Plan" +
      "eControlService\022;\n\004Land\022\025.tareados.LandR" +
      "equest\032\026.tareados.LandResponse\"\000(\0010\001\022D\n\007" +
      "Takeoff\022\030.tareados.TakeoffRequest\032\031.tare" +
      "ados.TakeoffResponse\"\000(\0010\001B#\n\013avion.prot" +
      "oB\022PlaneControlProtosP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_tareados_LandRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tareados_LandRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tareados_LandRequest_descriptor,
        new java.lang.String[] { "Dest", "Plane", });
    internal_static_tareados_LandResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tareados_LandResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tareados_LandResponse_descriptor,
        new java.lang.String[] { "Runway", "Queue", "Altitude", "DestName", });
    internal_static_tareados_TakeoffRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_tareados_TakeoffRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tareados_TakeoffRequest_descriptor,
        new java.lang.String[] { "Plane", "Dest", "InstOK", "FlyOK", });
    internal_static_tareados_TakeoffResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_tareados_TakeoffResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tareados_TakeoffResponse_descriptor,
        new java.lang.String[] { "Altitude", "Runway", "Dest", "QueuePos", "PrevPlane", "DestOK", "RestrOK", "InstOK", "DestName", });
    internal_static_tareados_PlaneMsge_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_tareados_PlaneMsge_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tareados_PlaneMsge_descriptor,
        new java.lang.String[] { "Airline", "PlaneNumber", "MaxLoad", "CurrLoad", "MaxCapacity", "CurrCapacity", "SourceAddress", "DestAddress", "Runway", "DestName", "Time", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
