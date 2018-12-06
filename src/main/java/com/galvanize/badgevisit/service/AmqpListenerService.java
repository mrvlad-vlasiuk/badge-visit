package com.galvanize.badgevisit.service;

import com.galvanize.badgevisit.entity.VisitorExtended;
import com.galvanize.badgevisit.entity.VisitorFrontEnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class AmqpListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpListenerService.class);

    @Autowired
    VisitService visitService;

    @RabbitListener(queues = "${amqp.verify.queue}")
    public void verifyListener(final VisitorFrontEnd visitor) {
        LOGGER.info("Received verify message: {} from Badge-A-Rama exchange topic.", visitor);
        visitService.createVisit(visitor);
    }

    @RabbitListener(queues = "${amqp.checkout.queue}")
    public void vcheckoutListener(final VisitorFrontEnd visitor) {
        LOGGER.info("Received checkout message: {} from Badge-A-Rama exchange topic.", visitor);
    }

}
