package com.mexus.homeleisure.streaming.api.media.service;

import static org.springframework.http.HttpHeaders.ACCEPT_RANGES;
import static org.springframework.http.HttpHeaders.CONTENT_LENGTH;
import static org.springframework.http.HttpHeaders.CONTENT_RANGE;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 트레이닝 데이터 스트리밍 서비스
 *
 * @author always0ne
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class MediaService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public static final String VIDEO = "./video";
  public static final int BYTE_RANGE = 1024;

  /**
   * Streaming Media
   *
   * @param fileName String.
   * @param fileType String.
   * @return ResponseEntity.
   */
  public ResponseEntity<byte[]> streamingMedia(String fileName, String fileType)
      throws IOException {
    String fullFileName = fileName + "." + fileType;
    Long fileSize = getFileSizeByName(fullFileName);
    return ResponseEntity.status(HttpStatus.OK)
        .header(CONTENT_TYPE, "video/" + fileType)
        .header(CONTENT_LENGTH, String.valueOf(fileSize))
        .body(readByteRange(fullFileName, 0, fileSize - 1));
  }

  /**
   * Streaming Ranged Media
   *
   * @param fileName String.
   * @param fileType String.
   * @param range    String.
   * @return ResponseEntity.
   */
  public ResponseEntity<byte[]> streamingMediaRange(String fileName, String fileType, String range)
      throws IOException {
    String fullFileName = fileName + "." + fileType;
    Long fileSize = getFileSizeByName(fullFileName);

    String[] ranges = range.split("-");
    long rangeStart = Long.parseLong(ranges[0].substring(6));
    long rangeEnd;
    if (ranges.length > 1) {
      rangeEnd = Long.parseLong(ranges[1]);
      if (fileSize < rangeEnd) {
        rangeEnd = fileSize - 1;
      }
    } else {
      rangeEnd = fileSize - 1;
    }

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
        .header(CONTENT_TYPE, "video/" + fileType)
        .header(ACCEPT_RANGES, "bytes")
        .header(CONTENT_LENGTH, String.valueOf((rangeEnd - rangeStart) + 1))
        .header(CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + fileSize)
        .body(readByteRange(fullFileName, rangeStart, rangeEnd));
  }

  /**
   * ready file byte by byte.
   *
   * @param filename String.
   * @param start    long.
   * @param end      long.
   * @return byte array.
   * @throws IOException exception.
   */
  public byte[] readByteRange(String filename, long start, long end) throws IOException {
    try (InputStream inputStream = (Files.newInputStream(Paths.get(VIDEO, filename)));
         ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream()) {
      byte[] data = new byte[BYTE_RANGE];

      int nRead;
      while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
        bufferedOutputStream.write(data, 0, nRead);
      }
      bufferedOutputStream.flush();

      byte[] result = new byte[(int) (end - start) + 1];
      System.arraycopy(bufferedOutputStream.toByteArray(), (int) start, result, 0, result.length);
      return result;
    }
  }

  /**
   * Getting the size from file name
   *
   * @param fileName String.
   * @return Long.
   */
  public Long getFileSizeByName(String fileName) {
    return Optional.ofNullable(fileName)
        .map(file -> Paths.get(VIDEO, file))
        .map(this::getFileSizeByPath)
        .orElse(0L);
  }

  /**
   * Getting the size from the path.
   *
   * @param path Path.
   * @return Long.
   */
  private Long getFileSizeByPath(Path path) {
    try {
      return Files.size(path);
    } catch (IOException ioException) {
      logger.error("Error while getting the file size", ioException);
      return 0L;
    }
  }

  public ResponseEntity<byte[]> streamingVideo(Long trainingId, String httpRangeList)
      throws IOException {
    if (httpRangeList == null) {
      return streamingMedia(trainingId.toString(), "mp4");
    }
    return streamingMediaRange(trainingId.toString(), "mp4", httpRangeList);
  }

  public ResponseEntity<byte[]> streamingShape(Long trainingId, String httpRangeList)
      throws IOException {
    if (httpRangeList == null) {
      return streamingMedia(trainingId + "_shape", "mp4");
    }
    return streamingMediaRange(trainingId + "-shape", "mp4", httpRangeList);
  }
}
