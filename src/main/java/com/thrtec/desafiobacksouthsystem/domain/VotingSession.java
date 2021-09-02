package com.thrtec.desafiobacksouthsystem.domain;

import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionResultType;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voting_session")
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long topicId;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime finishDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VotingSessionStatusType status;

    @Enumerated(EnumType.STRING)
    private VotingSessionResultType result;

}
