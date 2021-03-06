// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: planecontrol.proto

package avion.proto;

public interface PlaneMsgeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tareados.PlaneMsge)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *nombre aerolinea
   * </pre>
   *
   * <code>string airline = 1;</code>
   */
  java.lang.String getAirline();
  /**
   * <pre>
   *nombre aerolinea
   * </pre>
   *
   * <code>string airline = 1;</code>
   */
  com.google.protobuf.ByteString
      getAirlineBytes();

  /**
   * <pre>
   *nombre del avion
   * </pre>
   *
   * <code>string planeNumber = 2;</code>
   */
  java.lang.String getPlaneNumber();
  /**
   * <pre>
   *nombre del avion
   * </pre>
   *
   * <code>string planeNumber = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlaneNumberBytes();

  /**
   * <pre>
   *peso maximo
   * </pre>
   *
   * <code>int32 maxLoad = 3;</code>
   */
  int getMaxLoad();

  /**
   * <pre>
   *peso actual
   * </pre>
   *
   * <code>int32 currLoad = 4;</code>
   */
  int getCurrLoad();

  /**
   * <pre>
   *capacidad de combustible maxima
   * </pre>
   *
   * <code>int32 maxCapacity = 5;</code>
   */
  int getMaxCapacity();

  /**
   * <pre>
   *capacidad acutal de combustible
   * </pre>
   *
   * <code>int32 currCapacity = 6;</code>
   */
  int getCurrCapacity();

  /**
   * <pre>
   *direccion de torre fuente
   * </pre>
   *
   * <code>string sourceAddress = 7;</code>
   */
  java.lang.String getSourceAddress();
  /**
   * <pre>
   *direccion de torre fuente
   * </pre>
   *
   * <code>string sourceAddress = 7;</code>
   */
  com.google.protobuf.ByteString
      getSourceAddressBytes();

  /**
   * <pre>
   *direccion de torre de destino
   * </pre>
   *
   * <code>string destAddress = 8;</code>
   */
  java.lang.String getDestAddress();
  /**
   * <pre>
   *direccion de torre de destino
   * </pre>
   *
   * <code>string destAddress = 8;</code>
   */
  com.google.protobuf.ByteString
      getDestAddressBytes();

  /**
   * <pre>
   *pista en la que se encuentra el avion, -1 si no esta en pista (en vuelo)
   * </pre>
   *
   * <code>int32 runway = 9;</code>
   */
  int getRunway();

  /**
   * <pre>
   *nombre del destino del avion
   * </pre>
   *
   * <code>string destName = 10;</code>
   */
  java.lang.String getDestName();
  /**
   * <pre>
   *nombre del destino del avion
   * </pre>
   *
   * <code>string destName = 10;</code>
   */
  com.google.protobuf.ByteString
      getDestNameBytes();

  /**
   * <pre>
   *tiempo de entrada/salida (?)
   * </pre>
   *
   * <code>string time = 11;</code>
   */
  java.lang.String getTime();
  /**
   * <pre>
   *tiempo de entrada/salida (?)
   * </pre>
   *
   * <code>string time = 11;</code>
   */
  com.google.protobuf.ByteString
      getTimeBytes();
}
