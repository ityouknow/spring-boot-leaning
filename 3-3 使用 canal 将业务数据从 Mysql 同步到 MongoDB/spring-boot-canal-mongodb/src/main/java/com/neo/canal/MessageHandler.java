package com.neo.canal;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.neo.service.DataService;
import com.neo.util.DBConvertUtil;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("messageHandler")
public class MessageHandler {

    private final static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    //行数据日志
    private static String row_format = "binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {} , delay : {}ms \n";
    //数据存储耗时日志
    private static String execute_format = "name[{},{}] , eventType : {} , rows : {} consume : {}ms \n";
    @Resource
    private DataService dataService;

    public boolean execute(List<CanalEntry.Entry> entrys) throws Exception {
        for (CanalEntry.Entry entry : entrys) {
            long executeTime = entry.getHeader().getExecuteTime();
            long startTime = System.currentTimeMillis();
            long delayTime = startTime - executeTime;
            //保存事务内变动数据
            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                CanalEntry.RowChange rowChage = null;
                try {
                    rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }
                CanalEntry.EventType eventType = rowChage.getEventType();
                logger.info(row_format, entry.getHeader().getLogfileName(), String.valueOf(entry.getHeader().getLogfileOffset()),
                        entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                        eventType, String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime));
                if (eventType == CanalEntry.EventType.ERASE || eventType == CanalEntry.EventType.TRUNCATE) {
                    logger.info(" sql ----> " + rowChage.getSql());
                    continue;
                } else if (eventType == CanalEntry.EventType.QUERY || rowChage.getIsDdl()) {
                    logger.info(" sql ----> " + rowChage.getSql());
                    continue;
                }
                for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                    if (eventType == CanalEntry.EventType.DELETE) {
                        dataService.delete(rowData.getBeforeColumnsList(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName());
                    } else if (eventType == CanalEntry.EventType.INSERT) {
                        dataService.insert(rowData.getAfterColumnsList(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName());
                    } else if (eventType == CanalEntry.EventType.UPDATE) {
                        dataService.update(rowData.getAfterColumnsList(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName());
                    } else {
                        logger.info("未知数据变动类型:{}", eventType);
                    }
                }
                long consumeTime = System.currentTimeMillis() - startTime;
                logger.info(execute_format,
                        entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                        eventType, String.valueOf(rowChage.getRowDatasCount()), String.valueOf(consumeTime));
            }
        }
        return true;
    }
}
