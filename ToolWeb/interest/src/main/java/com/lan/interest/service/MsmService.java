package com.lan.interest.service;

import java.util.Map;

public interface MsmService {
    public Boolean send(Map<String, Object> param, String email);
}

