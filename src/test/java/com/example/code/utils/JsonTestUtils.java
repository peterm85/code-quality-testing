package com.example.code.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

@SuppressWarnings("deprecation")
public class JsonTestUtils {
  protected static final String BASE_PATH = "src/test/resources/%s/";
  private static final String INPUT_PATH = "input/";
  private static final String EXPECTED_PATH = "expected/";

  protected static ObjectMapper objectMapper = new ObjectMapper();

  static {
    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm.SSS'Z'");
    objectMapper.setDateFormat(df);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
  }

  @SneakyThrows
  protected <T> T readFile(final String path, final String fileName, final Class<T> clazz) {
    final String fullPath = String.format(BASE_PATH, path) + INPUT_PATH + fileName;
    return objectMapper.readValue(new File(fullPath), clazz);
  }

  @SneakyThrows
  protected void checkResult(final Object obj, final String path, final String expectedFileName) {
    final String fullPath = String.format(BASE_PATH, path) + EXPECTED_PATH + expectedFileName;
    final String result = objectMapper.writeValueAsString(obj);
    final String expected = new String(Files.readAllBytes(Paths.get(fullPath)));
    JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
  }
}
