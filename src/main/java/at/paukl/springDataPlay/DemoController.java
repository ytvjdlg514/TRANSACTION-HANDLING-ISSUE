package at.paukl.springDataPlay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.transaction.annotation.Transactional;

@RestController
public class DemoController {

    private final DemoService demoService;
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // GET http://localhost:8088/
    @GetMapping("/")
    public String dummy() {
        LOG.info("dummy() before service call");
        demoService.requiresNewTransaction();
        LOG.info("dummy() after service call");
        return "also try acccessing /nested";
    }

    @Transactional
    @GetMapping("/nested")
    public String nested(){
        LOG.info("nested() before service call");
        try {
            Thread.sleep(100L); //0.1초
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
            e.printStackTrace(); 
        }
        demoService.requiresNewTransaction();
        LOG.info("nested() after service call");
        return "done - try calling me multiple times in parallel";
    }
}