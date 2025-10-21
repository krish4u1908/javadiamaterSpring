package com.example.diameter.service;

import com.example.diameter.dto.DiameterMessageResponse;
import dk.i1.diameter.AVP_UTF8String;
import dk.i1.diameter.Message;
import dk.i1.diameter.ProtocolConstants;
import dk.i1.diameter.node.Capability;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class DiameterMessageService {

    public DiameterMessageResponse buildCapabilitiesMessage(String originHost, String originRealm) {
        Capability capability = new Capability();
        capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_NASREQ);
        capability.addAcctApp(ProtocolConstants.DIAMETER_APPLICATION_NASREQ);

        Message message = new Message();
        message.hdr.command_code = ProtocolConstants.DIAMETER_COMMAND_CAPABILITIES_EXCHANGE;
        message.hdr.application_id = ProtocolConstants.DIAMETER_APPLICATION_COMMON;
        message.hdr.setRequest(true);
        message.hdr.setProxiable(true);
        message.add(new AVP_UTF8String(ProtocolConstants.DI_ORIGIN_HOST, originHost));
        message.add(new AVP_UTF8String(ProtocolConstants.DI_ORIGIN_REALM, originRealm));

        return new DiameterMessageResponse(
                message.hdr.command_code,
                message.hdr.application_id,
                message.hdr.isRequest(),
                message.hdr.isProxiable(),
                message.size(),
                toSortedList(capability.getAuthApps()),
                toSortedList(capability.getAcctApps()),
                toSortedList(capability.getSupportedVendors()),
                bytesToHex(message.encode()));
    }

    private List<Integer> toSortedList(Set<Integer> values) {
        List<Integer> list = new ArrayList<>(values);
        list.sort(Integer::compareTo);
        return list;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }
}
