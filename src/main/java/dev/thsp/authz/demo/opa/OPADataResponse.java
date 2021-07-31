package dev.thsp.authz.demo.opa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OPADataResponse {
  private Result result;

  @lombok.Data
  public static class Result {
    private boolean allow;
    private String username;
    private String reason;
  }
}
