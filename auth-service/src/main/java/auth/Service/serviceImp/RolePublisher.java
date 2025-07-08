package auth.Service.serviceImp;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.UserRoleEvent;
import org.springframework.kafka.core.KafkaTemplate;
@RequiredArgsConstructor
public class RolePublisher {

    private final KafkaTemplate<String, UserRoleEvent> kafkaTemplate;

    public void publishRoleAssignment(UserRoleEvent event) {
        kafkaTemplate.send("user-role-events", event);
    }
}
