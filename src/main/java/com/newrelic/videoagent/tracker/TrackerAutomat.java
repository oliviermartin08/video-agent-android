package com.newrelic.videoagent.tracker;

import com.newrelic.videoagent.backend.BackendActions;
import com.newrelic.videoagent.utils.NRLog;

import java.util.EmptyStackException;
import java.util.Stack;

public class TrackerAutomat {

    public enum State {
        Stopped,
        Starting,
        Playing,
        Paused,
        Buffering,
        Seeking
    }

    public enum Transition {
        Autoplay,
        ClickPlay,
        ClickPause,
        ClickStop,
        FrameShown,
        InitBuffering,
        EndBuffering,
        VideoFinished,
        ErrorPlaying,
        InitDraggingSlider,
        EndDraggingSlider,
        Heartbeat,
        RenditionChanged
    }

    private BackendActions actions;
    private State state;
    private Stack<State> stateStack;
    private Boolean isAd;

    public TrackerAutomat() {
        actions = new BackendActions();
        state = State.Stopped;
        stateStack = new Stack<>();
        isAd = false;
    }

    // Getters and setters

    public State getState() {
        return state;
    }

    public BackendActions getActions() {
        return actions;
    }

    public Boolean getAd() {
        return isAd;
    }

    public void setAd(Boolean ad) {
        isAd = ad;
    }

    // Transitions and States

    public void doTransition(Transition tran) {
        NRLog.d(">>>> TRANSITION " + tran);

        if (!handleStateIndependantTransition(tran)) {
            switch (state) {
                default:
                case Stopped: {
                    performTransitionInStateStopped(tran);
                    break;
                }

                case Starting: {
                    performTransitionInStateStarting(tran);
                    break;
                }

                case Paused: {
                    performTransitionInStatePaused(tran);
                    break;
                }

                case Playing: {
                    performTransitionInStatePlaying(tran);
                    break;
                }

                case Seeking: {
                    performTransitionInStateSeeking(tran);
                    break;
                }

                case Buffering: {
                    performTransitionInStateBuffering(tran);
                    break;
                }
            }
        }
    }

    public void force(State state) {
        stateStack.removeAllElements();
        moveState(state);
    }

    // Private Transition and State Stuff

    private Boolean handleStateIndependantTransition(Transition tran) {
        switch (tran) {
            case Heartbeat: {
                sendHeartbeat();
                return true;
            }

            case RenditionChanged: {
                sendRenditionChange();
                return true;
            }

            case InitDraggingSlider: {
                sendSeekStart();
                moveStateAndPush(State.Seeking);
                return true;
            }

            case InitBuffering: {
                sendBufferStart();
                moveStateAndPush(State.Buffering);
                return true;
            }

            case ErrorPlaying: {
                sendError();
                return true;
            }

            // NOTE: this should happend only while playing or seeking, but the event is too important and strange things could happen in the state machine.
            case VideoFinished: {
                sendEnd();
                endState();
                return true;
            }

            default:
                return false;
        }
    }

    private void performTransitionInStateStopped(Transition tran) {
        if (tran == Transition.Autoplay || tran == Transition.ClickPlay) {
            sendRequest();
            moveState(State.Starting);
        }
    }

    private void performTransitionInStateStarting(Transition tran) {
        if (tran == Transition.FrameShown) {
            sendStart();
            moveState(State.Playing);
        }
    }

    private void performTransitionInStatePlaying(Transition tran) {
        if (tran == Transition.ClickPause) {
            sendPause();
            moveState(State.Paused);
        }
    }

    private void performTransitionInStatePaused(Transition tran) {
        if (tran == Transition.ClickPlay) {
            sendResume();
            moveState(State.Playing);
        }
    }

    private void performTransitionInStateSeeking(Transition tran) {
        if (tran == Transition.EndDraggingSlider) {
            sendSeekEnd();
            backToState();
        }
    }

    private void performTransitionInStateBuffering(Transition tran) {
        if (tran == Transition.EndBuffering) {
            sendBufferEnd();
            backToState();
        }
    }

    // Senders

    private void sendRequest() {
        if (!isAd) {
            actions.sendRequest();
        }
        else {
            actions.sendAdRequest();
        }
    }

    private void sendStart() {
        if (!isAd) {
            actions.sendStart();
        }
        else {
            actions.sendAdStart();
        }
    }

    private void sendEnd() {
        if (!isAd) {
            actions.sendEnd();
        }
        else {
            actions.sendAdEnd();
        }
    }

    private void sendPause() {
        if (!isAd) {
            actions.sendPause();
        }
        else {
            actions.sendAdPause();
        }
    }

    private void sendResume() {
        if (!isAd) {
            actions.sendResume();
        }
        else {
            actions.sendAdResume();
        }
    }

    private void sendSeekStart() {
        if (!isAd) {
            actions.sendSeekStart();
        }
        else {
            actions.sendAdSeekStart();
        }
    }

    private void sendSeekEnd() {
        if (!isAd) {
            actions.sendSeekEnd();
        }
        else {
            actions.sendAdSeekEnd();
        }
    }

    private void sendBufferStart() {
        if (!isAd) {
            actions.sendBufferStart();
        }
        else {
            actions.sendAdBufferStart();
        }
    }

    private void sendBufferEnd() {
        if (!isAd) {
            actions.sendBufferEnd();
        }
        else {
            actions.sendAdBufferEnd();
        }
    }

    private void sendHeartbeat() {
        if (!isAd) {
            actions.sendHeartbeat();
        }
        else {
            actions.sendAdHeartbeat();
        }
    }

    private void sendRenditionChange() {
        if (!isAd) {
            actions.sendRenditionChange();
        }
        else {
            actions.sendAdRenditionChange();
        }
    }

    private void sendError() {
        if (!isAd) {
            actions.sendError();
        }
        else {
            actions.sendAdError();
        }
    }

    // Utils

    private void moveState(State state) {
        this.state = state;
    }

    private void moveStateAndPush(State newState) {
        stateStack.push(state);
        this.state = newState;
    }

    private void backToState() {
        try {
            State prevState = stateStack.pop();
            state = prevState;
        }
        catch (EmptyStackException e) {
            NRLog.d("State Stack underun");
        }
    }

    private void endState() {
        stateStack.removeAllElements();
        moveState(State.Stopped);
    }
}
