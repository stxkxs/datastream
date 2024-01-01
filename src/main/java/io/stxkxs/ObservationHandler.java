package io.stxkxs;

import io.micrometer.observation.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

public class ObservationHandler implements io.micrometer.observation.ObservationHandler<Observation.Context> {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void onStart(Observation.Context context) {
    log.info("before running the observation for context [{}]", context.getName());
  }

  @Override
  public void onStop(Observation.Context context) {
    log.info("after running the observation for context [{}]", context.getName());
  }

  @Override
  public boolean supportsContext(@NonNull Observation.Context context) {
    return true;
  }
}
