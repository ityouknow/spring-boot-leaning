package com.neo.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.neo.canal.config.CanalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@Component
public class CanalRunner implements CommandLineRunner {

    protected final static Logger logger = LoggerFactory.getLogger(CanalRunner.class);

    @Autowired
    private CanalProperties canalProperties;

    private final static List<CanalClient> canalClientList = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        List<String> destinations = canalProperties.getDestination();
        if (destinations != null && destinations.size() > 0) {
            for (String destination : destinations) {
                logger.info("## start the canal client : {}", destination);
                //单机版
                CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalProperties.getIp(), canalProperties.getPort()), destination, "", "");
                // 基于zookeeper动态获取canal server的地址，建立链接，其中一台server发生crash，可以支持failover
                //CanalConnector connector = CanalConnectors.newClusterConnector(canalProperties.getZkServers(), destination, "", "");
                CanalClient client = new CanalClient(destination, connector);
                client.start();
                canalClientList.add(client);
            }
        }
    }
}