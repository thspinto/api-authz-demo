package dev.thsp.authz.demo.opa;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.client.RestTemplate;

public class OPAVoter implements AccessDecisionVoter<Object> {

  private final String opaUrl;

  public OPAVoter(String opaUrl) {
    this.opaUrl = opaUrl;
  }

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class clazz) {
    return true;
  }

  @Override
  public int vote(Authentication authentication, Object obj, Collection<ConfigAttribute> attrs) {
    if (!(obj instanceof FilterInvocation)) {
      return ACCESS_ABSTAIN;
    }

    FilterInvocation filter = (FilterInvocation) obj;
    String header = filter.getRequest().getHeader("Authorization");
    String token = "";
    if (header == null || !header.startsWith("Bearer ")) {
      token = header.substring(7);
    }

    Map<String, Object> input = new HashMap<>();
    input.put("auth", authentication);
    input.put("method", filter.getRequest().getMethod());
    input.put("path", filter.getRequest().getRequestURI());
    input.put("token", token);

    RestTemplate client = new RestTemplate();
    HttpEntity<?> request = new HttpEntity<>(new OPADataRequest(input));
    OPADataResponse response = client.postForObject(this.opaUrl, request, OPADataResponse.class);
    System.out.println(response.getResult().toString());

    if (response == null || !response.getResult().isAllow()) {
      return ACCESS_DENIED;
    }

    return ACCESS_GRANTED;
  }
}
