package at.paukl.springDataPlay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Service
public class DemoService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoService.class);

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void requiresNewTransaction() {
        LOG.info("simulated db work - before");
        try {
            Thread.sleep(3000); //3초
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            e.printStackTrace(); 
        }
        LOG.info("simulated db work - after");
    }
}