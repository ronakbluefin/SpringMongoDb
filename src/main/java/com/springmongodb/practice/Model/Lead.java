package com.springmongodb.practice.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Lead  {

    @Id
    private String id;
    private String jornayaLeadId;
    private String visitorTrackerId;
    private String callId;
    private String textId;
    private String channelId;
    private String leadType;
    private String tcpa;
    private String countryCode;
    private String subId;
    private String ipAddress;
    private String status;
    private String partition;
    private long timestamp;
    private long updatedAt;
    private long lastSentAt;
    private String verticalFullName;
    private String note;


    public Lead(String jornayaLeadId,
                String visitorTrackerId,
                String callId,
                String textId,
                String channelId,
                String leadType,
                String tcpa,
                String countryCode,
                String subId,
                String ipAddress,
                String status,
                String partition,
                long timestamp,
                long updatedAt,
                long lastSentAt,
                String verticalFullName,
                String note) {

        this.jornayaLeadId = jornayaLeadId;
        this.visitorTrackerId = visitorTrackerId;
        this.callId = callId;
        this.textId = textId;
        this.channelId = channelId;
        this.leadType = leadType;
        this.tcpa = tcpa;
        this.countryCode = countryCode;
        this.subId = subId;
        this.ipAddress = ipAddress;
        this.status = status;
        this.partition = partition;
        this.timestamp = timestamp;
        this.updatedAt = updatedAt;
        this.lastSentAt = lastSentAt;
        this.verticalFullName = verticalFullName;
        this.note = note;
    }


}
