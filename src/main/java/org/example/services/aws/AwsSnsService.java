package org.example.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AwsSnsService {
  private final AmazonSNS sns;

  @Qualifier("catalogEventsTopic")
  private final Topic topic;

  public AwsSnsService(final AmazonSNS sns, final Topic topic) {
    this.sns = Objects.requireNonNull(sns);
    this.topic = Objects.requireNonNull(topic);
  }

  public void publish(final MessageDTO message) {
    System.out.println(message.message());
    this.sns.publish(this.topic.getTopicArn(), message.message());
  }
}
