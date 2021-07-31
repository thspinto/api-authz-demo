package dev.thsp.authz.demo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "pii", description = "false")
public class DemoController {

  @GetMapping("/books")
  public List<String> getBooks() {
    return Arrays.asList("book1", "book2");
  }
}
