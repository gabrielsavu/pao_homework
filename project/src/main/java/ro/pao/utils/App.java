package ro.pao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

@Named
@Singleton
@Startup
@Lock(LockType.READ)
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @PostConstruct
    public void postConstructor() {
        logger.debug("postConstructor() - start");

        logger.debug("postConstructor() - end");
    }

}
