package com.plotorion.plotorion.models;

import com.plotorion.plotorion.models.enums.ProposalStatus;
import jakarta.persistence .*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "proposals")
public class Proposal {

        @Id
        @GeneratedValue
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "request_id", nullable = false)
        private CollaborationRequest request;

        @Lob
        private String sampleText;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private ProposalStatus proposalStatus = ProposalStatus.PENDING;

        @Column(nullable = false, updatable = false)
        private Instant submittedAt = Instant.now();
}
