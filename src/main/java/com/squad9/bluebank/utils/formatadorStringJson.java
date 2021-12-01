package com.squad9.bluebank.utils;

import java.util.HashMap;
import java.util.Map;

public class formatadorStringJson {
    public static Map<String, String> formataUmRetornoGenerico(String topico, String msg) {
        Map<String, String> error = new HashMap<>();
        error.put(topico, msg);
        return error;
    }
}
