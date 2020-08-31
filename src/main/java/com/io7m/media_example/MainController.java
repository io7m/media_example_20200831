/*
 * Copyright © 2020 Mark Raynsford <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.media_example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public final class MainController implements Initializable
{
  @FXML
  private MediaView mediaView;

  public MainController()
  {

  }

  @Override
  public void initialize(
    final URL url,
    final ResourceBundle resourceBundle)
  {
    try (var stream = MainController.class.getResourceAsStream(
      "/com/io7m/media_example/water.mp4")) {

      final var tempFile = Files.createTempFile("water", ".mp4");
      Files.deleteIfExists(tempFile);
      Files.copy(stream, tempFile);

      final var mediaSource = tempFile.toUri().toString();
      System.err.println("Media source: " + mediaSource);

      final var media = new Media(mediaSource);
      final var mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      this.mediaView.setMediaPlayer(mediaPlayer);
    } catch (final IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
