package ro.pao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pao.ejbs.ExchangeRateEjb;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Named;

@Named
@Singleton
@Startup
@Lock(LockType.READ)
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @EJB
    private ExchangeRateEjb exchangeRateEjb;

    @PostConstruct
    public void postConstructor() {
        logger.debug("postConstructor() - start");
        exchangeRateEjb.importExchangeRate();
        logger.debug("postConstructor() - end");
    }

}
