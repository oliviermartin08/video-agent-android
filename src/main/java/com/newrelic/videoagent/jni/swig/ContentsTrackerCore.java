/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.newrelic.videoagent.jni.swig;

public class ContentsTrackerCore extends TrackerCore {
  private transient long swigCPtr;

  protected ContentsTrackerCore(long cPtr, boolean cMemoryOwn) {
    super(Core_WrapperJNI.ContentsTrackerCore_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ContentsTrackerCore obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        Core_WrapperJNI.delete_ContentsTrackerCore(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ContentsTrackerCore() {
    this(Core_WrapperJNI.new_ContentsTrackerCore(), true);
  }

  public void reset() {
    Core_WrapperJNI.ContentsTrackerCore_reset(swigCPtr, this);
  }

  public void setup() {
    Core_WrapperJNI.ContentsTrackerCore_setup(swigCPtr, this);
  }

  public void sendRequest() {
    Core_WrapperJNI.ContentsTrackerCore_sendRequest(swigCPtr, this);
  }

  public void sendStart() {
    Core_WrapperJNI.ContentsTrackerCore_sendStart(swigCPtr, this);
  }

  public void sendEnd() {
    Core_WrapperJNI.ContentsTrackerCore_sendEnd(swigCPtr, this);
  }

  public void sendPause() {
    Core_WrapperJNI.ContentsTrackerCore_sendPause(swigCPtr, this);
  }

  public void sendResume() {
    Core_WrapperJNI.ContentsTrackerCore_sendResume(swigCPtr, this);
  }

  public void sendSeekStart() {
    Core_WrapperJNI.ContentsTrackerCore_sendSeekStart(swigCPtr, this);
  }

  public void sendSeekEnd() {
    Core_WrapperJNI.ContentsTrackerCore_sendSeekEnd(swigCPtr, this);
  }

  public void sendBufferStart() {
    Core_WrapperJNI.ContentsTrackerCore_sendBufferStart(swigCPtr, this);
  }

  public void sendBufferEnd() {
    Core_WrapperJNI.ContentsTrackerCore_sendBufferEnd(swigCPtr, this);
  }

  public void sendHeartbeat() {
    Core_WrapperJNI.ContentsTrackerCore_sendHeartbeat(swigCPtr, this);
  }

  public void sendRenditionChange() {
    Core_WrapperJNI.ContentsTrackerCore_sendRenditionChange(swigCPtr, this);
  }

  public void sendError(String message) {
    Core_WrapperJNI.ContentsTrackerCore_sendError(swigCPtr, this, message);
  }

  public void sendPlayerReady() {
    Core_WrapperJNI.ContentsTrackerCore_sendPlayerReady(swigCPtr, this);
  }

  public void sendDownload() {
    Core_WrapperJNI.ContentsTrackerCore_sendDownload(swigCPtr, this);
  }

  public void sendCustomAction(String name) {
    Core_WrapperJNI.ContentsTrackerCore_sendCustomAction__SWIG_0(swigCPtr, this, name);
  }

  public void sendCustomAction(String name, SWIGTYPE_p_std__mapT_std__string_ValueHolder_t attr) {
    Core_WrapperJNI.ContentsTrackerCore_sendCustomAction__SWIG_1(swigCPtr, this, name, SWIGTYPE_p_std__mapT_std__string_ValueHolder_t.getCPtr(attr));
  }

  public boolean setTimestamp(double timestamp, String attributeName) {
    return Core_WrapperJNI.ContentsTrackerCore_setTimestamp(swigCPtr, this, timestamp, attributeName);
  }

  public void adHappened(double time) {
    Core_WrapperJNI.ContentsTrackerCore_adHappened(swigCPtr, this, time);
  }

}