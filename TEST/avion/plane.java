// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/planecontrol.proto

package avion;

/**
 * Protobuf type {@code tareados.plane}
 */
public  final class plane extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tareados.plane)
    planeOrBuilder {
private static final long serialVersionUID = 0L;
  // Use plane.newBuilder() to construct.
  private plane(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private plane() {
    planeId_ = 0;
    sourceName_ = "";
    destName_ = "";
    departureTime_ = "";
    arrivalTime_ = "";
    sourceRunway_ = 0;
    destRunway_ = 0;
    altitude_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private plane(
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
          case 8: {

            planeId_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            sourceName_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            destName_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            departureTime_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            arrivalTime_ = s;
            break;
          }
          case 48: {

            sourceRunway_ = input.readInt32();
            break;
          }
          case 56: {

            destRunway_ = input.readInt32();
            break;
          }
          case 64: {

            altitude_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
    return avion.PlaneControl.internal_static_tareados_plane_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return avion.PlaneControl.internal_static_tareados_plane_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            avion.plane.class, avion.plane.Builder.class);
  }

  public static final int PLANE_ID_FIELD_NUMBER = 1;
  private int planeId_;
  /**
   * <code>int32 plane_id = 1;</code>
   */
  public int getPlaneId() {
    return planeId_;
  }

  public static final int SOURCE_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object sourceName_;
  /**
   * <code>string source_name = 2;</code>
   */
  public java.lang.String getSourceName() {
    java.lang.Object ref = sourceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      sourceName_ = s;
      return s;
    }
  }
  /**
   * <code>string source_name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getSourceNameBytes() {
    java.lang.Object ref = sourceName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      sourceName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEST_NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object destName_;
  /**
   * <code>string dest_name = 3;</code>
   */
  public java.lang.String getDestName() {
    java.lang.Object ref = destName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      destName_ = s;
      return s;
    }
  }
  /**
   * <code>string dest_name = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDestNameBytes() {
    java.lang.Object ref = destName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      destName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEPARTURE_TIME_FIELD_NUMBER = 4;
  private volatile java.lang.Object departureTime_;
  /**
   * <code>string departure_time = 4;</code>
   */
  public java.lang.String getDepartureTime() {
    java.lang.Object ref = departureTime_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      departureTime_ = s;
      return s;
    }
  }
  /**
   * <code>string departure_time = 4;</code>
   */
  public com.google.protobuf.ByteString
      getDepartureTimeBytes() {
    java.lang.Object ref = departureTime_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      departureTime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ARRIVAL_TIME_FIELD_NUMBER = 5;
  private volatile java.lang.Object arrivalTime_;
  /**
   * <code>string arrival_time = 5;</code>
   */
  public java.lang.String getArrivalTime() {
    java.lang.Object ref = arrivalTime_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      arrivalTime_ = s;
      return s;
    }
  }
  /**
   * <code>string arrival_time = 5;</code>
   */
  public com.google.protobuf.ByteString
      getArrivalTimeBytes() {
    java.lang.Object ref = arrivalTime_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      arrivalTime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SOURCE_RUNWAY_FIELD_NUMBER = 6;
  private int sourceRunway_;
  /**
   * <code>int32 source_runway = 6;</code>
   */
  public int getSourceRunway() {
    return sourceRunway_;
  }

  public static final int DEST_RUNWAY_FIELD_NUMBER = 7;
  private int destRunway_;
  /**
   * <code>int32 dest_runway = 7;</code>
   */
  public int getDestRunway() {
    return destRunway_;
  }

  public static final int ALTITUDE_FIELD_NUMBER = 8;
  private int altitude_;
  /**
   * <code>int32 altitude = 8;</code>
   */
  public int getAltitude() {
    return altitude_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (planeId_ != 0) {
      output.writeInt32(1, planeId_);
    }
    if (!getSourceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, sourceName_);
    }
    if (!getDestNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, destName_);
    }
    if (!getDepartureTimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, departureTime_);
    }
    if (!getArrivalTimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, arrivalTime_);
    }
    if (sourceRunway_ != 0) {
      output.writeInt32(6, sourceRunway_);
    }
    if (destRunway_ != 0) {
      output.writeInt32(7, destRunway_);
    }
    if (altitude_ != 0) {
      output.writeInt32(8, altitude_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (planeId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, planeId_);
    }
    if (!getSourceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, sourceName_);
    }
    if (!getDestNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, destName_);
    }
    if (!getDepartureTimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, departureTime_);
    }
    if (!getArrivalTimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, arrivalTime_);
    }
    if (sourceRunway_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, sourceRunway_);
    }
    if (destRunway_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, destRunway_);
    }
    if (altitude_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, altitude_);
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
    if (!(obj instanceof avion.plane)) {
      return super.equals(obj);
    }
    avion.plane other = (avion.plane) obj;

    boolean result = true;
    result = result && (getPlaneId()
        == other.getPlaneId());
    result = result && getSourceName()
        .equals(other.getSourceName());
    result = result && getDestName()
        .equals(other.getDestName());
    result = result && getDepartureTime()
        .equals(other.getDepartureTime());
    result = result && getArrivalTime()
        .equals(other.getArrivalTime());
    result = result && (getSourceRunway()
        == other.getSourceRunway());
    result = result && (getDestRunway()
        == other.getDestRunway());
    result = result && (getAltitude()
        == other.getAltitude());
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
    hash = (37 * hash) + PLANE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getPlaneId();
    hash = (37 * hash) + SOURCE_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getSourceName().hashCode();
    hash = (37 * hash) + DEST_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getDestName().hashCode();
    hash = (37 * hash) + DEPARTURE_TIME_FIELD_NUMBER;
    hash = (53 * hash) + getDepartureTime().hashCode();
    hash = (37 * hash) + ARRIVAL_TIME_FIELD_NUMBER;
    hash = (53 * hash) + getArrivalTime().hashCode();
    hash = (37 * hash) + SOURCE_RUNWAY_FIELD_NUMBER;
    hash = (53 * hash) + getSourceRunway();
    hash = (37 * hash) + DEST_RUNWAY_FIELD_NUMBER;
    hash = (53 * hash) + getDestRunway();
    hash = (37 * hash) + ALTITUDE_FIELD_NUMBER;
    hash = (53 * hash) + getAltitude();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static avion.plane parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.plane parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.plane parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.plane parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.plane parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static avion.plane parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static avion.plane parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static avion.plane parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static avion.plane parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static avion.plane parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static avion.plane parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static avion.plane parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(avion.plane prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code tareados.plane}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tareados.plane)
      avion.planeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return avion.PlaneControl.internal_static_tareados_plane_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return avion.PlaneControl.internal_static_tareados_plane_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              avion.plane.class, avion.plane.Builder.class);
    }

    // Construct using avion.plane.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      planeId_ = 0;

      sourceName_ = "";

      destName_ = "";

      departureTime_ = "";

      arrivalTime_ = "";

      sourceRunway_ = 0;

      destRunway_ = 0;

      altitude_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return avion.PlaneControl.internal_static_tareados_plane_descriptor;
    }

    @java.lang.Override
    public avion.plane getDefaultInstanceForType() {
      return avion.plane.getDefaultInstance();
    }

    @java.lang.Override
    public avion.plane build() {
      avion.plane result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public avion.plane buildPartial() {
      avion.plane result = new avion.plane(this);
      result.planeId_ = planeId_;
      result.sourceName_ = sourceName_;
      result.destName_ = destName_;
      result.departureTime_ = departureTime_;
      result.arrivalTime_ = arrivalTime_;
      result.sourceRunway_ = sourceRunway_;
      result.destRunway_ = destRunway_;
      result.altitude_ = altitude_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof avion.plane) {
        return mergeFrom((avion.plane)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(avion.plane other) {
      if (other == avion.plane.getDefaultInstance()) return this;
      if (other.getPlaneId() != 0) {
        setPlaneId(other.getPlaneId());
      }
      if (!other.getSourceName().isEmpty()) {
        sourceName_ = other.sourceName_;
        onChanged();
      }
      if (!other.getDestName().isEmpty()) {
        destName_ = other.destName_;
        onChanged();
      }
      if (!other.getDepartureTime().isEmpty()) {
        departureTime_ = other.departureTime_;
        onChanged();
      }
      if (!other.getArrivalTime().isEmpty()) {
        arrivalTime_ = other.arrivalTime_;
        onChanged();
      }
      if (other.getSourceRunway() != 0) {
        setSourceRunway(other.getSourceRunway());
      }
      if (other.getDestRunway() != 0) {
        setDestRunway(other.getDestRunway());
      }
      if (other.getAltitude() != 0) {
        setAltitude(other.getAltitude());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      avion.plane parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (avion.plane) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int planeId_ ;
    /**
     * <code>int32 plane_id = 1;</code>
     */
    public int getPlaneId() {
      return planeId_;
    }
    /**
     * <code>int32 plane_id = 1;</code>
     */
    public Builder setPlaneId(int value) {
      
      planeId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 plane_id = 1;</code>
     */
    public Builder clearPlaneId() {
      
      planeId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object sourceName_ = "";
    /**
     * <code>string source_name = 2;</code>
     */
    public java.lang.String getSourceName() {
      java.lang.Object ref = sourceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        sourceName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string source_name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSourceNameBytes() {
      java.lang.Object ref = sourceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sourceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string source_name = 2;</code>
     */
    public Builder setSourceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      sourceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string source_name = 2;</code>
     */
    public Builder clearSourceName() {
      
      sourceName_ = getDefaultInstance().getSourceName();
      onChanged();
      return this;
    }
    /**
     * <code>string source_name = 2;</code>
     */
    public Builder setSourceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      sourceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object destName_ = "";
    /**
     * <code>string dest_name = 3;</code>
     */
    public java.lang.String getDestName() {
      java.lang.Object ref = destName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        destName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string dest_name = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDestNameBytes() {
      java.lang.Object ref = destName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        destName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string dest_name = 3;</code>
     */
    public Builder setDestName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      destName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string dest_name = 3;</code>
     */
    public Builder clearDestName() {
      
      destName_ = getDefaultInstance().getDestName();
      onChanged();
      return this;
    }
    /**
     * <code>string dest_name = 3;</code>
     */
    public Builder setDestNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      destName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object departureTime_ = "";
    /**
     * <code>string departure_time = 4;</code>
     */
    public java.lang.String getDepartureTime() {
      java.lang.Object ref = departureTime_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        departureTime_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string departure_time = 4;</code>
     */
    public com.google.protobuf.ByteString
        getDepartureTimeBytes() {
      java.lang.Object ref = departureTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        departureTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string departure_time = 4;</code>
     */
    public Builder setDepartureTime(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      departureTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string departure_time = 4;</code>
     */
    public Builder clearDepartureTime() {
      
      departureTime_ = getDefaultInstance().getDepartureTime();
      onChanged();
      return this;
    }
    /**
     * <code>string departure_time = 4;</code>
     */
    public Builder setDepartureTimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      departureTime_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object arrivalTime_ = "";
    /**
     * <code>string arrival_time = 5;</code>
     */
    public java.lang.String getArrivalTime() {
      java.lang.Object ref = arrivalTime_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        arrivalTime_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string arrival_time = 5;</code>
     */
    public com.google.protobuf.ByteString
        getArrivalTimeBytes() {
      java.lang.Object ref = arrivalTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        arrivalTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string arrival_time = 5;</code>
     */
    public Builder setArrivalTime(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      arrivalTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string arrival_time = 5;</code>
     */
    public Builder clearArrivalTime() {
      
      arrivalTime_ = getDefaultInstance().getArrivalTime();
      onChanged();
      return this;
    }
    /**
     * <code>string arrival_time = 5;</code>
     */
    public Builder setArrivalTimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      arrivalTime_ = value;
      onChanged();
      return this;
    }

    private int sourceRunway_ ;
    /**
     * <code>int32 source_runway = 6;</code>
     */
    public int getSourceRunway() {
      return sourceRunway_;
    }
    /**
     * <code>int32 source_runway = 6;</code>
     */
    public Builder setSourceRunway(int value) {
      
      sourceRunway_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 source_runway = 6;</code>
     */
    public Builder clearSourceRunway() {
      
      sourceRunway_ = 0;
      onChanged();
      return this;
    }

    private int destRunway_ ;
    /**
     * <code>int32 dest_runway = 7;</code>
     */
    public int getDestRunway() {
      return destRunway_;
    }
    /**
     * <code>int32 dest_runway = 7;</code>
     */
    public Builder setDestRunway(int value) {
      
      destRunway_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 dest_runway = 7;</code>
     */
    public Builder clearDestRunway() {
      
      destRunway_ = 0;
      onChanged();
      return this;
    }

    private int altitude_ ;
    /**
     * <code>int32 altitude = 8;</code>
     */
    public int getAltitude() {
      return altitude_;
    }
    /**
     * <code>int32 altitude = 8;</code>
     */
    public Builder setAltitude(int value) {
      
      altitude_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 altitude = 8;</code>
     */
    public Builder clearAltitude() {
      
      altitude_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tareados.plane)
  }

  // @@protoc_insertion_point(class_scope:tareados.plane)
  private static final avion.plane DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new avion.plane();
  }

  public static avion.plane getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<plane>
      PARSER = new com.google.protobuf.AbstractParser<plane>() {
    @java.lang.Override
    public plane parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new plane(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<plane> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<plane> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public avion.plane getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
