// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: planecontrol.proto

package avion.proto;

/**
 * Protobuf type {@code tareados.PlaneMsge}
 */
public  final class PlaneMsge extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tareados.PlaneMsge)
    PlaneMsgeOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PlaneMsge.newBuilder() to construct.
  private PlaneMsge(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlaneMsge() {
    airline_ = "";
    planeNumber_ = "";
    maxLoad_ = 0;
    currLoad_ = 0;
    maxCapacity_ = 0;
    currCapacity_ = 0;
    sourceAddress_ = "";
    destAddress_ = "";
    runway_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlaneMsge(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            airline_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            planeNumber_ = s;
            break;
          }
          case 24: {

            maxLoad_ = input.readInt32();
            break;
          }
          case 32: {

            currLoad_ = input.readInt32();
            break;
          }
          case 40: {

            maxCapacity_ = input.readInt32();
            break;
          }
          case 48: {

            currCapacity_ = input.readInt32();
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            sourceAddress_ = s;
            break;
          }
          case 66: {
            java.lang.String s = input.readStringRequireUtf8();

            destAddress_ = s;
            break;
          }
          case 72: {

            runway_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return avion.proto.PlaneControlProtos.internal_static_tareados_PlaneMsge_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return avion.proto.PlaneControlProtos.internal_static_tareados_PlaneMsge_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            avion.proto.PlaneMsge.class, avion.proto.PlaneMsge.Builder.class);
  }

  public static final int AIRLINE_FIELD_NUMBER = 1;
  private volatile java.lang.Object airline_;
  /**
   * <pre>
   *nombre aerolinea
   * </pre>
   *
   * <code>string airline = 1;</code>
   */
  public java.lang.String getAirline() {
    java.lang.Object ref = airline_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      airline_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *nombre aerolinea
   * </pre>
   *
   * <code>string airline = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAirlineBytes() {
    java.lang.Object ref = airline_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      airline_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PLANENUMBER_FIELD_NUMBER = 2;
  private volatile java.lang.Object planeNumber_;
  /**
   * <pre>
   *nombre del avion
   * </pre>
   *
   * <code>string planeNumber = 2;</code>
   */
  public java.lang.String getPlaneNumber() {
    java.lang.Object ref = planeNumber_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      planeNumber_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *nombre del avion
   * </pre>
   *
   * <code>string planeNumber = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPlaneNumberBytes() {
    java.lang.Object ref = planeNumber_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      planeNumber_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MAXLOAD_FIELD_NUMBER = 3;
  private int maxLoad_;
  /**
   * <pre>
   *peso maximo
   * </pre>
   *
   * <code>int32 maxLoad = 3;</code>
   */
  public int getMaxLoad() {
    return maxLoad_;
  }

  public static final int CURRLOAD_FIELD_NUMBER = 4;
  private int currLoad_;
  /**
   * <pre>
   *peso actual
   * </pre>
   *
   * <code>int32 currLoad = 4;</code>
   */
  public int getCurrLoad() {
    return currLoad_;
  }

  public static final int MAXCAPACITY_FIELD_NUMBER = 5;
  private int maxCapacity_;
  /**
   * <pre>
   *capacidad de combustible maxima
   * </pre>
   *
   * <code>int32 maxCapacity = 5;</code>
   */
  public int getMaxCapacity() {
    return maxCapacity_;
  }

  public static final int CURRCAPACITY_FIELD_NUMBER = 6;
  private int currCapacity_;
  /**
   * <pre>
   *capacidad acutal de combustible
   * </pre>
   *
   * <code>int32 currCapacity = 6;</code>
   */
  public int getCurrCapacity() {
    return currCapacity_;
  }

  public static final int SOURCEADDRESS_FIELD_NUMBER = 7;
  private volatile java.lang.Object sourceAddress_;
  /**
   * <pre>
   *direccion de torre fuente
   * </pre>
   *
   * <code>string sourceAddress = 7;</code>
   */
  public java.lang.String getSourceAddress() {
    java.lang.Object ref = sourceAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      sourceAddress_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *direccion de torre fuente
   * </pre>
   *
   * <code>string sourceAddress = 7;</code>
   */
  public com.google.protobuf.ByteString
      getSourceAddressBytes() {
    java.lang.Object ref = sourceAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sourceAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESTADDRESS_FIELD_NUMBER = 8;
  private volatile java.lang.Object destAddress_;
  /**
   * <pre>
   *direccion de torre de destino
   * </pre>
   *
   * <code>string destAddress = 8;</code>
   */
  public java.lang.String getDestAddress() {
    java.lang.Object ref = destAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      destAddress_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *direccion de torre de destino
   * </pre>
   *
   * <code>string destAddress = 8;</code>
   */
  public com.google.protobuf.ByteString
      getDestAddressBytes() {
    java.lang.Object ref = destAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      destAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RUNWAY_FIELD_NUMBER = 9;
  private int runway_;
  /**
   * <pre>
   *pista en la que se encuentra el avion, -1 si no esta en pista (en vuelo)
   * </pre>
   *
   * <code>int32 runway = 9;</code>
   */
  public int getRunway() {
    return runway_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getAirlineBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, airline_);
    }
    if (!getPlaneNumberBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, planeNumber_);
    }
    if (maxLoad_ != 0) {
      output.writeInt32(3, maxLoad_);
    }
    if (currLoad_ != 0) {
      output.writeInt32(4, currLoad_);
    }
    if (maxCapacity_ != 0) {
      output.writeInt32(5, maxCapacity_);
    }
    if (currCapacity_ != 0) {
      output.writeInt32(6, currCapacity_);
    }
    if (!getSourceAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, sourceAddress_);
    }
    if (!getDestAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 8, destAddress_);
    }
    if (runway_ != 0) {
      output.writeInt32(9, runway_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAirlineBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, airline_);
    }
    if (!getPlaneNumberBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, planeNumber_);
    }
    if (maxLoad_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, maxLoad_);
    }
    if (currLoad_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, currLoad_);
    }
    if (maxCapacity_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, maxCapacity_);
    }
    if (currCapacity_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, currCapacity_);
    }
    if (!getSourceAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, sourceAddress_);
    }
    if (!getDestAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(8, destAddress_);
    }
    if (runway_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, runway_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof avion.proto.PlaneMsge)) {
      return super.equals(obj);
    }
    avion.proto.PlaneMsge other = (avion.proto.PlaneMsge) obj;

    boolean result = true;
    result = result && getAirline()
        .equals(other.getAirline());
    result = result && getPlaneNumber()
        .equals(other.getPlaneNumber());
    result = result && (getMaxLoad()
        == other.getMaxLoad());
    result = result && (getCurrLoad()
        == other.getCurrLoad());
    result = result && (getMaxCapacity()
        == other.getMaxCapacity());
    result = result && (getCurrCapacity()
        == other.getCurrCapacity());
    result = result && getSourceAddress()
        .equals(other.getSourceAddress());
    result = result && getDestAddress()
        .equals(other.getDestAddress());
    result = result && (getRunway()
        == other.getRunway());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + AIRLINE_FIELD_NUMBER;
    hash = (53 * hash) + getAirline().hashCode();
    hash = (37 * hash) + PLANENUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getPlaneNumber().hashCode();
    hash = (37 * hash) + MAXLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getMaxLoad();
    hash = (37 * hash) + CURRLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getCurrLoad();
    hash = (37 * hash) + MAXCAPACITY_FIELD_NUMBER;
    hash = (53 * hash) + getMaxCapacity();
    hash = (37 * hash) + CURRCAPACITY_FIELD_NUMBER;
    hash = (53 * hash) + getCurrCapacity();
    hash = (37 * hash) + SOURCEADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getSourceAddress().hashCode();
    hash = (37 * hash) + DESTADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getDestAddress().hashCode();
    hash = (37 * hash) + RUNWAY_FIELD_NUMBER;
    hash = (53 * hash) + getRunway();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static avion.proto.PlaneMsge parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.proto.PlaneMsge parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.proto.PlaneMsge parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.proto.PlaneMsge parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.proto.PlaneMsge parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.proto.PlaneMsge parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.proto.PlaneMsge parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static avion.proto.PlaneMsge parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static avion.proto.PlaneMsge parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static avion.proto.PlaneMsge parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static avion.proto.PlaneMsge parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static avion.proto.PlaneMsge parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(avion.proto.PlaneMsge prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code tareados.PlaneMsge}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tareados.PlaneMsge)
      avion.proto.PlaneMsgeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return avion.proto.PlaneControlProtos.internal_static_tareados_PlaneMsge_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return avion.proto.PlaneControlProtos.internal_static_tareados_PlaneMsge_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              avion.proto.PlaneMsge.class, avion.proto.PlaneMsge.Builder.class);
    }

    // Construct using avion.proto.PlaneMsge.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      airline_ = "";

      planeNumber_ = "";

      maxLoad_ = 0;

      currLoad_ = 0;

      maxCapacity_ = 0;

      currCapacity_ = 0;

      sourceAddress_ = "";

      destAddress_ = "";

      runway_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return avion.proto.PlaneControlProtos.internal_static_tareados_PlaneMsge_descriptor;
    }

    public avion.proto.PlaneMsge getDefaultInstanceForType() {
      return avion.proto.PlaneMsge.getDefaultInstance();
    }

    public avion.proto.PlaneMsge build() {
      avion.proto.PlaneMsge result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public avion.proto.PlaneMsge buildPartial() {
      avion.proto.PlaneMsge result = new avion.proto.PlaneMsge(this);
      result.airline_ = airline_;
      result.planeNumber_ = planeNumber_;
      result.maxLoad_ = maxLoad_;
      result.currLoad_ = currLoad_;
      result.maxCapacity_ = maxCapacity_;
      result.currCapacity_ = currCapacity_;
      result.sourceAddress_ = sourceAddress_;
      result.destAddress_ = destAddress_;
      result.runway_ = runway_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof avion.proto.PlaneMsge) {
        return mergeFrom((avion.proto.PlaneMsge)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(avion.proto.PlaneMsge other) {
      if (other == avion.proto.PlaneMsge.getDefaultInstance()) return this;
      if (!other.getAirline().isEmpty()) {
        airline_ = other.airline_;
        onChanged();
      }
      if (!other.getPlaneNumber().isEmpty()) {
        planeNumber_ = other.planeNumber_;
        onChanged();
      }
      if (other.getMaxLoad() != 0) {
        setMaxLoad(other.getMaxLoad());
      }
      if (other.getCurrLoad() != 0) {
        setCurrLoad(other.getCurrLoad());
      }
      if (other.getMaxCapacity() != 0) {
        setMaxCapacity(other.getMaxCapacity());
      }
      if (other.getCurrCapacity() != 0) {
        setCurrCapacity(other.getCurrCapacity());
      }
      if (!other.getSourceAddress().isEmpty()) {
        sourceAddress_ = other.sourceAddress_;
        onChanged();
      }
      if (!other.getDestAddress().isEmpty()) {
        destAddress_ = other.destAddress_;
        onChanged();
      }
      if (other.getRunway() != 0) {
        setRunway(other.getRunway());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      avion.proto.PlaneMsge parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (avion.proto.PlaneMsge) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object airline_ = "";
    /**
     * <pre>
     *nombre aerolinea
     * </pre>
     *
     * <code>string airline = 1;</code>
     */
    public java.lang.String getAirline() {
      java.lang.Object ref = airline_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        airline_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *nombre aerolinea
     * </pre>
     *
     * <code>string airline = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAirlineBytes() {
      java.lang.Object ref = airline_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        airline_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *nombre aerolinea
     * </pre>
     *
     * <code>string airline = 1;</code>
     */
    public Builder setAirline(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      airline_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *nombre aerolinea
     * </pre>
     *
     * <code>string airline = 1;</code>
     */
    public Builder clearAirline() {
      
      airline_ = getDefaultInstance().getAirline();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *nombre aerolinea
     * </pre>
     *
     * <code>string airline = 1;</code>
     */
    public Builder setAirlineBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      airline_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object planeNumber_ = "";
    /**
     * <pre>
     *nombre del avion
     * </pre>
     *
     * <code>string planeNumber = 2;</code>
     */
    public java.lang.String getPlaneNumber() {
      java.lang.Object ref = planeNumber_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        planeNumber_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *nombre del avion
     * </pre>
     *
     * <code>string planeNumber = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPlaneNumberBytes() {
      java.lang.Object ref = planeNumber_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        planeNumber_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *nombre del avion
     * </pre>
     *
     * <code>string planeNumber = 2;</code>
     */
    public Builder setPlaneNumber(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      planeNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *nombre del avion
     * </pre>
     *
     * <code>string planeNumber = 2;</code>
     */
    public Builder clearPlaneNumber() {
      
      planeNumber_ = getDefaultInstance().getPlaneNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *nombre del avion
     * </pre>
     *
     * <code>string planeNumber = 2;</code>
     */
    public Builder setPlaneNumberBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      planeNumber_ = value;
      onChanged();
      return this;
    }

    private int maxLoad_ ;
    /**
     * <pre>
     *peso maximo
     * </pre>
     *
     * <code>int32 maxLoad = 3;</code>
     */
    public int getMaxLoad() {
      return maxLoad_;
    }
    /**
     * <pre>
     *peso maximo
     * </pre>
     *
     * <code>int32 maxLoad = 3;</code>
     */
    public Builder setMaxLoad(int value) {
      
      maxLoad_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *peso maximo
     * </pre>
     *
     * <code>int32 maxLoad = 3;</code>
     */
    public Builder clearMaxLoad() {
      
      maxLoad_ = 0;
      onChanged();
      return this;
    }

    private int currLoad_ ;
    /**
     * <pre>
     *peso actual
     * </pre>
     *
     * <code>int32 currLoad = 4;</code>
     */
    public int getCurrLoad() {
      return currLoad_;
    }
    /**
     * <pre>
     *peso actual
     * </pre>
     *
     * <code>int32 currLoad = 4;</code>
     */
    public Builder setCurrLoad(int value) {
      
      currLoad_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *peso actual
     * </pre>
     *
     * <code>int32 currLoad = 4;</code>
     */
    public Builder clearCurrLoad() {
      
      currLoad_ = 0;
      onChanged();
      return this;
    }

    private int maxCapacity_ ;
    /**
     * <pre>
     *capacidad de combustible maxima
     * </pre>
     *
     * <code>int32 maxCapacity = 5;</code>
     */
    public int getMaxCapacity() {
      return maxCapacity_;
    }
    /**
     * <pre>
     *capacidad de combustible maxima
     * </pre>
     *
     * <code>int32 maxCapacity = 5;</code>
     */
    public Builder setMaxCapacity(int value) {
      
      maxCapacity_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *capacidad de combustible maxima
     * </pre>
     *
     * <code>int32 maxCapacity = 5;</code>
     */
    public Builder clearMaxCapacity() {
      
      maxCapacity_ = 0;
      onChanged();
      return this;
    }

    private int currCapacity_ ;
    /**
     * <pre>
     *capacidad acutal de combustible
     * </pre>
     *
     * <code>int32 currCapacity = 6;</code>
     */
    public int getCurrCapacity() {
      return currCapacity_;
    }
    /**
     * <pre>
     *capacidad acutal de combustible
     * </pre>
     *
     * <code>int32 currCapacity = 6;</code>
     */
    public Builder setCurrCapacity(int value) {
      
      currCapacity_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *capacidad acutal de combustible
     * </pre>
     *
     * <code>int32 currCapacity = 6;</code>
     */
    public Builder clearCurrCapacity() {
      
      currCapacity_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object sourceAddress_ = "";
    /**
     * <pre>
     *direccion de torre fuente
     * </pre>
     *
     * <code>string sourceAddress = 7;</code>
     */
    public java.lang.String getSourceAddress() {
      java.lang.Object ref = sourceAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sourceAddress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *direccion de torre fuente
     * </pre>
     *
     * <code>string sourceAddress = 7;</code>
     */
    public com.google.protobuf.ByteString
        getSourceAddressBytes() {
      java.lang.Object ref = sourceAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sourceAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *direccion de torre fuente
     * </pre>
     *
     * <code>string sourceAddress = 7;</code>
     */
    public Builder setSourceAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sourceAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *direccion de torre fuente
     * </pre>
     *
     * <code>string sourceAddress = 7;</code>
     */
    public Builder clearSourceAddress() {
      
      sourceAddress_ = getDefaultInstance().getSourceAddress();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *direccion de torre fuente
     * </pre>
     *
     * <code>string sourceAddress = 7;</code>
     */
    public Builder setSourceAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      sourceAddress_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object destAddress_ = "";
    /**
     * <pre>
     *direccion de torre de destino
     * </pre>
     *
     * <code>string destAddress = 8;</code>
     */
    public java.lang.String getDestAddress() {
      java.lang.Object ref = destAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        destAddress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *direccion de torre de destino
     * </pre>
     *
     * <code>string destAddress = 8;</code>
     */
    public com.google.protobuf.ByteString
        getDestAddressBytes() {
      java.lang.Object ref = destAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        destAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *direccion de torre de destino
     * </pre>
     *
     * <code>string destAddress = 8;</code>
     */
    public Builder setDestAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      destAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *direccion de torre de destino
     * </pre>
     *
     * <code>string destAddress = 8;</code>
     */
    public Builder clearDestAddress() {
      
      destAddress_ = getDefaultInstance().getDestAddress();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *direccion de torre de destino
     * </pre>
     *
     * <code>string destAddress = 8;</code>
     */
    public Builder setDestAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      destAddress_ = value;
      onChanged();
      return this;
    }

    private int runway_ ;
    /**
     * <pre>
     *pista en la que se encuentra el avion, -1 si no esta en pista (en vuelo)
     * </pre>
     *
     * <code>int32 runway = 9;</code>
     */
    public int getRunway() {
      return runway_;
    }
    /**
     * <pre>
     *pista en la que se encuentra el avion, -1 si no esta en pista (en vuelo)
     * </pre>
     *
     * <code>int32 runway = 9;</code>
     */
    public Builder setRunway(int value) {
      
      runway_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *pista en la que se encuentra el avion, -1 si no esta en pista (en vuelo)
     * </pre>
     *
     * <code>int32 runway = 9;</code>
     */
    public Builder clearRunway() {
      
      runway_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tareados.PlaneMsge)
  }

  // @@protoc_insertion_point(class_scope:tareados.PlaneMsge)
  private static final avion.proto.PlaneMsge DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new avion.proto.PlaneMsge();
  }

  public static avion.proto.PlaneMsge getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PlaneMsge>
      PARSER = new com.google.protobuf.AbstractParser<PlaneMsge>() {
    public PlaneMsge parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PlaneMsge(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlaneMsge> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlaneMsge> getParserForType() {
    return PARSER;
  }

  public avion.proto.PlaneMsge getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
