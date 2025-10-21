package com.example.diameter;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.diameter.dto.DiameterMessageResponse;
import com.example.diameter.service.DiameterMessageService;
import dk.i1.diameter.ProtocolConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiameterApplicationTests {

    @Autowired
    private DiameterMessageService service;

    @Test
    void buildsCapabilitiesMessage() {
        DiameterMessageResponse response = service.buildCapabilitiesMessage("test-host", "example.com");
        assertThat(response.commandCode()).isEqualTo(ProtocolConstants.DIAMETER_COMMAND_CAPABILITIES_EXCHANGE);
        assertThat(response.payloadHex()).isNotBlank();
        assertThat(response.avpCount()).isEqualTo(2);
    }
}
