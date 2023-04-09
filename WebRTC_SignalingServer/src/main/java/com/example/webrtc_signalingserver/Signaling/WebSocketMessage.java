package com.example.webrtc_signalingserver.Signaling;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

// 기능 : 프론트에 응답하는 시그널링용 Message
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {
    private String sender;
    private String type;
    private Long roomId;
    private String data;
    private List<String> allUsers;
    private String receiver;
    private Object offer;
    private Object answer;
    private Object candidate;
    private Object sdp;

    public void setFrom(String from) {
        this.sender = from;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setOffer(Objects offer) {
        this.offer = offer;
    }
    public void setCandidate(Object candidate) {
        this.candidate = candidate;
    }

    public void setSdp(Object sdp) {
        this.sdp = sdp;
    }
}