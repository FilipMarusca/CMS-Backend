package server;

import service.common.IConferenceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Marius Adam
 */
class Threading {
    private final static Logger logger = Logger.getLogger(Threading.class.getName());

    /**
     * Creates worker thread to periodically check if clients are still connected
     * and if not disconnects them
     *
     * @param seconds       The number of seconds to wait before rechecking client
     * @param loggedClients The client to watch
     * @return The worker thread
     */
    @SuppressWarnings("InfiniteLoopStatement")
    static Thread getConnectionChecker(Map<String, IConferenceClient> loggedClients, int seconds) {
        return new Thread(() -> {
            while (true) {
                logger.info("Client health check iteration.");
                List<String> clientsToRemove = new ArrayList<>();
                loggedClients.forEach((s, iConferenceClient) -> {
                    try {
                        iConferenceClient.ping();
                        logger.info("Client " + s + " is connected,");
                    } catch (Throwable e) {
                        //could not call client so remove it
                        clientsToRemove.add(s);
                        logger.warning(e.getMessage());
                        logger.info("Client " + s + " will automatically be logged out.");
                    }
                });
                logger.info("Disconnecting " + clientsToRemove.size() + " clients");
                clientsToRemove.forEach(loggedClients::remove);

                try {
                    logger.info("Sleeping " + seconds + " seconds before clients recheck.");
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
