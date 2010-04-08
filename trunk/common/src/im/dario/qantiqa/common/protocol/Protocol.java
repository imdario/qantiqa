// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/Protocol.proto

package im.dario.qantiqa.common.protocol;

public final class Protocol {
    private Protocol() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }

    public static final class hash extends com.google.protobuf.GeneratedMessage {
        // Use hash.newBuilder() to construct.
        private hash() {
            initFields();
        }

        private hash(boolean noInit) {
        }

        private static final hash defaultInstance;

        public static hash getDefaultInstance() {
            return defaultInstance;
        }

        public hash getDefaultInstanceForType() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.dario.qantiqa.common.protocol.Protocol.internal_static_hash_descriptor;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.dario.qantiqa.common.protocol.Protocol.internal_static_hash_fieldAccessorTable;
        }

        // optional string error = 1;
        public static final int ERROR_FIELD_NUMBER = 1;
        private boolean hasError;
        private java.lang.String error_ = "";

        public boolean hasError() {
            return hasError;
        }

        public java.lang.String getError() {
            return error_;
        }

        // optional string request = 2;
        public static final int REQUEST_FIELD_NUMBER = 2;
        private boolean hasRequest;
        private java.lang.String request_ = "";

        public boolean hasRequest() {
            return hasRequest;
        }

        public java.lang.String getRequest() {
            return request_;
        }

        private void initFields() {
        }

        public final boolean isInitialized() {
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            if (hasError()) {
                output.writeString(1, getError());
            }
            if (hasRequest()) {
                output.writeString(2, getRequest());
            }
            getUnknownFields().writeTo(output);
        }

        private int memoizedSerializedSize = -1;

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1)
                return size;

            size = 0;
            if (hasError()) {
                size += com.google.protobuf.CodedOutputStream
                        .computeStringSize(1, getError());
            }
            if (hasRequest()) {
                size += com.google.protobuf.CodedOutputStream
                        .computeStringSize(2, getRequest());
            }
            size += getUnknownFields().getSerializedSize();
            memoizedSerializedSize = size;
            return size;
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.hash parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(
                im.dario.qantiqa.common.protocol.Protocol.hash prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        public static final class Builder extends
                com.google.protobuf.GeneratedMessage.Builder<Builder> {
            private im.dario.qantiqa.common.protocol.Protocol.hash result;

            // Construct using
            // im.dario.qantiqa.common.protocol.Protocol.hash.newBuilder()
            private Builder() {
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new im.dario.qantiqa.common.protocol.Protocol.hash();
                return builder;
            }

            protected im.dario.qantiqa.common.protocol.Protocol.hash internalGetResult() {
                return result;
            }

            public Builder clear() {
                if (result == null) {
                    throw new IllegalStateException(
                            "Cannot call clear() after build().");
                }
                result = new im.dario.qantiqa.common.protocol.Protocol.hash();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(result);
            }

            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
                return im.dario.qantiqa.common.protocol.Protocol.hash
                        .getDescriptor();
            }

            public im.dario.qantiqa.common.protocol.Protocol.hash getDefaultInstanceForType() {
                return im.dario.qantiqa.common.protocol.Protocol.hash
                        .getDefaultInstance();
            }

            public boolean isInitialized() {
                return result.isInitialized();
            }

            public im.dario.qantiqa.common.protocol.Protocol.hash build() {
                if (result != null && !isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return buildPartial();
            }

            private im.dario.qantiqa.common.protocol.Protocol.hash buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                if (!isInitialized()) {
                    throw newUninitializedMessageException(result)
                            .asInvalidProtocolBufferException();
                }
                return buildPartial();
            }

            public im.dario.qantiqa.common.protocol.Protocol.hash buildPartial() {
                if (result == null) {
                    throw new IllegalStateException(
                            "build() has already been called on this Builder.");
                }
                im.dario.qantiqa.common.protocol.Protocol.hash returnMe = result;
                result = null;
                return returnMe;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof im.dario.qantiqa.common.protocol.Protocol.hash) {
                    return mergeFrom((im.dario.qantiqa.common.protocol.Protocol.hash) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(
                    im.dario.qantiqa.common.protocol.Protocol.hash other) {
                if (other == im.dario.qantiqa.common.protocol.Protocol.hash
                        .getDefaultInstance())
                    return this;
                if (other.hasError()) {
                    setError(other.getError());
                }
                if (other.hasRequest()) {
                    setRequest(other.getRequest());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet
                        .newBuilder(this.getUnknownFields());
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                    case 0:
                        this.setUnknownFields(unknownFields.build());
                        return this;
                    default: {
                        if (!parseUnknownField(input, unknownFields,
                                extensionRegistry, tag)) {
                            this.setUnknownFields(unknownFields.build());
                            return this;
                        }
                        break;
                    }
                    case 10: {
                        setError(input.readString());
                        break;
                    }
                    case 18: {
                        setRequest(input.readString());
                        break;
                    }
                    }
                }
            }

            // optional string error = 1;
            public boolean hasError() {
                return result.hasError();
            }

            public java.lang.String getError() {
                return result.getError();
            }

            public Builder setError(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result.hasError = true;
                result.error_ = value;
                return this;
            }

            public Builder clearError() {
                result.hasError = false;
                result.error_ = getDefaultInstance().getError();
                return this;
            }

            // optional string request = 2;
            public boolean hasRequest() {
                return result.hasRequest();
            }

            public java.lang.String getRequest() {
                return result.getRequest();
            }

            public Builder setRequest(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result.hasRequest = true;
                result.request_ = value;
                return this;
            }

            public Builder clearRequest() {
                result.hasRequest = false;
                result.request_ = getDefaultInstance().getRequest();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:hash)
        }

        static {
            defaultInstance = new hash(true);
            im.dario.qantiqa.common.protocol.Protocol.internalForceInit();
            defaultInstance.initFields();
        }

        // @@protoc_insertion_point(class_scope:hash)
    }

    public static final class gluons extends
            com.google.protobuf.GeneratedMessage {
        // Use gluons.newBuilder() to construct.
        private gluons() {
            initFields();
        }

        private gluons(boolean noInit) {
        }

        private static final gluons defaultInstance;

        public static gluons getDefaultInstance() {
            return defaultInstance;
        }

        public gluons getDefaultInstanceForType() {
            return defaultInstance;
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.dario.qantiqa.common.protocol.Protocol.internal_static_gluons_descriptor;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.dario.qantiqa.common.protocol.Protocol.internal_static_gluons_fieldAccessorTable;
        }

        // repeated string gluon = 1;
        public static final int GLUON_FIELD_NUMBER = 1;
        private java.util.List<java.lang.String> gluon_ = java.util.Collections
                .emptyList();

        public java.util.List<java.lang.String> getGluonList() {
            return gluon_;
        }

        public int getGluonCount() {
            return gluon_.size();
        }

        public java.lang.String getGluon(int index) {
            return gluon_.get(index);
        }

        private void initFields() {
        }

        public final boolean isInitialized() {
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            for (java.lang.String element : getGluonList()) {
                output.writeString(1, element);
            }
            getUnknownFields().writeTo(output);
        }

        private int memoizedSerializedSize = -1;

        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1)
                return size;

            size = 0;
            {
                int dataSize = 0;
                for (java.lang.String element : getGluonList()) {
                    dataSize += com.google.protobuf.CodedOutputStream
                            .computeStringSizeNoTag(element);
                }
                size += dataSize;
                size += 1 * getGluonList().size();
            }
            size += getUnknownFields().getSerializedSize();
            memoizedSerializedSize = size;
            return size;
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return newBuilder().mergeFrom(data, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            Builder builder = newBuilder();
            if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
                return builder.buildParsed();
            } else {
                return null;
            }
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input).buildParsed();
        }

        public static im.dario.qantiqa.common.protocol.Protocol.gluons parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return newBuilder().mergeFrom(input, extensionRegistry)
                    .buildParsed();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(
                im.dario.qantiqa.common.protocol.Protocol.gluons prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        public static final class Builder extends
                com.google.protobuf.GeneratedMessage.Builder<Builder> {
            private im.dario.qantiqa.common.protocol.Protocol.gluons result;

            // Construct using
            // im.dario.qantiqa.common.protocol.Protocol.gluons.newBuilder()
            private Builder() {
            }

            private static Builder create() {
                Builder builder = new Builder();
                builder.result = new im.dario.qantiqa.common.protocol.Protocol.gluons();
                return builder;
            }

            protected im.dario.qantiqa.common.protocol.Protocol.gluons internalGetResult() {
                return result;
            }

            public Builder clear() {
                if (result == null) {
                    throw new IllegalStateException(
                            "Cannot call clear() after build().");
                }
                result = new im.dario.qantiqa.common.protocol.Protocol.gluons();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(result);
            }

            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
                return im.dario.qantiqa.common.protocol.Protocol.gluons
                        .getDescriptor();
            }

            public im.dario.qantiqa.common.protocol.Protocol.gluons getDefaultInstanceForType() {
                return im.dario.qantiqa.common.protocol.Protocol.gluons
                        .getDefaultInstance();
            }

            public boolean isInitialized() {
                return result.isInitialized();
            }

            public im.dario.qantiqa.common.protocol.Protocol.gluons build() {
                if (result != null && !isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return buildPartial();
            }

            private im.dario.qantiqa.common.protocol.Protocol.gluons buildParsed()
                    throws com.google.protobuf.InvalidProtocolBufferException {
                if (!isInitialized()) {
                    throw newUninitializedMessageException(result)
                            .asInvalidProtocolBufferException();
                }
                return buildPartial();
            }

            public im.dario.qantiqa.common.protocol.Protocol.gluons buildPartial() {
                if (result == null) {
                    throw new IllegalStateException(
                            "build() has already been called on this Builder.");
                }
                if (result.gluon_ != java.util.Collections.EMPTY_LIST) {
                    result.gluon_ = java.util.Collections
                            .unmodifiableList(result.gluon_);
                }
                im.dario.qantiqa.common.protocol.Protocol.gluons returnMe = result;
                result = null;
                return returnMe;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof im.dario.qantiqa.common.protocol.Protocol.gluons) {
                    return mergeFrom((im.dario.qantiqa.common.protocol.Protocol.gluons) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(
                    im.dario.qantiqa.common.protocol.Protocol.gluons other) {
                if (other == im.dario.qantiqa.common.protocol.Protocol.gluons
                        .getDefaultInstance())
                    return this;
                if (!other.gluon_.isEmpty()) {
                    if (result.gluon_.isEmpty()) {
                        result.gluon_ = new java.util.ArrayList<java.lang.String>();
                    }
                    result.gluon_.addAll(other.gluon_);
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet
                        .newBuilder(this.getUnknownFields());
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                    case 0:
                        this.setUnknownFields(unknownFields.build());
                        return this;
                    default: {
                        if (!parseUnknownField(input, unknownFields,
                                extensionRegistry, tag)) {
                            this.setUnknownFields(unknownFields.build());
                            return this;
                        }
                        break;
                    }
                    case 10: {
                        addGluon(input.readString());
                        break;
                    }
                    }
                }
            }

            // repeated string gluon = 1;
            public java.util.List<java.lang.String> getGluonList() {
                return java.util.Collections.unmodifiableList(result.gluon_);
            }

            public int getGluonCount() {
                return result.getGluonCount();
            }

            public java.lang.String getGluon(int index) {
                return result.getGluon(index);
            }

            public Builder setGluon(int index, java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                result.gluon_.set(index, value);
                return this;
            }

            public Builder addGluon(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                if (result.gluon_.isEmpty()) {
                    result.gluon_ = new java.util.ArrayList<java.lang.String>();
                }
                result.gluon_.add(value);
                return this;
            }

            public Builder addAllGluon(
                    java.lang.Iterable<? extends java.lang.String> values) {
                if (result.gluon_.isEmpty()) {
                    result.gluon_ = new java.util.ArrayList<java.lang.String>();
                }
                super.addAll(values, result.gluon_);
                return this;
            }

            public Builder clearGluon() {
                result.gluon_ = java.util.Collections.emptyList();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:gluons)
        }

        static {
            defaultInstance = new gluons(true);
            im.dario.qantiqa.common.protocol.Protocol.internalForceInit();
            defaultInstance.initFields();
        }

        // @@protoc_insertion_point(class_scope:gluons)
    }

    private static com.google.protobuf.Descriptors.Descriptor internal_static_hash_descriptor;
    private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_hash_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.Descriptor internal_static_gluons_descriptor;
    private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_gluons_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = { "\n\024proto/Protocol.proto\"&\n\004hash\022\r\n\005error\030"
                + "\001 \001(\t\022\017\n\007request\030\002 \001(\t\"\027\n\006gluons\022\r\n\005gluo"
                + "n\030\001 \003(\tB\"\n im.dario.qantiqa.common.proto"
                + "col" };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            public com.google.protobuf.ExtensionRegistry assignDescriptors(
                    com.google.protobuf.Descriptors.FileDescriptor root) {
                descriptor = root;
                internal_static_hash_descriptor = getDescriptor()
                        .getMessageTypes().get(0);
                internal_static_hash_fieldAccessorTable = new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_hash_descriptor,
                        new java.lang.String[] { "Error", "Request", },
                        im.dario.qantiqa.common.protocol.Protocol.hash.class,
                        im.dario.qantiqa.common.protocol.Protocol.hash.Builder.class);
                internal_static_gluons_descriptor = getDescriptor()
                        .getMessageTypes().get(1);
                internal_static_gluons_fieldAccessorTable = new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_gluons_descriptor,
                        new java.lang.String[] { "Gluon", },
                        im.dario.qantiqa.common.protocol.Protocol.gluons.class,
                        im.dario.qantiqa.common.protocol.Protocol.gluons.Builder.class);
                return null;
            }
        };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(
                        descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {},
                        assigner);
    }

    public static void internalForceInit() {
    }

    // @@protoc_insertion_point(outer_class_scope)
}
