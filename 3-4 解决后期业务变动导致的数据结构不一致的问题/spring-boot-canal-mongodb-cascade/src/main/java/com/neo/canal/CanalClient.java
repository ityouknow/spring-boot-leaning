package com.neo.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.neo.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.Assert;

public class CanalClient {

    private final static Logger logger = LoggerFactory.getLogger(CanalClient.class);
    private volatile boolean running = false;
    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread t, Throwable e) {
            logger.error("parse events has an error", e);
        }
    };
    private Thread thread = null;
    private CanalConnector connector;
    private static String canal_get = "get_message batchId : {} , entrySize : {}";
    private static String canal_ack = "ack_message batchId : {} ";

    private String destination;

    public String getDestination() {
        return destination;
    }

    public CanalClient(String destination, CanalConnector connector) {
        this.destination = destination;
        this.connector = connector;
    }

    public void start() {
        Assert.notNull(connector, "connector is null");
        thread = new Thread(new Runnable() {
            public void run() {
                logger.info("destination:{} running", destination);
                process();
            }
        });
        thread.setUncaughtExceptionHandler(handler);
        thread.start();
        running = true;
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
    }

    private void process() {
        int batchSize = 4 * 1024;
        while (running) {
            try {
                MDC.put("destination", destination);
                connector.connect();
                connector.subscribe();
                while (running) {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId != -1 && size > 0) {
                        logger.info(canal_get, batchId, size);
                        MessageHandler messageHandler = SpringUtil.getBean("messageHandler", MessageHandler.class);
                        messageHandler.execute(message.getEntries());
//                        for (CanalEntry.Entry entry : message.getEntries()) {
//                            logger.info("parse event has an  data:" + entry.toString());
//                        }
                        logger.info(canal_ack, batchId);
                    }
                    connector.ack(batchId); // 提交确认
                }
            } catch (Exception e) {
                logger.error("process error!", e);
            } finally {
                connector.disconnect();
                MDC.remove("destination");
            }
        }
    }



}
