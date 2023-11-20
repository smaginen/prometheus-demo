package com.smagin.prometheusdemo.rest;

import com.smagin.prometheusdemo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {
  private final TestService testService;

  @GetMapping("/test")
  public TestDto getTest(@RequestParam Long id) {
    return testService.getTest(id);
  }

  @PostMapping("/test")
  public TestDto postTest(@RequestParam String text) {
    return testService.saveTest(text);
  }
}
