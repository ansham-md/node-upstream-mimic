package com.amos.nodeupstream.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DPRequest {

    private long  consentid;

    private String tppName;

    public DPRequest(long consentid, String tppName) {
        this.consentid = consentid;
        this.tppName = tppName;
    }
}
