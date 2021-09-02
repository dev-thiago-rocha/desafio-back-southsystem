package com.thrtec.desafiobacksouthsystem.dto.userinfo.v1;

import com.thrtec.desafiobacksouthsystem.enumeration.CpfVoteValidationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCpfResponseDto {

    private CpfVoteValidationType status;

}
