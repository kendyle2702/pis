package lqc.com.pis.service.inter;

import com.nimbusds.jose.JOSEException;
import lqc.com.pis.dto.request.IntrospectRequest;
import lqc.com.pis.dto.request.LoginRequest;
import lqc.com.pis.dto.response.IntrospectResponse;
import lqc.com.pis.dto.response.LoginResponse;

import java.text.ParseException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
