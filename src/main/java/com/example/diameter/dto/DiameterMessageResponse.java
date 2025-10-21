package com.example.diameter.dto;

import java.util.List;

public record DiameterMessageResponse(
        int commandCode,
        int applicationId,
        boolean request,
        boolean proxiable,
        int avpCount,
        List<Integer> authApplications,
        List<Integer> accountingApplications,
        List<Integer> supportedVendors,
        String payloadHex
) {
}
