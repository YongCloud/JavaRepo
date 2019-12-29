package com.xxl.job.executor.service.jobhandler;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value = "testLogJobHandler")
@Component
public class TestLogJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        StringBuilder sb = new StringBuilder(150);
        sb.append("select DATAID,DEVICENO,TESTTIME,LONGITUDE,POLICESTATION,LATITUDE ")
            .append("from MY_TABLE_XXX ")
            .append("where DBTIME>=TO_DATE(:start,'yyyy-mm-dd hh24:mi:ss') ")
            .append("and DBTIME<TO_DATE(:start,'yyyy-mm-dd hh24:mi:ss')");
        String srcSQL = sb.toString();
        XxlJobLogger.log("fetch data, SQL: {}", srcSQL);
        for (int i = 0; i < 10; i++) {
            XxlJobLogger.log("other log {}", i);
        }
        return ReturnT.SUCCESS;
    }
}
