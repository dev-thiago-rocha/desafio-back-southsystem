package com.thrtec.desafiobacksouthsystem.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MaskUtil {

    private static final String RGX_NO_NUMBERS = "[^0-9]";

    public static String removeMaskFromCpf(final String cpf) {
        return cpf.replaceAll(RGX_NO_NUMBERS, "");
    }

}
