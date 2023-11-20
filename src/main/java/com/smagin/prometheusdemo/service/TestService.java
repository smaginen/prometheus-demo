package com.smagin.prometheusdemo.service;

import com.smagin.prometheusdemo.data.model.Test;
import com.smagin.prometheusdemo.data.repo.TestRepository;
import com.smagin.prometheusdemo.rest.TestDto;
import io.micrometer.common.util.StringUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {
  private final TestRepository testRepository;
  private final RedisTemplate<String, String> redisTemplate;

  public TestDto getTest(Long id) {
    final var fromCache = redisTemplate.opsForValue().get(String.valueOf(id));
    if (StringUtils.isBlank(fromCache)) {
      return getFromDB(id);
    }
    return new TestDto(id, fromCache);
  }

  private TestDto getFromDB(Long id) {
    final var optTest = testRepository.findById(id);
    if (optTest.isEmpty()) {
      return new TestDto(id, "Not found");
    }
    final var text = optTest.get().getText();
    redisTemplate.opsForValue().set(id.toString(), text);
    return new TestDto(id, text);
  }

  public TestDto saveTest(String text) {
    final var test = new Test();
    test.setText(text);
    final var savedTest = testRepository.save(test);
    return new TestDto(savedTest.getId(), savedTest.getText());
  }
}
