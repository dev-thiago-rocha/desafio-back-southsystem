package com.thrtec.desafiobacksouthsystem.domain;

import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteDecisionType decision;

    @NotNull
    private Long votingSessionId;

    @NotNull
    private LocalDateTime createdAt;

}
