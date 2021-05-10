package com.dotin.model;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "emailId")
    private Long emailId;

    @Column(name = "filAddress")
    private String filAddress;

    public Attachment() {
    }

    public Attachment(Long emailId, String filAddress) {
        this.emailId = emailId;
        this.filAddress = filAddress;
    }

    public Attachment(Long id, Long emailId, String filAddress) {
        this.id = id;
        this.emailId = emailId;
        this.filAddress = filAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getFilAddress() {
        return filAddress;
    }

    public void setFilAddress(String filAddress) {
        this.filAddress = filAddress;
    }
}
