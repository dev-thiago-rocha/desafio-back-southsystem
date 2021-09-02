package com.thrtec.desafiobacksouthsystem.feign;

import com.thrtec.desafiobacksouthsystem.dto.userinfo.v1.ValidateCpfResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-info", url = "${desafio.feign.user-info.url}")
public interface UserInfoClient {

    @GetMapping("/{cpf}")
    ValidateCpfResponseDto validateCpf(@PathVariable String cpf);

}
