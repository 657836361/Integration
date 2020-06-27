package com.camelot.admin.notice;

import com.camelot.admin.notice.server.ServerInstanceFactory;
import com.camelot.admin.notice.server.ServerInstanceService;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Admin
 * @date 2020/6/16
 */
@Slf4j
@Service
public class CustomNotifier extends AbstractStatusChangeNotifier {

    protected CustomNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(()->{
            if (event instanceof InstanceStatusChangedEvent) {
                String serverName = instance.getRegistration().getName();
                String serverStatus = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
                log.info("Instance {} ({}) is {}", serverName, event.getInstance(), serverStatus);
                ServerInstanceService serverInstanceService = ServerInstanceFactory.getConsumerService(serverStatus);
                if (serverInstanceService != null) {
                    serverInstanceService.execute(serverName,serverStatus);
                }else {
                    log.error("服务状态未被检测到");
                }
            } else {
                log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }
}
