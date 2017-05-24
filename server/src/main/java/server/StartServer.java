package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Raul on 25/04/2017.
 */
public class StartServer {
    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-server.xml");

    }
}
