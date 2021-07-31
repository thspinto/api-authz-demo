package dev.thsp.authz.demo.opa;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OPADataRequest {
  private Map<String, Object> input;
}
