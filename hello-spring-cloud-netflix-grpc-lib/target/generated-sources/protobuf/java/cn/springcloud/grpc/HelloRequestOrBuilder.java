// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HiService.proto

package cn.springcloud.grpc;

public interface HelloRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.springcloud.grpc.HelloRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 age = 2;</code>
   */
  int getAge();

  /**
   * <code>repeated string hobbies = 3;</code>
   */
  java.util.List<java.lang.String>
      getHobbiesList();
  /**
   * <code>repeated string hobbies = 3;</code>
   */
  int getHobbiesCount();
  /**
   * <code>repeated string hobbies = 3;</code>
   */
  java.lang.String getHobbies(int index);
  /**
   * <code>repeated string hobbies = 3;</code>
   */
  com.google.protobuf.ByteString
      getHobbiesBytes(int index);

  /**
   * <code>map&lt;string, string&gt; tags = 4;</code>
   */
  int getTagsCount();
  /**
   * <code>map&lt;string, string&gt; tags = 4;</code>
   */
  boolean containsTags(
      java.lang.String key);
  /**
   * Use {@link #getTagsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getTags();
  /**
   * <code>map&lt;string, string&gt; tags = 4;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getTagsMap();
  /**
   * <code>map&lt;string, string&gt; tags = 4;</code>
   */

  java.lang.String getTagsOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; tags = 4;</code>
   */

  java.lang.String getTagsOrThrow(
      java.lang.String key);
}
